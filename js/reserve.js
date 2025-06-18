function openPopup(bookId,el) {
    document.getElementById('reservePopup').style.display = 'block';
    document.getElementById('bookIdInput').value = bookId;

    currReserveBtn = el;
}

function closePopup() {
    document.getElementById('reservePopup').style.display = 'none';
    document.getElementById('reserveForm').reset();

    currReserveBtn = null;
}

$(document).ready(function () {
    $("#reserveForm").validate({
        rules: {
            bookId: {
                required: true,
            },
            date: {
                required: true
            }
        },
        messages: {
            bookId: {
                required: "Book Id is required to reserve book!",
            },
            date: {
                required: "Please choose a reservation date."
            }
        }
    });

    $('#reserveForm').on('submit', function (e) {
        e.preventDefault();

        if (!$('#reserveForm').valid()) return;

        const bookId = $('#bookIdInput').val();
        alert("Submitted!");
        alert("Book Reserved: " + bookId);

        if(currReserveBtn)
        {
            $(currReserveBtn).text("Book Reserved!");
            $(currReserveBtn).css("background-color", "green");
        }

        closePopup();
    });
});
