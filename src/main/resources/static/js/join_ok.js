function sendit(){

    const name = document.getElementById('prName');
    const hp = document.getElementById('prHp');
    const userpw = document.getElementById('prUserpw');
    const userpw_re = document.getElementById('passwordConfirm');
    const nick = document.getElementById('prNick');
    // const checkBtn = document.getElementById('checkBtn');
    // let nickCheck = document.getElementById('checked_nick');
    const birthY = document.getElementById('birthY');
    const birthM = document.getElementById('birthM');
    const birthD = document.getElementById('birthD');
    // const submitButton = document.querySelector('#submit-button');


    // 정규 표현식
    const expNameText = /[가-힣]+$/;                        // 한글만 가능
    // const expNameText = /^[A-Za-z가-힣]{2,20}$/;           // 
    const expHpText = /^\d{3}\d{3,4}\d{4}$/;                // (-) 제외
    // const expPwText = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}/;
    // /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}/
    const expPwText = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}/;    
    const expNickText = /^[A-Za-z가-힣\d]{1,20}$/;            //
    const expBirthYText = /^(19[0-9][0-9]|20\d{2})$/
    const expBirthMText = /^(0[0-9]|1[0-2])$/
    const expBirthDText = /^(0[1-9]|[1-2][0-9]|3[0-1])$/


    if(!expNameText.test(name.value)){
        alert('성명은 두 글자 이상의 한글로 입력하세요');
        name.focus();
        return false;
    }

    if(!expHpText.test(hp.value)){
        alert('하이픈(-)을 제외한 숫자만 입력해 주세요.');
        hp.focus();
        return false;
    }
    
    if(!expPwText.test(userpw.value)){
        alert('비밀번호는 대소문자 구분없이 영문, 숫자, 특수문자를 각각 하나 이상 반드시 사용하여 8-20자리로 설정해주세요.');
        // alert('비밀번호는 영문, 숫자, 특수문자 중 2종류 이상을 조합하여 8-20자리로 설정해주세요.');
            // 8~20, 특1, 대소문자 상관x
        // alert('비밀번호 형식을 확인하세요\n소문자,대문자,숫자,특수문자를 1개씩 꼭 입력해야합니다.');
        userpw.focus();
        return false;
    }

    if(userpw.value != userpw_re.value){
        alert('비밀번호를 확인해 주세요.');
        userpw_re.focus();
        return false;
    }

    if(!expNickText.test(nick.value)){
        alert('닉네임은 20자 이하의 한글, 영문 대소문자, 숫자를 사용할 수 있습니다.');
        nick.focus();
        return false;
    }

    // if(nick.value == "" || nickCheck.value == ""){
    //     // nickCheck.value = 'y';
    //     alert('닉네임 중복 확인을 해주세요.');
    //     nick.focus();
    //     return false;
    // } else if (){
    //     nickCheck.value = "y";
    // }

    if ($('.nick_input').attr("check_result") == "fail"){
        alert("닉네임 중복체크를 해주시기 바랍니다.");
        $('.nick_input').focus();
        return false;
    }

    if(!expBirthYText.test(birthY.value)){
        alert("생년월일의 연도를 입력해 주세요");
        birthY.focus();
        return false;
    }

    if(!expBirthMText.test(birthM.value)){
        alert("생년월일의 월을 입력해 주세요");
        birthM.focus();
        return false;
    }

    if(!expBirthDText.test(birthD.value)) {
        alert('생년월일의 일을 입력해 주세요');
        birthD.focus();
        return false;
    }

    return true;        // return은 하나만 있어야돼
}

function double_check(){

    const nick = document.getElementById('prNick');

    $('.nick_input').change(function () {
        // $('#id_check_sucess').hide();
        // $('.id_overlap_button').show();
        $('.nick_input').attr("check_result", "fail");
    })

    if ($('.nick_input').val() == '') {
        alert('닉네임을 입력해 주세요')
        return;
    }

    $.ajax({
        url:'/idCheck'
        ,type:'POST'
        ,data:{
            "prNick":nick.value
        }
        ,success:function (data){
            console.log(data);
            if(data){
                alert("사용가능한 닉네임입니다")
                $('.nick_input').attr("check_result", "success");
                // checkBtn.disabled = true;
            }
            else {
                alert("중복된 닉네임입니다")
                nick.focus();
                // checkValue.value("");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("ERROR : " + textStatus + " : " + errorThrown);
        }
    })

}
