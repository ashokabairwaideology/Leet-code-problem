---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20009.%20%E4%B9%98%E7%A7%AF%E5%B0%8F%E4%BA%8E%20K%20%E7%9A%84%E5%AD%90%E6%95%B0%E7%BB%84/README.md
---

<!-- problem:start -->

# [剑指 Offer II 009. 乘积小于 K 的子数组](https://leetcode.cn/problems/ZVAVXX)

## 题目描述

<!-- description:start -->

<p>给定一个正整数数组&nbsp;<code>nums</code>和整数 <code>k</code>&nbsp;，请找出该数组内乘积小于&nbsp;<code>k</code>&nbsp;的连续的子数组的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [10,5,2,6], k = 100
<strong>输出:</strong> 8
<strong>解释:</strong> 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3], k = 0
<strong>输出:</strong> 0</pre>

<p>&nbsp;</p>

<p><strong>提示:&nbsp;</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 713&nbsp;题相同：<a href="https://leetcode.cn/problems/subarray-product-less-than-k/">https://leetcode.cn/problems/subarray-product-less-than-k/</a>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们使用滑动窗口维护一个乘积不超过 $k$ 的连续子数组。每次右边界 $j$ 向右移动一位，如果乘积超过了 $k$，则左边界 $i$ 向右移动，直到乘积小于 $k$。那么以右边界 $j$ 为结尾的子数组个数为 $j - i + 1$，我们将其累加到答案中。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        s = 1
        ans = i = 0
        for j, x in enumerate(nums):
            s *= x
            while i <= j and s >= k:
                s //= nums[i]
                i += 1
            ans += j - i + 1
        return ans
```

#### Java

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        long s = 1;
        int ans = 0;
        for (int i = 0, j = 0; j < nums.length; ++j) {
            s *= nums[j];
            while (i <= j && s >= k) {
                s /= nums[i++];
            }
            ans += j - i + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        long long s = 1;
        int ans = 0, n = nums.size();
        for (int i = 0, j = 0; j < n; ++j) {
            s *= nums[j];
            while (i <= j && s >= k) {
                s /= nums[i++];
            }
            ans += j - i + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func numSubarrayProductLessThanK(nums []int, k int) int {
	s := 1
	ans, i := 0, 0
	for j, x := range nums {
		s *= x
		for i <= j && s >= k {
			s /= nums[i]
			i++
		}
		ans += j - i + 1
	}
	return ans
}
```

#### TypeScript

```ts
function numSubarrayProductLessThanK(nums: number[], k: number): number {
    let s = 1;
    let ans = 0;
    const n = nums.length;
    for (let i = 0, j = 0; j < n; ++j) {
        s *= nums[j];
        while (i <= j && s >= k) {
            s /= nums[i++];
        }
        ans += j - i + 1;
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func numSubarrayProductLessThanK(_ nums: [Int], _ k: Int) -> Int {
        if k <= 1 { return 0 }

        var product: Int = 1
        var ans: Int = 0
        var left: Int = 0

        for right in 0..<nums.count {
            product *= nums[right]
            while product >= k {
                product /= nums[left]
                left += 1
            }
            ans += right - left + 1
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
