import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DaySix {
    public static void main(String[] args) throws IOException {
        int partOne = PartOne();
        int partTwo = PartTwo();

        System.out.println("Part One: " + partOne + "\nPart Two: " + PartTwo());
    }

    public static int PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/daysix.txt"));

        String line = br.readLine();
        int returnValue = 0;

        for (int i = 0; i < line.length() - 4; i++) {
            String currString = line.substring(i, i + 4);

            char[] chars = currString.toCharArray();
            Map<Character, Integer> map = new HashMap<>();

            for (char c : chars) {
                map.put(c, 1);
            }

            if (map.size() == 4) {
                returnValue = i + 4;
                break;
            }
        }

        return returnValue;
    }

    public static int PartTwo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/daysix.txt"));

        String line = br.readLine();
        int returnValue = 0;

        for (int i = 0; i < line.length() - 14; i++) {
            String currString = line.substring(i, i + 14);

            char[] chars = currString.toCharArray();
            Map<Character, Integer> map = new HashMap<>();

            for (char c : chars) {
                map.put(c, 1);
            }

            if (map.size() == 14) {
                returnValue = i + 14;
                break;
            }

        }

        return returnValue;
    }
}
