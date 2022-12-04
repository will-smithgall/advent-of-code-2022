import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DayOne {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayone.txt"));

        String line = br.readLine();

        int temp = 0;
        int first = 0;
        int second = 0;
        int third = 0;

        while(line != null) {
            if (line.equals("")) {
                if (temp > first) {
                    third = second;
                    second = first;
                    first = temp;
                } else if (temp > second) {
                    third = second;
                    second = temp;
                } else if (temp > third) {
                    third = temp;
                }

                temp = 0;
            } else {
                temp += Integer.parseInt(line);
            }

            line = br.readLine();
        }

        if (temp != 0) {
            if (temp > first) {
                third = second;
                second = first;
                first = temp;
            } else if (temp > second) {
                third = second;
                second = temp;
            } else if (temp > third) {
                third = temp;
            }
        }

        System.out.println(first + second + third);
    }
}
