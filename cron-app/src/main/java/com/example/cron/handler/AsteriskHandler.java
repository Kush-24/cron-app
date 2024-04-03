package com.example.cron.handler;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.cron.utility.CronUtility;

@Component
class AsteriskHandler implements FieldHandler {
	
	public AsteriskHandler() {
		FieldHandlerRegistory.addHandler(this,"*");
	}
	
    @Override
    public String parseString(String field, String label) {
        // Handle asterisk field
        Set<Integer> values = new TreeSet<>();
        int start = CronUtility.getLowerBound(label);
        int end = CronUtility.getUpperBound(label);
        for (int i = start; i <= end; i++) {
            values.add(i);
        }
        return values.stream().map(e->String.valueOf(e)).collect(Collectors.joining(" "));
    }
    
}