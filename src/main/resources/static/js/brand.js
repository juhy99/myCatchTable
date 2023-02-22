$(function () {
  $(".rest_brand> div").hide();
  $(".__tab-menu a")
    .click(function () {
      $(".rest_brand > div").hide().filter(this.hash).fadeIn();
      $(".__tab-menu a").removeClass("__active");
      $(this).addClass("__active");
      return false;
    })
    .filter(":eq(0)")
    .click();
});
