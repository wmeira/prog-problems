/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/the-labyrinth/
*/

import java.util.*;
import java.io.*;
import java.math.*;


class Player {

    private static final int INF = Integer.MAX_VALUE;

    private static int xT;
    private static int yT;
    private static int xC = -1;
    private static int yC = -1;

    private static boolean backing = false;

    private static char[][] map;
    private static int[][] graph;

    private static List<Integer> questionMarks;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows.
        int C = in.nextInt(); // number of columns.
        int A = in.nextInt(); // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.
        System.err.println(R + " " + C);
        map = new char[R][C];
        graph = new int[R*C][R*C];
        questionMarks = new ArrayList<Integer>();

        Result result = new Result();
        // game loop
        while (true) {

            int KR = in.nextInt(); // row where Kirk is located.
            int KC = in.nextInt(); // column where Kirk is located.

            for (int i = 0; i < R; i++) {
                String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                char[] cRow = ROW.toCharArray();
                for(int j = 0; j < C; j++) {
                    map[i][j] = cRow[j];
                    System.err.print(map[i][j] + " ");
                    if(cRow[j] == '?') {
                        int r = (i*C + j);
                        questionMarks.add(r);
                    }
                    else if(cRow[j] == 'T') {
                        xT = i;
                        yT = j;
                    } else if(cRow[j] == 'C') {
                        xC = i;
                        yC = j;
                    }
                }
                System.err.println();
            }

            mapToGraph();

            if(KR == xC && KC == yC && !backing) {
                backing = true; //REACHED CONTROL! GO BACK!
            }

            if(backing) {
                result = djikstra(KR*C + KC, xT*C + yT, true);
            } else if(xC != -1) {
                result = djikstra(KR*C + KC, xC*C + yC, true);
            } else {
                result.distance = Integer.MAX_VALUE;
                for(Integer question : questionMarks) {
                    Result actual = djikstra(question, KR*C + KC, false);
                    if(actual.distance <= 3) {
                        result = actual;
                        break;
                    } else if(actual.distance < result.distance) {
                        result = actual;
                    }
                }
            }
            //System.err.println( (KR*C + KC) + " " + result);

            System.out.println(result.directionStep(KR*C + KC, C)); // Kirk's next move (UP DOWN LEFT or RIGHT).
            questionMarks.clear();
        }
    }

    private static void mapToGraph() {
        int N = graph.length;
        int C = map[0].length;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(j%C!=0 && j==i+1) graph[i][j] = toValue(map[i/C][i%C+1]);
                else if(j%C!=C-1 && j==i-1) graph[i][j] = toValue(map[i/C][i%C-1]);
                else if(j==i+C) graph[i][j] = toValue(map[i/C+1][i%C]);
                else if(j==i-C) graph[i][j] = toValue(map[i/C-1][i%C]);
                else graph[i][j] = INF;
            }
        }
    }

    private static int toValue(char s) {
        switch(s) {
            case '#':
                return INF;
            case '?':
                return xC != -1 && !backing ? 1 : INF;
            case 'T':
            case 'C':
            case '.':
                return 1;
        }
        return INF;
    }

    //First indicates if the next step is the first from s (true) or the last (false).
    private static Result djikstra(int s, int t, boolean first) {
        int d[] = new int[graph.length]; //distâncias do ponto s
        boolean[] in = new boolean[graph.length];
        int predecessor[] = new int[graph.length];

        for(int i = 0; i < graph.length; i++) {
            d[i] = graph[s][i];
            in[i] = false;
            predecessor[i] = s;
        }
        d[s] = 0;
        predecessor[s] = -1;

        int u = s;
        while(!in[t]) {
            int best = INF;
            for(int i = 0; i < graph.length; i++) {
                if(!in[i] && best > d[i]) {
                    best = d[i];
                    u = i;
                }
            }

            if(best == INF) break;
            in[u] = true;

            // Atualiza valores de distância
            for(int i = 0; i < graph.length; i++) {
                if(!in[i] && graph[u][i] < INF && d[i] > d[u] + graph[u][i]) {
                    d[i] = d[u] + graph[u][i];
                    predecessor[i]= u;
                }
            }
        }
        Result r = new Result();
        r.distance = d[t];

        if(first) {
            //Lê os nodos do fim para o começo, a fim de encontrar a aresta que inicia esse caminho.
            int z = t;
            while(z!=s) {
                t = z;
                z = predecessor[t];
            }
            r.nextStep = t;
        } else {
            r.nextStep = predecessor[t];
        }

        return r;
    }
}

class Result {
    int nextStep;
    int distance;

    String directionStep(int s, int width) {
        if(nextStep == s+1) return "RIGHT";
        else if(nextStep == s-1) return "LEFT";
        else if(nextStep == s + width) return "DOWN";
        else if(nextStep == s - width) return "UP";
        else return "";
    }

    public String toString() {
        return nextStep + ";" + distance;
    }
}
