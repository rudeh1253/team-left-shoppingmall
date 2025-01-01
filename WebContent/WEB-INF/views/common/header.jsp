<%@ page contentType="text/html; charset=UTF-8" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<header class="p-3 mb-3 border-bottom">
	<div class="container">
		<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between">
			<a href="/" class="d-flex align-items-center mb-2 mb-lg-0 link-body-emphasis text-decoration-none">Team Left</a>
			<form class="nav col-6 mb-2 justify-content-center mb-md-0 form-control-lg" role="search">
				<input type="search" class="form-control" placeholder="Search..." aria-label="Search">
			</form>
			<div class="dropdown text-end">
				<a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					<img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
				</a>
				<c:if test="${sessionScope.member eq null }">
					<ul class="dropdown-menu text-small">
						<li><a class="dropdown-item" href="/product.do?command=list-product">상품 목록</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="/member.do?command=login">로그인</a></li>
						<li><a class="dropdown-item" href="/member.do?command=insert-member">회원가입</a></li>
					</ul>
				</c:if>
				<c:if test="${sessionScope.member ne null }">
					<ul class="dropdown-menu text-small">
						<li><a class="dropdown-item" href="/member.do?command=profile">마이페이지</a></li>
						<li><a class="dropdown-item" href="/product.do?command=list-product">상품 목록</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="#">로그아웃</a></li>
					</ul>
				</c:if>
			</div>
		</div>
	</div>
</header>