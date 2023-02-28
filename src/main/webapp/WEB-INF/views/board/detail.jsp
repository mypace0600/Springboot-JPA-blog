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
        <hr />

        <div class="card">
            <form>
                <input type="hidden" id="boardId" value="${board.id}"/>
                <input type="hidden" id="userId" value="${principal.user.id}"/>
                <div class="card-body"><textarea id="reply-content" rows="1" class="form-control"></textarea></div>
                <div class="card-footer"><button type="button" id="btn-reply-save" class="btn- btn-primary">등록</button></div>
            </form>
        </div>
        <br />
        <div class="card">
            <div class="card-header">댓글 리스트</div>
            <ul id="reply-box" class="list-group">
                <c:forEach var="reply" items="${board.replys}">
                    <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                        <div class="reply-content-exist">${reply.content}</div>
                        <div class="d-none reply-content-edit">
                            <textarea id="edit-reply-content" rows="1" class="form-control"></textarea>
                        </div>
                        <div class="d-flex">
                            <div class="font-italic">${reply.user.userName} &nbsp;</div>
                            <c:if test="${reply.user.id == principal.user.id}">
                                <button type="button" class="badge m-1 btn-edit-box-display reply-content-exist">수정</button>
                                <button onclick="index.replyUpdate(${board.id},${reply.id})"  type="button" id="btn-reply-edit-save" class="badge m-1 d-none reply-content-edit">저장</button>
                                <button onclick="index.replyDelete(${board.id},${reply.id})" class="badge m-1 reply-content-exist">삭제</button>
                                <button type="button" id="btn-reply-edit-cancel" class="badge m-1 d-none reply-content-edit">취소</button>
                            </c:if>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>



