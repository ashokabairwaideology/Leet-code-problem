---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1358.Number%20of%20Substrings%20Containing%20All%20Three%20Characters/README_EN.md
rating: 1646
source: Biweekly Contest 20 Q3
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [1358. Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters)

[中文文档](/solution/1300-1399/1358.Number%20of%20Substrings%20Containing%20All%20Three%20Characters/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>&nbsp;consisting only of characters <em>a</em>, <em>b</em> and <em>c</em>.</p>

<p>Return the number of substrings containing <b>at least</b>&nbsp;one occurrence of all these characters <em>a</em>, <em>b</em> and <em>c</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabc&quot;
<strong>Output:</strong> 10
<strong>Explanation:</strong> The substrings containing&nbsp;at least&nbsp;one occurrence of the characters&nbsp;<em>a</em>,&nbsp;<em>b</em>&nbsp;and&nbsp;<em>c are &quot;</em>abc<em>&quot;, &quot;</em>abca<em>&quot;, &quot;</em>abcab<em>&quot;, &quot;</em>abcabc<em>&quot;, &quot;</em>bca<em>&quot;, &quot;</em>bcab<em>&quot;, &quot;</em>bcabc<em>&quot;, &quot;</em>cab<em>&quot;, &quot;</em>cabc<em>&quot; </em>and<em> &quot;</em>abc<em>&quot; </em>(<strong>again</strong>)<em>. </em>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaacb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The substrings containing&nbsp;at least&nbsp;one occurrence of the characters&nbsp;<em>a</em>,&nbsp;<em>b</em>&nbsp;and&nbsp;<em>c are &quot;</em>aaacb<em>&quot;, &quot;</em>aacb<em>&quot; </em>and<em> &quot;</em>acb<em>&quot;.</em><em> </em>
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 5 x 10^4</code></li>
	<li><code>s</code>&nbsp;only consists of&nbsp;<em>a</em>, <em>b</em> or <em>c&nbsp;</em>characters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We use an array $d$ of length $3$ to record the most recent occurrence of the three characters, initially all set to $-1$.

We traverse the string $s$. For the current position $i$, we first update $d[s[i]]=i$, then the number of valid strings is $\min(d[0], d[1], d[2]) + 1$, which is accumulated to the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        d = {"a": -1, "b": -1, "c": -1}
        ans = 0
        for i, c in enumerate(s):
            d[c] = i
            ans += min(d["a"], d["b"], d["c"]) + 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] d = new int[] {-1, -1, -1};
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            d[c - 'a'] = i;
            ans += Math.min(d[0], Math.min(d[1], d[2])) + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSubstrings(string s) {
        int d[3] = {-1, -1, -1};
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            d[s[i] - 'a'] = i;
            ans += min(d[0], min(d[1], d[2])) + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubstrings(s string) (ans int) {
	d := [3]int{-1, -1, -1}
	for i, c := range s {
		d[c-'a'] = i
		ans += min(d[0], min(d[1], d[2])) + 1
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
