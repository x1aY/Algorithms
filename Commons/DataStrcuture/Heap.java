package DataStrcuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Heap<T> {

    List<T> heapList;

    int currLen = 0;

    private Comparator<T> compare;

    public Heap(Comparator<T> comparator) {
        this.compare = comparator;
        this.heapList = new ArrayList<T>();
    }

    public Heap(List<T> list, Comparator<T> comparator) {
        this.compare = comparator;
        this.heapList = buildHeap(list);
        this.currLen = this.heapList.size();
    }

    public void heapSort() {
        for (int i = currLen - 1; i > 0; i--) {
            swapVal(heapList, 0, i);
            sinkDown(heapList, 0, i);
        }
        Collections.reverse(heapList);
    }

    /* 堆排序, 下沉操作sink */

    /**
     * 经过 buildHeap 函数构建堆后，堆的最大（或最小，具体取决于堆的类型）元素就位于堆的根节点位置（索引为 0）
     * 交换根节点和最后一个元素： 初始时，最大元素位于堆的根节点（索引为 0）。首先，将根节点的值与堆中最后一个元素的值进行交换。这相当于将最大元素移出堆，放到了列表的末尾位置。
     * 恢复堆属性： 交换后，最大元素现在位于列表的最后。然而，这个时候堆属性可能被破坏了（根节点的值可能小于其子节点的值）。因此，需要对根节点进行下沉操作，将其逐步下沉到合适的位置，恢复堆的属性。
     */
    public List<T> heapSort(List<T> list) {
        list = buildHeap(list);
        for (int i = list.size() - 1; i > 0; i--) {
            swapVal(list, 0, i);
            sinkDown(list, 0, i);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * -----为什么从 len/2-1 开始：
     * 堆的叶子节点位于二叉树的底部，它们自身可以看作已经满足堆属性，因为它们没有子节点。
     * 所以，我们只需要对非叶子节点进行操作，以确保堆属性被满足。
     * 在一个堆的二叉树表示中，最后一个非叶子节点的索引是 len/2-1。因此，for 循环从 len/2-1开始，
     * 逐个对这些节点进行下沉操作，从而逐步将整个列表转换为堆。这是因为在这个点之后，所有的节点都是叶子节点或者不需要进行下沉操作的节点。
     * 通过从最后一个非叶子节点开始，逐渐向上移动，每次进行下沉操作，可以保证在每一步操作中，当前节点及其子节点都满足堆的性质，最终实现整个列表的堆有序化
     * 
     * -----为什么for循环是逆序而非顺序：
     * 如果你从根节点开始，首先需要将根节点与其子节点进行比较，然后根据需要交换它们的位置。然后，你必须继续将根节点的子节点与它们的子节点进行比较和交换。这就会导致在构建堆的过程中进行很多不必要的操作，因为在根节点进行下沉操作时，底部的叶子节点还没有满足堆属性，而这些叶子节点实际上是已经满足堆属性的。
     * 而如果从最后一个非叶子节点开始，你将直接对那些需要下沉的节点进行操作，而跳过了那些已经是叶子节点的部分。这样能够减少操作次数，提高算法效率。
     * 
     * sinkDown是从当前结点往下交换，假设这是大顶堆，如果在底下（比如叶子）有一个最大的数字，按照从上往下的顺序调整，最后这个数字最多和他的父元素交换，而不会跑到最顶上去
     */
    private List<T> buildHeap(List<T> list) {
        int len = list.size();
        for (int i = len / 2 - 1; i >= 0; i--) {
            sinkDown(list, i, len);
        }
        return new ArrayList<>(list);
    }

    /**
     * 创建堆/排序/删除元素, 下沉操作sink 
     * 先假设再比较
     * */
    private void sinkDown(List<T> list, int x, int listLen) {
        // 如果沉到堆底，就沉不下去了
        while (LChild(x) < listLen) {
            // 先假设左边节点较大
            int max = LChild(x);
            // 如果右边节点存在，比一下大小
            if (RChild(x) < listLen && less(list.get(max), list.get(RChild(x))))
                max = RChild(x);
            // 结点 x 比俩孩子都大，就不必下沉了
            if (less(list.get(max), list.get(x)))
                break;
            // 否则，不符合最大堆的结构，下沉 x 结点
            swapVal(x, max);
            x = max;
        }
    }

    public T pop() {
        T res = getItem(heapList, 0);
        setItem(heapList, 0, heapList.remove(--currLen));
        sinkDown(heapList, 0, currLen);
        return res;
    }

    /* 添加元素, 上浮操作float */

    public void pushAll(List<T> list) {
        for (T listi : list)
            push(listi);
    }

    public void push(T item) {
        heapList.add(item);
        floatUp(heapList, currLen++, item);
    }

    private void floatUp(List<T> list, int index, T item) {
        int len = list.size(), pIndex = parent(index);
        while (inRange(pIndex, len) && less(item, getItem(list, pIndex))) {
            swapVal(list, index, pIndex);
            index = pIndex;
            pIndex = parent(pIndex);
        }
    }

    /* 对Comparator接口的再包装 */

    private boolean less(T first, T second) {
        return compare.compare(first, second) < 0;
    }

    /* 基本操作 */

    public int length() {
        return currLen;
    }

    private void swapVal(List<T> list, int first, int second) {
        T temp = getItem(list, first);
        setItem(list, first, getItem(list, second));
        setItem(list, second, temp);
    }

    private void swapVal(int first, int second) {
        T temp = getItem(heapList, first);
        setItem(heapList, first, getItem(heapList, second));
        setItem(heapList, second, temp);
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int LChild(int index) {
        return index * 2 + 1;
    }

    private int RChild(int index) {
        return index * 2 + 2;
    }

    private T getItem(List<T> list, int index) {
        return list.get(index);
    }

    private T setItem(List<T> list, int index, T val) {
        return list.set(index, val);
    }

    private boolean inRange(int index, int listLen) {
        return 0 <= index && index < listLen;
    }

    @Override
    public String toString() {
        return String.join(", ",
                heapList.stream()
                        .map(item -> item.toString())
                        .collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(
                Arrays.asList(8, 2, 1, 9, 4, 5, 6, 7, 3),
                (fst, sec) -> fst - sec);

        heap.push(-1);
        heap.pushAll(Arrays.asList(18, 12, 11, 19, 24, 35, 16, 27, 23));
        heap.pop();
        System.out.println(heap.toString());

        heap.heapSort();
        System.out.println(heap.toString());

        System.out.println(heap.heapSort(Arrays.asList(5, 6, 7, 3, 8, 2, 1, 9, 4)));
    }

}