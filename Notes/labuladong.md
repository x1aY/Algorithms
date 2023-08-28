# 数据结构

## 堆

[二叉堆](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-daeca/er-cha-dui-1a386/)

---

[合并k个有序链表](https://leetcode.cn/problems/merge-k-sorted-lists/)

优先级队列，二叉堆

## 链表

[分隔链表](https://leetcode.cn/problems/partition-list/)

如果我们需要把原链表的节点接到新链表上，而不是 new 新节点来组成新链表的话，那么断开节点和原链表之间的链接可能是必要的。但凡遇到这种情况，就把原链表的节点断开，这样就不会出错了

---

[反转链表](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/di-gui-mo--10b77/)

TODO

```java
// 定义：输入一个单链表头结点，将该链表反转，返回新的头结点
ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode last = reverse(head.next);
    head.next.next = head;
    head.next = null;
    return last;
}
```

---

TODO

[k个一组反转链表](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/ru-he-k-ge-d591d/)


[回文链表](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/ru-he-pan--f9d3c/)



# 双指针

## 快慢指针

[删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)

让fst先走N步，构造长度为N的间隔，再让fst和sec同时走，fst走到末尾时sec就到了目标位置

[题解](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/shuang-zhi-0f7cc/#%E5%8D%95%E9%93%BE%E8%A1%A8%E7%9A%84%E5%80%92%E6%95%B0%E7%AC%AC-k-%E4%B8%AA%E8%8A%82%E7%82%B9)

---

[链表的中间节点](https://leetcode.cn/problems/middle-of-the-linked-list/)

快慢指针

---

[环形链表](https://leetcode.cn/problems/linked-list-cycle-ii/)

[图示题解](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/shuang-zhi-0f7cc/#%E5%88%A4%E6%96%AD%E9%93%BE%E8%A1%A8%E6%98%AF%E5%90%A6%E5%8C%85%E5%90%AB%E7%8E%AF)

```java
class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }

        // fast 遇到空指针说明没有环
        if (fast == null || fast.next == null) return null;
          
        // 重新指向头结点
        slow = head;
        // 快慢指针同步前进，相交点就是环起点
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

```

---

[相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

如果用两个指针 p1 和 p2 分别在两条链表上前进，并不能同时走到公共节点，也就无法得到相交节点 c1。

解决这个问题的关键是，通过某些方式，让 p1 和 p2 能够同时到达相交节点 c1。

所以，我们可以让 p1 遍历完链表 A 之后开始遍历链表 B，让 p2 遍历完链表 B 之后开始遍历链表 A，这样相当于「逻辑上」两条链表接在了一起。

如果这样进行拼接，就可以让 p1 和 p2 同时进入公共部分，也就是同时到达相交节点 c1

[题解](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/shuang-zhi-0f7cc/#%E4%B8%A4%E4%B8%AA%E9%93%BE%E8%A1%A8%E6%98%AF%E5%90%A6%E7%9B%B8%E4%BA%A4)

---

[删除有序数组中的重复项](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)

[删除链表中的重复元素](https://leetcode.cn/problems/remove-duplicates-from-sorted-list/)

[移除元素](https://leetcode.cn/problems/remove-element/)

[移动0](https://leetcode.cn/problems/move-zeroes/)

让慢指针 slow 走在后面，快指针 fast 走在前面探路，找到一个不重复的元素就赋值给 slow 并让 slow 前进一步。

这样，就保证了 nums[0..slow] 都是无重复的元素，当 fast 指针遍历完整个数组 nums 后，nums[0..slow] 就是整个数组去重之后的结果

<img src="https://labuladong.gitee.io/algo/images/%E6%95%B0%E7%BB%84%E5%8E%BB%E9%87%8D/1.gif" alt="图示" style="zoom: 50%;" />

```java
int removeDuplicates(int[] nums) {
    if (nums.length == 0) {
        return 0;
    }
    for(int slow = 0, fast = 0; fast < nums.length; fast++){
        if (nums[fast] != nums[slow]) {
            slow++;
            // 维护 nums[0..slow] 无重复
            nums[slow] = nums[fast];
        }
    }
    // 数组长度为索引 + 1
    return slow + 1;
}

```

## 左右指针：

## 回文

[最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)

如果输入相同的 l 和 r，就相当于寻找长度为奇数的回文串，如果输入相邻的 l 和 r，则相当于寻找长度为偶数的回文串:

```java
// 在 s 中寻找以 s[l] 和 s[r] 为中心的最长回文串
String palindrome(String s, int l, int r) {
    // 防止索引越界
    while (l >= 0 && r < s.length()
            && s.charAt(l) == s.charAt(r)) {
        // 双指针，向两边展开
        l--; r++;
    }
    // 返回以 s[l] 和 s[r] 为中心的最长回文串
    return s.substring(l + 1, r);
}

```

```java
String longestPalindrome(String s) {
    String res = "";
    for (int i = 0; i < s.length(); i++) {
        // 以 s[i] 为中心的最长回文子串
        String s1 = palindrome(s, i, i);
        // 以 s[i] 和 s[i+1] 为中心的最长回文子串
        String s2 = palindrome(s, i, i + 1);
        // res = longest(res, s1, s2)
        res = res.length() > s1.length() ? res : s1;
        res = res.length() > s2.length() ? res : s2;
    }
    return res;
}

```

## 二分查找


### 求target的左右边界


```java
    public int binSearchBound(int[] nums, int left, int right, 
                                int target, boolean isLeft) {
        if (left < 0 || right > nums.length) return -1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                res = mid;
                if (isLeft)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return res;
    }
```

### 模板

```java

int binary_search(int[] nums, int target) {
    int left = 0, right = nums.length - 1; 
    while(left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1; 
        } else if(nums[mid] == target) {
            // 直接返回
            return mid;
        }
    }
    // 直接返回
    return -1;
}

int left_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 别返回，锁定左侧边界
            right = mid - 1;
        }
    }
    // 判断 target 是否存在于 nums 中
    if (left < 0 || left >= nums.length) {
        return -1;
    }
    // 判断一下 nums[left] 是不是 target
    return nums[left] == target ? left : -1;
}

int right_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 别返回，锁定右侧边界
            left = mid + 1;
        }
    }
    // 判断 target 是否存在于 nums 中
    // if (left - 1 < 0 || left - 1 >= nums.length) {
    //     return -1;
    // }
    
    // 由于 while 的结束条件是 right == left - 1，且现在在求右边界
    // 所以用 right 替代 left - 1 更好记
    if (right < 0 || right >= nums.length) {
        return -1;
    }
    return nums[right] == target ? right : -1;
}
```


## 滑动窗口

[视频讲解](https://www.bilibili.com/video/BV1AV4y1n7Zt)

[讲解](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/wo-xie-le--f02cd/)

# 前缀和/差分数组

[视频讲解](https://www.bilibili.com/video/BV1NY4y1J7xQ)


# 数组遍历技巧

[讲解](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/er-wei-shu-150fb/)