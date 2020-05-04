/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/mayan-calculation/
*/


import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static final int MAYAN_NUMBERS = 20;
    private static int width;
    private static int height;

    private static Number[] numbers = new Number[MAYAN_NUMBERS];

    private static Scanner in;

    public static void main(String args[]) {
        in = new Scanner(System.in);
        width = in.nextInt();
        height = in.nextInt();
        initNumbers();

        for (int i = 0; i < height; i++) {
            String numeral = in.next();
            for(int n = 0; n < MAYAN_NUMBERS; n++) {
                for(int l = 0; l < width; l++) {
                    numbers[n].structure[i][l] = numeral.charAt(n*width + l);
                }
            }
        }

        int S1 = in.nextInt();
        long firstNumber = readNumber(S1);
        int S2 = in.nextInt();
        long secondNumber = readNumber(S2);

        String operation = in.next();
        long result = calculateResult(firstNumber, secondNumber, operation);

        System.err.println(firstNumber + operation + secondNumber + " = " + result);

        List<Long> numbersToPrint = new ArrayList<Long>();
        do {
            numbersToPrint.add(result % 20);
            result /= 20;
            System.err.println(result);
        } while(result != 0);

        for(int i = numbersToPrint.size() - 1; i >= 0; i--) {
            int nPrint = new BigDecimal(numbersToPrint.get(i)).intValueExact();
            numbers[nPrint].print();
        }

    }

    private static void initNumbers() {
        for(int n = 0; n < MAYAN_NUMBERS; n++) {
            numbers[n] = new Number(width, height);
        }
    }

    private static long readNumber(int lines) {
        long read = 0;
        for (int i = 0; i < lines; i = i+height) {
            Number n1 = new Number(width, height);
            for(int h = 0; h < height; h++) {
                String num1Line = in.next();
                for(int l = 0; l < width; l++) {
                    n1.structure[h][l] = num1Line.charAt(l);
                }
            }
            read += matchNumber(n1) * Math.pow(20, (lines-i)/height - 1);
        }
        return read;
    }

    private static int matchNumber(Number number) {
        for(int n = 0; n < MAYAN_NUMBERS; n++) {
            if(compareTwoNumbers(number, numbers[n])) return n;
        }
        return -1;
    }

    private static boolean compareTwoNumbers(Number n1, Number n2) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(n1.structure[i][j] != n2.structure[i][j]) return false;
            }
        }
        return true;
    }

    private static long calculateResult(long firstNumber, long secondNumber, String operation) {
        if("+".equals(operation)) return firstNumber + secondNumber;
        else if("-".equals(operation)) return firstNumber - secondNumber;
        else if("*".equals(operation)) return firstNumber * secondNumber;
        else if("/".equals(operation)) return firstNumber / secondNumber;
        else throw new IllegalArgumentException();
    }
}

class Number {
    char[][] structure;

    public Number(int width, int height) {
        structure = new char[width][height];
    }

    void print() {
        for(int i = 0; i < structure.length; i++) {
            for(int j = 0; j < structure[i].length; j++) {
                System.out.print(structure[i][j]);
            }
            System.out.println();
        }
    }
}