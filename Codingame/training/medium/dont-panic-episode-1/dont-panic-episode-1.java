/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/don't-panic-episode-1
*/

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static int[][] board;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        board = new int[nbFloors][width];

        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            board[elevatorFloor][elevatorPos] = 1;
        }

        int posElevator;

        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT
            System.err.println(cloneFloor + " " + clonePos);

            if(cloneFloor == -1 || clonePos == -1) {
                System.out.println("WAIT");
                continue;
            }

            posElevator = posElevatorFloor(cloneFloor, clonePos);
            if(!hasMarvinBlockingFloor(cloneFloor)) {
                if(exitFloor != cloneFloor) {
                    if((posElevator == -1 && "RIGHT".equals(direction)) || (posElevator == 1 && "LEFT".equals(direction))) {
                        board[cloneFloor][clonePos] = 2;
                        System.out.println("BLOCK");
                    } else {
                        System.out.println("WAIT");
                    }
                } else {
                    if((exitPos < clonePos && "RIGHT".equals(direction)) || (exitPos > clonePos && "LEFT".equals(direction))) {
                        board[cloneFloor][clonePos] = 2;
                        System.out.println("BLOCK");
                    } else {
                        System.out.println("WAIT");
                    }
                }
            } else {
                System.out.println("WAIT"); // action: WAIT or BLOCK
            }
        }
    }

    private static boolean hasMarvinBlockingFloor(int floor) {
        for(int i = 0;  i < board[floor].length; i++) {
            if(board[floor][i] == 2) {
                return true;
            }
        }
        return false;
    }

    private static int posElevatorFloor(int floor, int pos) {
        for(int i = 0;  i < board[floor].length; i++) {
            if(board[floor][i] == 1) {
                if(i < pos) return -1;
                else if(i > pos) return 1;
                else return 0;
            }
        }
        return 0;
    }
}