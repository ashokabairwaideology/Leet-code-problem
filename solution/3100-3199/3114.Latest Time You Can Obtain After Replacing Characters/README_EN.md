---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3114.Latest%20Time%20You%20Can%20Obtain%20After%20Replacing%20Characters/README_EN.md
rating: 1290
source: Weekly Contest 393 Q1
tags:
    - String
    - Enumeration
---

<!-- problem:start -->

# [3114. Latest Time You Can Obtain After Replacing Characters](https://leetcode.com/problems/latest-time-you-can-obtain-after-replacing-characters)

[中文文档](/solution/3100-3199/3114.Latest%20Time%20You%20Can%20Obtain%20After%20Replacing%20Characters/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> representing a 12-hour format time where some of the digits (possibly none) are replaced with a <code>&quot;?&quot;</code>.</p>

<p>12-hour times are formatted as <code>&quot;HH:MM&quot;</code>, where <code>HH</code> is between <code>00</code> and <code>11</code>, and <code>MM</code> is between <code>00</code> and <code>59</code>. The earliest 12-hour time is <code>00:00</code>, and the latest is <code>11:59</code>.</p>

<p>You have to replace <strong>all</strong> the <code>&quot;?&quot;</code> characters in <code>s</code> with digits such that the time we obtain by the resulting string is a <strong>valid</strong> 12-hour format time and is the <strong>latest</strong> possible.</p>

<p>Return <em>the resulting string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1?:?4&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;11:54&quot;</span></p>

<p><strong>Explanation:</strong> The latest 12-hour format time we can achieve by replacing <code>&quot;?&quot;</code> characters is <code>&quot;11:54&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0?:5?&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;09:59&quot;</span></p>

<p><strong>Explanation:</strong> The latest 12-hour format time we can achieve by replacing <code>&quot;?&quot;</code> characters is <code>&quot;09:59&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s.length == 5</code></li>
	<li><code>s[2]</code> is equal to the character <code>&quot;:&quot;</code>.</li>
	<li>All characters except <code>s[2]</code> are digits or <code>&quot;?&quot;</code> characters.</li>
	<li>The input is generated such that there is <strong>at least</strong> one time between <code>&quot;00:00&quot;</code> and <code>&quot;11:59&quot;</code> that you can obtain after replacing the <code>&quot;?&quot;</code> characters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate all times from large to small, where the hour $h$ ranges from $11$ to $0$, and the minute $m$ ranges from $59$ to $0$. For each time $t$, we check whether each digit of $t$ matches the corresponding digit in $s$ (if the corresponding digit in $s$ is not "?"). If it does, then we have found the answer and return $t$.

The time complexity is $O(h \times m)$, where $h = 12$ and $m = 60$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLatestTime(self, s: str) -> str:
        for h in range(11, -1, -1):
            for m in range(59, -1, -1):
                t = f"{h:02d}:{m:02d}"
                if all(a == b for a, b in zip(s, t) if a != "?"):
                    return t
```

#### Java

```java
class Solution {
    public String findLatestTime(String s) {
        for (int h = 11;; h--) {
            for (int m = 59; m >= 0; m--) {
                String t = String.format("%02d:%02d", h, m);
                boolean ok = true;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) != '?' && s.charAt(i) != t.charAt(i)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    return t;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findLatestTime(string s) {
        for (int h = 11;; h--) {
            for (int m = 59; m >= 0; m--) {
                char t[6];
                sprintf(t, "%02d:%02d", h, m);
                bool ok = true;
                for (int i = 0; i < s.length(); i++) {
                    if (s[i] != '?' && s[i] != t[i]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    return t;
                }
            }
        }
    }
};
```

#### Go

```go
func findLatestTime(s string) string {
	for h := 11; ; h-- {
		for m := 59; m >= 0; m-- {
			t := fmt.Sprintf("%02d:%02d", h, m)
			ok := true
			for i := 0; i < len(s); i++ {
				if s[i] != '?' && s[i] != t[i] {
					ok = false
					break
				}
			}
			if ok {
				return t
			}
		}
	}
}
```

#### TypeScript

```ts
function findLatestTime(s: string): string {
    for (let h = 11; ; h--) {
        for (let m = 59; m >= 0; m--) {
            const t: string = `${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}`;
            let ok: boolean = true;
            for (let i = 0; i < s.length; i++) {
                if (s[i] !== '?' && s[i] !== t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return t;
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Judge Each Digit

We can judge each digit of $s$ one by one. If it is "?", we determine the value of this digit based on the characters before and after it. Specifically, we have the following rules:

-   If $s[0]$ is "?", then the value of $s[0]$ should be "1" or "0", depending on the value of $s[1]$. If $s[1]$ is "?" or $s[1]$ is less than "2", then the value of $s[0]$ should be "1", otherwise the value of $s[0]$ should be "0".
-   If $s[1]$ is "?", then the value of $s[1]$ should be "1" or "9", depending on the value of $s[0]$. If $s[0]$ is "1", then the value of $s[1]$ should be "1", otherwise the value of $s[1]$ should be "9".
-   If $s[3]$ is "?", then the value of $s[3]$ should be "5".
-   If $s[4]$ is "?", then the value of $s[4]$ should be "9".

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLatestTime(self, s: str) -> str:
        s = list(s)
        if s[0] == "?":
            s[0] = "1" if s[1] == "?" or s[1] < "2" else "0"
        if s[1] == "?":
            s[1] = "1" if s[0] == "1" else "9"
        if s[3] == "?":
            s[3] = "5"
        if s[4] == "?":
            s[4] = "9"
        return "".join(s)
```

#### Java

```java
class Solution {
    public String findLatestTime(String s) {
        char[] cs = s.toCharArray();
        if (cs[0] == '?') {
            cs[0] = cs[1] == '?' || cs[1] < '2' ? '1' : '0';
        }
        if (cs[1] == '?') {
            cs[1] = cs[0] == '1' ? '1' : '9';
        }
        if (cs[3] == '?') {
            cs[3] = '5';
        }
        if (cs[4] == '?') {
            cs[4] = '9';
        }
        return new String(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findLatestTime(string s) {
        if (s[0] == '?') {
            s[0] = s[1] == '?' || s[1] < '2' ? '1' : '0';
        }
        if (s[1] == '?') {
            s[1] = s[0] == '1' ? '1' : '9';
        }
        if (s[3] == '?') {
            s[3] = '5';
        }
        if (s[4] == '?') {
            s[4] = '9';
        }
        return s;
    }
};
```

#### Go

```go
func findLatestTime(s string) string {
	cs := []byte(s)
	if cs[0] == '?' {
		if cs[1] == '?' || cs[1] < '2' {
			cs[0] = '1'
		} else {
			cs[0] = '0'
		}
	}
	if cs[1] == '?' {
		if cs[0] == '1' {
			cs[1] = '1'
		} else {
			cs[1] = '9'
		}
	}
	if cs[3] == '?' {
		cs[3] = '5'
	}
	if cs[4] == '?' {
		cs[4] = '9'
	}
	return string(cs)
}
```

#### TypeScript

```ts
function findLatestTime(s: string): string {
    const cs = s.split('');
    if (cs[0] === '?') {
        cs[0] = cs[1] === '?' || cs[1] < '2' ? '1' : '0';
    }
    if (cs[1] === '?') {
        cs[1] = cs[0] === '1' ? '1' : '9';
    }
    if (cs[3] === '?') {
        cs[3] = '5';
    }
    if (cs[4] === '?') {
        cs[4] = '9';
    }
    return cs.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
