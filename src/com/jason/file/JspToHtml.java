/**  
* @Title: JspToHtml.java
* @Package com.jason.file
* @Description: TODO(用一句话描述该文件做什么)
* @author Jason.XW.Li@pccw.com  
* @date 2014-11-27 下午3:57:19
* @version V1.0  
*/ 
package com.jason.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: JspToHtml
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Jason.XW.Li@pccw.com
 * @date 2014-11-27 下午3:57:19
 *
 */
public class JspToHtml {
	
	private static String title ="标题测试";
	private static String context ="标题测试";
	private static String editer ="标题测试";
	   
    /**
     * 根据本地模板生成静态页面
     * @param JspFile  jsp路经
     * @param HtmlFile html路经
     * @return
     */
    public static boolean JspToHtmlFile(String filePath, String HtmlFile) {
        String str = "";
        //long beginDate = (new Date()).getTime();
        try {
            String tempStr = "";
            FileInputStream is = new FileInputStream(filePath);//读取模块文件
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((tempStr = br.readLine()) != null)
            str = str + tempStr ;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {               
	        
	        //替换掉模块中相应的地方
        	/*str = str.replaceAll("###title###",title);
	        str = str.replaceAll("###content###",context);
	        str = str.replaceAll("###author###",editer);
	         */        
            File f = new File(HtmlFile);
            BufferedWriter o = new BufferedWriter(new FileWriter(f));
            o.write(str);
            o.close();
            //System.out.println("共用时：" + ((new Date()).getTime() - beginDate) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据url生成静态页面
     *
     * @param u    动态文件路经 如：http://www.163.com/x.jsp	
     * @param path 文件存放路经如：x:\\abc\bbb.html
     * @return
     */
    public static boolean JspToHtmlByURL(String u, String path) {
        //从utl中读取html存为str
        String str = "";
        try {
            URL url = new URL(u);
            URLConnection uc = url.openConnection();
            InputStream is = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
            	str += br.readLine() + "\n";                    
            }
            is.close();
            //写入文件
            File f = new File(path);
            BufferedWriter o = new BufferedWriter(new FileWriter(f));
            o.write(str);
            o.close();
            str = "";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据url生成静态页面
     *
     * @param url 动态文件路经 如：http://www.163.com/x.jsp	
     * @return d
     */
    public static StringBuffer getHtmlTextByURL(String url) {
        //从utl中读取html存为str
        StringBuffer sb = new StringBuffer();
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            InputStream is = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
            	sb.append(br.readLine() + "\n");
            }
            is.close();
            return sb;
        } catch (Exception e) {
	        e.printStackTrace();
	        return sb;
        }
    }

    /**
     * 测试main 函数
     *
     * @param arg
     */
    public static void main(String[] arg) {
        long begin = System.currentTimeMillis();
        //循环生成20个html文件	                
        String template = "D:\\remind.html";//模板文件地址
        String path = "C:/remind.html";//生成文件地址
        
        findReplaceString(template,path);		
        
        //pattern();
    }
    
    public static void pattern(){
    	Pattern p = Pattern.compile("^([\\s\\S]*)##([\\s\\S]*)\\s");
		Matcher m = p.matcher("<multiline label='Description'>Dear ##customer.f_name ##customer.l_name,");
		while(m.find()) {
			System.out.println("找到："+m.group());
		}
    }
    
    //找到想要替换的字符串并替换
    public static List<String> findReplaceString(String template,String path) {
        ArrayList<String> list = new ArrayList<String>();
        String[] split = {};
        String replaceStr = "";
        String replaceStred = "";
    	String allStr = "";
    	int len = 0;
        try {
            String tempStr = "";
            //读取模块文件
            FileInputStream is = new FileInputStream(template);
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while ((tempStr = br.readLine()) != null){
            	//判断所需字符串
            	if(tempStr.indexOf("##")!=-1){
            		split = tempStr.split("##");
            		len = split.length;
          		  	System.out.println("找到："+tempStr);
          		  	
          		  	for (int i = 1; i < len; i++) {						
						replaceStr = "##"+split[i];
						System.out.println(replaceStr);
						//找到对应的字符，如果存在就把它替换掉
						replaceStred = "<input type='text' name='replaceStr' value='"+replaceStr+"'/>";
						tempStr = tempStr.replaceAll(replaceStr,replaceStred);
						
						list.add(replaceStred);
					}
            	}
            	allStr = allStr + tempStr;            	
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //写入文件
        /*File f = new File(path);
        BufferedWriter o;
		try {
			o = new BufferedWriter(new FileWriter(f));
			o.write(allStr);
	        o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}              
        System.out.println(allStr);*/
        
        return list;
    }
	
}
