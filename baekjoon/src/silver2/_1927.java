package silver2;

import java.io.*;
import java.util.PriorityQueue;
/*
우선순위큐 이용하는 문제
 */

public class _1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp == 0) {
                if (pq.isEmpty()) {
                    bw.write(0 + "\n");
                } else {
                    bw.write(pq.poll() + "\n");
                }
                bw.flush();
            } else {
                pq.add(tmp);
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
