<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="java.util.ResourceBundle"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>My test page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <style type="text/css">
  	.tag {  
	    zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */  
	    vertical-align: baseline;  
	    outline: none;  
	    cursor: pointer;  
	    text-align: center;  
	    text-decoration: none;  
	    font-size: 12px;
	    font-family: sans-serif;
	    padding: 5px;
	    margin: 0px 5px 5px 0px; 
	    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);  
	    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);  
	    box-shadow: 0 1px 2px rgba(0,0,0,.2);  
	    width: 100px; 
	    
	    color: #d9eef7;  
	    border: solid 1px #0076a3;  
	    background: #0095cd;  
	    background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));  
	    background: -moz-linear-gradient(top,  #00adee,  #0078a5);  
	    filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee', endColorstr='#0078a5');
	}
  </style>
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript">
  	/*var email = "Jason.XW.Li@pccw.com";
  	var pattern = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
  	if (pattern.test(email)) { 
    	alert("success");
    }else{
    	alert("eror");
    }*/
    
    
    /* $(function(){
    	var arrPrePrice = [];
    	var amount = 0;
    	$(".price").each(function () {
    		arrPrePrice.push($(this).html().replace("$",""));
    	});
    	//arrPrePrice 就是你要的数组
    	for ( var i = 0; i < arrPrePrice.length; i++) {
    		amount += arrPrePrice[i]*1;    		
		}
    	$("#amount").text("$"+amount); 
    	
    });*/
    
	//判断字符串长度
    function WidthCheck(){  
	    var s = $("#data").val();
	    var w = 0;   
	    for (var i=0; i<s.length; i++) {   
	       var c = s.charCodeAt(i);   
	       //单字节加1   
	       if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {   
	        w++;   
	       }   
	       else {   
	        w+=2;   
	       }   
	    }   
	    if (w > 8) {   
	    	alert("超过长度！");
	       	return false;   
	    }   
    	return true;   
    }  
    
    var GetLength = function (str) {
        ///<summary>获得字符串实际长度，中文2，英文1</summary>
        ///<param name="str">要获得长度的字符串</param>
        var realLength = 0, len = str.length, charCode = -1;
        for (var i = 0; i < len; i++) {
            charCode = str.charCodeAt(i);
            if (charCode >= 0 && charCode <= 128) realLength += 1;
            else realLength += 2;
        }
        return realLength;
    };

    /** 
     * js截取字符串，中英文都能用 
     * @param str：需要截取的字符串 
     * @param len: 需要截取的长度 
     */
    function cutstr(data, len) {
        var str_length = 0;
        var str_len = 0;
        str_cut = new String();
        str_len = data.length;
        for (var i = 0; i < str_len; i++) {
            a = data.charAt(i);
            str_length++;
            if (escape(a).length > 4) {
                //中文字符的长度经编码之后大于4  
                str_length++;
            }
            str_cut = str_cut.concat(a);
            if (str_length >= len) {
                str_cut = str_cut.concat("");
                return str_cut;
            }
        }
        //如果给定字符串小于指定长度，则返回源字符串；  
        if (str_length < len) {
            return data;
        }
    }
	
    function lengthCheck(obj,len){
	 	$(obj).bind('keyup', function () {
	 	    if (GetLength($(this).val()) > len) { 
		 	    $(this).val(cutstr($(this).val(),len)); 
		 	    return; 
	 	    } 
	 	});   
    }   
    
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
    
    function removeElement(){
    	$("#RE").next().remove();
    	//$("#RE+ div").remove();
    }    
    
   	function selectChange(){
   		
   		$("#select2").empty();
   		
   		var index = $("option:selected", "#select").index();
   		
   		var data = $("#displayTable tr:nth-child("+index+")").find("td").html();
   		
   		if ($("#select").val()=="A") {
   			var $option = "<option>111</option>";
   			$("#select2").append($option);
		}   		
   	}    
    
  </script>
  <script type="text/javascript" src="js/testJs.js"></script>
  <body>
    总金额：<label id='amount'></label> <br>    
	<%--<table>
		<tr>
			<td class='price'>$100</td>
			<td class='price'>$200</td>
			<td class='price'>$300</td>
		</tr>
	</table>--%>
	<input type="text" id="data" onmouseout="lengthCheck(this,3);"/>
	<input type="text" id="username" onmouseout="lengthCheck(this,8);"/><br> 	
	<input type="text" id="RE" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" />
	<div>123abc</div><br>
	<input type="text" id="countValue" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" /><br> 
	<input type="button" value="Save" onclick="save();" />  
	<input type="button" value="Remove" onclick="removeElement();" />
	<br>
	<select id="select" onchange="selectChange();">
		<option>A</option>
		<option>B</option>
		<option>C</option>
	</select>
	<select id="select2">
	</select>
	<table id="displayTable" style="display: none !important;">
		<tr><td>123</td></tr>
		<tr><td>456</td></tr>
		<tr><td>789</td></tr>
	</table>
  </body> 
</html>
