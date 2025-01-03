<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal fade" id="warning-modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">경고</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p id="modal-text">경고 입력 칸</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- 
모달 사용하려면

1. 본문 jsp에 추가
<%-- <%@include file="/WEB-INF/views/common/modal.jsp"%> --%>

2. js로 버튼에 이벤트 추가해주시면 됩니다
const warningModal = bootstrap.Modal.getOrCreateInstance($('#warning-modal'));
$("#modal-text").text("경고 메시지 입력");
warningModal.show();
 -->