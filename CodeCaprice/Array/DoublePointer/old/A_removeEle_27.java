package CodeCaprice.Array.DoublePointer.old;

import java.util.Arrays;

public class A_removeEle_27 {
    //
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val && i + 1 < nums.length) {
                nums[i] = nums[i + 1];
            } else
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        System.out.println(removeElement(nums, val));
        System.out.println(Arrays.toString(nums));

    }
}
