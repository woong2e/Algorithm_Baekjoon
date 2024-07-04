package gold4;

import java.io.*;
import java.util.*;

public class _14500 {
    static ArrayList<int[][]> polyomino = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        addPolyomino();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer stM = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(stM.nextToken());
            }
        }

        int maxSum = findMaxSum(paper, N, M);

        bw.write(String.valueOf(maxSum));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findMaxSum(int[][] paper, int N, int M) {
        int maxSum = 0;

        Iterator it = polyomino.iterator();
        while (it.hasNext()) {
            int[][] poly = (int[][]) it.next();

            for (int i = 0; i < N; i++) {
                try {
                    for (int j = 0; j < M; j++) {
                        try {
                            int tmp = paper[poly[0][0] + i][poly[1][0] + j] + paper[poly[0][1] + i][poly[1][1] + j] +
                                    paper[poly[0][2] + i][poly[1][2] + j] + paper[poly[0][3] + i][poly[1][3] + j];
                            if (tmp > maxSum) {
                                maxSum = tmp;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            break;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }
        return maxSum;
    }

    static void addPolyomino() {
        polyomino.add(new int[][]{{0,0,0,0},{0,1,2,3}});
        polyomino.add(new int[][]{{0,1,2,3},{0,0,0,0}});

        polyomino.add(new int[][]{{0,0,1,1},{0,1,0,1}});

        polyomino.add(new int[][]{{0,1,2,2},{0,0,0,1}});
        polyomino.add(new int[][]{{1,1,1,0},{0,1,2,2}});
        polyomino.add(new int[][]{{0,0,1,2},{0,1,1,1}});
        polyomino.add(new int[][]{{1,0,0,0},{0,0,1,2}});
        polyomino.add(new int[][]{{2,2,1,0},{0,1,1,1}});
        polyomino.add(new int[][]{{0,0,0,1},{0,1,2,2}});
        polyomino.add(new int[][]{{0,0,1,2},{1,0,0,0}});
        polyomino.add(new int[][]{{0,1,1,1},{0,0,1,2}});

        polyomino.add(new int[][]{{0,1,1,2},{0,0,1,1}});
        polyomino.add(new int[][]{{1,1,0,0},{0,1,1,2}});
        polyomino.add(new int[][]{{0,1,1,2},{1,1,0,0}});
        polyomino.add(new int[][]{{0,0,1,1},{0,1,1,2}});

        polyomino.add(new int[][]{{0,0,0,1},{0,1,2,1}});
        polyomino.add(new int[][]{{0,1,2,1},{0,0,0,1}});
        polyomino.add(new int[][]{{0,1,2,1},{1,1,1,0}});
        polyomino.add(new int[][]{{1,1,1,0},{0,1,2,1}});
    }
}
