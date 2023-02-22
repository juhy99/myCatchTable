window.onload = function (){
    const btn = document.getElementById('colModBtn');
    btn.addEventListener('click',colMod);
}
function colMod(){
    const colTitle = document.getElementById("colTitle");
    const colContent = document.getElementById("colContent");
    const colIdx = document.getElementById("colIdx");
    let lock = document.getElementById('colLock');
    // let colLock = true;
    if(lock.checked){
        colLock = true;
    }else{
        colLock = false;
    }
    let param = {"colIdx":colIdx.value,"colTitle": colTitle.value, "colContent": colContent.value, "colLock":colLock};
    $.ajax({
        type: 'POST',
        data: JSON.stringify(param),
        url: '/mypage/collection/detail/modify',
        contentType: "application/json",
        success: function (data) {
            console.log(data);
            if( data = 'ok') {
                alert('컬렉션 수정 성공')
                location.href = "/mypage/collection";
            }else{
                window.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("ERROR : " + textStatus + " : " + errorThrown);
        }
    });

    // fetch('http://localhost:8888/mypage/collection/detail/'+colIdx+'/modify', {
    //     method: 'POST',
    //     headers: { 'Content-Type': 'application/json'},
    //     body: JSON.stringify({
    //         "transaction_time":`${new Date()}`,
    //         "resultCode":"ok",
    //         "description":"정상",
    //         "data":{
    //             "colTitle":`${colTitle.value}`,
    //             "colContent":`${colContent.value}`,
    //             "colLock":`${colLock}`
    //         }
    //     }),
    // })
    //     .then((res) => {
    //         alert('수정성공')
    //         location.href='/mypage/collection/';
    //         return;
    //     })
    //     .then((data) => {
    //         console.log(data);
    //         return;
    //     })
    //     .catch((err) => {
    //         alert('에러!');
    //         location.reload();
    //         return;
    //     });
    // document.getElementById('frm').submit(
    //
    // );
}