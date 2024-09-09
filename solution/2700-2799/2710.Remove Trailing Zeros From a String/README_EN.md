---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2710.Remove%20Trailing%20Zeros%20From%20a%20String/README_EN.md
rating: 1164
source: Weekly Contest 347 Q1
tags:
    - String
---

<!-- problem:start -->

# [2710. Remove Trailing Zeros From a String](https://leetcode.com/problems/remove-trailing-zeros-from-a-string)

[中文文档](/solution/2700-2799/2710.Remove%20Trailing%20Zeros%20From%20a%20String/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>positive</strong> integer <code>num</code> represented as a string, return <em>the integer </em><code>num</code><em> without trailing zeros as a string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;51230100&quot;
<strong>Output:</strong> &quot;512301&quot;
<strong>Explanation:</strong> Integer &quot;51230100&quot; has 2 trailing zeros, we remove them and return integer &quot;512301&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;123&quot;
<strong>Output:</strong> &quot;123&quot;
<strong>Explanation:</strong> Integer &quot;123&quot; has no trailing zeros, we return integer &quot;123&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 1000</code></li>
	<li><code>num</code> consists&nbsp;of only digits.</li>
	<li><code>num</code> doesn&#39;t&nbsp;have any leading zeros.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We can traverse the string from the end to the beginning, stopping when we encounter the first character that is not `0`. Then, we return the substring from the beginning to this character.

The time complexity is $O(n)$, where $n$ is the length of the string. Ignoring the space consumed by the answer string, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeTrailingZeros(self, num: str) -> str:
        return num.rstrip("0")
```

#### Java

```java
class Solution {
    public String removeTrailingZeros(String num) {
        int i = num.length() - 1;
        while (num.charAt(i) == '0') {
            --i;
        }
        return num.substring(0, i + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string removeTrailingZeros(string num) {
        while (num.back() == '0') {
            num.pop_back();
        }
        return num;
    }
};
```

#### Go

```go
func removeTrailingZeros(num string) string {
	i := len(num) - 1
	for num[i] == '0' {
		i--
	}
	return num[:i+1]
}
```

#### TypeScript

```ts
function removeTrailingZeros(num: string): string {
    let i = num.length - 1;
    while (num[i] === '0') {
        --i;
    }
    return num.substring(0, i + 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn remove_trailing_zeros(num: String) -> String {
        let mut i = num.len() - 1;

        while num.chars().nth(i) == Some('0') {
            i -= 1;
        }

        num[..i + 1].to_string()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
