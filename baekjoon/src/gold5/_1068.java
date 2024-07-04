package gold5;

import java.io.*;
import java.util.StringTokenizer;

public class _1068 {
    static int N, cnt;
    static int[] parent;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int root = 0;
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        isVisited = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ;i++){
            int x = Integer.parseInt(st.nextToken());
            parent[i] = x;
            if(x == -1) {
                root = i;
            }
        }

        int remove = Integer.parseInt(br.readLine());
        removeNode(remove);
        cnt = 0;
        countLeaf(root);

        bw.write(String.valueOf(cnt));
        bw.flush();

        br.close();
        bw.close();
    }

    private static void countLeaf(int idx) {
        boolean isLeaf = true;
        isVisited[idx] = true;
        if(parent[idx] != -2){
            for(int i = 0 ; i < N ; i++){
                if(parent[i] == idx && !isVisited[i]){
                    countLeaf(i);
                    isLeaf = false;
                }
            }
            if(isLeaf) {
                cnt++;
            }
        }
    }

    static void removeNode(int idx) {
        parent[idx] = -2;

        for(int i = 0 ; i < N ; i++){
            if(parent[i] == idx)
                removeNode(i);
        }
    }
}

