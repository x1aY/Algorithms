package CodeCaprice.Array.SpiralMatrix;

import java.util.*;

public class SpiralOrder_54 {

    public static Map<Coord, Coord> dirMap = new HashMap<Coord, Coord>() {
        {
            put(new Coord(0, 1), new Coord(1, 0));
            put(new Coord(1, 0), new Coord(0, -1));
            put(new Coord(0, -1), new Coord(-1, 0));
            put(new Coord(-1, 0), new Coord(0, 1));
        }
    };

    private static class Coord {
        public int x;
        public int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void moveCoord(Coord coord) {
            this.x += coord.x;
            this.y += coord.y;
        }

        @Override
        public String toString() {
            return "x=" + this.x + ", y=" + this.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            Coord coord = (Coord) obj;
            return this.x == coord.x && this.y == coord.y;
        }
    }

    public static List<Integer> MoveLen(int rows, int cols) {
        List<Integer> moveLen = new ArrayList<>();
        int i = cols, j = rows - 1;
        boolean isOdd = true;
        while (i != 0 && j != 0) {
            if (isOdd)
                moveLen.add(i--);
            else
                moveLen.add(j--);
            isOdd = !isOdd;
        }
        moveLen.add(i != 0 ? i : j);
        return moveLen;
    }

    public static List<Integer> mySpiralOrder(int[][] matrix) {
        List<Integer> elements = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length;
        List<Integer> moveLen = MoveLen(rows, cols);

        Coord currCoord = new Coord(0, 0), currDir = new Coord(0, 1);
        for (Integer oneDirLen : moveLen) {
            for (int i = 0; i < oneDirLen; i++) {
                elements.add(matrix[currCoord.x][currCoord.y]);
                if (i + 1 == oneDirLen)
                    currDir = dirMap.get(currDir);
                currCoord.moveCoord(currDir);
            }
        }
        return elements;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        // int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new ArrayList<>();
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        Integer[] res = new Integer[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top
            if (++l > r) break;
        }
        return Arrays.asList(res);
    }

}
