---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2965.Find%20Missing%20and%20Repeated%20Values/README.md
rating: 1244
source: 第 376 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 数学
    - 矩阵
---

<!-- problem:start -->

# [2965. 找出缺失和重复的数字](https://leetcode.cn/problems/find-missing-and-repeated-values)

[English Version](/solution/2900-2999/2965.Find%20Missing%20and%20Repeated%20Values/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从<strong> 0 </strong>开始的二维整数矩阵 <code><font face="monospace">grid</font></code>，大小为 <code>n * n</code> ，其中的值在 <code>[1, n<sup>2</sup>]</code> 范围内。除了 <code>a</code> 出现 <strong>两次</strong>，<code>b</code> <strong>缺失</strong> 之外，每个整数都<strong> 恰好出现一次</strong> 。</p>

<p>任务是找出重复的数字<code>a</code> 和缺失的数字 <code>b</code> 。</p>

<p>返回一个下标从 0 开始、长度为 <code>2</code> 的整数数组 <code>ans</code> ，其中 <code>ans[0]</code> 等于 <code>a</code> ，<code>ans[1]</code> 等于 <code>b</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,3],[2,2]]
<strong>输出：</strong>[2,4]
<strong>解释：</strong>数字 2 重复，数字 4 缺失，所以答案是 [2,4] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[9,1,7],[8,9,2],[3,4,6]]
<strong>输出：</strong>[9,5]
<strong>解释：</strong>数字 9 重复，数字 5 缺失，所以答案是 [9,5] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == grid.length == grid[i].length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= n * n</code></li>
	<li>对于所有满足<code>1 &lt;= x &lt;= n * n</code> 的 <code>x</code> ，恰好存在一个 <code>x</code> 与矩阵中的任何成员都不相等。</li>
	<li>对于所有满足<code>1 &lt;= x &lt;= n * n</code> 的 <code>x</code> ，恰好存在一个 <code>x</code> 与矩阵中的两个成员相等。</li>
	<li>除上述的两个之外，对于所有满足<code>1 &lt;= x &lt;= n * n</code> 的 <code>x</code> ，都恰好存在一对 <code>i, j</code> 满足 <code>0 &lt;= i, j &lt;= n - 1</code> 且 <code>grid[i][j] == x</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们创建一个长度为 $n^2 + 1$ 的数组 $cnt$，统计矩阵中每个数字出现的次数。

接下来遍历 $i \in [1, n^2]$，如果 $cnt[i] = 2$，则 $i$ 是重复的数字，我们将答案的第一个元素设为 $i$；如果 $cnt[i] = 0$，则 $i$ 是缺失的数字，我们将答案的第二个元素设为 $i$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是矩阵的边长。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMissingAndRepeatedValues(self, grid: List[List[int]]) -> List[int]:
        n = len(grid)
        cnt = [0] * (n * n + 1)
        for row in grid:
            for v in row:
                cnt[v] += 1
        ans = [0] * 2
        for i in range(1, n * n + 1):
            if cnt[i] == 2:
                ans[0] = i
            if cnt[i] == 0:
                ans[1] = i
        return ans
```

#### Java

```java
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] cnt = new int[n * n + 1];
        int[] ans = new int[2];
        for (int[] row : grid) {
            for (int x : row) {
                if (++cnt[x] == 2) {
                    ans[0] = x;
                }
            }
        }
        for (int x = 1;; ++x) {
            if (cnt[x] == 0) {
                ans[1] = x;
                return ans;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findMissingAndRepeatedValues(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> cnt(n * n + 1);
        vector<int> ans(2);
        for (auto& row : grid) {
            for (int x : row) {
                if (++cnt[x] == 2) {
                    ans[0] = x;
                }
            }
        }
        for (int x = 1;; ++x) {
            if (cnt[x] == 0) {
                ans[1] = x;
                return ans;
            }
        }
    }
};
```

#### Go

```go
func findMissingAndRepeatedValues(grid [][]int) []int {
	n := len(grid)
	ans := make([]int, 2)
	cnt := make([]int, n*n+1)
	for _, row := range grid {
		for _, x := range row {
			cnt[x]++
			if cnt[x] == 2 {
				ans[0] = x
			}
		}
	}
	for x := 1; ; x++ {
		if cnt[x] == 0 {
			ans[1] = x
			return ans
		}
	}
}
```

#### TypeScript

```ts
function findMissingAndRepeatedValues(grid: number[][]): number[] {
    const n = grid.length;
    const cnt: number[] = Array(n * n + 1).fill(0);
    const ans: number[] = Array(2).fill(0);
    for (const row of grid) {
        for (const x of row) {
            if (++cnt[x] === 2) {
                ans[0] = x;
            }
        }
    }
    for (let x = 1; ; ++x) {
        if (cnt[x] === 0) {
            ans[1] = x;
            return ans;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
