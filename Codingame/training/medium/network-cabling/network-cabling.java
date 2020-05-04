/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/network-cabling
*/


import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    private static int yh[];
    private static int xFirst = Integer.MAX_VALUE;
    private static int xLast = Integer.MIN_VALUE;
    private static long yMin = Long.MAX_VALUE;
    private static long yMax = Long.MIN_VALUE;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        yh = new int[N];
        for (int i = 0; i < N; i++) {
            int X = in.nextInt();
            int Y = in.nextInt();
            yh[i] = Y;

            if(X < xFirst) xFirst = X;
            if(X > xLast) xLast = X;

            if(Y < yMin) yMin = Y;
            if(Y > yMax) yMax = Y;
        }

        long actual = calculteBestLenght();

        //FORCA BRUTA
        //for (long i = first; i <= last; i++) {
        //    long actual = calculateCableLength(i);
        //    System.err.println(i + " " + actual);
        //    if(actual < best) best = actual;
        //}
        System.out.println(actual);

    }

    public static long calculteBestLenght() {
        long actual;
        long pos;
        long pre;
        while(true) {
            if(yMin == yMax) {
                actual = calculateCableLength(yMin);
                break;
            }
            long midRange = yMin + (yMax - yMin)/2;
            actual = calculateCableLength(midRange);
            pos = calculateCableLength(midRange + 1);
            pre = calculateCableLength(midRange - 1);
            if(pre < actual) {
                yMax = midRange - 1;
            } else if(pos < actual) {
                yMin = midRange + 1;
            } else {
                break;
            }
         }
         return actual;
    }


    public static long calculateCableLength(double yMainCable) {
        long cable = xLast - xFirst;
        for(int i = 0; i < yh.length; i++) {
            cable += Math.abs(yh[i] - yMainCable);
        }
        return cable;
    }

    public static double calculateMean(int N) {
        double mean = 0;
        for(int i = 0; i < N; i++) {
            mean += yh[i];
        }
        return mean/N;
    }

    public static double calculateSD(double mean) {
        int N = yh.length;
        double p = 1.0 / (N-1.0);
        double t = 0;
        for(int i = 0; i < N; i++) {
            t += Math.pow(yh[i] - mean, 2);
        }
        return Math.sqrt(t * p);
    }
}