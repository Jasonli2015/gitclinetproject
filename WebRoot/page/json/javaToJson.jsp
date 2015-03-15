<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>JsonByJsJq Test</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		function arrayData(){	
			$.ajax({			
	             type: "GET",
	             url: "JsonServlet",
	             contentType: "application/x-www-form-urlencoded",
	             dataType: "json",
	             traditional: true,
	             /*data: {
	             	array: jsonArray	
	             }, */            	             
	             success: function(data){
	            	/*List集合转换成json方法*/	            	
	         		//alert("List集合转换成JSONArray方法: "+data[0]);	            	
	            	//alert("List集合转换成JSONObject方法: "+data.first);	            
	         		
	         		/*数组转换成json代码*/
	         		//alert("数组转换成json代码: "+data[0]);
	         		
	         		/*一般数据转换成json代码*/
	         		//alert("一般数据转换成json代码: "+data[0]);
	         		
	         		/*JavaBean转换成json代码*/
	         		//alert("JavaBean转换成JSONArray代码 JavaBean: "+data[0].name);
	         		//alert("JavaBean转换成JSONArray代码 JavaBean in list:"+data[0].name);
	         		//alert("JavaBean转换成JSONObject代码 JavaBean:"+data.name);
	         		//alert("JavaBean转换成JSONObject代码 JavaBean in list:"+data.employee.name);
	         		
	         		/*Map结合转换成json方法*/
	         		//alert("Map结合转换成JSONArray方法: "+data[0].boolean+" "+data[0].employee.name);
	         		alert("Map结合转换成JSONObject方法: "+data.boolean+" "+data.employee.name);
	             }
         	});
		}
		
		function ajaxShow(){	
			var array1 = new Array();
		    array1.push("ABC");
		    array1.push("abc");
		                
		    var array2 = new Array();
		    array2.push("123");
		    array2.push("456");
		            
		    var allArray = new Array();
		    allArray.push(array1);
		    allArray.push(array2);

		    //将字符串数组转化为JSON数组
		    var jsonArry = JSON.stringify(allArray);
		    
			$.ajax({           
			    type: "POST",
			    url: "JsonServlet",
			    dataType: "json",
		       	traditional: true,
		       	async: true,  
		       	data: {
		             array: jsonArry
			    },
			    success: function(data){
			    	var $dataForPost = "";
			        for ( var i = 0; i < data.length; i++) {
						$dataForPost = $dataForPost+"<tr><td style='border: 1px thin #000000;'>"+data[i]+"</td></tr>";
					}   
			        $("#showByPost").append($dataForPost);
			    }
		    });
		}
	
	</script>

  </head>
  
  <body>  	
  		<h3>java将各种类型的数据转换为json数据</h3>	
	  	<input type="button" name="btnValue" value="json" onclick="arrayData();" /><br>	
	  	<h3>java处理来自客户端的json类型的数组</h3>  			
		<input type="button" value="show table" onclick="ajaxShow();"/>	
		<div>
			<table>
				<tbody id='showByPost'>
				
				</tbody>
			</table>
		</div>		
  </body>
</html>
