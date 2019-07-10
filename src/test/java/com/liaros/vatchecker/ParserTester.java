package com.liaros.vatchecker;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

class ParserTester {

	@Test
	void testValidHttpRequest() {
		Parser parser = new Parser();
		Object[] countries = parser.parseFromURL("http://jsonvat.com/");
		assertNotNull("parser returned null object", countries);
	}
	
	@Test
	void testInValidHttpRequest() {
		Parser parser = new Parser();
		Object[] countries = parser.parseFromURL("thisisnotaurl");
		assertNull("parser did not return null object", countries);
	}

}
