package com.board.controller;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletRequest;

import com.board.vo.BoardVO;
import com.main.controller.Controller;
import com.main.controller.ExecuteService;
import com.main.controller.Service;
import com.util.bean.Beans;

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
		
		// request에 담을 데이터의 key - jsp에서 데이터를 찾아가는 key
		String key = "";
		
		// 보여줄 JSP나 이동할 페이지의 정보 저장 변수
		String jsp = "";
		
		// 실행전 실행할 서비스 셋팅
		setService(url);
		if(service != null)
			System.out.println(service.getClass().getSimpleName());
		else if(!url.equals("/board/writeForm.do")) {
			System.out.println("서비스가 선택되지 않았습니다. - 404");
			throw new Exception("서비스가 선택되지 않았습니다. - 404");
		}
		
		// CRUD에 해당되는 처리문 작성
		switch (url) {
		// 게시판 리스트 처리
		case "/board/list.do":
			// 파일의 위치를 앞에 자동을 붙게 작성. 뒤에 확장자 .jsp가 자동으로 붙게 작성
			// /WEB-INF/views + /board/list + .jsp
			jsp = "board/list";
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
			data = new Object[] {no, inc};	//앞에꺼는 넘버, 뒤는 조회수
			
			// data가 담길 key
			key = "vo";
			
			// DB에서 데이터를 가져오면 view.jsp를 이용해서 HTML을 만들도록 설정
			// /WEB-INF/views + /board/view + .jsp
			jsp = "/board/view";
			break;
			
		// 게시판 글쓰기 폼
		case "/board/writeForm.do":
			jsp = "board/writeForm";
			break;
			
		// 게시판 글쓰기 처리
		case "/board/write.do":
			// 데이터 수집을 한다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriter(writer);
			data = vo;
			
			//DB저장 하는 처리는 아래에 있다.
			// 처리가 다끝나면 바로 list로 페이지 이동이 일어나야 한다. "redirect:URL"
			jsp = "redirect:list.do";
			
			break;
			
		// 게시판 글수정 폼
		case "/board/updateForm.do":
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			// inc는 자동으로 0으로 셋팅한다.
			data = new Object[] {no, 0};
			
			key = "vo";
			jsp = "board/updateForm";
			
			break;
			
		// 게시판 글수정 처리
		case "/board/update.do":
			
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			title = request.getParameter("title");
			content = request.getParameter("content");
			writer = request.getParameter("writer");
			
			vo = new BoardVO();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriter(writer);
			
			data = vo;
			
			//DB 처리가 끝나면 게시판 글보기 페이지로 이동. 글번호, inc=1
			jsp = "redirect:view.do?no=" + no + "&inc=0";
			
			break;
			
		// 게시판 글삭제 처리
		case "/board/delete.do":
			
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			
			data = no;
			
			jsp = "redirect:list.do";
			
			break;
			
		default:
			System.out.println("페이지가 존재하지 않습니다. - 404 예외처리해야 한다.");
			break;
		}
		// service가 null이 아닌 경우만 실행하자.
		if(service != null)
			request.setAttribute(key, ExecuteService.execute(service, data));
		// 보여줄 JSP의 정보나 이동할 URL 정보를 넣는다.
		return jsp;
	}

}



//
//
//public class BoardController implements Controller {
//	// 실행에 필요한 service 객체 선언
//	Service service = null;
//
//	private void setService(String url) {
//		service = Beans.getService(url);
//	}
//
//	@Override
//	public String execute(HttpServletRequest request) throws Exception {
//		// System.out.println(getClass().getSimpleName()+".execute()");
//		// list, view, write, update, delete ???- > request에서 URL 가져온다.
//		String url = request.getServletPath();
//		// 실행전 실행할 서비스 세팅.
//		setService(url);
//		if (service != null) {
//			System.out.println(service.getClass().getSimpleName());
//		} else {
////			request.setAttribute("list",ExecuteService.execute(service, null));
////			//리스트에만 저장한거 만들어냄. 데이터 넘기면 결과 나오고 걔를 리퀘스트 감고 디
//			System.out.println("서비스 선택 안됨");
//		}
//		// crud에 해당하는 처리문 작성
//
//		switch (url) {
//		
//		//게시판 리스트 처리
//		case "/board/list.do":
//			break;
//
//		//게시판 글보기 처리
//
//		// 데이터 수집 - 글번호, 조회수1증가
//		case "/board/view.do":
//			break;
//	
//		//게시판 글쓰기 폼
//		case "/board/writeForm.do":
//			break;
//		//게시판 글쓰기 처리
//		case "/board/write.do":
//			break;
//		//게시판 글수정 폼
//		case "/board/updateForm.do":
//			break;
//		//게시판 글 수정 처리
//		case "/board/update.do":
//			break;
//		//게시판 글 삭제 처리
//
//		case "/board/delete.do":
//			break;
//		default:
//			System.out.println("페이지가 존재 x -> 404 예외처리 해야 한다.");
//			break;
//		}
//
//		request.setAttribute("list",ExecuteService.execute(service,null));
//		return "";	
//	}
//
//// 실행에 필요한 service객체 선언
////	Service boardListService = null;
////	Service boardViewService = null;
////	Service boardWriteService = null;
////	Service boardUpdateService = null;
////	Service boardDeleteService = null;
//
////	
////	
////	
////	public void setBoardListService(Service boardListService) {
////		this.boardListService = boardListService;
////	}
////
////
////
////
////	public void setBoardViewService(Service boardViewService) {
////		this.boardViewService = boardViewService;
////	}
////
////
////
////
////	public void setBoardWriteService(Service boardWriteService) {
////		this.boardWriteService = boardWriteService;
////	}
////
////
////
////
////	public void setBoardUpdateService(Service boardUpdateService) {
////		this.boardUpdateService = boardUpdateService;
////	}
////
////
////
////
////	public void setBoardDeleteService(Service boardDeleteService) {
////		this.boardDeleteService = boardDeleteService;
////	}
////
////
////
////
////	@Override
////	public String execute(HttpServletRequest request) throws Exception{
////		System.out.println(getClass().getSimpleName()+".execute()");
////		return null;
////	}
//}
