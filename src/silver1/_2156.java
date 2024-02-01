package silver1;

import java.io.*;

public class _2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] drink = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            drink[i] = Integer.parseInt(br.readLine());
        }

        int v = findMaxVolume(N, drink);

        bw.write(String.valueOf(v));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findMaxVolume(int N, int[] drink) {
        int[][] V = new int[3][N+1];
        V[0][1] = 0;
        V[1][1] = drink[1];
        V[2][1] = drink[1];

        for (int j = 2; j <= N; j++) {
            V[0][j] = Math.max(Math.max(V[0][j-1], V[1][j-1]), V[2][j-1]);
            V[1][j] = V[0][j-1] + drink[j];
            V[2][j] = V[1][j-1] + drink[j];
        }

        return Math.max(Math.max(V[0][N], V[1][N]), V[2][N]);
    }

}
