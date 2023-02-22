function delRes(saveIdx){

    if (!confirm("저장리스트에서 삭제하시겠습니까?")) {

    }else {
        console.log(saveIdx);
        $.ajax({
            type: 'DELETE',
            url: '/mypage/saveList/' + saveIdx,
            success: function (data) {
                console.log(data);
                if(data == 'OK'){
                    location.reload();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("ERROR : " + textStatus + " : " + errorThrown);
            }
        });
    }
}