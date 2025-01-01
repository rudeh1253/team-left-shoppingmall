function initSubmit() {
    $("#submit-button").on("click", () => {
        if (validate()) {
            submit();
        }
    });
}

function validate() {
    let isAllValid = true;
    for (const item of elementSelectorToValidationCallback) {
        const isValid = item.func();
        const targetElem = $(item.input);
        if (isValid) {
            targetElem.removeClass("is-invalid");
            targetElem.addClass("is-valid");
        } else {
            targetElem.removeClass("is-valid");
            targetElem.addClass("is-invalid");
        }
        console.log(targetElem);
        setFormValidationFeedback(targetElem, isValid, item.message);
        isAllValid &&= isValid;
    }
    return isAllValid;
}

function submit() {
    const submitData = {
        email: `${$("input[name='email-account']").val()}@${$("input[name='email-host']").val()}`,
        password: $("input[name='password']").val(),
        memberName: $("input[name='member-name']").val(),
        year: $("#year-select").val(),
        month: $("#month-select").val(),
        dayOfMonth: $("#day-of-month-select").val(),
        tel: $("input[name='tel']").val(),
        address: $("input[name='address']").val(),
        gender: $("input[name='gender']:checked").val(),
        role: $("input[name='role']:checked").val(),
        company: $("input[name='company']").val() || null,
        answer: $("input[name='answer']").val()
    }

    const signUpForm = $("#sign-up-form");

    const hiddenForm = document.createElement("form");
    hiddenForm.action = signUpForm.attr("action");
    hiddenForm.method = signUpForm.attr("method");

    Object.keys(submitData).forEach((key) => {
        const value = submitData[key];
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = key;
        input.value = value;
        hiddenForm.appendChild(input);
    });

    document.body.appendChild(hiddenForm);
    hiddenForm.submit();
}