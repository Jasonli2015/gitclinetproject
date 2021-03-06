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
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/testJs.js"></script>
  <script type="text/javascript">		
		function preview(obj){
			var template = $("#template").val();
			var newWindow;
			$.ajax({		
				type: "POST",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
				dataType: "text",//返回结果类型
				async: false,
				url: "GenerateTemplateServlet?template="+template,
				success: function(data) {
					//打开新窗口并把返回内容写入到新窗口中
					newWindow = window.open("target","_blank");
					newWindow.document.write(data); 
				    newWindow.document.close();
				},
				error: function(data) {	
					alert(data);
				}
		    });
		}
	</script>
  <body>	
  	<h3>截取字符串</h3>
  	<input type="text" id="checkData" onchange="lengthCheck(8);"/> 
  	<h3>非空校验</h3>
	<input type="text" id="countValue" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" /><br> 
	<input type="button" value="Save" onclick="save();" />  
	<br>
	<h3>根据下拉框选中的值显示不同的数据</h3>
	<select id="select" onchange="selectChange();">
		<option>A</option>
		<option>B</option>
		<option>C</option>
	</select>
	<select id="select2"></select>
	<h3>读取并显示html模板</h3>
	<input type="file" id="template" /><input type="button" value="preview" onclick="preview(this);" />
  </body> 
</html>
