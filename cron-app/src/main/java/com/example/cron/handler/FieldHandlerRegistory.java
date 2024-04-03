package com.example.cron.handler;

import java.util.HashMap;
import java.util.Map;


public class FieldHandlerRegistory {
	static	Map<String,FieldHandler> fieldRegistoryMap=new HashMap<>();
	
	public static void addHandler(FieldHandler handler,String key) {
		if(!fieldRegistoryMap.containsKey(key)) {
			fieldRegistoryMap.put(key, handler);
		}
	}
	
	private static Map<String,FieldHandler> getFieldRegistry(){
		return fieldRegistoryMap;
	}
	
	public static String runHandlers(String field, String label) {
		if (field.equals("*")) {
        	return getFieldRegistry().get("*").parseString(field, label);
        } else if (field.contains(",")) {
        	return getFieldRegistry().get(",").parseString(field, label);

        } else if (field.contains("-")) {
        	return getFieldRegistry().get("-").parseString(field, label);

        } else if (field.contains("/")) {
        	return getFieldRegistry().get("/").parseString(field, label);
        } 
		return field;
	}
}
