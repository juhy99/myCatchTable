function double_check(){
    const nick = document.getElementById('prNick');

    $.ajax({
        url:'/idCheck'
        ,type:'POST'
        ,data:{
            "prNick":nick.value
        }
        ,success:function (data){
            console.log(data);
            if(data){
                alert("사용가능 닉네임입니다")

            }
            else {
                alert("중복된 닉네임 입니다")
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("ERROR : " + textStatus + " : " + errorThrown);
        }
    })
}