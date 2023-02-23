let index = {
    init:function (){
        $("#btn-save").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
            this.save();
        });
        $("#btn-login").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
            this.login();
        });
    },

    save:function (){
        // alert('user의 SAVE 버튼 호출됨');
        let data = {
            userName: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // console.log(data);
        // ajax통신을 이용해서 데이터를 json으로 변경해서 insert 요청하는 이유
        // 1. 요청에 대한 응답을 html이 아닌 json 데이터로 받기 위해서 (응답을 받는 클라이언트가 항상 브라우저는 아니기 때문에 서버를 하나만 쓸 수 있다.)
        // 2. 비동기통신(Request를 보내고 Response를 상관하지 않는 상태)을 하기 위해서
        // ajax default = async 비동기호출
        $.ajax({
            type:"POST",
            url:"/blog/api/user",
            data:JSON.stringify(data), // javascript object인 data를 json 형식으로 변환해서 java가 인식할 수 있도록 준비함
            contentType:"application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
            dataType:"json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열(생긴게 json이라면)=> javascript object로 변경해줌
        }).done(function (resp){
            alert("회원가입 완료");
            console.log(resp);
            location.href="/blog";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    login:function (){
        // alert('user의 SAVE 버튼 호출됨');
        let data = {
            userName: $("#username").val(),
            password: $("#password").val(),
        };

        // console.log(data);
        // ajax통신을 이용해서 데이터를 json으로 변경해서 insert 요청하는 이유
        // 1. 요청에 대한 응답을 html이 아닌 json 데이터로 받기 위해서 (응답을 받는 클라이언트가 항상 브라우저는 아니기 때문에 서버를 하나만 쓸 수 있다.)
        // 2. 비동기통신(Request를 보내고 Response를 상관하지 않는 상태)을 하기 위해서
        // ajax default = async 비동기호출
        $.ajax({
            type:"POST",
            url:"/blog/api/user/login",
            data:JSON.stringify(data), // javascript object인 data를 json 형식으로 변환해서 java가 인식할 수 있도록 준비함
            contentType:"application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
            dataType:"json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열(생긴게 json이라면)=> javascript object로 변경해줌
        }).done(function (resp){
            alert("로그인 완료");
            console.log(resp);
            location.href="/blog";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();
