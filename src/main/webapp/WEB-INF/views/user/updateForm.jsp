<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <form <%--action="/user/join" method="POST"--%>>
        <input type="hidden" id="id" value="${principal.user.id}"/>
        <div class="form-group">
            <label for="username">User Name :</label>
            <input type="text" class="form-control" value="${principal.user.userName}" id="username" readonly>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeHolder="enter password" id="password">
        </div>
        <div class="form-group">
            <label for="email">Email address:</label>
            <input type="email" class="form-control" value="${principal.user.email}" id="email">
        </div>
        <div class="form-group">
            <label for="role">Role:</label>
            <input type="text" class="form-control" value="${principal.user.role}" id="role" readonly>
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">수정 완료</button>
    <button class="btn btn-secondary m-1" onclick="history.back()">돌아가기</button>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>
