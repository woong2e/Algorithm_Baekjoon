package gold4;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
최단경로 - dijkstra
다음 루트로 갈 시 기록된 누적 손해보다 더 클 경우(lossMap[nextX][nextY] <= lossMap[current.x][current.y] + cave[nextX][nextY])
더이상 진행하지 않는다
 */
public class _4485 {
    private static class Point {
        int x;
        int y;
        int loss;

        Point(int x, int y, int loss) {
            this.x = x;
            this.y = y;
            this.loss = loss;
        }
    }

    private static final String PROBLEM = "Problem ";
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int MAX_INTEGER = Integer.MAX_VALUE;

    private static int minValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int num = 1;
        while (num > 0) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            int[][] cave = new int[N][N];

            for (int i = 0; i < N; i++) {
                cave[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            minValue = MAX_INTEGER;
            int minLoss = dijkstra(cave, N);

            sb.setLength(0);
            sb.append(PROBLEM).append(num).append(": ").append(minLoss).append("\n");
            bw.write(String.valueOf(sb));
            bw.flush();
            num++;
        }

        br.close();
        bw.close();
    }

    private static int dijkstra(int[][] cave, int N) {
        int[][] lossMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(lossMap[i], MAX_INTEGER);
        }

        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.loss > o2.loss ? 1 : -1;
            }
        });
        pq.add(new Point(0, 0, cave[0][0]));
        lossMap[0][0] = cave[0][0];

        while (!pq.isEmpty()) {
            Point current = pq.poll();

            if (current.x == N - 1 && current.y == N - 1) {
                if (minValue < lossMap[current.x][current.y]) {
                    minValue = lossMap[current.x][current.y];
                    break;
                }
            }

            for (int[] direction : DIRECTIONS) {
                int nextX = current.x + direction[0];
                int nextY = current.y + direction[1];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
                    if (lossMap[nextX][nextY] > lossMap[current.x][current.y] + cave[nextX][nextY]) {
                        lossMap[nextX][nextY] = lossMap[current.x][current.y] + cave[nextX][nextY];
                        pq.add(new Point(nextX, nextY, cave[nextX][nextY]));
                    }
                }
            }
        }

        return Math.abs(lossMap[N - 1][N - 1]);
    }
}