<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 offset-md-1 col-md-2">
			<div class="list-group" id="list-tab" role="tablist">
				<a class="list-group-item list-group-item-action active"
					id="list-calendar-list" data-toggle="list" href="#list-calendar"
					role="tab" aria-controls="calendar">달력</a>
				<a class="list-group-item list-group-item-action"
					id="list-allschedule-list" data-toggle="list" href="#list-all-schedule"
					role="tab" aria-controls="allschedule">전체일정</a>
				<a class="list-group-item list-group-item-action" 
					id="list-today-list" data-toggle="list" href="#list-today">오늘일정</a>
				<a class="list-group-item list-group-item-action"
					id="list-messages-list" data-toggle="list" href="#list-add-schedule" 
					role="tab" aria-controls="schedule">일정추가</a>
				<a class="list-group-item list-group-item-action"
					id="list-settings-list" data-toggle="list" href="#list-category"
					role="tab" aria-controls="category">카테고리</a>
			</div>
		</div>
		<div class="col-sm-9 col-md-8">
			<div class="tab-content" id="nav-tabContent">
				<jsp:include page="tabpanel/calendar.jsp"/>
				<jsp:include page="tabpanel/allschedule.jsp"/>
				<jsp:include page="tabpanel/today.jsp"/>
				<jsp:include page="tabpanel/schedule.jsp"/>
				<jsp:include page="tabpanel/category.jsp"/>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />