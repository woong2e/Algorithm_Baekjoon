package silver5;

import java.util.Scanner;

public class _1094 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int stickLength1 = 64;
        int stickLength2 = 0;
        int testX = sc.nextInt();

        System.out.println(cutStick(stickLength1, stickLength2, testX));
    }

    static int cutStick(int stickLength1, int stickLength2, int testX) {
        if (stickLength1 + stickLength2 == testX) {
            return 1;
        } else {
            stickLength1 /= 2;
            stickLength2 += stickLength1;
            if (stickLength2 == testX) {
                stickLength1 = 0;
            } else if (stickLength2 > testX) {
                stickLength2 -= stickLength1;
            } else {
                return 1 + cutStick(stickLength1, stickLength2, testX);
            }
            return cutStick(stickLength1, stickLength2, testX);
        }
    }
}

