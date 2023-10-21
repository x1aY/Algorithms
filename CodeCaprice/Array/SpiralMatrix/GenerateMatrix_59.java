package CodeCaprice.Array.SpiralMatrix;

import java.util.*;

public class GenerateMatrix_59 {
    public static Map<Coord, Coord> clockwiseDirMap = new HashMap<Coord, Coord>() {
        {
            put(new Coord(0, 1), new Coord(1, 0));
            put(new Coord(1, 0), new Coord(0, -1));
            put(new Coord(0, -1), new Coord(-1, 0));
            put(new Coord(-1, 0), new Coord(0, 1));
        }
    };

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

    public static int[][] MyGenerateMatrix(int n) {
        int[][] matrix = new int[n][n];
        Coord currCoord = new Coord(0, 0), currDir = new Coord(0, 1);
        List<Integer> moveLen = MoveLen(n, n);
        int ele = 1;
        for (Integer oneDirMove : moveLen) {
            for (int j = 0; j < oneDirMove; j++) {
                matrix[currCoord.x][currCoord.y] = ele++;
                if (j + 1 == oneDirMove)
                    currDir = clockwiseDirMap.get(currDir);
                currCoord.moveCoord(currDir);
            }
        }
        return matrix;
    }

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

    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = generateMatrix(n);
        System.out.println(Arrays.deepToString(matrix));

    }

    public static int[][] generateMatrix(int n) {
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while (num <= tar) {
            for (int i = left; i <= right; i++) mat[top][i] = num++; // left to right.
            top++;
            for (int i = top; i <= bottom; i++) mat[i][right] = num++; // top to bottom.
            right--;
            for (int i = right; i >= left; i--) mat[bottom][i] = num++; // right to left.
            bottom--;
            for (int i = bottom; i >= top; i--) mat[i][left] = num++; // bottom to top.
            left++;
        }
        return mat;
    }

}
