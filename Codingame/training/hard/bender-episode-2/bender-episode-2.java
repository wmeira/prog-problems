/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/bender-episode-2/
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static Map<Integer, List<Edge>> adjacencyList = new HashMap<Integer, List<Edge>>();
    private static int[] distances;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        initAdjacencyList(N);
        distances = new int[N+1];
        for (int i = 0; i < N; i++) {
            String room = in.nextLine();
            int[] input = roomToIntegerInput(room, N);
            adjacencyList.get(i).add(new Edge(i, input[2], input[1]));
            adjacencyList.get(i).add(new Edge(i, input[3], input[1]));
        }
        dfs(0, 0);
        System.out.println(distances[N]);
    }

    private static void initAdjacencyList(int nodes) {
    	for(int i = 0; i <= nodes; i++) {
    		adjacencyList.put(i, new ArrayList<Edge>());
    	}
    }

    private static int[] roomToIntegerInput(String room, int nodes) {
    	int[] input = new int[4];
    	String[] sInput = room.split(" ");
    	input[0] = Integer.parseInt(sInput[0]);
    	input[1] = Integer.parseInt(sInput[1]);
    	if("E".equals(sInput[2])) input[2] = nodes;
    	else input[2] = Integer.parseInt(sInput[2]);
    	if("E".equals(sInput[3])) input[3] = nodes;
    	else input[3] = Integer.parseInt(sInput[3]);
    	return input;
    }

    //Not as good as I wanted, maybe a max-flow algorithm will beat this.
    private static void dfs(int n, int distance) {
    	if(distances[n] > distance) {
    		return;
    	}
    	distances[n] = distance;
    	for(Edge e : adjacencyList.get(n)) {
    		dfs(e.to, distance + e.weight);
    	}
    }
}

class Edge {
	int from;
	int to;
	int weight;

	Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

}