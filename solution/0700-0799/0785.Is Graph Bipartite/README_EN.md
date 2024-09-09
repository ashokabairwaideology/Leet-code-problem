---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0785.Is%20Graph%20Bipartite/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
---

<!-- problem:start -->

# [785. Is Graph Bipartite](https://leetcode.com/problems/is-graph-bipartite)

[中文文档](/solution/0700-0799/0785.Is%20Graph%20Bipartite/README.md)

## Description

<!-- description:start -->

<p>There is an <strong>undirected</strong> graph with <code>n</code> nodes, where each node is numbered between <code>0</code> and <code>n - 1</code>. You are given a 2D array <code>graph</code>, where <code>graph[u]</code> is an array of nodes that node <code>u</code> is adjacent to. More formally, for each <code>v</code> in <code>graph[u]</code>, there is an undirected edge between node <code>u</code> and node <code>v</code>. The graph has the following properties:</p>

<ul>
	<li>There are no self-edges (<code>graph[u]</code> does not contain <code>u</code>).</li>
	<li>There are no parallel edges (<code>graph[u]</code> does not contain duplicate values).</li>
	<li>If <code>v</code> is in <code>graph[u]</code>, then <code>u</code> is in <code>graph[v]</code> (the graph is undirected).</li>
	<li>The graph may not be connected, meaning there may be two nodes <code>u</code> and <code>v</code> such that there is no path between them.</li>
</ul>

<p>A graph is <strong>bipartite</strong> if the nodes can be partitioned into two independent sets <code>A</code> and <code>B</code> such that <strong>every</strong> edge in the graph connects a node in set <code>A</code> and a node in set <code>B</code>.</p>

<p>Return <code>true</code><em> if and only if it is <strong>bipartite</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0785.Is%20Graph%20Bipartite/images/bi2.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0785.Is%20Graph%20Bipartite/images/bi1.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> graph = [[1,3],[0,2],[1,3],[0,2]]
<strong>Output:</strong> true
<strong>Explanation:</strong> We can partition the nodes into two sets: {0, 2} and {1, 3}.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>graph.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= graph[u].length &lt; n</code></li>
	<li><code>0 &lt;= graph[u][i] &lt;= n - 1</code></li>
	<li><code>graph[u]</code>&nbsp;does not contain&nbsp;<code>u</code>.</li>
	<li>All the values of <code>graph[u]</code> are <strong>unique</strong>.</li>
	<li>If <code>graph[u]</code> contains <code>v</code>, then <code>graph[v]</code> contains <code>u</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        def dfs(u, c):
            color[u] = c
            for v in graph[u]:
                if not color[v]:
                    if not dfs(v, 3 - c):
                        return False
                elif color[v] == c:
                    return False
            return True

        n = len(graph)
        color = [0] * n
        for i in range(n):
            if not color[i] and not dfs(i, 1):
                return False
        return True
