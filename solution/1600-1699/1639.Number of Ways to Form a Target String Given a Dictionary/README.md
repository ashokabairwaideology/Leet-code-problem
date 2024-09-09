---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1639.Number%20of%20Ways%20to%20Form%20a%20Target%20String%20Given%20a%20Dictionary/README.md
rating: 2081
source: 第 38 场双周赛 Q4
tags:
    - 数组
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [1639. 通过给定词典构造目标字符串的方案数](https://leetcode.cn/problems/number-of-ways-to-form-a-target-string-given-a-dictionary)

[English Version](/solution/1600-1699/1639.Number%20of%20Ways%20to%20Form%20a%20Target%20String%20Given%20a%20Dictionary/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串列表 <code>words</code> 和一个目标字符串 <code>target</code> 。<code>words</code> 中所有字符串都 <strong>长度相同</strong>  。</p>

<p>你的目标是使用给定的 <code>words</code> 字符串列表按照下述规则构造 <code>target</code> ：</p>

<ul>
	<li>从左到右依次构造 <code>target</code> 的每一个字符。</li>
	<li>为了得到 <code>target</code> 第 <code>i</code> 个字符（下标从 <strong>0</strong> 开始），当 <code>target[i] = words[j][k]</code> 时，你可以使用 <code>words</code> 列表中第 <code>j</code> 个字符串的第 <code>k</code> 个字符。</li>
	<li>一旦你使用了 <code>words</code> 中第 <code>j</code> 个字符串的第 <code>k</code> 个字符，你不能再使用 <code>words</code> 字符串列表中任意单词的第 <code>x</code> 个字符（<code>x <= k</code>）。也就是说，所有单词下标小于等于 <code>k</code> 的字符都不能再被使用。</li>
	<li>请你重复此过程直到得到目标字符串 <code>target</code> 。</li>
</ul>

<p><strong>请注意</strong>， 在构造目标字符串的过程中，你可以按照上述规定使用 <code>words</code> 列表中 <strong>同一个字符串</strong> 的 <strong>多个字符</strong> 。</p>

<p>请你返回使用 <code>words</code> 构造 <code>target</code> 的方案数。由于答案可能会很大，请对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后返回。</p>

<p>（译者注：此题目求的是有多少个不同的 <code>k</code> 序列，详情请见示例。）</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>words = ["acca","bbbb","caca"], target = "aba"
<b>输出：</b>6
<b>解释：</b>总共有 6 种方法构造目标串。
"aba" -> 下标为 0 ("<strong>a</strong>cca")，下标为 1 ("b<strong>b</strong>bb")，下标为 3 ("cac<strong>a</strong>")
"aba" -> 下标为 0 ("<strong>a</strong>cca")，下标为 2 ("bb<strong>b</strong>b")，下标为 3 ("cac<strong>a</strong>")
"aba" -> 下标为 0 ("<strong>a</strong>cca")，下标为 1 ("b<strong>b</strong>bb")，下标为 3 ("acc<strong>a</strong>")
"aba" -> 下标为 0 ("<strong>a</strong>cca")，下标为 2 ("bb<strong>b</strong>b")，下标为 3 ("acc<strong>a</strong>")
"aba" -> 下标为 1 ("c<strong>a</strong>ca")，下标为 2 ("bb<strong>b</strong>b")，下标为 3 ("acc<strong>a</strong>")
"aba" -> 下标为 1 ("c<strong>a</strong>ca")，下标为 2 ("bb<strong>b</strong>b")，下标为 3 ("cac<strong>a</strong>")
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>words = ["abba","baab"], target = "bab"
<b>输出：</b>4
<b>解释：</b>总共有 4 种不同形成 target 的方法。
"bab" -> 下标为 0 ("<strong>b</strong>aab")，下标为 1 ("b<strong>a</strong>ab")，下标为 2 ("ab<strong>b</strong>a")
"bab" -> 下标为 0 ("<strong>b</strong>aab")，下标为 1 ("b<strong>a</strong>ab")，下标为 3 ("baa<strong>b</strong>")
"bab" -> 下标为 0 ("<strong>b</strong>aab")，下标为 2 ("ba<strong>a</strong>b")，下标为 3 ("baa<strong>b</strong>")
"bab" -> 下标为 1 ("a<strong>b</strong>ba")，下标为 2 ("ba<strong>a</strong>b")，下标为 3 ("baa<strong>b</strong>")
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>words = ["abcd"], target = "abcd"
<b>输出：</b>1
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>words = ["abab","baba","abba","baab"], target = "abba"
<b>输出：</b>16
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 1000</code></li>
	<li><code>1 <= words[i].length <= 1000</code></li>
	<li><code>words</code> 中所有单词长度相同。</li>
	<li><code>1 <= target.length <= 1000</code></li>
	<li><code>words[i]</code> 和 <code>target</code> 都仅包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 记忆化搜索

我们注意到，字符串数组 $words$ 中的每一个字符串长度都相同，不妨记为 $n$，那么我们可以预处理出一个二维数组 $cnt$，其中 $cnt[j][c]$ 表示字符串数组 $words$ 中第 $j$ 个位置的字符 $c$ 的数量。

接下来，我们设计一个函数 $dfs(i, j)$，表示构造 $target[i,..]$ 且当前从 $words$ 中选取的字符位置为 $j$ 的方案数。那么答案就是 $dfs(0, 0)$。

函数 $dfs(i, j)$ 的计算逻辑如下：

-   如果 $i \geq m$，说明 $target$ 中的所有字符都已经被选取，那么方案数为 $1$。
-   如果 $j \geq n$，说明 $words$ 中的所有字符都已经被选取，那么方案数为 $0$。
-   否则，我们可以不选择 $words$ 中的第 $j$ 个位置的字符，那么方案数为 $dfs(i, j + 1)$；或者我们选择 $words$ 中的第 $j$ 个位置的字符，那么方案数为 $dfs(i + 1, j + 1) \times cnt[j][target[i] - 'a']$。

最后，我们返回 $dfs(0, 0)$ 即可。注意答案的取模操作。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 为字符串 $target$ 的长度，而 $n$ 为字符串数组 $words$ 中每个字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numWays(self, words: List[str], target: str) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= m:
                return 1
            if j >= n:
                return 0
            ans = dfs(i + 1, j + 1) * cnt[j][ord(target[i]) - ord('a')]
            ans = (ans + dfs(i, j + 1)) % mod
            return ans

        m, n = len(target), len(words[0])
        cnt = [[0] * 26 for _ in range(n)]
        for w in words:
            for j, c in enumerate(w):
                cnt[j][ord(c) - ord('a')] += 1
        mod = 10**9 + 7
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private int m;
    private int n;
    private String target;
    private Integer[][] f;
    private int[][] cnt;
    private final int mod = (int) 1e9 + 7;

    public int numWays(String[] words, String target) {
        m = target.length();
        n = words[0].length();
        f = new Integer[m][n];
        this.target = target;
        cnt = new int[n][26];
        for (var w : words) {
            for (int j = 0; j < n; ++j) {
                cnt[j][w.charAt(j) - 'a']++;
            }
        }
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m) {
            return 1;
        }
        if (j >= n) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        long ans = dfs(i, j + 1);
        ans += 1L * dfs(i + 1, j + 1) * cnt[j][target.charAt(i) - 'a'];
        ans %= mod;
        return f[i][j] = (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numWays(vector<string>& words, string target) {
        const int mod = 1e9 + 7;
        int m = target.size(), n = words[0].size();
        vector<vector<int>> cnt(n, vector<int>(26));
        for (auto& w : words) {
            for (int j = 0; j < n; ++j) {
                ++cnt[j][w[j] - 'a'];
            }
        }
        int f[m][n];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= m) {
                return 1;
            }
            if (j >= n) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = dfs(i, j + 1);
            ans = (ans + 1LL * dfs(i + 1, j + 1) * cnt[j][target[i] - 'a']) % mod;
            return f[i][j] = ans;
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func numWays(words []string, target string) int {
	m, n := len(target), len(words[0])
	f := make([][]int, m)
	cnt := make([][26]int, n)
	for _, w := range words {
		for j, c := range w {
			cnt[j][c-'a']++
		}
	}
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	const mod = 1e9 + 7
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m {
			return 1
		}
		if j >= n {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		ans := dfs(i, j+1)
		ans = (ans + dfs(i+1, j+1)*cnt[j][target[i]-'a']) % mod
		f[i][j] = ans
		return ans
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function numWays(words: string[], target: string): number {
    const m = target.length;
    const n = words[0].length;
    const f = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    const mod = 1e9 + 7;
    for (let j = 0; j <= n; ++j) {
        f[0][j] = 1;
    }
    const cnt = new Array(n).fill(0).map(() => new Array(26).fill(0));
    for (const w of words) {
        for (let j = 0; j < n; ++j) {
            ++cnt[j][w.charCodeAt(j) - 97];
        }
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = f[i][j - 1] + f[i - 1][j - 1] * cnt[j - 1][target.charCodeAt(i - 1) - 97];
            f[i][j] %= mod;
        }
    }
    return f[m][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：预处理 + 动态规划

与方法一类似，我们可以先预处理出一个二维数组 $cnt$，其中 $cnt[j][c]$ 表示字符串数组 $words$ 中第 $j$ 个位置的字符 $c$ 的数量。

接下来，我们定义 $f[i][j]$ 表示构造 $target$ 的前 $i$ 个字符，且当前是从 $words$ 中每个单词的前 $j$ 个字符中选取字符的方案数。那么答案就是 $f[m][n]$。初始时 $f[0][j] = 1$，其中 $0 \leq j \leq n$。

考虑 $f[i][j]$，其中 $i \gt 0$, $j \gt 0$。我们可以不选取 $words$ 中的第 $j$ 个位置的字符，那么方案数为 $f[i][j - 1]$；或者我们选择 $words$ 中的第 $j$ 个位置的字符，那么方案数为 $f[i - 1][j - 1] \times cnt[j - 1][target[i - 1] - 'a']$。最后，我们将这两种情况的方案数相加，即为 $f[i][j]$ 的值。

最后，我们返回 $f[m][n]$ 即可。注意答案的取模操作。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 为字符串 $target$ 的长度，而 $n$ 为字符串数组 $words$ 中每个字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numWays(self, words: List[str], target: str) -> int:
        m, n = len(target), len(words[0])
        cnt = [[0] * 26 for _ in range(n)]
        for w in words:
            for j, c in enumerate(w):
                cnt[j][ord(c) - ord('a')] += 1
        mod = 10**9 + 7
        f = [[0] * (n + 1) for _ in range(m + 1)]
        f[0] = [1] * (n + 1)
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                f[i][j] = (
                    f[i][j - 1]
                    + f[i - 1][j - 1] * cnt[j - 1][ord(target[i - 1]) - ord('a')]
                )
                f[i][j] %= mod
        return f[m][n]
```

#### Java

```java
class Solution {
    public int numWays(String[] words, String target) {
        int m = target.length();
        int n = words[0].length();
        final int mod = (int) 1e9 + 7;
        long[][] f = new long[m + 1][n + 1];
        Arrays.fill(f[0], 1);
        int[][] cnt = new int[n][26];
        for (var w : words) {
            for (int j = 0; j < n; ++j) {
                cnt[j][w.charAt(j) - 'a']++;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i][j - 1] + f[i - 1][j - 1] * cnt[j - 1][target.charAt(i - 1) - 'a'];
                f[i][j] %= mod;
            }
        }
        return (int) f[m][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numWays(vector<string>& words, string target) {
        int m = target.size(), n = words[0].size();
        const int mod = 1e9 + 7;
        long long f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        fill(f[0], f[0] + n + 1, 1);
        vector<vector<int>> cnt(n, vector<int>(26));
        for (auto& w : words) {
            for (int j = 0; j < n; ++j) {
                ++cnt[j][w[j] - 'a'];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i][j - 1] + f[i - 1][j - 1] * cnt[j - 1][target[i - 1] - 'a'];
                f[i][j] %= mod;
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func numWays(words []string, target string) int {
	const mod = 1e9 + 7
	m, n := len(target), len(words[0])
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for j := range f[0] {
		f[0][j] = 1
	}
	cnt := make([][26]int, n)
	for _, w := range words {
		for j, c := range w {
			cnt[j][c-'a']++
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = f[i][j-1] + f[i-1][j-1]*cnt[j-1][target[i-1]-'a']
			f[i][j] %= mod
		}
	}
	return f[m][n]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
