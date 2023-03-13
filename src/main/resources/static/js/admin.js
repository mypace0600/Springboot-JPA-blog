let index = {
    init:function (){
        $("#btn-request-admin-role").on("click",()=>{
            this.requestAdminRole();
        });
        $(".btn-approve-request-admin-role").on("click",(e)=>{
            this.updateAdminRole(e.target.id, e.target.value);
        });
        $(".admin-user-delete").on("click",(e)=>{
            console.log(e.target);
            this.deleteUser(e.target.id);
        });
    },

    requestAdminRole:function (){

        $.ajax({
            type:"PUT",
            url:"/admin/role/request",
            dataType:"json"
        }).done(function (resp){
            if(resp.status === 200 || resp.data === 1){
                alert("요청 완료");
                location.href = "/";
            } else {
                let index = resp.data.indexOf(":");
                let errorStr = resp.data.substring(index);
                alert("요청 실패 "+ errorStr);
            }
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    updateAdminRole:function (id,value){
        let data ={
            role : value
        }
        console.log(data);
        $.ajax({
            type:"PUT",
            url:"/admin/role/"+id,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function (resp){
            if(resp.status === 200 || resp.data === 1){
                alert("권한 변경 완료");
                location.href = "/admin/users";
            } else {
                let index = resp.data.indexOf(":");
                let errorStr = resp.data.substring(index);
                alert("권한 변경 실패 "+ errorStr);
            }
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    deleteUser:function (id){
        $.ajax({
            type:"DELETE",
            url:"/admin/role/"+id,
            dataType:"json"
        }).done(function (resp){
            if(resp.status===500){
                alert("유저 삭제 실패");
            } else {
                alert("유저 삭제 완료");
            }
            location.href = "/admin/users";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();
