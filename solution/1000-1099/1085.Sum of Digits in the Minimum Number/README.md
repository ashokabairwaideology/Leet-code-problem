---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1085.Sum%20of%20Digits%20in%20the%20Minimum%20Number/README.md
rating: 1256
source: 第 2 场双周赛 Q1
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [1085. 最小元素各数位之和 🔒](https://leetcode.cn/problems/sum-of-digits-in-the-minimum-number)

[English Version](/solution/1000-1099/1085.Sum%20of%20Digits%20in%20the%20Minimum%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数的数组 <code>A</code>。</p>

<p>然后计算 <code>S</code>，使其等于数组 <code>A</code> 当中最小的那个元素各个数位上数字之和。</p>

<p>最后，假如 <code>S</code> 所得计算结果是 <strong>奇数 </strong>，返回 0 ；否则请返回 1。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>[34,23,1,24,75,33,54,8]
<strong>输出：</strong>0
<strong>解释：</strong>
最小元素为 1 ，该元素各个数位上的数字之和 S = 1 ，是奇数所以答案为 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[99,77,33,66,55]
<strong>输出：</strong>1
<strong>解释：</strong>
最小元素为 33 ，该元素各个数位上的数字之和 S = 3 + 3 = 6 ，是偶数所以答案为 1 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= A.length <= 100</code></li>
	<li><code>1 <= A[i] <= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们先找到数组中的最小值，记为 $x$。然后计算 $x$ 的各个数位上的数字之和，记为 $s$。最后判断 $s$ 是否为奇数，若是则返回 $0$，否则返回 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfDigits(self, nums: List[int]) -> int:
        x = min(nums)
        s = 0
        while x:
            s += x % 10
            x //= 10
        return s & 1 ^ 1
```

#### Java

```java
class Solution {
    public int sumOfDigits(int[] nums) {
        int x = 100;
        for (int v : nums) {
            x = Math.min(x, v);
        }
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s & 1 ^ 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfDigits(vector<int>& nums) {
        int x = *min_element(nums.begin(), nums.end());
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s & 1 ^ 1;
    }
};
```

#### Go

```go
func sumOfDigits(nums []int) int {
	s := 0
	for x := slices.Min(nums); x > 0; x /= 10 {
		s += x % 10
	}
	return s&1 ^ 1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
