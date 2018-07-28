$("document").ready(function(){
	$('.card-deck').center();
	
	$(".card").mouseover (function(){
		var i = $(this).index();                   
		$(".border-effect").eq(i).animate({ width: "100%" }, 200 );
		$(this).find("img").css({
			'-moz-transform':'scale(1.1)',
			'-webkit-transform':'scale(1.1)',
			'transform':'scale(1.1)'
		});
	});

	$(".card").mouseleave(function(){
	  	var i = $(this).index();       
	  	$(".border-effect").eq(i).animate({ width: "20%" }, 200 );
	  	$(this).find("img").css({
		  	'-moz-transform':'scale(1.0)',
		  	'-webkit-transform':'scale(1.0)',
		  	'transform':'scale(1.0)'
	  	});        
  	});
})
/**
 * 이름 수정
 */
$('#btn-update-name').on('click', function(e) {
	// 1. 유효성검사
	let form = $('#frm-name')[0];
	let data = $(form).serializeObject();
	if (!form.checkValidity()) {
		form.classList.add('was-validated');
		e.preventDefault();
		e.stopPropagation();
		return false;
	}
	// 2. Exp.trans object set
	var options = {
			url : '/member/name',
			type : 'put',
			data : data,
			success : function(data) {
				if (data.code == '0') {
					swal({
						title: data.message
					}).then((value) => {
						location.href = '/member';
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
	// 3. 전송
	Exp.trans(options);
});

/**
 * 비밀번호 수정
 */
$('#btn-update-passwd').on('click', function(e) {
	// 1. 유효성검사
	let form = $('#frm-passwd')[0];
	let data = $(form).serializeObject();
	if (!form.checkValidity()) {
		form.classList.add('was-validated');
		e.preventDefault();
		e.stopPropagation();
		return false;
	}
	// 2. Exp.trans object set
	var options = {
			url : '/member/passwd',
			type : 'put',
			data : data,
			success : function(data) {
				if (data.code == '0') {
					swal({
						title: data.message
					}).then((value) => {
						location.href = '/member';
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
	// 3. 전송
	Exp.trans(options);
});

/**
 * mypage로 돌아가기
 */
$('.btn-member').on('click', function() {
	location.href = '/member';
});

/**
 * 회원탈퇴 
 */
$('#btn-delete').on('click', function(e) {
	// 1. 유효성검사
	let form = $('#frm-delete')[0];
	let data = $(form).serializeObject();
	if (!form.checkValidity()) {
		form.classList.add('was-validated');
		e.preventDefault();
		e.stopPropagation();
		return false;
	}
	var options = {
		url : '/member/delete',
		type : 'delete',
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
	// 3. 전송
	Exp.trans(options);
});