package com.example.cron.handler;
import java.util.*;

import org.springframework.stereotype.Component;

import com.example.cron.utility.CronUtility;

@Component
class SlashHandler implements FieldHandler {
	
	public SlashHandler() {
		FieldHandlerRegistory.addHandler(this,"/");
	}
	
    @Override
    public String parseString(String field, String label) {
        // Handle slash field
        Set<String> values = new TreeSet<>();
        String[] parts = field.split("/");
        int start = CronUtility.getLowerBound(label);
        int interval = Integer.parseInt(parts[1]);
        for (int i = start; i <= CronUtility.getUpperBound(label); i += interval) {
            values.add(Integer.toString(i));
        }
        return String.join(" ", values);
    }
    
}