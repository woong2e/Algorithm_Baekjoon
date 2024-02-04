package gold5;

import java.io.*;
import java.util.*;

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
