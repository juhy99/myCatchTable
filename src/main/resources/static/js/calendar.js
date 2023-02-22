let month;
let day;

<!-- 캘린더를 위한 스크립트-->
$("#datepicker").datepicker({
    language: "ko",
    minDate: new Date()
});

$(function () {
    $("#datepicker").datepicker({
        onSelect: function (dateText) {
            //  $('#datepicker-display').datepicker("setDate", $(this).datepicker("getDate"));
            month = $("#datepicker")
                .datepicker({dateFormat: "mm"})
                .val();
            day = $("#datepicker")
                .datepicker({dateFormat: "dd"})
                .val();
        },
    });
});

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

    let param = {"resMonth": resMonth, "resDay": resDay, "resaBisName": resa_bis_name, "resPerson": res_person};

    let price = res_person * 50000;

    let price_html = document.getElementsByClassName("price_html")[0];
    let price_html2 = document.getElementsByClassName("price_html")[1];
    let person_html = document.getElementsByClassName("person_html")[0];
    price_html.innerHTML = price + "원";
    price_html2.innerHTML = price + "원";
    person_html.innerHTML = res_person + "명";

    $.ajax({
        anyne: true,
        type: 'POST',
        data: JSON.stringify(param),
        url: "/reservation/" + resa_bis_name,
        contentType: "application/json",
        success: function (data) {
            console.log(data)
            if (data.length == 0) {
                let item_empty = '';
                item_empty = "<a href=\"#\" id=\"scrollContainer_1671430849_164_itemElem_17002\" class=\"timetable-list-item empty\" style=\"margin-bottom: auto; margin-top: auto;\"> <span class=\"time\">예약 마감</span></a>"
                $("#timelist").html(item_empty);
            } else {
                let btn = '';
                for (let i = 0; i < data.length; i++) {
                    btn += "<button onclick='change(" + i + ")' class='timetable-list-item'>"
                        + "<span id='time' class='time'>" + data[i].shopResTime + ":00 </span></button>"

                }
                $("#timelist").html(btn);
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("ERROR : " + textStatus + " : " + errorThrown);
        }
    });
}

function change(idx) {
    let get = document.getElementsByClassName("time")[idx].innerHTML;
    $('.boxing').removeClass('boxing');
    $('.boxing').addClass('unboxing');
    resTime = get.substr(0, 2);
}

function sendPayment() {
    resMonth = month;
    resDay = day;
    res_person = $('input[name=count]:checked').val();
    resa_bis_name = document.getElementById('header').innerText;
    console.log(resMonth);

    if (resDay == undefined || resMonth == undefined) {
        alert("날짜를 선택해주세요")
        location.reload()
    } else if (res_person == undefined) {
        alert("인원을 선택해주세요")
        location.reload()
    } else if (resTime == '') {
        alert("시간을 선택해주세요")
        location.reload()
    } else {
        let param = {
            "resMonth": resMonth,
            "resDay": resDay,
            "resaBisName": resa_bis_name,
            "resPerson": res_person,
            "resTime": resTime
        };
        if (resDay == '' || resMonth == '') {
            alert("날짜를 선택해주세요")
            location.reload()
        } else {
            localStorage.setItem("ResInfo", JSON.stringify(param));
            location.href = resa_bis_name + "/payment";

        }
    }
}


