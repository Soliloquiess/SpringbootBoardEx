<%@ page import="com.spring.boot.vo.Vo_member" %>
<%@ page import="java.util.List" %><%--
 헤더 파일
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String strLoginId = "guest";
    String strName = "guest";
    String strRole = "";

    String strLogInOut="";
    String strUrl="";

    session = request.getSession(false);
    if(session != null){
        strLoginId = (String) session.getAttribute("ss_login_id");
        strName = (String) session.getAttribute("ss_name");
        strRole = (String) session.getAttribute("ss_role");
        strLogInOut ="LogOut";
        //strUrl = "/login/logout";
        strUrl = "/logout";

    }
    if(strLoginId==null){
         strLoginId = "guest";
         strName = "guest";
        strLogInOut ="LogIn";
        //strUrl = "/login/login";
        strUrl = "/secure/login";
    }

    String strAuth = (String) request.getAttribute("ss_auth");
    if(strAuth==null){ strAuth = "N"; }

%>

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center py-3 mb-4 border-bottom">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="/home" class="nav-link active" aria-current="page">Home(H)</a></li>
            <li class="nav-item"><a href="/study/list" class="nav-link">공부기록</a></li>
            <% //if("Y".equals(strAuth)){ %>
            <li class="nav-item"><a href="/member/list" class="nav-link">회원목록</a></li>
            <% //} %>
            <li class="nav-item"><a href="#" class="nav-link">About</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='<%=strUrl%>'" ><%=strLogInOut%></button>
            <% if("guest".equals(strLoginId)) { %>
            <button type="button" class="btn btn-primary" onclick="location.href='/member/insert'">Sign-up</button>
            <% } %>
        </div>
    </header>
</div>