---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1156.Swap%20For%20Longest%20Repeated%20Character%20Substring/README_EN.md
rating: 1787
source: Weekly Contest 149 Q3
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [1156. Swap For Longest Repeated Character Substring](https://leetcode.com/problems/swap-for-longest-repeated-character-substring)

[中文文档](/solution/1100-1199/1156.Swap%20For%20Longest%20Repeated%20Character%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>text</code>. You can swap two of the characters in the <code>text</code>.</p>

<p>Return <em>the length of the longest substring with repeated characters</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;ababa&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can swap the first &#39;b&#39; with the last &#39;a&#39;, or the last &#39;b&#39; with the first &#39;a&#39;. Then, the longest repeated character substring is &quot;aaa&quot; with length 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;aaabaaa&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> Swap &#39;b&#39; with the last &#39;a&#39; (or the first &#39;a&#39;), and we get longest repeated character substring &quot;aaaaaa&quot; with length 6.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;aaaaa&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> No need to swap, longest repeated character substring is &quot;aaaaa&quot; with length is 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>text</code> consist of lowercase English characters only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

First, we use a hash table or array $cnt$ to count the occurrence of each character in the string $text$.

Next, we define a pointer $i$, initially $i = 0$. Each time, we set the pointer $j$ to $i$, and continuously move $j$ to the right until the character pointed by $j$ is different from the character pointed by $i$. At this time, we get a substring $text[i..j-1]$ of length $l = j - i$, where all characters are the same.

Then we skip the character pointed by the pointer $j$, and continue to move the pointer $k$ to the right until the character pointed by $k$ is different from the character pointed by $i$. At this time, we get a substring $text[j+1..k-1]$ of length $r = k - j - 1$, where all characters are the same. So the longest single-character repeated substring we can get by at most one swap operation is $\min(l + r + 1, cnt[text[i]])$. Next, we move the pointer $i$ to $j$ and continue to find the next substring. We take the maximum length of all substrings that meet the conditions.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the length of the string, and $C$ is the size of the character set. In this problem, $C = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxRepOpt1(self, text: str) -> int:
        cnt = Counter(text)
        n = len(text)
        ans = i = 0
        while i < n:
            j = i
            while j < n and text[j] == text[i]:
                j += 1
            l = j - i
            k = j + 1
            while k < n and text[k] == text[i]:
                k += 1
            r = k - j - 1
            ans = max(ans, min(l + r + 1, cnt[text[i]]))
            i = j
        return ans
```

#### Java

```java
class Solution {
    public int maxRepOpt1(String text) {
        int[] cnt = new int[26];
        int n = text.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[text.charAt(i) - 'a'];
        }
        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && text.charAt(j) == text.charAt(i)) {
                ++j;
            }
            int l = j - i;
            int k = j + 1;
            while (k < n && text.charAt(k) == text.charAt(i)) {
                ++k;
            }
            int r = k - j - 1;
            ans = Math.max(ans, Math.min(l + r + 1, cnt[text.charAt(i) - 'a']));
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxRepOpt1(string text) {
        int cnt[26] = {0};
        for (char& c : text) {
            ++cnt[c - 'a'];
        }
        int n = text.size();
        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && text[j] == text[i]) {
                ++j;
            }
            int l = j - i;
            int k = j + 1;
            while (k < n && text[k] == text[i]) {
                ++k;
            }
            int r = k - j - 1;
            ans = max(ans, min(l + r + 1, cnt[text[i] - 'a']));
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func maxRepOpt1(text string) (ans int) {
	cnt := [26]int{}
	for _, c := range text {
		cnt[c-'a']++
	}
	n := len(text)
	for i, j := 0, 0; i < n; i = j {
		j = i
		for j < n && text[j] == text[i] {
			j++
		}
		l := j - i
		k := j + 1
		for k < n && text[k] == text[i] {
			k++
		}
		r := k - j - 1
		ans = max(ans, min(l+r+1, cnt[text[i]-'a']))
	}
	return
}
```

#### TypeScript

```ts
function maxRepOpt1(text: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const cnt: number[] = new Array(26).fill(0);
    for (const c of text) {
        cnt[idx(c)]++;
    }
    let ans = 0;
    let i = 0;
    const n = text.length;
    while (i < n) {
        let j = i;
        while (j < n && text[j] === text[i]) {
            ++j;
        }
        const l = j - i;
        let k = j + 1;
        while (k < n && text[k] === text[i]) {
            ++k;
        }
        const r = k - j - 1;
        ans = Math.max(ans, Math.min(cnt[idx(text[i])], l + r + 1));
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
