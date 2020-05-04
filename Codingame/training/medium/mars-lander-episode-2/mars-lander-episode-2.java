/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/mars-lander-episode-2
*/

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static int xFlatInit;
    private static int xFlatFinal;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the number of points used to draw the surface of Mars.

        int lastY = -1;
        int lastX = 0;
        for (int i = 0; i < N; i++) {
            int LAND_X = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int LAND_Y = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            if(lastY == LAND_Y) {
                xFlatInit = lastX;
                xFlatFinal = LAND_X;
            }
            lastY = LAND_Y;
            lastX = LAND_X;

        }

        // game loop
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int HS = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int VS = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int F = in.nextInt(); // the quantity of remaining fuel in liters.
            int R = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int P = in.nextInt(); // the thrust power (0 to 4).


            //HARD-CODED METHOD - TODOS A HEURÍSTICA FOI EMPÍRICA, BASEADO EM POUCOS DADOS TÉCNICOS.

            //21º com Thrust 4 é o ângulo máximo que consigo usar para deslocar a nave até o flat
            //ground com aceleração vertical = 0 (anulando com a gravidade)
            int overFlatArea = overFlatArea(X);
            if(overFlatArea == -1) {
                if(HS < 45) {
                   System.out.println("-21 4");
                } else {
                    System.out.println("21 4");
                }
            } else if(overFlatArea == 1) {
                if(HS > -45) {
                   System.out.println("21 4");
                } else {
                    System.out.println("-21 4");
                }
            } else {
                System.err.println(Y + " " + HS + " " + VS);
                if(HS > 20) {
                    System.out.println("50 4");
                } else if(HS < -20) {
                    System.out.println("-50 4");
                } else if(HS > 1) {
                    System.out.println("15 4");
                } else if(HS < -1) {
                    System.out.println("-15 4");
                }else if(HS > 0) {
                    System.out.println("5 3");
                } else if(HS < 0) {
                    System.out.println("-5 3");
                } else if(HS == 0) {
                    if(VS >= -34) {
                        System.out.println("0 0");
                    } else {
                        System.out.println("0 4");
                    }

                }
            }
        }
    }

    private static int overFlatArea(int x) {
        if(x > xFlatFinal - 30) {
            return 1;
        } else if(x < xFlatInit + 30) {
            return -1;
        }
        return 0;
    }
