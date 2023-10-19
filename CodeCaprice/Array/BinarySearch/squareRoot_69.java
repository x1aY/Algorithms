package CodeCaprice.Array.BinarySearch;

public class squareRoot_69 {
    public static int NewtonSqrt(int input) {
        double curr = input + 1, next = input;
        while (Math.abs(next - curr) > 1e-5) {
            curr = next;
            next = (curr + input / curr) / 2;
        }
        return (int) curr;
    }

    public static void main(String[] args) {
        System.out.println(NewtonSqrt(8));
    }
}
