let slider4 = document.querySelectorAll(".items4");
console.log(slider4);
let isDown4 = false;
let startX4;
let scrollLeft4;

function slide99(slider4) {
  slider4.addEventListener("mousedown", (e) => {
    isDown4 = true;
    slider4.classList.add("active");
    startX4 = e.pageX - slider4.offsetLeft;
    scrollLeft4 = slider4.scrollLeft;
  });

  slider4.addEventListener("mouseleave", () => {
    isDown4 = false;
    slider4.classList.remove("active");
  });

  slider4.addEventListener("mouseup", () => {
    isDown4 = false;
    slider4.classList.remove("active");
  });

  slider4.addEventListener("mousemove", (e) => {
    if (!isDown4) return;
    e.preventDefault();
    const x4 = e.pageX - slider4.offsetLeft;
    const walk4 = x4 - startX4;
    slider4.scrollLeft = scrollLeft4 - walk4;
  });
}

for (let i = 0; i < slider4.length; i++) {
  slide99(slider4[i]);
}
