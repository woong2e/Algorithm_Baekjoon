package silver3;

import java.io.*;
/*
DP알고리즘 문제
점화식 구하면 피보나치 수열이랑 똑같음
 */

public class _11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int num = count(n);

        bw.write(String.valueOf(num));
        bw.flush();


        br.close();
        bw.close();
    }

    static int count(int n) {
        int[] mem = new int[n+2];
        mem[1] = 1;
        mem[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            mem[i] = (mem[i - 2] + mem[i - 1]) % 10007;
        }

        return mem[n];
    }
}
