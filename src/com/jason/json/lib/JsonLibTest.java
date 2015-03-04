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
public class JsonLibTest {

	/**
	 * 构造Json数据
	 * */
	public static String BuildJson() {

        // JSON格式数据解析对象
        JSONObject jo = new JSONObject();

        // 下面构造两个map、一个list和一个Employee对象
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "Alexia");
        map1.put("sex", "female");
        map1.put("age", "23");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "Edward");
        map2.put("sex", "male");
        map2.put("age", "24");

        List<Map> list = new ArrayList<Map>();
        list.add(map1);
        list.add(map2);

        Employee employee = new Employee();
        employee.setName("wjl");
        employee.setSex("female");
        employee.setAge("24");
        
        Employee employee2 = new Employee();
        employee2.setName("lxw");
        employee2.setSex("male");
        employee2.setAge("25");
        
        List<Employee> list2 = new ArrayList<Employee>();
        list2.add(employee);
        list2.add(employee2);

        // 将Map转换为JSONArray数据
        JSONArray ja1 = JSONArray.fromObject(map1);
        // 将List转换为JSONArray数据
        JSONArray ja2 = JSONArray.fromObject(list);
        JSONArray ja3 = JSONArray.fromObject(list2);
        // 将Bean转换为JSONArray数据
        JSONArray ja4 = JSONArray.fromObject(employee);

        System.out.println("JSONArray对象数据格式：");
        System.out.println(ja1.toString());
        System.out.println(ja2.toString());
        System.out.println(ja3.toString());
        System.out.println(ja4.toString());

        // 构造Json数据，包括一个map和一个Employee对象
        jo.put("map", ja1);
        jo.put("employee", ja2);
        System.out.println("\n最终构造的JSON数据格式：");
        System.out.println(jo.toString());

        return jo.toString();

    }
	
	/**
	 * 解析Json数据
	 * */
	public static void ParseJson(String jsonString) {

        // 以employee为例解析，map类似
        JSONObject jb = JSONObject.fromObject(jsonString);
        JSONArray ja = jb.getJSONArray("employee");

        List<Employee> empList = new ArrayList<Employee>();

        // 循环添加Employee对象（可能有多个）
        for (int i = 0; i < ja.size(); i++) {
            Employee employee = new Employee();

            employee.setName(ja.getJSONObject(i).getString("name"));
            employee.setSex(ja.getJSONObject(i).getString("sex"));
            employee.setAge(ja.getJSONObject(i).getString("age"));

            empList.add(employee);
        }

        System.out.println("\n将Json数据转换为Employee对象：");
        for (int i = 0; i < empList.size(); i++) {
            Employee emp = empList.get(i);
            System.out.println("name: " + emp.getName() + " sex: " + emp.getSex() + " age: " + emp.getAge());
        }

    }
	
    public static void main(String[] args) {

        ParseJson(BuildJson());
    }
	
}
