package com.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.bean.Beans;

/**
 * Servlet implementation class DispatcherServlet
 */
//URL매핑은 web.xml에서 하기로 한다. @WebServlet()대신에(매핑은 둘중 하나만 둘 다 들어가면 충돌남)
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//url 확인
		
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("Dispatcher"+request.getServletPath());
//		response.getWriter().println("Dispatcher"+request.getServletPath());
		String url = request.getServletPath();
		String key = url.substring(0, url.indexOf("/",1));//0은 처음부터 url.index는 찾는 글자(/ 찾는 글자가 /), 그리고 찾는 처음 위치가 1
		System.out.println(key);
		/*
		 * PrintWriter out = response.getWriter(); out.println("요청한 url " +
		 * request.getServletPath()+"<br>");
		 * out.println("요청하는 사용자의 ip"+request.getRemoteAddr()+"<br>");
		 * out.println("넘어가는 데이터 (쿼리 -get): "+request.getQueryString()+"<br>");
		 */
		try {
			//실행할 객체를 꺼낸다. 그리고 실행한다.
			//없는 키를 찾으면 객체를 못 찾아서 결과 null이 나오게 되고 nullPointerException이 일어난다.
			Beans.get(key).execute(request);
			
		}catch(Exception e) {
			e.printStackTrace();
			out.println("404 에러 - 없는 페이지 참조");
		}
	}

}
