---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1262.Greatest%20Sum%20Divisible%20by%20Three/README_EN.md
rating: 1762
source: Weekly Contest 163 Q3
tags:
    - Greedy
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [1262. Greatest Sum Divisible by Three](https://leetcode.com/problems/greatest-sum-divisible-by-three)

[中文文档](/solution/1200-1299/1262.Greatest%20Sum%20Divisible%20by%20Three/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code>, return <em>the <strong>maximum possible sum </strong>of elements of the array such that it is divisible by three</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6,5,1,8]
<strong>Output:</strong> 18
<strong>Explanation:</strong> Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since 4 is not divisible by 3, do not pick any number.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,4]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the maximum sum of several numbers selected from the first $i$ numbers, such that the sum modulo $3$ equals $j$. Initially, $f[0][0]=0$, and the rest are $-\infty$.

For $f[i][j]$, we can consider the state of the $i$th number $x$:

-   If we do not select $x$, then $f[i][j]=f[i-1][j]$;
-   If we select $x$, then $f[i][j]=f[i-1][(j-x \bmod 3 + 3)\bmod 3]+x$.

Therefore, we can get the state transition equation:

$$
f[i][j]=\max\{f[i-1][j],f[i-1][(j-x \bmod 3 + 3)\bmod 3]+x\}
$$

The final answer is $f[n][0]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

Note that the value of $f[i][j]$ is only related to $f[i-1][j]$ and $f[i-1][(j-x \bmod 3 + 3)\bmod 3]$, so we can use a rolling array to optimize the space complexity, reducing the space complexity to $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSumDivThree(self, nums: List[int]) -> int:
        n = len(nums)
        f = [[-inf] * 3 for _ in range(n + 1)]
        f[0][0] = 0
        for i, x in enumerate(nums, 1):
            for j in range(3):
                f[i][j] = max(f[i - 1][j], f[i - 1][(j - x) % 3] + x)
        return f[n][0]
```

#### Java

```java
class Solution {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        final int inf = 1 << 30;
        int[][] f = new int[n + 1][3];
        f[0][1] = f[0][2] = -inf;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j < 3; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][(j - x % 3 + 3) % 3] + x);
            }
        }
        return f[n][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSumDivThree(vector<int>& nums) {
        int n = nums.size();
        const int inf = 1 << 30;
        int f[n + 1][3];
        f[0][0] = 0;
        f[0][1] = f[0][2] = -inf;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j < 3; ++j) {
                f[i][j] = max(f[i - 1][j], f[i - 1][(j - x % 3 + 3) % 3] + x);
            }
        }
        return f[n][0];
    }
};
```

#### Go

```go
func maxSumDivThree(nums []int) int {
	n := len(nums)
	const inf = 1 << 30
	f := make([][3]int, n+1)
	f[0] = [3]int{0, -inf, -inf}
	for i, x := range nums {
		i++
		for j := 0; j < 3; j++ {
			f[i][j] = max(f[i-1][j], f[i-1][(j-x%3+3)%3]+x)
		}
	}
	return f[n][0]
}
```

#### TypeScript

```ts
function maxSumDivThree(nums: number[]): number {
    const n = nums.length;
    const inf = 1 << 30;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(3).fill(-inf));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        const x = nums[i - 1];
        for (let j = 0; j < 3; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[i - 1][(j - (x % 3) + 3) % 3] + x);
        }
    }
    return f[n][0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSumDivThree(self, nums: List[int]) -> int:
        f = [0, -inf, -inf]
        for x in nums:
            g = f[:]
            for j in range(3):
                g[j] = max(f[j], f[(j - x) % 3] + x)
            f = g
        return f[0]
```

#### Java

```java
class Solution {
    public int maxSumDivThree(int[] nums) {
        final int inf = 1 << 30;
        int[] f = new int[] {0, -inf, -inf};
        for (int x : nums) {
            int[] g = f.clone();
            for (int j = 0; j < 3; ++j) {
                g[j] = Math.max(f[j], f[(j - x % 3 + 3) % 3] + x);
            }
            f = g;
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSumDivThree(vector<int>& nums) {
        const int inf = 1 << 30;
        vector<int> f = {0, -inf, -inf};
        for (int& x : nums) {
            vector<int> g = f;
            for (int j = 0; j < 3; ++j) {
                g[j] = max(f[j], f[(j - x % 3 + 3) % 3] + x);
            }
            f = move(g);
        }
        return f[0];
    }
};
```

#### Go

```go
func maxSumDivThree(nums []int) int {
	const inf = 1 << 30
	f := [3]int{0, -inf, -inf}
	for _, x := range nums {
		g := [3]int{}
		for j := range f {
			g[j] = max(f[j], f[(j-x%3+3)%3]+x)
		}
		f = g
	}
	return f[0]
}
```

#### TypeScript

```ts
function maxSumDivThree(nums: number[]): number {
    const inf = 1 << 30;
    const f: number[] = [0, -inf, -inf];
    for (const x of nums) {
        const g = [...f];
        for (let j = 0; j < 3; ++j) {
            f[j] = Math.max(g[j], g[(j - (x % 3) + 3) % 3] + x);
        }
    }
    return f[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
