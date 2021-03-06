<%@ page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>  
<%@ page import="com.jspsmart.upload.SmartUpload"%>  
     
<%  
    //新建一个SmartUpload对象  
    SmartUpload su = new SmartUpload();  
 
    //上传初始化  
    su.initialize(pageContext);  
 
    // 设定上传限制  
    //1.限制每个上传文件的最大长度为1M
    su.setMaxFileSize(1024*1024*1024);  
 
    //2.限制总上传数据的长度为1M 
    su.setTotalMaxFileSize(1024*1024*1024);  
 
    //3.设定允许上传的文件（通过扩展名限制）,仅允许htm,html文件。  
    su.setAllowedFilesList("htm,html,jpg,jpeg,png,HTM,HTML,JPG,JPEG,PNG");  
      
    boolean sign = true;  
    
	StringBuffer filename = new StringBuffer();
    
    //生成时间戳  
    long time = System.currentTimeMillis();    
    String second = String.valueOf(time);    
    filename.append(second);  
    
    String templateInput = "";
    
    String errMsg = "";
    
    //4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,doc,txt,jpg,rar,mid,waw,mp3,gif扩展名的文件和没有扩展名的文件。 
    try {  
        su.setDeniedFilesList("exe,bat,jsp,doc,txt,rar,mid,waw,mp3,gif");   
        //上传文件  
        su.upload();  
        
        //通过file input的name属性获得文件名
        templateInput = su.getRequest().getParameter("templateInput");
        
        filename.append(templateInput);
        
        //将上传文件保存到指定目录  
        //su.save("upload");
        
        String url = this.getServletContext().getRealPath("/") + "upload" 
            	+java.io.File.separator + filename;
        
        su.getFiles().getFile(0).saveAs(url); 
        
    } catch (Exception e) {
        e.getStackTrace();
        sign = false;
    }
    if(sign==true) {
        out.println("<script>alert('Upload file successfully')</script>");
    }else {
        out.println("<script>alert('Upload file fail')</script>");  
    }
%>   

