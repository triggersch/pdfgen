package com.pdfgen;

public class Content {
	
	private String row1;
	private String row2;
	private String row3;
	
	public Content() {
		row1 = "this is a cell1 with very long text that you cannot now how long it is";
		row2 = "this is a cell2";
		row3 = "this is a cell3";
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

}
