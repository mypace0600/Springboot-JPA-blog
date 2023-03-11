<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <h1>User List</h1>
    <c:forEach var="user" items="${users.content}">
        <form>
            <div>
                <div><a href="/admin/user=${user.id}/boards">username : ${user.userName}</a></div>
                <div>email : ${user.email}</div>
                <div>role : ${user.role}</div>
                <div>createDate : ${user.createDate}</div>
            </div>
            <button type="button" class="btn btn-danger admin-user-delete" id="${user.id}">계정 삭제</button>
            <c:choose>
                <c:when test="${user.role eq 'REQUEST'}">
                    <button type="button" class="btn btn-primary btn-approve-request-admin-role" id="${user.id}" value="ADMIN">Admin 권한 요청 승인</button>
                </c:when>
                <c:when test="${user.role eq 'ADMIN'}">
                    <button type="button" class="btn btn-danger btn-approve-request-admin-role" id="${user.id}" value="USER">Admin 권한 해제</button>
                </c:when>
            </c:choose>
            <hr />
        </form>
        <br />
    </c:forEach>
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${users.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${users.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${users.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${users.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${users.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${users.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<script src="/js/admin.js"></script>
<%@include file="../layout/footer.jsp"%>



