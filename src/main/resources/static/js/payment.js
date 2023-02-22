const {resMonth, resDay, resTime, resaBisName, resPerson} = JSON.parse(localStorage.getItem("ResInfo"));
console.log(resMonth, resDay, resTime, resaBisName, resPerson)

let infoText = resMonth + "." + resDay + " · " + resTime + ": 00 · " + resPerson + "명";
let priceText = resPerson*50000;
let personText = resPerson;
$('#resaBisName').append(resaBisName);
$('#resPerson').append(personText);
$('#resInfo').append(infoText);
$('.resPrice').append(priceText);



$(function (){
    $("#btn-kakao-pay").click(function (){
        let resName = document.getElementById('res_name');
        let resHp = document.getElementById('res_hp');
        let resRequest = document.getElementById("res_request");
        let bisIdx = document.getElementById('bisIdx');
        let bdIdx = document.getElementById('bdIdx');
        let prIdx = document.getElementById('prIdx');
        console.log(bisIdx)
        console.log(bdIdx)
        console.log(prIdx)

        let data = {
            "total_amount": priceText
            ,"prName": resName.innerText
            ,"resaBisName": resaBisName
            ,"totalPrice" : priceText
            ,"resHp": resHp.innerText
            ,"resRequest": resRequest.value
            ,"resPerson" : resPerson
            ,"resTime" : resTime
            ,"resMonth" : resMonth
            ,"resDay" : resDay
            ,"bisIdx" : bisIdx.value
            ,"bdIdx": bdIdx.value
            ,"prIdx": prIdx.value
        }

        let json = JSON.stringify(data);

        $.ajax({
            type:'POST'
            ,url:'/reservation/pay'
            ,data: json
            ,contentType : "application/json",
            success:function (res){
                let url =res.next_redirect_pc_url;
                location.href = url;
            }
        })
    })
})
