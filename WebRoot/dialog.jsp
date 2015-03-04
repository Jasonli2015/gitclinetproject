<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dialog.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
	<style type="text/css">
		*{margin:0;padding:0}
		.demo{margin:20% auto;width:120px;overflow:hidden;}
		.demo a{display:block;height:40px;line-height:40px;background:#159b48;color:#fff;font-size:16px;text-decoration:none;text-align:center;border-radius:5px;}
		.demo a:hover{background:#0d853a;}
		#alert{display:none;width:100%;height:100%;top:0;left:0;position:absolute;z-index:999;}
		.bd{width:100%;height:100%;top:0;left:0;position:absolute;background:rgba(0,0,0,.4);}
		.alert_con{width:300px;background:#f4f4f4;box-shadow:0 0 10px rgba(0,0,0,0.8);overflow:hidden;position:absolute;}
		.alert_con h3{padding-left:15px;height:30px;line-height:30px;background:#dc4236;color:#fff;font-size:16px;font-weight:700;position:relative;}
		#closed{right:10px;top:5px;position:absolute;display:inline-block;*display:inline;zoom:1;width:20px;height:20px;line-height:20px;font-family:"微软雅黑";font-size:14px;text-align:center;cursor:pointer;}
		.con_info{list-style:none;padding:10px;}
		.con_info li{height:30px;line-height:30px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			var w=$("body").outerWidth();
			var h=$("body").outerHeight();
			var Alw=parseInt((w/2)-150);
			var Alh=$(".demo a").offset().top;
		    $(".demo a").bind("click",function(){
		        $("#alert").css("display","block");
		        $(".bd").css("height",h);
		        $(".alert_con").css({"left":Alw,"top":Alh});
		    });
		    $("#closed").bind("click",function(){
		        $("#alert").css("display","none");
		    });
		});
	</script>

  </head>
  
  <body>
    <div class="demo">
    <a href="javascript:void(0);">点击预览效果</a>
	</div>
	<div id="alert">
	    <div class="bd"></div>
	    <div class="alert_con">
	        <h3>弹出层标题<span id="closed">X</span></h3>
	        <ul class="con_info">
	            <li>如果爱，请深爱！</li>
	            <li>如果爱，请深爱！</li>
	            <li>如果爱，请深爱！</li>
	        </ul>
	    </div>
	</div>
  </body>
</html>
