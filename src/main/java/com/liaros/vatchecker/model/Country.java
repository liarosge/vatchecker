package com.liaros.vatchecker.model;

public class Country{
	public String name;
	public String code;
	public String country_code;
	public Period[] periods;

	public int getCurrentVATRate(){
		Period latestPeriod = periods[0];
		for (int i = 1; i < periods.length; i++) {
			if(periods[i].compareTo(latestPeriod)==1) {
				latestPeriod = periods[i];
			}
		}
		return latestPeriod.getStandardRate();
	}
	
	@Override
	public String toString() {
		return name + "\t" + getCurrentVATRate();
	}
}
