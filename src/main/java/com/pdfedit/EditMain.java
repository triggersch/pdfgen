package com.pdfedit;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

public class EditMain {

	public static void main(String[] args) throws IOException {
		
		File file = FileUtils.getFile("/tmp/in.pdf");
		
		PDDocument pdDocument = PDDocument.load(file);
		PDPageContentStream cs = new PDPageContentStream(pdDocument, pdDocument.getPage(0),
				                                          AppendMode.APPEND, false);
		
		PDPage page = pdDocument.getPage(0);
		
		cs.setLineWidth(5);
		//cs.drawLine(75, 230, 100, 260);
		cs.moveTo(68, page.getMediaBox().getHeight() - 393);
		cs.lineTo(300, page.getMediaBox().getHeight() -  490);
		cs.stroke();
		cs.close();
		
		pdDocument.save("/tmp/out.pdf");
		


	}

}
