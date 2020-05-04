/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/chuck-norris/
*/

import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();
        String answer = "";
        char bitAtual = '9';
        for(char s : MESSAGE.toCharArray()) {
            int ascii = s;
            String byteArray = String.format("%7s", Integer.toBinaryString(ascii));
            byteArray = byteArray.replace(' ','0');
            for(char bit : byteArray.toCharArray()) {
                if(bitAtual != bit) {
                    if(bit == '0') {
                        bitAtual = '0';
                        answer += " 00 ";
                    } else {
                        bitAtual = '1';
                        answer += " 0 ";
                    }
                }
                answer += '0';
            }
        }

        System.out.println(answer.trim());
    }
}