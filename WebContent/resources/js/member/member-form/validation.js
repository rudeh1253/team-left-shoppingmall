function validateEmailAccount() {
    const emailAccount = $("input[name='email-count']").val();
    
    return /^[0-9a-z]+$/.test(emailAccount);
}

function validateEmailHost() {
    const emailHost = $("input[name='email-host']").val();

    return /^\w+\.\w(\.\w)?$/.test(emailHost);
}