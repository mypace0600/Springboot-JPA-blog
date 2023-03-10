<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <h1>hello admin</h1>
    <c:forEach var="user" items="${users.content}">
        <div>
            <div>username : ${user.username}</div>
            <div>email : ${user.email}</div>
            <div>role : ${user.role}</div>
        </div>
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
<%@include file="../layout/footer.jsp"%>



