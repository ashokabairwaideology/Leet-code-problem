---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2366.Minimum%20Replacements%20to%20Sort%20the%20Array/README_EN.md
rating: 2060
source: Biweekly Contest 84 Q4
tags:
    - Greedy
    - Array
    - Math
---

<!-- problem:start -->

# [2366. Minimum Replacements to Sort the Array](https://leetcode.com/problems/minimum-replacements-to-sort-the-array)

[中文文档](/solution/2300-2399/2366.Minimum%20Replacements%20to%20Sort%20the%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. In one operation you can replace any element of the array with <strong>any two</strong> elements that <strong>sum</strong> to it.</p>

<ul>
	<li>For example, consider <code>nums = [5,6,7]</code>. In one operation, we can replace <code>nums[1]</code> with <code>2</code> and <code>4</code> and convert <code>nums</code> to <code>[5,2,4,7]</code>.</li>
</ul>

<p>Return <em>the minimum number of operations to make an array that is sorted in <strong>non-decreasing</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,9,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Here are the steps to sort the array in non-decreasing order:
- From [3,9,3], replace the 9 with 3 and 6 so the array becomes [3,3,6,3]
- From [3,3,6,3], replace the 6 with 3 and 3 so the array becomes [3,3,3,3,3]
There are 2 steps to sort the array in non-decreasing order. Therefore, we return 2.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array is already in non-decreasing order. Therefore, we return 0. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy Approach

We observe that to make the array $nums$ non-decreasing or monotonically increasing, the elements towards the end of the array should be as large as possible. Therefore, it is unnecessary to replace the last element $nums[n-1]$ of the array $nums$ with multiple smaller numbers.

In other words, we can traverse the array $nums$ from the end to the beginning, maintaining the current maximum value $mx$, initially $mx = nums[n-1]$.

-   If the current element $nums[i] \leq mx$, there is no need to replace $nums[i]$. We simply update $mx = nums[i]$.
-   Otherwise, we need to replace $nums[i]$ with multiple numbers that sum to $nums[i]$. The maximum of these numbers is $mx$, and the total number of replacements is $k=\left \lceil \frac{nums[i]}{mx} \right \rceil$. Therefore, we need to perform $k-1$ operations, which are added to the answer. Among these $k$ numbers, the smallest number is $\left \lfloor \frac{nums[i]}{k} \right \rfloor$. Therefore, we update $mx = \left \lfloor \frac{nums[i]}{k} \right \rfloor$.

After the traversal, we return the total number of operations.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumReplacement(self, nums: List[int]) -> int:
        ans = 0
        n = len(nums)
        mx = nums[-1]
        for i in range(n - 2, -1, -1):
            if nums[i] <= mx:
                mx = nums[i]
                continue
            k = (nums[i] + mx - 1) // mx
            ans += k - 1
            mx = nums[i] // k
        return ans
```

#### Java

```java
class Solution {
    public long minimumReplacement(int[] nums) {
        long ans = 0;
        int n = nums.length;
        int mx = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] <= mx) {
                mx = nums[i];
                continue;
            }
            int k = (nums[i] + mx - 1) / mx;
            ans += k - 1;
            mx = nums[i] / k;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumReplacement(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        int mx = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] <= mx) {
                mx = nums[i];
                continue;
            }
            int k = (nums[i] + mx - 1) / mx;
            ans += k - 1;
            mx = nums[i] / k;
        }
        return ans;
    }
};
```

#### Go

```go
func minimumReplacement(nums []int) (ans int64) {
	n := len(nums)
	mx := nums[n-1]
	for i := n - 2; i >= 0; i-- {
		if nums[i] <= mx {
			mx = nums[i]
			continue
		}
		k := (nums[i] + mx - 1) / mx
		ans += int64(k - 1)
		mx = nums[i] / k
	}
	return
}
```

#### TypeScript

```ts
function minimumReplacement(nums: number[]): number {
    const n = nums.length;
    let mx = nums[n - 1];
    let ans = 0;
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] <= mx) {
            mx = nums[i];
            continue;
        }
        const k = Math.ceil(nums[i] / mx);
        ans += k - 1;
        mx = Math.floor(nums[i] / k);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn minimum_replacement(nums: Vec<i32>) -> i64 {
        if nums.len() == 1 {
            return 0;
        }

        let n = nums.len();
        let mut max = *nums.last().unwrap();
        let mut ret = 0;

        for i in (0..=n - 2).rev() {
            if nums[i] <= max {
                max = nums[i];
                continue;
            }
            // Otherwise make the substitution
            let k = (nums[i] + max - 1) / max;
            ret += (k - 1) as i64;
            // Update the max value, which should be the minimum among the substitution
            max = nums[i] / k;
        }

        ret
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
