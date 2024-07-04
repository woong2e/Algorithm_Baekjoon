package gold3;

import java.io.*;
import java.util.*;

/*
최단 경로를 찾는 dijkstra알고리즘
단방향 그래프이므로 가는 경로와 오는 경로의 weight합이 다를 수 있다. dijkstra알고리즘을 가는 경로, 오는 경로 따로 적용시켜 줘야 한다.
dijkstra알고리즘의 핵심은 시작 노드부터 현재 위치한 노드까지의 weight합의 최소값을 memoization하여 최소 경로를 구하는 DP문제이다.
이때, 다음 노드에 대해 최소 경로를 우선적으로 탐색하기 위해 우선순위큐를 사용한다.
저장된 최소경로 weight와 이전 노드에서 현재노드로 오는 weight 합을 비교하여 최소경로를 구해나간다.
 */

public class _1238 {
    static ArrayList<NextNode>[] road;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st =  new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        road = new ArrayList[N+1];
        for (int i = 0; i < N + 1; i++) {
            road[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer sti =  new StringTokenizer(br.readLine());
            road[Integer.parseInt(sti.nextToken())].
                    add(new NextNode(Integer.parseInt(sti.nextToken()), Integer.parseInt(sti.nextToken())));
        }
        int[] time = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            if (i == X) {
                continue;
            }
            time[i] = dijkstra(N, i, X) + dijkstra(M, X, i);;
        }

        Arrays.sort(time);
        int longestTime = time[N];
        bw.write(String.valueOf(longestTime));
        bw.flush();

        br.close();
        bw.close();
    }

    static int dijkstra(int N, int start, int end) {
        PriorityQueue<NextNode> pq = new PriorityQueue<>();
        int[] mem = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(mem, Integer.MAX_VALUE);

        pq.add(new NextNode(start, 0));
        mem[start] = 0;

        while (!pq.isEmpty()) {
            NextNode tmp = pq.poll();
            if (visited[tmp.nextNode]) {
                continue;
            }
            visited[tmp.nextNode] = true;

            for (NextNode next : road[tmp.nextNode]) {
                if (mem[tmp.nextNode] + next.weight < mem[next.nextNode]) {
                    mem[next.nextNode] = mem[tmp.nextNode] + next.weight;
                    pq.add(new NextNode(next.nextNode, mem[next.nextNode]));
                }
            }
        }

        return mem[end];
    }
}

class NextNode implements Comparable<NextNode> {
    int nextNode;
    int weight;

    public NextNode(int nextNode, int weight) {
        this.nextNode = nextNode;
        this.weight = weight;
    }

    @Override
    public int compareTo(NextNode n) {
        return this.weight - n.weight;
    }
}
