package silver2;

import java.io.*;
import java.util.*;

public class _1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer edge = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(edge.nextToken());
            int node2 = Integer.parseInt(edge.nextToken());
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        List<Integer> dfsResult = dfs(graph, N, V);
        List<Integer> bfsResult = bfs(graph, N, V);

        Iterator<Integer> itDfs = dfsResult.iterator();
        Iterator<Integer> itBfs = bfsResult.iterator();

        while (itDfs.hasNext()) {
            bw.write(itDfs.next() + " ");
        }
        bw.write("\n");
        while (itBfs.hasNext()) {
            bw.write(itBfs.next() + " ");
        }

        bw.flush();

        br.close();
        bw.close();
    }

    static List<Integer> dfs(ArrayList<Integer>[] graph, int N, int V) {
        List<Integer> v = new ArrayList<>(N);
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[N+1];
        int tmp = V;
        stack.push(tmp);

        while (!stack.isEmpty()) {
            tmp = stack.pop();
            if (!visited[tmp]) {
                visited[tmp] = true;
                v.add(tmp);

                List<Integer> list = (List<Integer>) graph[tmp].clone();
                Collections.sort(list, Collections.reverseOrder());
                Iterator<Integer> it = list.iterator();

                while (it.hasNext()) {
                    stack.push(it.next());
                }
            }
        }

        return v;
    }

    static List<Integer> bfs(ArrayList<Integer>[] graph, int N, int V) {
        List<Integer> v = new ArrayList<>(N);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        int tmp = V;
        queue.add(tmp);
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if (!visited[tmp]) {
                visited[tmp] = true;
                v.add(tmp);

                List<Integer> list = (List<Integer>) graph[tmp].clone();
                Collections.sort(list);
                Iterator<Integer> it = list.iterator();

                while (it.hasNext()) {
                    queue.add(it.next());
                }
            }
        }
        return v;
    }
}
