/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/shadows-of-the-knight-episode-1/
*/

import java.util.*;
import java.io.*;
import java.math.*;


class Player {

    private static int xMin;
    private static int xMax;
    private static int yMin;
    private static int yMax;

    private static int xB;
    private static int yB;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        xB = X0;
        yB = Y0;
        xMin = 0;
        xMax = W-1;
        yMin = 0;
        yMax = H-1;

        // game loop
        while (true) {
            String BOMB_DIR = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            updateConstraints(BOMB_DIR);
            updateBatmanPosition();
            System.out.println( (xB) + " " + (yB)); // the location of the next window Batman should jump to.
        }

    }

    private static void updateConstraints(String bombDirection) {
        if("U".equals(bombDirection)) {
            yMax = yB - 1;
            xMin = xB;
            xMax = xB;
        } else if("UR".equals(bombDirection)) {
            yMax = yB - 1;
            xMin = xB + 1;
        } else if("R".equals(bombDirection)) {
            xMin = xB + 1;
            yMin = yB;
            yMax = yB;
        } else if("DR".equals(bombDirection)) {
            yMin = yB + 1;
            xMin = xB + 1;
        } else if("D".equals(bombDirection)) {
            yMin = yB + 1;
            xMin = xB;
            xMax = xB;
        } else if("DL".equals(bombDirection)) {
            yMin = yB + 1;
            xMax = xB - 1;
        } else if("L".equals(bombDirection)) {
            xMax = xB - 1;
            yMin = yB;
            yMax = yB;
        } else if("UL".equals(bombDirection)) {
            yMax = yB - 1;
            xMax = xB - 1;
        }

    }

    private static void updateBatmanPosition() {
        xB = (xMax + xMin)/2;
        yB = (yMax + yMin)/2;
    }
}