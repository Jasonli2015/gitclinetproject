<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>读取模板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
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

  </head>
  
  <body>
    <input type="text" id="template" /><input type="button" value="preview" onclick="preview(this);" />
  </body>
</html>
