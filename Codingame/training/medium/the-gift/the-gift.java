/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/the-gift/
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static List<Integer> budgets;
    private static List<Integer> spended;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        budgets = new ArrayList<Integer>();
        spended = new ArrayList<Integer>();
        int C = in.nextInt();
        int totalBudget = 0;
        for (int i = 0; i < N; i++) {
            int B = in.nextInt();
            budgets.add(B);
            totalBudget += B;
        }
        if(totalBudget >= C) {
            Collections.sort(budgets);
            List<Integer> oodNotSetted = oodMoreThanMeanBudget(C, N);
            double newMean = ((double)C-totalSpended()) / oodNotSetted.size();
            for(int i = 0; i < oodNotSetted.size(); i++) {
                spended.add((int)Math.floor(newMean));
            }
            int left = C - totalSpended();
            for(int i = spended.size() -1; i >= spended.size() - left; i--) {
                spended.set(i, spended.get(i) + 1);
            }
            printSpended();
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }


    private static List<Integer> oodMoreThanMeanBudget(int C, int N) {
        List<Integer> oodNotSetted = new ArrayList<Integer>();
        double missingC = C;
        double nn = N;
        double mean = missingC/nn;
        for(int i = 0; i < N; i++) {
            if(budgets.get(i) <= mean) {
                spended.add(budgets.get(i));
                missingC -= budgets.get(i);
                mean = missingC/ --nn;
            } else {
                oodNotSetted.add(i);
            }
        }
        return oodNotSetted;
    }

    private static int totalSpended() {
        int total = 0;
        for(int i = 0; i < spended.size(); i++) {
            total += spended.get(i);
        }
        return total;
    }

    private static void printSpended() {
        Collections.sort(spended);
        for(int i = 0; i < spended.size(); i++) {
            System.out.println(spended.get(i));
        }
    }
}