package silver2;

import java.io.*;
import java.util.StringTokenizer;

/*
이분탐색 문제
@주의
low가 high보다 커질 때  high가 MAX_INTEGER인 경우  length의 자료형이 long이 될 수도 있음

@문제 해결 방식
1부터 주어진 랜선 중 최대값을 기준으로 하여 이분탐색을 해나가면서 그 값이 N개의 개수만큼 랜선을 만들 수 있는지 판단하여
만들 수 있는 경우 더 큰 범위를, 그렇지 않은 경우 더 작은 범위를 탐색.
 */

public class _1654 {
    private static int[] LANCables;
    private static int K;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        LANCables = new int[K];
        int maxLength = 0;
        for (int i = 0; i < K; i++) {
            LANCables[i] = Integer.parseInt(br.readLine());
            if (maxLength < LANCables[i]) {
                maxLength = LANCables[i];
            }
        }

        long maxLANLength = getMaxLANLength(1, maxLength);

        bw.write(String.valueOf(maxLANLength));
        bw.flush();

        br.close();
        bw.close();
    }

    private static long getMaxLANLength(long low, long high) {
        long length = (low + high) / 2;
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            cnt += LANCables[i] / length;
        }

        if (low > high) {
            return length;
        }

        if (cnt >= N) {
            return getMaxLANLength(length + 1, high);
        } else {
            return getMaxLANLength(low, length - 1);
        }
    }
}
