package silver1;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
bfs문제
 */

public class _14940 {
    private final static int[] DX = { 1, 0, -1, 0 };
    private final static int[] DY = { 0, -1, 0, 1 };
    private static int[][] map;
    private static int[][] distance;
    private static boolean[][] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        distance = new int[n][m];
        isVisited = new boolean[n][m];
        int startX = 0;
        int startY = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st1.nextToken());
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }

        bfs(startX, startY, n, m);
        printResult(n, m);
    }

    private static void bfs(int startX, int startY, int n, int m) {
        Queue<Point> queue = new LinkedList<>();
        isVisited = new boolean[n][m];

        queue.add(new Point(startX, startY));
        isVisited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + DX[i];
                int nextY = current.y + DY[i];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m
                    || map[nextX][nextY] == 0 || isVisited[nextX][nextY]) {
                    continue;
                }

                queue.add(new Point(nextX, nextY));
                distance[nextX][nextY] = distance[current.x][current.y] + 1;
                isVisited[nextX][nextY] = true;
            }
        }
    }

    private static void printResult(int n, int m) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isVisited[i][j] && map[i][j] == 1) {
                    bw.write(-1 + " ");
                } else {
                    bw.write(distance[i][j] + " ");
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
