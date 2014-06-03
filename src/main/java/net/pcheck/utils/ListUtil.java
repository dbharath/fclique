package net.pcheck.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ListUtil {
	
	public static <V, K> Map<String,List<V>> group(List<V> list, String field) {
		 
		HashMap<String,K> index= new HashMap<String,K>();
		HashMap<K,List<V>> groupedData= new HashMap<K, List<V>>();
		Method m=null;
		try {
			V obj=list.get(0);
			m = obj.getClass().getMethod("get"+StringUtils.getFirstUpper(field));
		} catch (NoSuchMethodException ex) {
			Logger.getLogger(ListUtil.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		//iterate through the list of objects passed as parameter
		for (V object : list) {
			K columnVal=null;
			try {
				columnVal = (K) m.invoke(object, null);
                List<V> group=groupedData.get(index.get(columnVal.toString()));
                if(group==null){
                	group= new ArrayList<V>();
                	groupedData.put(columnVal, group);
                	index.put(columnVal.toString(), columnVal);
                }
                //add the object to the group
                if(!group.contains(object)){
                	group.add(object);
                }
                

            } catch (Exception ex) {
                Logger.getLogger(ListUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
		}
		return (Map<String, List<V>>) groupedData;
	}

}
