package imageProcessing;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class ImageProcessing extends ImageConvertor{

	public ImageProcessing() {
		super();
	}
	
    public BufferedImage convertToGrayscale() throws Exception{
    	readImage();
        BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = img.getRaster();
        WritableRaster rasterJPEG = image.getRaster();
        for(int h=0; h<height; h++)
            for(int w=0; w<width; w++) {
                int[] p = new int[4];
                rasterJPEG.getPixel(w, h, p);
                p[0] = (int) (0.3 * p[0]);
                p[1] = (int) (0.59 * p[1]);
                p[2] = (int) (0.11 * p[2]);
                int y = p[0] + p[1] + p[2];
                raster.setSample(w,h,0,y);
            }
        return img;
    }

    public BufferedImage convertToBinary(int threshold) throws Exception{
    	readImage();
        BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_BINARY);
        WritableRaster raster = img.getRaster();
        WritableRaster rasterPB = convertToGrayscale().getRaster();
        //int threshold = 45;
        for(int h=0;h<height;h++){
            for(int w=0;w<width;w++) {
                int[] p = new int[1];
                rasterPB.getPixel(w, h, p);
                raster.setSample(w, h, 0, (p[0] > threshold)?1:0);
            }
        }
        return img;
    }

}
