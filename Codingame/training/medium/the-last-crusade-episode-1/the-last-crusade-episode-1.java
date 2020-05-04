/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/the-last-crusade-episode-1
*/

import java.util.*;
import java.io.*;
import java.math.*;


class Player {

    private static int[][] board;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        in.nextLine();
        board = new int[H][W];
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            String[] types = LINE.split(" ");
            for(int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(types[j]);
            }
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
        in.nextLine();

        int onType;
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();
            in.nextLine();

            onType = board[YI][XI];

            if( onType == 1 ||
                onType == 3 ||
                onType == 7 ||
                onType == 8 ||
                onType == 9 ||
                onType == 12 ||
                onType == 13) {

                System.out.println( XI + " " + (YI+1));

            } else if( onType == 2) {
                if("RIGHT".equals(POS)) {
                    System.out.println( (XI-1) + " " + (YI));
                } else {
                    System.out.println( (XI+1) + " " + (YI));
                }
            } else if( onType == 4) {
                if("TOP".equals(POS)) {
                    System.out.println( (XI-1) + " " + (YI));
                } else if("RIGHT".equals(POS)) {
                    System.out.println( (XI) + " " + (YI+1));
                } else {
                    System.err.println("Shouldn't get here: " + POS + " " + onType);
                }
            } else if( onType == 5) {
                if("TOP".equals(POS)) {
                    System.out.println( (XI+1) + " " + (YI));
                } else if("LEFT".equals(POS)) {
                    System.out.println( (XI) + " " + (YI+1));
                } else {
                    System.err.println("Shouldn't get here: " + POS + " " + onType);
                }
            } else if( onType == 6) {
                if("RIGHT".equals(POS)) {
                    System.out.println( (XI-1) + " " + (YI));
                } else if("LEFT".equals(POS)) {
                    System.out.println( (XI+1) + " " + (YI));
                } else {
                    System.err.println("Shouldn't get here: " + POS + " " + onType);
                }
            } else if( onType == 10) {
                if("TOP".equals(POS)) {
                    System.out.println( (XI-1) + " " + (YI));
                } else {
                    System.err.println("Shouldn't get here: " + POS + " " + onType);
                }
            } else if( onType == 11) {
                if("TOP".equals(POS)) {
                    System.out.println( (XI+1) + " " + (YI));
                } else {
                    System.err.println("Shouldn't get here: " + POS + " " + onType);
                }
            }
        }
    }
}