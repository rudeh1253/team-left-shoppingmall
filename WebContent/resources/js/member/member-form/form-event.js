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
            $("#role-input-box").after(`
                <div class="input-box" id="company-input-box">
                    <label for="">회사</label>
                    <div class="form-input-wrapper">
                        <input class="form-control form-input" type="text" name="company" required>
                    </div>
                </div>
            `);
        } else {
            $("#company-input-box").remove();
        }
    });

    $("#profile-image").on("click", (e) => {
        $("#profile-image-file-select").trigger("click");
    });

    $("#profile-image-file-select").on("change", (e) => {
        const fileReader = new FileReader();
        fileReader.onload = () => {
            $("#profile-image").attr("src", fileReader.result);
            $("#profile-image").data("filename", getRandomImageName(e.target.files[0].name));
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