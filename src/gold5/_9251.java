package gold5;

import java.io.*;
/*
Longest Common Subsequence, 최장 공통 부분 수열 문제 (DP 문제)
문자가 같을 경우 L[m][n] = L[m-1][n-1] + 1
다를경우....... L[m][n] = L[m][n-1] 와 L[m-1][n]을 비교하여 최대값을 저장해 나간다.
 */

public class _9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int maxLongestCommonSubsequenceLength = LCS(str1, str2);

        bw.write(String.valueOf(maxLongestCommonSubsequenceLength));
        bw.flush();

        br.close();
        bw.close();
    }

    static int LCS(String str1, String str2) {
        int[][] len = new int[str1.length()+1][str2.length()+1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    len[i][j] = len[i-1][j-1] + 1;
                } else {
                    len[i][j] = Math.max(len[i-1][j], len[i][j-1]);
                }
            }
        }

        return len[str1.length()][str2.length()];
    }
}
