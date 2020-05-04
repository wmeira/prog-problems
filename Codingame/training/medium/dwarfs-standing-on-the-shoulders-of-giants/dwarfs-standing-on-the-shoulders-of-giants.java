/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/dwarfs-standing-on-the-shoulders-of-giants/
*/


import java.util.*;
import java.io.*;
import java.math.*;


class Solution {
    private static int[][] graph;
    private static int NN;
    private static List<Integer> settledNodes = new ArrayList<Integer>();
    private static List<Integer> unsettledNodes = new ArrayList<Integer>();
    private static int[] distances;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of relationships of influence
        Set<Integer> setNodes = new HashSet<Integer>();
        int from[] = new int[n];
        int to[] = new int[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(); // a relationship of influence between two people (x influences y)
            int y = in.nextInt();
            from[i] = x;
            to[i] = y;
            setNodes.add(x);
            setNodes.add(y);
        }
        buildGraph(setNodes, from, to);

        distances = new int[NN];
        initUnsettledNodes();
        while(unsettledNodes.size() > 0) {
            calculateDistance(unsettledNodes.get(0));
        }
        System.out.println(hightestDistance() + 1); //+1 is the root node

    }

    private static void buildGraph(Set<Integer> setNodes, int[] from, int[] to) {
        List<Integer> nodes = new ArrayList<Integer>();
        nodes.addAll(setNodes);
        NN = nodes.size();
        graph = new int[NN][NN];
        for(int i = 0; i < from.length; i++) {
            int iTo = nodes.indexOf(to[i]);
            int iFrom = nodes.indexOf(from[i]);
            graph[iTo][iFrom] = 1;
        }
    }

    private static void initUnsettledNodes() {
        for(int i = 0; i < NN; i++) {
            unsettledNodes.add(i);
        }
    }

    private static void calculateDistance(Integer node) {
        int bestD = 0;
        for(int j = 0; j < NN; j++) {
            if(graph[node][j] == 1) {
                if(!settledNodes.contains(j)) {
                   calculateDistance(j);
                }
                if(distances[j] + 1 > bestD) {
                    bestD = distances[j] + 1;
                }
            }
        }
        unsettledNodes.remove((Integer) node);
        settledNodes.add(node);
        distances[node] = bestD;
        //System.err.println("D: " + node + " " + bestD);
    }

    private static int hightestDistance() {
        int bestD = 0;
        for(int i = 0; i < NN; i++) {
            if(distances[i] > bestD) {
                bestD = distances[i];
            }
        }
        return bestD;
    }
}