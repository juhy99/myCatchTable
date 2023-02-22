window.onload = function (){
    const btn = document.getElementById('findBtn');
    btn.addEventListener('click',findCheck);
}
function findCheck(){
    const prHp = document.getElementById('prHp');
    const prName = document.getElementById('prName');

    $.ajax({
        url:'/findPassword'
        ,type:'POST'
        ,data:{
            "prHp":prHp.value,
            "prName":prName.value

        }
        ,success:function (data){
            console.log(data);
            if(data == null){
                alert("일치하는 회원정보가 없습니다.");
            }
            else {
                location.href="/resetPassword/" + prHp.value;
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("ERROR : " + textStatus + " : " + errorThrown);
        }
    })
}