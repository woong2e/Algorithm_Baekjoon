package gold4;

import java.io.*;
import java.util.*;

/*
최단경로 알고리즘 - dijkstra 기본 문제
dijkstra는 음수인 가중치를 포함할 수 없다.
 */

public class _1753 {

    static class Node {
        int nextNode;
        int weight;

        public Node(int nextNode, int weight) {
            this.nextNode = nextNode;
            this.weight = weight;
        }
    }

    static int[] weight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        List<Node>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, weight));
        }
        dijkstra(graph, V, K);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (weight[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(weight[i]);
            }
            if (i < V) {
                sb.append("\n");
            }
        }
        bw.write(String.valueOf(sb));
        bw.flush();

        br.close();
        bw.close();
    }

    private static void dijkstra(List<Node>[] graph, int V, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        weight = new int[V + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[K] = 0;

        pq.add(new int[]{weight[K], K});

        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int currentWeight = tmp[0];
            int currentNode = tmp[1];

            for (Node node : graph[currentNode]) {
                int nextWeight = node.weight + currentWeight;
                int nextNode = node.nextNode;

                if (nextWeight < weight[nextNode]) {
                    weight[nextNode] = nextWeight;
                    pq.add(new int[]{nextWeight, nextNode});
                }
            }
        }
    }
}
