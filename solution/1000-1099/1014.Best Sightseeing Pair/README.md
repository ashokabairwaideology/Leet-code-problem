---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1014.Best%20Sightseeing%20Pair/README.md
rating: 1730
source: 第 129 场周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [1014. 最佳观光组合](https://leetcode.cn/problems/best-sightseeing-pair)

[English Version](/solution/1000-1099/1014.Best%20Sightseeing%20Pair/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组 <code>values</code>，其中 <code>values[i]</code> 表示第 <code>i</code> 个观光景点的评分，并且两个景点 <code>i</code> 和 <code>j</code> 之间的 <strong>距离</strong> 为 <code>j - i</code>。</p>

<p>一对景点（<code>i < j</code>）组成的观光组合的得分为 <code>values[i] + values[j] + i - j</code> ，也就是景点的评分之和<strong> 减去 </strong>它们两者之间的距离。</p>

<p>返回一对观光景点能取得的最高分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>values = [8,1,5,2,6]
<strong>输出：</strong>11
<strong>解释：</strong>i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>values = [1,2]
<strong>输出：</strong>2
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= values.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>1 <= values[i] <= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 维护前缀最大值

我们可以在 $[1,..n - 1]$ 的范围内枚举 $j$，那么我们要在 $[0,..j - 1]$ 的范围内找到一个 $i$，使得 $values[i] + values[j] + i - j$ 的值最大。我们可以维护一个前缀最大值，即 $values[i] + i$ 的最大值，那么我们只需要在枚举 $j$ 的过程中，不断地更新答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScoreSightseeingPair(self, values: List[int]) -> int:
        ans, mx = 0, values[0]
        for j in range(1, len(values)):
            ans = max(ans, values[j] - j + mx)
            mx = max(mx, values[j] + j)
        return ans
```

#### Java

```java
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0, mx = values[0];
        for (int j = 1; j < values.length; ++j) {
            ans = Math.max(ans, values[j] - j + mx);
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScoreSightseeingPair(vector<int>& values) {
        int ans = 0, mx = values[0];
        for (int j = 1; j < values.size(); ++j) {
            ans = max(ans, values[j] - j + mx);
            mx = max(mx, values[j] + j);
        }
        return ans;
    }
};
```

#### Go

```go
func maxScoreSightseeingPair(values []int) (ans int) {
	for j, mx := 1, values[0]; j < len(values); j++ {
		ans = max(ans, values[j]-j+mx)
		mx = max(mx, values[j]+j)
	}
	return
}
```

#### TypeScript

```ts
function maxScoreSightseeingPair(values: number[]): number {
    let ans = 0;
    let mx = values[0];
    for (let j = 1; j < values.length; ++j) {
        ans = Math.max(ans, values[j] - j + mx);
        mx = Math.max(mx, values[j] + j);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
