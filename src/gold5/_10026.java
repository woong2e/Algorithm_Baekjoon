package gold5;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
/*
큐에 add할 때 visited까지 true로 바꿔놓아, 큐에 중복 삽입되는 것을 방지하여 메모리 초과 해결
 */

public class _10026 {
    static final int[][] uDLR = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String[] picture = new String[N];
        for (int i = 0; i < N; i++) {
            picture[i] = br.readLine();
        }

        int personCount = countArea(picture, N,  false);
        int colorBlindPersonCount = countArea(picture, N,  true);

        bw.write(personCount + " " + colorBlindPersonCount);
        bw.flush();

        br.close();
        bw.close();
    }

    static int countArea(String[] picture, int N,  boolean hasColorBlind) {
        if (hasColorBlind) {
            for (int i = 0; i < N; i++) {
                picture[i] = picture[i].replace("R", "G");
            }
        }

        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                if (!visited[m][n]) {
                    cnt++;
                    queue.add(new int[]{m, n});
                    char tmpChar = picture[m].charAt(n);
                    visited[m][n] = true;

                    while (!queue.isEmpty()) {
                        int[] tmp = queue.poll();
                        int presX = tmp[1];
                        int presY = tmp[0];

                        for (int i = 0; i < 4; i++) {
                            int nextX = presX + uDLR[i][1];
                            int nextY = presY + uDLR[i][0];

                            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                                continue;
                            }
                            if (picture[nextY].charAt(nextX) == tmpChar && !visited[nextY][nextX]) {
                                visited[nextY][nextX] = true;
                                queue.add(new int[]{nextY, nextX});
                            }
                        }

                    }
                }
            }
        }
        return cnt;
    }
}
