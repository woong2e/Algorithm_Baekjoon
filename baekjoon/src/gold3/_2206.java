package gold3;

import java.io.*;
import java.util.*;
/*
bfs문제
벽을 최대 한번만 부술 수 있는 조건이 있어
벽을 부순 적이 있는 경우와 없는 경우로 나눠 방문배열에 체크함
Point에 누적 거리, 벽을 부술 수 있는지 여부를 저장해야함
 */

public class _2206 {

    static class Point {
        int row;
        int column;
        int distance;
        boolean canBreakTheWall;

        public Point(int row, int column, int distance, boolean canBreakTheWall) {
            this.row = row;
            this.column = column;
            this.distance = distance;
            this.canBreakTheWall = canBreakTheWall;
        }
    }

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = str.charAt(j - 1) - '0';
            }
        }
        int minDistance = bfs(map, N, M);

        bw.write(String.valueOf(minDistance));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int bfs(int[][] map, int N, int M) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[2][N + 1][M + 1];
        visited[0][1][1] = true;

        queue.add(new Point(1, 1, 1, true));

        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            if (tmp.row == N && tmp.column == M) {
               return tmp.distance;
            }
            for (int i = 0; i < 4; i++) {
                int nextRow = tmp.row + DX[i];
                int nextColumn = tmp.column + DY[i];

                if (1 <= nextRow && nextRow <= N && 1 <= nextColumn && nextColumn <= M) {
                    if (map[nextRow][nextColumn] == 1) {
                        if (tmp.canBreakTheWall) {
                            if (!visited[1][nextRow][nextColumn]) {
                                visited[1][nextRow][nextColumn] = true;
                                queue.add(new Point(nextRow, nextColumn, tmp.distance + 1, false));
                            }
                        }
                    } else if (map[nextRow][nextColumn] == 0) {
                        if (tmp.canBreakTheWall) {
                            if (!visited[0][nextRow][nextColumn]) {
                                visited[0][nextRow][nextColumn] = true;
                                queue.add(new Point(nextRow, nextColumn, tmp.distance + 1, true));
                            }
                        } else {
                            if (!visited[1][nextRow][nextColumn]) {
                                visited[1][nextRow][nextColumn] = true;
                                queue.add(new Point(nextRow, nextColumn, tmp.distance + 1, false));
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
