import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class DaySeven {
    public static void main(String[] args) throws IOException {
        int partOne = PartOne();
        int partTwo = PartTwo();

        System.out.println(partOne + " " + partTwo);
    }

    public static int PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayseven.txt"));

        String line = br.readLine();
        ArrayList<Integer> allSums = new ArrayList<Integer>();

        Stack<Integer> directories = new Stack<Integer>();
        directories.push(0); //Prevent empty stack exception

        while (line != null) {
            String[] instructions = line.split(" ");

            //Check which instruction type it is
            if (instructions[0].equals("$")) {
                //cd
                if (instructions[1].equals("cd")) {
                    if (instructions[2].equals("..")) {
                        int temp = directories.pop();
                        directories.push(directories.pop() + temp);
                        allSums.add(temp);
                    } else {
                        directories.push(0);
                    }
                }
            } else if (instructions[0].charAt(0) != 'd') {
                directories.push(directories.pop() + Integer.parseInt(instructions[0]));
            }

            line = br.readLine();
        }

        //Empty what is left in the stack
        while (directories.size() != 1) {
            int temp = directories.pop();
            directories.push(directories.pop() + temp);
            allSums.add(temp);
        }

        int totalSize = 0;
        for (int i : allSums) {
            if (i <= 100000) {
                totalSize += i;
            }
        }

        return totalSize;
    }

    public static int PartTwo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayseven.txt"));

        String line = br.readLine();
        ArrayList<Integer> allSums = new ArrayList<Integer>();

        Stack<Integer> directories = new Stack<Integer>();
        directories.push(0); //Prevent empty stack exception

        while (line != null) {
            String[] instructions = line.split(" ");

            //Check which instruction type it is
            if (instructions[0].equals("$")) {
                //cd
                if (instructions[1].equals("cd")) {
                    if (instructions[2].equals("..")) {
                        int temp = directories.pop();
                        directories.push(directories.pop() + temp);
                        allSums.add(temp);
                    } else {
                        directories.push(0);
                    }
                }
            } else if (instructions[0].charAt(0) != 'd') {
                directories.push(directories.pop() + Integer.parseInt(instructions[0]));
            }

            line = br.readLine();
        }

        //Empty what is left in the stack
        while (directories.size() != 1) {
            int temp = directories.pop();
            directories.push(directories.pop() + temp);
            allSums.add(temp);
        }

        int returnValue = 0;
        int spaceLeft = 70000000 - allSums.get(allSums.size() - 1);

        allSums.sort(Comparator.naturalOrder());
        for (int i : allSums) {
            if (i + spaceLeft > 30000000) {
                returnValue = i;
                break;
            }
        }

        return returnValue;
    }
}
