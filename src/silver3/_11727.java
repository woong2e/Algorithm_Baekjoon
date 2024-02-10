package silver3;

import java.io.*;
/*
dp문제 메모이제이션 방식으로 풀었다.
점화식 찾으면 해결되는 문제
근본은 피보나치 매커니즘이기 때문이다.
따라서 수가 엄청나게 커지는 것을 방지하기 위해 해 i번째 값을 구할 때 마다 10007로 나눈 나머지를 저장했다.
 */

public class _11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int num = dp(n);

        bw.write(String.valueOf(num));
        bw.flush();

        br.close();
        bw.close();

    }

    static int dp(int n) {
        int[] mem = new int[n + 2];
        mem[1] = 1;
        mem[2] = 3;
        for (int i = 3; i < n + 1; i++) {
            mem[i] = (mem[i - 1] + 2 * mem[i - 2]) % 10007;
        }

        return mem[n];
    }
}
