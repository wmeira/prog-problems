/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/skynet-revolution-episode-2/
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Player {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways

        int[][] graph = new int[N][N];
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		graph[i][j] = INF;
        	}
        }
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            graph[N1][N2] = 1;
            graph[N2][N1] = 1;
        }

        int[] gateways = new int[E];
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gateways[i] = EI;
            for(int j = 0; j < N; j++) graph[EI][j] = INF;
        }
        List<Integer> pontosCriticos = getPontosCriticos(graph, gateways);

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            Skynet bestSkynet = null;
            //Distance to all gateways from the SI
            List<Skynet> skynets = new ArrayList<Skynet>();
            for(int i = 0; i < E; i++) {
                skynets.add(djikstraSkynet(graph, SI, gateways[i], N));
            }
            Collections.sort(skynets);

            if(skynets.get(0).distance == 1) {
                //Cut, IT's URGENT.
                graph[skynets.get(0).to][skynets.get(0).from] = INF;
                graph[skynets.get(0).from][skynets.get(0).to] = INF;
                System.out.println(skynets.get(0).from + " " + skynets.get(0).to);
            } else {
                Skynet selected = null;
                if(pontosCriticos.size() != 0) {
                    //Where are all the critical points
                    List<Skynet> dPontosCriticos = new ArrayList<Skynet>();
                    for(int i = 0; i < pontosCriticos.size(); i++) {
                        dPontosCriticos.add(djikstraSkynet(graph, SI, pontosCriticos.get(i), N));
                    }
                    Collections.sort(dPontosCriticos);

                    for(int t = 0; t < skynets.size(); t++) {
                        Skynet sTeste = skynets.get(t);
                        for(Skynet d: dPontosCriticos) {
                            int d2 = djikstraSkynet(graph, sTeste.from, d.to, N).distance;
                            //I am walking toward this critical point, cut a piece.
                            if(d.distance > d2) {
                                //Cut any gateway that is linking the critical point choosen.
                                for(int j = 0; j < gateways.length; j++) {
                                    if(graph[d.to][gateways[j]] == 1) {
                                        pontosCriticos.remove((Integer)d.to);
                                        selected = new Skynet(d.to, gateways[j]);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        if(selected != null) break;
                    }

                    //Cut any critical point, you have the opportunity
                    if(selected == null) {
                        Skynet d = dPontosCriticos.get(0);
                        for(int j = 0; j < gateways.length; j++) {
                            if(graph[d.to][gateways[j]] == 1) {
                                pontosCriticos.remove((Integer)d.to);
                                selected = new Skynet(d.to, gateways[j]);
                                break;
                            }
                        }
                    }
                }
                //Ok, just go to the near one
                if(selected == null) selected = skynets.get(0);

                graph[selected.to][selected.from] = INF;
                graph[selected.from][selected.to] = INF;
                System.out.println(selected.from + " " + selected.to);
            }
        }
    }

    private static List<Integer> getPontosCriticos(int[][] graph, int[] gateways) {
        List<Integer> pontosCriticos = new ArrayList<Integer>();
        for(int i = 0; i < graph.length; i++) {
            double n = 0;
            for(int j = 0; j < gateways.length; j++) {
                if(graph[i][gateways[j]] == 1) n++;
            }
            if(n >= 2) pontosCriticos.add(i);
        }
        return pontosCriticos;
    }

    private static Skynet djikstraSkynet(int[][] graph, int s, int t, int N) {
        int d[] = new int[N]; //distâncias do ponto s
        boolean[] in = new boolean[N];
        int predecessor[] = new int[N];

        for(int i = 0; i < N; i++) {
            d[i] = graph[s][i];
            in[i] = false;
            predecessor[i] = s;
        }
        d[s] = 0;
        predecessor[s] = -1;

        int u = s;
        while(!in[t]) {
            int best = INF;
            for(int i = 0; i < N; i++) {
                if(!in[i] && best > d[i]) {
                    best = d[i];
                    u = i;
                }
            }

            if(best == INF) break;
            in[u] = true;

            // Atualiza valores de distância
            for(int i = 0; i < N; i++) {
                if(!in[i] && graph[u][i] < INF && d[i] > d[u] + graph[u][i]) {
                    d[i] = d[u] + graph[u][i];
                    predecessor[i]= u;
                }
            }
        }

        //Lê os nodos do fim para o começo, a fim de encontrar a aresta que inicia esse caminho.
        Skynet skynet = new Skynet();
        skynet.distance = d[t];
        skynet.to = t;
        skynet.from = predecessor[t];
        return skynet;
    }
}

class Skynet implements Comparable<Skynet> {
    int to;
    int from;
    int distance;

    Skynet(int to, int from) {
        this.to = to;
        this.from = from;
    }

    Skynet() {

    }

    public int compareTo(Skynet s) {
        int diff = this.distance - s.distance;
        if(diff != 0) return diff;
        else return this.from - s.from;
    }

    public String toString() {
        return distance + ";" + from + ";"  + to;
    }
}