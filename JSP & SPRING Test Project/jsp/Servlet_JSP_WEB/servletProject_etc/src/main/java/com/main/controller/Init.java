package com.main.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class Init
 */
//@WebServlet("/Init")
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
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//서버가 시작되면서 해야할 일
		
		try {
			// DBInfo 확인 - 드라이버 확인 한다.
			Class.forName("com.util.db.DBInfo");
			// 객체를 생성해서 저장해 놓는다. -Beans 객체 - controllerMap, serviceMap, daoMap 만들어서 저장
		
			// 서로 연관되어 있는 객체를 넣어준다. (DI - Dependency Inject : 의존성 주입)
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("드라이버가 존재하지 않습니다.");
		}
	
		//오라클 드라이버 확인 - 딱 한번만 해야됨.(static 블록 사용해야 됨.)
		//객체 생성 & 저장 &조립
		
	}

}
