<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- loginModal  -->
<div class="modal fade" id="pop-login">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">로그인</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<form id="frm-login" class="needs-validation" novalidate>
					<div class="form-group">
						<label for="userEmail">이메일</label> <input type="email"
							class="form-control" id="userEmail" name="userEmail"
							placeholder="Enter email" required="required">
					</div>
					<div class="form-group">
						<label for="userPassword">비밀번호</label> <input type="password"
							class="form-control" id="userPassword" name="userPassword"
							placeholder="Enter password" required="required">
					</div>
					<div class="checkbox">
						<label> <c:choose>
								<c:when test="${cookie.remember.value eq null }">
									<input type="checkbox" name="remember">
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="remember" checked="checked">
								</c:otherwise>
							</c:choose> Remember me
						</label>
					</div>
					<div class="text-right">
						<button type="button" class="btn btn-outline-primary"
								id="btn-login">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>