<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>
<div class="container">
    <c:forEach var="board" items="${boards.content}">
        <c:choose>
            <c:when test="${board.hidden==true && board.user.id != principal.user.id}">
                <div class="card m-2 opacity-25" style="pointer-events: none">
                    <div class="card-body">
                        <h4 class="card-title">${board.title} 🔒</h4>
            </c:when>
            <c:otherwise>
                <div class="card m-2">
                    <div class="card-body">
                        <h4 class="card-title">${board.title}</h4>
            </c:otherwise>
        </c:choose>
                <div> 작성자 : ${board.user.userName}</div>
                <div> 조회수 : ${board.count}</div>
                <br />
                <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>
        <ul class="pagination justify-content-center">
            <c:choose>
                <c:when test="${boards.first}">
                    <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${boards.last}">
                    <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
</div>
<%@include file="layout/footer.jsp"%>



