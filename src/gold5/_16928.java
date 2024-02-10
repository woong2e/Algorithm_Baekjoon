package gold5;

import java.io.*;
import java.util.*;

/*
그래프 탐색문제: bfs- Queue 자료구조 활용
주사위를 이동할 때 마다 count를 저장하기 위해 사용자 정의 자료형 Duo클래스 생성
 */

public class _16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> travel = new ArrayList<>();

        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        for (int i = 0; i < N + M; i++) {
            StringTokenizer startEnd = new StringTokenizer(br.readLine());
            start.add(Integer.parseInt(startEnd.nextToken()));
            end.add(Integer.parseInt(startEnd.nextToken()));
        }
        travel.add(start);
        travel.add(end);

        int MinRollOfDice = bfs(travel);

        bw.write(String.valueOf(MinRollOfDice));
        bw.flush();

        br.close();
        bw.close();
    }

    static int bfs(List<List<Integer>> travel) {
        Queue<Duo> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        int cnt = 0;
        queue.add(new Duo(1, 0));

        while (!queue.isEmpty()) {
            Duo tmp = queue.poll();
            if (travel.get(0).contains(tmp.num)) {
                tmp.num = travel.get(1).get(travel.get(0).indexOf(tmp.num));
            }
            if (tmp.num == 100) {
                cnt = tmp.cnt;
                break;
            }
            if (!visited[tmp.num]) {
                visited[tmp.num] = true;
                for (int i = 1; i <= 6; i++) {
                    if (tmp.num + i <= 100 && !visited[tmp.num + i]) {
                        queue.add(new Duo(tmp.num + i, tmp.cnt + 1));
                    }
                }
            }
        }
        return cnt;
    }
}

class Duo {
    int num;
    int cnt;

    Duo (int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}
