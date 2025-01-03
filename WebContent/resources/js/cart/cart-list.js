$(document).ready(function(e) {
	$(".increase-btn").on("click", function(e) {
		let requestUrl = "/cart.do?command=increase&productid="+ $(this).attr("id");
		const productId = $(this).attr("id");
		const amountElement = $("#amount-"+ productId);
		let currentAmount = parseInt(amountElement.text(),10); // 현재 수량
		currentAmount += 1; // 수량 증가
		amountElement.text(currentAmount);
		$("tr[data-product-id='" + productId + "']").attr("data-amount", currentAmount);
		calculateTotalPrice();
		$.ajax({
			url : requestUrl, // 서버에서 수량을 업데이트하는 URL
			type : 'GET'
		});
	});

	$(".decrease-btn").on("click",function(e) {
		const productId = $(this).attr("id");
	    const amountElement = $("#amount-" + productId);
	    let currentAmount = parseInt(amountElement.text(), 10);
	    currentAmount -= 1;
	    if (currentAmount <= 0) {
	        requestUrl = "/cart.do?command=delete&productid=" + productId;
	        alert("상품이 삭제되었습니다.");
	        $("tr[data-product-id='" + productId + "']").attr("data-amount", currentAmount);
		    calculateTotalPrice();
	        $.ajax({
	            url: requestUrl,
	            type: 'GET',
	            success: function () {
	                location.reload();
	            }
	        });
	    } else {
	        requestUrl = "/cart.do?command=decrease&productid=" + productId;
	        amountElement.text(currentAmount);
	        $("tr[data-product-id='" + productId + "']").attr("data-amount", currentAmount);
		    calculateTotalPrice();
	        $.ajax({
	            url: requestUrl,
	            type: 'GET'
	        });
	    }

	});
	
	$(".delete-btn").on("click", function() {
        var productId = $(this).data("product-id");
        let requestUrl = "/cart.do?command=delete&productid="+ $(this).attr("data-product-id");
        $.ajax({
            url: requestUrl,  // 요청을 보낼 URL
            type: 'GET',
            success: function(response) {
                alert("상품이 삭제되었습니다.");
                // 페이지 전체를 새로 고침하여 갱신
                location.reload();
            },
            error: function(xhr, status, error) {
                alert("삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
            }
        });
        $("button[data-product-id='" + productId + "']").closest("tr").remove();
    });
    
    $('#compareBtn').on('click', function () {
        const checkedValues = $('.form-check-input:checked').map(function () {
            return $(this).val();
        }).get();
        
        if (checkedValues.length === 2) {
            location.href=`/product.do?command=compare-product&productIds=${checkedValues}`;
        } else {
            const warningModal = bootstrap.Modal.getOrCreateInstance($('#warning-modal'));
            $("#modal-text").text("2개를 선택해주세요!");
            warningModal.show();
        }
    });
    
    $('#buy-btn').on('click', function () {
        location.href="/purchase.do?command=purchase-product";
    });
});

function calculateTotalPrice() {
    let totalPrice = 0;
    // 모든 상품을 순회하면서 가격과 수량을 곱한 값을 totalPrice에 더함
    $("tr[data-price]").each(function() {
        const price = parseFloat($(this).attr('data-price'));
        const amount = parseInt($(this).attr('data-amount'));
        totalPrice += price * amount;
    });

    // 총합을 화면에 출력 (천 단위 구분 기호 추가)
    $('#totalPrice').text(totalPrice.toLocaleString());
}

// 페이지 로딩 시, 총합 계산
window.onload = calculateTotalPrice;