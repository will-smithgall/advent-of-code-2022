import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3 {
    public static void main(String[] args) throws IOException {
        int priorityOne = PartOne();
        System.out.println("Part one: " + priorityOne);

        int priorityTwo = PartTwo();
        System.out.println("Part Two: " + priorityTwo);
    }

    public static int PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input/daythree.txt"));

        String line = br.readLine();
        int priority = 0;

        while (line != null) {
            String firstHalf = line.substring(0, (line.length() / 2));
            String secondHalf = line.substring((line.length() / 2));

            for (int i = 0; i < firstHalf.length(); i++) {
                if (secondHalf.contains(firstHalf.substring(i, i + 1))) {
                    priority += priority(firstHalf.charAt(i));
                    break;
                }
            }
            line = br.readLine();
        }

        return priority;
    }

    public static int PartTwo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input/daythree.txt"));

        String line1 = br.readLine();
        String line2 = br.readLine();
        String line3 = br.readLine();

        int priority = 0;

        while (line1 != null) {
            for (int i = 0; i < line1.length(); i++) {
                if (line2.contains(line1.substring(i, i+1)) && line3.contains(line1.substring(i, i+1))) {
                    priority += priority(line1.charAt(i));
                    break;
                }
            }

            line1 = br.readLine();
            line2 = br.readLine();
            line3 = br.readLine();
        }

        return priority;
    }

    public static int priority(char c) {
        int priority = 0;

        if (Character.isUpperCase(c)) {
            priority = c - 38;
        } else {
            priority = c - 96;
        }

        return priority;
    }
}
