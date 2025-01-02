<%@ page contentType="text/html; charset=UTF-8"%>


<script>
	function getCommandValue() {
		const urlParams = new URLSearchParams(window.location.search);
		return urlParams.get('command');
	}

	function active(string){
		if(string == "normal"){
			document.getElementById('normal').classList.add("active");
			document.getElementById('seller').classList.add("link-dark");
		} else if (string =="seller"){
			document.getElementById('seller').classList.add("active");
			document.getElementById('normal').classList.add("link-dark");
		}
	}
	window.onload = function() {
		const command = getCommandValue();
		console.log("Command Value:", command);

		// 'command' 값에 따라 특정 메뉴에 '▶' 표시
		if (command === 'profile') {
			document.getElementById('profile').innerText = '▶';
			active("normal");
		} else if (command === 'purchase') {
			document.getElementById('purchase').innerText = '▶';
			active("normal");
		} else if (command === 'show-register-list') {
			document.getElementById('show-register-list').innerText = '▶';
			active("seller");
		} else if (command === 'sell') {
			document.getElementById('sell').innerText = '▶';
			active("seller");
		}
	};
</script>


<body>
	<div style="width: 280px"></div>
	<div class="d-flex flex-column flex-shrink-0 p-3 bg-light h-100"
		style="width: 280px; position: fixed">
		<a href="/" style="padding: 10px 0; justify-content: center;"
			class="d-flex align-items-center mb-2 mb-lg-0 link-body-emphasis text-decoration-none">Team
			Left</a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item"><a href="#" id="normal" class="nav-link"
				aria-current="page"> <svg class="bi me-2" width="16" height="16">
						</svg> 일반 사용자
			</a></li>
			<hr>
			<c:if test="${!isMyProfile}">
				<li class="nav-item"><a href="/member.do?command=profile"
					class="nav-link link-dark" aria-current="page"> <svg
							class="bi me-2" width="16" height="16">
							<span id="purchase" style="color: #94A5EB"></span></svg> 내 정보
				</a></li>
			</c:if>
			<li class="nav-item"><a href="/member.do?command=profile&userid=${userid}"
				class="nav-link link-dark" aria-current="page"> <svg
						class="bi me-2" width="16" height="16">
						<span id="profile" style="color: #94A5EB"></span></svg>
						<c:if test="${isMyProfile}">내 정보</c:if>
						<c:if test="${!isMyProfile}">프로필</c:if>
			</a></li>
			<c:if test="${empty role || role == 'buy'}">
				<li class="nav-item"><a href="/purchase.do?command=purchase&userid=${ userid }"
					class="nav-link link-dark" aria-current="page"> <svg
							class="bi me-2" width="16" height="16">
							<span id="purchase" style="color: #94A5EB"></span></svg> 구매 내역
				</a></li>
			</c:if>
			<c:if test="${not empty role && role == 'sell'}">
			<hr>
			
				<li class="nav-item"><a href="#" id="seller" class="nav-link"
					aria-current="page"> <svg class="bi me-2" width="16" height="16">
							</svg> 판매자
				</a></li>
				<hr>
				<li class="nav-item"><a href="/purchase.do?command=sell&userid=${ userid }"
					class="nav-link link-dark" aria-current="purchase"> <svg
							class="bi me-2" width="16" height="16">
							<span id="sell" style="color: #94A5EB"></span></svg> 판매 내역
				</a></li>
				<li class="nav-item"><a
					href="/product.do?command=show-register-list&userid=${ userid }"
					class="nav-link link-dark" aria-current="page"> <svg
							class="bi me-2" width="16" height="16">
							<span id="show-register-list" style="color: #94A5EB"></span></svg> 물품 내역
				</a></li>
			</c:if>
		</ul>
		<hr>

	</div>
</body>
</html>