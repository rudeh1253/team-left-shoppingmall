<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/member/member-form.css">
<script src="/resources/js/member/member-form/birth-date-input.js"></script>
<script src="/resources/js/member/member-form/validation.js"></script>
<script src="/resources/js/member/member-form/submit.js"></script>
<script src="/resources/js/member/member-form/form-event.js"></script>
<script src="/resources/js/member/member-form/member-form.js"></script>
</head>
<body
	style="height: 100%; margin: 0; display: flex; flex-direction: column;">
	<%
		//@include file="/WEB-INF/views/common/header.jsp"
	%>
	<div style="flex: 1; display: flex; overflow: hidden;">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="d-flex justify-content-center" style="flex: 1; padding: 1rem; overflow: auto; margin: 50px 0 100px 0">
			<div class="outer-box" style="padding: 0px; width: 600px">
				<div class="page-title" style="padding: 20px">계정 보기</div>
				<form action="/member.do?command=insert-member" method="POST" class="form-box" id="sign-up-form">
					<div class="input-box">
						<label for="">프로필 사진</label>
						<div class="profile-image-box">
							<img id="profile-image" src="${member.profile_img}" data-filename="default-profile-image.png"> 
								<input id="profile-image-file-select" type="file" accept=".jpg,.png,.jpeg,.gif,.webp,.bmp">
						</div>
					</div>
					<div class="input-box">
						<label for="">이메일 <label style="font-size: 12px">*
								수정 가능</label>
						</label>
						<div class="horizontal-input-set">
							<span class="form-control form-input">${empty member.email ? 'NULL' : member.email}</span>
						</div>
					</div>
					<div class="input-box">
						<label for="">비밀번호<label style="font-size: 12px">*
								수정 가능</label></label> <span class="form-control form-input">${empty member.password ? 'NULL' : member.password}</span>

					</div>
					<div class="input-box">
						<label for="">이름</label> <span class="form-control form-input">${empty member.member_name ? 'NULL' : member.member_name}</span>

					</div>
					<div class="input-box">
						<label for="">전화번호<label style="font-size: 12px">*
								수정 가능</label></label> <span class="form-control form-input">${empty member.tel ? 'NULL' : member.tel}</span>

					</div>
					<div class="input-box">
						<label for="">주소<label style="font-size: 12px">*
								수정 가능</label></label> <span class="form-control form-input">${empty member.address ? 'NULL' : member.address}</span>

					</div>
					<div class="input-box">
						<label for="">생년월일<label style="font-size: 12px">*
								수정 가능</label></label> <span class="form-control form-input">${empty member.birth_date ? 'NULL' : member.birth_date}</span>
					</div>
					<div class="input-box">
						<label>성별<label style="font-size: 12px">* 수정 가능</label></label> <span
							class="form-control form-input">${empty member.gender ? 'NULL' : member.gender}</span>

					</div>
					<div class="input-box" id="role-input-box">
						<label>역할<label style="font-size: 12px">* 수정 가능</label></label> <span
							class="form-control form-input">${empty member.role ? 'NULL' : member.role}</span>

					</div>
					<div class="input-box" id="company-input-box">
						<label for="">회사<label style="font-size: 12px">*
								수정 가능</label></label> <span class="form-control form-input">${empty member.company ? "NULL" : member.company}</span>

					</div>
					<div class="input-box">
						<label for="">비밀번호 찾기 질문: 고향이 어디세요<label
							style="font-size: 12px">* 수정 가능</label></label> <span
							class="form-control form-input">${empty member.answer ? 'NULL' : member.answer}</span>

					</div>
					<button style="" type="button" id="submit-button">수정하기</button>
					<%@include file="/WEB-INF/views/common/footer.jsp"%>
				</form>
			</div>

		</div>
	</div>

</body>
</html>