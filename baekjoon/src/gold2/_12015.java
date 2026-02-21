package gold2;

import java.io.*;
import java.util.Arrays;

/*
가장 긴 증가하는 부분 수열(LIS)
가장 긴 증가하는 부분 수열은,
선행 원소보다 후행 원소가 커야한다.
부분 수열 내 상호 원소간 값이 차이가 적어야 한다. -> 그래야 많은 원소가 들어갈 수 있다.

전체 배열을 탐색하면서 LIS 배열이 들어갈 수 있는 위치를 찾아 대치한다.
LIS의 맨 끝 값보다 작을 경우 중간에 적당한 위치를 찾아 대치 -> 그래도 LIS배열의 길이가 변함이 없는 이유는 이미 대치하기 전 LIS가 최대 길이이기 때문
LIS의 맨 끝 값보다 클 경우 맨 끝에 추가
 */
public class _12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] array = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int result = maxIncreasingSubsequenceLength(array, N);

        bw.write(String.valueOf(result));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int maxIncreasingSubsequenceLength(int[] array, int N) {
        int lengthOfLIS = 1;

        int[] LIS = new int[N];
        LIS[0] = array[0];
        for (int i = 1; i < N; i++) {
            // 전체 배열을 탐색하면서 LIS 배열이 들어갈 수 있는 위치를 찾아 대치한다.
            if (LIS[lengthOfLIS - 1] < array[i]) { // LIS의 맨 끝 값보다 클 경우
                LIS[lengthOfLIS++] = array[i];  // 맨 끝에 추가
            } else {
                setNumberThroughBinarySearch(LIS, lengthOfLIS, array[i]); // LIS의 맨 끝 값보다 작을 경우 중간에 적당한 위치를 찾아 대치
            }
        }

        return lengthOfLIS;
    }

    // 적당한 위치를 찾기위해 이분 탐색을 통해 넣을려는 값(array[i])보다 큰 값의 최소 인덱스 값을 구해 값을 대치한다
    // 10 20 30에 15를 넣는경우 => 10 15 30 이렇게 됨
    private static void setNumberThroughBinarySearch(int[] LIS, int lengthOfLIS, int number) {
        int start = 0;
        int end = lengthOfLIS;

        while (start < end) {
            int mid = (start + end) >>> 1;

            if (LIS[mid] < number) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        LIS[start] = number;
    }
}
