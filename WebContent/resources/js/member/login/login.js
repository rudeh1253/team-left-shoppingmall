$(document).ready(() => {
    $("#login-btn").on("click", () => {
        const email = $("input[name='email']").val();
        const password = $("input[name='password']").val();

        console.log("email=" + email);
        console.log("password=" + password);

        $.ajax({
            url: "/member.do?command=login",
            method: "POST",
            data: {
                email,
                password
            },
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: function (data, status, jqXHR) {
                console.log(data);
                location.href = "/";
            },
            error: function (jqXHR, status, errorThrown) {
                console.log(status);
            }
        });
    });
});