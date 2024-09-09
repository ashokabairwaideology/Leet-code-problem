---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1180.Count%20Substrings%20with%20Only%20One%20Distinct%20Letter/README_EN.md
rating: 1315
source: Biweekly Contest 8 Q1
tags:
    - Math
    - String
---

<!-- problem:start -->

# [1180. Count Substrings with Only One Distinct Letter 🔒](https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter)

[中文文档](/solution/1100-1199/1180.Count%20Substrings%20with%20Only%20One%20Distinct%20Letter/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, return <em>the number of substrings that have only <strong>one distinct</strong> letter</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaba&quot;
<strong>Output:</strong> 8
<strong>Explanation: </strong>The substrings with one distinct letter are &quot;aaa&quot;, &quot;aa&quot;, &quot;a&quot;, &quot;b&quot;.
&quot;aaa&quot; occurs 1 time.
&quot;aa&quot; occurs 2 times.
&quot;a&quot; occurs 4 times.
&quot;b&quot; occurs 1 time.
So the answer is 1 + 2 + 4 + 1 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaaaaaaaa&quot;
<strong>Output:</strong> 55
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> consists of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We can use two pointers, where pointer $i$ points to the start of the current substring, and pointer $j$ moves to the right to the first position that is different from $s[i]$. Then, $[i,..j-1]$ is a substring with $s[i]$ as the only character, and its length is $j-i$. Therefore, the number of substrings with $s[i]$ as the only character is $\frac{(j-i+1)(j-i)}{2}$, which is added to the answer. Then, we set $i=j$ and continue to traverse until $i$ exceeds the range of string $s$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countLetters(self, s: str) -> int:
        n = len(s)
        i = ans = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            ans += (1 + j - i) * (j - i) // 2
            i = j
        return ans
```

#### Java

```java
class Solution {
    public int countLetters(String s) {
        int ans = 0;
        for (int i = 0, n = s.length(); i < n;) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            ans += (1 + j - i) * (j - i) / 2;
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
    int countLetters(string s) {
        int ans = 0;
        for (int i = 0, n = s.size(); i < n;) {
            int j = i;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            ans += (1 + j - i) * (j - i) / 2;
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func countLetters(s string) int {
	ans := 0
	for i, n := 0, len(s); i < n; {
		j := i
		for j < n && s[j] == s[i] {
			j++
		}
		ans += (1 + j - i) * (j - i) / 2
		i = j
	}
	return ans
}
```

#### TypeScript

```ts
function countLetters(s: string): number {
    let ans = 0;
    const n = s.length;
    for (let i = 0; i < n; ) {
        let j = i;
        let cnt = 0;
        while (j < n && s[j] === s[i]) {
            ++j;
            ans += ++cnt;
        }
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countLetters(self, s: str) -> int:
        ans = 0
        i, n = 0, len(s)
        while i < n:
            j = i
            cnt = 0
            while j < n and s[j] == s[i]:
                j += 1
                cnt += 1
                ans += cnt
            i = j
        return ans
```

#### Java

```java
class Solution {
    public int countLetters(String s) {
        int ans = 0;
        int i = 0, n = s.length();
        while (i < n) {
            int j = i;
            int cnt = 0;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
                ans += ++cnt;
            }
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
    int countLetters(string s) {
        int ans = 0;
        int i = 0, n = s.size();
        while (i < n) {
            int j = i;
            int cnt = 0;
            while (j < n && s[j] == s[i]) {
                ++j;
                ans += ++cnt;
            }
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func countLetters(s string) (ans int) {
	i, n := 0, len(s)
	for i < n {
		j := i
		cnt := 0
		for j < n && s[j] == s[i] {
			j++
			cnt++
			ans += cnt
		}
		i = j
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
