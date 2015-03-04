/**  
* @Title: GenerateTemplateServlet.java
* @Package com.jason.file
* @Description: TODO(用一句话描述该文件做什么)
* @author Jason.XW.Li@pccw.com  
* @date 2014-11-27 下午4:42:35
* @version V1.0  
*/ 
package com.jason.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

/**
 * @ClassName: GenerateTemplateServlet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Jason.XW.Li@pccw.com
 * @date 2014-11-27 下午4:42:35
 *
 */
public class GenerateTemplateServlet extends HttpServlet {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//resp.setContentType("application/json;charset=UTF-8"); 
        String template = req.getParameter("template");                
        String url = "D:\\"+template;//模板文件地址
        System.err.println(url);
        String html = JspToHtmlFile(url);
        //String json = JSONArray.fromObject(savepath).toString(); 		
		//resp.setHeader("Cache-Control", "no-cache");
		//resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(html);
		resp.getWriter().flush();
	}
	
	public static String JspToHtmlFile(String filePath) {
        String str = "";
        try {
            String tempStr = "";
            FileInputStream is = new FileInputStream(filePath);//读取模块文件
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while ((tempStr = br.readLine()) != null)
            str = str + tempStr ;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {      
            File f = new File(HtmlFile);
            BufferedWriter o = new BufferedWriter(new FileWriter(f));
            o.write(str);
            o.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }*/
        return str;
    }

}
