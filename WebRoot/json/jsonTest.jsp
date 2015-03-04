<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Json Test</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/json.js"></script>
	<script type="text/javascript">		
		/**
		* 将字符串转换为json对象
		*/
		function strToJson(){			
			//json字符串
			var records = {
			    "table": "GPS_MANAGER",
			    "token": "32sdfj-349sfdnfs32-fsdf348imfg323-df34",
			    "pk": "GPS_LONGITUDE,GPS_LATITUDE",
			    "rows": [
			        {
			            "type": "modify",
			            "columns": [
			                {
			                    "GPS_LONGITUDE": "GPS_LONGITUDE",
			                    "old": "24",
			                    "new": "112.9152287"
			                },
			                {
			                    "GPS_LATITUDE": "GPS_LATITUDE",
			                    "old": "35",
			                    "new": "22.6689977"
			                },
			                {
			                    "GPSMARK": "GPSMARK",
			                    "old": "46",
			                    "new": "1"
			                },
			                {
			                    "RTUNAME": "RTUNAME",
			                    "old": "58",
			                    "new": "大边岩站"
			                }
			            ]
			        }
			    ]
			};
			
			//使用JS的eval()方法将json字符串解析为json对象
			var obj = eval(records);		
			alert("使用JS的eval()方法将json字符串解析为json对象："+obj.rows[0].columns[0].GPS_LONGITUDE);
			
			//json字符串集
			var data = '{'  
			    +'"root":'  
				    +'['  
				    +'{"name":"1","value":"0"},'  
				    +'{"name":"6101","value":"西安市"},'   
				    +'{"name":"6102","value":"铜川市"},'   
				    +'{"name":"6103","value":"宝鸡市"},'  
				    +'{"name":"6104","value":"咸阳市"},'   
				    +'{"name":"6105","value":"渭南市"},'  
				    +'{"name":"6106","value":"延安市"},'   
				    +'{"name":"6107","value":"汉中市"},'   
				    +'{"name":"6108","value":"榆林市"},'   
				    +'{"name":"6109","value":"安康市"},'   
				    +'{"name":"6110","value":"商洛市"}'   
				    +']'  
			    +'}';
			
			//方法1：使用JS的eval()方法将json字符串集解析为json对象
			var obj2 = eval("("+data+")");
			/*
				为什么要 eval这里要添加 "("("+data+")");呢？
	
				原因在于：eval本身的问题。 由于json是以”{}”的方式来开始以及结束的，在JS中，它会被当成一个语句块来处理，所以必须强制性的将它转换成一种表达式。
	
				加上圆括号的目的是迫使eval函数在处理JavaScript代码的时候强制将括号内的表达式（expression）转化为对象，而不是作为语句（statement）来执行。
				举一个例子，例如对象字面量{}，如若不加外层的括号，那么eval会将大括号识别为JavaScript代码块的开始和结束标记，那么{}将会被认为是执行了一句空语句。
				所以下面两个执行结果是不同的：
				alert(eval("{}");  // return undefined
				alert(eval("({})");// return object[Object] 			
			*/
			alert("方法1：使用JS的eval()方法将json字符串集解析为json对象："+obj2.root[0].name);
			
			//方法2：使用JQuery的parseJSON()方法将json字符串转换为json对象
			var obj3 = jQuery.parseJSON(data);
			alert("方法2：使用JQuery的parseJSON()方法将json字符串转换为json对象："+obj3.root[1].value);
			
			//方法3：使用js的JSON.parse()方法将json字符串转换为json对象
			var obj4 = JSON.parse(data);
			alert("方法3：使用js的JSON.parse()方法将json字符串转换为json对象："+obj4.root[10].name);
			
			//使用js的JSON.parse()方法 反序列化 ISO 格式的日期字符串, 将返回Date格式对象 
		    var jsontext = '{ "hiredate": "2008-01-01T12:00:00Z", "birthdate": "2008-12-25T12:00:00Z" }';  
		    var dates = JSON.parse(jsontext, dateReviver);  
		      
		    function dateReviver(key, value) {  
		        var a;  
		        if (typeof value === 'string') {  
		            a = /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value);  
		            if (a) {  
		                return new Date(Date.UTC(+a[1], +a[2] - 1, +a[3], +a[4], +a[5], +a[6]));  
		            }  
		        }  
		        return value;  
		    };
		    
		    alert("使用js的JSON.parse()方法 反序列化 ISO 格式的日期字符串, 将返回Date格式对象："+dates.birthdate.toUTCString());
			
		    //方法4：使用Function对象来完成，它的典型应用就是在JQUERY中的AJAX方法下的success等对于返回数据data的解析
		    var obj5 = (new Function("","return "+data))();		    
		    alert("方法4：使用Function对象来完成，它的典型应用就是在JQUERY中的AJAX方法下的success等对于返回数据data的解析："+obj5.root[5].value);
			
		}
		
		/**
		* 将json对象转换为字符串
		*/
		function jsonToString(){
			
			var data = '{'  
			    +'"root":'  
				    +'['  
				    +'{"name":"1","value":"0"},'  
				    +'{"name":"6101","value":"西安市"},'   
				    +'{"name":"6102","value":"铜川市"},'   
				    +'{"name":"6103","value":"宝鸡市"},'  
				    +'{"name":"6104","value":"咸阳市"},'   
				    +'{"name":"6105","value":"渭南市"},'  
				    +'{"name":"6106","value":"延安市"},'   
				    +'{"name":"6107","value":"汉中市"},'   
				    +'{"name":"6108","value":"榆林市"},'   
				    +'{"name":"6109","value":"安康市"},'   
				    +'{"name":"6110","value":"商洛市"}'   
				    +']'  
			    +'}';
			    
			var obj = (new Function("","return "+data))();
			
			//方法1： 利用js的JSON.stringify(obj)方法 将json对象转换为json字符串
			var jsonText = JSON.stringify(obj); 
			
			alert("方法1： 利用js的JSON.stringify(obj)方法 将json对象转换为json字符串："+jsonText+ ", jsonText[0] = "+jsonText[0]);
			
			/*
			* 使用 JSON.stringify 将 contact 对象转换为 JSON 文本。 
			* 定义 memberfilter 数组以便只转换 surname 和 phone 成员。 省略 firstname 成员。
			*/
			var contact = new Object();
			contact.firstname = "Jesper";
			contact.surname = "Aaberg";
			contact.phone = ["555-0100", "555-0120"];

			var memberfilter = new Array();
			memberfilter[0] = "surname";
			memberfilter[1] = "phone";
			
			var jsonText2 = JSON.stringify(contact, memberfilter, "\t");
			
			alert("使用 JSON.stringify 将 contact 对象转换为 JSON 文本："+jsonText2+ ", jsonText2[0] = "+jsonText2[0]);
			
			//将 JSON.stringify 与一个数组一起使用。 replaceToUpper 函数将数组中的每个字符串转换为大写形式
			var continents = new Array();
			continents[0] = "Europe";
			continents[1] = "Asia";
			continents[2] = "Australia";
			continents[3] = "Antarctica";
			continents[4] = "North America";
			continents[5] = "South America";
			continents[6] = "Africa";

			var jsonText3 = JSON.stringify(continents, replaceToUpper);

			function replaceToUpper(key, value) {
			    return value.toString().toUpperCase();
			}
			
			alert("将 JSON.stringify 与一个数组一起使用。 replaceToUpper 函数将数组中的每个字符串转换为大写形式："+jsonText3+ ", jsonText3[0] = "+jsonText3[0]);
			
			// 利用js的JSON.stringify(obj)方法 将Array对象转换为json字符串
			var array = new Array();
			array[0] = "[1,'A','0.2']";
			array[1] = "[2,'b','0.4']";
			array[2] = "[3,'西','0.8']";
			
			var jsonText4 = JSON.stringify(array);
			alert("利用js的JSON.stringify(obj)方法 将Array对象转换为json字符串："+jsonText4 + ", jsonText4[0] = "+jsonText4[0]);
			 
		}
		
		//方法2：利用json解析和转换器json.js的toJSONString()方法
		function jsonParser(){
			
			var obj = new Car("Doge","Cornet",1968,"yellow");
                
			//利用json解析和转换器json.js的toJSONString方法将一个object类型转换为一个json结构的一个字符串
			var myObj = obj.toJSONString(); 
			alert("利用json解析和转换器json.js的toJSONString方法将一个object类型转换为一个json结构的一个字符串："+myObj+", myObj[1] = "+myObj[1]);	
			
			//定义一个数组
			var array = new Array();
			array[0] = "[1,'A','0.2']";
			array[1] = "[2,'b','0.4']";
			array[2] = "[3,'西','0.8']";
			
			//利用json解析和转换器json.js的toJSONString方法将一个Array类型转换为一个Array结构的字符串
			var myArray = array.toJSONString();
			alert("利用json解析和转换器json.js的toJSONString方法将一个Array类型转换为一个Array结构的字符串："+myArray+", myArray[1] = "+myArray[1]);
			
			//利用json解析和转换器json.js的toJSONString方法将一个Date类型转换为一个Date结构的字符串
			var date = new Date();    
			var myDate = date.toLocaleString();     //获取当前系统日期与时间   
			alert("利用json解析和转换器json.js的toJSONString方法将一个Date类型转换为一个Date结构的字符串："+myDate+", mytime[1] = "+myDate[1]);
			
			//利用json解析和转换器json.js的toJSONString方法将一个Boolean类型转换为一个Boolean结构的字符串
			var boolean = true;
			var myBoolean = boolean.toJSONString();
			alert("利用json解析和转换器json.js的toJSONString方法将一个Boolean类型转换为一个Boolean结构的字符串："+myBoolean+", myBoolean[1] = " + myBoolean[1]);
			
			//利用json解析和转换器json.js的toJSONString方法将一个Number类型转换为一个Number结构的字符串
			var number = 8.24;
			var myNumber = number.toJSONString();
			alert("利用json解析和转换器json.js的toJSONString方法将一个Number类型转换为一个Number结构的字符串："+myNumber+", myNumber[1] = " + myNumber[1]);
			
		}
		
		//定义一个对象
		function Car(make,model,year,color){
			this.make = make;
			this.model = model;
			this.year = year;
			this.color = color;
		}
	
	</script>

  </head>
  
  <body>
		<div>
			<h3>运用js和jq中关于处理json的方法将字符串等转换为json对象</h3>
			<input type="button" value='strToJson' onclick="strToJson();"/>
		</div>
		<div>
			<h3>将json对象转换为字符串</h3>
			<p>方法1：运用js和jq中关于处理json的方法将json对象转换为字符串</p>
			<input type="button" value='jsonToString' onclick="jsonToString();"/><br>
			<p>方法2：运用json解析器json.js中关于处理json的方法将json对象转换为字符串</p>
			<input type="button" value="jsonParser" onclick="jsonParser();" />
		</div>		
  </body>
</html>
