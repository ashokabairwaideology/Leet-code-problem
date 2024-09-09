---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0646.Maximum%20Length%20of%20Pair%20Chain/README_EN.md
tags:
    - Greedy
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [646. Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain)

[中文文档](/solution/0600-0699/0646.Maximum%20Length%20of%20Pair%20Chain/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <code>n</code> pairs <code>pairs</code> where <code>pairs[i] = [left<sub>i</sub>, right<sub>i</sub>]</code> and <code>left<sub>i</sub> &lt; right<sub>i</sub></code>.</p>

<p>A pair <code>p2 = [c, d]</code> <strong>follows</strong> a pair <code>p1 = [a, b]</code> if <code>b &lt; c</code>. A <strong>chain</strong> of pairs can be formed in this fashion.</p>

<p>Return <em>the length longest chain which can be formed</em>.</p>

<p>You do not need to use up all the given intervals. You can select pairs in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> pairs = [[1,2],[2,3],[3,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest chain is [1,2] -&gt; [3,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> pairs = [[1,2],[7,8],[4,5]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest chain is [1,2] -&gt; [4,5] -&gt; [7,8].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == pairs.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>-1000 &lt;= left<sub>i</sub> &lt; right<sub>i</sub> &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        pairs.sort()
        dp = [1] * len(pairs)
        for i, (c, _) in enumerate(pairs):
            for j, (_, b) in enumerate(pairs[:i]):
                if b < c:
                    dp[i] = max(dp[i], dp[j] + 1)
        return max(dp)
```

#### Java

```java
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            int c = pairs[i][0];
            for (int j = 0; j < i; ++j) {
                int b = pairs[j][1];
                if (b < c) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLongestChain(vector<vector<int>>& pairs) {
        sort(pairs.begin(), pairs.end());
        int n = pairs.size();
        vector<int> dp(n, 1);
        for (int i = 0; i < n; ++i) {
            int c = pairs[i][0];
            for (int j = 0; j < i; ++j) {
                int b = pairs[j][1];
                if (b < c) dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        return *max_element(dp.begin(), dp.end());
    }
};
```

#### Go

```go
func findLongestChain(pairs [][]int) int {
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][0] < pairs[j][0]
	})
	n := len(pairs)
	dp := make([]int, n)
	ans := 0
	for i := range pairs {
		dp[i] = 1
		c := pairs[i][0]
		for j := range pairs[:i] {
			b := pairs[j][1]
			if b < c {
				dp[i] = max(dp[i], dp[j]+1)
			}
		}
		ans = max(ans, dp[i])
	}
	return ans
}
```

#### TypeScript

```ts
function findLongestChain(pairs: number[][]): number {
    pairs.sort((a, b) => a[0] - b[0]);
    const n = pairs.length;
    const dp = new Array(n).fill(1);
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < i; j++) {
            if (pairs[i][0] > pairs[j][1]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    return dp[n - 1];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_longest_chain(mut pairs: Vec<Vec<i32>>) -> i32 {
        pairs.sort_by(|a, b| a[0].cmp(&b[0]));
        let n = pairs.len();
        let mut dp = vec![1; n];
        for i in 0..n {
            for j in 0..i {
                if pairs[i][0] > pairs[j][1] {
                    dp[i] = dp[i].max(dp[j] + 1);
                }
            }
        }
        dp[n - 1]
    }
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
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        ans, cur = 0, -inf
        for a, b in sorted(pairs, key=lambda x: x[1]):
            if cur < a:
                cur = b
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int[] p : pairs) {
            if (cur < p[0]) {
                cur = p[1];
                ++ans;
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
    int findLongestChain(vector<vector<int>>& pairs) {
        sort(pairs.begin(), pairs.end(), [](vector<int>& a, vector<int> b) {
            return a[1] < b[1];
        });
        int ans = 0, cur = INT_MIN;
        for (auto& p : pairs) {
            if (cur < p[0]) {
                cur = p[1];
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLongestChain(pairs [][]int) int {
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][1] < pairs[j][1]
	})
	ans, cur := 0, math.MinInt32
	for _, p := range pairs {
		if cur < p[0] {
			cur = p[1]
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findLongestChain(pairs: number[][]): number {
    pairs.sort((a, b) => a[1] - b[1]);
    let res = 0;
    let pre = -Infinity;
    for (const [a, b] of pairs) {
        if (pre < a) {
            pre = b;
            res++;
        }
    }
    return res;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_longest_chain(mut pairs: Vec<Vec<i32>>) -> i32 {
        pairs.sort_by(|a, b| a[1].cmp(&b[1]));
        let mut res = 0;
        let mut pre = i32::MIN;
        for pair in pairs.iter() {
            let a = pair[0];
            let b = pair[1];
            if pre < a {
                pre = b;
                res += 1;
            }
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
