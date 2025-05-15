package gold3;

import java.io.*;
import java.util.Arrays;

/*
DP
memoization bottom up 방식
3차원 배열을 사용하여(인덱스 : 게임 지시 순서대로 인덱스 부여(그러면 0은 처음 양발이 둘 다 0에 있을때임), 왼발 위치, 오른발 위치)
발판을 밟는 순서대로 모든 단계를 계산한다.
가능한 움직임에 대해 왼발과 오른발의 위치를 계산한 최소값을 갱신한다.
 */

public class _2342 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] directions = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int power = danceDanceRevolution(directions);

        bw.write(String.valueOf(power));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int danceDanceRevolution(int[] directions) {
        int n = directions.length;
        int[][][] dp = new int[n][5][5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = n * 4;
                }
            }
        }
        dp[0][0][0] = 0;

        for (int i = 0; i < n - 1; i++) {
            int next = directions[i];

            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    dp[i+1][left][next] = Math.min(dp[i+1][left][next], dp[i][left][right] + move(right, next));
                    dp[i+1][next][right] = Math.min(dp[i+1][next][right], dp[i][left][right] + move(left, next));
                }
            }
        }

        int minPower = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            minPower = Math.min(dp[n-1][directions[n-2]][i], minPower);
        }

        return minPower;
    }

    private static int move(int current, int next) {
        if (current == 0) {
            return 2;
        } else if (current == next) {
            return 1;
        } else if (Math.abs(current - next) == 2) {
            return 4;
        }
        return 3;
    }
}
