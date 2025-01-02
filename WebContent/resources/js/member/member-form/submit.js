function initSubmit() {
    $("#submit-button").on("click", (e) => {
        const isEdit = $(e.target).data("is-edit") === true;
        console.log("isEdit=" + isEdit);
        if (validate(isEdit)) {
            uploadFile(submit, isEdit);
        }
    });
}

function validate(isEdit) {
    let isAllValid = true;
    for (const item of elementSelectorToValidationCallback) {
        if (isEdit && (item.id === "password" || item.id === "passwordCheck")) {
            console.log("item.id=" + item.id);
            continue;
        }
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
        isAllValid = isAllValid && isValid;
    }
    return isAllValid;
}

function uploadFile(callback, isEdit) {
    const filename = $("#profile-image").data("filename");
    console.log("filename=" + filename);
    if (filename !== "default-profile-image.png" && hasChangedAndIsEdit(isEdit)) {
        const file = $("#profile-image-file-select")[0].files[0];
        $.ajax({
            url: `/file.do?command=upload-file&filename=${filename}`,
            type: "POST",
            data: file,
            dataType: "json",
            cache: false,
            contentType: file.type,
            processData: false,
            success: (data) => {
                console.log(data);
                callback(isEdit);
            },
            error: (jqXHR, status, errorThrown) => {
                console.error(jqXHR);
                console.error(status);
                console.error(errorThrown);
            }
        });
    } else {
        callback(isEdit);
    }
}

function hasChangedAndIsEdit(isEdit) {
    const hasChanged = $("#profile-image").data("has-changed") === true;
    return isEdit && hasChanged || !isEdit;
}

function submit(isEdit) {
    const filename = $("#profile-image").data("filename"); 
    const submitData = {
        memberName: $("input[name='member-name']").val(),
        year: $("#year-select").val(),
        month: $("#month-select").val(),
        dayOfMonth: $("#day-of-month-select").val(),
        tel: $("input[name='tel']").val(),
        address: $("input[name='address']").val(),
        gender: $("input[name='gender']:checked").val(),
        role: $("input[name='role']:checked").val(),
        company: $("input[name='company']").val() || null,
        answer: $("input[name='answer']").val(),
        profileImg: `/resources/images/${filename}`
    }

    if (!isEdit) {
        submitData.email = `${$("input[name='email-account']").val()}@${$("input[name='email-host']").val()}`;
        submitData.password = $("input[name='password']").val();
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