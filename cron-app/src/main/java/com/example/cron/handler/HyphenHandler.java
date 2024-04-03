package com.example.cron.handler;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
class HyphenHandler implements FieldHandler {
	
	public HyphenHandler() {
		FieldHandlerRegistory.addHandler(this,"-");
	}
	
    @Override
    public String parseString(String field, String label) {
        // Handle hyphen field
        Set<Integer> values = new TreeSet<>();
        String[] range = field.split("-");
        int start = Integer.parseInt(range[0]);
        int end = Integer.parseInt(range[1]);
        for (int i = start; i <= end; i++) {
            values.add(i);
        }
        return values.stream().map(e->String.valueOf(e)).collect(Collectors.joining(" "));
    }
    
}