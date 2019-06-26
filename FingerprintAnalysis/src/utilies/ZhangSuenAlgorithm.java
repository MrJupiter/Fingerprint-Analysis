package utilies;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.awt.Point;
import java.util.*;
 
public class ZhangSuenAlgorithm {
 
	final int[][] nbrs = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1},{-1, 1}, {-1, 0}, {-1, -1}, {0, -1}};
    final int[][][] nbrGroups = {{{0, 2, 4}, {2, 4, 6}}, {{0, 2, 6},{0, 4, 6}}};
    private List<Point> toWhite = new ArrayList<>();
    private char[][] grid;
 
    public void setGrid(char[][] grid) {
		this.grid = grid;
	}

    public char[][] thinImage() throws Exception{
        boolean firstStep = false;
        boolean hasChanged;
 
        do {
            hasChanged = false;
            firstStep = !firstStep;
 
            for (int r = 1; r < grid.length - 1; r++) {
                for (int c = 1; c < grid[0].length - 1; c++) {
 
                    if (grid[r][c] != '0')
                        continue;
 
                    int nn = blackNeighborPixelsNumber(r, c);
                    if (nn < 2 || nn > 6)
                        continue;
 
                    if (whiteToBlackTransitionsNumber(r, c) != 1)
                        continue;
 
                    if (!atLeastOneIsWhite(r, c, firstStep ? 0 : 1))
                        continue;
 
                    toWhite.add(new Point(c, r));
                    hasChanged = true;
                }
            }
 
            for (Point p : toWhite)
                grid[p.y][p.x] = '1';
            toWhite.clear();
 
        } while (firstStep || hasChanged);
        return grid;
    }
 
    private int blackNeighborPixelsNumber(int row, int column) {
        int counter = 0;
        for (int i = 0; i < nbrs.length - 1; i++)
            if (grid[row + nbrs[i][1]][column + nbrs[i][0]] == '0')
            	counter++;
        return counter;
    }
 
    private int whiteToBlackTransitionsNumber(int row, int column) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++)
            if (grid[row + nbrs[i][1]][column + nbrs[i][0]] == '1') {
                if (grid[row + nbrs[i + 1][1]][column + nbrs[i + 1][0]] == '0')
                    count++;
            }
        return count;
    }
 
    private boolean atLeastOneIsWhite(int row, int column, int step) {
        int count = 0;
        int[][] group = nbrGroups[step];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < group[i].length; j++) {
                int[] nbr = nbrs[group[i][j]];
                if (grid[row + nbr[1]][column + nbr[0]] == '1') {
                    count++;
                    break;
                }
            }
        return count > 1;
    }

}