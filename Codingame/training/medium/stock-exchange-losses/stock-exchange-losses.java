/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/stock-exchange-losses
*/

import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String vs = in.nextLine();

        String[] values = vs.split(" ");
        int[] intValues = new int[n];
        int lowestValue = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            intValues[i] = Integer.parseInt(values[i]);
            if(intValues[i] < lowestValue) {
                lowestValue = intValues[i];
            }
        }

        int biggestLoss = 0;
        for(int i = 0; i < n-1; i++) {
            for(int j = i+1; j<n; j++) {
                if((intValues[j] - intValues[i]) < biggestLoss) {
                    biggestLoss = intValues[j] - intValues[i];
                }

                //Parar quando já for descoberto a impossibilidade de bater o biggestLoss
                if(lowestValue -  intValues[i] > biggestLoss) {
                    break;
                }
            }
        }
        System.out.println(biggestLoss);
    }
}