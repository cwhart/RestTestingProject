$(document).on("click", "a.delete", function (event) {
    if(confirm("Are you sure you want to delete this?") == 1) {
        return true;
    } else {
        event.preventDefault();
        return false;
    }
});