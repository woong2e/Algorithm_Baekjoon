package gold5;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
bfs를 이용한 그래프 탐색 문제이다. Queue를 사용하여 구현하였다.
저장될 때부터 모든 토마토가 익어있는 상태라면 0을 출력하라고 한 것은 출제자의 배려 같다. ㅎㅎ~^^
 */
public class _7576 {
    static final int[][] uDLR = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());


        int[][] box = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer row = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(row.nextToken());
                if (box[i][j] == 1) {
                    queue.add(new int[]{i,j});
                }
            }
        }
        int date = bfs(box, N, M, queue);

        bw.write(String.valueOf(date));
        bw.flush();

        br.close();
        bw.close();
    }

    static int bfs(int[][] box, int N, int M, Queue<int[]> queue) {
       while (!queue.isEmpty()) {
           int[] tmp = queue.poll();
           int presX = tmp[1];
           int presY = tmp[0];

           for (int i = 0; i < 4; i++) {
               int nextX = presX + uDLR[i][1];
               int nextY = presY + uDLR[i][0];

               if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) {
                   continue;
               }
               if (box[nextY][nextX] == 0) {
                   box[nextY][nextX] = box[presY][presX] + 1;
                   queue.add(new int[]{nextY, nextX});
               }
           }
       }
        int max = Integer.MIN_VALUE;

        if (hasZero(box,N, M)) {
            return -1;
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (box[i][j] > max) {
                        max = box[i][j];
                    }
                }
            }
        }
        return max - 1;
    }

    static boolean hasZero(int[][] box, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
