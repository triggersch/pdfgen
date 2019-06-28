package com.pdfedit;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import com.google.common.collect.Lists;

public class DrawMain {

	public static void main(String[] args) throws IOException {
		
File file = FileUtils.getFile("/tmp/in.pdf");
		
		PDDocument pdDocument = PDDocument.load(file);
		PDPageContentStream cs = new PDPageContentStream(pdDocument, pdDocument.getPage(0),
				                                          AppendMode.APPEND, false);
		
		Pave pave = new Pave(true, true, true, false);
		
		PDFLine line = new PDFLine(10f, 20f, 20f, 40f);
		PDFLine line2 = new PDFLine(20f, 20f, 30f, 40f);
		PDFLine line3 = new PDFLine(30f, 20f, 40f, 40f);
		PDFLine line4 = new PDFLine(40f, 20f, 50f, 40f);
		
		List<PDFLine> lines = Lists.newArrayList();
 		if(pave.isB1()){
			lines.add(line);
		}
 		if(pave.isB2()){
 			lines.add(line2);
 		}
 		if(pave.isB3()){
			lines.add(line3);
		}
 		if(pave.isB4()){
 			lines.add(line4);
 		}
 		
 		new PDFFiller().drawLines(lines, cs);

 		pdDocument.save("/tmp/out.pdf");
	}

}
