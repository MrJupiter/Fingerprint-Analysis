package imageProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class ImageConvertor {
	 protected String pathInputImage;
	 protected BufferedImage image = null;
	 protected int width;
	 protected int height;
	 
	 public abstract BufferedImage convertToGrayscale() throws Exception;
	 public abstract BufferedImage convertToBinary(int threshold) throws Exception;

	 public ImageConvertor() {
	 
	 }
	 
	 public ImageConvertor(BufferedImage bufferedImage) {
		 image = bufferedImage;
	 }
	 
	 protected BufferedImage readImage() throws Exception {
		 File file = new File(pathInputImage);
		 String fileExtension = getFileExtension(file);
		 if (fileExtension.equalsIgnoreCase(".jpeg") || fileExtension.equalsIgnoreCase(".png")) {
			 image = ImageIO.read(file);
			 width = image.getWidth();
		     height = image.getHeight();
		  }
		 return image;
	 }
	 
	 protected static String getFileExtension(File file) throws Exception {
		 String extension = "";
		 if (file != null && file.exists()) {
			 String name = file.getName();
			 extension = name.substring(name.lastIndexOf("."));
		 }
		 return extension;
	 }
	 
	public void setPathInputImage(String pathInputImage) {
		this.pathInputImage = pathInputImage;
	}
	 

}