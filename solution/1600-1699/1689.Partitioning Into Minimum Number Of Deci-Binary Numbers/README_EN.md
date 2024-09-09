---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1689.Partitioning%20Into%20Minimum%20Number%20Of%20Deci-Binary%20Numbers/README_EN.md
rating: 1355
source: Weekly Contest 219 Q2
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [1689. Partitioning Into Minimum Number Of Deci-Binary Numbers](https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers)

[中文文档](/solution/1600-1699/1689.Partitioning%20Into%20Minimum%20Number%20Of%20Deci-Binary%20Numbers/README.md)

## Description

<!-- description:start -->

<p>A decimal number is called <strong>deci-binary</strong> if each of its digits is either <code>0</code> or <code>1</code> without any leading zeros. For example, <code>101</code> and <code>1100</code> are <strong>deci-binary</strong>, while <code>112</code> and <code>3001</code> are not.</p>

<p>Given a string <code>n</code> that represents a positive decimal integer, return <em>the <strong>minimum</strong> number of positive <strong>deci-binary</strong> numbers needed so that they sum up to </em><code>n</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;32&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> 10 + 11 + 11 = 32
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;82734&quot;
<strong>Output:</strong> 8
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;27346209830709182346&quot;
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> consists of only digits.</li>
	<li><code>n</code> does not contain any leading zeros and represents a positive integer.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Quick Thinking

The problem is equivalent to finding the maximum number in the string.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minPartitions(self, n: str) -> int:
        return int(max(n))
```

#### Java

```java
class Solution {
    public int minPartitions(String n) {
        int ans = 0;
        for (int i = 0; i < n.length(); ++i) {
            ans = Math.max(ans, n.charAt(i) - '0');
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minPartitions(string n) {
        int ans = 0;
        for (char& c : n) {
            ans = max(ans, c - '0');
        }
        return ans;
    }
};
```

#### Go

```go
func minPartitions(n string) (ans int) {
	for _, c := range n {
		if t := int(c - '0'); ans < t {
			ans = t
		}
	}
	return
}
```

#### TypeScript

```ts
function minPartitions(n: string): number {
    return Math.max(...n.split('').map(d => parseInt(d)));
}
```

#### Rust

```rust
impl Solution {
    pub fn min_partitions(n: String) -> i32 {
        let mut ans = 0;
        for c in n.as_bytes() {
            ans = ans.max((c - b'0') as i32);
        }
        ans
    }
}
```

#### C

```c
int minPartitions(char* n) {
    int ans = 0;
    for (int i = 0; n[i]; i++) {
        int v = n[i] - '0';
        if (v > ans) {
            ans = v;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
