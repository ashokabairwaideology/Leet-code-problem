---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20039.%20%E7%9B%B4%E6%96%B9%E5%9B%BE%E6%9C%80%E5%A4%A7%E7%9F%A9%E5%BD%A2%E9%9D%A2%E7%A7%AF/README.md
---

<!-- problem:start -->

# [剑指 Offer II 039. 直方图最大矩形面积](https://leetcode.cn/problems/0ynMMM)

## 题目描述

<!-- description:start -->

<p>给定非负整数数组 <code>heights</code>&nbsp;，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 <code>1</code> 。</p>
<p>求在该柱状图中，能够勾勒出来的矩形的最大面积。</p>
<p>&nbsp;</p>
<p><strong>示例 1:</strong></p>
<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20039.%20%E7%9B%B4%E6%96%B9%E5%9B%BE%E6%9C%80%E5%A4%A7%E7%9F%A9%E5%BD%A2%E9%9D%A2%E7%A7%AF/images/histogram.jpg" /></p>
<pre>
<strong>输入：</strong>heights = [2,1,5,6,2,3]
<strong>输出：</strong>10
<strong>解释：</strong>最大的矩形为图中红色区域，面积为 10
</pre>
<p><strong>示例 2：</strong></p>
<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20039.%20%E7%9B%B4%E6%96%B9%E5%9B%BE%E6%9C%80%E5%A4%A7%E7%9F%A9%E5%BD%A2%E9%9D%A2%E7%A7%AF/images/histogram-1.jpg" /></p>
<pre>
<strong>输入：</strong> heights = [2,4]
<b>输出：</b> 4</pre>
<p>&nbsp;</p>
<p><strong>提示：</strong></p>
<ul>
	<li><code>1 &lt;= heights.length &lt;=10<sup>5</sup></code></li>
	<li><code>0 &lt;= heights[i] &lt;= 10<sup>4</sup></code></li>
</ul>
<p>&nbsp;</p>
<p><meta charset="UTF-8" />注意：本题与主站 84&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/">https://leetcode.cn/problems/largest-rectangle-in-histogram/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

单调栈常见模型：找出每个数左/右边**离它最近的**且**比它大/小的数**。模板：

```python
stk = []
for i in range(n):
    while stk and check(stk[-1], i):
        stk.pop()
    stk.append(i)
```

枚举每根柱子的高度 $h$ 作为矩形的高度，向左右两边找第一个高度小于 $h$ 的下标 $left_i$, $right_i$。那么此时矩形面积为 $h \times (right_i-left_i-1)$，求最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为柱子个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, x in enumerate(heights):
            while stk and heights[stk[-1]] >= x:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and heights[stk[-1]] >= heights[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        return max(x * (r - l - 1) for x, l, r in zip(heights, left, right))
```

#### Java

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; ++i) {
            left[i] = -1;
            right[i] = n;
        }
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && heights[stk.peek()] >= heights[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && heights[stk.peek()] >= heights[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        int n = heights.size();
        vector<int> left(n, -1), right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && heights[stk.top()] >= heights[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.top();
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; ~i; --i) {
            while (!stk.empty() && heights[stk.top()] >= heights[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func largestRectangleArea(heights []int) (ans int) {
	n := len(heights)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, x := range heights {
		for len(stk) > 0 && heights[stk[len(stk)-1]] >= x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && heights[stk[len(stk)-1]] >= heights[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	for i, x := range heights {
		ans = max(ans, (right[i]-left[i]-1)*x)
	}
	return
}
```

#### TypeScript

```ts
function largestRectangleArea(heights: number[]): number {
    const n = heights.length;
    const left: number[] = new Array(n).fill(-1);
    const right: number[] = new Array(n).fill(n);
    const stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (stk.length && heights[stk[stk.length - 1]] >= heights[i]) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && heights[stk[stk.length - 1]] >= heights[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func largestRectangleArea(_ heights: [Int]) -> Int {
        let n = heights.count
        var left = [Int](repeating: -1, count: n)
        var right = [Int](repeating: n, count: n)
        var stack = [Int]()

        for i in 0..<n {
            while !stack.isEmpty && heights[stack.last!] >= heights[i] {
                stack.removeLast()
            }
            if !stack.isEmpty {
                left[i] = stack.last!
            }
            stack.append(i)
        }

        stack.removeAll()

        for i in stride(from: n - 1, through: 0, by: -1) {
            while !stack.isEmpty && heights[stack.last!] >= heights[i] {
                stack.removeLast()
            }
            if !stack.isEmpty {
                right[i] = stack.last!
            }
            stack.append(i)
        }

        var maxArea = 0
        for i in 0..<n {
            maxArea = max(maxArea, (right[i] - left[i] - 1) * heights[i])
        }

        return maxArea
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
