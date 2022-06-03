package com.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.bean.Beans;
import com.util.view.ViewResolver;

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
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// url 확인
//		PrintWriter out = response.getWriter();
//		out.println("요청한 URL : " + request.getServletPath() + "<br>");
//		out.println("요청하는 사용자의 IP : " + request.getRemoteAddr() + "<br>");
//		out.println("넘어가는 데이터(쿼리-get) : " + request.getQueryString() + "<br>");
		
		String url = request.getServletPath();
		// localhost/main.do -> localhost/main/main.do 
		System.out.println("DispacherServlet.service().url : " + url);
		if(url.equals("/main.do") || url.equals("/")) {	//뭐로 받든 메인으로 가도록 설정
			response.sendRedirect("/main/main.do");
			return;
		}
		try {
			// controller를 선택하기 위한 키 - url에서 맨처음 글자부터 두번째 "/"까지 가져온다.
			// /board/list.do -> /board
			String key = url.substring(0, url.indexOf("/", 1));
			// 실행할 Controller를 Beans에서 꺼내와서 실행한다.
			String jsp = executeController(key, request);
			// jsp 문자열 맨 앞에 redirect:일고 붙어있으면 URL이동을 시킨다. sendRedirect()
			// 없으면 앞에 /WEB-INF 뒤에 .jsp를 붙여서 jsp를 이용한 HTML이 작성되게 프로그램한다.forward()
			System.out.println("DispacherSerlet.service().jsp : " + jsp);
			// HTML을 만들거나 페이지이동이 되는 처리 메서드 호출
			ViewResolver.forward(request, response, jsp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // service() 끝

	// 결과로 나오는 String - 표시를 할 JSP 정보이거나 이동해야할 URL정보
	private String executeController(String key, HttpServletRequest request) throws Exception {
		// Beans에서 꺼내는 Controller는 Init.inti()에서 확인할 수 있다.
				// /board/list.do -> BoardController
				
		Controller controller = Beans.getController(key);
//		System.out.println(controller.getClass().getSimpleName() + ".execute() -------- ");
		
		// *.do로 요청을 했으나 controller가 null이면 운영을 하지 않는 URL에 해당된다. -> 500번오류 -> 404 오류
		
		
		return controller.execute(request);
	}
	
}

//분석 - 흐름이나 처리되는 동작 원리

//1. web.xml
//servlet->운영 : 사용자 url입력 요청
//-url 패턴 :*.do
//2. com.main.controller.DispatcherServlet -> servlet
//-찾아볼 순서 : service()->doGet(), doPost()
//3.URL에 따른 Controller, Service, DAO 는 Init.init()에서 관련된 URL로 다 찾아본다.
//4. Controller-Service-DAO
