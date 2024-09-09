---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1557.Minimum%20Number%20of%20Vertices%20to%20Reach%20All%20Nodes/README_EN.md
rating: 1512
source: Biweekly Contest 33 Q2
tags:
    - Graph
---

<!-- problem:start -->

# [1557. Minimum Number of Vertices to Reach All Nodes](https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes)

[中文文档](/solution/1500-1599/1557.Minimum%20Number%20of%20Vertices%20to%20Reach%20All%20Nodes/README.md)

## Description

<!-- description:start -->

<p>Given a<strong>&nbsp;directed acyclic graph</strong>,&nbsp;with&nbsp;<code>n</code>&nbsp;vertices numbered from&nbsp;<code>0</code>&nbsp;to&nbsp;<code>n-1</code>,&nbsp;and an array&nbsp;<code>edges</code>&nbsp;where&nbsp;<code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code>&nbsp;represents a directed edge from node&nbsp;<code>from<sub>i</sub></code>&nbsp;to node&nbsp;<code>to<sub>i</sub></code>.</p>

<p>Find <em>the smallest set of vertices from which all nodes in the graph are reachable</em>. It&#39;s guaranteed that a unique solution exists.</p>

<p>Notice that you can return the vertices in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1557.Minimum%20Number%20of%20Vertices%20to%20Reach%20All%20Nodes/images/untitled22.png" style="width: 231px; height: 181px;" /></p>

<pre>
<strong>Input:</strong> n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
<strong>Output:</strong> [0,3]
<b>Explanation: </b>It&#39;s not possible to reach all the nodes from a single vertex. From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1557.Minimum%20Number%20of%20Vertices%20to%20Reach%20All%20Nodes/images/untitled.png" style="width: 201px; height: 201px;" /></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
<strong>Output:</strong> [0,2,3]
<strong>Explanation: </strong>Notice that vertices 0, 3 and 2 are not reachable from any other node, so we must include them. Also any of these vertices can reach nodes 1 and 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= edges.length &lt;= min(10^5, n * (n - 1) / 2)</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= from<sub>i,</sub>&nbsp;to<sub>i</sub> &lt; n</code></li>
	<li>All pairs <code>(from<sub>i</sub>, to<sub>i</sub>)</code> are distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSmallestSetOfVertices(self, n: int, edges: List[List[int]]) -> List[int]:
        cnt = Counter(t for _, t in edges)
        return [i for i in range(n) if cnt[i] == 0]
```

#### Java

```java
class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        var cnt = new int[n];
        for (var e : edges) {
            ++cnt[e.get(1)];
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (cnt[i] == 0) {
                ans.add(i);
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
    vector<int> findSmallestSetOfVertices(int n, vector<vector<int>>& edges) {
        vector<int> cnt(n);
        for (auto& e : edges) {
            ++cnt[e[1]];
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (cnt[i] == 0) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findSmallestSetOfVertices(n int, edges [][]int) (ans []int) {
	cnt := make([]int, n)
	for _, e := range edges {
		cnt[e[1]]++
	}
	for i, c := range cnt {
		if c == 0 {
			ans = append(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function findSmallestSetOfVertices(n: number, edges: number[][]): number[] {
    const cnt: number[] = new Array(n).fill(0);
    for (const [_, t] of edges) {
        cnt[t]++;
    }
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (cnt[i] === 0) {
            ans.push(i);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_smallest_set_of_vertices(n: i32, edges: Vec<Vec<i32>>) -> Vec<i32> {
        let mut arr = vec![true; n as usize];
        edges.iter().for_each(|edge| {
            arr[edge[1] as usize] = false;
        });
        arr.iter()
            .enumerate()
            .filter_map(|(i, &v)| if v { Some(i as i32) } else { None })
            .collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
