let index = {
    init:function (){
        $("#btn-save").on("click",()=>{
            this.save();
        });

        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });

        $("#btn-update").on("click",()=>{
            this.updateById();
        });

        $("#btn-reply-save").on("click",()=>{
            this.replySave();
        });
        // 전통적 방식의 로그인 구현
        /*$("#btn-login").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
            this.login();
        });*/
    },

    save:function (){
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data), // javascript object인 data를 json 형식으로 변환해서 java가 인식할 수 있도록 준비함
            contentType:"application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
            dataType:"json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열(생긴게 json이라면)=> javascript object로 변경해줌
        }).done(function (resp){
            if(resp.status===500){
                alert("글쓰기 실패");
            } else {
                alert("글쓰기 완료");
            }
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    deleteById:function(){
            let id = $("#id").text(); // val()로 하면 delete request가 막힌다. 그래서 text로 해야 함

            $.ajax({
                type:"DELETE",
                url:"/api/board/"+id,
                dataType:"json"
            }).done(function (resp,textStatus,jqXHR){
                if(resp.status===200){
                    alert("글 삭제 완료");
                    location.href="/";
                } else {
                    alert("글 삭제 실패!!!!!!");
                }
            }).fail(function (error){
                alert(JSON.stringify(error));
            });
    },

    updateById:function(){

           let id = $("#id").val();

           let data = {
               title: $("#title").val(),
               content: $("#content").val()
           };

           $.ajax({
               type:"PUT",
               url:"/api/board/"+id,
               data:JSON.stringify(data), // javascript object인 data를 json 형식으로 변환해서 java가 인식할 수 있도록 준비함
               contentType:"application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
               dataType:"json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열(생긴게 json이라면)=> javascript object로 변경해줌
           }).done(function (resp){
               if(resp.status===200){
                                   alert("글 수정 완료");
                                   location.href="/";
                               } else {
                                   alert("글 수정 실패!!!!!!");
                               }
           }).fail(function (error){
               alert(JSON.stringify(error));
           });
    },

    replySave:function (){
        let data = {
            userId : $("#userId").val(),
            boardId : $("#boardId").val(),
            content: $("#reply-content").val()
        };

        $.ajax({
            type:"POST",
            url:`/api/board/${data.boardId}/reply`,
            data:JSON.stringify(data), // javascript object인 data를 json 형식으로 변환해서 java가 인식할 수 있도록 준비함
            contentType:"application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
            dataType:"json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열(생긴게 json이라면)=> javascript object로 변경해줌
        }).done(function (resp){
            if(resp.status===500){
                alert("댓글작성 실패");
            } else {
                alert("댓글작성 완료");
            }
            location.href=`/board/${data.boardId}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    replyDelete:function (boardId, replyId){

        $.ajax({
            type:"DELETE",
            url:`/api/board/${boardId}/reply/${replyId}`,
            dataType:"json"
        }).done(function (resp){
            if(resp.status===500){
                alert("댓글삭제 실패");
            } else {
                alert("댓글삭제 완료");
            }
            location.href=`/board/${boardId}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    replyUpdate:function (boardId,replyId){
        let data = {
            boardId : boardId,
            content: $("#edit-reply-content").val()
        };
        console.log(replyId);
        console.log(data);

        $.ajax({
            type:"PUT",
            url:`/api/board/${boardId}/reply/${replyId}`,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function (resp){
            if(resp.status===500){
                alert("댓글수정 실패");
            } else {
                alert("댓글수정 완료");
            }
            location.href=`/board/${boardId}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();


document.querySelector(".btn-edit-box-display").addEventListener("click",(e)=>{
    console.log(e.target.id);
    editBox(e.target.id);
})

function editBox(id){
    let exist = Array.from(document.getElementsByClassName(id));
    console.log(exist);
    exist.forEach(box=>{
    console.log(box);
        box.classList.add("d-none");
    });

    let edit = Array.from(document.getElementsByClassName(id+"none"));
    console.log(edit);
    edit.forEach(box=>{
    console.log(box);
        box.classList.remove("d-none");
    });

}

document.querySelector(".btn-reply-edit-cancel").addEventListener("click",(e)=>{
console.log(e.target.id);
    editCancel(e.target.id);
})

function editCancel(id){
let exist = Array.from(document.getElementsByClassName(id));
console.log(exist);
    exist.forEach(box=>{
    console.log(box);
        box.classList.remove("d-none");
    });

    let edit = Array.from(document.getElementsByClassName(id+"none"));

    console.log(edit);
    edit.forEach(box=>{
    console.log(box);
        box.classList.add("d-none");
    });
}
