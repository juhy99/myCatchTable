const category = document.querySelector(".categoryList");
const smallCategory = document.querySelector(".small_categoryList");
const dropdown = document.querySelector(".dropdown");
const smallHeader = document.querySelector(".small_header");
const smallDropdown = document.querySelector(".small_dropdown");
const mainHeader = document.querySelector(".header");

// 스크롤이벤트(스크롤헤더 활성화/비활성화)
window.addEventListener('scroll', () => { 
    if(window.scrollY >= 300){
        mainHeader.style.display = "none";
        smallHeader.style.display = "block";

    }else if(window.scrollY < 108){
        smallHeader.style.display = "none";
        mainHeader.style.display = "block";
        smallDropdown.style.display = "none";

    }
});


// hover시 dropdown이 내려오는 함수
const down_handler = () =>{
    dropdown.classList.remove("fade")
    dropdown.classList.add("appear")
    dropdown.style.display = "flex";
}
// hover해제시 dropdown이 올라가는 함수
const up_handler = ()=>{
    dropdown.classList.add("fade")
    dropdown.classList.remove("appear")
    dropdown.style.zIndex = 1;
}
// dropdown에 hover시 dropdown이 유지되는 함수
const keep_handler = () =>{
    dropdown.style.display = "flex";
    dropdown.classList.remove("fade")
    window.addEventListener('scroll', remove_scroll_handler)
}
// dropdown에 hover 해제시 dropdown이 유지가 해제되는 함수
const remove_keep_handler = () =>{
    dropdown.classList.add("fade");

}
// dropdown에 호버중에 스크롤시 dropdown 강제해제
const remove_scroll_handler = () =>{
    dropdown.classList.add("fade");
    dropdown.style.display = "none";

}

dropdown.addEventListener('mouseover', keep_handler)
dropdown.addEventListener('mouseout', remove_keep_handler)
category.addEventListener('mouseover',down_handler)
category.addEventListener('mouseout',up_handler)

// 스크롤헤더 드롭다운-----------------------
// hover시 dropdown이 내려오는 함수
const small_down_handler = () =>{
    // dropdown.style.zIndex = -1;

    smallDropdown.classList.remove("small_fade")
    smallDropdown.classList.add("small_appear")
    smallDropdown.style.display = "flex";

}
// hover해제시 dropdown이 올라가는 함수
const small_up_handler = ()=>{
    smallDropdown.classList.add("small_fade")
    smallDropdown.classList.remove("small_appear")
    smallDropdown.style.zIndex = 1;

}
// dropdown에 hover시 dropdown이 유지되는 함수
const small_keep_handler = () =>{
    smallDropdown.style.display = "flex";
    smallDropdown.classList.remove("small_fade")
}
// dropdown에 hover 해제시 dropdown이 유지가 해제되는 함수
const small_remove_keep_handler = () =>{
    smallDropdown.classList.add("small_fade");
}

smallDropdown.addEventListener('mouseover', small_keep_handler)
smallDropdown.addEventListener('mouseout', small_remove_keep_handler)
smallCategory.addEventListener('mouseover',small_down_handler)
smallCategory.addEventListener('mouseout',small_up_handler)