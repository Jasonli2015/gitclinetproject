/**  
* @Title: FileUpload.java
* @Package com.jason.File
* @Description: TODO(用一句话描述该文件做什么)
* @author Jason.XW.Li@pccw.com  
* @date 2014-10-23 下午4:29:45
* @version V1.0  
*/ 
package com.jason.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/** 
 *  
 * @ClassName: FileUpload
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Jason.XW.Li@pccw.com
 * @date 2014-10-23 下午4:29:45
 * 文件上传 
 * 具体步骤： 
 * 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 
 * 2） 利用 request 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同 
 * 3）对 DiskFileItemFactory 对象设置一些 属性 
 * 4）高水平的API文件上传处理  ServletFileUpload upload = new ServletFileUpload(factory); 
 * 目的是调用 parseRequest（request）方法  获得 FileItem 集合list
 *      
 * 5）在 FileItem 对象中 获取信息，   遍历， 判断 表单提交过来的信息 是否是 普通文本信息  另做处理 
 * 6） 
 *    第一种. 用第三方 提供的  item.write( new File(path,filename) );  直接写到磁盘上 
 *    第二种. 手动处理   
 * 
 */  
public class UploadFileServlet extends HttpServlet {  
  
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
          
        request.setCharacterEncoding("utf-8");  //设置编码  
          
        //获得磁盘文件条目工厂  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        
        //获取文件需要上传到的路径  
        String path = this.getServletContext().getRealPath("/WEB-INF/classes/com/jason/template"); 
          
        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，  
        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同  
        /** 
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，  
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的  
         * 然后再将其真正写到 对应目录的硬盘上 
         */  
        factory.setRepository(new File(path));  
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室  
        factory.setSizeThreshold(1024*1024) ;  
          
        //高水平的API文件上传处理  
        ServletFileUpload upload = new ServletFileUpload(factory);   
        
        //将页面请求传递信息最大值设置为50M
        upload.setSizeMax(1024*1024*50);
        //将单个上传文件信息最大值设置为6M
        upload.setFileSizeMax(1024*1024*5);
          
        try {  
            //可以上传多个文件  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
              
            for(FileItem item : list) {  
                //获取表单的属性名字  
                String name = item.getFieldName();  
                  
                //如果获取的 表单信息是普通的 文本 信息  
                if(item.isFormField()) {                     
                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的  
                    String value = item.getString() ;  
                      
                    request.setAttribute(name, value);  
                } else {  
                    /** 
                     * 以下三步，主要获取 上传文件的名字 
                     */  
                    //获取路径名  
                    String value = item.getName() ;  
                    //索引到最后一个反斜杠  
                    int start = value.lastIndexOf("\\");  
                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，  
                    String filename = value.substring(start+1); 
                    //获取上传文件的类型
                    String endName= filename.substring(filename.lastIndexOf(".")+1);
                    
                    if (endName.equals("html")||endName.equals("htm")) {
                    	 request.setAttribute(name, filename);                                            
                         //判断上传文件的大小
                         if (item.getSize() > 5 * 1024 * 1024){
                         	System.out.println("获取上传文件的总共的容量："+item.getSize()+"，文件超过了5M， 请重新选择!");
                         } else {
                         	 //真正写到磁盘上  
                             //它抛出的异常 用exception 捕捉                                
                             //手动写的  
                             OutputStream out = new FileOutputStream(new File(path,filename));  
                               
                             InputStream in = item.getInputStream() ;  
                               
                             int length = 0 ;  
                             byte [] buf = new byte[1024] ;  
                               
                             System.out.println("获取上传文件的总共的容量："+item.getSize()+"b.");  
           
                             // in.read(buf) 每次读到的数据存放在   buf 数组中  
                             while( (length = in.read(buf) ) != -1) {  
                                 //在   buf 数组中 取出数据 写到 （输出流）磁盘上  
                                 out.write(buf, 0, length);                         
                             }  
                               
                             in.close();  
                             out.close();
                         }
					} else {
						System.out.println("获取上传文件的类型"+endName+"不符合！");
					}                                    
                     
                }  
            }              
                         
        } catch (FileUploadException e) {   
            e.printStackTrace();  
        }  
        catch (Exception e) {                
            e.printStackTrace();  
        }  
            
    }  
  
}  
