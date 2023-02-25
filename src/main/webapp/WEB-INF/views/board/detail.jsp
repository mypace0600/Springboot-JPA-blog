<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
    <div class="container">
        <div>
            글번호 : <span id = "id"><i>${board.id}  </i></span>
            작성자 : <span><i>${board.user.userName}  </i></span>
            작성일 : <span><i>${board.createDate}  </i></span>
        </div>
        <br />
        <hr />
        <div>
            <h3>${board.title}</h3>
        </div>
        <hr/>
        <div>
            <div>${board.content}</div>
        </div>
        <br />
        <br />
        <hr />
        <div class="d-flex justify-content-end">
            <button class="btn btn-secondary m-1" onclick="history.back()">돌아가기</button>
            <c:if test="${board.user.id == principal.user.id}">
                <a href="/board/${board.id}/updateForm" class="btn btn-warning m-1">수정</a>
                <button id="btn-delete" class="btn btn-danger m-1">삭제</button>
            </c:if>
        </div>
    </div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>



