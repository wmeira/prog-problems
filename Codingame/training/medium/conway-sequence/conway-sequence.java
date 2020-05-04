/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/conway-sequence/
*/

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int L = in.nextInt();

        List<Integer> actual = new ArrayList<Integer>();
        actual.add(R);
        List<Integer> next;

        int n; //actual number
        int q; //quantity of actual number
        for(int i = 1; i < L; i++) {
            next = new ArrayList<Integer>();
            n = actual.get(0);
            q = 0;
            for(int j = 0; j < actual.size(); j++) {
                if(n != actual.get(j)) {
                    next.add(q);
                    next.add(n);
                    n = actual.get(j);
                    q = 1;
                } else {
                    q++;
                }
            }
            next.add(q);
            next.add(n);
            actual = next;
        }
        printOutput(actual);
    }

    private static void printOutput(List<Integer> list) {
        String output = "";
        for(int i = 0; i < list.size(); i++) {
            output += list.get(i) + " ";
        }
        System.out.println(output.trim());
    }
}