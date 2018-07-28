<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="container">
	<div class="card-deck">
		<div class="card">
			<div class="item">
			</div>
			<div class="border-effect bg-primary"></div>
			<div class="card-body">
				<h4 class="card-title">Modify User Info</h4>
				<p class="card-text">This is a longer card with supporting text
					below as a natural lead-in to additional content. This content is a
					little bit longer.</p>
			</div>
			<div class="card-footer bg-transparent text-right">
			<p class="card-text">
					<small class="text-muted"><a href="/member/passwd">Modify</a></small>
				</p>
			</div>
		</div>
		<div class="card">
			<div class="item">
			</div>
			<div class="border-effect bg-primary"></div>
			<div class="card-body">
				<h4 class="card-title">Modify Password</h4>
				<p class="card-text">This card has supporting text below as a
					natural lead-in to additional content.</p>
			</div>
			<div class="card-footer bg-transparent text-right">
			<p class="card-text">
					<small class="text-muted"><a href="/member/passwd">Modify</a></small>
				</p>
			</div>
		</div>
		<div class="card">
			<div class="item">
			</div>
			<div class="border-effect bg-primary"></div>
			<div class="card-body">
				<h4 class="card-title">Withdrawal</h4>
				<p class="card-text">This is a wider card with supporting text
					below as a natural lead-in to additional content. This card has
					even longer content than the first to show that equal height
					action.</p>
			</div>
			<div class="card-footer bg-transparent text-right">
			<p class="card-text">
					<small class="text-muted"><a href="/member/delete">Withdrawal</a></small>
				</p>
			</div>
		</div>
	</div>
	<!-- div class="col-md-6 offset-md-3 col-sm-8 offset-sm-2 mb-4">
		<div class="card border-success">
			<div class="card-header bg-transparent border-success text-success">
				Modify name</div>
			<div class="card-body">
				<h5 class="card-title">You can modify the name.</h5>
			</div>
			<div class="card-footer bg-transparent border-success">
				<a href="/member/name" class="btn btn-success">Modify</a>
			</div>
		</div>
	</div>
	<div class="col-md-6 offset-md-3 col-sm-8 offset-sm-2 mb-4">
		<div class="card border-primary">
			<div class="card-header bg-transparent border-primary text-primary">
				Modify Password</div>
			<div class="card-body">
				<h5 class="card-title">You can modify the password.</h5>
			</div>
			<div class="card-footer bg-transparent border-primary">
				<a href="/member/passwd" class="btn btn-primary">Modify</a>
			</div>
		</div>
	</div>
	<div class="col-md-6 offset-md-3 col-sm-8 offset-sm-2 mb-4">
		<div class="card border-danger">
			<div class="card-header bg-transparent border-danger text-danger">
				Withdrawal</div>
			<div class="card-body">
				<h5 class="card-title">Membership Withdrawal</h5>
			</div>
			<div class="card-footer bg-transparent border-danger">
				<a href="/member/delete" class="btn btn-danger">Withdrawal</a>
			</div>
		</div>
	</div-->
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
