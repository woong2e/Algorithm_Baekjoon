package silver4;

import java.io.*;
import java.util.StringTokenizer;

public class _1018 {
    static char[][] white = new char[8][8];
    static char[][] black = new char[8][8];
    public static void main(String[] args) throws IOException {
        String W = "WBWBWBWB";
        String B = "BWBWBWBW";
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                white[i] = W.toCharArray();
                black[i] = B.toCharArray();
            } else {
                white[i] = B.toCharArray();
                black[i] = W.toCharArray();
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            board[i] = s.toCharArray();
        }
        int num = countMinimumValue(N, M, board);

        bw.write(String.valueOf(num));
        bw.flush();

        br.close();
    }

    static int countMinimumValue(int n, int m, char[][] board) {
        int minCnt = Integer.MAX_VALUE;
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                int diff = compareChar(i, j, board);
                if (minCnt > diff) {
                    minCnt = diff;
                }
            }
        }
        return minCnt;
    }

    static int compareChar(int row, int col, char[][] board) {
        int WCnt = 0;
        int BCnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[row + i][col + j] != white[i][j]) {
                    WCnt++;
                }
                if (board[row + i][col + j] != black[i][j]) {
                    BCnt++;
                }
            }
        }
        return Math.min(WCnt, BCnt);
    }
}
