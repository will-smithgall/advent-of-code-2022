import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayTen {
    public static void main(String[] args) throws IOException {
        int partOne = PartOne();
        System.out.println(partOne);

        PartTwo();
    }

    public static int PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayten.txt"));

        String line = br.readLine();
        int register = 1;
        int cycle = 0;
        int signalStrengths = 0;

        while (line != null) {
            cycle++;

            if (line.equals("noop")) {
                if ((cycle + 20) % 40 == 0) {
                    signalStrengths += register * cycle;
                }
            } else {
                if ((cycle + 20) % 40 == 0) { // Check before and after second cycle
                    signalStrengths += register * cycle;
                }

                cycle++;

                if ((cycle + 20) % 40 == 0) {
                    signalStrengths += register * cycle;
                }

                register += Integer.parseInt(line.split(" ")[1]);
            }

            line = br.readLine();
        }

        return signalStrengths;
    }

    public static void PartTwo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayten.txt"));

        String line = br.readLine();
        int register = 1;
        int cycle = 0;
        int crt = 0;

        ArrayList<Integer> spriteLocations = new ArrayList<Integer>();
        spriteLocations.add(0);
        spriteLocations.add(1);
        spriteLocations.add(2);

        while (line != null) {
            cycle++;
            crt = (cycle - 1) % 40;

            spriteLocations.set(0, register - 1);
            spriteLocations.set(1, register);
            spriteLocations.set(2, register + 1);

            //if its a noop inst we dont care ->
            //perform first action for during clock tick regardless of operation

            if (crt == 0) {
                System.out.println();
            }

            if (spriteLocations.contains(crt)) {
                System.out.print("#");
            } else {
                System.out.print(" ");
            }

            if (!line.equals("noop")) {
                cycle++;
                crt = (cycle - 1) % 40;

                if (crt == 0) {
                    System.out.println();
                }

                if (spriteLocations.contains(crt)) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }

                register += Integer.parseInt(line.split(" ")[1]);
            }

            line = br.readLine();
        }
    }
}
