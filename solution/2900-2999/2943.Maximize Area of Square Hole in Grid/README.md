---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/README.md
rating: 1677
source: 第 118 场双周赛 Q2
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [2943. 最大化网格图中正方形空洞的面积](https://leetcode.cn/problems/maximize-area-of-square-hole-in-grid)

[English Version](/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个网格图，由&nbsp;<code>n + 2</code>&nbsp;条 <strong>横线段</strong>&nbsp;和&nbsp;<code>m + 2</code>&nbsp;条&nbsp;<strong>竖线段</strong>&nbsp;组成，一开始所有区域均为&nbsp;<code>1 x 1</code>&nbsp;的单元格。</p>

<p>所有线段的编号从 <strong>1</strong>&nbsp;开始。</p>

<p>给你两个整数&nbsp;<code>n</code> 和&nbsp;<code>m</code>&nbsp;。</p>

<p>同时给你两个整数数组&nbsp;<code>hBars</code> 和&nbsp;<code>vBars</code>&nbsp;。</p>

<ul>
	<li><code>hBars</code> 包含区间&nbsp;<code>[2, n + 1]</code>&nbsp;内&nbsp;<strong>互不相同</strong>&nbsp;的横线段编号。</li>
	<li><code>vBars</code>&nbsp;包含&nbsp;<code>[2, m + 1]</code>&nbsp;内&nbsp;<strong>互不相同的</strong>&nbsp;竖线段编号。</li>
</ul>

<p>如果满足以下条件之一，你可以 <strong>移除</strong>&nbsp;两个数组中的部分线段：</p>

<ul>
	<li>如果移除的是横线段，它必须是&nbsp;<code>hBars</code>&nbsp;中的值。</li>
	<li>如果移除的是竖线段，它必须是&nbsp;<code>vBars</code>&nbsp;中的值。</li>
</ul>

<p>请你返回移除一些线段后（<strong>可能不移除任何线段）</strong>，剩余网格图中 <strong>最大正方形</strong>&nbsp;空洞的面积，正方形空洞的意思是正方形 <strong>内部</strong> 不含有任何线段。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-05-22-40-25.png" style="width: 411px; height: 220px;" /></p>

<pre>
<b>输入：</b>n = 2, m = 1, hBars = [2,3], vBars = [2]
<b>输出：</b>4
<b>解释：</b>左边的图是一开始的网格图。
横线编号的范围是区间 [1,4] ，竖线编号的范围是区间 [1,3] 。
可以移除的横线段为 [2,3] ，竖线段为 [2] 。
一种得到最大正方形面积的方法是移除横线段 2 和竖线段 2 。
操作后得到的网格图如右图所示。
正方形空洞面积为 4。
无法得到面积大于 4 的正方形空洞。
所以答案为 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-04-17-01-02.png" style="width: 368px; height: 145px;" /></p>

<pre>
<b>输入：</b>n = 1, m = 1, hBars = [2], vBars = [2]
<b>输出：</b>4
<b>解释：</b>左边的图是一开始的网格图。
横线编号的范围是区间 [1,3] ，竖线编号的范围是区间 [1,3] 。
可以移除的横线段为 [2] ，竖线段为 [2] 。
一种得到最大正方形面积的方法是移除横线段 2 和竖线段 2 。
操作后得到的网格图如右图所示。
正方形空洞面积为 4。
无法得到面积大于 4 的正方形空洞。
所以答案为 4 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-05-22-33-35.png" style="width: 648px; height: 218px;" /></p>

<pre>
<b>输入：</b>n = 2, m = 3, hBars = [2,3], vBars = [2,3,4]
<b>输出：</b>9
<b>解释：</b>左边的图是一开始的网格图。
横线编号的范围是区间 [1,4] ，竖线编号的范围是区间 [1,5] 。
可以移除的横线段为 [2,3] ，竖线段为 [2,3,4] 。
一种得到最大正方形面积的方法是移除横线段 2、3 和竖线段 3、4 。
操作后得到的网格图如右图所示。
正方形空洞面积为 9。
无法得到面积大于 9 的正方形空洞。
所以答案为 9 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= hBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= hBars[i] &lt;= n + 1</code></li>
	<li><code>1 &lt;= vBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= vBars[i] &lt;= m + 1</code></li>
	<li><code>hBars</code>&nbsp;中的值互不相同。</li>
	<li><code>vBars</code> 中的值互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

题目实际上要我们找出数组中最长的连续递增子序列的长度，然后再加上 $1$。

我们定义一个函数 $f(nums)$，表示数组 $nums$ 中最长的连续递增子序列的长度。

对于数组 $nums$，我们先对其进行排序，然后遍历数组，如果当前元素 $nums[i]$ 等于前一个元素 $nums[i - 1]$ 加 $1$，则说明当前元素可以加入到连续递增子序列中，否则，说明当前元素不能加入到连续递增子序列中，我们需要重新开始计算连续递增子序列的长度。最后，我们返回连续递增子序列的长度加 $1$。

我们在求出 $hBars$ 和 $vBars$ 中最长的连续递增子序列的长度之后，我们取两者中的最小值作为正方形的边长，然后再求出正方形的面积即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $hBars$ 或 $vBars$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximizeSquareHoleArea(
        self, n: int, m: int, hBars: List[int], vBars: List[int]
    ) -> int:
        def f(nums: List[int]) -> int:
            nums.sort()
            ans = cnt = 1
            for i in range(1, len(nums)):
                if nums[i] == nums[i - 1] + 1:
                    cnt += 1
                    ans = max(ans, cnt)
                else:
                    cnt = 1
            return ans + 1

        return min(f(hBars), f(vBars)) ** 2
```

#### Java

```java
class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int x = Math.min(f(hBars), f(vBars));
        return x * x;
    }

    private int f(int[] nums) {
        Arrays.sort(nums);
        int ans = 1, cnt = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1] + 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximizeSquareHoleArea(int n, int m, vector<int>& hBars, vector<int>& vBars) {
        auto f = [](vector<int>& nums) {
            int ans = 1, cnt = 1;
            sort(nums.begin(), nums.end());
            for (int i = 1; i < nums.size(); ++i) {
                if (nums[i] == nums[i - 1] + 1) {
                    ans = max(ans, ++cnt);
                } else {
                    cnt = 1;
                }
            }
            return ans + 1;
        };
        int x = min(f(hBars), f(vBars));
        return x * x;
    }
};
```

#### Go

```go
func maximizeSquareHoleArea(n int, m int, hBars []int, vBars []int) int {
	f := func(nums []int) int {
		sort.Ints(nums)
		ans, cnt := 1, 1
		for i, x := range nums[1:] {
			if x == nums[i]+1 {
				cnt++
				ans = max(ans, cnt)
			} else {
				cnt = 1
			}
		}
		return ans + 1
	}
	x := min(f(hBars), f(vBars))
	return x * x
}
```

#### TypeScript

```ts
function maximizeSquareHoleArea(n: number, m: number, hBars: number[], vBars: number[]): number {
    const f = (nums: number[]): number => {
        nums.sort((a, b) => a - b);
        let [ans, cnt] = [1, 1];
        for (let i = 1; i < nums.length; ++i) {
            if (nums[i] === nums[i - 1] + 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans + 1;
    };
    return Math.min(f(hBars), f(vBars)) ** 2;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximize_square_hole_area(n: i32, m: i32, h_bars: Vec<i32>, v_bars: Vec<i32>) -> i32 {
        let f = |nums: &mut Vec<i32>| -> i32 {
            let mut ans = 1;
            let mut cnt = 1;
            nums.sort();
            for i in 1..nums.len() {
                if nums[i] == nums[i - 1] + 1 {
                    cnt += 1;
                    ans = ans.max(cnt);
                } else {
                    cnt = 1;
                }
            }
            ans + 1
        };

        let mut h_bars = h_bars;
        let mut v_bars = v_bars;
        let x = f(&mut h_bars).min(f(&mut v_bars));
        x * x
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
