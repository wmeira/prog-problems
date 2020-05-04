/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/mars-lander-episode-1/
*/


import java.util.*;
import java.io.*;
import java.math.*;


class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt(); // the number of points used to draw the surface of Mars.
        for (int i = 0; i < N; i++) {
            int LAND_X = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int LAND_Y = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars
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

            if(VS >= -39) {
                System.out.println("0 0");
            } else {
                System.out.println("0 4");
            }

        }
    }
}