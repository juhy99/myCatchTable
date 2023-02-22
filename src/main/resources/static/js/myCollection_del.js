function colDel(){
    const colIdx = document.getElementById("colIdx");

    let param = {"colIdx":colIdx.value};
    $.ajax({
        type: 'DELETE',
        data: JSON.stringify(param),
        url: '/mypage/collection/detail/',
        contentType: "application/json",
        success: function (data) {
            console.log(data);
            if( data = 'ok') {
                alert('컬렉션 삭제 완료')
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