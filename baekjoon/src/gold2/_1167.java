package gold2;

import java.io.*;
import java.util.*;

/*
우선 선형 시간안에 트리의 지름을 구하는 방법은 다음과 같다.

트리에서 임의의 정점 X를 정한다.
정점 X에서 가장 먼 정점 Y를 구한다.
정점 Y에서 가장 먼 정점 Z를 구한다.
즉, 트리의 지름은 정점 Y와 정점 Z 사이의 거리를 의미한다.
StringTokenizer의 countTokens()은 토큰의 갯수에 영향을 받아 선형시간의 시간복잡도를 가진다.
따라서 마지막 토큰이 -1인 경우를 직접 확인하여 StringTokenizer의 토큰을 받아야 한다.
int nextNode = Integer.parseInt(st.nextToken());
while (nextNode != -1) {
    int currentNode = nextNode;
    int weight = Integer.parseInt(st.nextToken());
    tree[node].add(new Node(currentNode, weight));
    nextNode = Integer.parseInt(st.nextToken());
} <---------------------이부분

원래
while (st.countTokens() > 1) {
    int nextNode = Integer.parseInt(st.nextToken());
    int weight = Integer.parseInt(st.nextToken());
    tree[node].add(new Node(nextNode, weight));
} <-----------------이렇게 하다가 시간초과 뜸
 */

public class _1167 {
    static int maxNode;
    static int maxDistance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stV = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(stV.nextToken());

        ArrayList<Node>[] tree = new ArrayList[V + 1];
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            tree[node] = new ArrayList();

            int nextNode = Integer.parseInt(st.nextToken());
            while (nextNode != -1) {
                int currentNode = nextNode;
                int weight = Integer.parseInt(st.nextToken());
                tree[node].add(new Node(currentNode, weight));
                nextNode = Integer.parseInt(st.nextToken());
            }

        }

        maxNode = 1;
        maxDistance = 1;
        dfs(tree,V);
        dfs(tree,V);

        bw.write(String.valueOf(maxDistance));
        bw.flush();

        br.close();
        bw.close();
    }

    private static void dfs(ArrayList<Node>[] tree, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V + 1];
        int[] distance = new int[V + 1];

        visited[maxNode] = true;
        stack.push(maxNode);

        while (!stack.isEmpty()) {
            int tmp = stack.pop();

            Iterator<Node> iterators = tree[tmp].iterator();
            while (iterators.hasNext()) {
                Node node = iterators.next();
                if (!visited[node.getNode()]) {
                    visited[node.getNode()] = true;
                    stack.push(node.getNode());
                    distance[node.getNode()] += distance[tmp] + node.getWeight();
                    if (maxDistance < distance[node.getNode()]) {
                        maxDistance = distance[node.getNode()];
                        maxNode = node.getNode();
                    }
                }
            }
        }
    }
}

class Node {
    private int node;
    private int weight;

    public Node(int nextNode, int weight) {
        this.node = nextNode;
        this.weight = weight;
    }

    public int getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }
}