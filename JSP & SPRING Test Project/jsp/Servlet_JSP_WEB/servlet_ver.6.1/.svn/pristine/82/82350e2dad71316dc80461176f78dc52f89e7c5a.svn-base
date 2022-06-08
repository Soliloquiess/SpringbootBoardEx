package com.webjjang.main.controller;

import javax.servlet.http.HttpServletRequest;
import com.webjjang.util.PageObject;
import com.webjjang.util.bean.Beans;

public class MainController implements Controller {

	// 실행에 필요한 service 객체 선언.
	Service service = null;
	
	private void setService(String url) {
		// Init.init()에서 관련 URL로 찾아본다. : /board/list.do -> BoardListService
		service = Beans.getService(url);
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		// list, view, write, update, delete ??? -> request에서 URL 가져온다.
		String url = request.getServletPath();
		
		// Service에 전달을 해야할 객체 - main을 list만 있어서 pageObject를 넘긴다.
		Object data = null;
		
		// request에 담을 데이터의 key - jsp에서 데이터를 찾아가는 key
		String key = "";
		
		// 보여줄 JSP나 이동할 페이지의 정보 저장 변수
		String jsp = "";
		
		// 페이지 처리를 위한 객체
		PageObject pageObject = null;
		
		// CRUD에 해당되는 처리문 작성 - 게시판 모든 URL을 정의해 놓는다. 그외에는 404로 처리한다.(500 오류로 잡는다.)
		switch (url) {
		// 게시판 리스트 처리
		case "/main/main.do":
			// DB에서 list 데이터를 가져오는 처리는 아래에 있다.
			// 맨 앞에 "redirect:" 붙으면 URL 이동한다. 없으면 jsp를 이용해서 HTML을 만든다.
			// 파일의 위치를 앞에 자동을 붙게 작성. 뒤에 확장자 .jsp가 자동으로 붙게 작성
			// /WEB-INF/views/ + main/main + .jsp
			jsp = "main/main";
			// 페이지 처리를 위한 객체
			// getInstance() - 전달되는 페이지 정보를 이용한 페이지 객체를 만들어 내는 메서드
			// PageObject를 생성해서 넘어오는 페이지와 표시되는 데이터 갯수를 받아서 셋팅해 준다.
			// 넘어오는 데이터가 없으면 page = 1, perPageNum = 10으로 셋팅이 된다.
			// Service에서 전체 데이터를 셋팅하는 setTotalRow()를 이용해서 전체 글의 갯수를 넣어주면 페이지 정보가 계산된다.
			pageObject = PageObject.getInstance(request);
			
			// 메인에서 공지사항과 게시판을 보여줄 때 7개만 보여준다.
			pageObject.setPerPageNum(7);
			
			data = pageObject;
			
			// 공지사항을 가져오는 처리
			// 실행전 실행할 서비스 셋팅
			setService("/notice/list.do");// url에 맞는 Service 선택 -> NoticeListService 선택
			// request.setAttribute(key, data) -> JSP에서 꺼낼 때 ${key}
			key = "noticeList";
			// service가 null이 아닌 경우만 실행하자.
			if(service != null)
				request.setAttribute(key, ExecuteService.execute(service, data));
			
			// 일반게시판을 가져오는 처리
			// 실행전 실행할 서비스 셋팅
			setService("/board/list.do");// url에 맞는 Service 선택 -> BoardListService 선택
			// request.setAttribute(key, data) -> JSP에서 꺼낼 때 ${key}
			key = "boardList";
			// service가 null이 아닌 경우만 실행하자.
			if(service != null)
				request.setAttribute(key, ExecuteService.execute(service, data));
			
			// 이미지게시판을 가져오는 처리 - 이미지의 개수를 4개만 가져온다.
			pageObject.setPerPageNum(4);
			// 실행전 실행할 서비스 셋팅
			setService("/image/list.do");// url에 맞는 Service 선택 -> ImageListService 선택
			// request.setAttribute(key, data) -> JSP에서 꺼낼 때 ${key}
			key = "imageList";
			// service가 null이 아닌 경우만 실행하자.
			if(service != null)
				request.setAttribute(key, ExecuteService.execute(service, data));
			
			break;


		default:
			System.out.println("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
			throw new Exception("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
		}
		
		// 보여줄 JSP의 정보나 이동할 URL 정보를 넣는다.
		return jsp;
	}

}
