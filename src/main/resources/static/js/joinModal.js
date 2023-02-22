const loremIpsum = document.getElementById("lorem-ipsum")
fetch("https://baconipsum.com/api/?type=all-meat&paras=200&format=html")
    .then(response => response.text())
    .then(result => loremIpsum.innerHTML = result)

const modal = document.getElementById("modal")
const btnModal = document.getElementById("btn-modal")
// const btnModal = document.getElementsByClassName("btn-modal");
btnModal.addEventListener("click", e => {
    modal.style.display = "flex"
})

modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modal.style.display = "none"
    }
})

const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modal.style.display = "none"
})

const modal2 = document.getElementById("modal2")
const btnModal2 = document.getElementById("btn-modal2")
// const btnModal = document.getElementsByClassName("btn-modal");
btnModal2.addEventListener("click", e => {
    modal2.style.display = "flex"
})

modal2.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modal2.style.display = "none"
    }
})

const closeBtn2 = modal2.querySelector(".close-area")
closeBtn2.addEventListener("click", e => {
    modal2.style.display = "none"
})