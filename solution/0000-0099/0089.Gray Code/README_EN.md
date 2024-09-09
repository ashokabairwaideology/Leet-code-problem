---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0089.Gray%20Code/README_EN.md
tags:
    - Bit Manipulation
    - Math
    - Backtracking
---

<!-- problem:start -->

# [89. Gray Code](https://leetcode.com/problems/gray-code)

[中文文档](/solution/0000-0099/0089.Gray%20Code/README.md)

## Description

<!-- description:start -->

<p>An <strong>n-bit gray code sequence</strong> is a sequence of <code>2<sup>n</sup></code> integers where:</p>

<ul>
	<li>Every integer is in the <strong>inclusive</strong> range <code>[0, 2<sup>n</sup> - 1]</code>,</li>
	<li>The first integer is <code>0</code>,</li>
	<li>An integer appears <strong>no more than once</strong> in the sequence,</li>
	<li>The binary representation of every pair of <strong>adjacent</strong> integers differs by <strong>exactly one bit</strong>, and</li>
	<li>The binary representation of the <strong>first</strong> and <strong>last</strong> integers differs by <strong>exactly one bit</strong>.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>any valid <strong>n-bit gray code sequence</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> [0,1,3,2]
<strong>Explanation:</strong>
The binary representation of [0,1,3,2] is [00,01,11,10].
- 0<u>0</u> and 0<u>1</u> differ by one bit
- <u>0</u>1 and <u>1</u>1 differ by one bit
- 1<u>1</u> and 1<u>0</u> differ by one bit
- <u>1</u>0 and <u>0</u>0 differ by one bit
[0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
- <u>0</u>0 and <u>1</u>0 differ by one bit
- 1<u>0</u> and 1<u>1</u> differ by one bit
- <u>1</u>1 and <u>0</u>1 differ by one bit
- 0<u>1</u> and 0<u>0</u> differ by one bit
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 16</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary to Gray Code Conversion

Gray code is a type of encoding method that we often encounter in engineering. Its basic feature is that only one bit of binary number is different between any two adjacent codes.

The rule for converting binary code to binary Gray code is to keep the highest bit of the binary code as the highest bit of the Gray code, and the second highest bit of the Gray code is the XOR of the highest bit and the second highest bit of the binary code. The calculation of the remaining bits of the Gray code is similar to the second highest bit.

Assume that a binary number is represented as $B_{n-1}B_{n-2}...B_2B_1B_0$, and its Gray code is represented as $G_{n-1}G_{n-2}...G_2G_1G_0$. The highest bit is retained, so $G_{n-1} = B_{n-1}$; and the other bits $G_i = B_{i+1} \oplus B_{i}$, where $i=0,1,2..,n-2$.

Therefore, for an integer $x$, we can use the function $gray(x)$ to get its Gray code:

```java
int gray(x) {
    return x ^ (x >> 1);
}
```

We directly map the integers $[0,..2^n - 1]$ to the corresponding Gray codes to get the answer array.

The time complexity is $O(2^n)$, where $n$ is the integer given in the problem. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def grayCode(self, n: int) -> List[int]:
        return [i ^ (i >> 1) for i in range(1 << n)]
```

#### Java

```java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> ans;
        for (int i = 0; i < 1 << n; ++i) {
            ans.push_back(i ^ (i >> 1));
        }
        return ans;
    }
};
```

#### Go

```go
func grayCode(n int) (ans []int) {
	for i := 0; i < 1<<n; i++ {
		ans = append(ans, i^(i>>1))
	}
	return
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var grayCode = function (n) {
    const ans = [];
    for (let i = 0; i < 1 << n; ++i) {
        ans.push(i ^ (i >> 1));
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
