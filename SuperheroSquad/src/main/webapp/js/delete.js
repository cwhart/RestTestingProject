$(document).on("click", "a.delete", function (event) {
    if(confirm("Are you sure you want to delete this?") ) {
        return true;
    } else {
        event.preventDefault();
        return false;
    }
});