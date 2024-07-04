package silver1;

import java.io.*;
import java.util.StringTokenizer;

public class _1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int n = findOrder(0, N, r, c);

        bw.write(String.valueOf(n));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findOrder(int top, int N, int r, int c){
        if (N == 1) {
            if (r == 1 && c == 1) {
                return top + 3;
            } else if (r == 1) {
                return top + 2;
            } else if (c == 1) {
                return top + 1;
            } else {
                return top;
            }
        } else {
            if (r < Math.pow(2, N - 1) && c < Math.pow(2, N - 1)) {
                return findOrder(top, N - 1, r, c);
            } else if (r < Math.pow(2, N - 1) && c >= Math.pow(2, N - 1)) {
                return findOrder((int)(top + Math.pow(4, N - 1)), N - 1, r, (int)(c - Math.pow(2, N - 1)));
            } else if (r >= Math.pow(2, N - 1) && c < Math.pow(2, N - 1)) {
                return findOrder((int)(top + 2*Math.pow(4, N - 1)), N - 1, (int)(r - Math.pow(2, N - 1)), c);
            } else {
                return findOrder((int)(top + 3*Math.pow(4, N - 1)), N - 1, (int)(r - Math.pow(2, N - 1)), (int)(c - Math.pow(2, N - 1)));
            }
        }
    }
}
