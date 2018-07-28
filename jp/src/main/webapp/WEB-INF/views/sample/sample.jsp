<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/include/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">사용자 정보</h3>
					</div>
					<div class="card-body" style="background-color: #fafafa;">
						<form id="frm-sample" class="needs-validation" novalidate>
							<input type="hidden" name="user_no" value="">
							<div class="form-group">
								<label for="validationCustomUsername">이메일</label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroupPrepend">@</span>
									</div>
									<input type="email" class="form-control"
										id="validationCustomUsername" placeholder="Email"
										aria-describedby="inputGroupPrepend" name="user_email" required>
									<div class="invalid-feedback">Please choose a username.</div>
								</div>
							</div>
							<div class="form-group">
								<label for="validationCustom01">이름</label> <input type="text"
									class="form-control" id="validationCustom01"
									placeholder="Name" name="user_name" required>
								<div class="valid-feedback">Looks good!</div>
							</div>
							<div class="form-group">
								<label for="validationCustom02">비밀번호</label> <input type="password"
									class="form-control" id="validationCustom02"
									placeholder="Password" name="user_passwd" required>
								<div class="valid-feedback">Looks good!</div>
							</div>
							<div class="form-group">
								<label for="validationCustom03">등록일자</label> <input type="date"
									class="form-control" id="validationCustom03" placeholder="City"
									name="reg_dtm" disabled="disabled" required>
								<div class="invalid-feedback">Please provide a valid city.</div>
							</div>
							<button class="btn btn-outline-secondary btn-sm" type="button" id="btn-reset">Rest</button>
							<button class="btn btn-outline-secondary btn-sm" type="button" id="btn-save">Save</button>
							<button class="btn btn-outline-secondary btn-sm" type="button" id="btn-delete">Delete</button>
						</form>
					</div>
				</div>
				<!-- .card end -->
			</div>
			<div class="col-md-8">
				<div class="table-responsive-sm">
					<h3>목록</h3>
					<table id="tbl-sample" class="table table-hover">
						<thead>
							<tr>
								<th>번호</th>
								<th>이름</th>
								<th>이메일</th>
								<th>비밀번호</th>
								<th>등록일자</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${data ne null }">
									<c:forEach var="rows" items="${data }">
										<tr data-no="${rows.USER_NO }">
											<td>${rows.USER_NO }</td>
											<td>${rows.USER_NAME }</td>
											<td>${rows.USER_EMAIL }</td>
											<td>${rows.USER_PASSWD }</td>
											<td>${rows.REG_DTM }</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<td colspan="7">검색 결과가 없습니다.</td>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<button class="btn btn-outline-secondary btn-sm" type="button" id="btn-search">Search</button>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<script src="/js/jquery.form.min.js"></script>
	<script src="/js/common.js"></script>
	<script>
		function callbackje() {
			console.log('지은');
		}
		search = function() {
			var options = {
				url : '/sample',
				data : {
					mode : 'search'
				},
				success : function(data) {
					var list = data.data,
						tbody = $('#tbl-sample tbody');
					tbody.empty();
					list.forEach(function(items, index, ary){
						var tr, td;
						tr = Exp.createElement('tr', {'data-no':items.USER_NO}, tbody[0]);
						$(Exp.createElement('td', {}, tr)).text(items.USER_NO);
						$(Exp.createElement('td', {}, tr)).text(items.USER_NAME);
						$(Exp.createElement('td', {}, tr)).text(items.USER_EMAIL);
						$(Exp.createElement('td', {}, tr)).text(items.USER_PASSWD);
						$(Exp.createElement('td', {}, tr)).text(items.REG_DTM);
						/* var el = [
							'<tr data-no="',items.USER_NO,'">',
							'<td>',items.USER_NO,'</td>',
							'<td>',items.USER_NAME,'</td>',
							'<td>',items.USER_EMAIL,'</td>',
							'<td>',items.USER_PASSWD,'</td>',
							'<td>',items.REG_DTM,'</td>',
							'</tr>'
						];
						tbody.append(el.join('')); */
					});
				},
				error : function() {
					
				}
			};
			Exp.trans(options);
		}
		$(function() {
			$('#btn-search').on('click', function() {
				search();
			});
			$(document).on('click', '#tbl-sample tbody td', function(e) {
				var no = $(this).parent().data('no'),
					options = {
						url : '/sample/' + no,
						success : function(data) {
							var frm = $('form'), data = data.data;
							frm.find('input[name=user_no]').val(data.USER_NO);
							frm.find('input[name=user_name]').val(data.USER_NAME);
							frm.find('input[name=user_email]').val(data.USER_EMAIL);
							frm.find('input[name=reg_dtm]').val(data.REG_DTM);
						},
						error : function() {
							alert('오류가 발생하였습니다.');
						}
					};
				Exp.trans(options);
			});
			$('#btn-reset').on('click', function(e) {
				$('#frm-sample').clearForm();
			});
			$('#btn-save').on('click', function(e) {
				var no = $('input[name=user_no]').val();
				var options = {
					url : ('/sample').concat((Exp.isEmpty(no)) ? '' : '/' + no),
					type : (Exp.isEmpty(no)) ? 'post' : 'put',
					data : $('#frm-sample').serializeObject(),
					success : function(data){
						alert(data.errCode + "," + data.errMsg);
						search();
					},
					error : function() {
						alert('오류가 발생하였습니다.');
					}
				};
				Exp.trans(options);
			});
			$('#btn-delete').on('click', function(e) {
				var no = $('input[name=user_no]').val();
				var passwd = $('input[name=user_passwd]').val();
				if (Exp.isEmpty(no)) {
					return false;
				}
				var options = {
					url : '/sample/' + no,
					type : 'delete',
					data : {
						user_passwd : passwd
					},
					success : function(data) {
						alert(data.errCode + "," + data.errMsg);
						search();
					},
					error : function(data) {
						alert("오류가 발생하였습니다.");
					}
				};
				Exp.trans(options);
			});
			/*
			$('table tr td').on('dblclick', function(){
				var frm = $('form');
					no = $(this).parent().data('no');
					
				$.ajax({
					url : frm.attr('action') + '/' + no,
					type : 'get',
					dataType : 'json',
					data : {
						param1: 'test'
					},
					success : function(data) {
						console.log(data.data);
						var frm = $('form'),
							data = data.data;
						frm.find('input[name=user_no]').val(data.USER_NO);
						frm.find('input[name=user_name]').val(data.USER_NAME);
						frm.find('input[name=user_email]').val(data.USER_EMAIL);
						frm.find('input[name=reg_dtm]').val(data.REG_DTM);
					},
					error: function() {
						alert('오류가 발생하였습니다.');
					}
				});
			});
			
			$('#btn-save').on('click', function() {
				var frm = $('#frm-sample');
				frm.validator();
				var no = frm.find('input[name=user_no]').val();
				if (no != '') {
					$.ajax({
						url : frm.attr('action') + '/' + no,
						type : 'put',
						dataType : 'json',
						data : JSON.stringify(frm.serializeObject()),
						headers: {"X-HTTP-Method-Override": "PUT"}, // X-HTTP-Method-Override set to PUT.
						beforeSend: function(xhr) {
							xhr.setRequestHeader("Accept", "application/json");
							xhr.setRequestHeader("Content-Type", "application/json");
						},
						success : function(data) {
							alert(data.errCode + "," + data.errMsg);
						},
						error: function() {
							alert('오류가 발생하였습니다.');
						}
					});
				} else {
					$.ajax({
						url : frm.attr('action'),
						type : 'post',
						dataType : 'json',
						data : frm.serializeObject(),
						success : function(data) {
							alert(data.errCode + "," + data.errMsg);
						},
						error: function() {
							alert('오류가 발생하였습니다.');
						}
					});
				}
			});
			$('#btn-delete').on('click', function(){
				var frm = $('form'),
					no = frm.find('input[name=user_no]').val();
				$.ajax({
					url : frm.attr('action') + '/' + no,
					type : 'delete',
					contentType: 'application/json',
					headers: {"X-HTTP-Method-Override": "DELETE"}, // X-HTTP-Method-Override set to DELETE.
					dataType : 'json',
					data : {},
					beforeSend: function(xhr) {
						xhr.setRequestHeader("Accept", "application/json");
						xhr.setRequestHeader("Content-Type", "application/json");
					},
					success : function(data) {
						alert('삭제되었습니다.');
					},
					error: function() {
						alert('오류가 발생하였습니다.');
					}
				});
			});
			$('#btn-reset').on('click', function(){
				var frm = $('form')
				frm.find('input[name=user_passwd]').removeAttr('disabled');
				frm[0].reset();
			});*/
		});
	</script>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />