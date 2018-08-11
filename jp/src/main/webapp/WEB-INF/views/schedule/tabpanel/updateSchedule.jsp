<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<h4>Update Schdule</h4>
<form id="frm-update-schedule" class="needs-validation" novalidate>
		<div class="form-group">
			<label for="update-schedule-title" class="col-form-label">Title</label> <input
				type="text" class="form-control is-invalid" id="update-schedule-title"
				name="update_schedule_title" placeholder=${data.TITLE } required="required">
			<div class="valid-feedback">체크완료</div>
			<div class="invalid-feedback">invalid</div>
		</div>
		<div class="form-group">
			<label for="update-schedule-content" class="col-form-label">Content</label>
			<textarea rows="2" class="form-control is-invalid"
				id="update-schedule-content" name="update_schedule_content"
				placeholder="${data.CONTENT }"></textarea>
			<div class="valid-feedback">체크완료</div>
			<div class="invalid-feedback">invalid</div>
		</div>
		<div class="form-group">
			<label for="cbo-category" class="col-form-label" >Options</label>
			<input type="text" class="form-control is-invalid" id="update-schedule-category"
				name="update_schedule_title" placeholder=${data.CATEGORY } required="required">
			<select class="form-control is-invalid" id="cbo-category" name="cbo_category">
				<c:forEach items="${list}" var="list">
					<option value="${list.CTGR_NO}">${list.CTGR_NM}</option>
				</c:forEach>
			</select>
			<div class="valid-feedback">체크완료</div>
			<div class="invalid-feedback">invalid</div>
		</div>
		<div class="form-group">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value=""
					id="chk-all-day"> <label class="form-check-label"
					for="chk-all-day">All day</label>
			</div>
		</div>
		<div class="form-row">
			<div class="col-md-6 mb-4">
				<label for="start-date">Start date</label> <input type="date"
					class="form-control" id="txt-start-date" name="start_date"
					placeholder="${data.START_DT }" required>
				<div class="valid-feedback">Looks good!</div>
			</div>
			<div class="col-md-6 mb-4">
				<label for="start-time">Start time</label> <input type="time"
					class="form-control" id="txt-start-time" name="start_time"
					placeholder="Start time" required>
				<div class="valid-feedback">Looks good!</div>
			</div>
		</div>
		<div class="form-row">
			<div class="col-md-6 mb-4">
				<label for="end-date">End date</label> <input type="date"
					class="form-control" id="txt-end-date" name="end_date"
					placeholder="${data.END_DT }" required>
				<div class="valid-feedback">Looks good!</div>
			</div>
			<div class="col-md-6 mb-4">
				<label for="end-time">End time</label> <input type="time"
					class="form-control" id="txt-end-time" name="end_time"
					placeholder="End time" required>
				<div class="valid-feedback">Looks good!</div>
			</div>
		</div>
		<div class="text-right">
			<button type="button" id="btn-schedule-list"
				class="btn btn-outline-success">목록</button>
			<button type="button" id="btn-update-schedule"
				class="btn btn-outline-success">수정</button>
			<button type="button" id="btn-schedule-delete"
				class="btn btn-outline-danger">삭제</button>
		</div>
	</form>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />