package Sort;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = { 4, 1, 5, 7, 3, 9, 2, 6, 8 };
        quickSort(nums, 0, nums.length - 1);
        System.out.println(nums.toString());
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int pivotIdx = partition(nums, left, right);
        quickSort(nums, left, pivotIdx - 1);
        quickSort(nums, pivotIdx + 1, right);
    }

    public static int partition(int[] nums, int left, int right) {
        if (left > right) return -1;
        int pivot = left, i = left, j = right;
        while (i < j) {
            // 必须j在前：最后一次交换时：若i先，会在比pivot大的元素停下，交换时报错; j先，会在比pivot小的元素停下，交换正常
            while (i < j && nums[j] >= nums[pivot]) j--;
            while (i < j && nums[i] <= nums[pivot]) i++;
            swap(nums, i, j);
        }
        swap(nums, pivot, i);
        return i;
    }

    public static int partitionLoopInvariant1(int[] nums, int left, int right) {
        if (left > right) return -1;
        int pivot = nums[left];
        int pivotIdx = left, smallIdx = left;
        // all in [left+1, smallIdx], <= pivot; all in (smallIdx, bigIdx), > pivot
        for (int bigIdx = left + 1; bigIdx <= right; bigIdx++) {
            if(nums[bigIdx]<=pivot){
                swap(nums, ++smallIdx, bigIdx);
            }
        }
        swap(nums, pivotIdx, smallIdx);
        return smallIdx;
    }

    public static void swap(int[] nums, int fstIdx, int secIdx) {
        int temp = nums[fstIdx];
        nums[fstIdx] = nums[secIdx];
        nums[secIdx] = temp;
    }
}
