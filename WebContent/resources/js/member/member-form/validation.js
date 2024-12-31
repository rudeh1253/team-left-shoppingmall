// TODO: validation 실패 시 메시지

function validateEmailAccount() {
    const emailAccount = $("input[name='email-count']").val();
    
    return /^[0-9a-z]+$/.test(emailAccount);
}

function validateEmailHost() {
    const emailHost = $("input[name='email-host']").val();
    console.log(emailHost);

    return /^\w+\.\w+(\.\w)?$/.test(emailHost);
}

function validatePasswordInput() {
    const passwordValue = $("input[name='password']").val();

    return passwordValue.length >= 8 && passwordValue.length <= 16;
}

function validatePasswordCheck() {
    const passwordValue = $("input[name='password']").val();
    const passwordCheck = $("input[name='password-check']").val();
    return passwordValue === passwordCheck;
}

function validateMemberName() {
    const nameValue = $("input[name='member-name']").val();
    return /[가-힣]{1,5}/.test(nameValue);
}

function validateTel() {
    const tel = $("input[name='tel']").val();
    return /(010|011|016|017|019)\-\d{3,4}\-\d{4}/.test(tel);
}

function validateAddress() {
    const address = $("input[name='address']").val();
    return address.length > 0;
}

function validateBirthDate() {
    const year = parseInt($("input[name='year-select']"));
    const month = parseInt($("input[name='month-select']"));
    const dayOfMonth = parseInt($("input[name='day-of-month-select']"));

    const now = new Date();
    const currentInput = new Date(year, month - 1, dayOfMonth);

    return now - currentInput >= 0;
}
