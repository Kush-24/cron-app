package com.example.cron.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class CronUtility {
     public static String[] labels = {"minute", "hour", "day of month", "month", "day of week", "command"};

	 public static boolean validateCronExpression(String cronExpression) {
		 
		     String[] fields = cronExpression.split("\\s+");
	        
	        if (fields.length != 5) {
	            return false; // Incorrect number of fields
	        }

	        // Validate each field
	        for (String field : fields) {
	            if (!isValidField(field)) {
	                return false;
	            }
	        }

	        return true;
	    }
	    private static boolean isValidField(String field) {
	        // Validate if the field contains valid characters
	        for (char c : field.toCharArray()) {
	            if (!(Character.isDigit(c) || c == '*' || c == ',' || c == '-' || c == '/')) {
	                return false;
	            }
	        }
	        return true;
	    }
	    
	    /*
	     * Field i.e * 4 9 
	     * Label used to identify the field belongs to which format. 
	     */
	    public static String parseField(String field, String label) {
	        StringBuilder result = new StringBuilder();
	        Set<String> values = new TreeSet<>();

	        if (field.equals("*")) {
	        	int startingPoint=getLowerBound(label);
	        	int endPoint=getUpperBound(label);
	            for (int i = startingPoint; i <=endPoint ; i++) {
	                values.add(Integer.toString(i));
	            }
	        } else if (field.contains(",")) {
	        	// if 1,15 means 1 or 15
	            String[] parts = field.split(",");
	            for (String part : parts) {
	                if (part.contains("/")) {
	                    String[] range = part.split("/");
	                    int start = Integer.parseInt(range[0]);
	                    int interval = Integer.parseInt(range[1]);
	                    for (int i = start; i <= getUpperBound(label); i += interval) {
	                        values.add(Integer.toString(i));
	                    }
	                } else {
	                    values.add(part);
	                }
	            }
	        } else if (field.contains("-")) {
	            String[] range = field.split("-");
	            int start = Integer.parseInt(range[0]);
	            int end = Integer.parseInt(range[1]);
	            for (int i = start; i <= end; i++) {
	                values.add(Integer.toString(i));
	            }
	        } else if (field.contains("/")) {
	            String[] parts = field.split("/");
	            int start = getLowerBound(label);
	            int interval = Integer.parseInt(parts[1]);
	            for (int i = start; i <= getUpperBound(label); i += interval) {
	                values.add(Integer.toString(i));
	            }
	        } else {
	            values.add(field);
	        }

	        // Construct the result string
	        for (String value : values) {
	            result.append(value).append(" ");
	        }

	        return result.toString().trim();
	    }

	    private static int getLowerBound(String label) {
	        if (label.equals("minute") || label.equals("hour")) {
	            return 0;
	        } else if (label.equals("day of month")) {
	            return 1;
	        } else if (label.equals("month")) {
	            return 1;
	        } else if (label.equals("day of week")) {
	            return 0;
	        }
	        return -1;
	    }

	    private static int getUpperBound(String label) {
	        if (label.equals("minute")) {
	            return 59;
	        } else if (label.equals("hour")) {
	            return 23;
	        } else if (label.equals("day of month")) {
	            return 31;
	        } else if (label.equals("month")) {
	            return 12;
	        } else if (label.equals("day of week")) {
	            return 7;
	        }
	        return -1;
	    }
}
