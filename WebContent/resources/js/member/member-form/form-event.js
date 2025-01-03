function initFormEvents() {
    elementSelectorToValidationCallback.forEach((item) => {
        $(item.input).on("change", (e) => {
            const isValid = item.func();
            const targetElem = $(e.target);
            if (isValid) {
                targetElem.removeClass("is-invalid");
                targetElem.addClass("is-valid");
            } else {
                targetElem.removeClass("is-valid");
                targetElem.addClass("is-invalid");
            }
            setFormValidationFeedback(targetElem, isValid, item.message);
        })
    });

    $("input[name='role']").on("change", (e) => {
        const isSeller = e.target.value === "sell";
        if (isSeller) {
            const company = $(e.target).data("company");
            $("#role-input-box").after(`
                <div class="input-box" id="company-input-box">
                    <label for="">회사</label>
                    <div class="form-input-wrapper">
                        <input class="form-control form-input" type="text" name="company" value="${company}" required>
                    </div>
                </div>
            `);
        } else {
            $("#company-input-box").remove();
        }
    });

    $("input[name='company']").on("change", (e) => {
        const val = e.target.value;
        $("#seller-select").data("company", val);
    });

    $("#profile-image").on("click", (e) => {
        $("#profile-image-file-select").trigger("click");
    });

    $("#profile-image-file-select").on("change", (e) => {
        const fileReader = new FileReader();
        fileReader.onload = () => {
            $("#profile-image").attr("src", fileReader.result);
            $("#profile-image").data("filename", getRandomImageName(e.target.files[0].name));
            
            const isEdit = $("body").data("is-edit") === true;
            if (isEdit) {
                $("#profile-image").data("has-changed", true);
            }

            console.log($("#profile-image").data("filename"));
        };
        fileReader.readAsDataURL(e.target.files[0]);
        e.target.files[0];
        $("#to-default-image-button").remove();
        const defaultImageButton = $(`<button type="button" id="to-default-image-button">기본 프로필로</button>`);
        defaultImageButton.on("click", () => {
            $("#profile-image").attr("src", "/resources/images/default-profile-image.png");
            $("#profile-image").data("filename", "default-profile-image.png");
            $("#to-default-image-button").remove();
            $("#profile-image-file-select").val("");
        });
        $("#profile-image").after(defaultImageButton);
    });

    $("#password-change-submit-button").on("click", () => {
        if (!validatePasswordChange()) {
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
                    "비밀번호가 일치하지 않습니다"
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
}

function validatePasswordChange() {
    const isCurrentPasswordValid = validateCurrentPassword();
    const isNewPasswordValid = validateNewPassword();
    const isNewPasswordCheckValid = validateNewPasswordCheck();
    return isCurrentPasswordValid && isNewPasswordValid && isNewPasswordCheckValid;
}

function validateCurrentPassword() {
    const elem = $("#current-password-input");
    const value = elem.val();

    if (value.length === 0) {
        setFormValidationFeedbackForPasswordChange(elem, false, "현재 비밀번호를 입력하세요");
        return false;
    }

    if (value.length < 8 || value.length > 16) {
        setFormValidationFeedbackForPasswordChange(elem, false, "비밀번호는 8 ~ 16자 길이로 입력해 주세요");
        return false;
    }

    setFormValidationFeedbackForPasswordChange(elem, true);
    return true;
}

function validateNewPassword() {
    const elem = $("#new-password-input");
    const value = elem.val();

    if (value.length === 0) {
        setFormValidationFeedbackForPasswordChange(elem, false, "새 비밀번호를 입력하세요");
        return false;
    }

    if (value.length < 8 || value.length > 16) {
        setFormValidationFeedbackForPasswordChange(elem, false, "비밀번호는 8 ~ 16자 길이로 입력해 주세요");
        return false;
    } 

    setFormValidationFeedbackForPasswordChange(elem, true);
    return true;
}

function validateNewPasswordCheck() {
    const elem = $("#new-password-check-input");
    const value = elem.val();
    const newPasswordValue = $("#new-password-input").val();

    if (value.length === 0) {
        setFormValidationFeedbackForPasswordChange(elem, false, "새 비밀번호를 재입력하세요");
        return false;
    }

    if (value !== newPasswordValue) {
        setFormValidationFeedbackForPasswordChange(elem, false, "비밀번호가 일치하지 않습니다");
        return false;
    }
    setFormValidationFeedbackForPasswordChange(elem, true);
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

function setFormValidationFeedback(element, isValid, message) {
    element.siblings(".form-feedback").remove();
    if (!isValid) {
        element.after(`
            <div class="invalid-feedback feedback-box">
                ${message}
            </div>
        `);
    }
}

const FILENAME_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ-_".split("");

/**
 * @param {string} imageName 
 */
function getRandomImageName(imageName, len = 20) {
    const split = imageName.split(".");
    const extension = split[split.length - 1];
    
    let randomFilename = ""
    for (let i = 0; i < len; i++) {
        randomFilename += FILENAME_CHARS[Math.floor(Math.random() * 100) % FILENAME_CHARS.length];
    }
    return randomFilename + "." + extension;
}