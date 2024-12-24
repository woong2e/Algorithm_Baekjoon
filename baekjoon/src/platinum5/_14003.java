package platinum5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
/*
가장 긴 증가하는 부분 수열
수열의 크기가 클 수도 있다. -> DP가 아닌 이분탐색으로 문제를 해결해보자.
- 입력된 값의 처음부터 순차적으로 검사를 시작
- 검사하는 숫자가 증가수열 리스트의 마지막 숫자보다 큰 경우
    -> 증가수열 리스트에 추가.
- 검사하는 숫자가 증가수열 리스트의 마지막 숫자보다 작은 경우
    -> 증가수열 리스트에 적당한 위치를 찾아서 갱신한다.
    -> 이렇게 되면 list의 길이에는 변함이 없게된다.
-> 최종적으로 list 길이를 이용하여 가장 긴 증가하는 부분 수열의 길이를 도출해 낼 수 있다.

증가수열 값들을 구하기 위한 방법
증가수열의 인덱스를 저장하는 배열을 만들어, 역추적을 하여 구할 수 있다.
뒤에서 검사를 하는 이유는 나중에 선택된 원소가 최종적으로 선택된 원소이기 때문이다.
 */

public class _14003 {
    private static List<Integer> list;
    private static int[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        list = new ArrayList<>();
        index = new int[N];

        list.add(A[0]);
        for (int i = 0; i < N; i++) {
            binarySearch(i, A[i]);
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        sb.append(list.size() + "\n");

        int len = list.size() - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (index[i] == len) {
                stack.push(A[i]);
                len--;
            }
        }

        while (!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    private static void binarySearch(int idx, int num) {
        int start = 0;
        int end = list.size() - 1;
        if (list.get(list.size() - 1) < num) {
            list.add(num);
            index[idx] = list.size() - 1;
        } else {
            while (start < end) {
                int mid = (start + end) >> 1;
                if (list.get(mid) >= num) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            list.set(end, num);
            index[idx] = end;
        }
    }
}
