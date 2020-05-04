/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/ascii-art/
*/

import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        in.nextLine();
        int H = in.nextInt();
        in.nextLine();
        String T = in.nextLine();
        T = T.toUpperCase();

        int ASCII_A = 'A';
        int ASCII_Z = 'Z';
        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            String answer = "";
            for(char c : T.toCharArray()) {
                int ascii = c;
                if(ascii >= ASCII_A && ascii <= ASCII_Z) {
                    answer = answer + ROW.substring((ascii - ASCII_A) * L, (ascii - ASCII_A) * L + L);
                } else {
                    answer = answer + ROW.substring((ASCII_Z - ASCII_A + 1) * L);
                }
            }
            System.out.println(answer);
        }
    }
}