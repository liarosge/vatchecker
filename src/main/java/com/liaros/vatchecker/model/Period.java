package com.liaros.vatchecker.model;

import java.util.Date;

public class Period implements Comparable<Period>{
	public Date effective_from;
	public Rate rates;
	
	public int getStandardRate() {
		return rates.standard;
	}

	@Override
	public int compareTo(Period o) {
		return this.effective_from.compareTo(o.effective_from);
	}
}
