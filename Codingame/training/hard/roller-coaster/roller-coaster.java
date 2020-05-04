/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/roller-coaster
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static Map<Integer, Integer> groupEarning  = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> groupNext  = new HashMap<Integer, Integer>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int C = in.nextInt();
        int N = in.nextInt();
        int[] queue = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            queue[i] = in.nextInt();
            max += queue[i];
        }


        //Without it I was doing a verification every step of the algorithm below
        //checking if the pos is again the starting point of the ride.
        if(max <= L) { //The thing, 4 test
            System.out.println(max*C);
            return;
        }

        long totalEarnedMoney = 0;
        int pos = 0;
        for(int i = 0; i < C; i++) {
            int startGroup = pos;
            if(groupEarning.get(startGroup) != null) {
                totalEarnedMoney += groupEarning.get(startGroup);
                pos = groupNext.get(startGroup);
            } else {
                int occupiedSits = 0;
                while(true) {
                    if(queue[pos] + occupiedSits > L) {
            	        groupEarning.put(startGroup, occupiedSits);
            	        groupNext.put(startGroup, pos);
            	        totalEarnedMoney += occupiedSits;
            	        break;
            	    }
            	    occupiedSits += queue[pos];
                    if(++pos >= queue.length) pos = 0;
                }
            }
        }
        System.out.println(totalEarnedMoney);
    }
}
