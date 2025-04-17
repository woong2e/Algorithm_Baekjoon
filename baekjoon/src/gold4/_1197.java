package gold4;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
MST - 최소 가중치를 가지는 사이클이 없는 트리를 찾기 Kruskal 알고리즘
사이클 여부 확인 방법 - union-find
 */

public class _1197 {
    private static PriorityQueue<Edge> graph = new PriorityQueue<>();
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        int V = Integer.parseInt(input.split(" ")[0]);
        int E = Integer.parseInt(input.split(" ")[1]);

        for (int i = 0; i < E; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            graph.add(new Edge(edge[0], edge[1], edge[2]));
        }

        parent = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            parent[i] = i;
        }
        long minWeight = minimumSpanningTree(V);

        bw.write(String.valueOf(minWeight));
        bw.flush();

        br.close();
        bw.close();
    }

    private static long minimumSpanningTree(int V) {
        long weight = 0L;

        for (int i = 0; i < V - 1; i++) {
            while (true) {
                Edge edge = graph.poll();
                if (find(edge.start) == find(edge.end)) {
                    continue;
                }

                union(edge.start, edge.end);
                weight += edge.weight;
                break;
            }
        }

        return weight;
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        parent[rootY] = rootX;
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
