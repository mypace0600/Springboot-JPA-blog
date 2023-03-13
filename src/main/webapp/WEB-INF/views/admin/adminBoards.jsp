<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <c:choose>
        <c:when test="${empty boards}">
            <h1>게시글이 없습니다!!!</h1>
        </c:when>
        <c:otherwise>
            <h1>Favorite Boards List</h1>
            <c:forEach var="board" items="${boards.content}">
                <div>${board.count}</div>
                <div><a href="/board/${board.id}">${board.title}</a></div>
                <div>${board.user.userName}</div>
                <div>${board.createDate}</div>
                <hr />
                <br />
            </c:forEach>
        </c:otherwise>
    </c:choose>


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

<script src="/js/admin.js"></script>
<%@include file="../layout/footer.jsp"%>



