---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2264.Largest%203-Same-Digit%20Number%20in%20String/README_EN.md
rating: 1308
source: Weekly Contest 292 Q1
tags:
    - String
---

<!-- problem:start -->

# [2264. Largest 3-Same-Digit Number in String](https://leetcode.com/problems/largest-3-same-digit-number-in-string)

[中文文档](/solution/2200-2299/2264.Largest%203-Same-Digit%20Number%20in%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>num</code> representing a large integer. An integer is <strong>good</strong> if it meets the following conditions:</p>

<ul>
	<li>It is a <strong>substring</strong> of <code>num</code> with length <code>3</code>.</li>
	<li>It consists of only one unique digit.</li>
</ul>

<p>Return <em>the <strong>maximum good </strong>integer as a <strong>string</strong> or an empty string </em><code>&quot;&quot;</code><em> if no such integer exists</em>.</p>

<p>Note:</p>

<ul>
	<li>A <strong>substring</strong> is a contiguous sequence of characters within a string.</li>
	<li>There may be <strong>leading zeroes</strong> in <code>num</code> or a good integer.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;6<strong><u>777</u></strong>133339&quot;
<strong>Output:</strong> &quot;777&quot;
<strong>Explanation:</strong> There are two distinct good integers: &quot;777&quot; and &quot;333&quot;.
&quot;777&quot; is the largest, so we return &quot;777&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;23<strong><u>000</u></strong>19&quot;
<strong>Output:</strong> &quot;000&quot;
<strong>Explanation:</strong> &quot;000&quot; is the only good integer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;42352338&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= num.length &lt;= 1000</code></li>
	<li><code>num</code> only consists of digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate each digit $i$ from large to small, where $0 \le i \le 9$, and then check whether the string $s$ consisting of three consecutive $i$ is a substring of $num$. If it is, we directly return $s$.

If we have enumerated all the possible values of $i$ and still haven't found a substring that satisfies the condition, we return an empty string.

The time complexity is $O(10 \times n)$, where $n$ is the length of the string $num$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestGoodInteger(self, num: str) -> str:
        for i in range(9, -1, -1):
            if (s := str(i) * 3) in num:
                return s
        return ""
```

#### Java

```java
class Solution {
    public String largestGoodInteger(String num) {
        for (int i = 9; i >= 0; i--) {
            String s = String.valueOf(i).repeat(3);
            if (num.contains(s)) {
                return s;
            }
        }
        return "";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string largestGoodInteger(string num) {
        for (char i = '9'; i >= '0'; --i) {
            string s(3, i);
            if (num.find(s) != string::npos) {
                return s;
            }
        }
        return "";
    }
};
```

#### Go

```go
func largestGoodInteger(num string) string {
	for c := '9'; c >= '0'; c-- {
		if s := strings.Repeat(string(c), 3); strings.Contains(num, s) {
			return s
		}
	}
	return ""
}
```

#### TypeScript

```ts
function largestGoodInteger(num: string): string {
    for (let i = 9; i >= 0; i--) {
        const s = String(i).repeat(3);
        if (num.includes(s)) {
            return s;
        }
    }
    return '';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
