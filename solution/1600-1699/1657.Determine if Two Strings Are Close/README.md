---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1657.Determine%20if%20Two%20Strings%20Are%20Close/README.md
rating: 1530
source: 第 215 场周赛 Q2
tags:
    - 哈希表
    - 字符串
    - 计数
    - 排序
---

<!-- problem:start -->

# [1657. 确定两个字符串是否接近](https://leetcode.cn/problems/determine-if-two-strings-are-close)

[English Version](/solution/1600-1699/1657.Determine%20if%20Two%20Strings%20Are%20Close/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 <strong>接近</strong> ：</p>

<ul>
	<li>操作 1：交换任意两个 <strong>现有</strong> 字符。

    <ul>
    	<li>例如，<code>a<u>b</u>cd<u>e</u> -&gt; a<u>e</u>cd<u>b</u></code></li>
    </ul>
    </li>
    <li>操作 2：将一个 <strong>现有</strong> 字符的每次出现转换为另一个 <strong>现有</strong> 字符，并对另一个字符执行相同的操作。
    <ul>
    	<li>例如，<code><u>aa</u>c<u>abb</u> -&gt; <u>bb</u>c<u>baa</u></code>（所有 <code>a</code> 转化为 <code>b</code> ，而所有的 <code>b</code> 转换为 <code>a</code> ）</li>
    </ul>
    </li>

</ul>

<p>你可以根据需要对任意一个字符串多次使用这两种操作。</p>

<p>给你两个字符串，<code>word1</code> 和 <code>word2</code> 。如果<em> </em><code>word1</code><em> </em>和<em> </em><code>word2</code><em> </em><strong>接近 </strong>，就返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word1 = "abc", word2 = "bca"
<strong>输出：</strong>true
<strong>解释：</strong>2 次操作从 word1 获得 word2 。
执行操作 1："a<u>bc</u>" -&gt; "a<u>cb</u>"
执行操作 1："<u>a</u>c<u>b</u>" -&gt; "<u>b</u>c<u>a</u>"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word1 = "a", word2 = "aa"
<strong>输出：</strong>false
<strong>解释：</strong>不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word1 = "cabbba", word2 = "abbccc"
<strong>输出：</strong>true
<strong>解释：</strong>3 次操作从 word1 获得 word2 。
执行操作 1："ca<u>b</u>bb<u>a</u>" -&gt; "ca<u>a</u>bb<u>b</u>"
执行操作 2：<code>"</code><u>c</u>aa<u>bbb</u>" -&gt; "<u>b</u>aa<u>ccc</u>"
执行操作 2："<u>baa</u>ccc" -&gt; "<u>abb</u>ccc"
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word1</code> 和 <code>word2</code> 仅包含小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 排序

根据题目描述，两个字符串接近，需要同时满足以下两个条件：

1. 字符串 `word1` 和 `word2` 包含的字母种类必须相同；
1. 将字符串 `word1` 和 `word2` 的所有字符出现次数排序，得到的两个数组必须相同。

因此，我们可以先用数组或哈希表分别统计 `word1` 和 `word2` 中每种字母出现的次数，然后比较两者是否相同，不相同则提前返回 `false`。

否则，我们将对应的次数排序，然后依次比较对应位置的两个次数是否相同，不同则返回 `false`。

遍历结束，返回 `true`。

时间复杂度 $O(m + n + C \times \log C)$，空间复杂度 $O(C)$。其中 $m$ 和 $n$ 分别为字符串 `word1` 和 `word2` 的长度，而 $C$ 是字母种类。本题中 $C=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def closeStrings(self, word1: str, word2: str) -> bool:
        cnt1, cnt2 = Counter(word1), Counter(word2)
        return sorted(cnt1.values()) == sorted(cnt2.values()) and set(
            cnt1.keys()
        ) == set(cnt2.keys())
```

#### Java

```java
class Solution {
    public boolean closeStrings(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < word1.length(); ++i) {
            ++cnt1[word1.charAt(i) - 'a'];
        }
        for (int i = 0; i < word2.length(); ++i) {
            ++cnt2[word2.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if ((cnt1[i] == 0) != (cnt2[i] == 0)) {
                return false;
            }
        }
        Arrays.sort(cnt1);
        Arrays.sort(cnt2);
        return Arrays.equals(cnt1, cnt2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool closeStrings(string word1, string word2) {
        int cnt1[26]{};
        int cnt2[26]{};
        for (char& c : word1) {
            ++cnt1[c - 'a'];
        }
        for (char& c : word2) {
            ++cnt2[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if ((cnt1[i] == 0) != (cnt2[i] == 0)) {
                return false;
            }
        }
        sort(cnt1, cnt1 + 26);
        sort(cnt2, cnt2 + 26);
        return equal(cnt1, cnt1 + 26, cnt2);
    }
};
```

#### Go

```go
func closeStrings(word1 string, word2 string) bool {
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for _, c := range word1 {
		cnt1[c-'a']++
	}
	for _, c := range word2 {
		cnt2[c-'a']++
	}
	if !slices.EqualFunc(cnt1, cnt2, func(v1, v2 int) bool { return (v1 == 0) == (v2 == 0) }) {
		return false
	}
	sort.Ints(cnt1)
	sort.Ints(cnt2)
	return slices.Equal(cnt1, cnt2)
}
```

#### TypeScript

```ts
function closeStrings(word1: string, word2: string): boolean {
    const cnt1 = Array(26).fill(0);
    const cnt2 = Array(26).fill(0);
    for (const c of word1) {
        ++cnt1[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    for (const c of word2) {
        ++cnt2[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    for (let i = 0; i < 26; ++i) {
        if ((cnt1[i] === 0) !== (cnt2[i] === 0)) {
            return false;
        }
    }
    cnt1.sort((a, b) => a - b);
    cnt2.sort((a, b) => a - b);
    return cnt1.join('.') === cnt2.join('.');
}
```

#### Rust

```rust
impl Solution {
    pub fn close_strings(word1: String, word2: String) -> bool {
        let mut cnt1 = vec![0; 26];
        let mut cnt2 = vec![0; 26];
        for c in word1.chars() {
            cnt1[((c as u8) - b'a') as usize] += 1;
        }
        for c in word2.chars() {
            cnt2[((c as u8) - b'a') as usize] += 1;
        }
        for i in 0..26 {
            if (cnt1[i] == 0) != (cnt2[i] == 0) {
                return false;
            }
        }
        cnt1.sort();
        cnt2.sort();
        cnt1 == cnt2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
