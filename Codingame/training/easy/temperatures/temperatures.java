/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/temperatures/
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the number of temperatures to analyse
        in.nextLine();

        if(N == 0) {
            System.out.println(0);
        } else {
            String TEMPS = in.nextLine(); // the N temperatures expressed as integers ranging from -273 to 5526
            String[] temperatures = TEMPS.split(" ");

            int lowest = 9999;
            for(String t : temperatures) {
                int tInt = Integer.parseInt(t);

                if(Math.abs(tInt) <= Math.abs(lowest)) {
                    if(tInt + lowest == 0) {
                        lowest = Math.abs(lowest);
                    } else {
                        lowest = tInt;
                    }

                }
            }
            System.out.println(lowest);
        }


    }
}