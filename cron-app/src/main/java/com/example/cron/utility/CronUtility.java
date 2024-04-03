package com.example.cron.utility;

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
	    
	    public static int getLowerBound(String label) {
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

	    public static int getUpperBound(String label) {
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
