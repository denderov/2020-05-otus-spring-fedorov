$(document).ready(function () {
  $(".delete-book").click(function () {
    var book_id = $(this).attr('id');
    $.ajax({
      url: "/book/" + book_id,
      type: "DELETE"
    })
    $(this).closest("tr").remove();
  });
});