/********************************************************************************
 * @Desc    JavaScript Package
 * @Author  KimYong
 * @Date    2017.08.24	최초작성               Kim Yong
 * @Update	2017.09.07	ExtJs참조 Util구현     Kim Yong
 * @Version 1.0
 ********************************************************************************/
var Exp = Exp || {};

(function(){
	'use strict';
	
	var enumerables = [
		'valueOf',
		'toLocaleString',
		'toString',
		'constructor'
	];
	/**
	 * 현재 Timestamp를 반환합니다.
	 * @member 	Exp
	 * @method 	now
	 * @example var d = Exp.now();
	 * @return 	{Number} Milliseconds를 반환
	 */
	Exp.now = Date.now || (Date.now = function() {
        return +new Date();
    });
	/**
	 * config의 모든 속성을 지정된 object에 복사하여 반환합니다.
	 * 원본 객체를 참조하지 않고 재귀적 병합 및 복제를 수행합니다.
	 * 배열이 필요하다면 Exp.Object.merge를 사용해야 합니다.
	 * @member 	Exp
	 * @method 	apply
	 * @param 	{Object} 복사대상이 될 object
	 * @param 	{Object} 복사할 object
	 * @param 	{Object} 기본값 object
	 * @example var obj;
	 * 			Exp.apply(obj, {a:1}, {a:2});
	 * 			Exp.apply(obj, {a:1});
	 * 			Exp.apply(obj, {}, {a:2});
	 * @return 	{Object}
	 */
	Exp.apply = function(object, config, defaults) {
        if (object) {
            if (defaults) {
            	Exp.apply(object, defaults);
            }
            if (config && typeof config === 'object') {
                var i, j, k;
                for (i in config) {
                    object[i] = config[i];
                }
                if (enumerables) {
                    for (j = enumerables.length; j--;) {
                        k = enumerables[j];
                        if (config.hasOwnProperty(k)) {
                            object[k] = config[k];
                        }
                    }
                }
            }
        }
        return object;
    };
    
    Exp.apply(Exp, {
    	/**
    	 * 전달 된 값이 배열티입이면 true를 반환, 그렇지 않으면 false를 반환합니다.
    	 * @member 	Exp
    	 * @method	isArray
    	 * @method 	isArray	체크할 값
    	 * @param 	{Number|String|Object|Array}
    	 * @return 	{Boolean}
    	 */
    	isArray: ('isArray' in Array) ? Array.isArray : function(value) {
            return toString.call(value) === '[object Array]';
        },
        /**
         * 전달 된 값이 비어 있으면 true를 반환하고 그렇지 않으면 false를 반환합니다.
         * 값이 다음 중 하나 인 경우 값은 비어있는 것으로 간주됩니다.
         * - null
         * - undefined
         * - 길이가 0 인 배열
         * - 길이가 0 인 문자열 ('allowEmptyString' 매개 변수가'true'로 설정되지 않은 경우)
         * @member	Exp
         * @method	isEmpty
         * @param	{Object}	체크할 값
         * @param	{Boolean}	false | 빈 문자열 허용여부 허용하는 경우 true로 설정
         * @example	Exp.isEmpty(value[,true]);
         * @return	{Boolean}
         */
    	isEmpty: function(value, allowEmptyString) {
	        return (value == null) || (!allowEmptyString ? value === '' : false) || (Exp.isArray(value) && value.length === 0);
	    },
	    /**
	     * @private
	     */
	    getElementById : function(id) {
	    	return document.getElementById(id);
	    }
    });
    
    /**
     * DOMScript의 document.createElement와 setAttribute를 하나의 메소드로 구현
     * @param {String} 생성될 태그 문자열 
     * @param {Object} 생성된 태그의 속성
     * @param {Element} 생성할 태그가 삽입될 element
     * @return {Element} 
     */
    Exp.createElement = function(elementTag, attributeData, appendElement) {
    	/**
    	 * element 생성하는 부분
    	 */
    	var elementObject = document.createElement(elementTag);
    	
    	/**
    	 * attribute를 추가하는 부분 
    	 */
    	for (var key in attributeData) {
    		elementObject.setAttribute(key, attributeData[key]);
    	}
    	
    	/**
    	 * element를 append하는 부분
    	 */
    	if(appendElement) {
    		appendElement.appendChild(elementObject);
    	}
    	
    	return elementObject;
    }
    
    Exp.trans = function(options, alert) {
    	if (Exp.isEmpty(options) || Exp.isEmpty(options.url)) {
			return false;
		}
    	var type = options.type || 'get',
    		data = (type=='get') ? options.data : JSON.stringify(options.data);    			
    	$.ajax({
    		url : options.url,
    		type : type,
    		data : data,
    		async : options.async || false,
    		dataType : 'json',
    		contentType : 'application/json; charset=UTF-8',
    		beforeSend : function(xhr, settings) {
    			console.log('ajax beforeSend');
    			/* show spindle */
                xhr.setRequestHeader("Accept", "application/json; charset=UTF-8");
                xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
                xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
                xhr.setRequestHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    		},
    		complete : function(xhr, status) {
    			console.log('ajax complete');
    			/* hidden spindle */
    		}
    	}).done(options.success).fail(options.error);
    };
}());

/**
 * JavaScript StringBuffer 객체의 구현 (rendering 성능개선) 
 * @example
 * var sb = new StringBuffer();
 * sb.append("Hello ");
 * sb.append("Java ");
 * sb.append("script!");
 * document.write(sb.toString());
 */
function StringBuffer() {
    this.buffer = [];
}
 
StringBuffer.prototype.append = function(string) {
    this.buffer.push(string);
    return this;
}
 
StringBuffer.prototype.toString = function() { 
    return this.buffer.join("");
}