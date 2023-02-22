const slider3 = document.querySelector('.items3');
  let isDown3 = false;
  let startX3;
  let scrollLeft3;

  slider3.addEventListener('mousedown', e => {
    isDown3 = true;
    slider3.classList.add('active');
    startX3 = e.pageX - slider3.offsetLeft;
    scrollLeft3 = slider3.scrollLeft;
  });

  slider3.addEventListener('mouseleave', () => {
    isDown3 = false;
    slider3.classList.remove('active');
  });

  slider3.addEventListener('mouseup', () => {
    isDown3 = false;
    slider3.classList.remove('active');
  });

  slider3.addEventListener('mousemove', e => {
    if (!isDown3) return; 
    e.preventDefault();
    const x3 = e.pageX - slider3.offsetLeft;
    const walk3 = x3 - startX3;
    slider3.scrollLeft = scrollLeft3 - walk3;
  });