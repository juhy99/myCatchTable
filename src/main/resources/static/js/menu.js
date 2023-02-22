$(function () {
  $(".tab-contents > div").hide();
  $(".list a")
    .click(function () {
      $(".tab-contents > div").hide().filter(this.hash).fadeIn();
      $(".list a").removeClass("active");
      $(this).addClass("active");
      return false;
    })
    .filter(":eq(0)")
    .click();
});
