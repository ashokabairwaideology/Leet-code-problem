---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0091.Decode%20Ways/README.md
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [91. 解码方法](https://leetcode.cn/problems/decode-ways)

[English Version](/solution/0000-0099/0091.Decode%20Ways/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一条包含字母&nbsp;<code>A-Z</code> 的消息通过以下映射进行了 <strong>编码</strong> ：</p>

<p><code>"1" -&gt; 'A'<br />
"2" -&gt; 'B'<br />
...<br />
"25" -&gt; 'Y'<br />
"26" -&gt; 'Z'</code></p>

<p>然而，在&nbsp;<strong>解码</strong> 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（<code>"2"</code> 和 <code>"5"</code> 与 <code>"25"</code>）。</p>

<p>例如，<code>"11106"</code> 可以映射为：</p>

<ul>
	<li><code>"AAJF"</code> ，将消息分组为 <code>(1, 1, 10, 6)</code></li>
	<li><code>"KJF"</code> ，将消息分组为 <code>(11, 10, 6)</code></li>
	<li>消息不能分组为&nbsp; <code>(1, 11, 06)</code> ，因为 <code>"06"</code>&nbsp;不是一个合法编码（只有 "6" 是合法的）。</li>
</ul>

<p>注意，可能存在无法解码的字符串。</p>

<p>给你一个只含数字的 <strong>非空 </strong>字符串 <code>s</code> ，请计算并返回 <strong>解码</strong> 方法的 <strong>总数</strong> 。如果没有合法的方式解码整个字符串，返回 <code>0</code>。</p>

<p>题目数据保证答案肯定是一个 <strong>32 位</strong> 的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "12"
<strong>输出：</strong>2
<strong>解释：</strong>它可以解码为 "AB"（1 2）或者 "L"（12）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "226"
<strong>输出：</strong>3
<strong>解释：</strong>它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "06"
<strong>输出：</strong>0
<strong>解释：</strong>"06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 只包含数字，并且可能包含前导零。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i]$ 表示字符串的前 $i$ 个字符的解码方法数，初始时 $f[0]=1$，其余 $f[i]=0$。

考虑 $f[i]$ 如何进行状态转移。

-   如果第 $i$ 个字符（即 $s[i-1]$）单独形成编码，那么它对应一种解码方式，即 $f[i]=f[i-1]$。前提是 $s[i-1] \neq 0$。
-   如果第 $i-1$ 个字符和第 $i$ 个字符组成的字符串在 $[1,26]$ 范围内，那么它们可以作为一个整体，对应一种解码方式，即 $f[i] = f[i] + f[i-2]$。前提是 $s[i-2] \neq 0$，且 $s[i-2]s[i-1]$ 在 $[1,26]$ 范围内。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        n = len(s)
        f = [1] + [0] * n
        for i, c in enumerate(s, 1):
            if c != "0":
                f[i] = f[i - 1]
            if i > 1 and s[i - 2] != "0" and int(s[i - 2 : i]) <= 26:
                f[i] += f[i - 2]
        return f[n]
```

#### Java

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numDecodings(string s) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && (s[i - 2] == '1' || s[i - 2] == '2' && s[i - 1] <= '6')) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func numDecodings(s string) int {
	n := len(s)
	f := make([]int, n+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		if s[i-1] != '0' {
			f[i] = f[i-1]
		}
		if i > 1 && (s[i-2] == '1' || (s[i-2] == '2' && s[i-1] <= '6')) {
			f[i] += f[i-2]
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function numDecodings(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        if (s[i - 1] !== '0') {
            f[i] = f[i - 1];
        }
        if (i > 1 && (s[i - 2] === '1' || (s[i - 2] === '2' && s[i - 1] <= '6'))) {
            f[i] += f[i - 2];
        }
    }
    return f[n];
}
```

#### C#

```cs
public class Solution {
    public int NumDecodings(string s) {
        int n = s.Length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
```

<!-- tabs:end -->

我们注意到，状态 $f[i]$ 仅与状态 $f[i-1]$ 和状态 $f[i-2]$ 有关，而与其他状态无关，因此我们可以使用两个变量代替这两个状态，使得原来的空间复杂度 $O(n)$ 降低至 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        f, g = 0, 1
        for i, c in enumerate(s, 1):
            h = g if c != "0" else 0
            if i > 1 and s[i - 2] != "0" and int(s[i - 2 : i]) <= 26:
                h += f
            f, g = g, h
        return g
```

#### Java

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s.charAt(i - 1) != '0' ? g : 0;
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numDecodings(string s) {
        int n = s.size();
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s[i - 1] != '0' ? g : 0;
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
};
```

#### Go

```go
func numDecodings(s string) int {
	n := len(s)
	f, g := 0, 1
	for i := 1; i <= n; i++ {
		h := 0
		if s[i-1] != '0' {
			h = g
		}
		if i > 1 && (s[i-2] == '1' || (s[i-2] == '2' && s[i-1] <= '6')) {
			h += f
		}
		f, g = g, h
	}
	return g
}
```

#### TypeScript

```ts
function numDecodings(s: string): number {
    const n = s.length;
    let [f, g] = [0, 1];
    for (let i = 1; i <= n; ++i) {
        let h = s[i - 1] !== '0' ? g : 0;
        if (i > 1 && (s[i - 2] === '1' || (s[i - 2] === '2' && s[i - 1] <= '6'))) {
            h += f;
        }
        [f, g] = [g, h];
    }
    return g;
}
```

#### C#

```cs
public class Solution {
    public int NumDecodings(string s) {
        int n = s.Length;
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s[i - 1] != '0' ? g : 0;
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
