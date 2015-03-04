/**
 * @author Jason
 *
 * @time 2015-2-13
 *
 * @version V1.0
 */
package com.jason.json.org;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Jason
 *
 * description: 由于org.json不能直接与bean进行转换，需要通过map进行中转，所以此方法用于Json与Map、Bean的相互转换
 *  
 */
public class OrgJsonHelper {
	
	/**
	 * 将JavaBean转化为Map对象
	 * */
	public static Map<String, String> javaBeanToMap(Object javaBean){
		
		Map<String, String> result = new HashMap<String, String>();
		
		Method[] methods = javaBean.getClass().getDeclaredMethods();
		
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {		
				
				try {
					String field = method.getName();
					field = field.substring(field.indexOf("get")+3); //截取“get”后面的字符串
					field = field.toLowerCase().charAt(0) + field.substring(1);
					
					Object value = method.invoke(javaBean, (Object[])null);
					result.put(field, null == value ? "" : value.toString());
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return result;
		
	}
	
	/**
	 * 将Json转化为Map对象
	 * */
	public static Map<String, String> jsonToMap(String jsonString){
		
		Map<String, String> result = new HashMap<String, String>();
		
		try {
			
			JSONObject jsonObject = new JSONObject(jsonString);			
			
			Iterator<String> iterator = jsonObject.keys();
			
			String key = null;
			
			String value = null;
			
			while (iterator.hasNext()) {
				
				key = (String) iterator.next();
				
				value = jsonObject.getString(key);
				
				result.put(key, value);				
			}
						
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		return result;
	}
	
	/**
	 * 将JavaBean转化为Json对象
	 * */
	public static JSONObject javaBeanToJsonObject(Object bean){
		
		return new JSONObject(javaBeanToMap(bean));
		
	}
	
	/**
	 * 将Map转化为JavaBean对象
	 * */
	public static Object mapToJavaBean(Object javaBean, Map<String, String> data){
		
		Method[] methods = javaBean.getClass().getDeclaredMethods();
		
		for (Method method : methods) {
			
			try {
				if (method.getName().startsWith("set")) {
					String field = method.getName();
					field = field.substring(field.indexOf("set")+3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					method.invoke(javaBean, new Object[]{
							data.get(field)
					});
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		
		return javaBean;
		
	}
	
	/**
	 * 将JSONObject转化为JavaBean对象
	 * */
	public static void jsonObjectToJavaBean(Object javaBean, String jsonString){
		
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			Map<String, String> map = jsonToMap(jsonObject.toString());			
			mapToJavaBean(javaBean,map);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
	}

}
