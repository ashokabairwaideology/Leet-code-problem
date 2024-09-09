---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9810-%20II.%20%E9%9D%92%E8%9B%99%E8%B7%B3%E5%8F%B0%E9%98%B6%E9%97%AE%E9%A2%98/README.md
---

<!-- problem:start -->

# [面试题 10- II. 青蛙跳台阶问题](https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

## 题目描述

<!-- description:start -->

<p>一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 <code>n</code>&nbsp;级的台阶总共有多少种跳法。</p>

<p>答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 7
<strong>输出：</strong>21
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 0
<strong>输出：</strong>1</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 100</code></li>
</ul>

<p>注意：本题与主站 70 题相同：<a href="https://leetcode.cn/problems/climbing-stairs/">https://leetcode.cn/problems/climbing-stairs/</a></p>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递推

青蛙想上第 $n$ 级台阶，可从第 $n-1$ 级台阶跳一级上去，也可从第 $n-2$ 级台阶跳两级上去，即 $f(n) = f(n-1) + f(n-2)$。这实际上可以转换为斐波那契数列的问题。

我们定义初始项 $a=1$, $b=1$，接下来执行 $n$ 次循环，每次循环中，计算 $c=a+b$，并更新 $a=b$, $b=c$，循环 $n$ 次后，答案即为 $a$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为输入的整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numWays(self, n: int) -> int:
        a = b = 1
        for _ in range(n):
            a, b = b, (a + b) % 1000000007
        return a
```

#### Java

```java
class Solution {
    public int numWays(int n) {
        int a = 1, b = 1;
        while (n-- > 0) {
            int c = (a + b) % 1000000007;
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
    int numWays(int n) {
        int a = 1, b = 1;
        while (n--) {
            int c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return a;
    }
};
```

#### Go

```go
func numWays(n int) int {
	a, b := 1, 1
	for i := 0; i < n; i++ {
		a, b = b, (a+b)%1000000007
	}
	return a
}
```

#### TypeScript

```ts
function numWays(n: number): number {
    let a = 0;
    let b = 1;
    for (let i = 0; i < n; i++) {
        [a, b] = [b, (a + b) % 1000000007];
    }
    return b;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_ways(n: i32) -> i32 {
        let mut tup = (0, 1);
        for _ in 0..n {
            tup = (tup.1, (tup.0 + tup.1) % 1000000007);
        }
        tup.1
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var numWays = function (n) {
    let a = (b = 1);
    while (n--) {
        [a, b] = [b, (a + b) % (1e9 + 7)];
    }
    return a;
};
```

#### C#

```cs
public class Solution {
    public int NumWays(int n) {
        int a = 1, b = 1, tmp;
        for (int i = 0; i < n; i++) {
            tmp = a;
            a = b;
            b = (tmp + b) % 1000000007;
        }
        return a % 1000000007;
    }
}
```

#### Swift

```swift
class Solution {
    func numWays(_ n: Int) -> Int {
        var a = 1
        var b = 1
        var count = n
        while count > 0 {
            let c = (a + b) % 1000000007
            a = b
            b = c
            count -= 1
        }
        return a
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
