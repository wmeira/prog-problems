/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/tan-network
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static final double INF = Double.MAX_VALUE;
    private static Map<String, Node> nodes = new HashMap<String, Node>();
    private static Map<Node, List<Edge>> graph = new HashMap<Node, List<Edge>>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String startPoint = in.next();
        in.nextLine();
        String endPoint = in.next();
        in.nextLine();
        int N = in.nextInt();
        in.nextLine();
        for (int i = 0; i < N; i++) {
            String STOP = in.nextLine();
            Node node = new Node(STOP);
            nodes.put(node.name, node);
            graph.put(node, new ArrayList<Edge>());
        }
        int M = in.nextInt();
        in.nextLine();
        for (int i = 0; i < M; i++) {
            String ROUTE = in.nextLine();
            String[] stops = ROUTE.split(" ");
            Node from = nodes.get(stops[0]);
            Node to = nodes.get(stops[1]);
            graph.get(from).add(new Edge(from, to, calculateDistance(from, to)));
        }
        Node start = nodes.get(startPoint);
        Node end = nodes.get(endPoint);
        List<Node> path = djikstra(start, end);

        if(path.size() == 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            for(Node node : path) {
                System.out.println(node.description);
            }
        }
    }

    private static double calculateDistance(Node from, Node to) {
        double x = (to.longitude - from.longitude) * Math.cos((from.latitude + to.latitude)/2);
        double y = (to.latitude - from.latitude);
        return Math.sqrt(x*x + y*y) * 6371;
    }

    private static List<Node> djikstra(Node from, Node to) {
        Map<Node, Double> distances = initDistances();
        Map<Node, Boolean> in = initIn();
        Map<Node, Node> predecessors = initPredecessors();
        for(Edge edge : graph.get(from)) {
            distances.replace(edge.to, edge.distance);
            predecessors.replace(edge.to, from);
        }
        distances.replace(from, 0.0);
        Node actual = from;
        while(!in.get(to)) {
            double best = INF;
            for(Node node : nodes.values()) {
                if(!in.get(node) && best > distances.get(node)) {
                    best = distances.get(node);
                    actual = node;
                }
            }
            if(best == INF) break;
            in.replace(actual, true);

            for(Edge edge : graph.get(actual)) {
                if(!in.get(edge.to) && distances.get(edge.to) > distances.get(actual) + edge.distance) {
                    distances.replace(edge.to, distances.get(actual) + edge.distance);
                    predecessors.replace(edge.to, actual);
                }
            }
        }
        if(distances.get(to) >= INF) return new ArrayList<Node>();
        else return predecessorsToPath(from, to, predecessors);
    }

    private static Map<Node, Double> initDistances() {
        Map<Node, Double> distances = new HashMap<Node, Double>();
        for(Node node : nodes.values()) {
            distances.put(node, INF);
        }
        return distances;
    }

    private static Map<Node, Boolean> initIn() {
        Map<Node, Boolean> in = new HashMap<Node, Boolean>();
        for(Node node : nodes.values()) {
            in.put(node, false);
        }
        return in;
    }

    private static Map<Node, Node> initPredecessors() {
        Map<Node, Node> predecessors = new HashMap<Node, Node>();
        for(Node node : nodes.values()) {
            predecessors.put(node, null);
        }
        return predecessors;
    }

    private static List<Node> predecessorsToPath(Node from, Node to, Map<Node, Node> predecessors) {
        List<Node> path = new ArrayList<Node>();
        path.add(to);
        Node z = to;
        while(z!=from) {
            to = z;
            z = predecessors.get(to);
            path.add(z);
        }
        Collections.reverse(path);
        return path;
    }
}

class Node {
    String name;
    String description;
    double latitude;
    double longitude;

    Node(String stop) {
        String[] s = stop.split(",");
        this.name = s[0];
        this.description = s[1].replace("\"", "");
        this.latitude = Math.toRadians(Double.parseDouble(s[3]));
        this.longitude = Math.toRadians(Double.parseDouble(s[4]));
    }
}

class Edge {
    Node from;
    Node to;
    double distance;

    Edge(Node from, Node to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

}