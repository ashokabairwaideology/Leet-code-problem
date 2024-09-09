---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1433.Check%20If%20a%20String%20Can%20Break%20Another%20String/README_EN.md
rating: 1436
source: Biweekly Contest 25 Q3
tags:
    - Greedy
    - String
    - Sorting
---

<!-- problem:start -->

# [1433. Check If a String Can Break Another String](https://leetcode.com/problems/check-if-a-string-can-break-another-string)

[中文文档](/solution/1400-1499/1433.Check%20If%20a%20String%20Can%20Break%20Another%20String/README.md)

## Description

<!-- description:start -->

<p>Given two strings: <code>s1</code> and <code>s2</code> with the same&nbsp;size, check if some&nbsp;permutation of string <code>s1</code> can break&nbsp;some&nbsp;permutation of string <code>s2</code> or vice-versa. In other words <code>s2</code> can break <code>s1</code>&nbsp;or vice-versa.</p>

<p>A string <code>x</code>&nbsp;can break&nbsp;string <code>y</code>&nbsp;(both of size <code>n</code>) if <code>x[i] &gt;= y[i]</code>&nbsp;(in alphabetical order)&nbsp;for all <code>i</code>&nbsp;between <code>0</code> and <code>n-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abc&quot;, s2 = &quot;xya&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;ayx&quot; is a permutation of s2=&quot;xya&quot; which can break to string &quot;abc&quot; which is a permutation of s1=&quot;abc&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abe&quot;, s2 = &quot;acd&quot;
<strong>Output:</strong> false 
<strong>Explanation:</strong> All permutations for s1=&quot;abe&quot; are: &quot;abe&quot;, &quot;aeb&quot;, &quot;bae&quot;, &quot;bea&quot;, &quot;eab&quot; and &quot;eba&quot; and all permutation for s2=&quot;acd&quot; are: &quot;acd&quot;, &quot;adc&quot;, &quot;cad&quot;, &quot;cda&quot;, &quot;dac&quot; and &quot;dca&quot;. However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;leetcodee&quot;, s2 = &quot;interview&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s1.length == n</code></li>
	<li><code>s2.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li>All strings consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkIfCanBreak(self, s1: str, s2: str) -> bool:
        cs1 = sorted(s1)
        cs2 = sorted(s2)
        return all(a >= b for a, b in zip(cs1, cs2)) or all(
            a <= b for a, b in zip(cs1, cs2)
        )
```

#### Java

```java
class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);
        return check(cs1, cs2) || check(cs2, cs1);
    }

    private boolean check(char[] cs1, char[] cs2) {
        for (int i = 0; i < cs1.length; ++i) {
            if (cs1[i] < cs2[i]) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkIfCanBreak(string s1, string s2) {
        sort(s1.begin(), s1.end());
        sort(s2.begin(), s2.end());
        return check(s1, s2) || check(s2, s1);
    }

    bool check(string& s1, string& s2) {
        for (int i = 0; i < s1.size(); ++i) {
            if (s1[i] < s2[i]) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func checkIfCanBreak(s1 string, s2 string) bool {
	cs1 := []byte(s1)
	cs2 := []byte(s2)
	sort.Slice(cs1, func(i, j int) bool { return cs1[i] < cs1[j] })
	sort.Slice(cs2, func(i, j int) bool { return cs2[i] < cs2[j] })
	check := func(cs1, cs2 []byte) bool {
		for i := range cs1 {
			if cs1[i] < cs2[i] {
				return false
			}
		}
		return true
	}
	return check(cs1, cs2) || check(cs2, cs1)
}
```

#### TypeScript

```ts
function checkIfCanBreak(s1: string, s2: string): boolean {
    const cs1: string[] = Array.from(s1);
    const cs2: string[] = Array.from(s2);
    cs1.sort();
    cs2.sort();
    const check = (cs1: string[], cs2: string[]) => {
        for (let i = 0; i < cs1.length; i++) {
            if (cs1[i] < cs2[i]) {
                return false;
            }
        }
        return true;
    };
    return check(cs1, cs2) || check(cs2, cs1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
