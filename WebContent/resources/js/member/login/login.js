$(document).ready(() => {
    $("#login-btn").on("click", () => {
        doLogin();
    });

    $("input[name='email']").on("keyup", (e) => {
        if (e.keyCode === 13) {
            if ($("input[name='password']").val().length === 0) {
                $("input[name='password']").focus();
            } else {
                doLogin();
            }
        }
    });

    $("input[name='password']").on("keyup", (e) => {
        if (e.keyCode === 13) {
            if ($("input[name='email']").val().length === 0) {
                $("input[name='email']").focus();
            } else {
                doLogin();
            }
        }
    });
});

function doLogin() {
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
}
