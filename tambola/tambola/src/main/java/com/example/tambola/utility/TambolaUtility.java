package com.example.tambola.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TambolaUtility {
    private static Random random = new Random();

    public static List<List<Integer>> generateTambolaCard() {
        List<List<Integer>> tambolaCard = new ArrayList<>();

        //for tracking that rows should contains unique numbers. 
        Set<Integer> usedNumbers = new HashSet<>();
        for (int row = 0; row < 3; row++) {
            List<Integer> currentRow = new ArrayList<>();

            for (int col = 0; col < 9; col++) {
                int startNum = col==0 ? 1 : col * 10;
                int endNum = startNum + 9;
                int randomNumber;
                do {
                    randomNumber = random.nextInt(endNum - startNum) + startNum;
                } while (usedNumbers.contains(randomNumber)); // Ensure uniqueness within the row
                currentRow.add(randomNumber);
                usedNumbers.add(randomNumber);
            }
            System.out.println("crrntrow: "+currentRow);
            tambolaCard.add(currentRow);
        }
        //set all rows 4 numbers zero, while validating all the rules
        setRowZero(tambolaCard,4);
        return tambolaCard;
    }
    
    private static void setRowZero(List<List<Integer>> tambolaCard, int num) {
    	for(int in=0;in<3;in++) {
    		int i=0;
    		List<Integer> row=tambolaCard.get(in);
    		Set<Integer> set=new HashSet<>();
    		while(i < num) {
    			int ind=random.nextInt(9); //get random index (0-9)
    			if(set.contains(ind)) continue;
    			//validate if previous row for this ind is zero or not
    			if(in==2 && tambolaCard.get(0).get(ind) == 0 && tambolaCard.get(1).get(ind) ==0) continue;
    			  row.set(ind, 0);
    			  set.add(ind);
    			  i++; //for maintaining how many number we made zero
    		}
    	}
	}

	 public static void printTambolaCard(List<List<Integer>> tambolaCard) {
	        for (List<Integer> row : tambolaCard) {
	            for (int number : row) {
	                System.out.printf("%2d ", number);
	            }
	            System.out.println();
	        }
	    }
}
