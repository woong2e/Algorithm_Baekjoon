package gold4;

import java.io.*;
import java.util.StringTokenizer;

/*
투 포인터, 누적합
누적합을 전부 다 계산하는 것이 아니라 이미 더한 것을 빼주고(left) 새로 더해주는 것(right)을 카운트해서 구한다.
 */

public class _1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minLen = subSequenceSumMinCount(arr, N, S);
        bw.write(String.valueOf(minLen));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int subSequenceSumMinCount(int[] arr, int N, int S) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        while (true) {
            if (sum < S) {
                if (right == N) {
                    break;
                }
                sum += arr[right++];
            } else {
                ans = Math.min(ans, right - left);
                sum -= arr[left++];
            }
        }

        if (ans == Integer.MAX_VALUE) {
            return 0;
        }
        return ans;
    }
}
