package com.spring.boot.config;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Component
public class BootInterceptor implements HandlerInterceptor {

    /*
        -preHandler : 컨트롤러에 도착하기전에 동작하는 메소드로 return값이 true이면 진행, false이면 멈춥니다.
        -postHandler : 컨트롤러에 도착하여 view가 랜더링되기 전에 동작합니다.
        -afterCompletion: view가 정상적으로 랜더링된 후에 마지막에 실행됩니다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {


        HttpSession session = request.getSession();

        String strLoginId = (String) session.getAttribute("ss_login_id");
        System.out.println("strLoginId :" + strLoginId);

        if (strLoginId == null) {
            System.out.println("Interceptor_Break false 1" + request.getRequestURI());
            response.sendRedirect("/login/login");
            return false;
        } else {
            System.out.println("Interceptor_Break true 2" + request.getRequestURI());
            return true;
        }

    }
 }