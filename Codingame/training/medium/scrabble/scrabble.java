/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/scrabble/
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static String[] dictionary;
    private static String letters;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        dictionary = new String[N];
        in.nextLine();
        for (int i = 0; i < N; i++) {
            String W = in.nextLine();
            dictionary[i] = W;
        }
        letters =  in.nextLine();
        List<String> possibleWords = new ArrayList<String>();
        for(int i = 0; i < N; i++) {
            if(isWordPossible(dictionary[i])) {
                    possibleWords.add(dictionary[i]);
            }
        }
        //PS: There is always a solution
        String bestWord = "";
        int bestPoints = 0;
        for(String word : possibleWords) {
            int p = calculatePointsOfWord(word);
            if(p > bestPoints) {
                bestPoints = p;
                bestWord = word;
            }
        }
        System.out.println(bestWord);
    }

    private static boolean isWordPossible(String word) {
        char[] cWord = word.toCharArray();
        char[] cLetters = letters.toCharArray();
        for(int i = 0; i < cWord.length; i++) {
            boolean exist = false;
            for(int j = 0; j < cLetters.length; j++) {
                if(cLetters[j] == cWord[i]) {
                    exist = true;
                    cLetters[j] = '?';
                    break;
                }
            }
            if(!exist) return false;
        }
        return true;
    }

    private static int calculatePointsOfWord(String word) {
        char[] cLetters = word.toCharArray();
        int points = 0;
        for(int i = 0; i < cLetters.length; i++) {
            points += pointsOfLetter(cLetters[i]);
        }
        return points;
    }

    private static int pointsOfLetter(char letter) {
        switch(letter) {
            case 'e': case 'a': case 'i': case 'o': case 'n': case 'r': case 't': case 'l': case 's': case 'u':
                return 1;
            case 'd': case 'g':
                return 2;
            case 'b': case 'c': case 'm': case 'p':
                return 3;
            case 'f': case 'h': case 'v': case 'w': case 'y':
                return 4;
            case 'k':
                return 5;
            case 'j': case 'x':
                return 8;
            case 'q': case 'z':
                return 10;
        }
        return 0;
    }
}