<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">User Name :</label>
            <input type="text" name="username" class="form-control" placeholder="Enter User Name" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" name="remember" type="checkbox"> Remember Me
            </label>
        </div>
        <button id="btn-login" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=428545857802c4216b58954a115f7ad1&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" src="/img/kakao_login.png"/></a>
    </form>

</div>
<%@include file="../layout/footer.jsp"%>
