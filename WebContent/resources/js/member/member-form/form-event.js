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