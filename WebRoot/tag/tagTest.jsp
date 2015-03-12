<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.jason.tag.entity.*"%>
<%@ taglib uri="/WEB-INF/tlds/jason-tag.tld" prefix="jason-tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Tag Test</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  
  	<%
		String tut = "tutorial";
		request.setAttribute("tut",tut);
	%>
	The String in request is :
	<p><c:out value="${tut}"/></p> 
  	
  	<%
		String s = "Jason";
		request.setAttribute("name",s);
	%>
	Test El supported tag:
	<p><jason-tag:output name="${name}"/></p>  	
  
    <%
		Man man = new Man();
		man.setSex("diego");		
		request.setAttribute("man",man);
	%>
	Test nested tag:
	<br>
	<jason-tag:with value="${man}">
		<jason-tag:nestedout property="sex"/>
	</jason-tag:with>
  </body>
</html>
