package com.util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
//위치 기반이 URL 선언 - 모든 URL선언
@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public EncodingFilter() {
        super();
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//목적하는 프로그램 가기 전 실행 코드
		//request가 jsp에서 사용하고 있는 request아니므로 형 변환
		HttpServletRequest req = (HttpServletRequest) request;
		//한글처리
		req.setCharacterEncoding("utf-8");
		// pass the request along the filter chain
		//실행하려는 객체를 호출하는 역할- 필터가 더 있으면 필터를 호출하는 역할
		chain.doFilter(request, response);
		
		System.out.println(getClass().getSimpleName()+"한글처리 완료");
		//목적하는 프로그램 실행한 후 코드
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
