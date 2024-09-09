---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1844.Replace%20All%20Digits%20with%20Characters/README.md
rating: 1300
source: 第 51 场双周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [1844. 将所有数字用字符替换](https://leetcode.cn/problems/replace-all-digits-with-characters)

[English Version](/solution/1800-1899/1844.Replace%20All%20Digits%20with%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>s</code> ，它的 <strong>偶数</strong> 下标处为小写英文字母，<strong>奇数</strong> 下标处为数字。</p>

<p>定义一个函数 <code>shift(c, x)</code> ，其中 <code>c</code> 是一个字符且 <code>x</code> 是一个数字，函数返回字母表中 <code>c</code> 后面第 <code>x</code> 个字符。</p>

<ul>
	<li>比方说，<code>shift('a', 5) = 'f'</code> 和 <code>shift('x', 0) = 'x'</code> 。</li>
</ul>

<p>对于每个 <strong>奇数</strong> 下标 <code>i</code> ，你需要将数字 <code>s[i]</code> 用 <code>shift(s[i-1], s[i])</code> 替换。</p>

<p>请你替换所有数字以后，将字符串 <code>s</code> 返回。题目 <strong>保证</strong><em> </em><code>shift(s[i-1], s[i])</code> 不会超过 <code>'z'</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "a1c1e1"
<b>输出：</b>"abcdef"
<strong>解释：</strong>数字被替换结果如下：
- s[1] -&gt; shift('a',1) = 'b'
- s[3] -&gt; shift('c',1) = 'd'
- s[5] -&gt; shift('e',1) = 'f'</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "a1b2c3d4e"
<b>输出：</b>"abbdcfdhe"
<strong>解释：</strong>数字被替换结果如下：
- s[1] -&gt; shift('a',1) = 'b'
- s[3] -&gt; shift('b',2) = 'd'
- s[5] -&gt; shift('c',3) = 'f'
- s[7] -&gt; shift('d',4) = 'h'</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 只包含小写英文字母和数字。</li>
	<li>对所有 <strong>奇数</strong> 下标处的 <code>i</code> ，满足 <code>shift(s[i-1], s[i]) &lt;= 'z'</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

遍历字符串，对于奇数下标的字符，将其替换为前一个字符后移对应位数的字符。

最后返回替换后的字符串。

时间复杂度 $(n)$，其中 $n$ 为字符串 $s$ 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def replaceDigits(self, s: str) -> str:
        s = list(s)
        for i in range(1, len(s), 2):
            s[i] = chr(ord(s[i - 1]) + int(s[i]))
        return ''.join(s)
```

#### Java

```java
class Solution {
    public String replaceDigits(String s) {
        char[] cs = s.toCharArray();
        for (int i = 1; i < cs.length; i += 2) {
            cs[i] = (char) (cs[i - 1] + (cs[i] - '0'));
        }
        return String.valueOf(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string replaceDigits(string s) {
        int n = s.size();
        for (int i = 1; i < n; i += 2) {
            s[i] = s[i - 1] + s[i] - '0';
        }
        return s;
    }
};
```

#### Go

```go
func replaceDigits(s string) string {
	cs := []byte(s)
	for i := 1; i < len(s); i += 2 {
		cs[i] = cs[i-1] + cs[i] - '0'
	}
	return string(cs)
}
```

#### TypeScript

```ts
function replaceDigits(s: string): string {
    const n = s.length;
    const ans = [...s];
    for (let i = 1; i < n; i += 2) {
        ans[i] = String.fromCharCode(ans[i - 1].charCodeAt(0) + Number(ans[i]));
    }
    return ans.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn replace_digits(s: String) -> String {
        let n = s.len();
        let mut ans = s.into_bytes();
        let mut i = 1;
        while i < n {
            ans[i] = ans[i - 1] + (ans[i] - b'0');
            i += 2;
        }
        ans.into_iter().map(char::from).collect()
    }
}
```

#### C

```c
char* replaceDigits(char* s) {
    int n = strlen(s);
    for (int i = 1; i < n; i += 2) {
        s[i] = s[i - 1] + s[i] - '0';
    }
    return s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
