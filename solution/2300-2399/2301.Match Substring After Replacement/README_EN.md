---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2301.Match%20Substring%20After%20Replacement/README_EN.md
rating: 1860
source: Biweekly Contest 80 Q3
tags:
    - Array
    - Hash Table
    - String
    - String Matching
---

<!-- problem:start -->

# [2301. Match Substring After Replacement](https://leetcode.com/problems/match-substring-after-replacement)

[中文文档](/solution/2300-2399/2301.Match%20Substring%20After%20Replacement/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>sub</code>. You are also given a 2D character array <code>mappings</code> where <code>mappings[i] = [old<sub>i</sub>, new<sub>i</sub>]</code> indicates that you may perform the following operation <strong>any</strong> number of times:</p>

<ul>
	<li><strong>Replace</strong> a character <code>old<sub>i</sub></code> of <code>sub</code> with <code>new<sub>i</sub></code>.</li>
</ul>

<p>Each character in <code>sub</code> <strong>cannot</strong> be replaced more than once.</p>

<p>Return <code>true</code><em> if it is possible to make </em><code>sub</code><em> a substring of </em><code>s</code><em> by replacing zero or more characters according to </em><code>mappings</code>. Otherwise, return <code>false</code>.</p>

<p>A <strong>substring</strong> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;fool3e7bar&quot;, sub = &quot;leet&quot;, mappings = [[&quot;e&quot;,&quot;3&quot;],[&quot;t&quot;,&quot;7&quot;],[&quot;t&quot;,&quot;8&quot;]]
<strong>Output:</strong> true
<strong>Explanation:</strong> Replace the first &#39;e&#39; in sub with &#39;3&#39; and &#39;t&#39; in sub with &#39;7&#39;.
Now sub = &quot;l3e7&quot; is a substring of s, so we return true.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;fooleetbar&quot;, sub = &quot;f00l&quot;, mappings = [[&quot;o&quot;,&quot;0&quot;]]
<strong>Output:</strong> false
<strong>Explanation:</strong> The string &quot;f00l&quot; is not a substring of s and no replacements can be made.
Note that we cannot replace &#39;0&#39; with &#39;o&#39;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Fool33tbaR&quot;, sub = &quot;leetd&quot;, mappings = [[&quot;e&quot;,&quot;3&quot;],[&quot;t&quot;,&quot;7&quot;],[&quot;t&quot;,&quot;8&quot;],[&quot;d&quot;,&quot;b&quot;],[&quot;p&quot;,&quot;b&quot;]]
<strong>Output:</strong> true
<strong>Explanation:</strong> Replace the first and second &#39;e&#39; in sub with &#39;3&#39; and &#39;d&#39; in sub with &#39;b&#39;.
Now sub = &quot;l33tb&quot; is a substring of s, so we return true.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sub.length &lt;= s.length &lt;= 5000</code></li>
	<li><code>0 &lt;= mappings.length &lt;= 1000</code></li>
	<li><code>mappings[i].length == 2</code></li>
	<li><code>old<sub>i</sub> != new<sub>i</sub></code></li>
	<li><code>s</code> and <code>sub</code> consist of uppercase and lowercase English letters and digits.</li>
	<li><code>old<sub>i</sub></code> and <code>new<sub>i</sub></code> are either uppercase or lowercase English letters or digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

First, we use a hash table $d$ to record the set of characters that each character can be replaced with.

Then we enumerate all substrings of length $sub$ in $s$, and judge whether the string $sub$ can be obtained by replacement. If it can, return `true`, otherwise enumerate the next substring.

At the end of the enumeration, it means that $sub$ cannot be obtained by replacing any substring in $s$, so return `false`.

The time complexity is $O(m \times n)$, and the space complexity is $O(C^2)$. Here, $m$ and $n$ are the lengths of the strings $s$ and $sub$ respectively, and $C$ is the size of the character set.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def matchReplacement(self, s: str, sub: str, mappings: List[List[str]]) -> bool:
        d = defaultdict(set)
        for a, b in mappings:
            d[a].add(b)
        for i in range(len(s) - len(sub) + 1):
            if all(a == b or a in d[b] for a, b in zip(s[i : i + len(sub)], sub)):
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        Map<Character, Set<Character>> d = new HashMap<>();
        for (var e : mappings) {
            d.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
        }
        int m = s.length(), n = sub.length();
        for (int i = 0; i < m - n + 1; ++i) {
            boolean ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s.charAt(i + j), b = sub.charAt(j);
                if (a != b && !d.getOrDefault(b, Collections.emptySet()).contains(a)) {
                    ok = false;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool matchReplacement(string s, string sub, vector<vector<char>>& mappings) {
        unordered_map<char, unordered_set<char>> d;
        for (auto& e : mappings) {
            d[e[0]].insert(e[1]);
        }
        int m = s.size(), n = sub.size();
        for (int i = 0; i < m - n + 1; ++i) {
            bool ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s[i + j], b = sub[j];
                if (a != b && !d[b].count(a)) {
                    ok = false;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func matchReplacement(s string, sub string, mappings [][]byte) bool {
	d := map[byte]map[byte]bool{}
	for _, e := range mappings {
		if d[e[0]] == nil {
			d[e[0]] = map[byte]bool{}
		}
		d[e[0]][e[1]] = true
	}
	for i := 0; i < len(s)-len(sub)+1; i++ {
		ok := true
		for j := 0; j < len(sub) && ok; j++ {
			a, b := s[i+j], sub[j]
			if a != b && !d[b][a] {
				ok = false
			}
		}
		if ok {
			return true
		}
	}
	return false
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Array + Enumeration

Since the character set only contains uppercase and lowercase English letters and numbers, we can directly use a $128 \times 128$ array $d$ to record the set of characters that each character can be replaced with.

The time complexity is $O(m \times n)$, and the space complexity is $O(C^2)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def matchReplacement(self, s: str, sub: str, mappings: List[List[str]]) -> bool:
        d = [[False] * 128 for _ in range(128)]
        for a, b in mappings:
            d[ord(a)][ord(b)] = True
        for i in range(len(s) - len(sub) + 1):
            if all(
                a == b or d[ord(b)][ord(a)] for a, b in zip(s[i : i + len(sub)], sub)
            ):
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        boolean[][] d = new boolean[128][128];
        for (var e : mappings) {
            d[e[0]][e[1]] = true;
        }
        int m = s.length(), n = sub.length();
        for (int i = 0; i < m - n + 1; ++i) {
            boolean ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s.charAt(i + j), b = sub.charAt(j);
                if (a != b && !d[b][a]) {
                    ok = false;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool matchReplacement(string s, string sub, vector<vector<char>>& mappings) {
        bool d[128][128]{};
        for (auto& e : mappings) {
            d[e[0]][e[1]] = true;
        }
        int m = s.size(), n = sub.size();
        for (int i = 0; i < m - n + 1; ++i) {
            bool ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s[i + j], b = sub[j];
                if (a != b && !d[b][a]) {
                    ok = false;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func matchReplacement(s string, sub string, mappings [][]byte) bool {
	d := [128][128]bool{}
	for _, e := range mappings {
		d[e[0]][e[1]] = true
	}
	for i := 0; i < len(s)-len(sub)+1; i++ {
		ok := true
		for j := 0; j < len(sub) && ok; j++ {
			a, b := s[i+j], sub[j]
			if a != b && !d[b][a] {
				ok = false
			}
		}
		if ok {
			return true
		}
	}
	return false
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
