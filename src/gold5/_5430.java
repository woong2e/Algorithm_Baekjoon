package gold5;

import java.io.*;
import java.util.*;
/*
시간초과가 계속 떠서 그냥 따로 함수로 분리 안하고 한번에 써버렸다....
이 문제는 배열의 순서를 reverse하는 방법에 있어서 시간초과의 여부가 갈린다.
먼저 진행한 방식은 Collection의 reverse함수를 이용해 리스트를 뒤집어주는 방식을 썼다. 하지만 이렇게 할 경우 시간초과가 떠서,
배열을 직접 뒤집는 방식이 아닌 자료구조를 이용하여 해결할 수 있는 문제이다.
뒤집는다는 생각을 앞쪽에서 뒤쪽에서 모두 요소를 꺼낼 수 있는 자료구조를 사용한다고 생각해보면 덱이란 자료구조를 사용해야 한다.
앞에서 요소를 꺼낼 수 있는 큐와 달리 덱은 앞 뒤 모두에서 요소를 꺼낼 수 있다.
배열을 뒤집을 때 마다 꺼내는 위치를 바꾸면 시간초과 없이 해결할 수 있다.
 */
public class _5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCase = Integer.parseInt(br.readLine());
        while (numTestCase-- > 0) {
            String func = br.readLine();

            int num = Integer.parseInt(br.readLine());

            Deque<Integer> deque = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine(),"[],");

            while(st.hasMoreTokens()) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            boolean head = true;
            boolean error = false;
            for (int i = 0; i < func.length(); i++) {
                if (func.charAt(i) == 'R') {
                    if (head) {
                        head = false;
                    } else {
                        head = true;
                    }
                } else if (func.charAt(i) == 'D') {
                    if (!deque.isEmpty()) {
                        if (head) {
                            deque.pollFirst();
                        } else {
                            deque.pollLast();
                        }
                    } else {
                        bw.write("error" + "\n");
                        error = true;
                        break;
                    }
                }
            }
            if (!error && num != 0) {
                bw.write("[");
                int dSize = deque.size();
                for (int i = 0; i < dSize; i++) {
                    if (head) {
                        if (i == dSize - 1) {
                            bw.write(String.valueOf(deque.pollFirst()));
                        } else {
                            bw.write(deque.pollFirst() + ",");
                        }
                    } else {
                        if (i == dSize - 1) {
                            bw.write(String.valueOf(deque.pollLast()));
                        } else {
                            bw.write(deque.pollLast() + ",");
                        }
                    }
                }
                bw.write("]" + "\n");
            } else if (!error && num == 0) {
                bw.write("[]" + "\n");
            }
            bw.flush();
        }
        br.close();
        bw.close();
    }
}
