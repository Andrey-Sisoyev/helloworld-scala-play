$ ->
  $.get "/books", (data) ->
    if data? and data.length
      $books = $("#books")
      $table = $("<table>")

      $books.append $table
      
      $.each data, (index, item) ->
        $tr = $("<tr>")
        $table.append $tr
        $tr.append $("<td>").text item.name
        $tr.append $("<td>").text item.wordsCount