const slider1 = document.querySelector('.items1');
  let isDown1 = false;
  let startX1;
  let scrollLeft1;

  slider1.addEventListener('mousedown', e => {
    isDown1 = true;
    slider1.classList.add('active');
    startX1 = e.pageX - slider1.offsetLeft;
    scrollLeft1 = slider1.scrollLeft;
  });

  slider1.addEventListener('mouseleave', () => {
    isDown1 = false;
    slider1.classList.remove('active');
  });

  slider1.addEventListener('mouseup', () => {
    isDown1 = false;
    slider1.classList.remove('active');
  });

  slider1.addEventListener('mousemove', e => {
    if (!isDown1) return; 
    e.preventDefault();
    const x1 = e.pageX - slider1.offsetLeft;
    const walk1 = x1 - startX1;
    slider1.scrollLeft = scrollLeft1 - walk1;
  });