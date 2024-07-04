package gold4;

import java.io.*;
import java.util.*;
/*
큐를 이용해서 푸는 bfs문제
핵심은 bfs돌릴때 명령어도 이어서 계속 저장해주어야 한다. c++에 있는 pair가 쓰고 싶어져 Pair클래스를 만들어주었다.
 */
public class _9019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCase = Integer.parseInt(br.readLine());
        while (numTestCase-- > 0) {
            StringTokenizer stAB = new StringTokenizer(br.readLine());
            //배열 인덱스 0: 일의 자리 ~ 인덱스 3: 천의 자리
            int A = Integer.parseInt(stAB.nextToken());
            int B = Integer.parseInt(stAB.nextToken());

            String result = bfs(A, B);

            bw.write(result + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }


    static String bfs(int A, int B) {
        boolean[] visit = new boolean[10001];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(funcD(A, ""));
        queue.add(funcS(A, ""));
        queue.add(funcL(A, ""));
        queue.add(funcR(A, ""));
        visit[A] = true;


        while (true) {
            Pair result = queue.poll();
            if(result.number == B) {
                return result.command;
            }
            if (!visit[result.number]) {
                queue.add(funcD(result.number, result.command));
                queue.add(funcS(result.number, result.command));
                queue.add(funcL(result.number, result.command));
                queue.add(funcR(result.number, result.command));
                visit[result.number] = true;
            }
        }
    }

    static Pair funcD(int A, String s) {
        return new Pair((A * 2) % 10000, s + "D");
    }

    static Pair funcS(int A, String s) {
        if (A == 0) {
            return new Pair(9999, s + "S");

        } else {
            return new Pair(A - 1, s + "S");
        }
    }

    static Pair funcL(int A, String s) {
        return new Pair((A % 1000) * 10 + A / 1000, s + "L");
    }

    static Pair funcR(int A, String s) {
        return new Pair((A % 10) * 1000 + A / 10, s + "R");
    }
}

class Pair {
    int number;
    String command;

    Pair(int number, String command) {
        this.number = number;
        this.command = command;
    }
}
