package gold1;

import java.io.*;
import java.util.Arrays;

/*
시간복잡도 O(N^2)

1. 펠린드롬 판별(DP)
모든 구간 (i, j)에 대해 팰린드롬 여부를 미리 계산하여 O(1)로 조회할 수 있게 만든다.
부분 문자열의 길이(l)를 1부터 len까지 점차 늘려가며 Bottom-up 방식으로 채운다.
string[start-1] == string[end-1] && palindrome[start+1][end-1]
(양 끝 문자가 같고, 그 안쪽 문자열이 팰린드롬이면 전체도 팰린드롬이다)

2. 최소 분할 횟수 (DP)
count 배열을 무한대(Integer.MAX_VALUE)로 초기화해 둔다.
구간 (i, j)가 팰린드롬이라면, j까지의 최소 분할 횟수(count[j])는 i-1까지의 최소 분할 횟수 + 1과 기존 count[j] 중 더 작은 값으로 갱신한다.
 */
public class _1509 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] string = br.readLine().toCharArray();
        int len = string.length;

        boolean[][] palindrome = buildPalindromeDP(string);

        int[] count = new int[len + 1];
        Arrays.fill(count, Integer.MAX_VALUE);
        count[0] = 0;
        for (int i = 1; i < len + 1; i++) {
            for (int j = i; j < len + 1; j++) {
                if (palindrome[i][j] && count[j] > count[i - 1] + 1) {
                    count[j] = count[i - 1] + 1;
                }
            }
        }

        bw.write(String.valueOf(count[len]));
        bw.flush();

        br.close();
        bw.close();
    }

    private static boolean[][] buildPalindromeDP(char[] string) {
        int len = string.length;
        boolean[][] palindrome = new boolean[len + 1][len + 1];

        for (int i = 1; i <= len; i++) {
            palindrome[i][i] = true;
        }

        for (int i = 1; i < len; i++) {
            if (string[i - 1] == string[i]) {
                palindrome[i][i + 1] = true;
            }
        }

        for (int l = 3; l <= len; l++) {
            for (int start = 1; start <= len - l + 1; start++) {
                int end = start + l - 1;

                if (string[start - 1] == string[end - 1] && palindrome[start + 1][end - 1]) {
                    palindrome[start][end] = true;
                }
            }
        }

        return palindrome;
    }
}
