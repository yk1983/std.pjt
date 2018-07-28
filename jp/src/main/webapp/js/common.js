/**
 * 
 */
(function($) {
	var validations ={
	    email: [/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/, '올바른 이메일 양식으로 작성하세요.']
	};
	/**
	 * javascript form의 input type
	 * 'hidden, checkbox, radio'는 reset button으로 초기화가 되지 않으므로
	 * reset 메소드를 제공한다.
	 */
	$.fn.clearForm = function() {
		return this.each(function() {
			var type = this.type;
			var tag = this.tagName.toLowerCase();
			if (tag === 'form') {
				return $(':input', this).clearForm();
			}
			if (type === 'email'
				|| type === 'date' || type === 'time'
				|| type === 'text' || type === 'hidden'
				|| type === 'password' || tag === 'textarea') {
				this.value = '';
			} else if (type === 'checkbox' || type === 'radio') {
				this.checked = false;
			} else if (tag === 'select') {
				this.selectedIdx = -1;
			}
		});
	}
	
	
	/**
	 * form data 직렬화(object)
	 */
	$.fn.serializeObject = function() {
		"use strict";
		
		var result = {};
		var extend = function(i, element) {
			var node = result[element.name];

			if ('undefined' !== typeof node && node !== null) {
				if ($.isArray(node)) {
					node.push(element.value);
				} else {
					result[element.name] = [ node, element.value ];
				}
			} else {
				result[element.name] = element.value;
			}
		};

		$.each(this.serializeArray(), extend);
		return result;
	};
	
	$.fn.center = function() {
		this.css('position', 'relative');
		this.css('top', Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + $(window).scrollTop()) + 'px');
		//this.css('left', Math.max(0, (($('.container').width() - $(this).outerWidth()) / 2) + $('.container').scrollLeft()) + 'px');
		return this;
	};
	
	/**
	 * bootstrap4 form validation
	 */
	window.addEventListener('load', function() {
		// Fetch all the forms we want to apply custom Bootstrap validation styles to
		var forms = $('.needs-validation');
		// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
			form.addEventListener('submit', function(event) {
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
				}
				form.classList.add('was-validated');
			}, false);
		});
	}, false);
	
	/**
	 * user login
	 */
	$('#btn-login').on('click', function() {
		var options = {
			url : '/login',
			type : 'post',
			data : $('#frm-login').serializeObject(),
			success : function(data) {
				if (data.code == '0') {
					swal({
						icon: 'success',
						title: data.message
					}).then((value) => {
						location.href='/';
					});
				} else if (data.code == '-1'){
					// code가 0 이 아니면 모두 에러...
					swal({
						icon: 'error',
						title: data.message
					});
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
	
	$('#btn-logout').on('click', function() {
		var options = {
			url: '/logout',
			success: function(data) {
				if (data.code === '0') {
					swal({
						icon: 'success',
						title: data.message
					}).then((value) => {
						location.href='/';
					});
				}
			},
			error: function() {}
		};
		Exp.trans(options);
	});
	
	// On every :input focusout validate if empty
    $(':input').blur(function(){
    	let fieldType = this.type;
    	switch(fieldType){
    		case 'text': 
    		case 'password':
                validateText($(this));
                break;
    		case 'email':
                validateEmail($(this));
                break;
    		case 'checkbox':
    			validateCheckBox($(this));
    			break;
    		case 'select-one':
    			validateSelectOne($(this));
    			break;
    		case 'select-multiple':
    			validateSelectMultiple($(this));
    			break;
    		default:
	    		break;
    	}
	});
    
    // Check all the input fields of type email. This function will handle all the email addresses validations
    $("input[type=email]").change( function(){
        // Set the regular expression to validate the email 
        validation = new RegExp(validations['email'][0]);
        // validate the email value against the regular expression
        if (!validation.test(this.value)){
            // If the validation fails then we show the custom error message
            this.setCustomValidity(validations['email'][1]);
            return false;
        } else {
            // This is really important. If the validation is successful you need to reset the custom error message
            this.setCustomValidity('');
        }
    });
    
	// On every :input focusin remove existing validation messages if any
    $(':input').click(function(){
    	$(this).removeClass('is-valid is-invalid');
	});
    
	// Reset form and remove validation messages
    $(':reset').click(function(){
    	$(':input, :checked').removeClass('is-valid is-invalid');
    	$(this).closest('form').removeClass('was-validated');
    });
    
    // On every :input focusin remove existing validation messages if any
    $(':input').keydown(function(){
        $(this).removeClass('is-valid is-invalid');
    });
})(jQuery);

//Validate Text and password
function validateText(thisObj) {
    let fieldValue = thisObj.val();
    if(fieldValue.length > 1) {
        thisObj.addClass('is-valid');
    } else {
        thisObj.addClass('is-invalid').siblings('.invalid-feedback').text(thisObj[0].validationMessage);
    }
}

// Validate Email
function validateEmail(thisObj) {
    let fieldValue = thisObj.val();
    let pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
    if(pattern.test(fieldValue)) {
        thisObj.addClass('is-valid');
    } else {
        thisObj.addClass('is-invalid').siblings('.invalid-feedback').text(thisObj[0].validationMessage);
    }
}

// Validate CheckBox
function validateCheckBox(thisObj) {
    if($(':checkbox:checked').length > 0) {
        thisObj.addClass('is-valid');
    } else {
        thisObj.addClass('is-invalid').siblings('.invalid-feedback').text(thisObj[0].validationMessage);
    }
}

// Validate Select One Tag
function validateSelectOne(thisObj) {
    let fieldValue = thisObj.val();
    if(fieldValue != null) {
        thisObj.addClass('is-valid');
    } else {
        thisObj.addClass('is-invalid').siblings('.invalid-feedback').text(thisObj[0].validationMessage);
    }
}

// Validate Select Multiple Tag
function validateSelectMultiple(thisObj) {
    let fieldValue = thisObj.val();
    if(fieldValue.length > 0) {
        thisObj.addClass('is-valid');
    } else {
        thisObj.addClass('is-invalid').siblings('.invalid-feedback').text(thisObj[0].validationMessage);
    }
}