---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1763.Longest%20Nice%20Substring/README_EN.md
rating: 1521
source: Biweekly Contest 46 Q1
tags:
    - Bit Manipulation
    - Hash Table
    - String
    - Divide and Conquer
    - Sliding Window
---

<!-- problem:start -->

# [1763. Longest Nice Substring](https://leetcode.com/problems/longest-nice-substring)

[中文文档](/solution/1700-1799/1763.Longest%20Nice%20Substring/README.md)

## Description

<!-- description:start -->

<p>A string <code>s</code> is <strong>nice</strong> if, for every letter of the alphabet that <code>s</code> contains, it appears <strong>both</strong> in uppercase and lowercase. For example, <code>&quot;abABB&quot;</code> is nice because <code>&#39;A&#39;</code> and <code>&#39;a&#39;</code> appear, and <code>&#39;B&#39;</code> and <code>&#39;b&#39;</code> appear. However, <code>&quot;abA&quot;</code> is not because <code>&#39;b&#39;</code> appears, but <code>&#39;B&#39;</code> does not.</p>

<p>Given a string <code>s</code>, return <em>the longest <strong>substring</strong> of <code>s</code> that is <strong>nice</strong>. If there are multiple, return the substring of the <strong>earliest</strong> occurrence. If there are none, return an empty string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;YazaAay&quot;
<strong>Output:</strong> &quot;aAa&quot;
<strong>Explanation: </strong>&quot;aAa&quot; is a nice string because &#39;A/a&#39; is the only letter of the alphabet in s, and both &#39;A&#39; and &#39;a&#39; appear.
&quot;aAa&quot; is the longest nice substring.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Bb&quot;
<strong>Output:</strong> &quot;Bb&quot;
<strong>Explanation:</strong> &quot;Bb&quot; is a nice string because both &#39;B&#39; and &#39;b&#39; appear. The whole string is a substring.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;c&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There are no nice substrings.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of uppercase and lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestNiceSubstring(self, s: str) -> str:
        n = len(s)
        ans = ''
        for i in range(n):
            ss = set()
            for j in range(i, n):
                ss.add(s[j])
                if (
                    all(c.lower() in ss and c.upper() in ss for c in ss)
                    and len(ans) < j - i + 1
                ):
                    ans = s[i : j + 1]
        return ans
```

#### Java

```java
class Solution {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        int k = -1;
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            Set<Character> ss = new HashSet<>();
            for (int j = i; j < n; ++j) {
                ss.add(s.charAt(j));
                boolean ok = true;
                for (char a : ss) {
                    char b = (char) (a ^ 32);
                    if (!(ss.contains(a) && ss.contains(b))) {
                        ok = false;
                        break;
                    }
                }
                if (ok && mx < j - i + 1) {
                    mx = j - i + 1;
                    k = i;
                }
            }
        }
        return k == -1 ? "" : s.substring(k, k + mx);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string longestNiceSubstring(string s) {
        int n = s.size();
        int k = -1, mx = 0;
        for (int i = 0; i < n; ++i) {
            unordered_set<char> ss;
            for (int j = i; j < n; ++j) {
                ss.insert(s[j]);
                bool ok = true;
                for (auto& a : ss) {
                    char b = a ^ 32;
                    if (!(ss.count(a) && ss.count(b))) {
                        ok = false;
                        break;
                    }
                }
                if (ok && mx < j - i + 1) {
                    mx = j - i + 1;
                    k = i;
                }
            }
        }
        return k == -1 ? "" : s.substr(k, mx);
    }
};
```

#### Go

```go
func longestNiceSubstring(s string) string {
	n := len(s)
	k, mx := -1, 0
	for i := 0; i < n; i++ {
		ss := map[byte]bool{}
		for j := i; j < n; j++ {
			ss[s[j]] = true
			ok := true
			for a := range ss {
				b := a ^ 32
				if !(ss[a] && ss[b]) {
					ok = false
					break
				}
			}
			if ok && mx < j-i+1 {
				mx = j - i + 1
				k = i
			}
		}
	}
	if k < 0 {
		return ""
	}
	return s[k : k+mx]
}
```

#### TypeScript

```ts
function longestNiceSubstring(s: string): string {
    const n = s.length;
    let ans = '';
    for (let i = 0; i < n; i++) {
        let lower = 0,
            upper = 0;
        for (let j = i; j < n; j++) {
            const c = s.charCodeAt(j);
            if (c > 96) {
                lower |= 1 << (c - 97);
            } else {
                upper |= 1 << (c - 65);
            }
            if (lower == upper && j - i + 1 > ans.length) {
                ans = s.substring(i, j + 1);
            }
        }
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
    def longestNiceSubstring(self, s: str) -> str:
        n = len(s)
        ans = ''
        for i in range(n):
            lower = upper = 0
            for j in range(i, n):
                if s[j].islower():
                    lower |= 1 << (ord(s[j]) - ord('a'))
                else:
                    upper |= 1 << (ord(s[j]) - ord('A'))
                if lower == upper and len(ans) < j - i + 1:
                    ans = s[i : j + 1]
        return ans
```

#### Java

```java
class Solution {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        int k = -1;
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; ++j) {
                char c = s.charAt(j);
                if (Character.isLowerCase(c)) {
                    lower |= 1 << (c - 'a');
                } else {
                    upper |= 1 << (c - 'A');
                }
                if (lower == upper && mx < j - i + 1) {
                    mx = j - i + 1;
                    k = i;
                }
            }
        }
        return k == -1 ? "" : s.substring(k, k + mx);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string longestNiceSubstring(string s) {
        int n = s.size();
        int k = -1, mx = 0;
        for (int i = 0; i < n; ++i) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; ++j) {
                char c = s[j];
                if (islower(c))
                    lower |= 1 << (c - 'a');
                else
                    upper |= 1 << (c - 'A');
                if (lower == upper && mx < j - i + 1) {
                    mx = j - i + 1;
                    k = i;
                }
            }
        }
        return k == -1 ? "" : s.substr(k, mx);
    }
};
```

#### Go

```go
func longestNiceSubstring(s string) string {
	n := len(s)
	k, mx := -1, 0
	for i := 0; i < n; i++ {
		var lower, upper int
		for j := i; j < n; j++ {
			if unicode.IsLower(rune(s[j])) {
				lower |= 1 << (s[j] - 'a')
			} else {
				upper |= 1 << (s[j] - 'A')
			}
			if lower == upper && mx < j-i+1 {
				mx = j - i + 1
				k = i
			}
		}
	}
	if k < 0 {
		return ""
	}
	return s[k : k+mx]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
