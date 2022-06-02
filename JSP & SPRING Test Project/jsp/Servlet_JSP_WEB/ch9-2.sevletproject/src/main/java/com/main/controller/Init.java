package com.main.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.board.controller.BoardController;
import com.board.dao.BoardDAO;
import com.board.service.BoardDeleteService;
import com.board.service.BoardListService;
import com.board.service.BoardUpdateService;
import com.board.service.BoardViewService;
import com.board.service.BoardWriteService;
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
		try {
			// DBInfo 확인 - 드라이버 확인한다.
			Class.forName("com.util.db.DBInfo");
			newAndSave();
			//서로 연관되어 있는 객체를 넣어준다.(DI = Dipendency inject: 의존성 주입)
			
			inject();
		} catch (ClassNotFoundException e) {
			// 객체를 생성해서 저장해둔다. - Beans객체, controllerMap, servicemap, daomap만들어서 저장
			e.printStackTrace();
			throw new ServletException("드라이버가 존재하지 않습니다.");
		}
	}
	

	// 객체를 생성해서 저장하는 메서드
	private void newAndSave() {
		// beans객체가 필요하다.

		// controller생성 저장
		Beans.put("/board", new BoardController());

		// service생성 저장한다. -> URL -> 실행할 서비스를 선택한다.
		
		Beans.put("/board/list.do", new BoardListService());
		Beans.put("/board/view.do", new BoardViewService());
		Beans.put("/board/write.do", new BoardWriteService());
		Beans.put("/board/update.do", new BoardUpdateService());
		Beans.put("/board/delete.do", new BoardDeleteService());

		// DAO생성 저장한다.
		Beans.put("boardDAO", new BoardDAO());
	}
	
	//필요한 객체 넣어주기 : service->controller , dao ->service	
	public void inject() {
		//board** service = >boardController : URL 에 따라서 자동 선택되도록 작성한다. 코드를 없앤다.
		 	Beans.getService("/board/list.do").setDao(Beans.getDAO("boardDAO"));
		 	Beans.getService("/board/view.do").setDao(Beans.getDAO("boardDAO"));
		 	Beans.getService("/board/write.do").setDao(Beans.getDAO("boardDAO"));
		 	Beans.getService("/board/update.do").setDao(Beans.getDAO("boardDAO"));
		 	Beans.getService("/board/delete.do").setDao(Beans.getDAO("boardDAO"));
		 	//앞은 보드리스트 서비스  뒤는 보드다오
		
		 	
		
		/*
		 * ((BoardController)Beans.getController("/boardController"))
		 * .setBoardListService(Beans.getService("boardListService"));
		 */
	}
	
	/*
	 * System.out.println("서버로딩 - 초기화 작업 진행"); //처리에 필요한 객체 생성 - 서버가 시작과 함께
	 * Beans.put("/board", new BoardController()); //beans안에 객체 저장해둠
	 * Beans.put("/notice", new NoticeController()); //beans안에 객체 저장해둠
	 */

}
