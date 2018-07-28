/**
 * 회원가입
 */
$('#btn-join').on('click', function(e) {
	// 1. 유효성검사
	if (!checkpasswd()) {
		swal({
			title: '비밀번호 중복체크 오류'
		});
		return false;
	}
	let form = $('#frm-join')[0];
	let data = $(form).serializeObject();
	if (!form.checkValidity()) {
		form.classList.add('was-validated');
		e.preventDefault();
		e.stopPropagation();
		return false;
	}
	var options = {
		url : '/join',
		type : 'post',
		data : data,
		success : function(data) {
			if (data.code == '0') {
				swal({
					title: data.message
				}).then((value) => {
					location.href = '/';
				});
			} else if (data.code == '-1') {
				swal({
					title: data.message
				});
				return false;
			}
		},
		error : function(data) {
			swal({
				icon: 'error',
				title: 'Ajax 통신 오류'
			});
		}
	};
	Exp.trans(options);
});

/**
 * 비밀번호 중복체크
 */
checkpasswd = function() {
	var passwd1 = $('#txt-user-passwd').val();
	var passwd2 = $('#txt-cfm-passwd').val();
	if (passwd1 == passwd2) {
		return true;
	}
	return false;
}