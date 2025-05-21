package gold4;

import java.io.*;

/*
최장 공통 부분수열 - LCS
memoization 으로 해결,
DP 배열 마지막 인덱스부터 거꾸로 탐색하여 LCS 를 찾을 수 있음
 */

public class _9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1 = br.readLine();
        String str2 = br.readLine();

        String lcs = getLCS(str1, str2);

        bw.write(lcs.length() + "\n");
        bw.write(lcs);
        bw.flush();

        br.close();
        bw.close();
    }

    private static String getLCS(String str1, String str2) {
        int[][] LCS = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i < str1.length() + 1; i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    LCS[i][j] = LCS[i-1][j-1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
                }
            }
        }


        int i = str1.length();
        int j = str2.length();
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (LCS[i][j] == LCS[i-1][j]) {
                i--;
            } else if (LCS[i][j] == LCS[i][j-1]) {
                j--;
            } else {
                i--;
                j--;
                sb.append(str1.charAt(i));
            }
        }

        return String.valueOf(sb.reverse());
    }
}
