$(document).ready(function () {

  $.get(
      "/api/flux/books",
      showAllBooks
  );
});

function showAllBooks(json) {

  $('#tableContent').empty();

  // Get table body and print
  for (var i = 0; i < Object.keys(json).length; i++) {
    $('#tableContent').append(
        '<tr id="' + json[i]["id"] + '">'
        + '    <td>' + json[i]["title"] + '</td>\n'
        + '    <td>' + json[i]["author"] + '</td>\n'
        + '    <td>' + json[i]["genre"] + '</td>\n'
        + '    <td>\n'
        + '      <div class="dropdown dropleft">\n'
        + '        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">\n'
        + '          Action\n'
        + '        </button>\n'
        + '        <div class="dropdown-menu">\n'
        + '          <div class="edit-book" id="' + json[i]["id"] + '">\n'
        + '            <a class="dropdown-item" href="#">Edit</a>\n'
        + '          </div>\n'
        + '          <div class="delete-book" id="' + json[i]["id"] + '">\n'
        + '            <a class="dropdown-item" href="#">Delete</a>\n'
        + '          </div>\n'
        + '        </div>\n'
        + '      </div>\n'
        + '    </td>\n'
        + '</tr>'
    );

    $(".bookTable").show();
  }

  $(".edit-book").click(function () {
    var book_id = $(this).attr('id');
    $(".bookTable").hide();
    showEditor(book_id);
  });

  $(".new-book").click(function () {
    $(".bookTable").hide();
    showNewEditor();
  });

  $(".delete-book").click(function () {
    var book_id = $(this).attr('id');
    $.ajax({
      url: "/api/flux/books/" + book_id,
      type: "DELETE"
    })
    $(this).closest("tr").remove();
  });

  // $('#editForm').off().on('click', function (e) {
  $(".save-book").click(function () {
    // e.preventDefault();
    $.post("api/flux/books",
        $(this).serialize()).done(function (data) {
      $.get(
          "/api/flux/books",
          showAllBooks
      );
    });
    $('#editForm').hide();
  });

}

function showEditor(book_id) {
  $.get(
      "/api/flux/books/" + book_id,
      fillBookEditor
  );
  $('#editForm').show();
}

function showNewEditor() {
  $('#editForm').show();
  fillBookEditor(JSON.stringify({}));
}

function fillBookEditor(json) {
  $('#id').empty().attr("value", null).attr("value", json["id"]);
  $('#title').empty().attr("value", null).attr("value", json["title"]).val(
      json["title"]);

  $.get(
      "/api/flux/authors",
      function (authorJson) {
        $('#author').empty();
        for (var i = 0; i < Object.keys(authorJson).length; i++) {
          $('#author').append('<option value="' + authorJson[i]["id"] + '\"' +
              ((json["authorId"] == authorJson[i]["id"]) ? ' selected' : '')
              + '>' + authorJson[i]["fullName"] + '</option>');
        }
      }
  );

  $.get(
      "/api/flux/genres",
      function (genreJson) {
        $('#genre').empty();
        for (var i = 0; i < Object.keys(genreJson).length; i++) {
          $('#genre').append('<option value="' + genreJson[i]["id"] + '\"' +
              ((json["genreId"] == genreJson[i]["id"]) ? ' selected' : '')
              + '>'
              + genreJson[i]["name"] + '</option>');
        }
      }
  );

  $('#editForm').off().on('submit', function (e) {
    e.preventDefault();
    $.post("api/flux/books",
        $(this).serialize()).done(function (data) {
      $.get(
          "/api/flux/books",
          showAllBooks
      );
    });
    $('#editForm').hide();
  });

}