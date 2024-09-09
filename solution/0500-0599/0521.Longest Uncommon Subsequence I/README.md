---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0521.Longest%20Uncommon%20Subsequence%20I/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [521. 最长特殊序列 Ⅰ](https://leetcode.cn/problems/longest-uncommon-subsequence-i)

[English Version](/solution/0500-0599/0521.Longest%20Uncommon%20Subsequence%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串&nbsp;<code>a</code>&nbsp;和&nbsp;<code>b</code>，请返回 <em>这两个字符串中 <strong>最长的特殊序列</strong>&nbsp;</em> 的长度。如果不存在，则返回 <code>-1</code>&nbsp;。</p>

<p><strong>「最长特殊序列」</strong>&nbsp;定义如下：该序列为&nbsp;<strong>某字符串独有的最长<span data-keyword="subsequence-array">子序列</span>（即不能是其他字符串的子序列）</strong>&nbsp;。</p>

<p>字符串&nbsp;<code>s</code>&nbsp;的子序列是在从&nbsp;<code>s</code>&nbsp;中删除任意数量的字符后可以获得的字符串。</p>

<ul>
	<li>例如，<code>"abc"</code> 是 <code>"aebdc"</code> 的子序列，因为删除 <code>"a<em><strong>e</strong></em>b<strong><em>d</em></strong>c"</code> 中斜体加粗的字符可以得到 <code>"abc"</code> 。 <code>"aebdc"</code> 的子序列还包括 <code>"aebdc"</code> 、 <code>"aeb"</code> 和 <code>""</code> (空字符串)。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> a = "aba", b = "cdc"
<strong>输出:</strong> 3
<strong>解释:</strong> 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = "aaa", b = "bbb"
<strong>输出：</strong>3
<strong>解释:</strong> 最长特殊序列是 "aaa" 和 "bbb" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = "aaa", b = "aaa"
<strong>输出：</strong>-1
<strong>解释:</strong> 字符串 a 的每个子序列也是字符串 b 的每个子序列。同样，字符串 b 的每个子序列也是字符串 a 的子序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 100</code></li>
	<li><code>a</code>&nbsp;和&nbsp;<code>b</code>&nbsp;由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

如果字符串 `a` 和 `b` 相等，那么它们没有特殊序列，返回 `-1`；否则，返回长度较长的字符串的长度。

时间复杂度 $O(n)$，其中 $n$ 为字符串 `a` 和 `b` 中较长的字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLUSlength(self, a: str, b: str) -> int:
        return -1 if a == b else max(len(a), len(b))
```

#### Java

```java
class Solution {
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLUSlength(string a, string b) {
        return a == b ? -1 : max(a.size(), b.size());
    }
};
```

#### Go

```go
func findLUSlength(a string, b string) int {
	if a == b {
		return -1
	}
	if len(a) > len(b) {
		return len(a)
	}
	return len(b)
}
```

#### TypeScript

```ts
function findLUSlength(a: string, b: string): number {
    return a === b ? -1 : Math.max(a.length, b.length);
}
```

#### Rust

```rust
impl Solution {
    pub fn find_lu_slength(a: String, b: String) -> i32 {
        if a == b {
            return -1;
        }
        a.len().max(b.len()) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
