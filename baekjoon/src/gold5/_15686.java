package gold5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
백트래킹 - 가지치기하는 과정에서 해가 아니라고 판단될 경우 그 경로를 더이상 가지 않고 되돌아 간다.
백트래킹으로 모든 경우의 수를 확인해야하는 브루트포스 알고리즘
집과 치킨집의 좌표를 뽑아내, M개의 치킨집이 열었을 때만
도시의 치킨 거리의 값을 구해 모든 경우의 수를 확인하여 최소값을 구한다.
 */

public class _15686 {
    private static final int MAX_DISTANCE = Integer.MAX_VALUE;
    private static int[] NM;
    private static List<Point> house;
    private static List<Point> chickenHouse;
    private static boolean[] isOpen;
    private static int chickenDistanceOfCity;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        NM = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        house = new ArrayList<>(2 * NM[0]);
        chickenHouse = new ArrayList<>(13);

        for (int i = 1; i <= NM[0]; i++) {
            int[] cityLine = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 1; j <= NM[0]; j++) {
                if (cityLine[j - 1] == 1) {
                    house.add(new Point(i, j));
                } else if (cityLine[j - 1] == 2) {
                    chickenHouse.add(new Point(i, j));
                }
            }
        }

        chickenDistanceOfCity = MAX_DISTANCE;
        isOpen = new boolean[chickenHouse.size()];
        shutChickenHouseDownDfsBack(0, 0);

        bw.write(String.valueOf(chickenDistanceOfCity));
        bw.flush();

        br.close();
        bw.close();
    }

    private static void shutChickenHouseDownDfsBack(int start, int cnt) {
        if (cnt == NM[1]) {
            int chickenDistance = 0;
            for (Point housePoint : house) {
                int manhattanDistance = MAX_DISTANCE;
                for (int i = 0; i < chickenHouse.size(); i++) {
                    if (isOpen[i]) {
                        int tmp = calculateManhattanDistance(housePoint, chickenHouse.get(i));
                        manhattanDistance = Math.min(manhattanDistance, tmp);
                    }
                }
                chickenDistance += manhattanDistance;
            }

            chickenDistanceOfCity = Math.min(chickenDistanceOfCity, chickenDistance);
            return;
        }

        for (int i = start; i < chickenHouse.size(); i++) {
            isOpen[i] = true;
            shutChickenHouseDownDfsBack(i + 1, cnt + 1);
            isOpen[i] = false;
        }
    }

    private static int calculateManhattanDistance(Point housePoint, Point chickenHousePoint) {
        return Math.abs(chickenHousePoint.r - housePoint.r)
                + Math.abs(chickenHousePoint.c - housePoint.c);
    }

    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
