<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="container">
	<form id="frm-name" class="needs-validate" novalidate="novalidate">
		<h4>Modify name</h4>
		<input type="hidden" id="txt-user-number" name="user_number"
			value="${sessionScope.G_USER_NO }">
		<div class="form-group">
			<label for="txt-user-email">Email</label> <input type="text"
				id="txt-user-email" name="user_email" class="form-control"
				placeholder="${sessionScope.G_USER_EMAIL}"
				value="${sessionScope.G_USER_EMAIL}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="txt-cur-name">Current name</label> <input type="text"
				id="txt-cur-name" name="cur_name" class="form-control"
				placeholder="${sessionScope.G_USER_NAME}"
				value="${sessionScope.G_USER_NAME}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="txt-new-name">New Name</label> <input type="text"
				id="txt-new-name" name="new_name" class="form-control"
				placeholder="Enter user name" required="required">
			<div class="valid-feedback">체크완료</div>
			<div class="invalid-feedback">invalid</div>
		</div>
		<div class="form-group">
			<label for="txt-user-passwd">Password</label> <input type="password"
				id="txt-user-passwd" name="user_passwd" class="form-control"
				placeholder="Enter password" required="required">
			<div class="valid-feedback">체크완료</div>
			<div class="invalid-feedback">invalid</div>
		</div>
		<div class="text-right">
			<button type="button" id="btn-update-name" class="btn btn-primary">수정</button>
			<button type="button" class="btn btn-secondary btn-member">목록</button>
		</div>
	</form>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />