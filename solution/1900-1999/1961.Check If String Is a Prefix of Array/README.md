---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1961.Check%20If%20String%20Is%20a%20Prefix%20of%20Array/README.md
rating: 1234
source: 第 253 场周赛 Q1
tags:
    - 数组
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [1961. 检查字符串是否为数组前缀](https://leetcode.cn/problems/check-if-string-is-a-prefix-of-array)

[English Version](/solution/1900-1999/1961.Check%20If%20String%20Is%20a%20Prefix%20of%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个字符串数组 <code>words</code> ，请你判断 <code>s</code> 是否为 <code>words</code> 的 <strong>前缀字符串</strong> 。</p>

<p>字符串 <code>s</code> 要成为 <code>words</code> 的 <strong>前缀字符串</strong> ，需要满足：<code>s</code> 可以由 <code>words</code> 中的前 <code>k</code>（<code>k</code> 为 <strong>正数</strong> ）个字符串按顺序相连得到，且 <code>k</code> 不超过 <code>words.length</code> 。</p>

<p>如果 <code>s</code> 是 <code>words</code> 的 <strong>前缀字符串</strong> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "iloveleetcode", words = ["i","love","leetcode","apples"]
<strong>输出：</strong>true
<strong>解释：</strong>
s 可以由 "i"、"love" 和 "leetcode" 相连得到。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "iloveleetcode", words = ["apples","i","love","leetcode"]
<strong>输出：</strong>false
<strong>解释：</strong>
数组的前缀相连无法得到 s 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>words[i]</code> 和 <code>s</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们遍历数组 $words$，用一个变量 $t$ 记录当前已经拼接的字符串，如果 $t$ 的长度大于 $s$ 的长度，说明 $s$ 不是 $words$ 的前缀字符串，返回 $false$；如果 $t$ 的长度等于 $s$ 的长度，返回 $t$ 是否等于 $s$。

遍历结束后，如果 $t$ 的长度小于 $s$ 的长度，说明 $s$ 不是 $words$ 的前缀字符串，返回 $false$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPrefixString(self, s: str, words: List[str]) -> bool:
        n, m = len(s), 0
        for i, w in enumerate(words):
            m += len(w)
            if m == n:
                return "".join(words[: i + 1]) == s
        return False
```

#### Java

```java
class Solution {
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder t = new StringBuilder();
        for (var w : words) {
            t.append(w);
            if (t.length() > s.length()) {
                return false;
            }
            if (t.length() == s.length()) {
                return s.equals(t.toString());
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPrefixString(string s, vector<string>& words) {
        string t;
        for (auto& w : words) {
            t += w;
            if (t.size() > s.size()) {
                return false;
            }
            if (t.size() == s.size()) {
                return t == s;
            }
        }
        return false;
    }
};
```

#### Go

```go
func isPrefixString(s string, words []string) bool {
	t := strings.Builder{}
	for _, w := range words {
		t.WriteString(w)
		if t.Len() > len(s) {
			return false
		}
		if t.Len() == len(s) {
			return t.String() == s
		}
	}
	return false
}
```

#### TypeScript

```ts
function isPrefixString(s: string, words: string[]): boolean {
    const t: string[] = [];
    const n = s.length;
    let m = 0;
    for (const w of words) {
        m += w.length;
        if (m > n) {
            return false;
        }
        t.push(w);
        if (m === n) {
            return s === t.join('');
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
