package com.example.cron.handler;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.cron.utility.CronUtility;

@Component
class CommaHandler implements FieldHandler {
	
	public CommaHandler() {
		FieldHandlerRegistory.addHandler(this,",");
	}
	
    @Override
    public String parseString(String field, String label) {
        // Handle comma field
    	Set<Integer> values = new TreeSet<>();
         
    	String[] parts = field.split(",");
        for (String part : parts) {
            if (part.contains("/")) {
                String[] range = part.split("/");
                int start = Integer.parseInt(range[0]);
                int interval = Integer.parseInt(range[1]);
                for (int i = start; i <= CronUtility.getUpperBound(label); i += interval) {
                    values.add(i);
                }
            } else {
                values.add(Integer.parseInt(part));
            }
        }
       
        return values.stream().map(e->String.valueOf(e)).collect(Collectors.joining(" "));
    }
    
}