$('#searchForm').on('submit',function(e){
    e.preventDefault();
});

$(document).ready(function(){
    let allbooks = [];

    $('#search-heading').hide();

    $.getJSON('/js/books.json', function(data){
        allbooks = data;
        renderBooks(allbooks);
    })

    $('#search-heading').hide();
    $('#searchInput').on('keyup', function(){
        $('#result').html('');
        $('#search-heading').show();
        var search = $('#searchInput').val().trim();
        var expression = new RegExp(search, "i");

        if(search === ''){
            return;
        }

        $.getJSON('/js/books.json', function(data){
            let matchFound = false;
            $.each(data, function(key, value){
                if(value.title.search(expression) !== -1 || value.author.search(expression) !== -1)
                {
                    matchFound = true;
                    $('#result').append(`
                        <div class="book-card">
                            <img src="${value.image}" alt="Book 1">
                            <div class="book-info">
                            <h4>${value.title}</h4>
                            <p><strong>Author:</strong>${value.author}</p>
                            <p><strong>Availability:</strong> ${value.availability}</p>
                            <a href="javascript:void(0);" class="reserve-btn" onclick="openPopup('${value.bookId}',this)">Reserve</a> 
                            </div>
                        </div>
                    `);
                }
            });
            if(!matchFound){
                $('#result').html('<p>No books found.</p>');
            }
        });
    });
    function renderBooks(bookList){
        $('#result').empty();
        bookList.forEach(function(value) {
            $('#result').append(`
                <div class="book-card">
                    <img src="${value.image}" alt="Book 1">
                    <div class="book-info">
                        <h4>${value.title}</h4>
                        <p><strong>Author:</strong>${value.author}</p>
                        <p><strong>Availability:</strong> ${value.availability}</p>
                        <a href="javascript:void(0);" class="reserve-btn" onclick="openPopup('${value.bookId}',this)">Reserve</a> 
                    </div>
                </div>
            `);
        });
    }
});