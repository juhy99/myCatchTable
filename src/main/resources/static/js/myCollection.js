window.onload = function (){
    const btn = document.getElementById('colBtn');
    btn.addEventListener('click',sendit);
}
function sendit(){

    const colTitle = document.getElementById('colTitle');
    const colContent = document.getElementById('colContent');
    let lock = document.getElementById('colLock');
    let colLock = true;
    const prIdx = document.getElementById("prIdx");
    if(lock.checked){
        colLock = true;
    }else{
        colLock = false;
    }
    console.log(colTitle.value)
    console.log(colContent.value)
    console.log(colLock)
    console.log(prIdx.value)

    let param = {"colTitle": colTitle.value, "colContent": colContent.value, "colLock":colLock,"prIdx":prIdx.value};
        $.ajax({
            type: 'POST',
            data: JSON.stringify(param),
            url: "/mypage/myCollection/new",
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                if( data = 'ok') {
                    alert('등록성공')
                    location.href = "/mypage/collection";
                }else{
                        window.reload();
                    }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("ERROR : " + textStatus + " : " + errorThrown);
            }
        });
}