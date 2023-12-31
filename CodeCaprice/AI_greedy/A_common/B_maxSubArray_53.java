package CodeCaprice.AI_greedy.A_common;

public class B_maxSubArray_53 {

    public int maxSubArray(int[] nums) {
        int maxSubSum = 0;
        return maxSubSum;
    }

    public static void main(String[] args) {
        B_maxSubArray_53 solution = new B_maxSubArray_53();
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(solution.maxSubArray(nums));
    }
    /**
     * https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C.html#%E8%B4%AA%E5%BF%83%E8%A7%A3%E6%B3%95
     * 
     * 关键在于：不能让“连续和”为负数的时候加上下一个元素，而不是 不让“连续和”加上一个负数
     * 
     * 如果[-2,1]在一起，计算起点的时候，一定是从1开始计算，因为负数只会拉低总和，这就是贪心贪的地方！
     * 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
     * 全局最优：选取最大“连续和”
     * 局部最优的情况下，并记录最大的“连续和”，可以推出全局最优。
     * 从代码角度上来讲：遍历nums，从头开始用count累积，如果count一旦加上nums[i]变为负数，那么就应该从nums[i+1]开始从0累积count了，因为已经变为负数的count，只会拖累总和。
     * 这相当于是暴力解法中的不断调整最大子序和区间的起始位置。
     * 那有同学问了，区间终止位置不用调整么？ 如何才能得到最大“连续和”呢？
     * 区间的终止位置，其实就是如果count取到最大值了，及时记录下来了
     */
    public int greddyMaxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            count += nums[i];
            // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            sum = Math.max(sum, count);
            if (count <= 0){
                count = 0; // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
       return sum;
    }
}
