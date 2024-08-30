package gold3;

import java.io.*;
import java.util.*;

/*
우선순위 큐에 n개의 항목을 삽입 하는 것은
점근적 복잡도 O(nlogn)를 가지므로 복잡도 측면 에서 결국 한 번 사용하는 것보다 효율적이지 않다.
즉 정렬이 최종적으로 한번 만 필요한 경우에는 vector 나 array를 한 번 sort 해주는 게 효율적이다.
반대로 원소의 insert와 pop이 빈번한 경우는 우선순위큐를 이용해 효율적으로 알고리즘을 구성할 수 있다.
Comparator를 활용하여 먹이의 우선순위 공간을 선택하도록 한다
@문제 해결 방식
현재 위치한 칸에서 아기 상어의 크기를 고려하여 먹을 수 있는 먹이를 골라 정렬 후 조건을 가장 충족하는 칸에 가서 먹이를 먹는다.
이 방식을 더이상 먹을 먹이가 없을 때 까지 반복
 */

public class _16236 {
    static class Space {
        int row;
        int col;
        int distanceTo;

        public Space(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distanceTo = distance;
        }
    }

    static final int[] moveRow = {1, 0, -1, 0};
    static final int[] moveCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int sharkRowPos = 0;
        int sharkColPos = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkRowPos = i;
                    sharkColPos = j;
                }
            }
        }

        int time = bfs(map, N, sharkRowPos,  sharkColPos);

        bw.write(String.valueOf(time));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int bfs(int[][] map, int N, int sharkRowPos, int sharkColPos) {
        int time = 0;
        int sharkSize = 2;
        int eatingCount = 0;

        while (true) {
            if (eatingCount == sharkSize) {
                sharkSize++;
                eatingCount = 0;
            }
            Queue<Space> queue = new LinkedList<>();
            List<Space> prey = new ArrayList<>();
            boolean[][] visited = new boolean[N][N];

            visited[sharkRowPos][sharkColPos] = true;
            queue.add(new Space(sharkRowPos, sharkColPos, 0));

            while (!queue.isEmpty()) {
                Space current =  queue.poll();
                int currentDistanceTo = current.distanceTo;

                for (int i = 0; i < 4; i++) {
                    int nextRow = current.row + moveRow[i];
                    int nextCol = current.col + moveCol[i];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) {
                        continue;
                    }
                    if (visited[nextRow][nextCol]) {
                        continue;
                    }
                    visited[nextRow][nextCol] = true;

                    if (map[nextRow][nextCol] <= sharkSize) {
                        queue.add(new Space(nextRow, nextCol, currentDistanceTo + 1));
                        if (map[nextRow][nextCol] > 0 && map[nextRow][nextCol] < sharkSize) {
                            prey.add(new Space(nextRow, nextCol, currentDistanceTo + 1));
                        }
                    }
                }
            }
            if (prey.size() == 0) {
                return time;
            } else {
                sortList(prey);
                Space nextEatingSpace = prey.get(0);

                time += nextEatingSpace.distanceTo;
                eatingCount++;

                map[sharkRowPos][sharkColPos] = 0;
                sharkRowPos = nextEatingSpace.row;
                sharkColPos = nextEatingSpace.col;
                map[sharkRowPos][sharkColPos] = 9;

            }
        }
    }

    private static void sortList(List<Space> prey) {
        Collections.sort(prey, new Comparator<Space>() {
            @Override
            public int compare(Space o1, Space o2) {
                if (o1.distanceTo == o2.distanceTo) {
                    if (o1.row == o2.row) {
                        return o1.col - o2.col;
                    } else {
                        return o1.row - o2.row;
                    }
                } else {
                    return o1.distanceTo - o2.distanceTo;
                }
            }
        });
    }
}
