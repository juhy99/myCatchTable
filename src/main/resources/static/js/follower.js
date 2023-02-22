let isFollow = document.querySelectorAll('.is-follow');
let prIdx = document.querySelectorAll('.pr-idx');
let listPrIdx = document.querySelectorAll('.list-pridx');
let size = document.querySelector('.size').value;

for (let i = 0; i < size; i++) {
    if (isFollow[i].value == 'true') {
        console.log("⭕")
        let btn = "<button type='button' onclick ='follow(" + prIdx[i].value + "," + listPrIdx[i].value + ")' class='btn btn-md btn-outline-orange btn-rounded full-width t" + listPrIdx[i].value + "'>" +
            "<span class='label " + listPrIdx[i].value + "'>팔로잉</span>" +
            "</button>"
        $('.following' + listPrIdx[i].value).append(btn);
    } else {
        console.log("❌")
        let btn = "<button type='button' onclick ='follow(" + prIdx[i].value + "," + listPrIdx[i].value + ")' class='btn btn-md btn-orange btn-rounded full-width t" + listPrIdx[i].value + "'>" +
            "<span class='label " + listPrIdx[i].value + "'>팔로우</span>" +
            "</button>"
        $('.following' + listPrIdx[i].value).append(btn);
    }
}

function follow(prIdx, timeLineIdx) {
    if(prIdx == null || prIdx == 0) {
        alert('로그인 후 이용해주세요!')
        location.href="/login";
    }
    const spanText = $('.' + timeLineIdx).text();
    console.log(spanText);
    if (spanText == '팔로우') {
        following(true, prIdx, timeLineIdx);
    } else {
        following(false, prIdx, timeLineIdx);
    }
}

function following(check, prIdx, timeLineIdx) {
    console.log(prIdx, timeLineIdx);
    let param = {"follower": prIdx, "following": timeLineIdx};
    if (check) {
        $.ajax({
            type: 'POST',
            data: JSON.stringify(param),
            url: "/timeline/following",
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                if (data == 'OK') {
                    $('.t' + timeLineIdx).addClass('btn-outline-orange');
                    $('.t' + timeLineIdx).removeClass('btn-orange');
                    $('.' + timeLineIdx).text('팔로잉');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("ERROR : " + textStatus + " : " + errorThrown);
            }
        });
    } else {
        $.ajax({
            type: 'POST',
            data: JSON.stringify(param),
            url: "/timeline/unfollowing",
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                if (data == 'OK') {
                    $('.t' + timeLineIdx).addClass('btn-orange');
                    $('.t' + timeLineIdx).removeClass('btn-outline-orange');
                    $('.' + timeLineIdx).text('팔로우');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("ERROR : " + textStatus + " : " + errorThrown);
            }
        });
    }
}