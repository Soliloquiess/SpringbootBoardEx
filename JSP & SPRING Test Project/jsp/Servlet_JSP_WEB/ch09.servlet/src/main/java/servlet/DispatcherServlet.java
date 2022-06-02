package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
//servlet은httpservlet객체를 상속받고 있는 클래스를 Servlet이라고 한다
//Servlet은 생성을 따로 하지 않는다. 톰캣 서버가 딱 한번만 생성하고 저장했다가 사용하게 된다.->singleton.
//localhost/Dispatcher호출하면 처리되는 클래스 작성 - "/DispatcherServlet"URL패턴
//패턴 - 모든 글자를 의미하는 * 사용가능, 위치에 해당되는 것을 사용할 수 있다.
@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 * 객체가 생성이 되면서 초기값을 세팅할 때 사용되는 메서드 ->DBInfo 서블릿에서 드라이버 확인 할 떄 사용
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 * 소멸자 : 객체가 사라질 때 처리되는 메서드
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 *
	 *요청한 내용을 처리해주는 메서드 - localhost/dispatcherServlet에 의해 실행
	 *doget()이나 doPost보다 우선순위가 높다.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;Charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("service를 실행중");

		response.getWriter().append("전달 방식"+ request.getMethod());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 *get방식의 요청만 처리 - 브라우저 주소란에 직접 입력, a태그로 입력. post로 지정되지 않으면 get방식이다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;Charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("doget()를 실행중");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 *post 방식의 요청만 처리 form method태그를  post로 지정
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;Charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("dopost()를 실행중");
		doGet(request, response);	//doGet()에 처리하도록 요청한다.
	}

}
