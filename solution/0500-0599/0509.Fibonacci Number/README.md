---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0509.Fibonacci%20Number/README.md
tags:
    - 递归
    - 记忆化搜索
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [509. 斐波那契数](https://leetcode.cn/problems/fibonacci-number)

[English Version](/solution/0500-0599/0509.Fibonacci%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>斐波那契数</strong>&nbsp;（通常用&nbsp;<code>F(n)</code> 表示）形成的序列称为 <strong>斐波那契数列</strong> 。该数列由&nbsp;<code>0</code> 和 <code>1</code> 开始，后面的每一项数字都是前面两项数字的和。也就是：</p>

<pre>
F(0) = 0，F(1)&nbsp;= 1
F(n) = F(n - 1) + F(n - 2)，其中 n &gt; 1
</pre>

<p>给定&nbsp;<code>n</code> ，请计算 <code>F(n)</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>1
<strong>解释：</strong>F(2) = F(1) + F(0) = 1 + 0 = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>2
<strong>解释：</strong>F(3) = F(2) + F(1) = 1 + 1 = 2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>3
<strong>解释：</strong>F(4) = F(3) + F(2) = 2 + 1 = 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 30</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def fib(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            a, b = b, a + b
        return a
```

#### Java

```java
class Solution {
    public int fib(int n) {
        int a = 0, b = 1;
        while (n-- > 0) {
            int c = a + b;
            a = b;
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
    int fib(int n) {
        int a = 0, b = 1;
        while (n--) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }
};
```

#### Go

```go
func fib(n int) int {
	a, b := 0, 1
	for i := 0; i < n; i++ {
		a, b = b, a+b
	}
	return a
}
```

#### TypeScript

```ts
function fib(n: number): number {
    let a = 0;
    let b = 1;
    for (let i = 0; i < n; i++) {
        [a, b] = [b, a + b];
    }
    return a;
}
```

#### Rust

```rust
impl Solution {
    pub fn fib(n: i32) -> i32 {
        let mut a = 0;
        let mut b = 1;
        for _ in 0..n {
            let t = b;
            b = a + b;
            a = t;
        }
        a
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var fib = function (n) {
    let a = 0;
    let b = 1;
    while (n--) {
        const c = a + b;
        a = b;
        b = c;
    }
    return a;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function fib($n) {
        if ($n == 0 || $n == 1) {
            return $n;
        }
        $dp = [0, 1];
        for ($i = 2; $i <= $n; $i++) {
            $dp[$i] = $dp[$i - 2] + $dp[$i - 1];
        }
        return $dp[$n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### TypeScript

```ts
function fib(n: number): number {
    if (n < 2) {
        return n;
    }
    return fib(n - 1) + fib(n - 2);
}
```

#### Rust

```rust
impl Solution {
    pub fn fib(n: i32) -> i32 {
        if n < 2 {
            return n;
        }
        Self::fib(n - 1) + Self::fib(n - 2)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
