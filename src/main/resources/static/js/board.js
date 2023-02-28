let index = {
    init:function (){
        $("#btn-save").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
            this.save();
        });

        $("#btn-delete").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
                    this.deleteById();
        });

        $("#btn-update").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
                    this.updateById();
        });

        $("#btn-reply-save").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
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
            alert("글쓰기 완료");
            console.log(resp);
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
        let boardId = $("#boardId").val();
        let data = {
            content: $("#reply-content").val()
        };

        $.ajax({
            type:"POST",
            url:`/api/board/${boardId}/reply`,
            data:JSON.stringify(data), // javascript object인 data를 json 형식으로 변환해서 java가 인식할 수 있도록 준비함
            contentType:"application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
            dataType:"json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열(생긴게 json이라면)=> javascript object로 변경해줌
        }).done(function (resp){
            alert("댓글작성 완료");
            console.log(resp);
            location.href=`/board/${boardId}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();
