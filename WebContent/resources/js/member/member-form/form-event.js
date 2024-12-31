function initFormEvents() {
    [
        {
            input: "input[name='email-account']",
            func: validateEmailAccount
        },
        {
            input: "input[name='email-host']",
            func: validateEmailHost
        },
        {
            input: "input[name='password']",
            func: validatePasswordInput
        },
        {
            input: "input[name='password-check']",
            func: validatePasswordCheck
        },
        {
            input: "input[name='member-name']",
            func: validateMemberName
        },
        {
            input: "input[name='tel']",
            func: validateTel
        },
        {
            input: "input[name='address']",
            func: validateAddress
        }
    ].forEach((item) => {
        $(item.input).on("change", (e) => {
            if (item.func()) {
                $(e.target).removeClass("is-invalid");
                $(e.target).addClass("is-valid");
            } else {
                $(e.target).removeClass("is-valid");
                $(e.target).addClass("is-invalid");
            }
        })
    });
}