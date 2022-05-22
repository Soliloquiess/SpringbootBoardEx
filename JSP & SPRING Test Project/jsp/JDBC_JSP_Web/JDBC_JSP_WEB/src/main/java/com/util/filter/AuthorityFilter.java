package com.util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.member.vo.LoginVO;
import com.util.init.Init;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
//@WebFilter("/AuthorityFilter") -> web.xml에 등록
public class AuthorityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	//필터가 전달받는건 ServletRequest가 아니긴 하지만 그 안에 있는 게 ServletRequest 그걸 받아서 꺼내고 캐스팅하는거
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 전처리 필터

		HttpServletRequest req = (HttpServletRequest)request;	//HttpServletRequest로 캐스팅 하고 담는다.
		HttpSession session = req.getSession();
		// null 이면 로그인을 하지 않았다.
		//만약 세션에서 넘어온정보가 널이면 로그인 안한거.
		
//		어플리케이션 생성해서 세션 집어넣고  세션생성해서 리퀘스트 집어넣고 리퀘스트 생성해서 페이지 컨텍스트 집어넣음.(HTTP세션에서)
		LoginVO loginVO = (LoginVO) session.getAttribute("login");	//로그인 꺼내서 타입을 캐스팅함(LoginVO로)
		// 사용자 권한
		Integer gradeNo = 0;	//맨 처음엔 0 넣음
		if(loginVO != null) gradeNo = loginVO.getGradeNo();
		//로그인 vo가 널이 아니면 gradeNo꺼내서 gradNo에 집어넣자.(널이면 못 집어넣음
		
		
		// 요청 url을 가저와 보자.(세션에서). 
		String url = req.getServletPath();
		System.out.println("AuthorityFilter.doFilter().url : " + url);
		
		// Init에서 권한 정보를 꺼내와서 활용해서 처리한다.
		Integer pageGradeNo = Init.authorityMap.get(url);	//꺼내서 키를 집어넣는다. (url이 키)
		
		//못가져 오면 null null이니까 int로 쓰기는 그렇고 Integer로 사용
		//null이 아닌경우만 쳌,./ 널이면 권한 체크할 필요가 없음.
		//null이면 모든 사용자가 다 볼수있다는 뜻임.
		
		// pageGradeNo == null -> 모든 사용자가 이용할 수 있는 페이지
		
		
		//이 부분은 어떤 페이지 들어갈 떄 권한 줄거냐 안 줄거냐 저장한거. 그러기 위해 필터 만들고 페이지에대한 정보 권한 정보를 저장해둬야한다다
		if(pageGradeNo != null) {
			// 로그인을 하지 않았으면 예외가 발생
			if(loginVO == null) throw new ServletException("로그인이 필요한 페이지 입니다.");
			if(gradeNo < pageGradeNo) throw new ServletException("당신은 페이지에 접근할 권한이 없습니다.");
			//pageGradeNo가 크면 권한이 없는 거. 1이면 권한이 없는거. 관리자가 처리할수 있는데 관리자가 아니므로 처리 못함.
			
		}
		
		// URL에 따른 처리로 간다.
		chain.doFilter(request, response);
		// 후처리 필터
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
