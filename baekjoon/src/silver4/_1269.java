package silver4;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class _1269 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stInt = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(stInt.nextToken());
        int B = Integer.parseInt(stInt.nextToken());

        int[] setA = new int[A];
        int[] setB = new int[B];
        StringTokenizer stA = new StringTokenizer(br.readLine());
        StringTokenizer stB = new StringTokenizer(br.readLine());

        for (int i = 0; i < A; i++) {
            setA[i] = Integer.parseInt(stA.nextToken());
        }
        for (int i = 0; i < B; i++) {
            setB[i] = Integer.parseInt(stB.nextToken());
        }
        int numberOfCommonElement = findSymmetricDifferenceSet(A, B, setA, setB);

        int numberOfSymmetricDifferenceElement = A + B - 2*numberOfCommonElement;

        bw.write(String.valueOf(numberOfSymmetricDifferenceElement));
        bw.flush();

        br.close();
        bw.close();

    }

    static int findSymmetricDifferenceSet(int A, int B, int[] setA, int[] setB) {
        int d = A + B;
        Integer[] arr = new Integer[A + B];
        int idx = 0;
        for(int num: setA) {
            arr[idx++] = num;
        }
        for(int num: setB) {
            arr[idx++] = num;
        }
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(arr));

        return d - set.size();
    }
}
