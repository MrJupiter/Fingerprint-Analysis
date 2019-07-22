package utilies;
import java.awt.image.BufferedImage; 
import java.io.File; 

import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.rendering.PDFRenderer;  

public class ExtractImageFromPdf {  
   public static BufferedImage PDF2BufferedImage(String pdfPath) throws Exception{
	      PDDocument document = PDDocument.load(new File(pdfPath)); 
	      PDFRenderer renderer = new PDFRenderer(document);  
	      BufferedImage image = renderer.renderImage(0);  
	      document.close();
	      return image;
   }
   
}