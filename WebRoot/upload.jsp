<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>upload file</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <link type="text/css" rel="stylesheet" href="css/uploadify.css" />
  
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery.uploadify.min.js"></script>
  
  <script type="text/javascript">
  
  	$(function(){ 
	   $("#uploadify").uploadify({  
	        'swf'              : 'css/uploadify.swf',  
	        'uploader'         : 'UploadAttachmentServlet', 
	        'buttonText'       : 'Browse',
	        'folder'           : 'upload',  
	        'queueID'          : 'fileQueue',  
	        'auto'             : true,  
	        'multi'            : true,  
	        'wmode'            : 'transparent', 
	        'fileTypeExts'     : '*.pdf;*.doc;*.docx;*.xls;*.xlsx;*.txt;*.csv;*.png;*.jpeg;*.jpg',
	        'fileTypeDesc'     : 'Attachment files(.pdf,.doc,.docx,.xls,.xlsx,.txt,.csv,.png,.jpeg,.jpg)',	        	        
	        'sizeLimit'        : 1024,
	        'queueSizeLimit'   : 5,
	        'simUploadLimit'   : 5,  
	        'fileSizeLimit'    : 5120,	        
	        'removeCompleted'  : false,	      
	        'onSelect'         : function(e, queueId){
	        	//隐藏进度条
	        	$(".uploadify-progress").hide();
	        },	        
	        'onUploadStart'    : function (file) {
			    var myself = this;
			    var length = $("#fileQueue .uploadify-queue-item").length;
			    //限制上传文件的个数为5个
			    if(length > 5){
			      myself.cancelUpload(file.id);
			      $('#' + file.id).remove();
			      alert("The number of attachments not more than five.");
			    }
			}
	    });	   
  	});
  	
	function browseBtn(){
		$("#file").click();		
	}
	function changeBtn(){
		if ($.browser.webkit) {
        	var data = $("#file").val().replace(/(c:\\)*fakepath\\/i,'');
        	$("#templateInput").val(data);  
		} else if ($.browser.mozilla) {
			var data = $("#file").val();
			$("#templateInput").val(data);
		} else if ($.browser.msie) {
			var file_upl = document.getElementById("file");	
			file_upl.select();
			window.parent.document.body.focus(); 
			var realpath = document.selection.createRange().text;
			document.getElementById("templateInput").value = realpath;
		} 
	}
	
	function fileBtn(){		
		if( $.browser.msie && $.browser.version == "8.0" ){
			var file_upl = document.getElementById("file2");	
			file_upl.select();
			window.parent.document.body.focus(); 
			var realpath = document.selection.createRange().text;			
			document.getElementById("templateInput").value = realpath;
		}
	}
	
	function uploadFile(obj){
		if ($.browser.webkit||$.browser.msie) {
			var data = obj.value.replace(/(c:\\)*fakepath\\/i,'');
			$("#textfield").val(data);  
		} else if ($.browser.mozilla) {
			var data = obj.value;
			$("#textfield").val(data);
		} 
		//document.getElementById('textfield').value=obj.value;
	}

  </script>
  <style type="text/css">
	body{ font-size:14px;}
	input{ vertical-align:middle; margin:0; padding:0}
	.file-box{ position:relative;width:340px}
	.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
	.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
	.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px } 	
  </style>
  <body>      
	<form action="filedemo.jsp" id="fileform" name="fileform" encType="multipart/form-data"  method="post" target="hidden_frame" >
		<div>
			<input type="text" id="templateInput" name="templateInput" style="width: 248px;"/>
			<input type="button" value="browse" onclick="browseBtn();"/>
			<input type="file" name="file" id="file" onchange="changeBtn();"/>
			<input type="submit" id="submitBtn" value="submit"/>
		</div>			
		<!--<div>
			<input type="file" name="file2" id="file2" onchange="fileBtn();" />
			<input type="submit" value="submit" /> 
		</div>-->				
		<iframe name='hidden_frame' id="hidden_frame" style='display:none' ></iframe> 					
	</form>
	<div class="file-box">
	  <form action="filedemo.jsp" method="post" enctype="multipart/form-data" method="post" target="hidden_frame">
	 	<input type='text' name='textfield' id='textfield' class='txt' />  
	 	<input type='button' class='btn' value='browse' />
	    <%--<input type="file" name="fileField" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />--%>
	    <input type="file" name="fileField" class="file" id="fileField" size="28" onchange="uploadFile(this);" />
	 	<input type="submit" name="submit" class="btn" value="upload" />
	  </form>
	</div>
	<br>
	<br>
	<h4>多文件上传：</h4>
	<table style="width: 90%;">  
        <tr>  
            <td style="width: 370px;">  
                <div id="fileQueue" style="width: 370px;height: 280px; border: 1px solid #bfbfbf;"></div>
            </td> 
            <td valign="top">
            	<input type="file" name="uploadify" id="uploadify" />
            </td>
        </tr>  
    </table>    
  </body>
</html>
