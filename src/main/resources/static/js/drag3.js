const slider2 = document.querySelector('.items2');
  let isDown2 = false;
  let startX2;
  let scrollLeft2;

  slider2.addEventListener('mousedown', e => {
    isDown2 = true;
    slider2.classList.add('active');
    startX2 = e.pageX - slider2.offsetLeft;
    scrollLeft2 = slider2.scrollLeft;
  });

  slider2.addEventListener('mouseleave', () => {
    isDown2 = false;
    slider2.classList.remove('active');
  });

  slider2.addEventListener('mouseup', () => {
    isDown2 = false;
    slider2.classList.remove('active');
  });

  slider2.addEventListener('mousemove', e => {
    if (!isDown2) return; 
    e.preventDefault();
    const x2 = e.pageX - slider2.offsetLeft;
    const walk2 = x2 - startX2;
    slider2.scrollLeft = scrollLeft2 - walk2;
  });