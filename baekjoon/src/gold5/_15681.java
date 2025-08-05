package gold5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
입력 받은 값을 가지고 부모 자식 관계가 있는 트리를 만드는 것이 핵심
서브트리에 속한 정점의 수 계산을 DP를 통해 리스트에 저장해놓는다.
재귀를 사용하지 않기 위해 스택을 사용
서브트리에 속한 정점의 수 계산을 위해 stack 2개를 이용하여 bottom-up 방식을 구현
 */
public class _15681 {

    private static List<Integer>[] graph;
    private static List<Integer>[] tree;
    private static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        int N = Integer.parseInt(input.split(" ")[0]);
        int R = Integer.parseInt(input.split(" ")[1]);
        int Q = Integer.parseInt(input.split(" ")[2]);

        graph = new List[N + 1];
        tree = new List[N + 1];
        size = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            String route = br.readLine();
            int U = Integer.parseInt(route.split(" ")[0]);
            int V = Integer.parseInt(route.split(" ")[1]);

            graph[U].add(V);
            graph[V].add(U);
        }

        graph[0].add(R);
        graph[R].add(0);

        makeTree(R, 0);
        countSubtreeNodes(R);

        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            bw.write(size[q] + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    private static void makeTree(int current, int parent) {
        Stack<Node> stack = new Stack<>();
        stack.add(new Node(current, parent));

        while (!stack.empty()) {
            Node tmp = stack.pop();

            for (Integer i : graph[tmp.current]) {
                if (i != tmp.parent) {
                    tree[tmp.current].add(i);
                    stack.add(new Node(i, tmp.current));
                }
            }
        }
    }

    private static void countSubtreeNodes(int root) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.add(root);

        while (!stack1.empty()) {
            int tmp = stack1.pop();
            stack2.push(tmp);
            stack1.addAll(tree[tmp]);
        }

        while (!stack2.empty()) {
            int parent = stack2.pop();

            size[parent]++;
            for (Integer child : tree[parent]) {
                size[parent] += size[child];
            }
        }
    }

    static class Node {
        int current;
        int parent;

        Node(int current, int parent) {
            this.current = current;
            this.parent = parent;
        }
    }
}
