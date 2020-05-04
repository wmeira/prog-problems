/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/horse-racing-duals
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Integer> strenghts = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            int Pi = in.nextInt();
            strenghts.add(Pi);
        }

        Collections.sort(strenghts);
        System.err.println(strenghts.toString());

        int closestStrenght = Integer.MAX_VALUE;
        for(int i = 1; i < N; i++) {
            int difference = strenghts.get(i) - strenghts.get(i-1);

            if(difference < closestStrenght) {
                closestStrenght = difference;
            }
        }
        System.out.println(closestStrenght);
    }
}