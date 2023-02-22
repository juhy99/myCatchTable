function delColRes(){


    if (!confirm("컬렉션에서 삭제하시겠습니까?")) {

    }else {
        const saveIdx = document.getElementById("saveIdx");
        let param = {"saveIdx": saveIdx.value};
        $.ajax({
            type: 'POST',
            data: JSON.stringify(param),
            url: '/mypage/collection/detail/delRes',
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                if (data = 'ok') {
                    alert("삭제되었습니다.")
                    location.reload();
                } else {
                    window.reload();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("ERROR : " + textStatus + " : " + errorThrown);
            }
        });
    }
}