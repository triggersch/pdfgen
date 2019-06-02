package com.pdfgen;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;

public abstract class PdfDrawer<T> {
	
	protected BaseTable baseTable;
	private PDDocument pdDocument;
	private PDRectangle pdRectangle;
	private float pageHeight;
	private float offset;
	
	
	public PdfDrawer(PDDocument pdDocument, float offset) {
		super();
		this.pdDocument = pdDocument;
		pdRectangle = new PDRectangle(200f, 310f);
		this.offset = offset;
	}


	public PdfDrawer(PDDocument pdDocument) {
		this(pdDocument,0f);
	}
	
	public void initDocument(){
		PDPage pdPage = new PDPage(pdRectangle);
		pdDocument.addPage(pdPage);
		pageHeight = pdPage.getMediaBox().getHeight();
		try {
			baseTable = new BaseTable(pageHeight - offset, pageHeight, 0, 200f, 0f, pdDocument, pdPage, true, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void saveDocument(String path){
		
		try {
			pdDocument.save(path);
			pdDocument.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeInDocument(List<T> list) throws IOException{
		
		float heihtBefore = 0;
		float heightAfter = 0;
		int nbrOfPageBefore  = 0;
		float epsilon = 0;
		Row<PDPage> paddinRow = null;
		for (T ob : list) {
			heihtBefore = baseTable.getHeaderAndDataHeight() + offset + epsilon;		
			nbrOfPageBefore =  new Float(heihtBefore / pageHeight).intValue() + 1;
	
			paddinRow  = addpaddingRow(20f);
			addCadre(ob);
			
			heightAfter = baseTable.getHeaderAndDataHeight() + offset + epsilon;		
			
			float deltaCadre = heightAfter - heihtBefore;
			float deltaTablePage = nbrOfPageBefore * pageHeight - heihtBefore;
			
			if(deltaTablePage < deltaCadre){
				float delta = nbrOfPageBefore * pageHeight - heihtBefore ;
				
				paddinRow.getCells().stream().forEach(c -> c.setHeight(delta -0.1f));
				paddinRow.setHeight(delta -0.1f);
				epsilon += 0.1f;

			}
		}
		
		System.out.println("base drow " + baseTable.draw());
		
	}
	
	protected abstract void addCadre(T object);
	
	protected abstract void setBorders(Cell<PDPage> cell, boolean left, boolean right, boolean top, boolean bottom);
	
	protected abstract Row<PDPage> addpaddingRow(float minimHeight);
	
}
