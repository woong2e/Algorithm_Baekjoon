package silver4;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;
/*
그리디 알고리즘 문제
 */

public class _11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            stack.push(Integer.parseInt(br.readLine()));
        }

        int num = findMinNumberOfCoins(stack, K);

        bw.write(String.valueOf(num));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findMinNumberOfCoins(Stack<Integer> stack, int K) {
        int cnt = 0;
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            while (K - tmp >= 0) {
                K -= tmp;
                cnt++;
            }
        }


        return cnt;
    }
}
