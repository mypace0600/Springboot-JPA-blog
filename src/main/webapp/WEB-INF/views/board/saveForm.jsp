<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
    <div class="container">
        <form>
            <div class="form-group">
                <label for="title">title</label>
                <input type="text" class="form-control" placeholder="Enter title" id="title">
            </div>
            <div class="form-group">
                <label for="content">content</label>
                <textarea class="form-control summernote" rows="5" id="content"></textarea>
            </div>
            <input type="checkbox" id="hiddenStat" val="true">비밀글 설정</input>
        </form>
        <button id="btn-save" class="btn btn-primary">작성 완료</button>
    </div>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>



