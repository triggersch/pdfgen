package com.pdfedit;

public class PDFLine {
	
	private Float xFrom;
	private Float yFrom;
	
	private Float xTo;
	private Float yTo;
	
	public PDFLine(Float xFrom, Float yFrom, Float xTo, Float yTo) {
		super();
		this.xFrom = xFrom;
		this.yFrom = yFrom;
		this.xTo = xTo;
		this.yTo = yTo;
	}
	
	public Float getxFrom() {
		return xFrom;
	}
	public Float getyFrom() {
		return yFrom;
	}
	public Float getxTo() {
		return xTo;
	}
	public Float getyTo() {
		return yTo;
	}
}
