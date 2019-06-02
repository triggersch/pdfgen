package com.pdfgen;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.google.common.collect.Lists;


public class PDFGenMain {

	public static void main(String[] args) {
		
		//PDFBuilder pdfBuilder = new PDFBuilder(new PDDocument());
		PdfDrawer<Content> pdfBuilder = new PDFBuilder(new PDDocument(), 50f);
		List<Content> contents = generateContents();
 		pdfBuilder.initDocument();
		try {
			pdfBuilder.writeInDocument(contents);
			//pdfBuilder.writeHolePage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tempPath = System.getProperty("java.io.tmpdir");
		pdfBuilder.saveDocument(tempPath + "/out.pdf");
		System.out.println("done ! : " + tempPath + "/out.pdf");

	}

	private static List<Content> generateContents() {
		List<Content> res = Lists.newArrayList();
		for (int i = 0; i < 11; i++) {
			res.add(new Content());
		}
		return res;
	}

}
