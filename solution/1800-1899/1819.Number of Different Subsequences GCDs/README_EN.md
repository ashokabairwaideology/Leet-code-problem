---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1819.Number%20of%20Different%20Subsequences%20GCDs/README_EN.md
rating: 2539
source: Weekly Contest 235 Q4
tags:
    - Array
    - Math
    - Counting
    - Number Theory
---

<!-- problem:start -->

# [1819. Number of Different Subsequences GCDs](https://leetcode.com/problems/number-of-different-subsequences-gcds)

[中文文档](/solution/1800-1899/1819.Number%20of%20Different%20Subsequences%20GCDs/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> that consists of positive integers.</p>

<p>The <strong>GCD</strong> of a sequence of numbers is defined as the greatest integer that divides <strong>all</strong> the numbers in the sequence evenly.</p>

<ul>
	<li>For example, the GCD of the sequence <code>[4,6,16]</code> is <code>2</code>.</li>
</ul>

<p>A <strong>subsequence</strong> of an array is a sequence that can be formed by removing some elements (possibly none) of the array.</p>

<ul>
	<li>For example, <code>[2,5,10]</code> is a subsequence of <code>[1,2,1,<strong><u>2</u></strong>,4,1,<u><strong>5</strong></u>,<u><strong>10</strong></u>]</code>.</li>
</ul>

<p>Return <em>the <strong>number</strong> of <strong>different</strong> GCDs among all <strong>non-empty</strong> subsequences of</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1819.Number%20of%20Different%20Subsequences%20GCDs/images/image-1.png" style="width: 149px; height: 309px;" />
<pre>
<strong>Input:</strong> nums = [6,10,3]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The figure shows all the non-empty subsequences and their GCDs.
The different GCDs are 6, 10, 3, 2, and 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,15,40,5,6]
<strong>Output:</strong> 7
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Mathematics

For all sub-sequences of the array $nums$, their greatest common divisor (GCD) will not exceed the maximum value $mx$ in the array.

Therefore, we can enumerate each number $x$ in $[1,.. mx]$, and determine whether $x$ is the GCD of a sub-sequence of the array $nums$. If it is, then we increment the answer by one.

So the problem is transformed into: determining whether $x$ is the GCD of a sub-sequence of the array $nums$. We can do this by enumerating the multiples $y$ of $x$, and checking whether $y$ exists in the array $nums$. If $y$ exists in the array $nums$, then we calculate the GCD $g$ of $y$. If $g = x$ occurs, then $x$ is the GCD of a sub-sequence of the array $nums$.

The time complexity is $O(n + M \times \log M)$, and the space complexity is $O(M)$. Here, $n$ and $M$ are the length of the array $nums$ and the maximum value in the array $nums$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDifferentSubsequenceGCDs(self, nums: List[int]) -> int:
        mx = max(nums)
        vis = set(nums)
        ans = 0
        for x in range(1, mx + 1):
            g = 0
            for y in range(x, mx + 1, x):
                if y in vis:
                    g = gcd(g, y)
                    if g == x:
                        ans += 1
                        break
        return ans
```

#### Java

```java
class Solution {
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int mx = Arrays.stream(nums).max().getAsInt();
        boolean[] vis = new boolean[mx + 1];
        for (int x : nums) {
            vis[x] = true;
        }
        int ans = 0;
        for (int x = 1; x <= mx; ++x) {
            int g = 0;
            for (int y = x; y <= mx; y += x) {
                if (vis[y]) {
                    g = gcd(g, y);
                    if (x == g) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countDifferentSubsequenceGCDs(vector<int>& nums) {
        int mx = *max_element(nums.begin(), nums.end());
        vector<bool> vis(mx + 1);
        for (int& x : nums) {
            vis[x] = true;
        }
        int ans = 0;
        for (int x = 1; x <= mx; ++x) {
            int g = 0;
            for (int y = x; y <= mx; y += x) {
                if (vis[y]) {
                    g = gcd(g, y);
                    if (g == x) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countDifferentSubsequenceGCDs(nums []int) (ans int) {
	mx := slices.Max(nums)
	vis := make([]bool, mx+1)
	for _, x := range nums {
		vis[x] = true
	}
	for x := 1; x <= mx; x++ {
		g := 0
		for y := x; y <= mx; y += x {
			if vis[y] {
				g = gcd(g, y)
				if g == x {
					ans++
					break
				}
			}
		}
	}
	return
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
