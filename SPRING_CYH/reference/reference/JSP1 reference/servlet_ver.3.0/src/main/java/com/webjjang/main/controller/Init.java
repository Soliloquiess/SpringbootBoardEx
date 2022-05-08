package com.webjjang.main.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.webjjang.board.controller.BoardController;
import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.service.BoardDeleteService;
import com.webjjang.board.service.BoardListService;
import com.webjjang.board.service.BoardUpdateService;
import com.webjjang.board.service.BoardViewService;
import com.webjjang.board.service.BoardWriteService;
import com.webjjang.image.controller.ImageController;
import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.service.ImageDeleteService;
import com.webjjang.image.service.ImageListService;
import com.webjjang.image.service.ImageUpdatePhotoService;
import com.webjjang.image.service.ImageUpdateService;
import com.webjjang.image.service.ImageViewService;
import com.webjjang.image.service.ImageWriteService;
import com.webjjang.notice.controller.NoticeController;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.service.NoticeDeleteService;
import com.webjjang.notice.service.NoticeListService;
import com.webjjang.notice.service.NoticeUpdateService;
import com.webjjang.notice.service.NoticeViewService;
import com.webjjang.notice.service.NoticeWriteService;
import com.webjjang.util.bean.Beans;

/**
 * Servlet implementation class Init
 */

// @WebServlet() -> 웹정보를 설정 value = 접근 url, loadOnStartup = 서버의 시작과 함께 로드하는 순서 번호
//@WebServlet(value = "/Init", loadOnStartup = 1) -> web.xml
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
	 * 서버가 실행이 될 때 초기화 시키는 작업 메서드
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			// DBInfo 확인 - 드라이버 확인 한다.
			Class.forName("com.webjjang.util.db.DBInfo");
			// 객체를 생성해서 저장해 놓는다. -Beans 객체 - controllerMap, serviceMap, daoMap 만들어서 저장
			newAndSave();
			// 서로 연관되어 있는 객체를 넣어준다. (DI - Dependency Inject : 의존성 주입)
			inject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("드라이버가 존재하지 않습니다.");
		}
	}
	
	// 객체를 생성해서 저장하는 메서드
	private void newAndSave() {
		// Beans 객체가 필요하다.
		// Controller 생성 저장
		Beans.put("/board", new BoardController());
		Beans.put("/notice", new NoticeController());
		Beans.put("/image", new ImageController());
		
		// Service 생성 저장한다. URL -> 실행할 서비스를 선택한다.
		// 게시판 서비스
		Beans.put("/board/list.do", new BoardListService());
		Beans.put("/board/view.do", new BoardViewService());
		Beans.put("/board/write.do", new BoardWriteService());
		// 수정폼에 데이터 셋팅을 위해서 글보기 서비스를 실행해서 데이터를 가져와야한다.
		Beans.put("/board/updateForm.do", Beans.getService("/board/view.do"));
		Beans.put("/board/update.do", new BoardUpdateService());
		Beans.put("/board/delete.do", new BoardDeleteService());
		
		// 공지사항 서비스
		Beans.put("/notice/list.do", new NoticeListService());
		Beans.put("/notice/view.do", new NoticeViewService());
		Beans.put("/notice/write.do", new NoticeWriteService());
		// 수정폼에 데이터 셋팅을 위해서 글보기 서비스를 실행해서 데이터를 가져와야한다.
		Beans.put("/notice/updateForm.do", Beans.getService("/notice/view.do"));
		Beans.put("/notice/update.do", new NoticeUpdateService());
		Beans.put("/notice/delete.do", new NoticeDeleteService());
		
		// 이미지 서비스
		Beans.put("/image/list.do", new ImageListService());
		Beans.put("/image/view.do", new ImageViewService());
		Beans.put("/image/write.do", new ImageWriteService());
		// 수정폼에 데이터 셋팅을 위해서 글보기 서비스를 실행해서 데이터를 가져와야한다.
		Beans.put("/image/updateForm.do", Beans.getService("/image/view.do"));
		Beans.put("/image/update.do", new ImageUpdateService());
		Beans.put("/image/updatePhoto.do", new ImageUpdatePhotoService());
		Beans.put("/image/delete.do", new ImageDeleteService());
		
		
		// DAO 생성 저장한다.
		Beans.put("boardDAO", new BoardDAO());
		Beans.put("noticeDAO", new NoticeDAO());
		Beans.put("imageDAO", new ImageDAO());
	}
	
	// 필요한 객체 넣어주기 : service -> controller, dao -> sevice
	public void inject() {
		// board**Service -> boradController : URL에 따라서 자동 선택되도록 작성하다. 코드를 없앤다.
		// 게시판 조립
		// dao -> board**Service
		Beans.getService("/board/list.do").setDao(Beans.getDAO("boardDAO"));
		Beans.getService("/board/view.do").setDao(Beans.getDAO("boardDAO"));
		Beans.getService("/board/write.do").setDao(Beans.getDAO("boardDAO"));
		Beans.getService("/board/update.do").setDao(Beans.getDAO("boardDAO"));
		Beans.getService("/board/delete.do").setDao(Beans.getDAO("boardDAO"));
		// 공지사항 조립
		// dao -> notice**Service
//		Beans.getService("/notice/list.do").setDao(Beans.getDAO("noticeDAO"));
//		Beans.getService("/notice/view.do").setDao(Beans.getDAO("noticeDAO"));
//		Beans.getService("/notice/write.do").setDao(Beans.getDAO("noticeDAO"));
//		Beans.getService("/notice/update.do").setDao(Beans.getDAO("noticeDAO"));
//		Beans.getService("/notice/delete.do").setDao(Beans.getDAO("noticeDAO"));
		// 이미지 게시판 조립
		// dao -> image**Service
		Beans.getService("/image/list.do").setDao(Beans.getDAO("imageDAO"));
		Beans.getService("/image/view.do").setDao(Beans.getDAO("imageDAO"));
		Beans.getService("/image/write.do").setDao(Beans.getDAO("imageDAO"));
//		Beans.getService("/image/update.do").setDao(Beans.getDAO("imageDAO"));
//		Beans.getService("/image/updatePhoto.do").setDao(Beans.getDAO("imageDAO"));
//		Beans.getService("/image/delete.do").setDao(Beans.getDAO("imageDAO"));
	}

}
