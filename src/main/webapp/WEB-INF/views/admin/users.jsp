<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <h1>hello admin</h1>
    <c:forEach var="user" items="${users.content}">
        <form>
            <div>
                <div>username : ${user.userName}</div>
                <div>email : ${user.email}</div>
                <div>role : ${user.role}</div>
                <div>createDate : ${user.createDate}</div>
            </div>
            <hr />
            <button type="button" class="btn btn-danger" class="admin-user-delete" id="${user.id}">계정 삭제</button>
            <c:if test="${user.role eq 'REQUEST'}">
                <button type="button" class="btn btn-primary" class="btn-approve-request-admin-role" id="${user.id}">Admin 권한 요청 승인</button>
            </c:if>
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

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>



