/** 
 * 检查字符串长度 
 */
/**start**/     
 function lengthCheck(len){
	 var str = $("#checkData").val();
	 str = cutData(str,len);
	 $("#checkData").val(str);
} 
 /** 
  * js截取字符串，中英文都能用 
  * @param str：需要截取的字符串 
  * @param len: 需要截取的长度 
  */
function cutData(str,len){	
	var str_len = str.length;
	if (str_len > len) {             		 	    
		var str_length = 0;		
		str_cut = new String();
	    for (var j = 0; j < str_len; j++) {
	        a = str.charAt(j);
	        str_length++;
	        if (escape(a).length > 4) {
	        	// 中文字符的长度经编码之后大于4
	            str_length++;
	        }
	        str_cut = str_cut.concat(a);
	        if (str_length >= len) {
	            str_cut = str_cut.concat("...");
	            return str_cut;
	        }
	    }            		 	    
 	} else {
 		// 如果给定字符串小于指定长度，则返回源字符串；
 		return str;
 	}
}
/** end* */

/**
 * 添加、删除校验信息
 */
/** start* */
function save(){
	var resourceData = {
   		countValue : $("#countValue").val()
   	};
   	removeAllErrorMessage(resourceData);
	var count = $("#countValue").val();
	var message = "can not be null";
	if (count==undefined||count=="") {
		addErrorMessage("countValue",message);
	}
}    
//add error message
function addErrorMessage(fieldId, message){	
	var $inputId = $("#" + fieldId);
	$inputId.after("<label for='" + fieldId + "Error' style='color: #cc0000;'>" + message + "</label>");
}
//remove error message
function removeAllErrorMessage(resourceData){	
	for(var i in resourceData){
		$("#" + i + "+ label").remove();
	}	
}  
/**end**/

/**
* 根据下拉框选中的值显示不同的数据
*/
/**start**/
function selectChange(){
	
	$("#select2").empty();
	
	if ($("#select").val()=="A") {
		var $option = "<option>Jason</option>";
		$("#select2").append($option);
	} else if ($("#select").val()=="B") {
		var $option = "<option>lxw</option>";
		$("#select2").append($option);
	} else if ($("#select").val()=="C") {
		var $option = "<option>李小伟</option>";
		$("#select2").append($option);
	}		
}    
/**end**/