---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1671.Minimum%20Number%20of%20Removals%20to%20Make%20Mountain%20Array/README_EN.md
rating: 1912
source: Biweekly Contest 40 Q4
tags:
    - Greedy
    - Array
    - Binary Search
    - Dynamic Programming
---

<!-- problem:start -->

# [1671. Minimum Number of Removals to Make Mountain Array](https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array)

[中文文档](/solution/1600-1699/1671.Minimum%20Number%20of%20Removals%20to%20Make%20Mountain%20Array/README.md)

## Description

<!-- description:start -->

<p>You may recall that an array <code>arr</code> is a <strong>mountain array</strong> if and only if:</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>There exists some index <code>i</code> (<strong>0-indexed</strong>) with <code>0 &lt; i &lt; arr.length - 1</code> such that:
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i]</code></li>
		<li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>Given an integer array <code>nums</code>​​​, return <em>the <strong>minimum</strong> number of elements to remove to make </em><code>nums<em>​​​</em></code><em> </em><em>a <strong>mountain array</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array itself is a mountain array so we do not need to remove any elements.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,1,5,6,2,3,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>It is guaranteed that you can make a mountain array out of <code>nums</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

This problem can be transformed into finding the longest increasing subsequence and the longest decreasing subsequence.

We define $left[i]$ as the length of the longest increasing subsequence ending with $nums[i]$, and define $right[i]$ as the length of the longest decreasing subsequence starting with $nums[i]$.

Then the final answer is $n - \max(left[i] + right[i] - 1)$, where $1 \leq i \leq n$, and $left[i] \gt 1$ and $right[i] \gt 1$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMountainRemovals(self, nums: List[int]) -> int:
        n = len(nums)
        left = [1] * n
        right = [1] * n
        for i in range(1, n):
            for j in range(i):
                if nums[i] > nums[j]:
                    left[i] = max(left[i], left[j] + 1)
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                if nums[i] > nums[j]:
                    right[i] = max(right[i], right[j] + 1)
        return n - max(a + b - 1 for a, b in zip(left, right) if a > 1 and b > 1)
```

#### Java

```java
class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] > nums[j]) {
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 1 && right[i] > 1) {
                ans = Math.max(ans, left[i] + right[i] - 1);
            }
        }
        return n - ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumMountainRemovals(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n, 1), right(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    left[i] = max(left[i], left[j] + 1);
                }
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] > nums[j]) {
                    right[i] = max(right[i], right[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 1 && right[i] > 1) {
                ans = max(ans, left[i] + right[i] - 1);
            }
        }
        return n - ans;
    }
};
```

#### Go

```go
func minimumMountainRemovals(nums []int) int {
	n := len(nums)
	left, right := make([]int, n), make([]int, n)
	for i := range left {
		left[i], right[i] = 1, 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if nums[i] > nums[j] {
				left[i] = max(left[i], left[j]+1)
			}
		}
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if nums[i] > nums[j] {
				right[i] = max(right[i], right[j]+1)
			}
		}
	}
	ans := 0
	for i := range left {
		if left[i] > 1 && right[i] > 1 {
			ans = max(ans, left[i]+right[i]-1)
		}
	}
	return n - ans
}
```

#### TypeScript

```ts
function minimumMountainRemovals(nums: number[]): number {
    const n = nums.length;
    const left = Array(n).fill(1);
    const right = Array(n).fill(1);
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[i] > nums[j]) {
                left[i] = Math.max(left[i], left[j] + 1);
            }
        }
    }
    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            if (nums[i] > nums[j]) {
                right[i] = Math.max(right[i], right[j] + 1);
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (left[i] > 1 && right[i] > 1) {
            ans = Math.max(ans, left[i] + right[i] - 1);
        }
    }
    return n - ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_mountain_removals(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut left = vec![1; n];
        let mut right = vec![1; n];
        for i in 1..n {
            for j in 0..i {
                if nums[i] > nums[j] {
                    left[i] = left[i].max(left[j] + 1);
                }
            }
        }
        for i in (0..n - 1).rev() {
            for j in i + 1..n {
                if nums[i] > nums[j] {
                    right[i] = right[i].max(right[j] + 1);
                }
            }
        }

        let mut ans = 0;
        for i in 0..n {
            if left[i] > 1 && right[i] > 1 {
                ans = ans.max(left[i] + right[i] - 1);
            }
        }

        (n as i32) - ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
