package com.jason.json.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.json.entity.Employee;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		List<Employee> list = new ArrayList<Employee>();		
		Employee employee = new Employee();
		employee.setName("Joy");
		employee.setSex("Female");
		employee.setAge("23");				
		list.add(employee);		
		JSONArray jsonArray = JSONArray.fromObject(employee);
		System.out.println(jsonArray.toString());
		
		//将list装换为Json数组（JSONArray）
		Object json = JSONArray.fromObject(jsonArray).toString();	
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);		
	}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
		/*String array = req.getParameter("array");
        
        Object[] object = getJsonToArray(array);
        
        Object data = object[0];
        
        Object[] dataArray = getJsonToArray(data.toString());
        
        System.out.println(dataArray);
        
        if (dataArray.length==0) {
			System.out.println("空");
		}
        
        Object[] dataArray = null;
        
        ArrayList<String> dataList = new ArrayList<String>();
        
        dataArray = getJsonToArray(data.toString());

        for(int i=0;i<dataArray.length;i++){            
            dataList.add(dataArray[i].toString());
        }
        
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println("dataList = "+dataList.get(i));
        } */
        
        req.setCharacterEncoding("UTF-8");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
		}
		//将list装换为Json数组（JSONArray）
		String json = JSONArray.fromObject(list).toString(); 		
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
