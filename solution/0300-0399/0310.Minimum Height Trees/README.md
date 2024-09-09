---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0310.Minimum%20Height%20Trees/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
---

<!-- problem:start -->

# [310. 最小高度树](https://leetcode.cn/problems/minimum-height-trees)

[English Version](/solution/0300-0399/0310.Minimum%20Height%20Trees/README_EN.md)

## 题目描述

<!-- description:start -->

<p>树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，任何一个没有简单环路的连通图都是一棵树。</p>

<p>给你一棵包含&nbsp;<code>n</code>&nbsp;个节点的树，标记为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code> 。给定数字&nbsp;<code>n</code>&nbsp;和一个有 <code>n - 1</code> 条无向边的 <code>edges</code>&nbsp;列表（每一个边都是一对标签），其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示树中节点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间存在一条无向边。</p>

<p>可选择树中任何一个节点作为根。当选择节点 <code>x</code> 作为根节点时，设结果树的高度为 <code>h</code> 。在所有可能的树中，具有最小高度的树（即，<code>min(h)</code>）被称为 <strong>最小高度树</strong> 。</p>

<p>请你找到所有的 <strong>最小高度树</strong> 并按 <strong>任意顺序</strong> 返回它们的根节点标签列表。</p>
树的 <strong>高度</strong> 是指根节点和叶子节点之间最长向下路径上边的数量。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0310.Minimum%20Height%20Trees/images/e1.jpg" style="height: 213px; width: 800px;" />
<pre>
<strong>输入：</strong>n = 4, edges = [[1,0],[1,2],[1,3]]
<strong>输出：</strong>[1]
<strong>解释：</strong>如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0310.Minimum%20Height%20Trees/images/e2.jpg" style="height: 321px; width: 800px;" />
<pre>
<strong>输入：</strong>n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
<strong>输出：</strong>[3,4]
</pre>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>所有 <code>(a<sub>i</sub>, b<sub>i</sub>)</code> 互不相同</li>
	<li>给定的输入 <strong>保证</strong> 是一棵树，并且 <strong>不会有重复的边</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：拓扑排序

如果这棵树只有一个节点，那么这个节点就是最小高度树的根节点，直接返回这个节点即可。

如果这棵树有多个节点，那么一定存在叶子节点。叶子节点是只有一个相邻节点的节点。我们可以利用拓扑排序，从外向内剥离叶子节点，当我们到达最后一层的时候，剩下的节点就是最小高度树的根节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMinHeightTrees(self, n: int, edges: List[List[int]]) -> List[int]:
        if n == 1:
            return [0]
        g = [[] for _ in range(n)]
        degree = [0] * n
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
            degree[a] += 1
            degree[b] += 1
        q = deque(i for i in range(n) if degree[i] == 1)
        ans = []
        while q:
            ans.clear()
            for _ in range(len(q)):
                a = q.popleft()
                ans.append(a)
                for b in g[a]:
                    degree[b] -= 1
                    if degree[b] == 1:
                        q.append(b)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return List.of(0);
        }
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] degree = new int[n];
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
            ++degree[a];
            ++degree[b];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] == 1) {
                q.offer(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            ans.clear();
            for (int i = q.size(); i > 0; --i) {
                int a = q.poll();
                ans.add(a);
                for (int b : g[a]) {
                    if (--degree[b] == 1) {
                        q.offer(b);
                    }
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
    vector<int> findMinHeightTrees(int n, vector<vector<int>>& edges) {
        if (n == 1) {
            return {0};
        }
        vector<vector<int>> g(n);
        vector<int> degree(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
            ++degree[a];
            ++degree[b];
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (degree[i] == 1) {
                q.push(i);
            }
        }
        vector<int> ans;
        while (!q.empty()) {
            ans.clear();
            for (int i = q.size(); i > 0; --i) {
                int a = q.front();
                q.pop();
                ans.push_back(a);
                for (int b : g[a]) {
                    if (--degree[b] == 1) {
                        q.push(b);
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findMinHeightTrees(n int, edges [][]int) (ans []int) {
	if n == 1 {
		return []int{0}
	}
	g := make([][]int, n)
	degree := make([]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
		degree[a]++
		degree[b]++
	}
	q := []int{}
	for i, d := range degree {
		if d == 1 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		ans = []int{}
		for i := len(q); i > 0; i-- {
			a := q[0]
			q = q[1:]
			ans = append(ans, a)
			for _, b := range g[a] {
				degree[b]--
				if degree[b] == 1 {
					q = append(q, b)
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function findMinHeightTrees(n: number, edges: number[][]): number[] {
    if (n === 1) {
        return [0];
    }
    const g: number[][] = Array.from({ length: n }, () => []);
    const degree: number[] = Array(n).fill(0);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
        ++degree[a];
        ++degree[b];
    }
    const q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (degree[i] === 1) {
            q.push(i);
        }
    }
    const ans: number[] = [];
    while (q.length > 0) {
        ans.length = 0;
        const t: number[] = [];
        for (const a of q) {
            ans.push(a);
            for (const b of g[a]) {
                if (--degree[b] === 1) {
                    t.push(b);
                }
            }
        }
        q.splice(0, q.length, ...t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
