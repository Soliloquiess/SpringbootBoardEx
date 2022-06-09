package com.webjjang.member.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExecuteService;
import com.webjjang.main.controller.Service;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.bean.Beans;
import com.webjjang.util.file.FileUtil;

public class MemberController implements Controller {

	// 모듈이름
	private final String MODULE = "member";
	
	// 실행에 필요한 service 객체 선언.
	Service service = null;
	
	private void setService(String url) {
		// Init.init()에서 관련 URL로 찾아본다. : /member/list.do -> MemberListService
		service = Beans.getService(url);
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		// 삭제해야할 파일
		String deleteFile = null;
		
		// 처리 결과를 담기 위해서  session을 request에서 꺼낸다.
		HttpSession session = request.getSession();
		
		// list, view, write, update, delete ??? -> request에서 URL 가져온다.
		String url = request.getServletPath();
		
		// Service에 전달을 해야할 객체
		Object data = null;
		
		// request에 담을 데이터의 key - jsp에서 데이터를 찾아가는 key
		String key = "";
		
		// 보여줄 JSP나 이동할 페이지의 정보 저장 변수
		String jsp = "";
		
		// 페이지 처리를 위한 객체
		PageObject pageObject = null;
		
		// 실행전 실행할 서비스 셋팅
		setService(url);
		
		// CRUD에 해당되는 처리문 작성 - 게시판 모든 URL을 정의해 놓는다. 그외에는 404로 처리한다.(500 오류로 잡는다.)
		switch (url) {
		// 회원 리스트 처리 - 관리자만 제공
		case "/" + MODULE + "/list.do":
			// DB에서 list 데이터를 가져오는 처리는 아래에 있다.
			// 맨 앞에 "redirect:" 붙으면 URL 이동한다. 없으면 jsp를 이용해서 HTML을 만든다.
			// 파일의 위치를 앞에 자동을 붙게 작성. 뒤에 확장자 .jsp가 자동으로 붙게 작성
			// /WEB-INF/views/ + member/list + .jsp
			jsp = MODULE + "/list";
			// request.setAttribute(key, data) -> JSP에서 꺼낼 때 ${key}
			key = "list";
			
			// 페이지 처리를 위한 객체
			// getInstance() - 전달되는 페이지 정보를 이용한 페이지 객체를 만들어 내는 메서드
			// PageObject를 생성해서 넘어오는 페이지와 표시되는 데이터 갯수를 받아서 셋팅해 준다.
			// Service에서 전체 데이터를 셋팅하는 setTotalRow()를 이용해서 전체 글의 갯수를 넣어주면 페이지 정보가 계산된다.
			// 넘어오는 데이터가 없으면 page = 1, perPageNum = 10으로 셋팅이 된다.
			pageObject = PageObject.getInstance(request);
			
			// 넘어오는 데이터가 없으면 page = 1, perPageNum = 8(4*2)으로 셋팅이 된다.
			// perPageNum의 기본값을 10인데 값이 안들어오면 8로 셋팅하자. 한 줄에 보여줄 이미지 개수의 배수 사용
			String strPerPageNum = request.getParameter("perPageNum");
			if (strPerPageNum == null || strPerPageNum.equals(""))
				pageObject.setPerPageNum(8);
			
			data = pageObject;
			
			break;

		// 회원 정보 보기 처리
		case "/" + MODULE + "/view.do":
			
			pageObject = PageObject.getInstance(request);
			
			// 데이터 수집 - 글번호 - 넘어오는 데이터, 아이디 - 세션 -> 2개 넘겨야 하므로 MemberVO 사용하자.
			String noStr = request.getParameter("no");
			long no = Long.parseLong(noStr);
			String id = LoginVO.getId(request);
			MemberVO vo = new MemberVO();
			vo.setId(id);
			data = vo;
			
			// data가 담길 key
			key = "vo";
			
			// DB에서 데이터를 가져오면 view.jsp를 이용해서 HTML을 만들도록 설정
			// /WEB-INF/views/ + member/view + .jsp
			jsp = MODULE + "/view";
			break;
			
		// 회원가입 폼
		case "/" + MODULE + "/writeForm.do":
			jsp = MODULE + "/writeForm";
			break;
			
		// 회원가입 처리
		case "/ " + MODULE + "/write.do":
			MultipartRequest multi = FileUtil.upload(MODULE, request);
			
			// 데이터 수집을 한다. - multi에서 데이터 가져오기.
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			// 데이터가 넘어오지 않는다면 html의 form의 input tag에서 name="memberFile" 인지 확인해야한다.
			String fileName = multi.getFilesystemName("memberFile");
//			String id = ((LoginVO)session.getAttribute("login")).getId();
			id = LoginVO.getId(request);
			vo = new MemberVO();
			vo.setId(id);
			data = vo;
			
			//DB저장 하는 처리는 아래에 있다.
			// 처리가 다끝나면 바로 list로 페이지 이동이 일어나야 한다. "redirect:URL"
			jsp = "redirect:list.do";
			
			// 글쓴 처리 결과를 session의 msg에 담기
			session.setAttribute("msg", "성공적으로 이미지 등록이 되었습니다. ");
			
			break;
			
		// 회원 정보 수정 폼
		case "/" + MODULE + "/updateForm.do":
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			// inc는 자동으로 0으로 셋팅한다.
			data = no;
			
			key = "vo";
			jsp = MODULE + "/updateForm";
			
			break;
			
		// 회원 정보 수정 처리
		case "/" + MODULE + "/update.do":
			
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			title = request.getParameter("title");
			content = request.getParameter("content");
			
			vo = new MemberVO();
			vo.setId(LoginVO.getId(request));
			
			data = vo;
			
			//DB 처리가 끝나면 게시판 글보기 페이지로 이동. 글번호, inc=1
			jsp = "redirect:view.do?no=" + no
					+ "&page=" + request.getParameter("page")
					+ "&perPageNum=" + request.getParameter("perPageNum")
					+ "&key=" + request.getParameter("key")
					// tomcat Server에서의 한글은 iso-8805-1 코드이다. 한글이 ?표로 나타난다.
					+ "&word=" + URLEncoder.encode(request.getParameter("word"), "utf-8") ;
			
			// 글쓴 처리 결과를 session의 msg에 담기
			session.setAttribute("msg", "성공적으로 이미지 정보 수정이 되었습니다. ");
			
			break;
			

			// 회원 사진 변경 처리
			// 1. 게시판에 새로운 사진이 등록된다.(서버의 하드디스크에 올라간다.) 2. 이전 이미지 파일을 지운다.
			// 회원관리 글보기에서 이미지 수청요청이 들어오므로 이미지 파일 정보를 전달받아서 지운다.
			case "/" + MODULE + "/updatePhoto.do":
				
				// 생성과 함께 파일 업로드는 끝난다. DB에 정보를 저장하는 것을 아래에서 처리한다.
				multi = FileUtil.upload(MODULE, request);
				
				// multi에서 꺼낼 수 있는지 확인이 꼭 필요하다.
				pageObject = new PageObject(); // page =1, perPageNum = 10 기본값으로 셋팅
				String strPage = multi.getParameter("page");
				if(strPage != null && !strPage.equals(""))
					pageObject.setPage(Long.parseLong(strPage));
				strPerPageNum = multi.getParameter("perPageNum");
				System.out.println("MemberController.execute().updatePhoto. perPageNum : " + strPerPageNum);
				if(strPerPageNum != null && !strPerPageNum.equals(""))
					pageObject.setPerPageNum(Long.parseLong(strPerPageNum));
				else pageObject.setPerPageNum(8);
				pageObject.setKey(multi.getParameter("key"));
				pageObject.setWord(multi.getParameter("word"));
				
				System.out.println("MemberController.execute().updatePhoto. pageObject : " + pageObject);
				
				// 데이터 수집을 한다. - multi에서 데이터 가져오기. - 글번호, 파일명
				String strNo = multi.getParameter("no");
				no = Long.parseLong(strNo);
				// 데이터가 넘어오지 않는다면 html의 form의 input tag에서 name="memberFile" 인지 확인해야한다.
				fileName = multi.getFilesystemName("memberFile");
				vo = new MemberVO();
				System.out.println("MemberController.execute().updatePhoto. vo : " + vo);
				data = vo;
				
				deleteFile = multi.getParameter("del");
				
				//DB저장 하는 처리는 아래에 있다.
				// 처리가 다끝나면 바로 list로 페이지 이동이 일어나야 한다. "redirect:URL"
				jsp = "redirect:view.do?no=" + no 
						+ "&page=" + pageObject.getPage()
						+ "&perPageNum=" + pageObject.getPerPageNum()
						+ "&key=" + pageObject.getKey()
						// tomcat Server에서의 한글은 iso-8805-1 코드이다. 한글이 ?표로 나타난다.
						+ "&word=" + URLEncoder.encode(pageObject.getWord(), "utf-8") ;
				
				// 글쓴 처리 결과를 session의 msg에 담기
				session.setAttribute("msg", "성공적으로 이미지 변경이 되었습니다. ");
				
				break;
				
	
			
		// 회원 탈퇴 -> 회원 상태만 "탈퇴"로 수정한다. - 로그인 할 때 상태가 "정상"으로 되어 있는 회원만 가능
		case "/" + MODULE + "/delete.do":
			
			// 데이터 수집
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			
			vo = new MemberVO();
			// 본인의 글만 삭제가 가능하다. 로그인한 아이디가 필요하다.
			vo.setId(LoginVO.getId(request));
			
			data = vo;
			
			// /upload/member/파일명
			deleteFile = request.getParameter("del");
			
			jsp = "redirect:list.do";
			
			// 글쓴 처리 결과를 session의 msg에 담기
			session.setAttribute("msg", "성공적으로 글 삭제가 처리되었습니다. ");
			
			break;
			
		// 로그인 폼	
		case "/" + MODULE + "/loginForm.do":
			
			jsp = MODULE + "/loginForm";
			
			break;
		
		// 로그인 처리	
		case "/" + MODULE + "/login.do":
			
			// 데이터 수집
			id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			// 전달할 객체 생성
			LoginVO loginVo = new LoginVO();
			loginVo.setId(id);
			loginVo.setPw(pw);
			
			data = loginVo;
			
			key = "login";
			
			String goURL = (String) session.getAttribute("goURL");
			
			jsp = "redirect:" + ((goURL == null)?"/":goURL);
			
			// session에 저장된 페이지 이동 정보도 없앤다.
			session.removeAttribute("goURL");
		break;
		
		// 로그아웃 처리 - session을 지운다.
		case "/" + MODULE + "/logout.do":
			
			// session 객체 없앤다. 이후 부터는 session 사용 못함. 메시지 경고 사용 못함다.
//			session.invalidate();
			
			// session에 저장되어 있는 로그인 정보만 없앤다. 메시지 경고 사용 가능
			session.removeAttribute("login");
			// session에 저장된 페이지 이동 정보도 없앤다.
			session.removeAttribute("goURL");
			session.setAttribute("msg", "로그아웃되었습니다. 건의사항이 있으면 알려 주세요.");
			
			jsp = "redirect:/";
			
			break;
		
		
		default:
			System.out.println("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
			throw new Exception("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
		}
		
		// service가 null이 아닌 경우만 실행하자.
		// 회원관리 CRUD의 모든 데이터는 request에 담는다.
		// 로그인 처리는 정상적으로 데이터를 가져왔을 때 session에 담는다.
		if(service != null) {
			if(url.equals("/member/login.do")) {
				// 정상적인 처리가 끝나면 가져온 데이터를 session에 담는다.(로그인 처리)
				session.setAttribute(key, ExecuteService.execute(service, data));
				session.setAttribute("msg", "정상적으로 로그인이 되었습니다. 즐거운 시간되세요.");
			} else {
				request.setAttribute(key, ExecuteService.execute(service, data));
			}
		}
		
		// 페이지 정보를 request에 담아서 JSP에 전달한다.
		request.setAttribute("pageObject", pageObject);
		
		// deleteFile != null 삭제 처리한다.
		// /upload/member/~~~
		if(deleteFile != null) FileUtil.delete(deleteFile, request);
		
		// 보여줄 JSP의 정보나 이동할 URL 정보를 넣는다.
		return jsp;
	}

}
