---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1611.Minimum%20One%20Bit%20Operations%20to%20Make%20Integers%20Zero/README.md
rating: 2345
source: 第 209 场周赛 Q4
tags:
    - 位运算
    - 记忆化搜索
    - 动态规划
---

<!-- problem:start -->

# [1611. 使整数变为 0 的最少操作次数](https://leetcode.cn/problems/minimum-one-bit-operations-to-make-integers-zero)

[English Version](/solution/1600-1699/1611.Minimum%20One%20Bit%20Operations%20to%20Make%20Integers%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，你需要重复执行多次下述操作将其转换为 <code>0</code> ：</p>

<ul>
	<li>翻转 <code>n</code> 的二进制表示中最右侧位（第 <code>0</code> 位）。</li>
	<li>如果第 <code>(i-1)</code> 位为 <code>1</code> 且从第 <code>(i-2)</code> 位到第 <code>0</code> 位都为 <code>0</code>，则翻转 <code>n</code> 的二进制表示中的第 <code>i</code> 位。</li>
</ul>

<p>返回将 <code>n</code> 转换为 <code>0</code> 的最小操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>2
<strong>解释：</strong>3 的二进制表示为 "11"
"<strong>1</strong>1" -&gt; "<strong>0</strong>1" ，执行的是第 2 种操作，因为第 0 位为 1 。
"0<strong>1</strong>" -&gt; "0<strong>0</strong>" ，执行的是第 1 种操作。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 6
<strong>输出：</strong>4
<strong>解释：</strong>6 的二进制表示为 "110".
"<strong>1</strong>10" -&gt; "<strong>0</strong>10" ，执行的是第 2 种操作，因为第 1 位为 1 ，第 0 到 0 位为 0 。
"01<strong>0</strong>" -&gt; "01<strong>1</strong>" ，执行的是第 1 种操作。
"0<strong>1</strong>1" -&gt; "0<strong>0</strong>1" ，执行的是第 2 种操作，因为第 0 位为 1 。
"00<strong>1</strong>" -&gt; "00<strong>0</strong>" ，执行的是第 1 种操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：格雷码逆变换（格雷码转二进制码）

本题实际上求的是格雷码为 $n$ 的逆变换，即通过格雷码构造原数。

我们先来回顾一下二进制码转换成二进制格雷码，其法则是保留二进制码的最高位作为格雷码的最高位，而次高位格雷码为二进制码的高位与次高位相异或，而格雷码其余各位与次高位的求法相类似。

假设某个二进制数表示为 $B_{n-1}B_{n-2}...B_2B_1B_0$，其格雷码表示为 $G_{n-1}G_{n-2}...G_2G_1G_0$。最高位保留，所以 $G_{n-1} = B_{n-1}$；而其它各位 $G_i = B_{i+1} \oplus B_{i}$，其中 $i=0,1,2..,n-2$。

那么，格雷码转换成二进制码的逆变换是什么呢？

我们可以发现，格雷码的最高位保留，所以 $B_{n-1} = G_{n-1}$；而 $B_{n-2} = G_{n-2} \oplus B_{n-1} = G_{n-2} \oplus G_{n-1}$；而其它各位 $B_i = G_{i} \oplus G_{i+1} \cdots \oplus G_{n-1}$，其中 $i=0,1,2..,n-2$。因此，我们可以用下面的函数 $rev(x)$ 得到其二进制码：

```java
int rev(int x) {
    int n = 0;
    for (; x != 0; x >>= 1) {
        n ^= x;
    }
    return n;
}
```

时间复杂度 $O(\log n)$，其中 $n$ 为题目给定的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOneBitOperations(self, n: int) -> int:
        ans = 0
        while n:
            ans ^= n
            n >>= 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumOneBitOperations(int n) {
        int ans = 0;
        for (; n > 0; n >>= 1) {
            ans ^= n;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOneBitOperations(int n) {
        int ans = 0;
        for (; n > 0; n >>= 1) {
            ans ^= n;
        }
        return ans;
    }
};
```

#### Go

```go
func minimumOneBitOperations(n int) (ans int) {
	for ; n > 0; n >>= 1 {
		ans ^= n
	}
	return
}
```

#### TypeScript

```ts
function minimumOneBitOperations(n: number): number {
    let ans = 0;
    for (; n > 0; n >>= 1) {
        ans ^= n;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOneBitOperations(self, n: int) -> int:
        if n == 0:
            return 0
        return n ^ self.minimumOneBitOperations(n >> 1)
```

#### Java

```java
class Solution {
    public int minimumOneBitOperations(int n) {
        if (n == 0) {
            return 0;
        }
        return n ^ minimumOneBitOperations(n >> 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOneBitOperations(int n) {
        if (n == 0) {
            return 0;
        }
        return n ^ minimumOneBitOperations(n >> 1);
    }
};
```

#### Go

```go
func minimumOneBitOperations(n int) int {
	if n == 0 {
		return 0
	}
	return n ^ minimumOneBitOperations(n>>1)
}
```

#### TypeScript

```ts
function minimumOneBitOperations(n: number): number {
    if (n === 0) {
        return 0;
    }
    return n ^ minimumOneBitOperations(n >> 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
