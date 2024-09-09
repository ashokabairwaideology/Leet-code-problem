---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1816.Truncate%20Sentence/README_EN.md
rating: 1235
source: Weekly Contest 235 Q1
tags:
    - Array
    - String
---

<!-- problem:start -->

# [1816. Truncate Sentence](https://leetcode.com/problems/truncate-sentence)

[中文文档](/solution/1800-1899/1816.Truncate%20Sentence/README.md)

## Description

<!-- description:start -->

<p>A <strong>sentence</strong> is a list of words that are separated by a single space with no leading or trailing spaces. Each of the words consists of <strong>only</strong> uppercase and lowercase English letters (no punctuation).</p>

<ul>
	<li>For example, <code>&quot;Hello World&quot;</code>, <code>&quot;HELLO&quot;</code>, and <code>&quot;hello world hello world&quot;</code> are all sentences.</li>
</ul>

<p>You are given a sentence <code>s</code>​​​​​​ and an integer <code>k</code>​​​​​​. You want to <strong>truncate</strong> <code>s</code>​​​​​​ such that it contains only the <strong>first</strong> <code>k</code>​​​​​​ words. Return <code>s</code>​​​​<em>​​ after <strong>truncating</strong> it.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Hello how are you Contestant&quot;, k = 4
<strong>Output:</strong> &quot;Hello how are you&quot;
<strong>Explanation:</strong>
The words in s are [&quot;Hello&quot;, &quot;how&quot; &quot;are&quot;, &quot;you&quot;, &quot;Contestant&quot;].
The first 4 words are [&quot;Hello&quot;, &quot;how&quot;, &quot;are&quot;, &quot;you&quot;].
Hence, you should return &quot;Hello how are you&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;What is the solution to this problem&quot;, k = 4
<strong>Output:</strong> &quot;What is the solution&quot;
<strong>Explanation:</strong>
The words in s are [&quot;What&quot;, &quot;is&quot; &quot;the&quot;, &quot;solution&quot;, &quot;to&quot;, &quot;this&quot;, &quot;problem&quot;].
The first 4 words are [&quot;What&quot;, &quot;is&quot;, &quot;the&quot;, &quot;solution&quot;].
Hence, you should return &quot;What is the solution&quot;.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;chopper is not a tanuki&quot;, k = 5
<strong>Output:</strong> &quot;chopper is not a tanuki&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>k</code> is in the range <code>[1, the number of words in s]</code>.</li>
	<li><code>s</code> consist of only lowercase and uppercase English letters and spaces.</li>
	<li>The words in <code>s</code> are separated by a single space.</li>
	<li>There are no leading or trailing spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We traverse the string $s$ from the beginning. For the current character $s[i]$, if it is a space, we decrement $k$. When $k$ becomes $0$, it means that we have extracted $k$ words, so we return the substring $s[0..i)$.

After the traversal, we return $s$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. Ignoring the space complexity of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def truncateSentence(self, s: str, k: int) -> str:
        return ' '.join(s.split()[:k])
```

#### Java

```java
class Solution {
    public String truncateSentence(String s, int k) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ' ' && (--k) == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string truncateSentence(string s, int k) {
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == ' ' && (--k) == 0) {
                return s.substr(0, i);
            }
        }
        return s;
    }
};
```

#### Go

```go
func truncateSentence(s string, k int) string {
	for i, c := range s {
		if c == ' ' {
			k--
		}
		if k == 0 {
			return s[:i]
		}
	}
	return s
}
```

#### TypeScript

```ts
function truncateSentence(s: string, k: number): string {
    for (let i = 0; i < s.length; ++i) {
        if (s[i] === ' ' && --k === 0) {
            return s.slice(0, i);
        }
    }
    return s;
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {number} k
 * @return {string}
 */
var truncateSentence = function (s, k) {
    for (let i = 0; i < s.length; ++i) {
        if (s[i] === ' ' && --k === 0) {
            return s.slice(0, i);
        }
    }
    return s;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def truncateSentence(self, s: str, k: int) -> str:
        for i, c in enumerate(s):
            k -= c == ' '
            if k == 0:
                return s[:i]
        return s
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
