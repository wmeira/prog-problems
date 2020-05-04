/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/bender-episode-1
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static char[][] graph;
    private static int L;
    private static int C;

    private static List<String> prioritiesSet = Arrays.asList("SOUTH", "EAST", "NORTH", "WEST");
    private static boolean breakerMode = false;
    private static int xBender;
    private static int yBender;
    private static int xNextBender;
    private static int yNextBender;
    private static String dirBender = "SOUTH";

    private static List<String> moves = new ArrayList<String>();

    private static final char START = '@';
    private static final char SUICIDE = '$';
    private static final char WALL = '#';
    private static final char OBSTACLE = 'X';
    private static final char SOUTH = 'S';
    private static final char NORTH = 'N';
    private static final char EAST = 'E';
    private static final char WEST = 'W';
    private static final char INVERTER = 'I';
    private static final char BEER = 'B';
    private static final char TELEPORTER = 'T';
    private static final char EMPTY = ' ';


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        L = in.nextInt();
        C = in.nextInt();
        in.nextLine();
        graph = new char[L][C];
        for (int i = 0; i < L; i++) {
            String row = in.nextLine();
            addRowToGraph(i, row.toCharArray());
        }

        char square = graph[xBender][yBender];
        int round = 0;
        while(square != SUICIDE) {
             if(++round >= L*C*3) {
                moves.clear();
                moves.add("LOOP");
                break;
            }
            executeModifiers(square);
            executeDirectionModifiers(square);
            calculateNextMovement();
            xBender = xNextBender;
            yBender = yNextBender;
            square = graph[xBender][yBender];
            moves.add(dirBender);
            //System.err.println(xBender + " " + yBender + " " + dirBender);
        }
        printMoves();
    }

    private static void addRowToGraph(int i, char[] r) {
        for(int j = 0; j < C; j++) {
            graph[i][j] = r[j];
            if(r[j] == START) {
                xBender = i;
                yBender = j;
            }
        }
    }

    private static void executeModifiers(char square) {
        if(square == BEER) breakerMode = !breakerMode;
        else if(square == INVERTER) Collections.reverse(prioritiesSet);
        else if(square == TELEPORTER) teleportBender();
    }

    private static void executeDirectionModifiers(char square) {
        if(square == SOUTH) dirBender = "SOUTH";
        else if(square == NORTH) dirBender = "NORTH";
        else if(square == EAST) dirBender = "EAST";
        else if(square == WEST) dirBender = "WEST";
    }

    private static void teleportBender() {
        for(int i = 0; i < L; i++) {
            for(int j = 0; j < C; j++) {
                if(graph[i][j] == TELEPORTER) {
                    if(xBender != i || yBender != j) {
                        xBender = i;
                        yBender = j;
                        return;
                    }
                }
            }
        }
    }

    private static void updateNextPositionGivenDirection(String direction) {
        if("SOUTH".equals(direction)) {
            xNextBender = xBender + 1;
            yNextBender = yBender;
        }
        else if("NORTH".equals(direction)) {
            xNextBender = xBender - 1;
            yNextBender = yBender;
        }
        else if("EAST".equals(direction)) {
            yNextBender = yBender + 1;
            xNextBender = xBender;
        }
        else {
            yNextBender = yBender - 1;
            xNextBender = xBender;
        }
    }

    private static void calculateNextMovement() {
        updateNextPositionGivenDirection(dirBender);
        char nextSquare = graph[xNextBender][yNextBender];
        if(nextSquare == OBSTACLE && breakerMode) {
            graph[xNextBender][yNextBender] = EMPTY;
            nextSquare = EMPTY;
        }
        if(nextSquare == WALL || nextSquare == OBSTACLE) {
            choosePriorityDirection();
        }
    }

    private static void choosePriorityDirection() {
        for(int i = 0; i < prioritiesSet.size(); i++) {
            String dirTest = prioritiesSet.get(i);
            updateNextPositionGivenDirection(dirTest);
            char nextSquare = graph[xNextBender][yNextBender];
            if(nextSquare != WALL && nextSquare != OBSTACLE) {
                dirBender = dirTest;
                return;
            }
        }
    }

    private static void printMoves() {
        for(String s : moves) {
            System.out.println(s);
        }
    }
}