```

#### Java

```java
class Solution {
    private int[] color;
    private int[][] g;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        g = graph;
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0 && !dfs(i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int u, int c) {
        color[u] = c;
        for (int v : g[u]) {
            if (color[v] == 0) {
                if (!dfs(v, 3 - c)) {
                    return false;
                }
            } else if (color[v] == c) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> color(n);
        for (int i = 0; i < n; ++i)
            if (!color[i] && !dfs(i, 1, color, graph))
                return false;
        return true;
    }

    bool dfs(int u, int c, vector<int>& color, vector<vector<int>>& g) {
        color[u] = c;
        for (int& v : g[u]) {
            if (!color[v]) {
                if (!dfs(v, 3 - c, color, g)) return false;
            } else if (color[v] == c)
                return false;
        }
        return true;
    }
};
```

#### Go

```go
func isBipartite(graph [][]int) bool {
	n := len(graph)
	color := make([]int, n)
	var dfs func(u, c int) bool
	dfs = func(u, c int) bool {
		color[u] = c
		for _, v := range graph[u] {
			if color[v] == 0 {
				if !dfs(v, 3-c) {
					return false
				}
			} else if color[v] == c {
				return false
			}
		}
		return true
	}
	for i := range graph {
		if color[i] == 0 && !dfs(i, 1) {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    let valid = true;
    // 0 未遍历， 1 红色标记， 2 绿色标记
    let colors = new Array(n).fill(0);
    function dfs(idx: number, color: number, graph: number[][]) {
        colors[idx] = color;
        const nextColor = 3 - color;
        for (let j of graph[idx]) {
            if (!colors[j]) {
                dfs(j, nextColor, graph);
                if (!valid) return;
            } else if (colors[j] != nextColor) {
                valid = false;
                return;
            }
        }
    }

    for (let i = 0; i < n && valid; i++) {
        if (!colors[i]) {
            dfs(i, 1, graph);
        }
    }
    return valid;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let mut graph = graph;
        let n = graph.len();
        let mut color_vec: Vec<usize> = vec![0; n];
        for i in 0..n {
            if color_vec[i] == 0 && !Self::traverse(i, 1, &mut color_vec, &mut graph) {
                return false;
            }
        }
        true
    }

    #[allow(dead_code)]
    fn traverse(
        v: usize,
        color: usize,
        color_vec: &mut Vec<usize>,
        graph: &mut Vec<Vec<i32>>,
    ) -> bool {
        color_vec[v] = color;
        for n in graph[v].clone() {
            if color_vec[n as usize] == 0 {
                // This node hasn't been colored
                if !Self::traverse(n as usize, 3 - color, color_vec, graph) {
                    return false;
                }
            } else if color_vec[n as usize] == color {
                // The color is the same
                return false;
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(len(graph)))
        for u, g in enumerate(graph):
            for v in g:
                if find(u) == find(v):
                    return False
                p[find(v)] = find(g[0])
        return True
```

#### Java

```java
class Solution {
    private int[] p;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int u = 0; u < n; ++u) {
            int[] g = graph[u];
            for (int v : g) {
                if (find(u) == find(v)) {
                    return false;
                }
                p[find(v)] = find(g[0]);
            }
        }
        return true;
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

    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (int u = 0; u < n; ++u) {
            auto& g = graph[u];
            for (int v : g) {
                if (find(u) == find(v)) return 0;
                p[find(v)] = find(g[0]);
            }
        }
        return 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

#### Go

```go
func isBipartite(graph [][]int) bool {
	n := len(graph)
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
	for u, g := range graph {
		for _, v := range g {
			if find(u) == find(v) {
				return false
			}
			p[find(v)] = find(g[0])
		}
	}
	return true
}
```

#### TypeScript

```ts
function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    let p = new Array(n);
    for (let i = 0; i < n; ++i) {
        p[i] = i;
    }
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    for (let u = 0; u < n; ++u) {
        for (let v of graph[u]) {
            if (find(u) == find(v)) {
                return false;
            }
            p[find(v)] = find(graph[u][0]);
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let n = graph.len();
        let mut disjoint_set: Vec<usize> = vec![0; n];
        // Initialize the disjoint set
        for i in 0..n {
            disjoint_set[i] = i;
        }

        // Traverse the graph
        for i in 0..n {
            if graph[i].is_empty() {
                continue;
            }
            let first = graph[i][0] as usize;
            for v in &graph[i] {
                let v = *v as usize;
                let i_p = Self::find(i, &mut disjoint_set);
                let v_p = Self::find(v, &mut disjoint_set);
                if i_p == v_p {
                    return false;
                }
                // Otherwise, union the node
                Self::union(first, v, &mut disjoint_set);
            }
        }

        true
    }

    #[allow(dead_code)]
    fn find(x: usize, d_set: &mut Vec<usize>) -> usize {
        if d_set[x] != x {
            d_set[x] = Self::find(d_set[x], d_set);
        }
        d_set[x]
    }

    #[allow(dead_code)]
    fn union(x: usize, y: usize, d_set: &mut Vec<usize>) {
        let p_x = Self::find(x, d_set);
        let p_y = Self::find(y, d_set);
        d_set[p_x] = p_y;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
