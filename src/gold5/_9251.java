package gold5;

import java.io.*;

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
