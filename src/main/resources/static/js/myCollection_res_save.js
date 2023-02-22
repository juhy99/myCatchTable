window.onload = function (){
    const btn = document.getElementById('saveBtn');
    btn.addEventListener('click',sendit);
}
function sendit(){

    const prIdx = document.getElementById("prIdx");
    const colIdx = document.getElementById("colIdx");
    const arr=[];
    const resaBisName = document.getElementsByName("resaBisName");
    for (let i = 0;i<resaBisName.length; i++){
        if(resaBisName[i].checked==true){
            arr.push(resaBisName[i].value);
        }
    }
    console.log(arr);

    let param = {"colIdx":colIdx.value,"bisIdx":arr};
    $.ajax({
        type: 'POST',
        data: JSON.stringify(param),
        url: "/mypage/collection/saveRes",
        contentType: "application/json",
        success: function (data) {
            console.log(data);
            if(data != null) {
                location.href="/mypage/collection/detail/"+data;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("ERROR : " + textStatus + " : " + errorThrown);
        }
    });
}