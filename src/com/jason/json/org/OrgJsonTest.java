/**
 * @author Jason
 *
 * @time 2015-2-28
 *
 * @version V1.0
 */
package com.jason.json.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jason.json.entity.Employee;

/**
 * @author Jason
 *
 * description: 使用org.json构造和解析Json数据
 */
public class OrgJsonTest {

	public static String BuildJson(){
		
		JSONObject jsonObject = new JSONObject();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Jason");
		map.put("sex", "male");
		map.put("age", "25");
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "Eson");
		map2.put("sex", "male");
		map2.put("age", "30");
		
		ArrayList<Map> list = new ArrayList<Map>();
		list.add(map);
		list.add(map2);
		
		Employee employee = new Employee();
		employee.setName("Anna");
		employee.setSex("female");
		employee.setAge("20");
		
		//将Map转换为JSONArray数据
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(map);
		System.out.println("将Map转换为JSONArray对象数据格式：");
        System.out.println(jsonArray.toString());
		
		//将JavaBean转换为Json数据（需要Map中转）
		JSONObject jsonObject2 = OrgJsonHelper.javaBeanToJsonObject(employee);
		System.out.println("\n将JavaBean转换为Json的数据格式：");
        System.out.println(jsonObject2.toString());
		
		//构造Jsons数据，包含一个Map和一个含JavaBean对象的Json对象
		try {
			jsonObject.put("map", jsonArray);
			jsonObject.put("employee", jsonObject2.toString());
			System.out.println("\n包含一个Map和一个含JavaBean对象的Json数据格式：");
	        System.out.println(jsonObject.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}
	
	//解析Json数据
	public static void ParseJson(String jsonString){
		
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("map");
			System.out.println("\n将Json数据解析为Map对象：");
			System.out.println("name: " + jsonArray.getJSONObject(0).getString("name")
					+ ", sex: " + jsonArray.getJSONObject(0).getString("sex") 
					+ ", age: " + jsonArray.getJSONObject(0).getString("age"));
			
			String jsonString2 =  jsonObject.getString("employee");
			Employee employee = new Employee();
			OrgJsonHelper.jsonObjectToJavaBean(employee, jsonString2);
			System.out.println("\n将Json数据解析为JavaBean对象：");
	        System.out.println("name: " + employee.getName() + ", sex: " + employee.getSex()
	                + ", age: " + employee.getAge());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		ParseJson(BuildJson());
		
	}
	
}
