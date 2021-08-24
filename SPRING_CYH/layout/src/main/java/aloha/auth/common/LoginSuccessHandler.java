package aloha.auth.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

//public class LoginSuccessHandler implements AuthenticationSuccessHandler{
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	
	private static final Logger log = LoggerFactory.getLogger(LoginSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		log.info("@LoginSuccessHandler");
		log.info(request.toString());
		
		log.info("userId    :  "+request.getParameter("username"));
		log.info("rememberId    :  "+request.getParameter("rememberId"));
		log.info("rememberMe :   "+request.getParameter("rememberMe"));
		
		log.info("requestURL: " + request.getRequestURI());	
		

		String userId=request.getParameter("username");
		String rememberId=request.getParameter("rememberId");
		String rememberMe=request.getParameter("rememberMe");
		String requestURI = request.getRequestURI();
		
		//아이디 저장 체크

		Cookie cookie = new Cookie("userId",userId);	//userid 저장하기 위한 쿠키 만듬.
//		cookie.setPath(requestURI); //이 경로에 갔을 떄 쿠키 등록하게(어느 경로 갔을 떄 등록하는지) auth/login에 등록
		cookie.setPath("/auth/loginForm");
		if(rememberId !=null && rememberId.equals("on")) {	//on이면 등록되서 쿠키 부여
			cookie.setMaxAge(60 * 60 * 24 * 15);  //만료기간 : 15일
		}else {
			cookie.setMaxAge(0);
		}
		
		//쿠키등록
		response.addCookie(cookie);
		
		//요청 온 경로로 보내줌
//		response.sendRedirect(requestURI);

//		response.sendRedirect("/");
//		response.sendRedirect(requestURI);
		 if (session != null) {
	            String redirectUrl = (String) session.getAttribute("prevPage");
	            if (redirectUrl != null) {
	                session.removeAttribute("prevPage");
	                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
	            } else {
	                super.onAuthenticationSuccess(request, response, authentication);
	            }
	        } else {
	            super.onAuthenticationSuccess(request, response, authentication);
	        }	
		
	}
}