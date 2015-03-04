/**
 * @author Jason
 *
 * @time 2015-3-4
 *
 * @version V1.0
 */
package com.jason.file;

import java.io.File;

/**
 * @author Jason
 *
 * description: 删除指定文件夹里面的所有文件
 */
public class FileOperation {
	
	public static boolean removeImage(){
		String delpath = "D:/apache-tomcat-6.0.30(could)/webapps/TestProject/WEB-INF/classes/com/jason/image";
		boolean flag = false;
		File file = new File(delpath);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] temList = file.list();
		File temp = null;
		for (int i = 0; i < temList.length; i++) {
			if (delpath.endsWith(File.separator)) {
				temp = new File(delpath + temList[i]);
			} else {
				temp = new File(delpath + File.separator + temList[i]);
			}
			if (temp.isFile()) {
				temp.delete();				
				flag = true;
			}			
		}
		return flag;
	}
	
	public static void main(String[] args) {
		removeImage();
	}

}
