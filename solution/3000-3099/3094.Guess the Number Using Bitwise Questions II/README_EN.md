---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3094.Guess%20the%20Number%20Using%20Bitwise%20Questions%20II/README_EN.md
tags:
    - Bit Manipulation
    - Interactive
---

<!-- problem:start -->

# [3094. Guess the Number Using Bitwise Questions II 🔒](https://leetcode.com/problems/guess-the-number-using-bitwise-questions-ii)

[中文文档](/solution/3000-3099/3094.Guess%20the%20Number%20Using%20Bitwise%20Questions%20II/README.md)

## Description

<!-- description:start -->

<p>There is a number <code>n</code> between <code>0</code> and <code>2<sup>30</sup> - 1</code> (both inclusive) that you have to find.</p>

<p>There is a pre-defined API <code>int commonBits(int num)</code> that helps you with your mission. But here is the challenge, every time you call this function, <code>n</code> changes in some way. But keep in mind, that you have to find the <strong>initial value of </strong><code>n</code>.</p>

<p><code>commonBits(int num)</code> acts as follows:</p>

<ul>
	<li>Calculate <code>count</code> which is the number of bits where both <code>n</code> and <code>num</code> have the same value in that position of their binary representation.</li>
	<li><code>n = n XOR num</code></li>
	<li>Return <code>count</code>.</li>
</ul>

<p>Return <em>the number</em> <code>n</code>.</p>

<p><strong>Note:</strong> In this world, all numbers are between <code>0</code> and <code>2<sup>30</sup> - 1</code> (both inclusive), thus for counting common bits, we see only the first 30 bits of those numbers.</p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 2<sup>30</sup> - 1</code></li>
	<li><code>0 &lt;= num &lt;= 2<sup>30</sup> - 1</code></li>
	<li>If you ask for some <code>num</code> out of the given range, the output wouldn&#39;t be reliable.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

Based on the problem description, we observe that:

-   If we call the `commonBits` function twice with the same number, the value of $n$ will not change.
-   If we call `commonBits(1 << i)`, the $i$-th bit of $n$ will be flipped, i.e., if the $i$-th bit of $n$ is $1$, it will become $0$ after the call, and vice versa.

Therefore, for each bit $i$, we can call `commonBits(1 << i)` twice, denoted as `count1` and `count2` respectively. If `count1 > count2`, it means the $i$-th bit of $n$ is $1$, otherwise it is $0$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition of commonBits API.
# def commonBits(num: int) -> int:


class Solution:
    def findNumber(self) -> int:
        n = 0
        for i in range(32):
            count1 = commonBits(1 << i)
            count2 = commonBits(1 << i)
            if count1 > count2:
                n |= 1 << i
        return n
```

#### Java

```java
/**
 * Definition of commonBits API (defined in the parent class Problem).
 * int commonBits(int num);
 */

public class Solution extends Problem {
    public int findNumber() {
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            int count1 = commonBits(1 << i);
            int count2 = commonBits(1 << i);
            if (count1 > count2) {
                n |= 1 << i;
            }
        }
        return n;
    }
}
```

#### C++

```cpp
/**
 * Definition of commonBits API.
 * int commonBits(int num);
 */

class Solution {
public:
    int findNumber() {
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            int count1 = commonBits(1 << i);
            int count2 = commonBits(1 << i);
            if (count1 > count2) {
                n |= 1 << i;
            }
        }
        return n;
    }
};
```

#### Go

```go
/**
 * Definition of commonBits API.
 * func commonBits(num int) int;
 */

func findNumber() (n int) {
	for i := 0; i < 32; i++ {
		count1 := commonBits(1 << i)
		count2 := commonBits(1 << i)
		if count1 > count2 {
			n |= 1 << i
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
