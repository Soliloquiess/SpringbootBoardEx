package com.util.init;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

//jsp도 servlet으로 만들었는데 더 쉽게 하기 위해 jsp로 쓰는거
/**
 * Servlet implementation class Init
 */
// @WebServlet("/Init") --> web.xml에서 servlet으로 등록
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//URL 등급에 대한 정보를 저장하는 map 변수 - map<url,권한번호>
	//url이 STring, 권한 정보는 Integer(앞은 url, 뒤는 권한 번호)
	public static Map<String, Integer> authorityMap = new HashMap<String, Integer>();
	//그 다음 web.xml에도 필터 등록
    /**
     * @see HttpServlet#HttpServlet()
     */
	public Init(){
        super();
        // TODO Auto-generated constructor stub
   }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    // 서버가 시작될 때 초기화 작업
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
        
        // 서버가 실행될 때 실행이되는지 확인하는 출력
        System.out.println("Init.init() 실행하고 있다.----------");
        
        // 1. DB 드라이버 확인 -> DB class확인을 한다.
        try {
			Class.forName("com.util.db.DB");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 2. 실행하려는 객체를 생성하고 조립해 둔다. -> 생략한다. 대신 필요할 때 new하는 것으로 한다.(Spring에서 자동 등록)
        // 3. 페이지 권한을 등록한다.
        // 9 : 관리자, 1: 일반회원
        // 회원관리 권한
        //authorityMap에 put으로 집어넣음.
        authorityMap.put("/member/list.jsp", 9);	//어드민만 가능
        authorityMap.put("/member/view.jsp", 1);
        authorityMap.put("/member/updateForm.jsp", 1);
        authorityMap.put("/member/update.jsp", 1);
        authorityMap.put("/member/delete.jsp", 1);
        
        // 공지사항 권한
        authorityMap.put("/notice/writeForm.jsp", 9);
        authorityMap.put("/notice/write.jsp", 9);
        authorityMap.put("/notice/updateForm.jsp", 9);
        authorityMap.put("/notice/update.jsp", 9);
        authorityMap.put("/notice/delete.jsp", 9);
        
        // 질문답변 권한	(질문답변은 하나 더 만들어야됨 , 내 아이디가 아니면 보여주지 않는다는 식으로, 비밀질문은 처리를 하나 더 만들어야됨.)
        authorityMap.put("/qna/writeForm.jsp", 1);
        authorityMap.put("/qna/write.jsp", 1);
        authorityMap.put("/qna/answerForm.jsp", 1);
        authorityMap.put("/qna/answer.jsp", 1);
        authorityMap.put("/qna/updateForm.jsp", 1);
        authorityMap.put("/qna/update.jsp", 1);
        authorityMap.put("/qna/delete.jsp", 1);
        //권한처리 등록하고 검사는 authorityfilter에서 검사, 등록은 여기서 서버가 스타트할떄 
 	}

}
