package com.liaros.vatchecker;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.liaros.vatchecker.model.Country;

public class Parser {
	//"http://jsonvat.com/";
	
	public Parser() {
		
	}
	
	public Country[] parseFromURL(String url) {
		Country[] countries;
		try (InputStream is = new URL(url).openStream();
				Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
			Gson gson = new Gson();
			JsonObject object = gson.fromJson(reader, JsonObject.class);
			JsonElement element = object.get("rates");
			countries = gson.fromJson(element, Country[].class);
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
			return null;
		} catch (IOException e) {
			System.err.println("Error parsing JSON");
			e.printStackTrace();
			return null;
		}
		return countries;
	}
	
	
}
