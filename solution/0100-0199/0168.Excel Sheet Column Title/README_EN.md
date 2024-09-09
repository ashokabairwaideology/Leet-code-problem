---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0168.Excel%20Sheet%20Column%20Title/README_EN.md
tags:
    - Math
    - String
---

<!-- problem:start -->

# [168. Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title)

[中文文档](/solution/0100-0199/0168.Excel%20Sheet%20Column%20Title/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>columnNumber</code>, return <em>its corresponding column title as it appears in an Excel sheet</em>.</p>

<p>For example:</p>

<pre>
A -&gt; 1
B -&gt; 2
C -&gt; 3
...
Z -&gt; 26
AA -&gt; 27
AB -&gt; 28 
...
</pre>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> columnNumber = 1
<strong>Output:</strong> &quot;A&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> columnNumber = 28
<strong>Output:</strong> &quot;AB&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> columnNumber = 701
<strong>Output:</strong> &quot;ZY&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= columnNumber &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def convertToTitle(self, columnNumber: int) -> str:
        res = []
        while columnNumber:
            columnNumber -= 1
            res.append(chr(ord('A') + columnNumber % 26))
            columnNumber //= 26
        return ''.join(res[::-1])
```

#### Java

```java
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber != 0) {
            --columnNumber;
            res.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return res.reverse().toString();
    }
}
```

#### Go

```go
func convertToTitle(columnNumber int) string {
	res := []rune{}
	for columnNumber != 0 {
		columnNumber -= 1
		res = append([]rune{rune(columnNumber%26 + int('A'))}, res...)
		columnNumber /= 26
	}
	return string(res)
}
```

#### TypeScript

```ts
function convertToTitle(columnNumber: number): string {
    let res: string[] = [];
    while (columnNumber > 0) {
        --columnNumber;
        let num: number = columnNumber % 26;
        res.unshift(String.fromCharCode(num + 65));
        columnNumber = Math.floor(columnNumber / 26);
    }
    return res.join('');
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn convert_to_title(column_number: i32) -> String {
        let mut ret = String::from("");
        let mut column_number = column_number;

        while column_number > 0 {
            if column_number <= 26 {
                ret.push((('A' as u8) + (column_number as u8) - 1) as char);
                break;
            } else {
                let mut left = column_number % 26;
                left = if left == 0 { 26 } else { left };
                ret.push((('A' as u8) + (left as u8) - 1) as char);
                column_number = (column_number - 1) / 26;
            }
        }

        ret.chars().rev().collect()
    }
}
```

#### C#

```cs
public class Solution {
    public string ConvertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber != 0) {
            --columnNumber;
            res.Append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return new string(res.ToString().Reverse().ToArray());
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
