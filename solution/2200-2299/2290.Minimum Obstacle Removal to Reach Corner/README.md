---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2290.Minimum%20Obstacle%20Removal%20to%20Reach%20Corner/README.md
rating: 2137
source: 第 295 场周赛 Q4
tags:
    - 广度优先搜索
    - 图
    - 数组
    - 矩阵
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [2290. 到达角落需要移除障碍物的最小数目](https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner)

[English Version](/solution/2200-2299/2290.Minimum%20Obstacle%20Removal%20to%20Reach%20Corner/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>grid</code> ，数组大小为 <code>m x n</code> 。每个单元格都是两个值之一：</p>

<ul>
	<li><code>0</code> 表示一个 <strong>空</strong> 单元格，</li>
	<li><code>1</code> 表示一个可以移除的 <strong>障碍物</strong> 。</li>
</ul>

<p>你可以向上、下、左、右移动，从一个空单元格移动到另一个空单元格。</p>

<p>现在你需要从左上角&nbsp;<code>(0, 0)</code> 移动到右下角 <code>(m - 1, n - 1)</code> ，返回需要移除的障碍物的 <strong>最小</strong> 数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2290.Minimum%20Obstacle%20Removal%20to%20Reach%20Corner/images/example1drawio-1.png" style="width: 605px; height: 246px;" /></p>

<pre>
<strong>输入：</strong>grid = [[0,1,1],[1,1,0],[1,1,0]]
<strong>输出：</strong>2
<strong>解释：</strong>可以移除位于 (0, 1) 和 (0, 2) 的障碍物来创建从 (0, 0) 到 (2, 2) 的路径。
可以证明我们至少需要移除两个障碍物，所以返回 2 。
注意，可能存在其他方式来移除 2 个障碍物，创建出可行的路径。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2290.Minimum%20Obstacle%20Removal%20to%20Reach%20Corner/images/example1drawio.png" style="width: 405px; height: 246px;" /></p>

<pre>
<strong>输入：</strong>grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
<strong>输出：</strong>0
<strong>解释：</strong>不移除任何障碍物就能从 (0, 0) 到 (2, 4) ，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> <strong>或</strong> <code>1</code></li>
	<li><code>grid[0][0] == grid[m - 1][n - 1] == 0</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双端队列 BFS

本题实际上也是最短路模型，只不过求解的是移除障碍物的最小数目。

在一个边权只有 $0$, $1$ 的无向图中搜索最短路径可以使用双端队列进行 $BFS$。其原理是当前可以扩展到的点的权重为 $0$ 时，将其加入队首；权重为 $1$ 时，将其加入队尾。

> 如果某条边权值为 $0$，那么新拓展出的节点权值就和当前队首节点权值相同，显然可以作为下一次拓展的起点。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

相似题目：

-   [1368. 使网格图至少有一条有效路径的最小代价](https://github.com/doocs/leetcode/blob/main/solution/1300-1399/1368.Minimum%20Cost%20to%20Make%20at%20Least%20One%20Valid%20Path%20in%20a%20Grid/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumObstacles(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = deque([(0, 0, 0)])
        vis = set()
        dirs = (-1, 0, 1, 0, -1)
        while 1:
            i, j, k = q.popleft()
            if i == m - 1 and j == n - 1:
                return k
            if (i, j) in vis:
                continue
            vis.add((i, j))
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n:
                    if grid[x][y] == 0:
                        q.appendleft((x, y, k))
                    else:
                        q.append((x, y, k + 1))
```

#### Java

```java
class Solution {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 0});
        int[] dirs = {-1, 0, 1, 0, -1};
        boolean[][] vis = new boolean[m][n];
        while (true) {
            var p = q.poll();
            int i = p[0], j = p[1], k = p[2];
            if (i == m - 1 && j == n - 1) {
                return k;
            }
            if (vis[i][j]) {
                continue;
            }
            vis[i][j] = true;
            for (int h = 0; h < 4; ++h) {
                int x = i + dirs[h], y = j + dirs[h + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (grid[x][y] == 0) {
                        q.offerFirst(new int[] {x, y, k});
                    } else {
                        q.offerLast(new int[] {x, y, k + 1});
                    }
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumObstacles(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        deque<tuple<int, int, int>> q{{0, 0, 0}};
        bool vis[m][n];
        memset(vis, 0, sizeof vis);
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (1) {
            auto [i, j, k] = q.front();
            q.pop_front();
            if (i == m - 1 && j == n - 1) {
                return k;
            }
            if (vis[i][j]) {
                continue;
            }
            vis[i][j] = true;
            for (int h = 0; h < 4; ++h) {
                int x = i + dirs[h], y = j + dirs[h + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (grid[x][y] == 0) {
                        q.push_front({x, y, k});
                    } else {
                        q.push_back({x, y, k + 1});
                    }
                }
            }
        }
    }
};
```

#### Go

```go
func minimumObstacles(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	q := doublylinkedlist.New()
	type tuple struct{ i, j, k int }
	q.Add(tuple{0, 0, 0})
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for {
		v, _ := q.Get(0)
		p := v.(tuple)
		q.Remove(0)
		i, j, k := p.i, p.j, p.k
		if i == m-1 && j == n-1 {
			return k
		}
		if vis[i][j] {
			continue
		}
		vis[i][j] = true
		for h := 0; h < 4; h++ {
			x, y := i+dirs[h], j+dirs[h+1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if grid[x][y] == 0 {
					q.Insert(0, tuple{x, y, k})
				} else {
					q.Add(tuple{x, y, k + 1})
				}
			}
		}
	}
}
```

#### TypeScript

```ts
function minimumObstacles(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    const dirs = [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ];
    let ans = Array.from({ length: m }, v => new Array(n).fill(Infinity));
    ans[0][0] = 0;
    let deque = [[0, 0]];
    while (deque.length) {
        let [x, y] = deque.shift();
        for (let [dx, dy] of dirs) {
            let [i, j] = [x + dx, y + dy];
            if (i < 0 || i > m - 1 || j < 0 || j > n - 1) continue;
            const cost = grid[i][j];
            if (ans[x][y] + cost >= ans[i][j]) continue;
            ans[i][j] = ans[x][y] + cost;
            deque.push([i, j]);
        }
    }
    return ans[m - 1][n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
