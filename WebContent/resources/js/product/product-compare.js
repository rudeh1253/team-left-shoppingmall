$(document).ready(function(e) {
	$("#back-btn").on("click", function(e) {
		if (window.history.length > 1) {
            window.history.back();
        } else {
            alert("No previous page in history!");
        }
	});
});
