---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1848.Minimum%20Distance%20to%20the%20Target%20Element/README_EN.md
rating: 1216
source: Weekly Contest 239 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1848. Minimum Distance to the Target Element](https://leetcode.com/problems/minimum-distance-to-the-target-element)

[中文文档](/solution/1800-1899/1848.Minimum%20Distance%20to%20the%20Target%20Element/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> <strong>(0-indexed)</strong> and two integers <code>target</code> and <code>start</code>, find an index <code>i</code> such that <code>nums[i] == target</code> and <code>abs(i - start)</code> is <strong>minimized</strong>. Note that&nbsp;<code>abs(x)</code>&nbsp;is the absolute value of <code>x</code>.</p>

<p>Return <code>abs(i - start)</code>.</p>

<p>It is <strong>guaranteed</strong> that <code>target</code> exists in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], target = 5, start = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> nums[4] = 5 is the only value equal to target, so the answer is abs(4 - 3) = 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], target = 1, start = 0
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums[0] = 1 is the only value equal to target, so the answer is abs(0 - 0) = 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1,1,1,1,1,1], target = 1, start = 0
<strong>Output:</strong> 0
<strong>Explanation:</strong> Every value of nums is 1, but nums[0] minimizes abs(i - start), which is abs(0 - 0) = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= start &lt; nums.length</code></li>
	<li><code>target</code> is in <code>nums</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

Traverse the array, find all indices equal to $target$, then calculate $|i - start|$, and take the minimum value.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getMinDistance(self, nums: List[int], target: int, start: int) -> int:
        return min(abs(i - start) for i, x in enumerate(nums) if x == target)
```

#### Java

```java
class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int ans = n;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == target) {
                ans = Math.min(ans, Math.abs(i - start));
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getMinDistance(vector<int>& nums, int target, int start) {
        int n = nums.size();
        int ans = n;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == target) {
                ans = min(ans, abs(i - start));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getMinDistance(nums []int, target int, start int) int {
	ans := 1 << 30
	for i, x := range nums {
		if t := abs(i - start); x == target && t < ans {
			ans = t
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function getMinDistance(nums: number[], target: number, start: number): number {
    let ans = Infinity;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] === target) {
            ans = Math.min(ans, Math.abs(i - start));
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn get_min_distance(nums: Vec<i32>, target: i32, start: i32) -> i32 {
        nums.iter()
            .enumerate()
            .filter(|&(_, &x)| x == target)
            .map(|(i, _)| ((i as i32) - start).abs())
            .min()
            .unwrap_or_default()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
