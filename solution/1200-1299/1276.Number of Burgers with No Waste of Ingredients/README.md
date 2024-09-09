---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1276.Number%20of%20Burgers%20with%20No%20Waste%20of%20Ingredients/README.md
rating: 1386
source: 第 165 场周赛 Q2
tags:
    - 数学
---

<!-- problem:start -->

# [1276. 不浪费原料的汉堡制作方案](https://leetcode.cn/problems/number-of-burgers-with-no-waste-of-ingredients)

[English Version](/solution/1200-1299/1276.Number%20of%20Burgers%20with%20No%20Waste%20of%20Ingredients/README_EN.md)

## 题目描述

<!-- description:start -->

<p>圣诞活动预热开始啦，汉堡店推出了全新的汉堡套餐。为了避免浪费原料，请你帮他们制定合适的制作计划。</p>

<p>给你两个整数&nbsp;<code>tomatoSlices</code>&nbsp;和&nbsp;<code>cheeseSlices</code>，分别表示番茄片和奶酪片的数目。不同汉堡的原料搭配如下：</p>

<ul>
	<li><strong>巨无霸汉堡：</strong>4 片番茄和 1 片奶酪</li>
	<li><strong>小皇堡：</strong>2 片番茄和&nbsp;1 片奶酪</li>
</ul>

<p>请你以&nbsp;<code>[total_jumbo, total_small]</code>（[巨无霸汉堡总数，小皇堡总数]）的格式返回恰当的制作方案，使得剩下的番茄片&nbsp;<code>tomatoSlices</code>&nbsp;和奶酪片&nbsp;<code>cheeseSlices</code>&nbsp;的数量都是&nbsp;<code>0</code>。</p>

<p>如果无法使剩下的番茄片&nbsp;<code>tomatoSlices</code>&nbsp;和奶酪片&nbsp;<code>cheeseSlices</code>&nbsp;的数量为&nbsp;<code>0</code>，就请返回&nbsp;<code>[]</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>tomatoSlices = 16, cheeseSlices = 7
<strong>输出：</strong>[1,6]
<strong>解释：</strong>制作 1 个巨无霸汉堡和 6 个小皇堡需要 4*1 + 2*6 = 16 片番茄和 1 + 6 = 7 片奶酪。不会剩下原料。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>tomatoSlices = 17, cheeseSlices = 4
<strong>输出：</strong>[]
<strong>解释：</strong>只制作小皇堡和巨无霸汉堡无法用光全部原料。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>tomatoSlices = 4, cheeseSlices = 17
<strong>输出：</strong>[]
<strong>解释：</strong>制作 1 个巨无霸汉堡会剩下 16 片奶酪，制作 2 个小皇堡会剩下 15 片奶酪。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>tomatoSlices = 0, cheeseSlices = 0
<strong>输出：</strong>[0,0]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>tomatoSlices = 2, cheeseSlices = 1
<strong>输出：</strong>[0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= tomatoSlices &lt;= 10^7</code></li>
	<li><code>0 &lt;= cheeseSlices &lt;= 10^7</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们设巨无霸汉堡数量为 $x$，小皇堡数量为 $y$，则有：

$$
\begin{aligned}
4x + 2y &= tomatoSlices \\
x + y &= cheeseSlices
\end{aligned}
$$

将上述两式转换，可以得到：

$$
\begin{aligned}
y = (4 \times cheeseSlices - tomatoSlices) / 2 \\
x = cheeseSlices - y
\end{aligned}
$$

其中 $x$ 和 $y$ 必须为非负整数。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numOfBurgers(self, tomatoSlices: int, cheeseSlices: int) -> List[int]:
        k = 4 * cheeseSlices - tomatoSlices
        y = k // 2
        x = cheeseSlices - y
        return [] if k % 2 or y < 0 or x < 0 else [x, y]
```

#### Java

```java
class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int k = 4 * cheeseSlices - tomatoSlices;
        int y = k / 2;
        int x = cheeseSlices - y;
        return k % 2 != 0 || y < 0 || x < 0 ? List.of() : List.of(x, y);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int k = 4 * cheeseSlices - tomatoSlices;
        int y = k / 2;
        int x = cheeseSlices - y;
        return k % 2 || x < 0 || y < 0 ? vector<int>{} : vector<int>{x, y};
    }
};
```

#### Go

```go
func numOfBurgers(tomatoSlices int, cheeseSlices int) []int {
	k := 4*cheeseSlices - tomatoSlices
	y := k / 2
	x := cheeseSlices - y
	if k%2 != 0 || x < 0 || y < 0 {
		return []int{}
	}
	return []int{x, y}
}
```

#### TypeScript

```ts
function numOfBurgers(tomatoSlices: number, cheeseSlices: number): number[] {
    const k = 4 * cheeseSlices - tomatoSlices;
    const y = k >> 1;
    const x = cheeseSlices - y;
    return k % 2 || y < 0 || x < 0 ? [] : [x, y];
}
```

#### Rust

```rust
impl Solution {
    pub fn num_of_burgers(tomato_slices: i32, cheese_slices: i32) -> Vec<i32> {
        let k = 4 * cheese_slices - tomato_slices;
        let y = k / 2;
        let x = cheese_slices - y;
        if k % 2 != 0 || y < 0 || x < 0 {
            Vec::new()
        } else {
            vec![x, y]
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
