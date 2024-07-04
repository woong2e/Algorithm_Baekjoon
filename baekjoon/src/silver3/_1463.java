package silver3;

import java.io.*;

public class _1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int n = findNumber(N);

        bw.write(String.valueOf(n));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findNumber(int N) {
        int[] mem = new int[N+1];
        for (int i = 2; i < N + 1; i++) {
            if (i % 6 == 0) {
                mem[i] = Math.min(Math.min(mem[i/3], mem[i/2]), mem[i-1]) + 1;
            } else if (i % 3 == 0) {
                mem[i] = Math.min(mem[i/3], mem[i-1]) + 1;
            } else if (i % 2 == 0) {
                mem[i] = Math.min(mem[i/2], mem[i-1]) + 1;
            } else {
                mem[i] = mem[i-1] + 1;
            }

            if (mem[N] > 0) {
                break;
            }
        }
        return mem[N];
    }
}
