/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/vox-codei-episode-1/
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Player {

	private static final int EMPTY = 0;
	private static final int SURVEILLANCE = 1;
	private static final int WALL = 2;
	private static final int PROHIBITED = 3;
	private static int[][] grid;
	private static int[][] gridOriginal;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int width = in.nextInt(); // width of the firewall grid
		int height = in.nextInt(); // height of the firewall grid
		gridOriginal = new int[height][width];
		grid = new int[height][width];
		for (int i = 0; i < height; i++) {
			String mapRow = in.next(); // one line of the firewall grid
			char[] cRow = mapRow.toCharArray();
			for (int j = 0; j < width; j++) {
				gridOriginal[i][j] = charToInt(cRow[j]);
			}
		}

		int rounds = in.nextInt(); // number of rounds left before the end of the game
		int bombs = in.nextInt(); // number of bombs left
		int bombsLeft = bombs;
		List<String> movements = new ArrayList<String>();
		while(true) {
		    bombsLeft = bombs;
		    movements.clear();
		    resetGrid();
		    while(hasSurveillance()) {
		        addRound();
		        Explosion ex = findBestExplosion();
		        for(int k = grid[ex.y][ex.x]; k < 0; k++) {
				    movements.add("WAIT");
			        addRound();
			    }
			    movements.add(ex.x + " " + ex.y);
			    bombsLeft--;
			    clearSurveillance(ex.y, ex.x);
		    }
		    if(bombsLeft < 0) {
		        prohibitFirstBombPlace(movements.get(0));
		    } else {
		        break;
		    }
		}

		int round = 0;
		while (true) {
			if(round < movements.size()) {
				System.out.println(movements.get(round));
			} else {
				System.out.println("WAIT");
			}
			round++;
			rounds = in.nextInt(); // number of rounds left before the end of the game
			bombs = in.nextInt(); // number of bombs left
		}
	}

	private static int charToInt(char c) {
		switch (c) {
		case '.':
			return EMPTY;
		case '@':
			return SURVEILLANCE;
		case '#':
			return WALL;
		}
		return -1;
	}

	private static void resetGrid() {
	    for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = gridOriginal[i][j];
            }
        }
	}

	private static Explosion findBestExplosion() {
	    Explosion ex = new Explosion(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	    int bestExplostion = 0;
	    for(int i = 0; i < grid.length; i++) {
	        for(int j = 0; j < grid[i].length; j++) {
	            if(grid[i][j] <= 0) {
	                int bombs = bombsInRange(i, j);
	                if(bombs > bestExplostion) {
	                    bestExplostion = bombs;
	                    ex = new Explosion(i, j, bombs);
	                }
	            }
	        }
	    }
	    return ex;
	}

	private static void prohibitFirstBombPlace(String s) {
	    String[] ss = s.split(" ");
	    gridOriginal[Integer.parseInt(ss[1])][Integer.parseInt(ss[0])] = PROHIBITED;
	}

	private static int bombsInRange(int y, int x) {
		int bombs = 0;
		for (int i = y - 1; i >= y - 3; i--) {
			if (i < 0) break;
			if (grid[i][x] == WALL) break;
			if (grid[i][x] == SURVEILLANCE) bombs++;
		}
		for (int i = y + 1; i <= y + 3; i++) {
			if (i >= grid.length) break;
			if (grid[i][x] == WALL) break;
			if (grid[i][x] == SURVEILLANCE) bombs++;
		}
		for (int i = x - 1; i >= x - 3; i--) {
			if (i < 0) break;
			if (grid[y][i] == WALL) break;
			if (grid[y][i] == SURVEILLANCE) bombs++;
		}
		for (int i = x + 1; i <= x + 3; i++) {
			if (i >= grid[0].length) break;
			if (grid[y][i] == WALL) break;
			if (grid[y][i] == SURVEILLANCE) bombs++;
		}
		return bombs;
	}

	private static void clearSurveillance(int y, int x) {
		for (int i = y - 1; i >= y - 3; i--) {
			if (i < 0) break;
			if (grid[i][x] == WALL) break;
			if (grid[i][x] == SURVEILLANCE) grid[i][x] = -3;
		}
		for (int i = y + 1; i <= y + 3; i++) {
			if (i >= grid.length) break;
			if (grid[i][x] == WALL) break;
			if (grid[i][x] == SURVEILLANCE) grid[i][x] = -3;
		}
		for (int i = x - 1; i >= x - 3; i--) {
			if (i < 0) break;
			if (grid[y][i] == WALL) break;
			if (grid[y][i] == SURVEILLANCE) grid[y][i] = -3;
		}
		for (int i = x + 1; i <= x + 3; i++) {
			if (i >= grid[0].length) break;
			if (grid[y][i] == WALL) break;
			if (grid[y][i] == SURVEILLANCE) grid[y][i] = -3;
		}
	}

	private static void addRound() {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] < 0) grid[i][j]++;
            }
        }
	}

	private static boolean hasSurveillance() {
	    for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1 ) return true;
            }
        }
        return false;
	}
}

class Explosion {
	int y;
	int x;
	int bombsDestroyed;

	Explosion(int y, int x, int bombsDestroyed) {
		this.y = y;
		this.x = x;
		this.bombsDestroyed = bombsDestroyed;
	}
}