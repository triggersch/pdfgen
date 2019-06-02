package com.pdfgen;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;

public class PDFBuilder extends PdfDrawer<Content> {

		
	public PDFBuilder(PDDocument pdDocument) {
		super(pdDocument);
	}
	
	public PDFBuilder(PDDocument pdDocument, float offset) {
		super(pdDocument, offset);
	}

	
	@Override
	protected void addCadre(Content content){
		
		
		Row<PDPage> row = baseTable.createRow(25f);
		Cell<PDPage> cell = row.createCell(50, content.getRow1());
		setBorders(cell, true, true, true, false);
		
		row = baseTable.createRow(25f);
		cell = row.createCell(50, content.getRow2());
		setBorders(cell, true, true, false, false);
		
		row = baseTable.createRow(50f);
		cell = row.createCell(50, content.getRow3());
		setBorders(cell, true, true, false, true);
	
		
	
	}
	
	@Override
	protected void setBorders(Cell<PDPage> cell, boolean left, boolean right, boolean top, boolean bottom){
		if(!left){
			cell.setLeftBorderStyle(null);
		}
		if(!right){
			cell.setRightBorderStyle(null);
		}
		if(!top){
			cell.setTopBorderStyle(null);
		}
		if(!bottom){
			cell.setBottomBorderStyle(null);
		}
	}
	
	@Override
	protected Row<PDPage> addpaddingRow(float minimHeight){
		
		Row<PDPage> paddinRow = baseTable.createRow(Math.min(minimHeight, 20f));
		Cell<PDPage> cell = paddinRow.createCell(50, "");
		cell.setLeftBorderStyle(null);
		cell.setTopBorderStyle(null);
		//cell.setRightBorderStyle(null);
		
		return paddinRow;
		
	}
	
	public void writeHolePage() throws IOException{
		
		Row<PDPage> row = baseTable.createRow(309.99f);
		Cell<PDPage> cell = row.createCell(50, "this is a very big cell");
		baseTable.draw();
		
	}
}
