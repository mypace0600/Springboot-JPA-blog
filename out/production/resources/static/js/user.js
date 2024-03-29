let index = {
    init:function (){
        $("#btn-save").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
            this.save();
        });
        $("#btn-update").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
            this.update();
        });
        $("#btn-user-delete").on("click",()=>{
            this.deleteUser();
        })

        // 전통적 방식의 로그인 구현
        /*$("#btn-login").on("click",()=>{  // function(){} 이 아니라 ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
            this.login();
        });*/
    },

    save:function (){
        // alert('user의 SAVE 버튼 호출됨');
        let data = {
            userName: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        let regexEmail = new RegExp("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])");
        if(!regexEmail.test(data.email)){
            alert("이메일 형식이 올바르지 않습니다.");
        } else {

            // console.log(data);
            // ajax통신을 이용해서 데이터를 json으로 변경해서 insert 요청하는 이유
            // 1. 요청에 대한 응답을 html이 아닌 json 데이터로 받기 위해서 (응답을 받는 클라이언트가 항상 브라우저는 아니기 때문에 서버를 하나만 쓸 수 있다.)
            // 2. 비동기통신(Request를 보내고 Response를 상관하지 않는 상태)을 하기 위해서
            // ajax default = async 비동기호출
            $.ajax({
                type: "POST",
                url: "/auth/joinProc",
                data: JSON.stringify(data), // javascript object인 data를 json 형식으로 변환해서 java가 인식할 수 있도록 준비함
                contentType: "application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
                dataType: "json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열(생긴게 json이라면)=> javascript object로 변경해줌
            }).done(function (resp) {
                if (resp.status === 500) {
                    alert("회원가입 실패");
                } else {
                    alert("회원가입 완료");
                }
                location.href = "/";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    // 전통적 방식의 로그인 구현
    /*login:function (){
        let data = {
            userName: $("#username").val(),
            password: $("#password").val(),
        };
        $.ajax({
            type:"POST",
            url:"/api/user/login",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function (resp){
            alert("로그인 완료");
            console.log(resp);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }*/

    update:function (){
            let data = {
                id : $("#id").val(),
                userName: $("#username").val(),
                password: $("#password").val(),
                email: $("#email").val()
            };

            $.ajax({
                type:"PUT",
                url:"/user",
                data:JSON.stringify(data), // javascript object인 data를 json 형식으로 변환해서 java가 인식할 수 있도록 준비함
                contentType:"application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
                dataType:"json" // 요청에 대한 응답이 왔을 때 기본적으로 문자열(생긴게 json이라면)=> javascript object로 변경해줌
            }).done(function (resp){
                if(resp.status === 200 || resp.data === 1){
                    alert("수정 완료");
                } else {
                    alert("수정 실패");
                }
                location.href="/";
            }).fail(function (error){
                alert(JSON.stringify(error));
            });
    },

    deleteUser:function (){

        let id = $("#id").val();
        console.log("@@@@@@@ id :"+id);

        $.ajax({
            type:"DELETE",
            url:"/user/"+id,
            dataType:"json"
        }).done(function (resp){
            if(resp.status === 200 || resp.data === 1){
                alert("탈퇴 완료");
            } else {
                alert("탈퇴 실패");
            }
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();
