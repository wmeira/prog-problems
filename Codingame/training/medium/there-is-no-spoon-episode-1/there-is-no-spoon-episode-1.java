/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/there-is-no-spoon-episode-1
*/


import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    public static int[][] grid;
    public static List<Node> nodes = new ArrayList<Node>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        in.nextLine();
        int height = in.nextInt(); // the number of cells on the Y axis
        in.nextLine();

        grid = new int[height][width];

        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            char[] cs = line.toCharArray();
            for(int j = 0; j < cs.length; j++) {
                int value = charToInt(cs[j]);
                if(value == 1) nodes.add(new Node(j, i));
                grid[i][j] = value;
            }
        }

        for(Node node : nodes) {
            Node right = new Node(-1, -1);
            for(int i = node.x+1; i < width; i++) {
            	if(grid[node.y][i] == 1) {
            		right.x = i;
                    right.y = node.y;
                    break;
            	}
            }
            Node bottom = new Node(-1, -1);
            for(int i = node.y+1; i < height; i++) {
            	if(grid[i][node.x] == 1) {
                    bottom.x = node.x;
                    bottom.y = i;
                    break;
                }
            }
            System.out.println(node.x + " " + node.y + " " + right.x + " " + right.y + " " + bottom.x + " " + bottom.y);
        }

    }

    public static int charToInt(char c) {
        if(c == '0') return 1;
        else return 0;
    }
}

class Node {
    int x;
    int y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}