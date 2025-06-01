package gold5;

import java.io.*;
import java.util.Arrays;

/*
투 포인터 알고리즘
 */

public class _2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] values = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String result = mix(N, values);

        bw.write(result);
        bw.flush();

        br.close();
        bw.close();
    }

    private static String mix(int N, int[] values) {
        int[] result = new int[2];

        int leftPointer = 0;
        int rightPointer = N - 1;
        int minValue = Integer.MAX_VALUE;
        while (leftPointer < rightPointer) {
            int hap = Math.abs(values[leftPointer] + values[rightPointer]);
            if (hap < minValue) {
                minValue = hap;
                result[0] = values[leftPointer];
                result[1] = values[rightPointer];
                if (minValue == 0) {
                    break;
                }
            }

            if (values[leftPointer] + values[rightPointer] < 0) {
                leftPointer++;
            } else {
                rightPointer--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append(" ").append(result[1]);
        return String.valueOf(sb);
    }
}
