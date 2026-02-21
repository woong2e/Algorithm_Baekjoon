package gold4;

import java.io.*;
import java.util.StringTokenizer;

/*
시간복잡도 O(N^2 + M)

팰린드롬 판별 (DP)
모든 구간 (start, end)에 대해 팰린드롬 여부를 미리 계산하여 O(1)로 조회할 수 있게 만든다.
부분 수열의 길이(len)를 3부터 N까지 점차 늘려가며 Bottom-up 방식으로 채운다.
(길이가 1, 2인 경우는 미리 초기화해 둔다.)
numbers[start] == numbers[end] && palindrome[start+1][end-1] == 1
(양 끝 숫자가 같고, 그 안쪽 수열이 팰린드롬이면 전체 구간도 팰린드롬이다)
 */
public class _10942 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[][] palindrome = buildPalindromeDP(N, numbers);

        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            bw.write(palindrome[S - 1][E - 1] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int[][] buildPalindromeDP(int N, int[] numbers) {
        int[][] palindrome = new int[N][N];

        for (int i = 0; i < N; i++) {
            palindrome[i][i] = 1;
        }

        for (int i = 0; i < N - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                palindrome[i][i + 1] = 1;
            }
        }

        for (int len = 3; len <= N; len++) {
            for (int start = 0; start < N - len + 1; start++) {
                int end = start + len - 1;
                if (numbers[start] == numbers[end] && palindrome[start + 1][end - 1] == 1) {
                    palindrome[start][end] = 1;
                }
            }
        }

        return palindrome;
    }
}