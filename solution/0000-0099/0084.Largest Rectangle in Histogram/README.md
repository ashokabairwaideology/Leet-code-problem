---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0084.Largest%20Rectangle%20in%20Histogram/README.md
tags:
    - 栈
    - 数组
    - 单调栈
---

<!-- problem:start -->

# [84. 柱状图中最大的矩形](https://leetcode.cn/problems/largest-rectangle-in-histogram)

[English Version](/solution/0000-0099/0084.Largest%20Rectangle%20in%20Histogram/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定 <em>n</em> 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。</p>

<p>求在该柱状图中，能够勾勒出来的矩形的最大面积。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0084.Largest%20Rectangle%20in%20Histogram/images/histogram.jpg" /></p>

<pre>
<strong>输入：</strong>heights = [2,1,5,6,2,3]
<strong>输出：</strong>10
<strong>解释：</strong>最大的矩形为图中红色区域，面积为 10
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0084.Largest%20Rectangle%20in%20Histogram/images/histogram-1.jpg" /></p>

<pre>
<strong>输入：</strong> heights = [2,4]
<b>输出：</b> 4</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= heights.length <=10<sup>5</sup></code></li>
	<li><code>0 <= heights[i] <= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

我们可以枚举每根柱子的高度 $h$ 作为矩形的高度，利用单调栈，向左右两边找第一个高度小于 $h$ 的下标 $left_i$, $right_i$。那么此时矩形面积为 $h \times (right_i-left_i-1)$，求最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 表示 $heights$ 的长度。

单调栈常见模型：找出每个数左/右边**离它最近的**且**比它大/小的数**。模板：

```python
stk = []
for i in range(n):
    while stk and check(stk[-1], i):
        stk.pop()
    stk.append(i)
```

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        stk = []
        left = [-1] * n
        right = [n] * n
        for i, h in enumerate(heights):
            while stk and heights[stk[-1]] >= h:
                right[stk[-1]] = i
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        return max(h * (right[i] - left[i] - 1) for i, h in enumerate(heights))
```

#### Java

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int res = 0, n = heights.length;
        Deque<Integer> stk = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && heights[stk.peek()] >= heights[i]) {
                right[stk.pop()] = i;
            }
            left[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(i);
        }
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        int res = 0, n = heights.size();
        stack<int> stk;
        vector<int> left(n, -1);
        vector<int> right(n, n);
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && heights[stk.top()] >= heights[i]) {
                right[stk.top()] = i;
                stk.pop();
            }
            if (!stk.empty()) left[i] = stk.top();
            stk.push(i);
        }
        for (int i = 0; i < n; ++i)
            res = max(res, heights[i] * (right[i] - left[i] - 1));
        return res;
    }
};
```

#### Go

```go
func largestRectangleArea(heights []int) int {
	res, n := 0, len(heights)
	var stk []int
	left, right := make([]int, n), make([]int, n)
	for i := range right {
		right[i] = n
	}
	for i, h := range heights {
		for len(stk) > 0 && heights[stk[len(stk)-1]] >= h {
			right[stk[len(stk)-1]] = i
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		} else {
			left[i] = -1
		}
		stk = append(stk, i)
	}
	for i, h := range heights {
		res = max(res, h*(right[i]-left[i]-1))
	}
	return res
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn largest_rectangle_area(heights: Vec<i32>) -> i32 {
        let n = heights.len();
        let mut left = vec![-1; n];
        let mut right = vec![-1; n];
        let mut stack: Vec<(usize, i32)> = Vec::new();
        let mut ret = -1;

        // Build left vector
        for (i, h) in heights.iter().enumerate() {
            while !stack.is_empty() && stack.last().unwrap().1 >= *h {
                stack.pop();
            }
            if stack.is_empty() {
                left[i] = -1;
            } else {
                left[i] = stack.last().unwrap().0 as i32;
            }
            stack.push((i, *h));
        }

        stack.clear();

        // Build right vector
        for (i, h) in heights.iter().enumerate().rev() {
            while !stack.is_empty() && stack.last().unwrap().1 >= *h {
                stack.pop();
            }
            if stack.is_empty() {
                right[i] = n as i32;
            } else {
                right[i] = stack.last().unwrap().0 as i32;
            }
            stack.push((i, *h));
        }

        // Calculate the max area
        for (i, h) in heights.iter().enumerate() {
            ret = std::cmp::max(ret, (right[i] - left[i] - 1) * *h);
        }

        ret
    }
}
```

#### C#

```cs
using System;
using System.Collections.Generic;
using System.Linq;

public class Solution {
    public int LargestRectangleArea(int[] height) {
        var stack = new Stack<int>();
        var result = 0;
        var i = 0;
        while (i < height.Length || stack.Any())
        {
            if (!stack.Any() || (i < height.Length && height[stack.Peek()] < height[i]))
            {
                stack.Push(i);
                ++i;
            }
            else
            {
                var previousIndex = stack.Pop();
                var area = height[previousIndex] * (stack.Any() ? (i - stack.Peek() - 1) : i);
                result = Math.Max(result, area);
            }
        }

        return result;
    }
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
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        stk = []
        left = [-1] * n
        right = [n] * n
        for i, h in enumerate(heights):
            while stk and heights[stk[-1]] >= h:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            h = heights[i]
            while stk and heights[stk[-1]] >= h:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        return max(h * (right[i] - left[i] - 1) for i, h in enumerate(heights))
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
