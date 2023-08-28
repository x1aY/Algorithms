### 二分法求target的左右边界


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

### 寻找最长回文串

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


### 计算质数

```java

public List<Integer> getPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
    
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                ans.add(i);
            }
        }
    
        return ans;
    }

```


### 判断溢出

```java
    /* *
     * (x ^ r) & (y ^ r)将用运算符算出来的r与x和y分别异或，异或的规则是同0异1。
     * 我们只看符号位，如果符号位不同，那么两个括号得到的都是1。
     * 而与运算又是只有1 & 1才是1，有一个为0都是0。
     * 因此，一旦溢出，无论是正溢出负溢出，(x ^ r) & (y ^ r)计算出的结果都应该小于0
     * 源码： Math.addExtract(), Math.multiplyExtract()
     */
    public boolean addOverflow(int a, int b, int res) {
        return ((a ^ res) & (b ^ res)) < 0;
    }
    
```

