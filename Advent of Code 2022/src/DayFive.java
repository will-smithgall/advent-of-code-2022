import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DayFive {
    public static void main(String[] args) throws IOException {
        String partOne = PartOne();
        String partTwo = PartTwo();

        System.out.println("Part One: " + partOne + "\nPart Two: " + partTwo);
    }

    public static Map<Integer, Stack<String>> parseStacks(ArrayList<String> stacksStr) {
        //Get stack label row
        String[] labelRow = stacksStr.get(stacksStr.size() - 1).trim().split("   ");

        //Create map
        Map<Integer, Stack<String>> stacks = new HashMap<>();

        //Create stacks
        for (int i = 0; i < labelRow.length; i++) {
            stacks.put(i + 1, new Stack<String>());
        }

        //Chomp through string rows
        for (int i = stacksStr.size() - 2; i >= 0; i--) {
            String line = stacksStr.get(i);

            for (int j = 0; j < stacks.size(); j++) {
                String container = line.substring(0, 2);
                line = line.substring(3);

                if (container.charAt(1) != ' ') {
                    stacks.get(j + 1).push(String.valueOf(container.charAt(1)));
                }

                if (line.length() > 1) {
                    line = line.substring(1);
                } else {
                    break;
                }
            }
        }

        return stacks;
    }

    public static String PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayfive.txt"));

        String line = br.readLine();
        ArrayList<String> stacksStr = new ArrayList<String>();

        //Add deault stack positions to stacksStr
        while (!line.equals("")) {
            stacksStr.add(line);

            line = br.readLine();
        }
        //Skip blank space
        line = br.readLine();

        Map<Integer, Stack<String>> map = parseStacks(stacksStr);

        while (line != null) {
            String[] instructions = line.split(" ");

            for (int i = 0; i < Integer.parseInt(instructions[1]); i++) {
                String box = map.get(Integer.parseInt(instructions[3])).pop();
                map.get(Integer.parseInt(instructions[5])).push(box);
            }

            line = br.readLine();
        }

        StringBuilder returnString = new StringBuilder();

        for (int i = 1; i <= map.size(); i++) {
            returnString.append(map.get(i).peek());
        }

        return returnString.toString();
    }

    public static String PartTwo() throws IOException {
         BufferedReader br = new BufferedReader(new FileReader("data/dayfive.txt"));

         String line = br.readLine();
         ArrayList<String> stacksStr = new ArrayList<String>();

         while (!line.equals("")) {
             stacksStr.add(line);

             line = br.readLine();
         }

         //Skip blank line
         line = br.readLine();

         Map<Integer, Stack<String>> map = parseStacks(stacksStr);

         while (line != null) {
             String[] instructions = line.split(" ");

             Stack<String> movementStack = new Stack<String>();

             for (int i = 0; i < Integer.parseInt(instructions[1]); i++) {
                 if (Integer.parseInt(instructions[1]) == 1) {
                     String box = map.get(Integer.parseInt(instructions[3])).pop();
                     map.get(Integer.parseInt(instructions[5])).push(box);
                 } else {
                     //Push all onto new move stack
                     movementStack.push(map.get(Integer.parseInt(instructions[3])).pop());
                 }
             }

             //Pop everything from move stack onto destination
             if (Integer.parseInt(instructions[1]) != 1) {
                 for (int i = 0; i < Integer.parseInt(instructions[1]); i++) {
                     map.get(Integer.parseInt(instructions[5])).push(movementStack.pop());
                 }
             }

             line = br.readLine();
         }

        StringBuilder returnString = new StringBuilder();

        for (int i = 1; i <= map.size(); i++) {
            returnString.append(map.get(i).peek());
        }

        return returnString.toString();
    }
}