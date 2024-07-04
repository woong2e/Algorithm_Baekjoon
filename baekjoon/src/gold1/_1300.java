package gold1;

import java.io.*;

public class _1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int low = findLow(N, k, 1, k);

        bw.write(String.valueOf(low));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findLow(int N, int k, int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;
            int cnt = 0;

            for (int i = 1; i <= N; i++) {
                cnt += Math.min(mid / i, N);
            }
            if (cnt < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
