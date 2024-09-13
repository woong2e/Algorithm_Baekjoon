package silver1;

import java.io.*;
import java.util.StringTokenizer;
/*
DP문제
table 각 위치 별로 (1,1)부터의 누적합을 구해놓으면 쉽게 구할 수 있음.
 */

public class _11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] table = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] startEnd = new int[M][4];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                startEnd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] accumulatedSumTable = calculateAccumulatedSum(table, startEnd, N, M);
        for (int i = 0; i < M; i++){
            bw.write(String.valueOf(accumulatedSumTable[i]));
            if (i < M - 1) {
                bw.write("\n");
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }

    private static int[] calculateAccumulatedSum(int[][] table, int[][] startEnd, int N, int M) {
        int[] accumulatedSum = new int[M];
        int[][] accumulatedSumTable = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                accumulatedSumTable[i][j] = table[i][j] +
                        accumulatedSumTable[i-1][j] + accumulatedSumTable[i][j-1] - accumulatedSumTable[i-1][j-1];
            }
        }

        for (int i = 0; i < M; i++) {
            int[] tmp = startEnd[i];
            accumulatedSum[i] = accumulatedSumTable[tmp[2]][tmp[3]]
                    - accumulatedSumTable[tmp[0]-1][tmp[3]] - accumulatedSumTable[tmp[2]][tmp[1]-1] +  accumulatedSumTable[tmp[0]-1][tmp[1]-1];
        }
        return accumulatedSum;
    }
}
