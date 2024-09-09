---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/README.md
rating: 1857
source: 第 206 场周赛 Q3
tags:
    - 并查集
    - 图
    - 数组
    - 最小生成树
---

<!-- problem:start -->

# [1584. 连接所有点的最小费用](https://leetcode.cn/problems/min-cost-to-connect-all-points)

[English Version](/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个<code>points</code>&nbsp;数组，表示 2D 平面上的一些点，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;。</p>

<p>连接点&nbsp;<code>[x<sub>i</sub>, y<sub>i</sub>]</code> 和点&nbsp;<code>[x<sub>j</sub>, y<sub>j</sub>]</code>&nbsp;的费用为它们之间的 <strong>曼哈顿距离</strong>&nbsp;：<code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>&nbsp;，其中&nbsp;<code>|val|</code>&nbsp;表示&nbsp;<code>val</code>&nbsp;的绝对值。</p>

<p>请你返回将所有点连接的最小总费用。只有任意两点之间 <strong>有且仅有</strong>&nbsp;一条简单路径时，才认为所有点都已连接。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/images/d.png" style="height:268px; width:214px; background:#e5e5e5" /></p>

<pre>
<strong>输入：</strong>points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
<strong>输出：</strong>20
<strong>解释：
</strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/images/c.png" style="height:268px; width:214px; background:#e5e5e5" />
我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
注意到任意两个点之间只有唯一一条路径互相到达。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>points = [[3,12],[-2,5],[-4,1]]
<strong>输出：</strong>18
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>points = [[0,0],[1,1],[1,0],[-1,1]]
<strong>输出：</strong>4
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>points = [[-1000000,-1000000],[1000000,1000000]]
<strong>输出：</strong>4000000
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>points = [[0,0]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 1000</code></li>
	<li><code>-10<sup>6</sup>&nbsp;&lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>所有点&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;两两不同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：朴素 Prim 算法

我们定义一个数组 $dist$，其中 $dist[i]$ 表示点 $i$ 到当前生成树的距离，初始时 $dist[0] = 0$，其余均为 $+\infty$，定义一个数组 $vis$，其中 $vis[i]$ 表示点 $i$ 是否在生成树中，初始时所有点均不在生成树中，定义一个二维数组 $g$，其中 $g[i][j]$ 表示点 $i$ 到点 $j$ 的距离，那么我们的目标是将所有点都加入到生成树中，且总费用最小。

我们每次从不在生成树中的点中选取一个距离最小的点 $i$，将点 $i$ 加入到生成树中，并将 $i$ 到其它点的距离更新到 $dist$ 数组中，直到所有点都在生成树中为止。

该算法适用于稠密图，时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为点的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        n = len(points)
        g = [[0] * n for _ in range(n)]
        dist = [inf] * n
        vis = [False] * n
        for i, (x1, y1) in enumerate(points):
            for j in range(i + 1, n):
                x2, y2 = points[j]
                t = abs(x1 - x2) + abs(y1 - y2)
                g[i][j] = g[j][i] = t
        dist[0] = 0
        ans = 0
        for _ in range(n):
            i = -1
            for j in range(n):
                if not vis[j] and (i == -1 or dist[j] < dist[i]):
                    i = j
            vis[i] = True
            ans += dist[i]
            for j in range(n):
                if not vis[j]:
                    dist[j] = min(dist[j], g[i][j])
        return ans
```

#### Java

```java
class Solution {
    public int minCostConnectPoints(int[][] points) {
        final int inf = 1 << 30;
        int n = points.length;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int t = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                g[i][j] = t;
                g[j][i] = t;
            }
        }
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        Arrays.fill(dist, inf);
        dist[0] = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = -1;
            for (int k = 0; k < n; ++k) {
                if (!vis[k] && (j == -1 || dist[k] < dist[j])) {
                    j = k;
                }
            }
            vis[j] = true;
            ans += dist[j];
            for (int k = 0; k < n; ++k) {
                if (!vis[k]) {
                    dist[k] = Math.min(dist[k], g[j][k]);
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size();
        int g[n][n];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int t = abs(x1 - x2) + abs(y1 - y2);
                g[i][j] = t;
                g[j][i] = t;
            }
        }
        int dist[n];
        bool vis[n];
        memset(dist, 0x3f, sizeof(dist));
        memset(vis, false, sizeof(vis));
        dist[0] = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = -1;
            for (int k = 0; k < n; ++k) {
                if (!vis[k] && (j == -1 || dist[k] < dist[j])) {
                    j = k;
                }
            }
            vis[j] = true;
            ans += dist[j];
            for (int k = 0; k < n; ++k) {
                if (!vis[k]) {
                    dist[k] = min(dist[k], g[j][k]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minCostConnectPoints(points [][]int) (ans int) {
	n := len(points)
	g := make([][]int, n)
	vis := make([]bool, n)
	dist := make([]int, n)
	for i := range g {
		g[i] = make([]int, n)
		dist[i] = 1 << 30
	}
	for i := range g {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			t := abs(x1-x2) + abs(y1-y2)
			g[i][j] = t
			g[j][i] = t
		}
	}
	dist[0] = 0
	for i := 0; i < n; i++ {
		j := -1
		for k := 0; k < n; k++ {
			if !vis[k] && (j == -1 || dist[k] < dist[j]) {
				j = k
			}
		}
		vis[j] = true
		ans += dist[j]
		for k := 0; k < n; k++ {
			if !vis[k] {
				dist[k] = min(dist[k], g[j][k])
			}
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minCostConnectPoints(points: number[][]): number {
    const n = points.length;
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(0));
    const dist: number[] = Array(n).fill(1 << 30);
    const vis: boolean[] = Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        const [x1, y1] = points[i];
        for (let j = i + 1; j < n; ++j) {
            const [x2, y2] = points[j];
            const t = Math.abs(x1 - x2) + Math.abs(y1 - y2);
            g[i][j] = t;
            g[j][i] = t;
        }
    }
    let ans = 0;
    dist[0] = 0;
    for (let i = 0; i < n; ++i) {
        let j = -1;
        for (let k = 0; k < n; ++k) {
            if (!vis[k] && (j === -1 || dist[k] < dist[j])) {
                j = k;
            }
        }
        vis[j] = true;
        ans += dist[j];
        for (let k = 0; k < n; ++k) {
            if (!vis[k]) {
                dist[k] = Math.min(dist[k], g[j][k]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：Kruskal 算法

我们先将所有边按照长度由小到大进行排序，循环遍历每条边，逐个加入到图中，当所有点达到一个连通状态时，退出循环，返回此时的总费用即可。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(m)$。其中 $m$ 为边的数量，本题中 $m = n^2$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(points)
        g = []
        for i, (x1, y1) in enumerate(points):
            for j in range(i + 1, n):
                x2, y2 = points[j]
                t = abs(x1 - x2) + abs(y1 - y2)
                g.append((t, i, j))
        p = list(range(n))
        ans = 0
        for cost, i, j in sorted(g):
            pa, pb = find(i), find(j)
            if pa == pb:
                continue
            p[pa] = pb
            ans += cost
            n -= 1
            if n == 1:
                break
        return ans
```

#### Java

```java
class Solution {
    private int[] p;

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]> g = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                g.add(new int[] {Math.abs(x1 - x2) + Math.abs(y1 - y2), i, j});
            }
        }
        g.sort(Comparator.comparingInt(a -> a[0]));
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int ans = 0;
        for (int[] e : g) {
            int cost = e[0], i = e[1], j = e[2];
            if (find(i) == find(j)) {
                continue;
            }
            p[find(i)] = find(j);
            ans += cost;
            if (--n == 1) {
                return ans;
            }
        }
        return 0;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> p;

    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size();
        vector<vector<int>> g;
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                g.push_back({abs(x1 - x2) + abs(y1 - y2), i, j});
            }
        }
        sort(g.begin(), g.end());
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        int ans = 0;
        for (auto& e : g) {
            int cost = e[0], i = e[1], j = e[2];
            if (find(i) == find(j)) continue;
            p[find(i)] = find(j);
            ans += cost;
            if (--n == 1) return ans;
        }
        return 0;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

#### Go

```go
func minCostConnectPoints(points [][]int) int {
	n := len(points)
	var g [][]int
	for i, p := range points {
		x1, y1 := p[0], p[1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			g = append(g, []int{abs(x1-x2) + abs(y1-y2), i, j})
		}
	}
	sort.Slice(g, func(i, j int) bool {
		return g[i][0] < g[j][0]
	})
	ans := 0
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range g {
		cost, i, j := e[0], e[1], e[2]
		if find(i) == find(j) {
			continue
		}
		p[find(i)] = find(j)
		ans += cost
		n--
		if n == 1 {
			return ans
		}
	}
	return 0
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
