package silver1;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

public class _1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] time = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        int n = findMaxNumber(time);

        bw.write(String.valueOf(n));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findMaxNumber(int[][] time) {
        Iterator<int[]> it = Arrays.stream(time).iterator();
        int cnt = 0;
        int end = 0;
        while (it.hasNext()) {
            int[] tmp = it.next();
            if (tmp[0] >= end) {
                end = tmp[1];
                cnt++;
            }
        }
        return cnt;
    }
}