---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0747.Largest%20Number%20At%20Least%20Twice%20of%20Others/README.md
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [747. 至少是其他数字两倍的最大数](https://leetcode.cn/problems/largest-number-at-least-twice-of-others)

[English Version](/solution/0700-0799/0747.Largest%20Number%20At%20Least%20Twice%20of%20Others/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，其中总是存在 <strong>唯一的</strong> 一个最大整数 。</p>

<p>请你找出数组中的最大元素并检查它是否 <strong>至少是数组中每个其他数字的两倍</strong> 。如果是，则返回 <strong>最大元素的下标</strong> ，否则返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,6,1,0]
<strong>输出：</strong>1
<strong>解释：</strong>6 是最大的整数，对于数组中的其他整数，6 至少是数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>-1
<strong>解释：</strong>4 没有超过 3 的两倍大，所以返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 50</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> 中的最大元素是唯一的</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们可以遍历数组 $nums$，找到数组中的最大值 $x$ 和第二大的值 $y$，如果 $x \ge 2y$，则返回 $x$ 的下标，否则返回 $-1$。

我们也可以先找到数组中的最大值 $x$，同时找到最大值 $x$ 的下标 $k$。然后再遍历一次数组，如果发现 $k$ 以外的元素 $y$ 满足 $x < 2y$，则返回 $-1$。否则遍历结束后返回 $k$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dominantIndex(self, nums: List[int]) -> int:
        x, y = nlargest(2, nums)
        return nums.index(x) if x >= 2 * y else -1
```

#### Java

```java
class Solution {
    public int dominantIndex(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[k] < nums[i]) {
                k = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (k != i && nums[k] < nums[i] * 2) {
                return -1;
            }
        }
        return k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int n = nums.size();
        int k = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[k] < nums[i]) {
                k = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (k != i && nums[k] < nums[i] * 2) {
                return -1;
            }
        }
        return k;
    }
};
```

#### Go

```go
func dominantIndex(nums []int) int {
	k := 0
	for i, x := range nums {
		if nums[k] < x {
			k = i
		}
	}
	for i, x := range nums {
		if k != i && nums[k] < x*2 {
			return -1
		}
	}
	return k
}
```

#### TypeScript

```ts
function dominantIndex(nums: number[]): number {
    let k = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] > nums[k]) {
            k = i;
        }
    }
    for (let i = 0; i < nums.length; ++i) {
        if (i !== k && nums[k] < nums[i] * 2) {
            return -1;
        }
    }
    return k;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var dominantIndex = function (nums) {
    let k = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] > nums[k]) {
            k = i;
        }
    }
    for (let i = 0; i < nums.length; ++i) {
        if (i !== k && nums[k] < nums[i] * 2) {
            return -1;
        }
    }
    return k;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
