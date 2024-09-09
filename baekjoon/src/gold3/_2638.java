package gold3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
그래프 문제
문제 해결 관건은 치즈의 내부와 내부를 어떻게 나눌 것인지
(0,0)에서 bfs로 치즈 외부를 파악해서 치즈가 다 녹을 때 까지 반복
 */

public class _2638 {
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};
    private static final int EXTERNAL_VALUE = Short.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graphPaper = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graphPaper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hour = meltAllCheese(graphPaper, N, M);

        bw.write(String.valueOf(hour));
        bw.flush();

        br.close();
        bw.close();

    }

    private static int meltAllCheese(int[][] graphPaper, int N, int M) {
        int hour = 0;

        while (!bfs(graphPaper, N, M)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++){
                    if (graphPaper[i][j] == 1) {
                        int sum = 0;
                        for (int k = 0; k < 4; k++) {
                            sum += graphPaper[i + DX[k]][j + DY[k]];
                        }
                        if (sum >= 2 * EXTERNAL_VALUE) {
                            graphPaper[i][j] = 0;
                        }
                    }
                }
            }
            hour++;
        }

        return hour;
    }

    private static boolean bfs(int[][] graphPaper, int N, int M) {
        Queue<int[]> queue = new LinkedList<>();
        int[] start = {0, 0};
        boolean[][] visited = new boolean[N][M];

        queue.add(start);
        visited[start[0]][start[1]] = true;
        graphPaper[start[0]][start[1]] = EXTERNAL_VALUE;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int tmpX = tmp[0];
            int tmpY = tmp[1];

            for (int i = 0; i < 4; i++) {
                if (0 <= tmpX + DX[i] && tmpX + DX[i] < N
                        && 0 <= tmpY + DY[i] && tmpY + DY[i] < M
                        && !visited[tmpX + DX[i]][tmpY + DY[i]]
                        && graphPaper[tmpX + DX[i]][tmpY + DY[i]] != 1) {
                    graphPaper[tmpX + DX[i]][tmpY + DY[i]] = EXTERNAL_VALUE;
                    queue.add(new int[]{tmpX + DX[i], tmpY + DY[i]});
                    visited[tmpX + DX[i]][tmpY + DY[i]] = true;
                }
            }
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt += graphPaper[i][j];
            }
        }

        return cnt == EXTERNAL_VALUE * N * M;
    }
}
