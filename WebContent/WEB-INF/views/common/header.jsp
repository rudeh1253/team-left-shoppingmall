<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="p-3 mb-3 border-bottom">
	<div class="container z-3">
		<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between">
			<a href="/" class="d-flex align-items-center fs-5 fw-bold mb-2 mb-lg-0 link-body-emphasis text-decoration-none">Team Left</a>

			<form action="product.do" method="GET" class="col-6 mb-2 mb-md-0 form-control-lg d-flex gap-2 flex-row" role="search">
				<input type="hidden" name="command" value="search-product"/>
				<div class="input-group">
					<input type="search" class="form-control" name="keyword" placeholder="상품 관련 검색어를 입력하세요!"" aria-label="Recipient's username" aria-describedby="button-addon2" required>
					<button class="btn bg-primary fw-bold d-flex align-items-center justify-content-center " type="submit" style="position: absolute; right: 0; z-index: 99; height: 38px; width:50px">
					<img width=20 alt="" src="/resources/images/search-icon.png">
					</button>
				</div>
			</form>
			<c:if test="${sessionScope.member eq null }">
				<div class="d-flex gap-3" style="color:grey">
					<a class="text-decoration-none text-reset" href="/member.do?command=login">로그인</a>
					<a class="text-decoration-none text-reset" href="/member.do?command=insert-member">회원가입</a>
				</div>
			</c:if>
			<c:if test="${sessionScope.member ne null }">
				<div class="d-flex align-items-center">
					<c:if test="${sessionScope.role eq 'buy' }">
						<a href="/cart.do?command=show-cart" class="text-decoration-none d-flex align-items-center me-3">
							<i class="bi bi-cart" style="font-size: 1.5rem;"></i>
						</a>
					</c:if>
					<div class="dropdown text-end ms-2">
						<a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
							<img style="object-fit:contain;" src="${sessionScope.memberProfileImg }" data-filename="${sessionScope.memberProfileImg }" alt="프로필 이미지" width="32" height="32" class="rounded-circle">
						</a>
						<ul class="dropdown-menu text-small">
							<li><span class="dropdown-item">${sessionScope.memberName }</span></li>
							<li><a class="dropdown-item" href="/member.do?command=profile">마이페이지</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/member.do?command=logout">로그아웃</a></li>
						</ul>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</header>