
$(document).ready(function() {
	search();
	categorylist();
	allScheduleList();
	/**
	 * category 값 불러오기
	 */
	$(document).on('click', '#tbl-category tbody tr', function() {
		var no = $(this).data('number');
		var title = $(this).find('.category-title').text();
		var color = $(this).find('.category-color-code').text();
		$('#txt-category-no').val(no);
		$('#txt-category-title').val(title);	
		$('#txt-category-color').val(color);
	});
	
	/**
	 * schedule update 페이지 이동
	 */
	$(document).on('click', '#tbl-allshcedule tbody tr', function() {
		var no = $(this).data('number');
		scheduleUpdatePage(no);
	});	
	
});

/**
 * category list 가져오기
 */
categorylist = function() {
	var list = $('#tbl-category').children('tbody');
	var options = {
			url : '/schedule/category',
			data : {},
			success : function(data) {
				list.empty();
				data.data.forEach(function(items, index, array) {
					/**
					 * dom script로 생성된 tag는 재사용이 불가하다.
					 */
					var tr, td = [], span;
					tr = Exp.createElement('tr', {'data-number' : items.CTGR_NO}, list[0]);
					td.push($(Exp.createElement('td', {}, tr)).text(items.CTGR_NO));
					td.push($(Exp.createElement('td', {'class' : 'category-title'}, tr)).text(items.CTGR_NM));
					td.push($(Exp.createElement('td', {'class' : 'category-color-code'}, tr)));
					$(Exp.createElement('span', {
						'class' : 'badge',
						'style' : 'color:#ffffff; background-color:#'+items.CTGR_COLOR_CD + ';'
					}, td[2][0])).text('#' + items.CTGR_COLOR_CD);
					/*var html = [
						'<tr data-number="', items.CTGR_NO ,'">',
						'<td>', items.CTGR_NO, '</td>',
						'<td class="category-title">', items.CTGR_NM, '</td>',	
						'<td class="category-color-code">', 
						'<span class="badge" style="color:#ffffff;background-color:#', items.CTGR_COLOR_CD , '">#',
						items.CTGR_COLOR_CD, 
						'</span>',
						'</td>',
						'</td>',
						'</td>',
						'</tr>'
					].join('');
					list.append(html);*/
				});
			},
			error : function(data) {
				swal({
					icon: 'error',
					title: 'Ajax 통신 오류'
				});
			}
		}
	Exp.trans(options);
};

/**
 * calendar에서 schedule list 가져오기
 */
function search() {
	var date = new Date();
	var options = {
		url : '/schedule/list',
		data : {},
		success : function(data) {
			let ary = [],
				obj = data.data;
			obj.forEach(function(items, index, array){
				let tar = {
		    		id : items.ID,
	    			title : items.TITLE,
	    			start : items.START_DT,
	    			end : items.END_DT,
	    			color : items.COLOR
		    	};
				ary.push(tar);
			});
			console.log('event', ary);
			$('#calendar').fullCalendar({
			    defaultDate: date,
			    editable: true,
			    eventLimit: true, // allow "more" link when too many events
			    eventColor: '#378006',
			    events: ary
			});
		},
		error : function(data) {
			
		}
	};
	Exp.trans(options);
}

/**
 * category 초기화
 */
$('#btn-category-new').on('click', function() {
	var no = $('#txt-category-no');
	var title = $('#txt-category-title');
	var cocode = $('#txt-category-color');
	if (!Exp.isEmpty(no)) {
		no.val('');
	};
	if (!Exp.isEmpty(title)) {
		title.val('');
	};
	if (!Exp.isEmpty(cocode)) {
		cocode.val('');
	}
});

/**
 * category 등록 및 update
 */
