window.onload = function (){
    const btn = document.getElementById('leaveBtn');
    btn.addEventListener('click',sendit);
}
function sendit(){
    if (!confirm("정말로 탈퇴하시겠어요?")) {
        // 취소(아니오) 버튼 클릭 시 이벤트
    } else {
        // 확인(예) 버튼 클릭 시 이벤트
        const prIdx = document.getElementById("prIdx").innerText;
        console.log(prIdx)

        fetch('http://localhost:8888/mypage/' + prIdx, {
            method: 'DELETE',
        })
            .then((res) => {
                alert('탈퇴 성공')
                location.href = '/';
                return;
            })
            .then((data) => {
                console.log(data);
                return;
            })
            .catch((err) => {
                alert('에러!');
                location.reload();
                return;
            });
    }
    // fetch
    return true;
}