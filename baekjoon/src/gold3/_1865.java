package gold3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
벨만포드 알고리즘 - 음수가 포함된 최단 경로 계산
1. 모든 정점에서 음의 사이클을 판단할 필요가 없다.
2. 어느 한 정점에서 시작하여 노드를 최대한 거치는 경로는 node개수 - 1이므로 node개수 - 1번 시작점으로 부터 노드의 경로의 값을 갱신한 후
마지막으로 한 번 더 경로를 탐색했을 때 경로가 갱신되는 경우 -> 음수 사이클 존재
-> 계속해서 경로를 갱신하면 음의 무한대로 갈 수 있음 -> 최단경로를 찾을 수 없는 그래프

1) 도로는 무방향이므로 양방향으로 간선 2개를 추가하기
2) 웜홀의 가중치는 음수로 취급하기

 */

public class _1865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int[] NMW = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Edge> edges = new ArrayList<>(NMW[1] + NMW[2]);
            for (int i = 0; i < NMW[1]; i++) {
                int[] SET = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                edges.add(new Edge(SET[0], SET[1], SET[2]));
                edges.add(new Edge(SET[1], SET[0], SET[2])); //놓친 부분:  도로는 무방향이어서 시작과 끝 부분을 바꿔서 추가해야 했음
            }

            for (int i = 0; i < NMW[2]; i++) {
                int[] SET = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                edges.add(new Edge(SET[0], SET[1], SET[2]*(-1)));
            }

            String canComeBack = bellmanFord(NMW[0], edges);
            sb.append(canComeBack);
            if (TC > 0) {
                sb.append("\n");
            }
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        br.close();
        bw.close();
    }

    static String bellmanFord(int N, List<Edge> edges) {
        int[] distance = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            for (Edge edge : edges) {
                if (distance[edge.nextPoint] > distance[edge.currentPoint] + edge.time) {
                    distance[edge.nextPoint] = distance[edge.currentPoint] + edge.time;

                    if ((i == N)) {
                        return "YES";
                    }
                }
            }
        }

        return "NO";
    }

    static class Edge {
        int currentPoint;
        int nextPoint;
        int time;

        public Edge(int currentPoint, int nextPoint, int time) {
            this.currentPoint = currentPoint;
            this.nextPoint = nextPoint;
            this.time = time;
        }
    }
}
