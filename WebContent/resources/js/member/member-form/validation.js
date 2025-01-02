const elementSelectorToValidationCallback = [
    {
        id: "emailAccount",
        input: "input[name='email-account']",
        func: validateEmailAccount,
        message: "이메일 계정을 입력해 주세요: 영문 + 숫자"
    },
    {
        id: "emailHost",
        input: "input[name='email-host']",
        func: validateEmailHost,
        message: "유효한 이메일을 입력해 주세요"
    },
    {
        id: "password",
        input: "input[name='password']",
        func: validatePasswordInput,
        message: "패스워드는 8자 이상 16자 이하로 입력해 주세요"
    },
    {
        id: "passwordCheck",
        input: "input[name='password-check']",
        func: validatePasswordCheck,
        message: "패스워드와 일치하지 않습니다"
    },
    {
        id: "memberName",
        input: "input[name='member-name']",
        func: validateMemberName,
        message: "1 ~ 5글자의 한글로 입력해 주세요"
    },
    {
        id: "tel",
        input: "input[name='tel']",
        func: validateTel,
        message: "유효한 형식의 전화번호를 입력해 주세요"
    },
    {
        id: "address",
        input: "input[name='address']",
        func: validateAddress,
        message: "주소를 입력해 주세요"
    },
    {
        id: "answer",
        input: "input[name='answer']",
        func: validateAnswer,
        message: "답변을 입력하세요"
    }
];

function validateEmailAccount() {
    const emailAccount = $("input[name='email-account']").val();

    return /^[0-9a-z]+$/.test(emailAccount);
}

function validateEmailHost() {
    const emailHost = $("input[name='email-host']").val();

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

function validateAnswer() {
    const answer = $("input[name='answer']").val();
    return answer.length > 0;
}
