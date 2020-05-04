/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/defibrillators
*/


import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static double longitudeA;
    private static double latitudeA;
    private static double longitudeB;
    private static double latitudeB;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String LON = in.next();
        longitudeA = Math.PI * Double.parseDouble(LON.replace(",",".")) / 180;
        in.nextLine();
        String LAT = in.next();
        latitudeA = Math.PI * Double.parseDouble(LAT.replace(",",".")) / 180;
        in.nextLine();
        int N = in.nextInt();
        in.nextLine();
        String nameClosest = "";
        double dClosest = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            String[] defibrillator = DEFIB.split(";");
            longitudeB = Double.parseDouble(defibrillator[4].replace(",","."));
            latitudeB = Double.parseDouble(defibrillator[5].replace(",","."));

            longitudeB = Math.PI * longitudeB / 180;
            latitudeB = Math.PI * latitudeB / 180;
            System.err.println(longitudeA + " " + latitudeA + " " + longitudeB  +" " + latitudeB);
            double d = calculateDistance();
            if(d < dClosest) {
                dClosest = d;
                nameClosest = defibrillator[1];
            }
        }
        System.out.println(nameClosest);
    }

    private static double calculateDistance() {
        double x = (longitudeB - longitudeA) * Math.cos((latitudeA + latitudeB)/2);
        double y = (latitudeB - latitudeA);
        return Math.sqrt(x*x + y*y) * 6371;
    }
}