<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>STD</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- plugin -->
<c:if test="${fn:length(module) > 0 }">
	<c:forEach items="${module }" var="module" >
		<c:if test="${module eq 'calendar' }">
			<link rel="stylesheet" href="/js/plugin/fullcalendar/fullcalendar.min.css">
		</c:if>
	</c:forEach>
</c:if>
<!-- Custom styles for this template -->
<link rel="stylesheet" href="/css/customer.css">

</head>
<body>
	<!-- header navibar -->
	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
		<h5 class="my-0 mr-md-auto font-weight-normal">
			<a href="/">Home</a>
		</h5>
		<nav class="my-2 my-md-0 mr-md-3">
			<ul class="nav justify-content-end">
				<li class="nav-item">
					<a class="nav-link active" href="/sample">Sample</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" 
						id="navbarPortfolioLink" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">Portfolio</a>
					<div class="dropdown-menu" aria-labelledby="navbarPortfolioLink">
						<a class="dropdown-item" href="/schedule" id="lnk-schedule">Schedule</a> 
						<a class="dropdown-item" href="/account-book">AccountBook</a> 
						<a class="dropdown-item" href="/commerce">Commerce</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/board">Board</a>
				</li>
				<c:choose>
					<c:when test="${sessionScope.G_USER_NO eq null || sessionScope.G_USER_NO eq '' }">
						<li class="nav-item">
							<a class="nav-link disabled" href="/join">Sign up</a>
						</li>
						<li class="nav-item">
							<a class="nav-link disabled" href="#pop-login" id="btn-login-modal" data-toggle="modal"> Login</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
							<a class="nav-link disabled" href="/member" id="btn-my-page">My Page</a>
						</li>
						<li class="nav-item">
							<a class="nav-link disabled" id="btn-logout" href="#">Logout</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
