function checkBlock(resaBisName, prIdx) {
    if (prIdx == null || prIdx == 0) {
        alert('로그인 후 이용해주세요!');
        location.href = '/login';
    } else {
        $.ajax({
            url: '/reservation/blockCheck'
            , type: 'GET'
            , success: function (data) {
                if (data) {
                    alert("예약이 불가능한 회원입니다")
                    location.reload();
                } else {
                    location.href = '/reservation/' + resaBisName
                }
            }
        })

    }

}