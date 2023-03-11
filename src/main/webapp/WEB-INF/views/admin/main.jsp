<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
    <div class="container">
        <div>
            <h1>Hello Admin</h1>
            <ul>
                <li><a href="/admin/users">유저 관리</a></li>
                <li><a href="/admin/boards">글 관리</a></li>
            </ul>
        </div>
    </div>

<script src="/js/admin.js"></script>
<%@include file="../layout/footer.jsp"%>