$('#btn-category-update').on('click', function() {
	// 1. 유효성 검사
	var form = $('#frm-category')[0];
	var no = $('#txt-category-no').val();
	if (no === '99') {
		swal({
			title: '수정할 수 없는 category입니다.'
		});
		return false;
	}
	if (!form.checkValidity()) {
		form.classList.add('was-validated');
		e.preventDefault();
		e.stopPropagation();
		return false;
	}
	var options = {
			url : ('schedule/category/').concat(Exp.isEmpty(no) ? '' : '/' + no),
			type : (Exp.isEmpty(no)) ? 'post': 'put',
			data : $(form).serializeObject(),
			success : function(data) {
				if (data.code == '0') {
					swal({
						title: data.message
					}).then((value) => {
						categorylist();
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
 * category delete
 */
$('#btn-category-delete').on('click', function() {
	// 1. 유효성 검사
	var form = $('#frm-category')[0];
	var no = $('#txt-category-no').val();
	if (no === '') {
		swal({
			title: '선택된 값이 없습니다.'
		});
		return false;
	}
	if (no === '99') {
		swal({
			title: '삭제할 수 없는 category입니다.'
		});
		return false;
	}
	if (!form.checkValidity()) {
		form.classList.add('was-validated');
		e.preventDefault();
		e.stopPropagation();
		return false;
	}
	swal({
		title: '삭제하시겠습니까?',
		html: "삭제되면 하위 일정들은 <strong>기본 카테고리</strong>로 이동하게 됩니다.",
		type: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '삭제',
		cancelButtonText : '취소'
	}).then((result) => {
		if (result.value) {
			var options = {
					url : '/schedule/category/' + no,
					type : 'delete',
					data :  $(form).serializeObject(),
					success : function(data) {
						if (data.code == '0') {
							swal({
								title: data.message
							}).then((value) => {
								categorylist();
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
		}
	});
});

/**
 * all day check
 */
$('#chk-all-day').on('click', function() {
	var start = $('#txt-start-time');
	var end = $('#txt-end-time');
	if ($('#chk-all-day').prop('checked')) {
		start.val('00:00');
		start.attr('readOnly', true);
		end.val('23:59');
		end.attr('readOnly', true);
	} else {
		start.val('');
		start.attr('readOnly', false);
		end.val('');
		end.attr('readOnly', false);
	}
});

/**
 * time check
 */
checkTime = function() {
	var frm = $('#frm-schedule').serializeObject();
	var stt = frm.start_date + ' ' + frm.start_time;
	var edt = frm.end_date + ' ' + frm.end_time;
	
	var start = moment(stt, 'YYYY-MM-DD HH:mm').format('YYYYMMDDHHmm');	
	var end = moment(edt, 'YYYY-MM-DD HH:mm').format('YYYYMMDDHHmm');
	
	if (Number(end)-Number(start) >= 0) {
		return true;
	}
	return false;
};


/**
 * schedule 등록
 */
$('#btn-register-schedule').on('click', function(e) {
	// 1. 유효성 검사
	let form = $('#frm-schedule')[0];
	let data = $(form).serializeObject();
	if (!checkTime()) {
		return false;
	}
	if (!form.checkValidity()) {
		form.classList.add('was-validated');
		e.preventDefault();
		e.stopPropagation();
		return false;
	}
	var options = {
			url : '/schedule/schedule',
			type : 'post',
			data : data,
			success : function(data) {
				if (data.code == '0') {
					swal({
						title: data.message
					}).then((value) => {
						search();
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
	};//options
	Exp.trans(options);
});

/**
 * schedule form 초기화
 */
$('#btn-schedule-reset').on('click', function() {
	//$('#frm-schedule').children('.form-group, .form-row').val();
	$('#schedule_title').val('');
	$('#schedule_content').val('');
	$('#invalidCheck').prop('checked', false);
	$('#txt-start-date').val('');
	$('#txt-start-time').val('');
	$('#txt-end-date').val('');
	$('#txt-end-time').val('');
});

/**
 * all schedule.jsp에 schedule list 초기화
 */
allScheduleList = function() {
	var list = $('#tbl-allshcedule').children('tbody');
	var options = {
			url : '/schedule/list',
			data : {},
			success : function(data) {
				list.empty();
				data.data.forEach(function(items, index, array) {
					/**
					 * dom script로 생성된 tag는 재사용이 불가하다.
					 */
					var tr, td = [];
					tr = Exp.createElement('tr', {'data-number' : items.ID}, list[0]);
					td.push($(Exp.createElement('td', {}, tr)).text(items.ID));
					td.push($(Exp.createElement('td', {'class' : 'schedule-title'}, tr)).text(items.TITLE));
					td.push($(Exp.createElement('td', {}, tr)).text(items.START_DT));
					td.push($(Exp.createElement('td', {}, tr)).text(items.END_DT));					
				});
			},
			error : function(data) {
				swal({
					icon: 'error',
					title: 'Ajax 통신 오류'
				});
			}
		}
	Exp.trans(options);
};

/**
 * schedule update page 가져오기
 */
scheduleUpdatePage = function(no) {
	if (Exp.isEmpty(no)) {
		return false;
	}
	var options = {
			url : '/schedule/list/' + no,
			type : 'get',
			data : {},
			success : function(data) {
				if (data.code == '0') {
					swal({
						title: data.message
					}).then((value) => {
						categorylist();
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
	};//options
	Exp.trans(options);
};
	