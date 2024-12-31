function birthDateInit() {
    const today = new Date();

    const year = today.getFullYear();
    const month = today.getMonth() + 1;
    const dayOfMonth = today.getDate();

    fillBirthDateSelectionInput(year, month, dayOfMonth);

    $("#year-select").on("change", (e) => {
        const selectedYear = parseInt(e.target.value);
        console.log(e.target.value);
        const selectedMonth = parseInt($("#month-select option:selected").val());

        console.log("selectedMonth=" + selectedMonth);
        console.log("selectedYear=" + selectedYear);
        fillBirthDateSelectionInput(selectedYear, selectedMonth, 1);
    });

    $("#month-select").on("change", (e) => {
        const selectedMonth = parseInt(e.target.value);
        const selectedYear = parseInt($("#year-select option:selected").val());

        console.log("selectedMonth=" + selectedMonth);
        console.log("selectedYear=" + selectedYear);
        fillBirthDateSelectionInput(selectedYear, selectedMonth, 1);
    });
}

/**
 * 
 * @param {number} year 
 * @param {number} month 
 * @param {number} dayOfMonth
 */
function fillBirthDateSelectionInput(year, month, dayOfMonth) {
    const jYearElem = $("#year-select");
    const jMonthElem = $("#month-select");
    const jDayOfMonthElem = $("#day-of-month-select");
    jYearElem.empty();
    jMonthElem.empty();
    jDayOfMonthElem.empty();

    getYearSelectionList(new Date().getFullYear()).forEach((e) => {
        jYearElem.append(`<option value="${e}" ${e === year && "selected"}>${e}</option>`);
    });

    for (let m = 1; m <= 12; m++) {
        jMonthElem.append(`<option value="${m}" ${m === month && "selected"}>${m}</option>`);
    }

    getAvailableDatesOfMonth(year, month).forEach((num) => {
        jDayOfMonthElem.append(`<option value="${num}" ${num === dayOfMonth && "selected"}>${num}</option`);
    });
}

/**
 * 기준 year로부터 현재까지 선택 가능한 년을 가져온다.
 * @param {number} year 기준 년
 * @returns {number[]} 선택 가능한 년들
 */
function getYearSelectionList(year) {
    const years = [];
    for (let i = year - 100; i <= year; i++) {
        years.push(i);
    }
    return years;
}

/**
 * 특정 년과 특정 월에서 선택 가능한 일의 배열을 가져온다.
 * @param {*} year 기준 년
 * @param {*} month 기준 월
 * @returns {number[]} 선택 가능한 일
 */
function getAvailableDatesOfMonth(year, month) {
    const date = new Date(year, month, 0);
    const lastDayOfMonth = date.getDate();
    const arr = [];
    for (let i = 0; i < lastDayOfMonth; i++) {
        const num = i + 1;
        arr.push(num);
    }
    return arr;
}