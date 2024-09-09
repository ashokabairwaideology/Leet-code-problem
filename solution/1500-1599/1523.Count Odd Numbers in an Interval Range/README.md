---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1523.Count%20Odd%20Numbers%20in%20an%20Interval%20Range/README.md
rating: 1209
source: 第 31 场双周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [1523. 在区间范围内统计奇数数目](https://leetcode.cn/problems/count-odd-numbers-in-an-interval-range)

[English Version](/solution/1500-1599/1523.Count%20Odd%20Numbers%20in%20an%20Interval%20Range/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个非负整数&nbsp;<code>low</code> 和&nbsp;<code>high</code>&nbsp;。请你返回<em>&nbsp;</em><code>low</code><em> </em>和<em>&nbsp;</em><code>high</code><em>&nbsp;</em>之间（包括二者）奇数的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>low = 3, high = 7
<strong>输出：</strong>3
<strong>解释：</strong>3 到 7 之间奇数数字为 [3,5,7] 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>low = 8, high = 10
<strong>输出：</strong>1
<strong>解释：</strong>8 到 10 之间奇数数字为 [9] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= low &lt;= high&nbsp;&lt;= 10^9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和思想

`[0, x]` 之间的奇数个数为 `(x + 1) >> 1`，那么 `[low, high]` 之间的奇数个数为 `((high + 1) >> 1) - (low >> 1)`。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOdds(self, low: int, high: int) -> int:
        return ((high + 1) >> 1) - (low >> 1)
```

#### Java

```java
class Solution {
    public int countOdds(int low, int high) {
        return ((high + 1) >> 1) - (low >> 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOdds(int low, int high) {
        return (high + 1 >> 1) - (low >> 1);
    }
};
```

#### Go

```go
func countOdds(low int, high int) int {
	return ((high + 1) >> 1) - (low >> 1)
}
```

#### TypeScript

```ts
function countOdds(low: number, high: number): number {
    return ((high + 1) >> 1) - (low >> 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn count_odds(low: i32, high: i32) -> i32 {
        ((high + 1) >> 1) - (low >> 1)
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $low
     * @param Integer $high
     * @return Integer
     */
    function countOdds($low, $high) {
        return ($high + 1 >> 1) - ($low >> 1);
    }
}
```

#### C

```c
int countOdds(int low, int high) {
    return ((high + 1) >> 1) - (low >> 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
