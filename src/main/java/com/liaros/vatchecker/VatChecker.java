package com.liaros.vatchecker;

import java.util.Arrays;
import java.util.Comparator;

import com.liaros.vatchecker.model.Country;

public class VatChecker {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Country[] countries = parser.parseFromURL("http://jsonvat.com/");

		solutionA(countries);
		System.out.println("--------");
		solutionB(countries);
		
	}

	public static String solutionA(Country[] countries) {
		Comparator<Country> countryComparator = new Comparator<Country>() {

			@Override
			public int compare(Country o1, Country o2) {
				Arrays.sort(o1.periods);
				Arrays.sort(o2.periods);
				return Integer.compare(o1.periods[o1.periods.length - 1].getStandardRate(),
						o2.periods[o2.periods.length - 1].getStandardRate());
			}
		};
		Arrays.sort(countries, countryComparator);
		String output = "";
		for (int i = 0; i < Math.min(countries.length, 3) ; i ++) {
			output += countries[i]+"\n";
		}
		for (int i = countries.length -1; i > Math.max(-1,countries.length -4); i--) {
			output += countries[i]+"\n";
		}
		System.out.print(output);
		return output;
	}

	public static String solutionB(Country[] countries) {
		
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		int min3 = Integer.MAX_VALUE;
		String min1CountryName = "";
		String min2CountryName = "";
		String min3CountryName = "";
		
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;
		String max1CountryName = "";
		String max2CountryName = "";
		String max3CountryName = "";
		
		for(Country country : countries) {
			int vat = country.getCurrentVATRate();
			if(vat < min1) {
				min3 = min2;
				min3CountryName = min2CountryName;
				min2 = min1;
				min2CountryName = min1CountryName;
				min1 = vat;
				min1CountryName = country.name;
			}
			else if(vat < min2) {
				min3 = min2;
				min3CountryName = min2CountryName;
				min2 = vat;
				min2CountryName = country.name;
			}
			else if(vat < min3) {
				min3 = vat;
				min3CountryName = country.name;
			}
			if(vat > max1) {
				max3 = max2;
				max3CountryName = max2CountryName;
				max2 = max1;
				max2CountryName = max1CountryName;
				max1 = vat;
				max1CountryName = country.name;
			}
			else if(vat > max2) {
				max3 = max2;
				max3CountryName = max2CountryName;
				max2 = vat;
				max2CountryName = country.name;
			}
			else if(vat > max3) {
				max3 = vat;
				max3CountryName = country.name;
			}
		}
		String output = "MAX:\n";
		if(max1!=Integer.MIN_VALUE)
			output += max1CountryName + "\t" + max1 + "\n";
		if(max2!=Integer.MIN_VALUE)
			output += max2CountryName + "\t" + max2 + "\n";
		if(max3!=Integer.MIN_VALUE)
			output += max3CountryName + "\t" + max3 + "\n";
		output += "MIN:\n";
		if(min1!=Integer.MAX_VALUE)
			output += min1CountryName + "\t" + min1 + "\n";
		if(min2!=Integer.MAX_VALUE)
			output += min2CountryName + "\t" + min2 + "\n";
		if(min3!=Integer.MAX_VALUE)
			output += min3CountryName + "\t" + min3 + "\n";
		System.out.print(output);
		return output;
	}

}
