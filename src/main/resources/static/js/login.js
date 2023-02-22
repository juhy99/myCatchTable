window.onload = function (){
    const btn = document.getElementById('loginBtn');
    btn.addEventListener('click',sendit);
}
function sendit(){

    const prHp = document.getElementById('prHp');
    const prUserpw = document.getElementById('prUserpw');

    if(prHp.value==''){
        alert('아이디를 입력하세요');
        prHp.focus()
        return false;
    }

    if(prUserpw.value==''){
        alert('비밀번호를 입력하세요');
        prUserpw.focus()
        return false;
    }
    document.getElementById('frm').submit(

    );
}