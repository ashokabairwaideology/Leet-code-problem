---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1410.HTML%20Entity%20Parser/README.md
rating: 1405
source: 第 184 场周赛 Q3
tags:
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [1410. HTML 实体解析器](https://leetcode.cn/problems/html-entity-parser)

[English Version](/solution/1400-1499/1410.HTML%20Entity%20Parser/README_EN.md)

## 题目描述

<!-- description:start -->

<p>「HTML&nbsp;实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。</p>

<p>HTML 里这些特殊字符和它们对应的字符实体包括：</p>

<ul>
	<li><strong>双引号：</strong>字符实体为&nbsp;<code>&amp;quot;</code>&nbsp;，对应的字符是&nbsp;<code>&quot;</code>&nbsp;。</li>
	<li><strong>单引号：</strong>字符实体为&nbsp;<code>&amp;apos;</code>&nbsp;，对应的字符是&nbsp;<code>&#39;</code>&nbsp;。</li>
	<li><strong>与符号：</strong>字符实体为&nbsp;<code>&amp;amp;</code>&nbsp;，对应对的字符是&nbsp;<code>&amp;</code>&nbsp;。</li>
	<li><strong>大于号：</strong>字符实体为&nbsp;<code>&amp;gt;</code>&nbsp;，对应的字符是&nbsp;<code>&gt;</code>&nbsp;。</li>
	<li><strong>小于号：</strong>字符实体为&nbsp;<code>&amp;lt;</code>&nbsp;，对应的字符是&nbsp;<code>&lt;</code>&nbsp;。</li>
	<li><strong>斜线号：</strong>字符实体为&nbsp;<code>&amp;frasl;</code>&nbsp;，对应的字符是&nbsp;<code>/</code>&nbsp;。</li>
</ul>

<p>给你输入字符串&nbsp;<code>text</code>&nbsp;，请你实现一个 HTML&nbsp;实体解析器，返回解析器解析后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>text = &quot;&amp;amp; is an HTML entity but &amp;ambassador; is not.&quot;
<strong>输出：</strong>&quot;&amp; is an HTML entity but &amp;ambassador; is not.&quot;
<strong>解释：</strong>解析器把字符实体 &amp;amp; 用 &amp; 替换
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>text = &quot;and I quote: &amp;quot;...&amp;quot;&quot;
<strong>输出：</strong>&quot;and I quote: \&quot;...\&quot;&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>text = &quot;Stay home! Practice on Leetcode :)&quot;
<strong>输出：</strong>&quot;Stay home! Practice on Leetcode :)&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>text = &quot;x &amp;gt; y &amp;amp;&amp;amp; x &amp;lt; y is always false&quot;
<strong>输出：</strong>&quot;x &gt; y &amp;&amp; x &lt; y is always false&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>text = &quot;leetcode.com&amp;frasl;problemset&amp;frasl;all&quot;
<strong>输出：</strong>&quot;leetcode.com/problemset/all&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10^5</code></li>
	<li>字符串可能包含 256 个ASCII 字符中的任意字符。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 模拟

我们可以使用哈希表来存储每个字符实体对应的字符，然后遍历字符串，当遇到字符实体时，我们就将其替换为对应的字符。

时间复杂度 $O(n \times l)$，空间复杂度 $O(l)$。其中 $n$ 是字符串的长度，而 $l$ 是字符实体的总长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def entityParser(self, text: str) -> str:
        d = {
            '&quot;': '"',
            '&apos;': "'",
            '&amp;': "&",
            "&gt;": '>',
            "&lt;": '<',
            "&frasl;": '/',
        }
        i, n = 0, len(text)
        ans = []
        while i < n:
            for l in range(1, 8):
                j = i + l
                if text[i:j] in d:
                    ans.append(d[text[i:j]])
                    i = j
                    break
            else:
                ans.append(text[i])
                i += 1
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String entityParser(String text) {
        Map<String, String> d = new HashMap<>();
        d.put("&quot;", "\"");
        d.put("&apos;", "'");
        d.put("&amp;", "&");
        d.put("&gt;", ">");
        d.put("&lt;", "<");
        d.put("&frasl;", "/");
        StringBuilder ans = new StringBuilder();
        int i = 0;
        int n = text.length();
        while (i < n) {
            boolean found = false;
            for (int l = 1; l < 8; ++l) {
                int j = i + l;
                if (j <= n) {
                    String t = text.substring(i, j);
                    if (d.containsKey(t)) {
                        ans.append(d.get(t));
                        i = j;
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                ans.append(text.charAt(i++));
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string entityParser(string text) {
        unordered_map<string, string> d = {
            {"&quot;", "\""},
            {"&apos;", "'"},
            {"&amp;", "&"},
            {"&gt;", ">"},
            {"&lt;", "<"},
            {"&frasl;", "/"},
        };
        string ans = "";
        int i = 0, n = text.size();
        while (i < n) {
            bool found = false;
            for (int l = 1; l < 8; ++l) {
                int j = i + l;
                if (j <= n) {
                    string t = text.substr(i, l);
                    if (d.count(t)) {
                        ans += d[t];
                        i = j;
                        found = true;
                        break;
                    }
                }
            }
            if (!found) ans += text[i++];
        }
        return ans;
    }
};
```

#### Go

```go
func entityParser(text string) string {
	d := map[string]string{
		"&quot;":  "\"",
		"&apos;":  "'",
		"&amp;":   "&",
		"&gt;":    ">",
		"&lt;":    "<",
		"&frasl;": "/",
	}
	var ans strings.Builder
	i, n := 0, len(text)

	for i < n {
		found := false
		for l := 1; l < 8; l++ {
			j := i + l
			if j <= n {
				t := text[i:j]
				if val, ok := d[t]; ok {
					ans.WriteString(val)
					i = j
					found = true
					break
				}
			}
		}
		if !found {
			ans.WriteByte(text[i])
			i++
		}
	}

	return ans.String()
}
```

#### TypeScript

```ts
function entityParser(text: string): string {
    const d: Record<string, string> = {
        '&quot;': '"',
        '&apos;': "'",
        '&amp;': '&',
        '&gt;': '>',
        '&lt;': '<',
        '&frasl;': '/',
    };

    let ans: string = '';
    let i: number = 0;
    const n: number = text.length;

    while (i < n) {
        let found: boolean = false;
        for (let l: number = 1; l < 8; ++l) {
            const j: number = i + l;
            if (j <= n) {
                const t: string = text.substring(i, j);
                if (d.hasOwnProperty(t)) {
                    ans += d[t];
                    i = j;
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            ans += text[i++];
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### TypeScript

```ts
function entityParser(text: string): string {
    const d: { [key: string]: string } = {
        '&quot;': '"',
        '&apos;': "'",
        '&amp;': '&',
        '&gt;': '>',
        '&lt;': '<',
        '&frasl;': '/',
    };

    const pattern = new RegExp(Object.keys(d).join('|'), 'g');
    return text.replace(pattern, match => d[match]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
