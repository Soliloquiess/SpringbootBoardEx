package com.webjjang.message.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webjjang.message.vo.MessageVO;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExecuteService;
import com.webjjang.main.controller.Service;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.bean.Beans;

// DispacherServlet 클래스에서 module 이름으로 만든 키에 따라서 데이터로 클라이언트에게 전달할 결정
public class AjaxController implements Controller{

	private final String MODULE = "ajax"; 
	
	// 실행에 필요한 service 객체 선언.
	Service service = null;
	
	private void setService(String url) {
		// Init.init()에서 관련 URL로 찾아본다. : /ajax/msgCount.do -> MessageCountService
		service = Beans.getService(url);
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		// 처리 결과를 담기 위해서  session을 request에서 꺼낸다.
		HttpSession session = request.getSession();
		
		// list, view, write, delete ??? -> request에서 URL 가져온다.
		String url = request.getServletPath();
		
		// Service에 전달을 해야할 객체
		Object data = null;
		
		// request에 담을 데이터의 key - jsp에서 데이터를 찾아가는 key
		String key = "";
		
		// 보여줄 JSP나 이동할 페이지의 정보 저장 변수
		String jsp = "";
		
		// 페이지 처리를 위한 객체
		PageObject pageObject = null;
		//pageObject = PageObject.getInstance(request);
		pageObject = new PageObject();	//페이지 객체 가져옴 가져와서 메시지 개수를 보여주게 한다
		
		// 실행전 실행할 서비스 셋팅
		setService(url);
		
		// CRUD에 해당되는 처리문 작성 - 메시지 모든 URL을 정의해 놓는다. 그외에는 404로 처리한다.(500 오류로 잡는다.)
		switch (url) {
		// 새로운 메시지 개수 가져오기
		case "/" + MODULE + "/msgCount.do":
			
			// 데이터 수집 - 아이디, mode = 4 : 새로운 메시지(모드를 4로)
			//메시지를 받는 모드 받기
			pageObject.setAcceptMode(4);
			String id = LoginVO.getId(request);
			if(id == null) {
				System.out.println("로그아웃된 상태입니다.");
				// default_decorator에서 전달 받는 데이터 -> jsp에서 페이지 새로고침하도록 처리한다.
				jsp = "null";
				return jsp;
			}
			pageObject.setAccepter(id);
			
			// 전체 새로운 메시지 데이터를 숫자로 전달한다.(클라이언트로 전달. jsp와 관련 없음). 숫자를 문자로 만들어야 한다.
			// 데이터를 문자로 바꾸는 방법 : 1.Object.toString(), 2. "" + Object
			//이 두 방법은 자바 할 떄 사용
			// Long 타입의 숫자와 String 문자열을 서로 관련이 없다. 캐스팅 불가.
			jsp = "" + ExecuteService.execute(service, pageObject);
			//ExecuteService가 캐스팅해서 문자열이면 괜찮은데 숫자가 들어가있으면 문자열로 캐스팅해줘야 이렇게 쓰고 .toString 메서드 사용
			break;
			
		default:
			System.out.println("404:: 존재하지 않는 페이지를 요청하셨습니다.");
			throw new Exception("404:: 존재하지 않는 페이지를 요청하셨습니다.");
		}
		
		// 클라이언트에 전달이 되어져야 하는 데이터가 된다.
		return jsp;
		
	}

}
