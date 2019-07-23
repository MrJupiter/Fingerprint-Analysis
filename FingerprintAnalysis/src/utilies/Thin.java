package utilies;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;

import imageProcessing.ImageConvertor;
import imageProcessing.ImageProcessing;

public class Thin {
	private String imagePath = "";
	private BufferedImage bufferedImageBefore = null;
	private BufferedImage bufferedImageAfter = null;
	private int height;
	private int width;
	private char[][] pixelsArrayBefore;
	private char[][] pixelsArrayAfter;
	private int threshold;

	public Thin(String imagePath, int threshold) {
		this.imagePath = imagePath;
		this.threshold = threshold;
		loadBufferedImage();
	}

	private void loadBufferedImage() {
		try {
			ImageConvertor imageConvertor = new ImageProcessing();
			imageConvertor.setPathInputImage(imagePath);
			bufferedImageBefore = imageConvertor.convertToBinary(threshold);
			height = bufferedImageBefore.getHeight();
			width = bufferedImageBefore.getWidth();
			pixelsArrayBefore = new char[height][width];

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color color = new Color(bufferedImageBefore.getRGB(j, i));
					if (color.getBlue() == 255 && color.getRed() == 255 && color.getGreen() == 255) pixelsArrayBefore[i][j] = '1';
					else if (color.getBlue() == 0 && color.getRed() == 0 && color.getGreen() == 0) pixelsArrayBefore[i][j] = '0';
					else System.out.println("No!");
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public BufferedImage thinImage() throws Exception {
		ZhangSuenAlgorithm zhangSuen = new ZhangSuenAlgorithm();
		zhangSuen.setGrid(pixelsArrayBefore);
		pixelsArrayAfter = zhangSuen.thinImage();
		bufferedImageAfter = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		WritableRaster raster = bufferedImageAfter.getRaster();
		for (int h = 0; h < height; h++)
		for (int w = 0; w < width; w++)
		raster.setSample(w, h, 0, pixelsArrayAfter[h][w]);
		return bufferedImageAfter;
	}

	public void displayPixelsBefore() throws Exception {
		FileWriter fWriter = new FileWriter(new File("D:\\Users\\Malik\\Desktop\\pBefore.txt"));
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++)
			fWriter.write("" + pixelsArrayBefore[i][j] + " ");
			fWriter.write("\n");
		}
		fWriter.close();
	}

	public void displayPixelsAfter() throws Exception {
		FileWriter fWriter = new FileWriter(new File("D:\\Users\\Malik\\Desktop\\pAfter.txt"));
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++)
			fWriter.write("" + pixelsArrayAfter[i][j] + " ");
			fWriter.write("\n");
		}
		fWriter.close();
	}

}