package gold4;

import java.io.*;
import java.util.Arrays;
/*
DP문제
해결방법:
부분 수열이 바이토닉 수열이기 위해
왼쪽, 오른쪽 각각 시작을 기준으로 부분 수열의 최대 길이를 구한다 - memoization
왼쪽과 오른쪽 각각 기준에서 어느 지점이 부분 수열의 길이의 합이 최대인지 구한다.
 */

public class _11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxBitonicSubsequenceLength = dp(A, N);

        bw.write(String.valueOf(maxBitonicSubsequenceLength));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int dp(int[] A, int N) {
        int[][] leftSubsequenceLength = new int[N + 1][N + 1];
        int[][] rightSubsequenceLength = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            leftSubsequenceLength[0][i] = 1;
            rightSubsequenceLength[0][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                if (A[i - 1] >= A[j - 1]) {
                    leftSubsequenceLength[i][j] = leftSubsequenceLength[i - 1][j];
                } else {
                    if (leftSubsequenceLength[i][i] + 1 > leftSubsequenceLength[i - 1][j]) {
                        leftSubsequenceLength[i][j] = leftSubsequenceLength[i][i] + 1;
                    } else {
                        leftSubsequenceLength[i][j] = leftSubsequenceLength[i - 1][j];
                    }
                }

                if (A[N - i] >= A[N - j]) {
                    rightSubsequenceLength[i][j] = rightSubsequenceLength[i - 1][j];
                } else {
                    if (rightSubsequenceLength[i][i] + 1 > rightSubsequenceLength[i - 1][j]) {
                        rightSubsequenceLength[i][j] = rightSubsequenceLength[i][i] + 1;
                    } else {
                        rightSubsequenceLength[i][j] = rightSubsequenceLength[i - 1][j];
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (max < leftSubsequenceLength[i][i] + rightSubsequenceLength[N + 1 - i][N + 1 - i] - 1) {
                max = leftSubsequenceLength[i][i] + rightSubsequenceLength[N + 1 - i][N + 1 - i] - 1;
            }
        }

        return max;
    }
}
