---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0730.Count%20Different%20Palindromic%20Subsequences/README.md
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [730. 统计不同回文子序列](https://leetcode.cn/problems/count-different-palindromic-subsequences)

[English Version](/solution/0700-0799/0730.Count%20Different%20Palindromic%20Subsequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，返回 <code>s</code>&nbsp;中不同的非空回文子序列个数 。由于答案可能很大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p>字符串的子序列可以经由字符串删除 0 个或多个字符获得。</p>

<p>如果一个序列与它反转后的序列一致，那么它是回文序列。</p>

<p>如果存在某个 <code>i</code> , 满足&nbsp;<code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code><sub>&nbsp;</sub>，则两个序列&nbsp;<code>a<sub>1</sub>, a<sub>2</sub>, ...</code>&nbsp;和&nbsp;<code>b<sub>1</sub>, b<sub>2</sub>, ...</code>&nbsp;不同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = 'bccb'
<strong>输出：</strong>6
<strong>解释：</strong>6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
注意：'bcb' 虽然出现两次但仅计数一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
<strong>输出：</strong>104860361
<strong>解释：</strong>共有 3104860382 个不同的非空回文子序列，104860361 是对 10<sup>9</sup> + 7 取余后的值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code>&nbsp;仅包含&nbsp;<code>'a'</code>,&nbsp;<code>'b'</code>,&nbsp;<code>'c'</code>&nbsp;或&nbsp;<code>'d'</code>&nbsp;</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：区间 DP

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPalindromicSubsequences(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        dp = [[[0] * 4 for _ in range(n)] for _ in range(n)]
        for i, c in enumerate(s):
            dp[i][i][ord(c) - ord('a')] = 1
        for l in range(2, n + 1):
            for i in range(n - l + 1):
                j = i + l - 1
                for c in 'abcd':
                    k = ord(c) - ord('a')
                    if s[i] == s[j] == c:
                        dp[i][j][k] = 2 + sum(dp[i + 1][j - 1])
                    elif s[i] == c:
                        dp[i][j][k] = dp[i][j - 1][k]
                    elif s[j] == c:
                        dp[i][j][k] = dp[i + 1][j][k]
                    else:
                        dp[i][j][k] = dp[i + 1][j - 1][k]
        return sum(dp[0][-1]) % mod
```

#### Java

```java
class Solution {
    private final int MOD = (int) 1e9 + 7;

    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        long[][][] dp = new long[n][n][4];
        for (int i = 0; i < n; ++i) {
            dp[i][i][s.charAt(i) - 'a'] = 1;
        }
        for (int l = 2; l <= n; ++l) {
            for (int i = 0; i + l <= n; ++i) {
                int j = i + l - 1;
                for (char c = 'a'; c <= 'd'; ++c) {
                    int k = c - 'a';
                    if (s.charAt(i) == c && s.charAt(j) == c) {
                        dp[i][j][k] = 2 + dp[i + 1][j - 1][0] + dp[i + 1][j - 1][1]
                            + dp[i + 1][j - 1][2] + dp[i + 1][j - 1][3];
                        dp[i][j][k] %= MOD;
                    } else if (s.charAt(i) == c) {
                        dp[i][j][k] = dp[i][j - 1][k];
                    } else if (s.charAt(j) == c) {
                        dp[i][j][k] = dp[i + 1][j][k];
                    } else {
                        dp[i][j][k] = dp[i + 1][j - 1][k];
                    }
                }
            }
        }
        long ans = 0;
        for (int k = 0; k < 4; ++k) {
            ans += dp[0][n - 1][k];
        }
        return (int) (ans % MOD);
    }
}
```

#### C++

```cpp
using ll = long long;

class Solution {
public:
    int countPalindromicSubsequences(string s) {
        int mod = 1e9 + 7;
        int n = s.size();
        vector<vector<vector<ll>>> dp(n, vector<vector<ll>>(n, vector<ll>(4)));
        for (int i = 0; i < n; ++i) dp[i][i][s[i] - 'a'] = 1;
        for (int l = 2; l <= n; ++l) {
            for (int i = 0; i + l <= n; ++i) {
                int j = i + l - 1;
                for (char c = 'a'; c <= 'd'; ++c) {
                    int k = c - 'a';
                    if (s[i] == c && s[j] == c)
                        dp[i][j][k] = 2 + accumulate(dp[i + 1][j - 1].begin(), dp[i + 1][j - 1].end(), 0ll) % mod;
                    else if (s[i] == c)
                        dp[i][j][k] = dp[i][j - 1][k];
                    else if (s[j] == c)
                        dp[i][j][k] = dp[i + 1][j][k];
                    else
                        dp[i][j][k] = dp[i + 1][j - 1][k];
                }
            }
        }
        ll ans = accumulate(dp[0][n - 1].begin(), dp[0][n - 1].end(), 0ll);
        return (int) (ans % mod);
    }
};
```

#### Go

```go
func countPalindromicSubsequences(s string) int {
	mod := int(1e9) + 7
	n := len(s)
	dp := make([][][]int, n)
	for i := range dp {
		dp[i] = make([][]int, n)
		for j := range dp[i] {
			dp[i][j] = make([]int, 4)
		}
	}
	for i, c := range s {
		dp[i][i][c-'a'] = 1
	}
	for l := 2; l <= n; l++ {
		for i := 0; i+l <= n; i++ {
			j := i + l - 1
			for _, c := range [4]byte{'a', 'b', 'c', 'd'} {
				k := int(c - 'a')
				if s[i] == c && s[j] == c {
					dp[i][j][k] = 2 + (dp[i+1][j-1][0]+dp[i+1][j-1][1]+dp[i+1][j-1][2]+dp[i+1][j-1][3])%mod
				} else if s[i] == c {
					dp[i][j][k] = dp[i][j-1][k]
				} else if s[j] == c {
					dp[i][j][k] = dp[i+1][j][k]
				} else {
					dp[i][j][k] = dp[i+1][j-1][k]
				}
			}
		}
	}
	ans := 0
	for _, v := range dp[0][n-1] {
		ans += v
	}
	return ans % mod
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
