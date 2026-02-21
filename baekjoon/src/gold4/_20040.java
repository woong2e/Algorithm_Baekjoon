package gold4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
간선(선분)을 하나씩 연결할 때마다 양 끝점의 최상위 부모(Root)를 find()로 확인한다.
만약 두 노드의 부모가 이미 같다면? 이미 다른 경로로 연결되어 있는 상태이므로, 지금 이 간선을 잇는 순간 사이클이 완성된다. (이때의 차례 i + 1을 반환하고 종료)
부모가 다르다면 사이클이 아니므로 union()으로 두 집합을 합친다.
parent[p] = find(parent[p])로 경로 압축
 */

public class _20040 {

    static class Line {
        int p1;
        int p2;

        Line(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] NM = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Line> games = new ArrayList<>();

        for (int i = 0; i < NM[1]; i++) {
            int[] line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            games.add(new Line(line[0], line[1]));
        }

        int result = startGame(NM[0], NM[1], games);

        bw.write(String.valueOf(result));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int startGame(int n, int m, List<Line> games) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            Line game = games.get(i);
            if (find(game.p1) == find(game.p2)) {
                return i + 1;
            }

            union(game.p1, game.p2);
        }

        return 0;
    }

    private static void union(int p1, int p2) {
        int pp1 = find(p1);
        int pp2 = find(p2);

        if (pp1 != pp2) {
            parent[pp2] = pp1;
        }
    }

    private static int find(int p) {
        if (parent[p] == p) {
            return p;
        }
        return parent[p] = find(parent[p]);
    }
}
