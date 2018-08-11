<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	
	<!-- /contatiner -->
	<jsp:include page="/WEB-INF/views/member/login.jsp"></jsp:include>
	<!-- plugin -->
	<c:if test="${fn:length(module) > 0 }">
		<c:forEach items="${module }" var="module" >
			<c:if test="${module eq 'calendar' }">
				<script src="/js/plugin/moment.min.js"></script>
				<script src="/js/plugin/fullcalendar/fullcalendar.min.js"></script>
			</c:if>
			<c:if test="${module eq 'schedule' }">
				<script src="js/control/schedule.js"></script>
			</c:if>
		</c:forEach>
	</c:if>
	<!-- js -->
	<script src="/js/plugin/jquery.form.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.26.10/dist/sweetalert2.all.min.js"></script>
	<script src="/js/expansion.js"></script>
	<script src="/js/common.js"></script>
	<c:if test="${fn:length(controlJs) > 0 }">
		<c:forEach items="${controlJs }" var="jsFiles">
			<script src="/js/control/${jsFiles }.js"></script>
		</c:forEach>
	</c:if>
	<c:if test="${logout ne null && logout ne '' }">
		<script>
		alert("로그아웃 완료!!");
		</script>
	</c:if>
	</body>
</html>
