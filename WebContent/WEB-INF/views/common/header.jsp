<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="p-3 mb-3 border-bottom">
	<div class="container">
		<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between">
			<a href="/" class="d-flex align-items-center fs-24 mb-2 mb-lg-0 link-body-emphasis text-decoration-none">Team Left</a>
			<form action="product.do?command=search-product" method="POST" class="col-6 mb-2 mb-md-0 form-control-lg d-flex gap-2 flex-row" role="search">
				<input type="search" class="form-control col-8" placeholder="상품 관련 검색어를 입력하세요!" name="keyword">
				<input type="submit" class="form-control" value="검색" />
				
			</form>
			<c:if test="${sessionScope.member eq null }">
				<div class="d-flex gap-3">
					<a class="text-decoration-none text-reset" href="/member.do?command=login">로그인</a>
					<a class="text-decoration-none text-reset" href="/member.do?command=insert-member">회원가입</a>
				</div>
			</c:if>
			<c:if test="${sessionScope.member ne null }">
				<div class="d-flex align-items-center">
					<a href="/cart.do?command=show-cart" class="text-decoration-none d-flex align-items-center">
						<i class="bi bi-cart" style="font-size: 1.5rem;"></i>
					</a>
					<div class="dropdown text-end ms-2">
						<a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
							<img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
						</a>
						<ul class="dropdown-menu text-small">
							<li><a class="dropdown-item" href="/member.do?command=profile">마이페이지</a></li>
							<li><a class="dropdown-item" href="/product.do?command=list-product">상품 목록</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/member.do?command=logout">로그아웃</a></li>
						</ul>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</header>