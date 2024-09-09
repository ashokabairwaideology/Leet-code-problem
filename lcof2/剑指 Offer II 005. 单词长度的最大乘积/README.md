---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20005.%20%E5%8D%95%E8%AF%8D%E9%95%BF%E5%BA%A6%E7%9A%84%E6%9C%80%E5%A4%A7%E4%B9%98%E7%A7%AF/README.md
---

<!-- problem:start -->

# [剑指 Offer II 005. 单词长度的最大乘积](https://leetcode.cn/problems/aseY1I)

## 题目描述

<!-- description:start -->

<p>给定一个字符串数组&nbsp;<code>words</code>，请计算当两个字符串 <code>words[i]</code> 和 <code>words[j]</code> 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> words = <code>[&quot;abcw&quot;,&quot;baz&quot;,&quot;foo&quot;,&quot;bar&quot;,&quot;fxyz&quot;,&quot;abcdef&quot;]</code>
<strong>输出: </strong><code>16 
<strong>解释:</strong> 这两个单词为<strong> </strong></code><code>&quot;abcw&quot;, &quot;fxyz&quot;</code>。它们不包含相同字符，且长度的乘积最大。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> words = <code>[&quot;a&quot;,&quot;ab&quot;,&quot;abc&quot;,&quot;d&quot;,&quot;cd&quot;,&quot;bcd&quot;,&quot;abcd&quot;]</code>
<strong>输出: </strong><code>4 
<strong>解释: </strong></code>这两个单词为 <code>&quot;ab&quot;, &quot;cd&quot;</code>。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> words = <code>[&quot;a&quot;,&quot;aa&quot;,&quot;aaa&quot;,&quot;aaaa&quot;]</code>
<strong>输出: </strong><code>0 
<strong>解释: </strong>不存在这样的两个单词。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 1000</code></li>
	<li><code>words[i]</code>&nbsp;仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 318&nbsp;题相同：<a href="https://leetcode.cn/problems/maximum-product-of-word-lengths/">https://leetcode.cn/problems/maximum-product-of-word-lengths/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算 + 枚举

由于题目限定了字符串中只包含英语的小写字母，因此每个字符串可以用一个 $32$ 位整数表示，该整数的每个二进制位都是 $0$ 或 $1$，分别对应字符串的每个字母是否出现。这样一来，我们判断两个字符串是否含有相同字符，只需要将对应的整数进行按位与运算，即可得到一个新的整数，如果新的整数的二进制表示中的每一位都是 $0$，就说明两个字符串不含有相同的字符。

具体地，我们用一个长度为 $n$ 的整数数组 $mask$ 表示每个字符串对应的整数，其中第 $i$ 个元素 $mask[i]$ 表示字符串 $words[i]$ 对应的整数。对于任意两个下标 $i$ 和 $j$，如果 $mask[i]$ 和 $mask[j]$ 按位与运算的结果为 $0$，就说明 $words[i]$ 和 $words[j]$ 不包含相同字符。由于需要找到长度乘积的最大值，因此我们可以枚举所有的 $0 \leq i \lt j \lt n$，并计算 $words[i]$ 和 $words[j]$ 的长度乘积，最终得到长度乘积的最大值。

时间复杂度 $O(n \times (n + |S|))$，空间复杂度 $O(n)$。其中 $n$ 是数组 $words$ 的长度，而 $|S|$ 是字符串的最大长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProduct(self, words: List[str]) -> int:
        mask = [0] * len(words)
        for i, w in enumerate(words):
            for c in w:
                mask[i] |= 1 << (ord(c) - ord("a"))
        ans = 0
        for i, a in enumerate(words):
            for j, b in enumerate(words[i + 1 :], i + 1):
                if (mask[i] & mask[j]) == 0:
                    ans = max(ans, len(a) * len(b))
        return ans
```

#### Java

```java
class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] mask = new int[n];
        for (int i = 0; i < n; ++i) {
            for (char c : words[i].toCharArray()) {
                mask[i] |= 1 << (c - 'a');
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if ((mask[i] & mask[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProduct(vector<string>& words) {
        int n = words.size();
        int mask[n];
        memset(mask, 0, sizeof(mask));
        for (int i = 0; i < n; i++) {
            for (char c : words[i]) {
                mask[i] |= 1 << (c - 'a');
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if ((mask[i] & mask[j]) == 0) {
                    ans = max(ans, int(words[i].size() * words[j].size()));
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxProduct(words []string) (ans int) {
	n := len(words)
	mask := make([]int, n)
	for i, w := range words {
		for _, c := range w {
			mask[i] |= 1 << (c - 'a')
		}
	}
	for i, x := range mask {
		for j := i + 1; j < n; j++ {
			if x&mask[j] == 0 {
				ans = max(ans, len(words[i])*len(words[j]))
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function maxProduct(words: string[]): number {
    const n = words.length;
    const mask: number[] = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (const c of words[i]) {
            mask[i] |= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            if ((mask[i] & mask[j]) === 0) {
                ans = Math.max(ans, words[i].length * words[j].length);
            }
        }
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func maxProduct(_ words: [String]) -> Int {
        let n = words.count
        var masks = [Int](repeating: 0, count: n)

        for i in 0..<n {
            for c in words[i] {
                masks[i] |= 1 << (c.asciiValue! - Character("a").asciiValue!)
            }
        }

        var maxProduct = 0
        for i in 0..<n {
            for j in i+1..<n {
                if masks[i] & masks[j] == 0 {
                    maxProduct = max(maxProduct, words[i].count * words[j].count)
                }
            }
        }

        return maxProduct
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
