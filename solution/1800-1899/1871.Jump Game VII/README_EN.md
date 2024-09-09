---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1871.Jump%20Game%20VII/README_EN.md
rating: 1896
source: Weekly Contest 242 Q3
tags:
    - String
    - Dynamic Programming
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [1871. Jump Game VII](https://leetcode.com/problems/jump-game-vii)

[中文文档](/solution/1800-1899/1871.Jump%20Game%20VII/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> binary string <code>s</code> and two integers <code>minJump</code> and <code>maxJump</code>. In the beginning, you are standing at index <code>0</code>, which is equal to <code>&#39;0&#39;</code>. You can move from index <code>i</code> to index <code>j</code> if the following conditions are fulfilled:</p>

<ul>
	<li><code>i + minJump &lt;= j &lt;= min(i + maxJump, s.length - 1)</code>, and</li>
	<li><code>s[j] == &#39;0&#39;</code>.</li>
</ul>

<p>Return <code>true</code><i> if you can reach index </i><code>s.length - 1</code><i> in </i><code>s</code><em>, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;<u>0</u>11<u>0</u>1<u>0</u>&quot;, minJump = 2, maxJump = 3
<strong>Output:</strong> true
<strong>Explanation:</strong>
In the first step, move from index 0 to index 3. 
In the second step, move from index 3 to index 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;01101110&quot;, minJump = 2, maxJump = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>s[0] == &#39;0&#39;</code></li>
	<li><code>1 &lt;= minJump &lt;= maxJump &lt; s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Dynamic Programming

We define a prefix sum array $pre$ of length $n+1$, where $pre[i]$ represents the number of reachable positions in the first $i$ positions of $s$. We define a boolean array $f$ of length $n$, where $f[i]$ indicates whether $s[i]$ is reachable. Initially, $pre[1] = 1$ and $f[0] = true$.

Consider $i \in [1, n)$, if $s[i] = 0$, then we need to determine whether there exists a position $j$ in the first $i$ positions of $s$, such that $j$ is reachable and the distance from $j$ to $i$ is within $[minJump, maxJump]$. If such a position $j$ exists, then we have $f[i] = true$, otherwise $f[i] = false$. When determining whether $j$ exists, we can use the prefix sum array $pre$ to determine whether such a position $j$ exists in $O(1)$ time.

The final answer is $f[n-1]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        n = len(s)
        pre = [0] * (n + 1)
        pre[1] = 1
        f = [True] + [False] * (n - 1)
        for i in range(1, n):
            if s[i] == "0":
                l, r = max(0, i - maxJump), i - minJump
                f[i] = l <= r and pre[r + 1] - pre[l] > 0
            pre[i + 1] = pre[i] + f[i]
        return f[-1]
```

#### Java

```java
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] pre = new int[n + 1];
        pre[1] = 1;
        boolean[] f = new boolean[n];
        f[0] = true;
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == '0') {
                int l = Math.max(0, i - maxJump);
                int r = i - minJump;
                f[i] = l <= r && pre[r + 1] - pre[l] > 0;
            }
            pre[i + 1] = pre[i] + (f[i] ? 1 : 0);
        }
        return f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canReach(string s, int minJump, int maxJump) {
        int n = s.size();
        int pre[n + 1];
        memset(pre, 0, sizeof(pre));
        pre[1] = 1;
        bool f[n];
        memset(f, 0, sizeof(f));
        f[0] = true;
        for (int i = 1; i < n; ++i) {
            if (s[i] == '0') {
                int l = max(0, i - maxJump);
                int r = i - minJump;
                f[i] = l <= r && pre[r + 1] - pre[l];
            }
            pre[i + 1] = pre[i] + f[i];
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func canReach(s string, minJump int, maxJump int) bool {
	n := len(s)
	pre := make([]int, n+1)
	pre[1] = 1
	f := make([]bool, n)
	f[0] = true
	for i := 1; i < n; i++ {
		if s[i] == '0' {
			l, r := max(0, i-maxJump), i-minJump
			f[i] = l <= r && pre[r+1]-pre[l] > 0
		}
		pre[i+1] = pre[i]
		if f[i] {
			pre[i+1]++
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function canReach(s: string, minJump: number, maxJump: number): boolean {
    const n = s.length;
    const pre: number[] = Array(n + 1).fill(0);
    pre[1] = 1;
    const f: boolean[] = Array(n).fill(false);
    f[0] = true;
    for (let i = 1; i < n; ++i) {
        if (s[i] === '0') {
            const [l, r] = [Math.max(0, i - maxJump), i - minJump];
            f[i] = l <= r && pre[r + 1] - pre[l] > 0;
        }
        pre[i + 1] = pre[i] + (f[i] ? 1 : 0);
    }
    return f[n - 1];
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {number} minJump
 * @param {number} maxJump
 * @return {boolean}
 */
var canReach = function (s, minJump, maxJump) {
    const n = s.length;
    const pre = Array(n + 1).fill(0);
    pre[1] = 1;
    const f = Array(n).fill(false);
    f[0] = true;
    for (let i = 1; i < n; ++i) {
        if (s[i] === '0') {
            const [l, r] = [Math.max(0, i - maxJump), i - minJump];
            f[i] = l <= r && pre[r + 1] - pre[l] > 0;
        }
        pre[i + 1] = pre[i] + (f[i] ? 1 : 0);
    }
    return f[n - 1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
