package gold4;

import java.io.*;
import java.util.*;
/*
TreeSet은 Set 인터페이스를 상속받아 구현한 클래스로 이진 탐색 트리의 구조를 가지고 있다.
따라서 Set의 특성을 가지고 있어 중복을 허용하지 않는데, 중복값을 저장하기 위해 Map 자료구조를 이용하여 카운트를 한 후,
Set에서 Delete를 하기 전 Map을 먼저 확인하여 중복값이 있을 경우 우선적으로 카운트를 하는 방식으로 이중 우선순위 큐를 구현하였다.
 */

public class _7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCase = Integer.parseInt(br.readLine());
        while (numTestCase-- > 0) {
            int k = Integer.parseInt(br.readLine());
            List<Command> commands = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                Command command = new Command(st.nextToken(), Integer.parseInt(st.nextToken()));
                commands.add(command);
            }

            String output = printMinMax(commands, k);

            bw.write(output + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    static String printMinMax(List<Command> commands, int k) {
        int cnt = 0;
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Command command: commands) {
            if (command.getOperator().equals("I")) {
                cnt++;
                if (map.containsKey(command.getValue())) {
                    map.put(command.getValue(), map.get(command.getValue()) + 1);
                } else {
                    map.put(command.getValue(), 1);
                }
                set.add(command.getValue());
            } else {
                if (cnt > 0) {
                    cnt--;
                    int tmp;
                    if (command.getValue() == 1) {
                        tmp = set.last();
                        if (map.containsKey(tmp)) {
                            map.put(tmp, map.get(tmp) - 1);
                            if (map.get(tmp) == 0) {
                                set.pollLast();
                            }
                        } else {
                            set.pollLast();
                        }
                    } else {
                        tmp = set.first();
                        if (map.containsKey(tmp)) {
                            map.put(tmp, map.get(tmp) - 1);
                            if (map.get(tmp) == 0) {
                                set.pollFirst();
                            }
                        } else {
                            set.pollFirst();
                        }
                    }
                }
                if (cnt == 0) {
                    set.clear();
                    map.clear();
                }
            }
        }
        if (cnt == 0) {
            return "EMPTY";
        } else {
            return set.last() + " " + set.first();
        }

    }
}

class Command {
    private String operator;
    private int value;

    Command (String operator, int value) {
        this.operator = operator;
        this.value = value;
    }

    String getOperator() {
        return operator;
    }

    int getValue() {
        return value;
    }
}
