package silver3;

import java.io.*;

public class _2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
        int s = findMaxScore(N, score);

        bw.write(String.valueOf(s));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findMaxScore(int N, int[] score) {
        int[][] mem = new int [2][N+1];
        mem[0][1] = score[1];
        mem[1][1] = 0;

        for (int j = 2; j <= N; j++) {
            mem[0][j] = score[j] + Math.max(mem[0][j-2], mem[1][j-2]);
            mem[1][j] = score[j] + mem[0][j-1];
        }

        return Math.max(mem[0][N], mem[1][N]);
    }
}
