---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0551.Student%20Attendance%20Record%20I/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [551. Student Attendance Record I](https://leetcode.com/problems/student-attendance-record-i)

[中文文档](/solution/0500-0599/0551.Student%20Attendance%20Record%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> representing an attendance record for a student where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:</p>

<ul>
	<li><code>&#39;A&#39;</code>: Absent.</li>
	<li><code>&#39;L&#39;</code>: Late.</li>
	<li><code>&#39;P&#39;</code>: Present.</li>
</ul>

<p>The student is eligible for an attendance award if they meet <strong>both</strong> of the following criteria:</p>

<ul>
	<li>The student was absent (<code>&#39;A&#39;</code>) for <strong>strictly</strong> fewer than 2 days <strong>total</strong>.</li>
	<li>The student was <strong>never</strong> late (<code>&#39;L&#39;</code>) for 3 or more <strong>consecutive</strong> days.</li>
</ul>

<p>Return <code>true</code><em> if the student is eligible for an attendance award, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;PPALLP&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The student has fewer than 2 absences and was never late 3 or more consecutive days.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;PPALLL&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The student was late 3 consecutive days in the last 3 days, so is not eligible for the award.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;A&#39;</code>, <code>&#39;L&#39;</code>, or <code>&#39;P&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkRecord(self, s: str) -> bool:
        return s.count('A') < 2 and 'LLL' not in s
```

#### Java

```java
class Solution {
    public boolean checkRecord(String s) {
        return s.indexOf("A") == s.lastIndexOf("A") && !s.contains("LLL");
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkRecord(string s) {
        return count(s.begin(), s.end(), 'A') < 2 && s.find("LLL") == string::npos;
    }
};
```

#### Go

```go
func checkRecord(s string) bool {
	return strings.Count(s, "A") < 2 && !strings.Contains(s, "LLL")
}
```

#### TypeScript

```ts
function checkRecord(s: string): boolean {
    return s.indexOf('A') === s.lastIndexOf('A') && s.indexOf('LLL') === -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
