---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.17.Contiguous%20Sequence/README.md
---

<!-- problem:start -->

# [面试题 16.17. 连续数列](https://leetcode.cn/problems/contiguous-sequence-lcci)

[English Version](/lcci/16.17.Contiguous%20Sequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong> [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出：</strong> 6
<strong>解释：</strong> 连续子数组 [4,-1,2,1] 的和最大，为 6。
</pre>

<p><strong>进阶：</strong></p>

<p>如果你已经实现复杂度为 O(<em>n</em>) 的解法，尝试使用更为精妙的分治法求解。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i]$ 表示以 $nums[i]$ 结尾的连续子数组的最大和，那么状态转移方程为：

$$
f[i] = \max(f[i-1], 0) + nums[i]
$$

其中 $f[0] = nums[0]$。

答案为 $\max\limits_{i=0}^{n-1}f[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

我们注意到 $f[i]$ 只与 $f[i-1]$ 有关，所以我们可以用一个变量 $f$ 来表示 $f[i-1]$，从而将空间复杂度降低到 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans = f = -inf
        for x in nums:
            f = max(f, 0) + x
            ans = max(ans, f)
        return ans
```

#### Java

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE, f = Integer.MIN_VALUE;
        for (int x : nums) {
            f = Math.max(f, 0) + x;
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int ans = INT_MIN, f = INT_MIN;
        for (int x : nums) {
            f = max(f, 0) + x;
            ans = max(ans, f);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSubArray(nums []int) int {
	ans, f := math.MinInt32, math.MinInt32
	for _, x := range nums {
		f = max(f, 0) + x
		ans = max(ans, f)
	}
	return ans
}
```

#### TypeScript

```ts
function maxSubArray(nums: number[]): number {
    let [ans, f] = [-Infinity, -Infinity];
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        ans = Math.max(ans, f);
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let [ans, f] = [-Infinity, -Infinity];
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        ans = Math.max(ans, f);
    }
    return ans;
};
```

#### Swift

```swift
class Solution {
    func maxSubArray(_ nums: [Int]) -> Int {
        var ans = Int.min
        var f = Int.min

        for x in nums {
            f = max(f, 0) + x
            ans = max(ans, f)
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
