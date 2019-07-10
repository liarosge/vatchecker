package com.liaros.vatchecker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.liaros.vatchecker.model.Country;
import com.liaros.vatchecker.model.Period;
import com.liaros.vatchecker.model.Rate;

class VatCheckerTester {

	@Test
	void testOneCountry() {
		Country c1 = initCountry("Australia", new Date[] { new Date() }, new int[] { 10 });

		Country countries[] = new Country[] { c1 };
		String output = VatChecker.solutionB(countries);
		assertEquals("MAX:\nAustralia\t10\nMIN:\nAustralia\t10\n", output);

	}

	@Test
	void testTwoCountries() {
		Country c1 = initCountry("Australia", new Date[] { new Date() }, new int[] { 10 });
		Country c2 = initCountry("New Zealand", new Date[] { new Date() }, new int[] { 20 });

		Country countries[] = new Country[] { c1, c2 };
		String output = VatChecker.solutionB(countries);
		assertEquals("MAX:\n" + 
				"New Zealand\t20\n" + 
				"Australia\t10\n" + 
				"MIN:\n" + 
				"Australia\t10\n" + 
				"New Zealand\t20\n", output);
	}

	@Test
	void testFiveCountries() {
		Country c1 = initCountry("Australia", new Date[] { new Date() }, new int[] { 10 });
		Country c2 = initCountry("New Zealand", new Date[] { new Date() }, new int[] { 20 });
		Country c3 = initCountry("South Africa", new Date[] { new Date() }, new int[] { 30 });
		Country c4 = initCountry("Argentina", new Date[] { new Date() }, new int[] { 40 });
		Country c5 = initCountry("India", new Date[] { new Date() }, new int[] { 50 });

		Country countries[] = new Country[] { c1, c2, c3, c4, c5 };
		String output = VatChecker.solutionB(countries);
		assertEquals("MAX:\n" + 
				"India\t50\n" + 
				"Argentina\t40\n" + 
				"South Africa\t30\n" + 
				"MIN:\n" + 
				"Australia\t10\n" + 
				"New Zealand\t20\n" + 
				"South Africa\t30\n", output);
	}

	@Test
	void testWithMultipleDates() {
		Country c1 = initCountry("Australia", new Date[] { new Date(), new Date(0) }, new int[] { 10, 20 });
		Country c2 = initCountry("New Zealand", new Date[] { new Date(0), new Date() }, new int[] { 20, 15 });
		Country c3 = initCountry("South Africa", new Date[] { new Date(0), new Date(1000), new Date(100000) },
				new int[] { 15, 16, 17 });
		Country c4 = initCountry("Argentina", new Date[] { new Date(10000), new Date(1000), new Date(5000) },
				new int[] { 19, 20, 21 });
		Country c5 = initCountry("India", new Date[] { new Date(50000), new Date(30000), new Date(10000) }, new int[] { 17,18,19 });
		Country countries[] = new Country[] { c1, c2, c3, c4, c5 };
		String output = VatChecker.solutionB(countries);
		assertEquals("MAX:\n" + 
				"Argentina\t19\n" + 
				"South Africa\t17\n" + 
				"India\t17\n" + 
				"MIN:\n" + 
				"Australia\t10\n" + 
				"New Zealand\t15\n" + 
				"South Africa\t17\n", output);
	}

	private Country initCountry(String name, Date[] dates, int[] rates) {
		Country country = new Country();
		country.name = name;

		Period[] periods = new Period[dates.length];
		for (int i = 0; i < dates.length; i++) {
			periods[i] = new Period();
			periods[i].effective_from = dates[i];
			Rate rate = new Rate();
			rate.standard = rates[i];
			periods[i].rates = rate;
		}
		country.periods = periods;

		return country;
	}

}
