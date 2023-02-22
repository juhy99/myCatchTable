// function test() {
//     if ($('.display').css('display') == 'block') {
//         $('.display').css('display', 'none');
//     } else {
//         $('.display').css('display', 'block');
//     }
// }


function homzzang() {
    var hz = document.getElementById("hz");
    var c = hz.childNodes;
    var txt = "";
    var i;
    // for(i = 0; i<c.length; i++){
    //   txt = txt + c[i].nodeName + "<br>";
    //   console.log(txt);
    // }
    console.log(c[13]);

    if(c[13].getAttribute('class', 'remove')){
        c[13].removeAttribute('class', 'remove');
        // 숫자 ++1
    }else{
        c[13].setAttribute('class', 'remove');
    }

    // document.getElementById("demo").innerHTML = c[3].text;
}


const items = document.querySelectorAll("#toggleButton");

items.forEach((item)=>{

    item.addEventListener('click',()=>{

        const ch=item.childNodes;
        // console.log(ch);

        if(ch[3].getAttribute('class', 'remove')){
            ch[3].removeAttribute('class', 'remove');
            // 숫자 ++1
        }else{
            ch[3].setAttribute('class', 'remove');
        }

    })
})


