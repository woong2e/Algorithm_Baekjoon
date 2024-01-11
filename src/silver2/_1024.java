package silver2;

import java.io.*;
import java.util.StringTokenizer;

public class _1024 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        String anw = findShortestSequence(N, L);

        if (anw.equals("-1")) {
            bw.write(anw);
        } else {
            int start = Integer.parseInt(anw.split(",")[0]);
            int cnt = Integer.parseInt(anw.split(",")[1]);
            for (int i = start; i < start + cnt; i++) {
                bw.write(String.valueOf(i) + " ");
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }

    static String findShortestSequence(int N, int L) {
        if (N - L*(L-1)/2 < 0 || L > 100) {
            return "-1";
        } else {
            double x = (double)(N - L*(L-1)/2)/(double)L;
            int y = (N - L*(L-1)/2)/L;
            if (x != y) {
                return findShortestSequence(N, L+1);
            } else {
                return y + "," + L;
            }
        }
    }

}
