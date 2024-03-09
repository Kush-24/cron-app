package com.example.tambola.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tambola.entiry.TambolaUserCard;
import com.example.tambola.utility.TambolaUtility;

@Service
public class TambolaServiceImpl implements TambolaService{

	@Override
	public TambolaUserCard generateCustomTambolaCard() {
		TambolaUserCard userCard=new TambolaUserCard();
		List<List<Integer>> card=TambolaUtility.generateTambolaCard();
		TambolaUtility.printTambolaCard(card);
		if(card!=null) {
			userCard.setTambolaCard(card);
			List<String> strs=generateCardInStringFormat(card);
			userCard.setTambolaCardInString(strs);
		}
		return userCard;
	}

	private List<String> generateCardInStringFormat(List<List<Integer>> card) {
		List<String> strn=new ArrayList<>();
		for(int i=0;i<card.size();i++) {
			StringBuilder sb=new StringBuilder();
			for(int j=0;j<card.get(0).size();j++) {
				 sb.append(card.get(i).get(j));
				if(j!=card.get(0).size()-1)
				 sb.append("__");
			}
			strn.add(sb.toString());
		}
		System.out.println(strn);
		return strn;
	}

}
