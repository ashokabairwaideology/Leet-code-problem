---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2017.Grid%20Game/README.md
rating: 1718
source: 第 260 场周赛 Q2
tags:
    - 数组
    - 矩阵
    - 前缀和
---

<!-- problem:start -->

# [2017. 网格游戏](https://leetcode.cn/problems/grid-game)

[English Version](/solution/2000-2099/2017.Grid%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的二维数组 <code>grid</code> ，数组大小为 <code>2 x n</code> ，其中 <code>grid[r][c]</code> 表示矩阵中 <code>(r, c)</code> 位置上的点数。现在有两个机器人正在矩阵上参与一场游戏。</p>

<p>两个机器人初始位置都是 <code>(0, 0)</code> ，目标位置是 <code>(1, n-1)</code> 。每个机器人只会 <strong>向右</strong> (<code>(r, c)</code> 到 <code>(r, c + 1)</code>) 或 <strong>向下 </strong>(<code>(r, c)</code> 到 <code>(r + 1, c)</code>) 。</p>

<p>游戏开始，<strong>第一个</strong> 机器人从 <code>(0, 0)</code> 移动到 <code>(1, n-1)</code> ，并收集路径上单元格的全部点数。对于路径上所有单元格 <code>(r, c)</code> ，途经后 <code>grid[r][c]</code> 会重置为 <code>0</code> 。然后，<strong>第二个</strong> 机器人从 <code>(0, 0)</code> 移动到 <code>(1, n-1)</code> ，同样收集路径上单元的全部点数。注意，它们的路径可能会存在相交的部分。</p>

<p><strong>第一个</strong> 机器人想要打击竞争对手，使 <strong>第二个</strong> 机器人收集到的点数 <strong>最小化</strong> 。与此相对，<strong>第二个</strong> 机器人想要 <strong>最大化</strong> 自己收集到的点数。两个机器人都发挥出自己的 <strong>最佳水平</strong>&nbsp;的前提下，返回 <strong>第二个</strong> 机器人收集到的 <strong>点数</strong> <em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2017.Grid%20Game/images/a1.png" style="width: 388px; height: 103px;" /></p>

<pre>
<strong>输入：</strong>grid = [[2,5,4],[1,5,1]]
<strong>输出：</strong>4
<strong>解释：</strong>第一个机器人的最佳路径如红色所示，第二个机器人的最佳路径如蓝色所示。
第一个机器人访问过的单元格将会重置为 0 。
第二个机器人将会收集到 0 + 0 + 4 + 0 = 4 个点。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2017.Grid%20Game/images/a2.png" style="width: 384px; height: 105px;" />
<pre>
<strong>输入：</strong>grid = [[3,3,1],[8,5,2]]
<strong>输出：</strong>4
<strong>解释：</strong>第一个机器人的最佳路径如红色所示，第二个机器人的最佳路径如蓝色所示。 
第一个机器人访问过的单元格将会重置为 0 。
第二个机器人将会收集到 0 + 3 + 1 + 0 = 4 个点。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2017.Grid%20Game/images/a3.png" style="width: 493px; height: 103px;" />
<pre>
<strong>输入：</strong>grid = [[1,3,1,15],[1,3,3,1]]
<strong>输出：</strong>7
<strong>解释：</strong>第一个机器人的最佳路径如红色所示，第二个机器人的最佳路径如蓝色所示。
第一个机器人访问过的单元格将会重置为 0 。
第二个机器人将会收集到 0 + 1 + 3 + 3 + 0 = 7 个点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>grid.length == 2</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= grid[r][c] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

我们注意到，如果确定了第一个机器人拐头向下的位置 $j$，那么第二个机器人的最优路径也就确定了，第二个机器人的最优路径就是第一行从 $j+1$ 到 $n-1$ 的前缀和，或者第二行从 $0$ 到 $j-1$ 的前缀和，取两者的最大值。

我们先计算第一行的后缀点数和，记为 $s_1$，第二行的前缀点数和记为 $s_2$，初始时 $s_1 = \sum_{j=0}^{n-1} grid[0][j]$, $s_2 = 0$。

然后我们枚举第一个机器人拐头向下的位置 $j$，此时更新 $s_1 = s_1 - grid[0][j]$, 那么第二个机器人的最优路径和就是 $max(s_1, s_2)$，我们取所有 $j$ 对应的 $max(s_1, s_2)$ 的最小值即可。然后更新 $s_2 = s_2 + grid[1][j]$。

枚举结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是网格的列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def gridGame(self, grid: List[List[int]]) -> int:
        ans = inf
        s1, s2 = sum(grid[0]), 0
        for j, v in enumerate(grid[0]):
            s1 -= v
            ans = min(ans, max(s1, s2))
            s2 += grid[1][j]
        return ans
```

#### Java

```java
class Solution {
    public long gridGame(int[][] grid) {
        long ans = Long.MAX_VALUE;
        long s1 = 0, s2 = 0;
        for (int v : grid[0]) {
            s1 += v;
        }
        int n = grid[0].length;
        for (int j = 0; j < n; ++j) {
            s1 -= grid[0][j];
            ans = Math.min(ans, Math.max(s1, s2));
            s2 += grid[1][j];
        }
        return ans;
    }
}
```

#### C++

```cpp
using ll = long long;

class Solution {
public:
    long long gridGame(vector<vector<int>>& grid) {
        ll ans = LONG_MAX;
        int n = grid[0].size();
        ll s1 = 0, s2 = 0;
        for (int& v : grid[0]) s1 += v;
        for (int j = 0; j < n; ++j) {
            s1 -= grid[0][j];
            ans = min(ans, max(s1, s2));
            s2 += grid[1][j];
        }
        return ans;
    }
};
```

#### Go

```go
func gridGame(grid [][]int) int64 {
	ans := math.MaxInt64
	s1, s2 := 0, 0
	for _, v := range grid[0] {
		s1 += v
	}
	for j, v := range grid[0] {
		s1 -= v
		ans = min(ans, max(s1, s2))
		s2 += grid[1][j]
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function gridGame(grid: number[][]): number {
    let ans = Number.MAX_SAFE_INTEGER;
    let s1 = grid[0].reduce((a, b) => a + b, 0);
    let s2 = 0;
    for (let j = 0; j < grid[0].length; ++j) {
        s1 -= grid[0][j];
        ans = Math.min(ans, Math.max(s1, s2));
        s2 += grid[1][j];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
