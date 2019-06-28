package com.pdfedit;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFFiller {
	
	
	public void drawLines(List<PDFLine> lines, PDPageContentStream cs ){
		try {
			cs.setLineWidth(5);
		for(PDFLine l: lines){
			addline(l, cs);
		}
			cs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addline(PDFLine l, PDPageContentStream cs) throws IOException {
		cs.moveTo(l.getxFrom(), l.getyFrom());
		cs.lineTo(l.getxTo(), l.getyTo());
		cs.stroke();
	}

}
