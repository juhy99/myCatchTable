let month;
let day;


let resMonth = '';
let resDay = '';
let res_person = '';
let resa_bis_name = '';
let resTime = '';
function sendRes() {
    resMonth = month;
    resDay = day;
    res_person = $('input[name=count]:checked').val();
    resa_bis_name = document.getElementById('header').innerText;

    if(res_person == undefined){
        return true;
    }

    resTime = '';

    let param = {"resMonth": resMonth, "resDay":resDay,"resaBisName":resa_bis_name, "resPerson" : res_person};


    $.ajax({
        anyne:true,
        type:'POST',
        data: JSON.stringify(param),
        url:"/reservation/"+resa_bis_name,
        contentType: "application/json",
        success : function(data) {
            console.log(data)
            if(data.length == 0){
                let item_empty = '';
                item_empty = "<a href=\"#\" id=\"scrollContainer_1671430849_164_itemElem_17002\" class=\"timetable-list-item empty\" style=\"margin-bottom: auto; margin-top: auto;\"> <span class=\"time\">빈자리 알림신청</span></a>"
                $("#timelist").html(item_empty);
            }else {
                let btn ='';
                for (let i = 0; i < data.length; i++) {
                    btn += "<button onclick='change("+i+")' class='timetable-list-item' style='margin-bottom: auto; margin-top: auto; min-width: 70px;'>"
                        +"<span id='time' class='time'>"+data[i].shopResTime +":00 </span></button>"

                }
                $("#timelist").html(btn);
            }

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("ERROR : " + textStatus + " : " + errorThrown);
        }
    });
}

function change(idx) {
    let get = document.getElementsByClassName("time")[idx].innerHTML;
    let time = get.substr(0, 2);
    resTime = time;
}

function upDateReservation(){
    resMonth = month;
    resDay = day;
    res_person = $('input[name=count]:checked').val();
    resa_bis_name = document.getElementById('header').innerText;
    console.log(resMonth);
    let resIdx = document.getElementById('resIdx').innerText;

    if (resDay==undefined||resMonth==undefined){
        alert("날짜를 선택해주세요")
        location.reload()
    } else if (res_person==undefined){
        alert("인원을 선택해주세요")
        location.reload()
    }else if(resTime==''){
        alert("시간을 선택해주세요")
        location.reload()
    }else{
        let param = {"resMonth": resMonth, "resDay":resDay,"resaBisName":resa_bis_name, "resPerson" : res_person ,"resTime" : resTime};
        if(resDay==''||resMonth==''){
            alert("날짜를 선택해주세요")
            location.reload()
        }else {
            $.ajax({
                anyne:true,
                type: 'POST'
                ,url:'/reservation/update/planned/'+resIdx
                ,data:JSON.stringify(param)
                ,contentType: "application/json"
                ,success(data){
                    if(data==null){
                        alert('예약 변경을 실패했습니다')
                        location.reload();
                    }else{
                        alert('예약 변경을 완료했습니다')
                        location.href='/mydining/planned'
                    }
                }
            })
        }
    }
}