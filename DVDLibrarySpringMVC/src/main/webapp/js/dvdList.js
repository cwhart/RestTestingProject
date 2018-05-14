$(document).ready(function () {

    // Add Button onclick handler
    $('#search-button').click(function (event) {

        //$('#errorMessages').empty();

        $.ajax({
            type: 'POST',
            url: '/DVDLibrarySpringMVC/search/dvds',
            data: JSON.stringify({
                title: $('#search-title').val(),
                director: $('#search-director').val(),
                year: $('#search-release-year').val(),
                rating: $('#search-rating').val()
                //notes: $('#search-email').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) {
                // clear errorMessages
                $('#errorMessages').empty();
                fillDvdTable(data);
            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service.  Please try again later.'));
            }
        });
    });
});

function fillDvdTable(data) {
    // we need to clear the previous content so we don't append to it
    clearDvdTable();

    // grab the the tbody element that will hold the rows of contact information
    var contentRows = $('#contentRows');

    $.each(data, function (index, dvd) {
        var title = dvd.dvdTitle;
        var director = dvd.director;
        var releaseYear = dvd.releaseYear;
        var rating = dvd.rating;

        var row = '<tr>';
        row += '<td>' + title + '</td>';
        row += '<td>' + director + '</td>';
        row += '<td>' + releaseYear + '</td>';
        row += '<td>' + rating + '</td>';
        row += '</tr>';
        contentRows.append(row);
    });
}

function clearDvdTable() {
    $('#contentRows').empty();
}