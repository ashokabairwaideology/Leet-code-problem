---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9865.%20%E4%B8%8D%E7%94%A8%E5%8A%A0%E5%87%8F%E4%B9%98%E9%99%A4%E5%81%9A%E5%8A%A0%E6%B3%95/README.md
---

<!-- problem:start -->

# [面试题 65. 不用加减乘除做加法](https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/)

## 题目描述

<!-- description:start -->

<p>写一个函数，求两个整数之和，要求在函数体内不得使用 &ldquo;+&rdquo;、&ldquo;-&rdquo;、&ldquo;*&rdquo;、&ldquo;/&rdquo; 四则运算符号。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> a = 1, b = 1
<strong>输出:</strong> 2</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>a</code>,&nbsp;<code>b</code>&nbsp;均可能是负数或 0</li>
	<li>结果不会溢出 32 位整数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

两数字 $a$, $b$ 求和。

假设 $a_i$ 和 $b_i$ 分别表示 $a$ 和 $b$ 的第 $i$ 个二进制位。一共有 $4$ 种情况：

| $a_i$ | $b_i$ | 不进位的和 | 进位 |
| ----- | ----- | ---------- | ---- |
| 0     | 0     | 0          | 0    |
| 0     | 1     | 1          | 0    |
| 1     | 0     | 1          | 0    |
| 1     | 1     | 0          | 1    |

观察可以发现，“不进位的和”与“异或运算”有相同规律，而进位则与“与”运算规律相同，并且需要左移一位。

-   对两数进行按位 `&` 与运算，然后左移一位，得到进位；
-   对两数进行按位 `^` 异或运算，得到不进位的和；
-   问题转换为求：“不进位的数 + 进位” 之和；
-   循环，直至进位为 $0$，返回不进位的数即可（也可以用递归实现）。

时间复杂度 $O(\log M)$，空间复杂度 $O(1)$。其中 $M$ 为整数的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def add(self, a: int, b: int) -> int:
        a, b = a & 0xFFFFFFFF, b & 0xFFFFFFFF
        while b:
            c = ((a & b) << 1) & 0xFFFFFFFF
            a, b = a ^ b, c
        return a if a < 0x80000000 else ~(a ^ 0xFFFFFFFF)
```

#### Java

```java
class Solution {
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int add(int a, int b) {
        while (b) {
            unsigned int c = (unsigned int) (a & b) << 1;
            a = a ^ b;
            b = c;
        }
        return a;
    }
};
```

#### Go

```go
func add(a int, b int) int {
	if b == 0 {
		return a
	}
	return add(a^b, (a&b)<<1)
}
```

#### TypeScript

```ts
function add(a: number, b: number): number {
    while (b) {
        const c = (a & b) << 1;
        a ^= b;
        b = c;
    }
    return a;
}
```

#### JavaScript

```js
/**
 * @param {number} a
 * @param {number} b
 * @return {number}
 */
var add = function (a, b) {
    if (b == 0) {
        return a;
    }
    return add(a ^ b, (a & b) << 1);
};
```

#### C#

```cs
public class Solution {
    public int Add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a = a ^ b;
            b = c;
        }

        return a;
    }
}
```

#### Swift

```swift
class Solution {
    func add(_ a: Int, _ b: Int) -> Int {
        var a = a
        var b = b
        while b != 0 {
            let c = (a & b) << 1
            a ^= b
            b = c
        }
        return a
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int add(int a, int b) {
        if (b == 0) {
            return a;
        }
        return add(a ^ b, (a & b) << 1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
