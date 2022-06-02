package com.main.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.board.controller.BoardController;
import com.notice.controller.NoticeController;
import com.util.bean.Beans;

/**
 * Servlet implementation class Init
 */
//@WebServlet("/Init")	-> web.xml에 등록
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
		System.out.println("서버로딩 - 초기화 작업 진행");
		//처리에 필요한 객체 생성 - 서버가 시작과 함께
		Beans.put("/board", new BoardController());	//beans안에 객체 저장해둠
		Beans.put("/notice", new NoticeController());	//beans안에 객체 저장해둠
	}
}
