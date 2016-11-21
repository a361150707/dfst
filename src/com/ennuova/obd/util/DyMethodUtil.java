package com.ennuova.obd.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl2.Expression;  
import org.apache.commons.jexl2.JexlContext;  
import org.apache.commons.jexl2.JexlEngine;  
import org.apache.commons.jexl2.MapContext;  

import com.ennuova.entity.CnSsclxx;
  
/** 
 * 动态加载方法 
 * @author wangyfc 
 * 
 */  
public class DyMethodUtil {  
      
    public static Object invokeMethod(String jexlExp,Map<String,Object> map){  
        JexlEngine jexl=new JexlEngine();  
        Expression e = jexl.createExpression(jexlExp);  
        JexlContext jc = new MapContext();  
        for(String key:map.keySet()){  
            jc.set(key, map.get(key));  
        }  
        if(null==e.evaluate(jc)){  
            return "";  
        }  
        return e.evaluate(jc);  
    }  
    
    public static void main(String[] args) {
    	
    	CnSsclxx cnSsclxx=new CnSsclxx();
    	
    	//System.out.println("aaa");
    	Map<String,Object> map=new HashMap<String,Object>();  
    	map.put("cnSsclxx",cnSsclxx);  
    	map.put("value","2");  
    	String expression="cnSsclxx.setF1(value)";  
    	DyMethodUtil.invokeMethod(expression,map);  
    	System.out.println(cnSsclxx.getF1());
	}
  
}  