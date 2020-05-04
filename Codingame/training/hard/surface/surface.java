/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/surface
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Solution {

    private static char[][] map;
    private static int width;
    private static int height;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        width = in.nextInt();
        height = in.nextInt();
        map = new char[height][width];

        for (int i = 0; i < height; i++) {
            String row = in.next();
            char[] cRow = row.toCharArray();
            for(int j = 0; j < cRow.length; j++) {
                map[i][j] = cRow[j];
            }
        }

        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int X = in.nextInt();
            int Y = in.nextInt();
            if(map[Y][X] == '#') {
                System.out.println(0);
            } else {
                int answer = detectRiverSurface(Y, X);
                System.out.println(answer);
            }
        }
    }

    private static int detectRiverSurface(int y, int x) {
        int[][] mapRiver = new int[height][width];
        List<Point> points = new ArrayList<Point>();
        int pos = 0;
        mapRiver[y][x] = 1;
        points.add(new Point(y, x));
        while(pos != points.size()) {
            Point p = points.get(pos);
            if(p.x!=(width-1) && map[p.y][p.x+1] == 'O' && mapRiver[p.y][p.x+1] != 1) {
                points.add(new Point(p.y, p.x+1));
                mapRiver[p.y][p.x+1] = 1;
            }
            if(p.x!=0 && map[p.y][p.x-1] == 'O' && mapRiver[p.y][p.x-1] != 1) {
                points.add(new Point(p.y, p.x -1));
                mapRiver[p.y][p.x-1] = 1;
            }
            if(p.y!=0 && map[p.y-1][p.x] == 'O' && mapRiver[p.y-1][p.x] != 1) {
                points.add(new Point(p.y-1, p.x));
                mapRiver[p.y-1][p.x] = 1;
            }
            if(p.y!=(height-1) && map[p.y+1][p.x] == 'O' && mapRiver[p.y+1][p.x] != 1) {
                points.add(new Point(p.y+1, p.x));
                mapRiver[p.y+1][p.x] = 1;
            }
            pos++;
        }
        return pos;
    }
}

class Point {
    int x;
    int y;
    Point(int y, int x) {
        this.x = x;
        this.y = y;
    }
}