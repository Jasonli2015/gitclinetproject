 package com.jason.properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * @ClassName: ReadPropertiesFiles
 * @Description: TODO(读取.properties配置文件)
 * @author Jason.XW.Li@pccw.com
 * @date 2014-8-19 下午2:48:25
 *
 */
public class ReadPropertiesFiles {
	
	
	public static void main(String[] args) {
		
		readFile();
		
	}
	
	public static void readFile(){
		
		String fileName = "user.properties";
		
		PropertiesConfiguration config = null;
		
		String url = "";
		
		String port = "";
		
		String username = "";
		
		String password = "";		
				
		try {
			config = new PropertiesConfiguration(fileName);
			config.setReloadingStrategy(new FileChangedReloadingStrategy());//自动刷新配置文件
			url = config.getString("url");
			port = config.getString("port");
			username = config.getString("username");
			password = config.getString("password");			
			config.setProperty("userno", "80546690");//该新加的键值对会保存在内存中
			config.setAutoSave(true);//自动保存
			System.out.println(url +":" + port + "&username = " + username +"&password = " + password + "&userno = " + config.getString("userno"));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}			
		
	}
}
