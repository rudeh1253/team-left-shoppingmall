$(document).ready(() => {
    initSubmit();
    initFormEvents();
});

function initSubmit() {
    $("#submit-button").on("click", (e) => {
        const isEdit = $(e.target).data("is-edit") === true;
        console.log("isEdit=" + isEdit);
        e.preventDefault();
        uploadFile(submit, isEdit);
    });
}

function uploadFile(callback, isEdit) {
    const filename = $("#profile-image").data("filename");
    console.log("filename=" + filename);
    const file = $("#profile-image-file-select")[0].files[0];
    $.ajax({
        url: `/file.do?command=upload-file&filename=${filename}`,
        type: "POST",
        data: file,
        dataType: "json",
        cache: false,
        contentType: file,
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
}

function hasChangedAndIsEdit(isEdit) {
    const hasChanged = $("#profile-image").data("has-changed") === true;
    return isEdit && hasChanged || !isEdit;
}

function submit(isEdit) {
    const filename = $("#profile-image").data("filename");
    const profileImg = `/resources/images/${filename}`;
    $("#thumbnail").val(profileImg);
    $("#productModal").submit();
}

function initFormEvents() {
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
    });
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