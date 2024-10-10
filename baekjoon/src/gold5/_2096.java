package gold5;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;
/*
DP문제
memoization을 통해 문제를 해결
최댓값과 최솟값을 각각 memoization하여 첫째 줄부터 계산해 나가면 됨
 */

public class _2096 {

    private static final int MAX_INT = Integer.MAX_VALUE;
    private static final int MIN_INT = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] table = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        String result = dp(table, N);
        bw.write(result);
        bw.flush();

        br.close();
        bw.close();
    }

    private static String dp(int[][] table, int N) {
        StringBuilder sb = new StringBuilder();
        int[][] minMem = new int[N + 1][3];
        int[][] maxMem = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(minMem[i], MAX_INT);
            Arrays.fill(maxMem[i], MIN_INT);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                int start = max(j - 1, 0);
                int end = min(j + 1, 2);
                for (int k = start; k <= end; k++) {
                    if (minMem[i - 1][k] + table[i][j] < minMem[i][j]) {
                        minMem[i][j] = minMem[i - 1][k] + table[i][j];
                    }

                    if (maxMem[i - 1][k] + table[i][j] > maxMem[i][j]) {
                        maxMem[i][j] = maxMem[i - 1][k] + table[i][j];
                    }
                }
            }
        }
        sb.append(max(max(maxMem[N][0], maxMem[N][1]), maxMem[N][2]))
                .append(" ")
                .append(min(min(minMem[N][0], minMem[N][1]), minMem[N][2]));

        return sb.toString();
    }
}
