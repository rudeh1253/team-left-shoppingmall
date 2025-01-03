$(document).ready(function () {
    $("#password-change-submit-button").on("click", () => {
        if (!validate()) {
            return;
        }
        const currentPassword = $("#current-password-input").val();
        const newPassword = $("#new-password-input").val();
        const newPasswordCheck = $("#new-password-check-input").val();

        $.ajax({
            type: "POST",
            url: "/member.do?command=edit-password",
            data: {
                currentPassword, newPassword
            },
            dataType: "json",
            success: function(response) {
                alert("비밀번호가 변경되었습니다");
                $("#modal-close-button").trigger("click");
            },
            error: function(xhr, status, errorThrown) {
                console.log(xhr.responseText);
                setFormValidationFeedback(
                    $("#current-password-input"),
                    false,
                    "비밀번호가 일치하지"
                );
            }
        });
    });

    $("#password-change-modal-trigger-button").on("click", () => {
        $("#current-password-input").val("");
        $("#current-password-input").removeClass("is-invalid");
        $("#current-password-input").removeClass("is-valid");
        $("#current-password-input").siblings(".feedback-box").remove();
        $("#new-password-input").val("");
        $("#new-password-input").removeClass("is-valid");
        $("#new-password-input").removeClass("is-invalid");
        $("#new-password-input").siblings(".feedback-box").remove();
        $("#new-password-check-input").val("");
        $("#new-password-check-input").removeClass("is-valid");
        $("#new-password-check-input").removeClass("is-invalid");
        $("#new-password-check-input").siblings(".feedback-box").remove();
    });
});

function validate() {
    const isCurrentPasswordValid = validateCurrentPassword();
    const isNewPasswordValid = validateNewPassword();
    const isNewPasswordCheckValid = validateNewPasswordCheck();
    return isCurrentPasswordValid && isNewPasswordValid && isNewPasswordCheckValid;
}

function validateCurrentPassword() {
    const elem = $("#current-password-input");
    const value = elem.val();

    if (value.length === 0) {
        setFormValidationFeedback(elem, false, "현재 비밀번호를 입력하세요");
        return false;
    }

    if (value.length < 8 || value.length > 16) {
        setFormValidationFeedback(elem, false, "비밀번호는 8 ~ 16자 길이로 입력해 주세요");
        return false;
    }

    setFormValidationFeedback(elem, true);
    return true;
}

function validateNewPassword() {
    const elem = $("#new-password-input");
    const value = elem.val();

    if (value.length === 0) {
        setFormValidationFeedback(elem, false, "새 비밀번호를 입력하세요");
        return false;
    }

    if (value.length < 8 || value.length > 16) {
        setFormValidationFeedback(elem, false, "비밀번호는 8 ~ 16자 길이로 입력해 주세요");
        return false;
    } 

    setFormValidationFeedback(elem, true);
    return true;
}

function validateNewPasswordCheck() {
    const elem = $("#new-password-check-input");
    const value = elem.val();
    const newPasswordValue = $("#new-password-input").val();

    if (value.length === 0) {
        setFormValidationFeedback(elem, false, "새 비밀번호를 재입력하세요");
        return false;
    }

    if (value !== newPasswordValue) {
        setFormValidationFeedback(elem, false, "비밀번호가 일치하지 않습니다");
        return false;
    }
    setFormValidationFeedback(elem, true);
    return true;
}

function setFormValidationFeedbackForPasswordChange(element, isValid, message) {
    if (isValid) {
        element.removeClass("is-invalid");
        element.addClass("is-valid");
    } else {
        element.removeClass("is-valid");
        element.addClass("is-invalid");
    }
    element.siblings(".feedback-box").remove();
    if (!isValid) {
        element.after(`
            <div class="invalid-feedback feedback-box">
                ${message}
            </div>
        `);
    }
}