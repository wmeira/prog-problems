/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/easy/mime-type/
*/

import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        in.nextLine();
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        in.nextLine();

        Map<String, String> mime = new HashMap<String, String>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            mime.put(EXT.toLowerCase(), MT);
            in.nextLine();
        }

        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            int posicaoUltimoPonto = FNAME.lastIndexOf(".");
            if(posicaoUltimoPonto != -1 && FNAME.length() > posicaoUltimoPonto + 1) {
                String extension = FNAME.substring(posicaoUltimoPonto + 1);
                String mimeType = mime.get(extension.toLowerCase());
                if(mimeType != null) {
                    System.out.println(mimeType);
                } else {
                    System.out.println("UNKNOWN");
                }
            } else {
                System.out.println("UNKNOWN");
            }
        }
    }
}