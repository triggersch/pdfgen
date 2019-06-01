package com.pdfgen;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;


public class PDFGenMain {

	public static void main(String[] args) {
		
		//PDFBuilder pdfBuilder = new PDFBuilder(new PDDocument());
		PDFBuilder pdfBuilder = new PDFBuilder(new PDDocument(), 50f);
		pdfBuilder.initDocument();
		try {
			pdfBuilder.writeInDocument();
			//pdfBuilder.writeHolePage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pdfBuilder.saveDocument("/tmp/out.pdf");
		System.out.println("done !");

	}

}
