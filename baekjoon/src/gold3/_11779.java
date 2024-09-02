package gold3;

import java.io.*;
import java.util.*;

/*
dijkstra 알고리즘 - 최단경로 찾기
우선순위 큐를 활용하여 가장 비용이 적게 드는 노드로 가는 방식을 통해 최단 경로를 구한다.
핵심은
if (tmpBus.cost > distance[tmpBus.arrivals]) {
                continue;
            }
------------> 가려고 하는 노드로의 비용이 가려고 하는 노드까지의 비용의 합보다 클 경우 진행하지 않도록 하여 불필요한 탐색을 막는다.
최단 경로 노드는 최소 비용으로 갱신될 때 이전 노드의 값을 저장해두고 최종적으로 마지막 노드에서부터 거꾸로 탐색하여 시작 노드까지 가는 경로로 구할 수 있다.

 */

public class _11779 {
    static class Bus implements Comparable<Bus> {
        int arrivals;
        int cost;

        public Bus(int arrivals, int cost) {
            this.arrivals = arrivals;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private static final int MAX_INTEGER = Integer.MAX_VALUE;
    private static int[] distance;
    private static int[] route;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Bus>[] list = new List[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int departures = Integer.parseInt(st.nextToken());
            int arrivals = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[departures].add(new Bus(arrivals, cost));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(list, n, start, end);

        StringBuilder sb = new StringBuilder();

        sb.append(distance[end]).append('\n');

        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(end);
        while (route[end] != 0) {
            cnt++;
            stack.push(route[end]);
            end = route[end];
        }

        sb.append(cnt + 1).append('\n');
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        bw.write(String.valueOf(sb));
        bw.flush();

        br.close();
        bw.close();
    }

    private static void dijkstra(List<Bus>[] list, int n, int start, int end) {
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.add(new Bus(start, 0));

        distance = new int[n + 1];
        route = new int[n + 1];
        Arrays.fill(distance, MAX_INTEGER);
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Bus tmpBus = pq.poll();

            if (tmpBus.arrivals == end) {
                break;
            }
            if (tmpBus.cost > distance[tmpBus.arrivals]) {
                continue;
            }

            List<Bus> tmpList = list[tmpBus.arrivals];
            for (Bus bus : tmpList) {
                if (bus.cost + distance[tmpBus.arrivals] < distance[bus.arrivals]) {
                    distance[bus.arrivals] = distance[tmpBus.arrivals] + bus.cost;
                    pq.add(new Bus(bus.arrivals, distance[bus.arrivals]));
                    route[bus.arrivals] = tmpBus.arrivals;
                }
            }

        }
    }
}
