package com.example.tambola.entiry;

import java.util.List;

public class TambolaUserCard {
	List<List<Integer>> tambolaCard;
	List<String> tambolaCardInString;
	public List<List<Integer>> getTambolaCard() {
		return tambolaCard;
	}
	public void setTambolaCard(List<List<Integer>> tambolaCard) {
		this.tambolaCard = tambolaCard;
	}
	public List<String> getTambolaCardInString() {
		return tambolaCardInString;
	}
	public void setTambolaCardInString(List<String> tambolaCardInString) {
		this.tambolaCardInString = tambolaCardInString;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TambolaUserCard [tambolaCardInString=");
		builder.append(tambolaCardInString);
		builder.append("]");
		return builder.toString();
	}
	
}
