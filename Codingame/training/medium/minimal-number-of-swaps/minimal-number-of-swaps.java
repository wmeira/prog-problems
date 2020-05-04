/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/minimal-number-of-swaps/
*/


import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int x[] = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
        }

        int counter = 0;
        for(int i = 0; i < n; i++) {
            if(x[i] == 0) {
                for(int j = n - 1; j > i; j--) {
                    if(x[j] == 1) {
                        x[i] = 1;
                        x[j] = 0;
                        counter++;
                        break;
                    }

                }
            }
        }
        System.out.println(counter);
    }
}