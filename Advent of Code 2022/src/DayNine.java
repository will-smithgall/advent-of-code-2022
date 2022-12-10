import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DayNine {
    public static void main(String[] args) throws IOException {
        int partOne = PartOne();
    }

    public static int PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/daynine.txt"));

        String line = br.readLine();
        
        Map<Point, Boolean> points = new HashMap<>();

        Rope head = new Rope(0, 0);
        Rope tail = new Rope(0, 0);

        while (line != null) {
            String[] instructions = line.split(" ");
            int amount = Integer.parseInt(instructions[1]);
            while (amount > 0) {
                int tempX = head.x;
                int tempY = head.y;

                head.move(instructions[0], amount);

                if (!tail.isAdjacent(head)) {
                    tail.x = tempX;
                    tail.y = tempY;
                }

                points.put(new Point(tail.x, tail.y), true);

                amount--;
            }

            line = br.readLine();
        }

        System.out.println(points.size());

        return 0;
    }

    private static class Point {
        public int x;
        public int y;
        private int hashCode;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.hashCode = Objects.hash(x, y);
        }

        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Point that = (Point) o;
            return x == that.x && y == that.y;
        }
    }
    
    private static class Rope {
        public int x;
        public int y;

        public Rope(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isAdjacent(Rope tail) {
            return Math.floor(Math.sqrt((this.x - tail.x) * (this.x - tail.x) + (this.y - tail.y) * (this.y - tail.y))) <= 1;
        }

        public void move(String direction, int amount) {
            if (direction.equals("U")) {
                this.y++;
            } else if (direction.equals("D")) {
                this.y--;
            } else if (direction.equals("R")) {
                this.x++;
            } else if (direction.equals("L")) {
                this.x--;
            }
        }
    }
}
