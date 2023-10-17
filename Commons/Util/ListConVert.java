package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author x1ay
 */
public class ListConVert {


    public static List<Integer> int2List(int[] nums){
        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    public static int[] list2int(List<Integer> nums){
        return nums.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static <T> void printArray(List<T> array) {
        System.out.println(Arrays.toString(array.toArray()));
    }

    /**
     * List<Integer>、Integer[]、int[]的相互转化
     * https://zhuanlan.zhihu.com/p/196698839
     */
    public static void ListConversions(){
         int[] nums = { 1, 2, 3, 4, 5 };
         Integer[] IntegerNums = { 1, 2, 3, 4, 5 };
         List<Integer> ListNums = new ArrayList<>(Arrays.asList(1,2,3,4,5));

         int[] Integer2Int = Arrays.stream(IntegerNums).mapToInt(Integer::valueOf).toArray();
         int[] List2Int = ListNums.stream().mapToInt(Integer::valueOf).toArray();

         Integer[] Int2Integer = Arrays.stream(nums).boxed().toArray(Integer[]::new);
         Integer[] List2Integer = ListNums.toArray(new Integer[0]);

         List<Integer> int2List = Arrays.stream(nums).boxed().collect(Collectors.toList());
         List<Integer> Integer2List = Arrays.asList(IntegerNums);
    }

}
