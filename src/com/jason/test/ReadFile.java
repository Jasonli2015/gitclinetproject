/**  
* @Title: SeparatorUtils.java
* @Package com.jason.test
* @Description: TODO(用一句话描述该文件做什么)
* @author Jason.XW.Li@pccw.com  
* @date 2014-11-12 下午2:53:05
* @version V1.0  
*/ 
package com.jason.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName: SeparatorUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Jason.XW.Li@pccw.com
 * @date 2014-11-12 下午2:53:05
 *
 */
public class ReadFile {
	
	public static void main(String[] args) {
		String filepath = "D:/PCCW/study/template";
		String name = "spring3.0.chm";
		try {
			//readfile(filepath);
			deletefile(filepath,name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * 读取某个文件夹下的所有文件
     */
    public static void readfile(String filepath) throws FileNotFoundException, IOException {
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                /*System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());*/
                System.out.println("name1=" + file.getName());

            } else if (file.isDirectory()) {
	            String[] filelist = file.list();
	            for (int i = 0; i < filelist.length; i++) {
	                File readfile = new File(filepath + "\\" + filelist[i]);
	                if (!readfile.isDirectory()) {
	                    /*System.out.println("path=" + readfile.getPath());
	                    System.out.println("absolutepath=" + readfile.getAbsolutePath());*/
	                    System.out.println("name=" + readfile.getName());
	                } else if (readfile.isDirectory()) {
	                    readfile(filepath + "\\" + filelist[i]);
	                }
	            }

            }

        } catch (FileNotFoundException e) {
                System.out.println("readfile() Exception:" + e.getMessage());
        }
    }
    
    public static void deletefile(String delpath,String name)
            throws FileNotFoundException, IOException {
	    try {	
            File file = new File(delpath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        /*System.out.println("path=" + delfile.getPath());
                        System.out.println("absolutepath="+ delfile.getAbsolutePath());
                        System.out.println("name=" + delfile.getName());*/
                        if (delfile.getName().equals(name)) {
                        	delfile.delete();
                            System.out.println("删除文件成功");
                            break;
						}                        
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i],name);
                    }
                 }
                 file.delete();	
	         }
	
	    } catch (FileNotFoundException e) {
	            System.out.println("deletefile() Exception:" + e.getMessage());
	    }
	}
}