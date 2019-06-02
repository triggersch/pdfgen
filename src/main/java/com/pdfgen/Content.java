package com.pdfgen;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

public class Content {
	
	private String row1;
	private String row2;
	private String row3;
	
	public Content() {
		row1 = generateRandomString();
		row2 = generateRandomString();
		row3 = generateRandomString();
	}
	
	public String getRow1() {
		return row1;
	}
	public String getRow2() {
		return row2;
	}
	public String getRow3() {
		return row3;
	}
	public void setRow1(String row1) {
		this.row1 = row1;
	}
	public void setRow2(String row2) {
		this.row2 = row2;
	}
	public void setRow3(String row3) {
		this.row3 = row3;
	}
	
	private String generateRandomString(){
		int size = new Random().nextInt(100) + 1;
	    String generatedString = RandomStringUtils.randomAlphabetic(size);
	    return generatedString;
	}

}
