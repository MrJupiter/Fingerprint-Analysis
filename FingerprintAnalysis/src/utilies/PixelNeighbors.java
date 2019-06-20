package utilies;

import java.util.ArrayList;

public class PixelNeighbors {

	public static ArrayList<Integer> getNeighbors(int[][] pixelsArray ,int row, int column, int height, int width) {
		ArrayList<Integer> neighborPixels = new ArrayList<>();
		
		if(row > 0 && row < height-1 && column > 0 && column < width-1) {
			neighborPixels.add(pixelsArray[row-1][column]);
			neighborPixels.add(pixelsArray[row-1][column+1]);
			neighborPixels.add(pixelsArray[row][column+1]);
			neighborPixels.add(pixelsArray[row+1][column+1]);
			neighborPixels.add(pixelsArray[row+1][column]);
			neighborPixels.add(pixelsArray[row+1][column-1]);
			neighborPixels.add(pixelsArray[row][column-1]);
			neighborPixels.add(pixelsArray[row-1][column-1]);
			neighborPixels.add(pixelsArray[row-1][column]);
		}
		
		if(row == 0) {
			if(column == 0) {
				neighborPixels.add(pixelsArray[row][column+1]);
				neighborPixels.add(pixelsArray[row+1][column+1]);
				neighborPixels.add(pixelsArray[row+1][column]);
			}else if(column == width-1) {
				neighborPixels.add(pixelsArray[row+1][column]);
				neighborPixels.add(pixelsArray[row+1][column-1]);
				neighborPixels.add(pixelsArray[row][column-1]);
			}else {
				neighborPixels.add(pixelsArray[row][column+1]);
				neighborPixels.add(pixelsArray[row+1][column+1]);
				neighborPixels.add(pixelsArray[row+1][column]);
				neighborPixels.add(pixelsArray[row+1][column-1]);
				neighborPixels.add(pixelsArray[row][column-1]);
			}
		}
		
		if(row == height-1) {
			if(column == 0) {
				neighborPixels.add(pixelsArray[row-1][column]);
				neighborPixels.add(pixelsArray[row-1][column+1]);
				neighborPixels.add(pixelsArray[row][column+1]);
			}else if(column == width-1) {
				neighborPixels.add(pixelsArray[row-1][column]);
				neighborPixels.add(pixelsArray[row][column-1]);
				neighborPixels.add(pixelsArray[row-1][column-1]);
				neighborPixels.add(pixelsArray[row-1][column]);
			}else {
				neighborPixels.add(pixelsArray[row-1][column]);
				neighborPixels.add(pixelsArray[row-1][column+1]);
				neighborPixels.add(pixelsArray[row][column+1]);
				neighborPixels.add(pixelsArray[row][column-1]);
				neighborPixels.add(pixelsArray[row-1][column-1]);
				neighborPixels.add(pixelsArray[row-1][column]);
			}
		}
		
		if(column == 0 && row != height-1 && row != 0) {
			neighborPixels.add(pixelsArray[row-1][column]);
			neighborPixels.add(pixelsArray[row-1][column+1]);
			neighborPixels.add(pixelsArray[row][column+1]);
			neighborPixels.add(pixelsArray[row+1][column+1]);
			neighborPixels.add(pixelsArray[row+1][column]);
		}
		
		if(column == width-1 && row != height-1 && row != 0) {
			neighborPixels.add(pixelsArray[row-1][column]);
			neighborPixels.add(pixelsArray[row+1][column]);
			neighborPixels.add(pixelsArray[row+1][column-1]);
			neighborPixels.add(pixelsArray[row][column-1]);
			neighborPixels.add(pixelsArray[row-1][column-1]);
			neighborPixels.add(pixelsArray[row-1][column]);
		}
		
		return neighborPixels;
	}
}
