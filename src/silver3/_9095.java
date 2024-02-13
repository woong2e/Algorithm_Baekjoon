package silver3;

import java.io.*;
/*
다이나믹 프로그래밍 알고리즘 문제
메모이제이션을 이용하여 해결
#피보나치수열
 */

public class _9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCase = Integer.parseInt(br.readLine());
        while (numTestCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int num = countKind(n);

            bw.write(num + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    static int countKind(int n) {
        int[] mem = new int[n+3];
        mem[1] = 1;
        mem[2] = 2;
        mem[3] = 4;

        for (int i = 4; i < n + 1; i++) {
            mem[i] = mem[i - 3] + mem[i - 2] + mem[i - 1];
        }

        return mem[n];
    }
}
