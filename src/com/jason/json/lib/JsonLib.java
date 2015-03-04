/**
 * @author Jason
 *
 * @time 2015-2-28
 *
 * @version V1.0
 */
package com.jason.json.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jason.json.entity.Employee;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Jason
 *
 * description: 使用json-lib构造和解析Json数据
 */
public class JsonLib {
	
	public static void jsonLib(){
		
		//List集合转换成json方法
		List<String> list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		list.add("thrid");
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.get(0)+"; "+jsonArray.get(1)+"; "+jsonArray.get(2));
		
		//Map结合转换成json方法
		Map map = new HashMap();
		map.put("name", "Jason");
		map.put("boolean", Boolean.TRUE);
		map.put("int", new Integer(1));
		map.put("array", new String[] { "a", "b" });
		map.put("function", "function(i){ return this.arr[i]; }");
		
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json.getString("name")+"; "+json.getString("boolean")+"; "
				+json.getString("int")+"; "+json.getString("array")+"; "+json.getString("function"));
		
		//数组转换成json代码
		boolean[] booleanArray = new boolean[]{true,false,true};
		JSONArray jsonArray2 = JSONArray.fromObject(booleanArray);
		System.out.println(jsonArray2.get(0)+"; "+jsonArray2.get(1)+"; "+jsonArray2.get(2));
		
		//一般数据转换成json代码
		JSONArray jsonArray3 = JSONArray.fromObject("['json','is','easy']");
		System.out.println(jsonArray3.get(0)+"; "+jsonArray3.get(1)+"; "+jsonArray3.get(2));
		
		//JavaBean转换成json代码			
		List<Employee> list2 = new ArrayList<Employee>();
		
		Employee employee = new Employee();
		employee.setName("Joy");
		employee.setSex("Female");
		employee.setAge("23");
		
		System.out.println(employee.getName()+", "+employee.getSex()+", "+employee.getAge());
		
		list2.add(employee);
		
		JSONArray jsonArray4 = JSONArray.fromObject(employee);
		System.out.println(jsonArray4.toString());
	}
	
	public static void main(String[] args) {
		jsonLib();
	}

}
