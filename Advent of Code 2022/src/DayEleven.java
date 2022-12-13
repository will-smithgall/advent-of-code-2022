import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DayEleven {
    public static void main(String[] args) throws IOException {
        int roundsOne = 20;
        long partOne = PartOne(roundsOne);

        int roundsTwo = 10000;
        long partTwo = PartTwo(roundsTwo);

        System.out.println(partTwo);
    }

    public static long PartOne(int rounds) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayeleven.txt"));

        String line = br.readLine();
        int counter = 0;
        ArrayList<Monkey> monkeys = new ArrayList<Monkey>();

        while (line != null) {
            if (line.equals("")) {
                counter += 1;
                line = br.readLine();
            } else {
                line = br.readLine();

                String[] values = line.split(":")[1].split(",");
                line = br.readLine();

                String[] operation = line.split(":")[1].split(" ");
                line = br.readLine();

                int test = Integer.parseInt(line.split(":")[1].split(" ")[3]);
                line = br.readLine();

                int[] targetMonkeys = new int[2];
                targetMonkeys[0] = Integer.parseInt(line.split(":")[1].split(" ")[4]);
                line = br.readLine();

                targetMonkeys[1] = Integer.parseInt(line.split(":")[1].split(" ")[4]);
                line = br.readLine();

                monkeys.add(new Monkey(values, operation, test, counter, targetMonkeys));
            }
        }

        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                int invSize = monkey.inventory.size();
                for (int j = 0; j < invSize; j++) {
                    long currItem = monkey.inventory.remove();
                    currItem = monkey.calculateWorry(currItem);
                    currItem = (long) Math.floor(currItem / 3);

                    if (currItem % monkey.test == 0) {
                        monkeys.get(monkey.targetMonkeys[0]).inventory.add(currItem);
                    } else {
                        monkeys.get(monkey.targetMonkeys[1]).inventory.add(currItem);
                    }

                    monkey.inspectedItems += 1;
                }
            }
        }

        return calculateMonkeyBusiness(monkeys);
    }

    public static long PartTwo(int rounds) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayeleven.txt"));

        String line = br.readLine();
        int counter = 0;
        ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
        int modBy = 1;

        while (line != null) {
            if (line.equals("")) {
                counter += 1;
                line = br.readLine();
            } else {
                line = br.readLine();

                String[] values = line.split(":")[1].split(",");
                line = br.readLine();

                String[] operation = line.split(":")[1].split(" ");
                line = br.readLine();

                int test = Integer.parseInt(line.split(":")[1].split(" ")[3]);
                modBy *= test;
                line = br.readLine();

                int[] targetMonkeys = new int[2];
                targetMonkeys[0] = Integer.parseInt(line.split(":")[1].split(" ")[4]);
                line = br.readLine();

                targetMonkeys[1] = Integer.parseInt(line.split(":")[1].split(" ")[4]);
                line = br.readLine();

                monkeys.add(new Monkey(values, operation, test, counter, targetMonkeys));
            }
        }

        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                int invSize = monkey.inventory.size();
                for (int j = 0; j < invSize; j++) {
                    long currItem = monkey.inventory.remove();
                    currItem = monkey.calculateWorry(currItem);
                    currItem = currItem % modBy;

                    if (currItem % monkey.test == 0) {
                        monkeys.get(monkey.targetMonkeys[0]).inventory.add(currItem);
                    } else {
                        monkeys.get(monkey.targetMonkeys[1]).inventory.add(currItem);
                    }

                    monkey.inspectedItems += 1;
                }
            }
        }

        for (Monkey m : monkeys) {
            System.out.println(m);
        }

        return calculateMonkeyBusiness(monkeys);
    }

    public static long calculateMonkeyBusiness(ArrayList<Monkey> monkeys) {
        long first = 0;
        long second = 0;

        for (Monkey monkey : monkeys) {
            if (monkey.inspectedItems >= first) {
                second = first;
                first = monkey.inspectedItems;
            } else if (monkey.inspectedItems >= second) {
                second = monkey.inspectedItems;
            }
        }

        return first * second;
    }

    private static class Monkey {
        Queue<Long> inventory; //Items the monkey has
        String[] operation;
        int test; //division value to test
        int monkeyID; //monkey label
        int[] targetMonkeys; //monkeys to throw to depending on test -> [0] if true, [1] false
        int inspectedItems;

        public Monkey(String[] values, String[] operation, int test, int monkeyID, int[] targetMonkeys) {
            inspectedItems = 0;
            this.test = test;
            this.monkeyID = monkeyID;
            this.targetMonkeys = targetMonkeys;

            inventory = new LinkedList<Long>();

            for (String value : values) {
                inventory.add(Long.parseLong(value.trim()));
            }

            this.operation = new String[2];
            this.operation[0] = operation[4];
            this.operation[1] = operation[5];
        }

        public long calculateWorry(long value) {
            long returnValue = value;
            long opValue = 0;

            if (operation[1].equals("old")) {
                opValue = value;
            } else {
                opValue = Integer.parseInt(operation[1]);
            }

            switch (operation[0]) {
                case "+":
                    returnValue += opValue;
                    break;
                case "*":
                    returnValue *= opValue;
                    break;
                default:
                    break;
            }

            return returnValue;
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "inventory=" + inventory +
                    ", operation=" + Arrays.toString(operation) +
                    ", test=" + test +
                    ", monkeyID=" + monkeyID +
                    ", targetMonkeys=" + Arrays.toString(targetMonkeys) +
                    ", inspectedItems=" + inspectedItems +
                    '}';
        }
    }
}
