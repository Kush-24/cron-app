package com.example.cron.cmd.tool;
import java.util.*;

public class CronParser {
    public static void main(String[] args) {
        //String str="*/15 0 1,15 * 1-5";
        //String str="* * * 1,2,5 *";
        // String[] fields=str.split("\\s+");
         //It should expect only 5 standard cron expression

        System.out.println("cronExpression:"+args[0]);
        String cronExpression = args[0];
        if (!validateCronExpression(cronExpression)) {
            System.out.println("Invalid cron expression. Please provide a valid cron expression.");
            return;
        }
        String[] fields = cronExpression.split("\\s+");
        String[] labels = {"minute", "hour", "day of month", "month", "day of week", "command"};
        String[] results = new String[labels.length];

        //fetch all fields one by one and make the result
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            String label = labels[i];
            String result = parseField(field, label);
            results[i] = result;
        }

        // Print output in table format
        for (int i = 0; i < labels.length; i++) {
            System.out.printf("%-14s%s\n", labels[i], results[i]);
        }
    }

    /*
     * Field i.e * 4 9 
     * Label used to identify the field belongs to which format. 
     */
    private static String parseField(String field, String label) {
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
    private static boolean validateCronExpression(String cronExpression) {
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


}
