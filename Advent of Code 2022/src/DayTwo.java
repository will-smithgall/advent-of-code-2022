import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayTwo {

    public static void main(String[] args) throws IOException {
        Day2 day2 = new Day2();

        int partOne = day2.PartOne();
        int partTwo = day2.PartTwo();

        System.out.println("Total Points: " + partOne);
        System.out.println("Total Points: " + partTwo);
    }

    public int PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input/daytwo.txt"));
        String line = br.readLine();

        int totalPoints = 0;

        while (line != null) {
            int roundPoints = 0;

            String[] moves = line.split(" ");

            if (moves[0].equals("A")) {
                if (moves[1].equals("X")) {
                    roundPoints += 4; //1 for rock, 3 for tie
                } else if (moves[1].equals("Y")) {
                    roundPoints += 8; //2 for paper, 8 for win
                } else if (moves[1].equals("Z")) {
                    roundPoints += 3; //3 for scissors, 0 for loss
                }
            } else if (moves[0].equals("B")) {
                if (moves[1].equals("X")) {
                    roundPoints += 1; //1 for rock, 0 for loss
                } else if (moves[1].equals("Y")) {
                    roundPoints += 5; //2 for paper, 3 for tie
                } else if (moves[1].equals("Z")) {
                    roundPoints += 9; //3 for scissors, 6 for win
                }

            } else if (moves[0].equals("C")) {
                if (moves[1].equals("X")) {
                    roundPoints += 7; //1 for rock, 6 for win
                } else if (moves[1].equals("Y")) {
                    roundPoints += 2; //2 for paper, 0 for loss
                } else if (moves[1].equals("Z")) {
                    roundPoints += 6; //3 for scissors, 3 for tie
                }

            }

            totalPoints += roundPoints;
            line = br.readLine();
        }

        return totalPoints;

    }

    public int PartTwo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input/daytwo.txt"));
        String line = br.readLine();

        int totalPoints = 0;

        while (line != null) {
            int roundPoints = 0;

            String[] moves = line.split(" ");

            if (moves[0].equals("A")) {
                //lose -> scissors
                if (moves[1].equals("X")) {
                    roundPoints += 3;
                } else if (moves[1].equals("Y")) {
                    roundPoints += 4; //tie -> rock
                } else if (moves[1].equals("Z")) {
                    roundPoints += 8;//win -> paper
                }
            } else if (moves[0].equals("B")) {
                if (moves[1].equals("X")) {
                    roundPoints += 1; //lose -> rock
                } else if (moves[1].equals("Y")) {
                    roundPoints += 5; //tie -> paper
                } else if (moves[1].equals("Z")) {
                    roundPoints += 9; //win -> scissors
                }
            } else if (moves[0].equals("C")) {
                if (moves[1].equals("X")) {
                    roundPoints += 2; //lose -> paper
                } else if (moves[1].equals("Y")) {
                    roundPoints += 6; //tie -> scissors
                } else if (moves[1].equals("Z")) {
                    roundPoints += 7; //win -> rock
                }
            }

            totalPoints += roundPoints;
            line = br.readLine();
        }

        return totalPoints;
    }
}
