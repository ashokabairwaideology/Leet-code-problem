---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2573.Find%20the%20String%20with%20LCP/README_EN.md
rating: 2681
source: Weekly Contest 333 Q4
tags:
    - Greedy
    - Union Find
    - Array
    - String
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [2573. Find the String with LCP](https://leetcode.com/problems/find-the-string-with-lcp)

[中文文档](/solution/2500-2599/2573.Find%20the%20String%20with%20LCP/README.md)

## Description

<!-- description:start -->

<p>We define the <code>lcp</code> matrix of any <strong>0-indexed</strong> string <code>word</code> of <code>n</code> lowercase English letters as an <code>n x n</code> grid such that:</p>

<ul>
	<li><code>lcp[i][j]</code> is equal to the length of the <strong>longest common prefix</strong> between the substrings <code>word[i,n-1]</code> and <code>word[j,n-1]</code>.</li>
</ul>

<p>Given an&nbsp;<code>n x n</code> matrix <code>lcp</code>, return the alphabetically smallest string <code>word</code> that corresponds to <code>lcp</code>. If there is no such string, return an empty string.</p>

<p>A string <code>a</code> is lexicographically smaller than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears earlier in the alphabet than the corresponding letter in <code>b</code>. For example, <code>&quot;aabd&quot;</code> is lexicographically smaller than <code>&quot;aaca&quot;</code> because the first position they differ is at the third letter, and <code>&#39;b&#39;</code> comes before <code>&#39;c&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
<strong>Output:</strong> &quot;abab&quot;
<strong>Explanation:</strong> lcp corresponds to any 4 letter string with two alternating letters. The lexicographically smallest of them is &quot;abab&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
<strong>Output:</strong> &quot;aaaa&quot;
<strong>Explanation:</strong> lcp corresponds to any 4 letter string with a single distinct letter. The lexicographically smallest of them is &quot;aaaa&quot;. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> lcp[3][3] cannot be equal to 3 since word[3,...,3] consists of only a single letter; Thus, no answer exists.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n ==&nbsp;</code><code>lcp.length == </code><code>lcp[i].length</code>&nbsp;<code>&lt;= 1000</code></li>
	<li><code><font face="monospace">0 &lt;= lcp[i][j] &lt;= n</font></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Construction

Since the constructed string requires the lexicographically smallest order, we can start by filling the string $s$ with the character `'a'`.

If the current position $i$ has not been filled with a character, then we can fill the character `'a'` at position $i$. Then we enumerate all positions $j > i$. If $lcp[i][j] > 0$, then position $j$ should also be filled with the character `'a'`. Then we add one to the ASCII code of the character `'a'` and continue to fill the remaining unfilled positions.

After filling, if there are unfilled positions in the string, it means that the corresponding string cannot be constructed, so we return an empty string.

Next, we can enumerate each position $i$ and $j$ in the string from large to small, and then judge whether $s[i]$ and $s[j]$ are equal:

-   If $s[i] = s[j]$, at this time we need to judge whether $i$ and $j$ are the last positions of the string. If so, then $lcp[i][j]$ should be equal to $1$, otherwise $lcp[i][j]$ should be equal to $0$. If the above conditions are not met, it means that the corresponding string cannot be constructed, so we return an empty string. If $i$ and $j$ are not the last positions of the string, then $lcp[i][j]$ should be equal to $lcp[i + 1][j + 1] + 1$, otherwise it means that the corresponding string cannot be constructed, so we return an empty string.
-   Otherwise, if $lcp[i][j] > 0$, it means that the corresponding string cannot be constructed, so we return an empty string.

If every position in the string meets the above conditions, then we can construct the corresponding string and return it.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheString(self, lcp: List[List[int]]) -> str:
        n = len(lcp)
        s = [""] * n
        i = 0
        for c in ascii_lowercase:
            while i < n and s[i]:
                i += 1
            if i == n:
                break
            for j in range(i, n):
                if lcp[i][j]:
                    s[j] = c
        if "" in s:
            return ""
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if s[i] == s[j]:
                    if i == n - 1 or j == n - 1:
                        if lcp[i][j] != 1:
                            return ""
                    elif lcp[i][j] != lcp[i + 1][j + 1] + 1:
                        return ""
                elif lcp[i][j]:
                    return ""
        return "".join(s)
```

#### Java

```java
class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        int i = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            while (i < n && s[i] != '\0') {
                ++i;
            }
            if (i == n) {
                break;
            }
            for (int j = i; j < n; ++j) {
                if (lcp[i][j] > 0) {
                    s[j] = c;
                }
            }
        }
        for (i = 0; i < n; ++i) {
            if (s[i] == '\0') {
                return "";
            }
        }
        for (i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s[i] == s[j]) {
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) {
                            return "";
                        }
                    } else if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                        return "";
                    }
                } else if (lcp[i][j] > 0) {
                    return "";
                }
            }
        }
        return String.valueOf(s);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findTheString(vector<vector<int>>& lcp) {
        int i = 0, n = lcp.size();
        string s(n, '\0');
        for (char c = 'a'; c <= 'z'; ++c) {
            while (i < n && s[i]) {
                ++i;
            }
            if (i == n) {
                break;
            }
            for (int j = i; j < n; ++j) {
                if (lcp[i][j]) {
                    s[j] = c;
                }
            }
        }
        if (s.find('\0') != -1) {
            return "";
        }
        for (i = n - 1; ~i; --i) {
            for (int j = n - 1; ~j; --j) {
                if (s[i] == s[j]) {
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) {
                            return "";
                        }
                    } else if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                        return "";
                    }
                } else if (lcp[i][j]) {
                    return "";
                }
            }
        }
        return s;
    }
};
```

#### Go

```go
func findTheString(lcp [][]int) string {
	i, n := 0, len(lcp)
	s := make([]byte, n)
	for c := 'a'; c <= 'z'; c++ {
		for i < n && s[i] != 0 {
			i++
		}
		if i == n {
			break
		}
		for j := i; j < n; j++ {
			if lcp[i][j] > 0 {
				s[j] = byte(c)
			}
		}
	}
	if bytes.IndexByte(s, 0) >= 0 {
		return ""
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if s[i] == s[j] {
				if i == n-1 || j == n-1 {
					if lcp[i][j] != 1 {
						return ""
					}
				} else if lcp[i][j] != lcp[i+1][j+1]+1 {
					return ""
				}
			} else if lcp[i][j] > 0 {
				return ""
			}
		}
	}
	return string(s)
}
```

#### TypeScript

```ts
function findTheString(lcp: number[][]): string {
    let i: number = 0;
    const n: number = lcp.length;
    let s: string = '\0'.repeat(n);
    for (let ascii = 97; ascii < 123; ++ascii) {
        const c: string = String.fromCharCode(ascii);
        while (i < n && s[i] !== '\0') {
            ++i;
        }
        if (i === n) {
            break;
        }
        for (let j = i; j < n; ++j) {
            if (lcp[i][j]) {
                s = s.substring(0, j) + c + s.substring(j + 1);
            }
        }
    }
    if (s.indexOf('\0') !== -1) {
        return '';
    }
    for (i = n - 1; ~i; --i) {
        for (let j = n - 1; ~j; --j) {
            if (s[i] === s[j]) {
                if (i === n - 1 || j === n - 1) {
                    if (lcp[i][j] !== 1) {
                        return '';
                    }
                } else if (lcp[i][j] !== lcp[i + 1][j + 1] + 1) {
                    return '';
                }
            } else if (lcp[i][j]) {
                return '';
            }
        }
    }
    return s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
