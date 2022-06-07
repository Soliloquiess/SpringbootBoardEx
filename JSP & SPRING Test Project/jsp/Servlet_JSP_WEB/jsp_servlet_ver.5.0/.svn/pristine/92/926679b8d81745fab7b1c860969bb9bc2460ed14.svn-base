package com.webjjang.notice.controller;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExecuteService;
import com.webjjang.main.controller.Service;
import com.webjjang.util.PageObject;
import com.webjjang.util.bean.Beans;

public class NoticeController implements Controller {

	// 모듈명을 저장하는 변수
	private final String MODULE = "notice"; 
	
	// 실행에 필요한 service 객체 선언.
	Service service = null;
	
	private void setService(String url) {
		// Init.init()에서 관련 URL로 찾아본다. : /notice/list.do -> NoticeListService
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
		
		// CRUD에 해당되는 처리문 작성 - 게시판 모든 URL을 정의해 놓는다. 그외에는 404로 처리한다.(500 오류로 잡는다.)
		switch (url) {
		// 게시판 리스트 처리
		case "/"+ MODULE +"/list.do":
			// DB에서 list 데이터를 가져오는 처리는 아래에 있다.
			
			PageObject pageObject = PageObject.getInstance(request);
		
			data = pageObject;
			
			// 맨 앞에 "redirect:" 붙으면 URL 이동한다. 없으면 jsp를 이용해서 HTML을 만든다.
			// 파일의 위치를 앞에 자동을 붙게 작성. 뒤에 확장자 .jsp가 자동으로 붙게 작성
			// /WEB-INF/views + /notice/list + .jsp
			jsp = MODULE + "/list";
			// request.setAttribute(key, data) -> JSP에서 꺼낼 때 ${key}
			key = "list";
			break;

		// 게시판 글보기 처리
		case "/"+ MODULE +"/view.do":
			// 데이터 수집 - 글번호, 조회수1증가
			String noStr = request.getParameter("no");
			long no = Long.parseLong(noStr);
			String incStr = request.getParameter("inc");
			int inc = Integer.parseInt(incStr);
			data = new Object[] {no, inc};
			
			// data가 담길 key
			key = "vo";
			
			// DB에서 데이터를 가져오면 view.jsp를 이용해서 HTML을 만들도록 설정
			// /WEB-INF/views + /notice/view + .jsp
			jsp = MODULE + "/view";
			break;
			
		// 게시판 글쓰기 폼
		case "/"+ MODULE +"/writeForm.do":
			jsp = MODULE + "/writeForm";
			break;
			
		// 게시판 글쓰기 처리
		case "/"+ MODULE +"/write.do":
			// 데이터 수집을 한다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			NoticeVO vo = new NoticeVO();
			vo.setTitle(title);
			vo.setContent(content);
			data = vo;
			
			//DB저장 하는 처리는 아래에 있다.
			// 처리가 다끝나면 바로 list로 페이지 이동이 일어나야 한다. "redirect:URL"
			jsp = "redirect:list.do";
			
			break;
			
		// 게시판 글수정 폼
		case "/"+ MODULE +"/updateForm.do":
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			// inc는 자동으로 0으로 셋팅한다.
			data = new Object[] {no, 0};
			
			key = "vo";
			jsp = "notice/updateForm";
			
			break;
			
		// 게시판 글수정 처리
		case "/"+ MODULE +"/update.do":
			
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			title = request.getParameter("title");
			content = request.getParameter("content");
			writer = request.getParameter("writer");
			
			vo = new NoticeVO();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			
			data = vo;
			
			//DB 처리가 끝나면 게시판 글보기 페이지로 이동. 글번호, inc=1
			jsp = "redirect:view.do?no=" + no + "&inc=0";
			
			break;
			
		// 게시판 글삭제 처리
		case "/"+ MODULE +"/delete.do":
			
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			
			data = no;
			
			jsp = "redirect:list.do";
			
			break;
			
		default:
			System.out.println("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
			throw new Exception("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
		}
		
		// service가 null이 아닌 경우만 실행하자.
		if(service != null)
			request.setAttribute(key, ExecuteService.execute(service, data));
		// 보여줄 JSP의 정보나 이동할 URL 정보를 넣는다.
		return jsp;
	}

}
