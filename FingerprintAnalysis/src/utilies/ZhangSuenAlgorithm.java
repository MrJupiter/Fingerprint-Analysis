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
 
                    int counter = 0;
                    
                    for (int i = 0; i < nbrs.length - 1; i++)
                        if (grid[r + nbrs[i][1]][c + nbrs[i][0]] == '0')
                        	counter++;
                    
                    if (counter < 2 || counter > 6)
                        continue;
                    
                    int whiteToBlackTransitionsNumber = 0;
                    
                    for (int i = 0; i < nbrs.length - 1; i++)
                        if (grid[r + nbrs[i][1]][c + nbrs[i][0]] == '1' && grid[r + nbrs[i + 1][1]][c + nbrs[i + 1][0]] == '0')
                            	whiteToBlackTransitionsNumber++;
                    
                    if (whiteToBlackTransitionsNumber != 1)
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