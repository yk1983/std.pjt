<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
	<h1 class="display-4">PORTFOLIO</h1>
	<p class="lead">Spring MVC를 이용한 웹 스터디 프로젝트 포트폴리오.</p>
</div>
<div class="container">
	<div class="card-deck mb-3 text-center">
		<div class="card mb-4 box-shadow">
			<div class="card-header">
				<h4 class="my-0 font-weight-normal">Schedule</h4>
			</div>
			<div class="card-body">
				<h1 class="card-title pricing-card-title">
					7 <small class="text-muted">/ 14</small>
				</h1>
				<ul class="list-unstyled mt-3 mb-4">
					<li>일정관리</li>
				</ul>
				<button type="button"
					class="btn btn-lg btn-block btn-outline-primary">Get
					started</button>
			</div>
		</div>
		<div class="card mb-4 box-shadow">
			<div class="card-header">
				<h4 class="my-0 font-weight-normal">AccountBook</h4>
			</div>
			<div class="card-body">
				<h1 class="card-title pricing-card-title">
					8 <small class="text-muted">/ 18</small>
				</h1>
				<ul class="list-unstyled mt-3 mb-4">
					<li>가계부</li>
				</ul>
				<button type="button" class="btn btn-lg btn-block btn-outline-primary">Get
					started</button>
			</div>
		</div>
		<div class="card mb-4 box-shadow">
			<div class="card-header">
				<h4 class="my-0 font-weight-normal">Commerce</h4>
			</div>
			<div class="card-body">
				<h1 class="card-title pricing-card-title">
					9 <small class="text-muted">/ 22</small>
				</h1>
				<ul class="list-unstyled mt-3 mb-4">
					<li>전자 상거래</li>
				</ul>
				<button type="button" class="btn btn-lg btn-block btn-outline-primary">Get
					started</button>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/copyright.jsp" />
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
