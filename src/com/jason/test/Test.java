package com.jason.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

/**
 * @ClassName: Test
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Jason.XW.Li@pccw.com
 * @date 2014-7-25 下午3:33:01
 *
 */
public class Test {
	public static void main(String[] args) {
		
		/*Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		for (int i = 0; i < 8; i++) {
			map.put("a"+i, "a"+i);
		}
		
		String items[] = {"a0","a1","c2","d3","e4","f5","a6","h7"};
		
       int l = 0;   
    	for (int k = 0; k < items.length; k++) { 
            for (int i = 0; i < map.size(); i++) {
				if (items[k].equals(map.get("a"+i))) {
					dataMap.put("a"+l, map.get("a"+i));
					l++;
					break;
				}
			}
            
        }*/
    	
		/*System.out.println("l = " + l);
		System.out.println("dataMap = " + dataMap.toString());
		System.out.println("dataMap = " + dataMap.size());*/
		
		//stringSplit();
		
		/*ArrayList<String> templateReplace = new ArrayList<String>();
		
		templateReplace.add("1");
		templateReplace.add("2");
		templateReplace.add("3");
		
		ArrayList<String> templateOriginal = new ArrayList<String>();
		
		templateOriginal.add("A");
		templateOriginal.add("B");
		templateOriginal.add("C");
		
		String[][] replacementClientMail = new String[templateReplace.size()][2];
		
		for (int i = 0; i < templateReplace.size(); i++) {
			for (int j = 0; j < templateOriginal.size(); j++) {				
				replacementClientMail[i][0] = templateReplace.get(i);
				replacementClientMail[j][1] = templateOriginal.get(j);				
			}
		}  			
      
		for (int i = 0; i < replacementClientMail.length; i++) {
			System.out.println(replacementClientMail[i][0]);
			System.out.println(replacementClientMail[i][1]);
		}
		
		String replacementClientMail[][] = {
				{"#customer.domain", "domain"},
				{"#customer.f_name", "firstname"},
				{"#customer.l_name", "lastname"},
				{"#customer.account_name", "account"},
				{"#customer.username", "username"},
				{"#customer.company_Ename", "companynameE"},
				{"#customer.company_Cname", "companynameC"},
				{"#customer.company_address", "companyAddr"},
				{"#customer.billing_address", "billingAddr"},				
				{"#customer.email", "email"},
				{"#customer.phone", "tel"}
		};
		
		for (int i = 0; i < replacementClientMail.length; i++) {
			System.out.println(replacementClientMail[i][0]);
			System.out.println(replacementClientMail[i][1]);
		}
		
		ArrayList<String> list = new ArrayList<String>();
		
		System.out.println(list.size());
		System.out.println(list.isEmpty());*/
		
		int length = 4;
		
		for (int i = 0; i < length;) {
			System.out.print(i+" ");
			if ((i+1)<length) {
				System.out.print((i+1)+" ");
			}
			if ((i+2)<length) {
				System.out.print(i+2);
			}
			System.out.println();
			i += 3;						
		}
            
	}
	
	public static void stringSplit(){
		String accountIdAndUuid = "sdsds-23w2w2";
		String[] strArr = accountIdAndUuid.split("-");
		String accountId =strArr[0];
		String uuid = strArr[1];
		
		System.out.println(accountId+":"+uuid);
	}
	
	
}
