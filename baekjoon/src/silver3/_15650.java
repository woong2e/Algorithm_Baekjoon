package silver3;

import java.io.*;
import java.util.StringTokenizer;
/*
백트레킹 문제 - dfs로 풀이
배열에 저장해두어 depth와 출력할 수열의 길이가 같아질 때 출력
 */

public class _15650 {
    private static int[] arr;
    private static int N;
    private static int M;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        dfs(1, 0);

        bw.write(String.valueOf(sb));
        bw.flush();

        br.close();
        bw.close();
    }

    private static void dfs(int start, int depth) {

        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            arr[depth] = i;
            dfs(i + 1, depth + 1);
        }

    }
}
