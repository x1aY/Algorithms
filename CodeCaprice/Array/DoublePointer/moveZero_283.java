package CodeCaprice.Array.DoublePointer;

import java.util.Arrays;

public class moveZero_283 {

    public static void moveZeroes(int[] nums) {
        int len = nums.length;
        if (len < 2) return;
        for (int fstZero = 0, idx = 0; idx < len; idx++) {
            while (fstZero < idx && nums[fstZero] != 0) fstZero++;
            if (nums[idx] != 0) {
                swap(nums, fstZero++, idx);
            }
        }
    }

    public static void swap(int[] nums, int fstIdx, int secIdx) {
        int temp = nums[fstIdx];
        nums[fstIdx] = nums[secIdx];
        nums[secIdx] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 0, 2, 3, 0, 5};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
