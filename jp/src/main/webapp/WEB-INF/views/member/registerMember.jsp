<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<div class="container">
		<div class="card">
			<div class="card-header">
				<h3 class="card-title">회원가입</h3>
			</div>
		</div>
		<div class="card-body">
			<form id="registerMemberForm">
				<div class="form-group">
					<label for="registerName">이름</label>
					<input type="text" class="form-control" id="registerName" name="registerName" placeholder="Enter name" required="required">
					<label for="registerEmail">이메일</label>
					<input type="email" class="form-control" id="registerEmail" name="registerEmail" placeholder="Enter email" required="required">
					<label for="registerPassowrd">비밀번호</label>
					<input type="password" class="form-control" id="registerPassword" name="registerPassword" placeholder="Enter password" required="required">
					<label for="confirmPassword">비밀번호 확인</label>
					<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Enter password" required="required">
				</div>
				<button type="button" id="btn-registerMember" class="btn btn-outline-primary">가입</button>
				<button type="button" class="btn btn-outline-danger">취소</button>
			</form>
		</div>
	</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
