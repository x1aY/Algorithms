package Algorithm;

/**
 * @author x1ay
 * @see <a href="https://leetcode.cn/problems/sqrtx/solutions/238553/x-de-ping-fang-gen-by-leetcode-solution/">
 *     牛顿迭代法题解</a>
 */
public class NewtonSqrt {

    public static int compute(int input) {
        double curr = input + 1, next = input;
        while (Math.abs(next - curr) > 1e-5) {
            curr = next;
            next = (curr + input / curr) / 2;
        }
        return (int) curr;
    }

}
