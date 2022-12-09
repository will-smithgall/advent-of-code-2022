import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DayEight {
    public static void main(String[] args) throws IOException {
        int partOne = PartOne();
        int partTwo = PartTwo();

        System.out.println("Visible Trees: " + partOne + "\nMaximum Eave Score: " + partTwo);
    }

    public static int PartOne() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayeight.txt"));

        String line = br.readLine();
        int count = 0;

        //Create map for tree grid -> (Tree, size)
        //0,0 top left, 99,99 top right
        Map<Tree, Integer> treeGrid = new HashMap<>();

        int visibleTrees = 0;

        while (line != null) {
            for (int i = 0; i < line.length(); i++) {
                int height = Integer.parseInt(line.substring(i, i + 1));
                treeGrid.put(new Tree(i, count, height), height);
            }

            count++;
            line = br.readLine();
        }

        //Check if each tree is visible
        for (Tree tree : treeGrid.keySet()) {
            if (isVisible(tree, count - 1, treeGrid)) {
                visibleTrees += 1;
            }
        }

        return visibleTrees;
    }

    public static int PartTwo() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/dayeight.txt"));

        String line = br.readLine();
        int count = 0;

        ArrayList<Tree> treeGrid = new ArrayList<Tree>();

        while (line != null) {
            for (int i = 0; i < line.length(); i++) {
                int height = Integer.parseInt(line.substring(i, i + 1));
                treeGrid.add(new Tree(i, count, height));
            }

            count++;
            line = br.readLine();
        }

        int maxEave = 0;

        for (Tree tree: treeGrid) {
            int currEave = eaveValue(tree, count - 1, treeGrid);

            if (currEave > maxEave) {
                maxEave = currEave;
            }
        }

        return maxEave;
    }

    public static boolean isVisible(Tree tree, int size, Map<Tree, Integer> treeGrid) {
        //Check if value is a border
        if (isBorder(tree, size)) {
            return true;
        }

        //Get all trees that potentially block view
        Map<Integer, ArrayList<Tree>> neighbors = treeNeighbors(tree, treeGrid);

        //Go through all neighbor trees, check if it blocks visibility
        int visibilityBlocks = 0; //If 4 -> all sight is blocked -> not visible
        for (int i = 0; i < neighbors.size(); i++) {
            for (int j = 0; j < neighbors.get(i).size(); j++) {
                if (neighbors.get(i).get(j).height >= tree.height) {
                    //must be blocked, increment the counter and break
                    visibilityBlocks += 1;
                    break;
                }
            }
        }

        return visibilityBlocks != 4;
    }

    public static int eaveValue(Tree tree, int size, ArrayList<Tree> treeGrid) {
        if (isBorder(tree, size)) {
            return 0;
        }

        //get tree neighbors
        Map<Integer, ArrayList<Tree>> neighbors = treeNeighbors(tree, treeGrid);
        int score = 1;

        //Go through neighbors, calculate eave score
        for (int i = 0; i < neighbors.size(); i++) {
            Tree blockedTree = new Tree(0, 0, 0);
            boolean blocked = false;

            for (int j = 0; j < neighbors.get(i).size(); j++) {
                if (neighbors.get(i).get(j).height >= tree.height) {
                    blocked = true;
                    blockedTree = neighbors.get(i).get(j);
                    break;
                }
            }

            if (blocked) {
                score *= distance(tree, blockedTree, i);
            } else {
                score *= neighbors.get(i).size();
            }
        }

        return score;
    }

    public static Map<Integer, ArrayList<Tree>> treeNeighbors(Tree tree, ArrayList<Tree> treeGrid) {
        ArrayList<Tree> north = new ArrayList<Tree>(); //0 is NORTH
        ArrayList<Tree> east = new ArrayList<Tree>(); //1 is EAST
        ArrayList<Tree> south = new ArrayList<Tree>(); //2 is SOUTH
        ArrayList<Tree> west = new ArrayList<Tree>(); //3 is WEST

        Map<Integer, ArrayList<Tree>> validNeighbors = new HashMap<>();

        //Add arraylists
        validNeighbors.put(0, north);
        validNeighbors.put(1, east);
        validNeighbors.put(2, south);
        validNeighbors.put(3, west);

        for (Tree t : treeGrid) {
            if (tree.x == t.x && tree.y > t.y) { //Check if north
                validNeighbors.get(0).add(0, t);
            } else if (tree.y == t.y && tree.x < t.x) { //Check if east
                validNeighbors.get(1).add(t);
            } else if (tree.x == t.x && tree.y < t.y) { //Check if south
                validNeighbors.get(2).add(t);
            } else if (tree.y == t.y && tree.x > t.x) { //Check if west
                validNeighbors.get(3).add(0, t);
            }
        }

        return validNeighbors;
    }

    public static boolean isBorder(Tree tree, int size) {
        return tree.x == 0 || tree.y == 0 || tree.x == size || tree.y == size;
    }

    public static Map<Integer, ArrayList<Tree>> treeNeighbors(Tree tree, Map<Tree, Integer> treeGrid) {
        ArrayList<Tree> north = new ArrayList<Tree>(); //0 is NORTH
        ArrayList<Tree> east = new ArrayList<Tree>(); //1 is EAST
        ArrayList<Tree> south = new ArrayList<Tree>(); //2 is SOUTH
        ArrayList<Tree> west = new ArrayList<Tree>(); //3 is WEST

        Map<Integer, ArrayList<Tree>> validNeighbors = new HashMap<>();

        //Add arraylists
        validNeighbors.put(0, north);
        validNeighbors.put(1, east);
        validNeighbors.put(2, south);
        validNeighbors.put(3, west);

        for (Tree t : treeGrid.keySet()) {
            if (tree.x == t.x && tree.y > t.y) { //Check if north
                validNeighbors.get(0).add(t);
            } else if (tree.y == t.y && tree.x < t.x) { //Check if east
                validNeighbors.get(1).add(t);
            } else if (tree.x == t.x && tree.y < t.y) { //Check if south
                validNeighbors.get(2).add(t);
            } else if (tree.y == t.y && tree.x > t.x) { //Check if west
                validNeighbors.get(3).add(t);
            }
        }

        return validNeighbors;
    }

    public static int distance(Tree tree, Tree blockedTree, int direction) {
        int distance = 1;

        if (direction == 0 || direction == 2) {
            distance = Math.abs(tree.y - blockedTree.y);
        } else if (direction == 1 || direction == 3) {
            distance = Math.abs(tree.x - blockedTree.x);
        }

        return distance;
    }

    private static class Tree {
        public int x;
        public int y;
        public int height;

        public Tree(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
