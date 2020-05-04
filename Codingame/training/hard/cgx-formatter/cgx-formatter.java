/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/cgx-formatter
*/

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static int spaces = 0;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String cgx = "";
        for (int i = 0; i < N; i++) {
            cgx += in.nextLine();
        }
        cgx = cgx.trim();
        List<String> subCgx = splitCgx(cgx);
        String answer = "";
        for(String e : subCgx) {
            if("(".equals(e)) {
                answer += insertSpaces() + e + "\n";
                spaces += 4;
            } else if(e.charAt(0) == ')') {
                spaces -= 4;
                answer += insertSpaces() + e + "\n";
            } else if(";".equals(e)) {
                answer += e + "\n";
            } else {
                answer += insertSpaces() + e + "\n";
            }
        }
        System.out.println(answer);
    }

    private static List<String> splitCgx(String cgx) {
        List<String> subCgx = new ArrayList<String>();
        String word = "";
        int aspas = 0;
        for(int i = 0; i < cgx.length(); i++) {
            char c = cgx.charAt(i);
            if(c == '(' && aspas%2 == 0) {
                if(!word.isEmpty()) subCgx.add(word);
                subCgx.add("" + c);
                word = "";
            } else if(c == ')' && aspas%2 == 0) {
                if(!word.isEmpty()) subCgx.add(word);
                word = "" + c;
            } else if(c == ';' && aspas%2 == 0) {
                subCgx.add(word + c);
                word = "";
            } else if(c == '\'') {
                aspas++;
                word += c;
            } else if(c == ' ' || c== '\t') {
                if(aspas%2 == 1) word += c;
            } else {
                word += c;
            }
        }
        if(!word.isEmpty()) subCgx.add(word);
        return subCgx;
    }

    private static String insertSpaces() {
        String s = "";
        for(int i = 0; i < spaces; i++) {
            s += " ";
        }
        return s;
    }
}