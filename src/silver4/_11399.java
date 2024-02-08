package silver4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        int minTimeSum = findMinTime(P, N);

        bw.write(String.valueOf(minTimeSum));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findMinTime(int[] P, int N) {
        Arrays.sort(P);

        int sum = 0;
        int n = N;
        for (int i = 0; i < N; i++) {
            sum += P[i] * n--;
        }

        return sum;
    }
}
