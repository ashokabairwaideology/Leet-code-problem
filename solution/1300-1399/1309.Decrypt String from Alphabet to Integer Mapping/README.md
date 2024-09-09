---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1309.Decrypt%20String%20from%20Alphabet%20to%20Integer%20Mapping/README.md
rating: 1257
source: 第 170 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [1309. 解码字母到整数映射](https://leetcode.cn/problems/decrypt-string-from-alphabet-to-integer-mapping)

[English Version](/solution/1300-1399/1309.Decrypt%20String%20from%20Alphabet%20to%20Integer%20Mapping/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>，它由数字（<code>'0'</code> - <code>'9'</code>）和&nbsp;<code>'#'</code>&nbsp;组成。我们希望按下述规则将&nbsp;<code>s</code>&nbsp;映射为一些小写英文字符：</p>

<ul>
	<li>字符（<code>'a'</code> - <code>'i'</code>）分别用（<code>'1'</code> -&nbsp;<code>'9'</code>）表示。</li>
	<li>字符（<code>'j'</code> - <code>'z'</code>）分别用（<code>'10#'</code>&nbsp;-&nbsp;<code>'26#'</code>）表示。&nbsp;</li>
</ul>

<p>返回映射之后形成的新字符串。</p>

<p>题目数据保证映射始终唯一。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "10#11#12"
<strong>输出：</strong>"jkab"
<strong>解释：</strong>"j" -&gt; "10#" , "k" -&gt; "11#" , "a" -&gt; "1" , "b" -&gt; "2".
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "1326#"
<strong>输出：</strong>"acz"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> 只包含数字（<code>'0'</code>-<code>'9'</code>）和&nbsp;<code>'#'</code>&nbsp;字符。</li>
	<li><code>s</code>&nbsp;是映射始终存在的有效字符串。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def freqAlphabets(self, s: str) -> str:
        def get(s):
            return chr(ord('a') + int(s) - 1)

        i, n = 0, len(s)
        res = []
        while i < n:
            if i + 2 < n and s[i + 2] == '#':
                res.append(get(s[i : i + 2]))
                i += 3
            else:
                res.append(get(s[i]))
                i += 1
        return ''.join(res)
```

#### Java

```java
class Solution {
    public String freqAlphabets(String s) {
        int i = 0, n = s.length();
        StringBuilder res = new StringBuilder();
        while (i < n) {
            if (i + 2 < n && s.charAt(i + 2) == '#') {
                res.append(get(s.substring(i, i + 2)));
                i += 3;
            } else {
                res.append(get(s.substring(i, i + 1)));
                i += 1;
            }
        }
        return res.toString();
    }

    private char get(String s) {
        return (char) ('a' + Integer.parseInt(s) - 1);
    }
}
```

#### TypeScript

```ts
function freqAlphabets(s: string): string {
    const n = s.length;
    const ans = [];
    let i = 0;
    while (i < n) {
        if (s[i + 2] == '#') {
            ans.push(s.slice(i, i + 2));
            i += 3;
        } else {
            ans.push(s[i]);
            i += 1;
        }
    }
    return ans.map(c => String.fromCharCode('a'.charCodeAt(0) + Number(c) - 1)).join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn freq_alphabets(s: String) -> String {
        let s = s.as_bytes();
        let n = s.len();
        let mut res = String::new();
        let mut i = 0;
        while i < n {
            let code: u8;
            if s.get(i + 2).is_some() && s[i + 2] == b'#' {
                code = (s[i] - b'0') * 10 + s[i + 1];
                i += 3;
            } else {
                code = s[i];
                i += 1;
            }
            res.push(char::from(('a' as u8) + code - b'1'));
        }
        res
    }
}
```

#### C

```c
char* freqAlphabets(char* s) {
    int n = strlen(s);
    int i = 0;
    int j = 0;
    char* ans = malloc(sizeof(s) * n);
    while (i < n) {
        int t;
        if (i + 2 < n && s[i + 2] == '#') {
            t = (s[i] - '0') * 10 + s[i + 1];
            i += 3;
        } else {
            t = s[i];
            i += 1;
        }
        ans[j++] = 'a' + t - '1';
    }
    ans[j] = '\0';
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
