package net.fclique.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.util.ClassUtil;

public class Utils {
	
	public static Map<String, String> getQueryMap(String query)  {  
	    String[] params = query.split("&");  
	    Map<String, String> map = new HashMap<String, String>();  
	    for (String param : params)  
	    {  
	    	String [] keyValues = param.split("=");
	    	String name = keyValues[0]; 
	    	String value = "";
	    	if(keyValues.length >1) {
	    		value = keyValues[1];  
	    	}	        
	        map.put(name, value);  
	    }  
	    return map;  
	}  
	
	public static String generateProductId(){
		Random lengthGenerator = new Random();
		
		Integer randomLength = 17 - lengthGenerator.nextInt(3);

		StringBuffer sb = new StringBuffer();
		for (int i = randomLength; i > 0; i -= 12) {
			int n = Math.min(12, Math.abs(i));
			sb.append(StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
		}

		String productId = sb.toString().trim().toUpperCase();
		return productId;
	}
	

}
