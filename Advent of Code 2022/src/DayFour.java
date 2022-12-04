import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayFour {
    public static void main(String[] args) throws IOException {
        int partOne = PartOne();
        System.out.println("Overlapping ID total: " + partOne);

        int partTwo = PartTwo();
        System.out.println("Partially overlapping ID total: " + partTwo);
    }

    public static int PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayfour.txt"));

        String line = br.readLine();
        int count = 0;

        while (line != null) {
            String[] values = line.split("[,-]");

            //Check if second contains first
            if (Integer.parseInt(values[0]) >= Integer.parseInt(values[2]) && Integer.parseInt(values[1]) <= Integer.parseInt(values[3])) {
                count += 1;
            } else if (Integer.parseInt(values[0]) <= Integer.parseInt(values[2]) && Integer.parseInt(values[1]) >= Integer.parseInt(values[3])) {
                count += 1;
            }

            line = br.readLine();
        }

        return count;
    }

    public static int PartTwo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayfour.txt"));

        String line = br.readLine();
        int count = 0;

        while (line != null) {
            String[] values = line.split("[,-]");

            //Check conditions
            if (Integer.parseInt(values[0]) >= Integer.parseInt(values[2]) && Integer.parseInt(values[1]) <= Integer.parseInt(values[3])) {
                count += 1;
            } else if (Integer.parseInt(values[0]) <= Integer.parseInt(values[2]) && Integer.parseInt(values[1]) >= Integer.parseInt(values[3])) {
                count += 1;
            } else if (Integer.parseInt(values[0]) <= Integer.parseInt(values[3]) && Integer.parseInt(values[0]) >= Integer.parseInt(values[2])) {
                count += 1;
            } else if (Integer.parseInt(values[1]) >= Integer.parseInt(values[2]) && Integer.parseInt(values[1]) <= Integer.parseInt(values[3])) {
                count += 1;
            }

            line = br.readLine();
        }

        return count;
    }
}
