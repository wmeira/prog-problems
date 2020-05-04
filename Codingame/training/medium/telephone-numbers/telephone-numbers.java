/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/telephone-numbers
*/

import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    private static Node rootNode =  new Node(-1);

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String telephone = in.next();
            int[] nPhone = toIntArray(telephone);

            Node ln = rootNode;
            for(int j = 0; j < nPhone.length; j++) {
                Node tn = ln.getSonWithValue(nPhone[j]);
                if(tn == null) {
                    ln = ln.addSon(nPhone[j]);
                } else {
                    ln = tn;
                }
            }
        }
        System.out.println(Node.instances - 1); // The number of elements (referencing a number) stored in the structure.
    }

    private static int[] toIntArray(String s) {
        int[] array = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            array[i] = Integer.parseInt(s.substring(i,i+1));
        }
        return array;
    }
}

class Node {

    public static int instances = 0;

    int value;
    List<Node> sons = new ArrayList<Node>();

    Node(int value) {
        instances++;
        this.value = value;
    }

    Node addSon(int value) {
        Node newNode = new Node(value);
        sons.add(newNode);
        return newNode;
    }

    Node getSonWithValue(int value) {
        for(Node son : sons) {
            if(son.value == value) return son;
        }
        return null;
    }
}