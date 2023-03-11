<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
    <div class="container">
        <div>
            <h1>접근 권한이 없습니다.</h1>
            <form>
                <input type="hidden" id="id" value="${user.id}"/>
                <button type ="button" class="btn btn-secondary m-1" id="btn-request-admin-role">관리자권한 요청하기</button>
                <a class="btn btn-secondary m-1" href="/">돌아가기</a>
            </form>
        </div>
    </div>

<script src="/js/admin.js"></script>
<%@include file="../layout/footer.jsp"%>



