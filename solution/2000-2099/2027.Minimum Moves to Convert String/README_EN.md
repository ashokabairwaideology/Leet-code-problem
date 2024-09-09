---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2027.Minimum%20Moves%20to%20Convert%20String/README_EN.md
rating: 1346
source: Weekly Contest 261 Q1
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [2027. Minimum Moves to Convert String](https://leetcode.com/problems/minimum-moves-to-convert-string)

[中文文档](/solution/2000-2099/2027.Minimum%20Moves%20to%20Convert%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of <code>n</code> characters which are either <code>&#39;X&#39;</code> or <code>&#39;O&#39;</code>.</p>

<p>A <strong>move</strong> is defined as selecting <strong>three</strong> <strong>consecutive characters</strong> of <code>s</code> and converting them to <code>&#39;O&#39;</code>. Note that if a move is applied to the character <code>&#39;O&#39;</code>, it will stay the <strong>same</strong>.</p>

<p>Return <em>the <strong>minimum</strong> number of moves required so that all the characters of </em><code>s</code><em> are converted to </em><code>&#39;O&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;XXX&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> <u>XXX</u> -&gt; OOO
We select all the 3 characters and convert them in one move.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;XXOX&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> <u>XXO</u>X -&gt; O<u>OOX</u> -&gt; OOOO
We select the first 3 characters in the first move, and convert them to <code>&#39;O&#39;</code>.
Then we select the last 3 characters and convert them so that the final string contains all <code>&#39;O&#39;</code>s.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;OOOO&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no <code>&#39;X&#39;s</code> in <code>s</code> to convert.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;X&#39;</code> or <code>&#39;O&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy Algorithm

Traverse the string $s$. Whenever you encounter `'X'`, move the pointer $i$ three steps forward and add $1$ to the answer; otherwise, move the pointer $i$ one step forward.

The time complexity is $O(n)$, where $n$ represents the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMoves(self, s: str) -> int:
        ans = i = 0
        while i < len(s):
            if s[i] == "X":
                ans += 1
                i += 3
            else:
                i += 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumMoves(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'X') {
                ++ans;
                i += 2;
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
    int minimumMoves(string s) {
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == 'X') {
                ++ans;
                i += 2;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumMoves(s string) (ans int) {
	for i := 0; i < len(s); i++ {
		if s[i] == 'X' {
			ans++
			i += 2
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumMoves(s: string): number {
    const n = s.length;
    let ans = 0;
    let i = 0;
    while (i < n) {
        if (s[i] === 'X') {
            ans++;
            i += 3;
        } else {
            i++;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_moves(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n {
            if s[i] == b'X' {
                ans += 1;
                i += 3;
            } else {
                i += 1;
            }
        }
        ans
    }
}
```

#### C

```c
int minimumMoves(char* s) {
    int n = strlen(s);
    int ans = 0;
    int i = 0;
    while (i < n) {
        if (s[i] == 'X') {
            ans++;
            i += 3;
        } else {
            i++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
