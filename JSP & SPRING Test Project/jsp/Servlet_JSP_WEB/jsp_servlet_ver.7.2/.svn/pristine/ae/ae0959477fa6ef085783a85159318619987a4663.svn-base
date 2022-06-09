package com.webjjang.board.controller;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExecuteService;
import com.webjjang.main.controller.Service;
import com.webjjang.util.bean.Beans;

public class BoardController implements Controller {

	// 실행에 필요한 service 객체 선언.
	Service service = null;
	
	private void setService(String url) {
		service = Beans.getService(url);
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		// list, view, write, update, delete ??? -> request에서 URL 가져온다.
		String url = request.getServletPath();
		
		// Service에 전달을 해야할 객체
		Object data = null;
		
		// request에 담을 데이터의 key
		String key = "";
		
		// 보여줄 JSP나 이동할 페이지의 정보 저장 변수
		String jsp = "";
		
		// 실행전 실행할 서비스 셋팅
		setService(url);
		if(service != null)
			System.out.println(service.getClass().getSimpleName());
		else System.out.println("서비스가 선택되지 않았습니다. - 404");
		
		// CRUD에 해당되는 처리문 작성
		switch (url) {
		// 게시판 리스트 처리
		case "/board/list.do":
			// 파일의 위치를 앞에 자동을 붙게 작성. 뒤에 확장자 .jsp가 자동으로 붙게 작성
			// /WEB-INF/views + /board/list + .jsp
			jsp = "/board/list";
			// request.setAttribute(key, data) -> JSP에서 꺼낼 때 ${key}
			key = "list";
			break;

		// 게시판 글보기 처리
		case "/board/view.do":
			// 데이터 수집 - 글번호, 조회수1증가
			String noStr = request.getParameter("no");
			long no = Long.parseLong(noStr);
			String incStr = request.getParameter("inc");
			int inc = Integer.parseInt(incStr);
			data = new Object[] {no, inc};
			break;
			
		// 게시판 글쓰기 폼
		case "/board/writeForm.do":
			
			break;
			
		// 게시판 글쓰기 처리
		case "/board/write.do":
			
			break;
			
		// 게시판 글수정 폼
		case "/board/updateForm.do":
			
			break;
			
		// 게시판 글수정 처리
		case "/board/update.do":
			
			break;
			
		// 게시판 글삭제 처리
		case "/board/delete.do":
			
			break;
			
		default:
			System.out.println("페이지가 존재하지 않습니다. - 404 예외처리해야 한다.");
			break;
		}
		request.setAttribute(key, ExecuteService.execute(service, data));
		// 보여줄 JSP의 정보나 이동할 URL 정보를 넣는다.
		return jsp;
	}

}
