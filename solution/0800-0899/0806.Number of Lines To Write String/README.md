---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0806.Number%20of%20Lines%20To%20Write%20String/README.md
tags:
    - 数组
    - 字符串
---

<!-- problem:start -->

# [806. 写字符串需要的行数](https://leetcode.cn/problems/number-of-lines-to-write-string)

[English Version](/solution/0800-0899/0806.Number%20of%20Lines%20To%20Write%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们要把给定的字符串 <code>S</code>&nbsp;从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。我们给定了一个数组&nbsp;<code>widths</code>&nbsp;，这个数组&nbsp;widths[0] 代表 &#39;a&#39; 需要的单位，&nbsp;widths[1] 代表 &#39;b&#39; 需要的单位，...，&nbsp;widths[25] 代表 &#39;z&#39; 需要的单位。</p>

<p>现在回答两个问题：至少多少行能放下<code>S</code>，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。</p>

<pre>
<strong>示例 1:</strong>
<strong>输入:</strong> 
widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = &quot;abcdefghijklmnopqrstuvwxyz&quot;
<strong>输出:</strong> [3, 60]
<strong>解释: 
</strong>所有的字符拥有相同的占用单位10。所以书写所有的26个字母，
我们需要2个整行和占用60个单位的一行。
</pre>

<pre>
<strong>示例 2:</strong>
<strong>输入:</strong> 
widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = &quot;bbbcccdddaaa&quot;
<strong>输出:</strong> [2, 4]
<strong>解释: 
</strong>除去字母&#39;a&#39;所有的字符都是相同的单位10，并且字符串 &quot;bbbcccdddaa&quot; 将会覆盖 9 * 10 + 2 * 4 = 98 个单位.
最后一个字母 &#39;a&#39; 将会被写到第二行，因为第一行只剩下2个单位了。
所以，这个答案是2行，第二行有4个单位宽度。
</pre>

<p>&nbsp;</p>

<p><strong>注:</strong></p>

<ul>
	<li>字符串&nbsp;<code>S</code> 的长度在&nbsp;[1, 1000] 的范围。</li>
	<li><code>S</code> 只包含小写字母。</li>
	<li><code>widths</code> 是长度为&nbsp;<code>26</code>的数组。</li>
	<li><code>widths[i]</code>&nbsp;值的范围在&nbsp;<code>[2, 10]</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们定义两个变量 `lines` 和 `last`，分别表示行数和最后一行的宽度，初始时 `lines = 1`，`last = 0`。

遍历字符串 $s$，对于每个字符 $c$，计算其宽度 $w$，如果 $last + w \leq 100$，则将 $w$ 加到 `last` 上，否则行数 `lines` 加一，并且 `last` 重置为 $w$。

最后返回 `lines` 和 `last` 构成的数组。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfLines(self, widths: List[int], s: str) -> List[int]:
        lines, last = 1, 0
        for w in map(lambda c: widths[ord(c) - ord("a")], s):
            if last + w <= 100:
                last += w
            else:
                lines += 1
                last = w
        return [lines, last]
```

#### Java

```java
class Solution {
    public int[] numberOfLines(int[] widths, String s) {
        int lines = 1, last = 0;
        for (int i = 0; i < s.length(); ++i) {
            int w = widths[s.charAt(i) - 'a'];
            if (last + w <= 100) {
                last += w;
            } else {
                ++lines;
                last = w;
            }
        }
        return new int[] {lines, last};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> numberOfLines(vector<int>& widths, string s) {
        int lines = 1, last = 0;
        for (char c : s) {
            int w = widths[c - 'a'];
            if (last + w <= 100) {
                last += w;
            } else {
                ++lines;
                last = w;
            }
        }
        return {lines, last};
    }
};
```

#### Go

```go
func numberOfLines(widths []int, s string) []int {
	lines, last := 1, 0
	for _, c := range s {
		w := widths[c-'a']
		if last+w <= 100 {
			last += w
		} else {
			lines++
			last = w
		}
	}
	return []int{lines, last}
}
```

#### TypeScript

```ts
function numberOfLines(widths: number[], s: string): number[] {
    let [lines, last] = [1, 0];
    for (const c of s) {
        const w = widths[c.charCodeAt(0) - 'a'.charCodeAt(0)];
        if (last + w <= 100) {
            last += w;
        } else {
            ++lines;
            last = w;
        }
    }
    return [lines, last];
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_lines(widths: Vec<i32>, s: String) -> Vec<i32> {
        let mut lines = 1;
        let mut last = 0;

        for c in s.chars() {
            let idx = ((c as u8) - b'a') as usize;
            let w = widths[idx];
            if last + w <= 100 {
                last += w;
            } else {
                lines += 1;
                last = w;
            }
        }

        vec![lines, last]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
