package com.pdfgen;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;

public class PDFBuilder {
	
	private BaseTable baseTable;
	private PDDocument pdDocument;
	private PDRectangle pdRectangle;
	private float pageHeight;
	private float offset;
		
	public PDFBuilder(PDDocument pdDocument) {
		this(pdDocument,0f);
	}
	
	public PDFBuilder(PDDocument pdDocument, float offset) {
		this.pdDocument = pdDocument;
		pdRectangle = new PDRectangle(200f, 310f);
		this.offset = offset;
	}

	public void initDocument(){
		PDPage pdPage = new PDPage(pdRectangle);
		pdDocument.addPage(pdPage);
		pageHeight = pdPage.getMediaBox().getHeight();
		try {
			baseTable = new BaseTable(pageHeight - offset, pageHeight, 0, 200f, 0f, pdDocument, pdPage, true, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeInDocument() throws IOException{

		float heihtBefore = 0;
		float heightAfter = 0;
		int nbrOfPageBefore  = 0;
		float epsilon = 0;
		Row<PDPage> paddinRow = null;
		for (int i = 0; i < 30; i++) {
			heihtBefore = baseTable.getHeaderAndDataHeight() + offset + epsilon;		
			nbrOfPageBefore =  new Float(heihtBefore / pageHeight).intValue() + 1;
	
			paddinRow  = addpaddingRow(20f);
			addCadre();
			
			heightAfter = baseTable.getHeaderAndDataHeight() + offset + epsilon;		
			
			float deltaCadre = heightAfter - heihtBefore;
			System.out.println("deltaCadre " + deltaCadre);
			float deltaTablePage = nbrOfPageBefore * pageHeight - heihtBefore;
			System.out.println("deltaTablePage " + deltaTablePage);
			
			if(deltaTablePage < deltaCadre){
				float delta = nbrOfPageBefore * pageHeight - heihtBefore ;
				System.out.println("hauteur cadreInséré " + delta);
				
				paddinRow.getCells().stream().forEach(c -> c.setHeight(delta -0.1f));
				paddinRow.setHeight(delta -0.1f);
				epsilon += 0.1f;

			}
		}
		
		System.out.println("base drow " + baseTable.draw());
		
	}
	
	public void saveDocument(String path){
		
		try {
			pdDocument.save(path);
			pdDocument.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void addCadre(){
		
		
		Row<PDPage> row = baseTable.createRow(25f);
		Cell<PDPage> cell = row.createCell(50, "this is a cell1 with very long text that you cannot now how long it is");
		setBorders(cell, true, true, true, false);
		
		row = baseTable.createRow(25f);
		cell = row.createCell(50, "this is a cell2");
		setBorders(cell, true, true, false, false);
		
		row = baseTable.createRow(50f);
		cell = row.createCell(50, "this is a cell3");
		setBorders(cell, true, true, false, true);
	
		
	
	}
	
	private void setBorders(Cell<PDPage> cell, boolean left, boolean right, boolean top, boolean bottom){
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
	
	private Row<PDPage> addpaddingRow(float minimHeight){
		
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
