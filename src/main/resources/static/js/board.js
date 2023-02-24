let index = {
    init:function (){
        $("#btn-save").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
            this.save();
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
    }
}

index.init();