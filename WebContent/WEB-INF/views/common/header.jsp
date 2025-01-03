<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="p-3 mb-3 border-bottom">
	<div class="container z-3">
		<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between">
			<a href="/" class="d-flex align-items-center fs-24 mb-2 mb-lg-0 link-body-emphasis text-decoration-none">Team Left</a>

			<form action="product.do" method="GET" class="col-6 mb-2 mb-md-0 form-control-lg d-flex gap-2 flex-row" role="search">
				<!-- <input type="search" class="form-control col-8" placeholder="상품 관련 검색어를 입력하세요!" name="keyword" required>
				<input type="submit" class="form-control" value="검색" /> -->
				<input type="hidden" name="command" value="search-product"/>
				<div class="input-group">
					<input type="search" class="form-control" name="keyword" placeholder="상품 관련 검색어를 입력하세요!"" aria-label="Recipient's username" aria-describedby="button-addon2" required>
					<button class="btn btn-outline-secondary" type="submit">검색</button>
				</div>
			</form>
			<c:if test="${sessionScope.member eq null }">
				<div class="d-flex gap-3">
					<a class="text-decoration-none text-reset" href="/member.do?command=login">로그인</a>
					<a class="text-decoration-none text-reset" href="/member.do?command=insert-member">회원가입</a>
				</div>
			</c:if>
			<c:if test="${sessionScope.member ne null }">
				<div class="d-flex align-items-center">
					<a href="/cart.do?command=show-cart" class="text-decoration-none d-flex align-items-center me-3">
						<i class="bi bi-cart" style="font-size: 1.5rem;"></i>
					</a>
					<div class="dropdown text-end ms-2">
						<a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
							<img src="/resources/images/default-profile-image.png" data-filename="default-profile-image.png" alt="프로필 이미지" width="32" height="32" class="rounded-circle">
						</a>
						<ul class="dropdown-menu text-small">
							<li><a class="dropdown-item" href="/member.do?command=profile">마이페이지</a></li>
							<li><a class="dropdown-item" href="/product.do?command=show-register-list&userid=${sessionScope.member}">상품 목록</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/member.do?command=logout">로그아웃</a></li>
						</ul>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</header>