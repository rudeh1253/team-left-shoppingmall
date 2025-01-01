$(document).ready(() => {
    $("#login-btn").on("click", () => {
        $("#login-form").trigger("submit");
    });
});