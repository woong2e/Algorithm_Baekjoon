package silver3;

import java.io.*;
import java.util.StringTokenizer;

public class _1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCase = Integer.parseInt(br.readLine());

        while (numTestCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Circle c1 = new Circle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Circle c2 = new Circle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int number = hasContactPoint(c1, c2);

            bw.write(number + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    private static int hasContactPoint(Circle c1, Circle c2) {
        double distance = c1.findCenterDistance(c2);

        if (c1.x == c2.x && c1.y == c2.y && c1.r == c2.r) {
            return -1;
        } else if (Math.abs(c1.r - c2.r) < distance && distance < c1.r + c2.r) {
            return 2;
        } else if (Math.abs(c1.r - c2.r) == distance || c1.r + c2.r == distance) {
            return 1;
        } else {
            return 0;
        }

    }
}

class Circle {
    int x, y, r;

    public Circle (int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double findCenterDistance(Circle c2) {
        return Math.sqrt(Math.pow(this.x - c2.x, 2) + Math.pow(this.y - c2.y, 2));
    }
}
