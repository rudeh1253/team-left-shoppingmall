$(document).ready(() => {
	$("#submit-button").on("click", () => {
		const name = $("input[name='name']").val();
		const tel = $("input[name='tel']").val();

        console.log("name=" + name);
        console.log("tel=" + tel);

        const command = $("body").data("command");
		
		$.ajax({
			url: `/member.do?command=${command}`,
            method: "POST",
            data: {
                name, tel
            },
            contentType: "application/x-www-form-urlencoded",
            dataType: "html",
            success: (data, status, jqXHR) => {
                $("#modal-content").empty();
                $("#body-before").siblings().remove();
                $("#body-before").after(data);
                $("#modal-trigger").trigger("click");
            },
            error: (jqXHR, status, errorThrown) => {
                console.error(jqXHR);
                console.error(status);
                console.error(errorThrown);
            }
		});
	});
});