package com.main.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class init
 */
//@WebServlet()=>웹 정보를 설정 value = 접근 url, loadOnStartup = 서버의 시작과 함께 로드하는 순서 번호
@WebServlet(value = "/Init", loadOnStartup = 1)
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated constructor stub
    	try {
    		Class.forName("com.util.db.DBInfo");
    		
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    		throw new ServletException("드라이버가 존재하지 않습니다.");
    		
    	}
    }
}