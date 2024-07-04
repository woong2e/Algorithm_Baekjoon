package silver3;

import java.io.*;

public class _1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCase = Integer.parseInt(br.readLine());

        while (numTestCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String cnt = fibonacci(n);

            bw.write(cnt + "\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }
    static String fibonacci(int n) {
        int[] zeroCount = new int[n + 1];
        int[] oneCount = new int[n + 1];

        if (n == 0) {
            zeroCount[0]++;
        } else if (n == 1) {
            oneCount[1]++;
        } else {
            zeroCount[0]++;
            oneCount[1]++;
            for (int i = 2; i < n + 1; i++) {
                zeroCount[i] = zeroCount[i - 1] + zeroCount[i - 2];
                oneCount[i] = oneCount[i - 1] + oneCount[i - 2];
            }
        }

        return zeroCount[n] + " " + oneCount[n];
    }
}
