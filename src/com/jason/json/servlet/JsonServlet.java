package com.jason.json.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.json.entity.Employee;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * java将各种类型的数据转换为json数据
	 * */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		/*List集合转换成json方法*/
		List<String> list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		list.add("thrid");
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println("List集合转换成json方法:");
		System.out.println("JSONArray:");
		System.out.println(jsonArray.get(0)+"; "+jsonArray.get(1)+"; "+jsonArray.get(2));
		System.out.println();
		
		JSONObject jsonObj = new JSONObject(); 
		jsonObj.put("first", "first");
		jsonObj.put("second", "second");
		jsonObj.put("thrid", "thrid");
		System.out.println("JSONObject:");
		System.out.println(jsonObj.toString());
		System.out.println();
		
		/*数组转换成json代码*/
		boolean[] booleanArray = new boolean[]{true,false,true};
		JSONArray jsonArray2 = JSONArray.fromObject(booleanArray);
		System.out.println("数组转换成json代码:");
		System.out.println(jsonArray2.get(0)+"; "+jsonArray2.get(1)+"; "+jsonArray2.get(2));
		System.out.println();
		
		/*一般数据转换成json代码*/
		JSONArray jsonArray3 = JSONArray.fromObject("['json','is','easy']");
		System.out.println("一般数据转换成json代码:");
		System.out.println(jsonArray3.get(0)+"; "+jsonArray3.get(1)+"; "+jsonArray3.get(2));
		System.out.println();
		
		/*JavaBean转换成json代码	*/		
		List<Employee> list2 = new ArrayList<Employee>();
		
		Employee employee = new Employee();
		employee.setName("Joy");
		employee.setSex("Female");
		employee.setAge("23");
		
		Employee employee2 = new Employee();
		employee2.setName("Jason");
		employee2.setSex("male");
		employee2.setAge("24");;	
		
		JSONArray jsonArray4 = JSONArray.fromObject(employee);
		System.out.println("JavaBean转换成json代码:");
		System.out.println("JSONArray:");
		System.out.println(jsonArray4.toString());
		System.out.println();	
		
		JSONObject jsonObj2 = JSONObject.fromObject(employee); 
		System.out.println("JSONObject:");
		System.out.println(jsonObj2.toString());
		System.out.println();
		
		//JavaBean in list
		list2.add(employee);
		list2.add(employee2);
		
		JSONArray jsonArray5 = JSONArray.fromObject(list2);
		System.out.println("JavaBean in list of JSONArray:");
		System.out.println(jsonArray5.toString());
		System.out.println();
		
		JSONObject jsonObj3 = new JSONObject();
		jsonObj3.put("employee", employee);
		jsonObj3.put("employee2", employee2);
		System.out.println("JavaBean in list of JSONObject:");
		System.out.println(jsonObj3.toString());
		System.out.println();
		
		/*Map结合转换成json方法*/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employee", employee);
		map.put("boolean", Boolean.TRUE);
		/*map.put("int", new Integer(1));
		map.put("array", new String[] { "a", "b" });
		map.put("function", "function(i){ return this.arr[i]; }");*/
		
		JSONArray jsonArray6 = JSONArray.fromObject(map);
		System.out.println("Map结合转换成json方法:");
		System.out.println("JSONArray:");
		System.out.println(jsonArray6.toString());
		System.out.println();
		
		JSONObject jsonObj4 = JSONObject.fromObject(map); 
		System.out.println("JSONObject:");
		System.out.println(jsonObj4.toString());
		System.out.println();
		
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(jsonObj4.toString());		
	}
	
	/**
	 * java处理来自客户端的json类型的数组
	 * */
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
		String array = req.getParameter("array");
        
        Object[] object = getJsonToArray(array);
        
        //获得第一个数组的元素
        Object data = object[0];
        
        Object[] dataArray = getJsonToArray(data.toString());
        
        ArrayList<String> dataList = new ArrayList<String>();

        for(int i=0;i<dataArray.length;i++){            
            dataList.add(dataArray[i].toString());
        }
        
        //获得第二个数组的元素
        Object data2 = object[1];
        
        Object[] dataArray2 = getJsonToArray(data2.toString());
        
        for(int i=0;i<dataArray2.length;i++){            
            dataList.add(dataArray2[i].toString());
        }
        
		//将list装换为Json数组（JSONArray）
		String json = JSONArray.fromObject(dataList).toString(); 		
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();	
    }
	
	/**
     * 从json数组中得到相应java数组
     * JSONArray下的toArray()方法的使用
     * @param dataArray
     * @return
     */
     public static Object[] getJsonToArray(Object dataArray) {
         JSONArray jsonArray = JSONArray.fromObject(dataArray);         
         return jsonArray.toArray();
     }

}
