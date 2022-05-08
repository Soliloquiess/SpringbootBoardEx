package com.webjjang.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.util.bean.Beans;
import com.webjjang.util.view.ViewResolver;

/**
 * Servlet implementation class DispacherServlet
 */
// URL 맵핑은 web.xml에서 하기로 한다. @WebServlet() 대신에
public class DispacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// url 확인
//		PrintWriter out = response.getWriter();
//		out.println("요청한 URL : " + request.getServletPath() + "<br>");
//		out.println("요청하는 사용자의 IP : " + request.getRemoteAddr() + "<br>");
//		out.println("넘어가는 데이터(쿼리-get) : " + request.getQueryString() + "<br>");
		
		String url = request.getServletPath();
		// localhost/main.do -> localhost/main/main.do 
		System.out.println("DispacherServlet.service().url : " + url);
		if(url.equals("/main.do") || url.equals("/")) {
			response.sendRedirect("/main/main.do");
			return;
		}
		
		String jsp = "";
		
		try {
			// 만약에 url = /error/error_404.do 라면은 try를 처리하지 않는다.
			// error_404.do URL 대신에 /WEB-INF/views/error/error_404.jsp로 바로 보낸다.
			// 404오류인 경우 DispacherServlet으로 들어오지 않는다.
//			if(url.equals("/error/error_404.do")) {
//				jsp = "error/error_404";
//			} else {
			// controller를 선택하기 위한 키 - url에서 맨처음 글자부터 두번째 "/"까지 가져온다.
			// /board/list.do -> /board
			String key = url.substring(0, url.indexOf("/", 1));
			// 실행할 Controller를 Beans에서 꺼내와서 실행한다.
			jsp = executeController(key, request);
//			} // if 문 끝
			// jsp 문자열 맨 앞에 redirect:일고 붙어있으면 URL이동을 시킨다. sendRedirect()
			// 없으면 앞에 /WEB-INF 뒤에 .jsp를 붙여서 jsp를 이용한 HTML이 작성되게 프로그램한다.forward()
			System.out.println("DispacherSerlet.service().jsp : " + jsp);
			// HTML을 만들거나 페이지이동이 되는 처리 메서드 호출
			ViewResolver.forward(request, response, jsp);
			
		} catch (StringIndexOutOfBoundsException e) {
			// /*.do로 들어온 경우 URL로 사용하지 않는 패턴 (사용하는 패턴 : /*/*.do) -> 404 오류
			throw new ServletException("404 오류 - 요청하신 페이지가 존재하지 않습니다.");
			// TODO: handle exception
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("********* e.getCause() : " + e.getCause());
			System.out.println("********* e.getMessage() : " + e.getMessage());
			// jsp에 전달이 된다. 페이지 디렉티브 태그에서  isErrorPage="true" 속성을 지정하면 예외를 전달 받는다.
			// 즉 jsp가 catch 부분이 된다.
			String msg = (e.getMessage() != null)?e.getMessage():e.getCause().toString();
			throw new ServletException(msg);
		}
	} // service() 끝

	// 결과로 나오는 String - 표시를 할 JSP 정보이거나 이동해야할 URL정보
	private String executeController(String key, HttpServletRequest request) throws Exception {
		// Beans에서 꺼내는 Controller는 Init.inti()에서 확인할 수 있다.
		// /board/list.do -> BoardController
		Controller controller = Beans.getController(key);
		// *.do로 요청을 했으나 controller가 null이면 운영을 하지 않는 URL에 해당된다. -> 500번오류 -> 404 오류
		if (controller == null) throw new Exception("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
		System.out.println(controller.getClass().getSimpleName() + ".execute() -------- ");
		return controller.execute(request);
	}
	
}
