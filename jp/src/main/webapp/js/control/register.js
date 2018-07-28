/**
 * 회원가입 처리
 */
registerMember = function() {
	var frm = $('#registerMemberForm');
	$.ajax({
		url : '/registerMember',
		type : 'post',
		dataType : 'json',
		data : frm.serialize(),
		success : function(data) {
			console.log(data);
			if (data.code == "0") {
				alert(data.message);
				location.href = '/';
			}
		},
		error : function(data) {
			alert('error!');
		}
	});
}
$(function() {
	$('#btn-registerMember').on('click', function() {
		registerMember();
	});
});