$(document).ready(function () {

  $.get(
      "/api/books",
      showAllBooks
  );
});

function showAllBooks(json) {

  // Get table body and print
  for (var i = 0; i < Object.keys(json).length; i++) {
    $('#table_content').append(
        '<tr id="' + json[i][Object.keys(json[0])[0]] + '">'
        + '    <td>' + json[i][Object.keys(json[0])[1]] + '</td>\n'
        + '    <td>' + json[i][Object.keys(json[0])[3]] + '</td>\n'
        + '    <td>' + json[i][Object.keys(json[0])[5]] + '</td>\n'
        + '    <td>\n'
        + '      <div class="dropdown dropleft">\n'
        + '        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">\n'
        + '          Action\n'
        + '        </button>\n'
        + '        <div class="dropdown-menu">\n'
        + '          <a class="dropdown-item" href="/api/book/edit/'
        + json[i][Object.keys(json[0])[0]] + '"\n'
        + '              href="book_edit.html">Edit</a>\n'
        + '          <div class="delete-book" id="' + json[i][Object.keys(
        json[0])[0]] + '">\n'
        + '            <a class="dropdown-item" href="#">Delete</a>\n'
        + '          </div>\n'
        + '        </div>\n'
        + '      </div>\n'
        + '    </td>\n'
        + '</tr>'
    );
  }

  $(".delete-book").click(function () {
    var book_id = $(this).attr('id');
    $.ajax({
      url: "/api/book/" + book_id,
      type: "DELETE"
    })
    $(this).closest("tr").remove();
    // $('#table_content').find("tr#" + book_id).remove();
  });

}