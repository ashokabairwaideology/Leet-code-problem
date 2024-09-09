---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2485.Find%20the%20Pivot%20Integer/README.md
rating: 1207
source: 第 321 场周赛 Q1
tags:
    - 数学
    - 前缀和
---

<!-- problem:start -->

# [2485. 找出中枢整数](https://leetcode.cn/problems/find-the-pivot-integer)

[English Version](/solution/2400-2499/2485.Find%20the%20Pivot%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>n</code> ，找出满足下述条件的<strong> 中枢整数</strong> <code>x</code> ：</p>

<ul>
	<li><code>1</code> 和 <code>x</code> 之间的所有元素之和等于 <code>x</code> 和 <code>n</code> 之间所有元素之和。</li>
</ul>

<p>返回中枢整数<em> </em><code>x</code> 。如果不存在中枢整数，则返回 <code>-1</code> 。题目保证对于给定的输入，至多存在一个中枢整数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 8
<strong>输出：</strong>6
<strong>解释：</strong>6 是中枢整数，因为 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
<strong>解释：</strong>1 是中枢整数，因为 1 = 1 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>-1
<strong>解释：</strong>可以证明不存在满足题目要求的整数。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以直接在 $[1,..n]$ 的范围内枚举 $x$，判断以下等式是否成立。若成立，则 $x$ 为中枢整数，直接返回 $x$ 即可。

$$
(1 + x) \times x = (x + n) \times (n - x + 1)
$$

时间复杂度 $O(n)$，其中 $n$ 为给定的正整数 $n$。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pivotInteger(self, n: int) -> int:
        for x in range(1, n + 1):
            if (1 + x) * x == (x + n) * (n - x + 1):
                return x
        return -1
```

#### Java

```java
class Solution {
    public int pivotInteger(int n) {
        for (int x = 1; x <= n; ++x) {
            if ((1 + x) * x == (x + n) * (n - x + 1)) {
                return x;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int pivotInteger(int n) {
        for (int x = 1; x <= n; ++x) {
            if ((1 + x) * x == (x + n) * (n - x + 1)) {
                return x;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func pivotInteger(n int) int {
	for x := 1; x <= n; x++ {
		if (1+x)*x == (x+n)*(n-x+1) {
			return x
		}
	}
	return -1
}
```

#### TypeScript

```ts
function pivotInteger(n: number): number {
    for (let x = 1; x <= n; ++x) {
        if ((1 + x) * x === (x + n) * (n - x + 1)) {
            return x;
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn pivot_integer(n: i32) -> i32 {
        let y = (n * (n + 1)) / 2;
        let x = (y as f64).sqrt() as i32;

        if x * x == y {
            return x;
        }

        -1
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function pivotInteger($n) {
        $sum = ($n * ($n + 1)) / 2;
        $pre = 0;
        for ($i = 1; $i <= $n; $i++) {
            if ($pre + $i === $sum - $pre) {
                return $i;
            }
            $pre += $i;
        }
        return -1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：数学

我们可以将上述等式进行变形，得到：

$$
n \times (n + 1) = 2 \times x^2
$$

即：

$$
x = \sqrt{\frac{n \times (n + 1)}{2}}
$$

如果 $x$ 为整数，则 $x$ 为中枢整数，否则不存在中枢整数。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pivotInteger(self, n: int) -> int:
        y = n * (n + 1) // 2
        x = int(sqrt(y))
        return x if x * x == y else -1
```

#### Java

```java
class Solution {
    public int pivotInteger(int n) {
        int y = n * (n + 1) / 2;
        int x = (int) Math.sqrt(y);
        return x * x == y ? x : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int pivotInteger(int n) {
        int y = n * (n + 1) / 2;
        int x = sqrt(y);
        return x * x == y ? x : -1;
    }
};
```

#### Go

```go
func pivotInteger(n int) int {
	y := n * (n + 1) / 2
	x := int(math.Sqrt(float64(y)))
	if x*x == y {
		return x
	}
	return -1
}
```

#### TypeScript

```ts
function pivotInteger(n: number): number {
    const y = Math.floor((n * (n + 1)) / 2);
    const x = Math.floor(Math.sqrt(y));
    return x * x === y ? x : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
