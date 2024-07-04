package silver4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ;i++) {
            A[i] = Integer.parseInt(st1.nextToken());
            B[i] = Integer.parseInt(st2.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);
        int S = 0;
        for(int i = 0 ; i < N ;i++) {
            S += A[i] * B[N-1-i];
        }

        bw.write(String.valueOf(S));
        bw.flush();

        br.close();
        bw.close();
    }
}
