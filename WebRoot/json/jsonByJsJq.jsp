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
	
		/*$(function(){
			var array = $("input[name='btnValue']");		
			array.each(function(){
				alert($(this).val());
			});	
		});*/
	
		function arrayData(){	
		
			//构造json对象
			var dataArray="[]";	
			var jsonArray = eval('('+dataArray+')');
			var arr = {
				 "name" : "jason",
				 "value" :"824"
			 	 };
			jsonArray.push(arr);
			//alert(jsonArray);
			/*将字符串数组转为Json数组*/
			//var jsonArray = JSON.stringify(dataArray); 
			
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
	             type: "GET",
	             url: "JsonServlet",
	             contentType: "application/x-www-form-urlencoded",
	             dataType: "json",
	             traditional: true,
	             data: {
	             	array: jsonArray	
	             },             	             
	             success: function(data){
	                alert(data[0].name);
	             }
         	});
		}
		
		function selectData(){
			var accountArray = new Array();
						
			$("[name='selected']:checked").each(function(){
				alert($(this).val());
				accountArray.push($(this).val());            
	        });
			//alert(accountArray);
			
		}
		
		function jsonArray(){
			
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
			    url: "JsonServelet",
			    dataType: "json",
		       	traditional: true,
		      	data: {
		             array: jsonArry
			    },                            
			    success: function(data){
			                             
			    }
		    });
		}
		
		function ajaxShow(){
			var $tableForGet = $("#showByGet");
			var $tableForPost = $("#showByPost");
			var $dataForGet = "";
			var $dataForPost = "";
			$.ajax({           
			    type: "GET",
			    url: "JsonServlet",
			    dataType: "json",
		       	traditional: true,
		       	async: true,                          
			    success: function(data){
			        for ( var i = 0; i < data.length; i++) {
						$dataForGet = $dataForGet+"<tr><td style='width:30px;height:50px;border: 1px thin #000000;'>"+data[i]+"</td></tr>";
					}   
			        $tableForGet.append($dataForGet);
			    }
		    });
			
			$.ajax({           
			    type: "POST",
			    url: "JsonServelet",
			    dataType: "json",
		       	traditional: true,
		       	async: true,                          
			    success: function(data){
			        for ( var i = 0; i < data.length; i++) {
						$dataForPost = $dataForPost+"<tr><td style='border: 1px thin #000000;'>"+data[i]+"</td></tr>";
					}   
			        $tableForPost.append($dataForPost);
			    }
		    });
		}
	
	</script>

  </head>
  
  <body>
  		<table>
	  		<tr>
	  			<td><input type="text" name="data" value="Jason" /></td>
	  		</tr>
	  		<tr>
	  			<td><input type="text" name="data" value="Sean" /></td>
	  		</tr>
	  		<tr>
	  			<td>
	  				<input type="text" name="data" value="Danny" />
	  				<input type="button" value="OK" onclick="arrayData();" />
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>
	  				<input type="checkbox" name="selected" value="Jason" />
	  				<input type="checkbox" name="selected" value="Sean" />
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>  				
	  				<input type="button" name="btnValue" value="select" title="OK" onclick="selectData();" />
	  				<input type="button" name="btnValue" value="json" onclick="jsonArray();" />
	  			</td>
	  		</tr>
	  	</table>
		<input type="button" value="show table" onclick="ajaxShow();"/>
		<div style="float: left;">
			<table>
				<tbody id='showByGet'>
				
				</tbody>
			</table>
		</div>	
		<div style="float: left;">
			<table>
				<tbody id='showByPost'>
				
				</tbody>
			</table>
		</div>		
  </body>
</html>
