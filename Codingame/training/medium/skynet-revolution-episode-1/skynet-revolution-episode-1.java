/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/skynet-revolution-episode-1/
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
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            Skynet bestSkynet = null;
            for(int i = 0; i < E; i++) {
                Skynet skynet = djikstraSkynet(graph, SI, gateways[i], N);
                if(bestSkynet == null || bestSkynet.distance > skynet.distance) {
                    bestSkynet = skynet;
                }
            }
            /* Deleta a aresta mais próxima do agente que leva ao menor caminho do gateway mais próxmo. */
            graph[bestSkynet.firstNodeVisited][SI] = INF;
            graph[SI][bestSkynet.firstNodeVisited] = INF;
            System.out.println(SI + " " + bestSkynet.firstNodeVisited);
        }
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
        int z = t;
        while(z != s) {
            skynet.firstNodeVisited = z;
            z = predecessor[z];
        }
        return skynet;
    }
}

/* Estrutura para ar*/
class Skynet {
    int firstNodeVisited;
    int distance;
}