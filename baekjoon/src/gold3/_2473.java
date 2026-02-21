package gold3;

import java.io.*;
import java.util.Arrays;

/*
투 포인터, 이분탐색, 정렬
3개의 용액을 섞어야 해서 어떻게 투포인터로 풀지 고민함.
1. 완전 탐색을 할 경우 O(N^3)으로 시간초과 날듯 함.
2. 하나를 고정하고 나머지 두개를 완전 탐색을 해도 시간초과남
3. 그래서 정렬을 하고 하나를 고정해서 고정한 값 뒤로 양쪽에 포인터를 둠
4. 합이 0보다 클 경우 오른쪽 포인터를 감소시켜서 합을 작게, 합이 0보다 클 경우 왼쪽 포인터를 증가시켜서 합을 크게 만드는 방식으로 함
 */

public class _2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        long[] values = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();

        String result = blend(values, N);

        bw.write(result);
        bw.flush();

        br.close();
        bw.close();
    }

    private static String blend(long[] values, int N) {
        StringBuilder sb = new StringBuilder();
        long[] result = new long[3];
        long hap = Long.MAX_VALUE;
        for (int fixedPointer = 0; fixedPointer < N - 2; fixedPointer++) {
            int leftPointer = fixedPointer + 1;
            int rightPointer = N - 1;
            long tmpHap = values[fixedPointer] + values[leftPointer] + values[rightPointer];

            while (leftPointer < rightPointer) {
                if (Math.abs(tmpHap) < Math.abs(hap)) {
                    result[0] = values[fixedPointer];
                    result[1] = values[leftPointer];
                    result[2] = values[rightPointer];
                    hap = values[fixedPointer] + values[leftPointer] + values[rightPointer];
                }

                if (tmpHap > 0) {
                    tmpHap -= (values[rightPointer] - values[--rightPointer]);
                } else if (tmpHap < 0) {
                    tmpHap -= (values[leftPointer] - values[++leftPointer]);
                } else {
                    Arrays.stream(result).forEach((value) -> sb.append(value).append(" "));
                    return String.valueOf(sb);
                }
            }
        }

        Arrays.stream(result).forEach((value) -> sb.append(value).append(" "));
        return String.valueOf(sb);
    }
}
