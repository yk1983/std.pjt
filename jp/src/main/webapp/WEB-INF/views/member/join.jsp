<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="container">
	<div class="card">
		<div class="card-header">
			<strong>회원가입</strong>
		</div>
		<div class="card-body">
			<form id="frm-join" class="needs-validation" novalidate="novalidate">
				<div class="form-group">
					<label for="txt-user-name">이름</label> <input type="text"
						class="form-control" id="txt-user-name" name="user_name"
						placeholder="Enter name" required="required">
					<div class="valid-feedback">체크완료</div>
					<div class="invalid-feedback">invalid</div>
				</div>
				<div class="form-group">
					<label for="txt-user-email">이메일</label> <input type="email"
						class="form-control" id="txt-user-email" name="user_email"
						placeholder="Enter email" required="required">
						<div class="valid-feedback">체크완료</div>
						<div class="invalid-feedback">invalid</div>
				</div>
				<div class="form-group">
					<label for="txt-user-passwd">비밀번호</label> <input type="password"
						class="form-control" id="txt-user-passwd" name="user_passwd"
						placeholder="Enter password" required="required">
						<div class="valid-feedback">체크완료</div>
						<div class="invalid-feedback">invalid</div>
				</div>
				<div class="form-group">
					<label for="txt-cfm-passwd">비밀번호 확인</label> <input type="password"
						class="form-control" id="txt-cfm-passwd" name="cfm_passwd"
						placeholder="Enter password" required="required">
						<div class="valid-feedback">체크완료</div>
						<div class="invalid-feedback">invalid</div>
				</div>
				<div class="text-right">
					<button type="button" id="btn-join" class="btn btn-success">가입</button>
					<button type="reset" class="btn btn-info">초기화</button>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
