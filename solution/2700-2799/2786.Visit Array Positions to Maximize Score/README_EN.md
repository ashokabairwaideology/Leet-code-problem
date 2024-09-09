---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2786.Visit%20Array%20Positions%20to%20Maximize%20Score/README_EN.md
rating: 1732
source: Biweekly Contest 109 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [2786. Visit Array Positions to Maximize Score](https://leetcode.com/problems/visit-array-positions-to-maximize-score)

[中文文档](/solution/2700-2799/2786.Visit%20Array%20Positions%20to%20Maximize%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and a positive integer <code>x</code>.</p>

<p>You are <strong>initially</strong> at position <code>0</code> in the array and you can visit other positions according to the following rules:</p>

<ul>
	<li>If you are currently in position <code>i</code>, then you can move to <strong>any</strong> position <code>j</code> such that <code>i &lt; j</code>.</li>
	<li>For each position <code>i</code> that you visit, you get a score of <code>nums[i]</code>.</li>
	<li>If you move from a position <code>i</code> to a position <code>j</code> and the <strong>parities</strong> of <code>nums[i]</code> and <code>nums[j]</code> differ, then you lose a score of <code>x</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> total score you can get</em>.</p>

<p><strong>Note</strong> that initially you have <code>nums[0]</code> points.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,6,1,9,2], x = 5
<strong>Output:</strong> 13
<strong>Explanation:</strong> We can visit the following positions in the array: 0 -&gt; 2 -&gt; 3 -&gt; 4.
The corresponding values are 2, 6, 1 and 9. Since the integers 6 and 1 have different parities, the move 2 -&gt; 3 will make you lose a score of x = 5.
The total score will be: 2 + 6 + 1 + 9 - 5 = 13.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,6,8], x = 3
<strong>Output:</strong> 20
<strong>Explanation:</strong> All the integers in the array have the same parities, so we can visit all of them without losing any score.
The total score is: 2 + 4 + 6 + 8 = 20.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], x &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

Based on the problem description, we can draw the following conclusions:

1. Moving from position $i$ to position $j$, if $nums[i]$ and $nums[j]$ have different parities, then $x$ points will be lost;
2. Moving from position $i$ to position $j$, if $nums[i]$ and $nums[j]$ have the same parity, then no points will be lost.

Therefore, we can use an array $f$ of length $2$ to represent the maximum score when the current position's parity is $0$ and $1$. Initially, the values of $f$ are $-\infty$, and then we initialize $f[nums[0] \& 1] = nums[0]$, indicating the score at the initial position.

Next, we start traversing the array $nums$ from position $1$. For each position $i$ corresponding to the value $v$, we update the value of $f[v \& 1]$ to be the larger value between $f[v \& 1]$ and $f[v \& 1 \oplus 1] - x$ plus $v$, i.e., $f[v \& 1] = \max(f[v \& 1], f[v \& 1 \oplus 1] - x) + v$.

The answer is the larger value between $f[0]$ and $f[1]$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int], x: int) -> int:
        f = [-inf] * 2
        f[nums[0] & 1] = nums[0]
        for v in nums[1:]:
            f[v & 1] = max(f[v & 1], f[v & 1 ^ 1] - x) + v
        return max(f)
```

#### Java

```java
class Solution {
    public long maxScore(int[] nums, int x) {
        long[] f = new long[2];
        Arrays.fill(f, -(1L << 60));
        f[nums[0] & 1] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int v = nums[i];
            f[v & 1] = Math.max(f[v & 1], f[v & 1 ^ 1] - x) + v;
        }
        return Math.max(f[0], f[1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& nums, int x) {
        const long long inf = 1LL << 60;
        vector<long long> f(2, -inf);
        f[nums[0] & 1] = nums[0];
        int n = nums.size();
        for (int i = 1; i < n; ++i) {
            int v = nums[i];
            f[v & 1] = max(f[v & 1], f[v & 1 ^ 1] - x) + v;
        }
        return max(f[0], f[1]);
    }
};
```

#### Go

```go
func maxScore(nums []int, x int) int64 {
	const inf int = 1 << 40
	f := [2]int{-inf, -inf}
	f[nums[0]&1] = nums[0]
	for _, v := range nums[1:] {
		f[v&1] = max(f[v&1], f[v&1^1]-x) + v
	}
	return int64(max(f[0], f[1]))
}
```

#### TypeScript

```ts
function maxScore(nums: number[], x: number): number {
    const f: number[] = Array(2).fill(-Infinity);
    f[nums[0] & 1] = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        const v = nums[i];
        f[v & 1] = Math.max(f[v & 1], f[(v & 1) ^ 1] - x) + v;
    }
    return Math.max(...f);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
