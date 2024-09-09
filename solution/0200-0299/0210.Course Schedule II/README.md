---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0210.Course%20Schedule%20II/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
---

<!-- problem:start -->

# [210. 课程表 II](https://leetcode.cn/problems/course-schedule-ii)

[English Version](/solution/0200-0299/0210.Course%20Schedule%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现在你总共有 <code>numCourses</code> 门课需要选，记为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>numCourses - 1</code>。给你一个数组&nbsp;<code>prerequisites</code> ，其中 <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> ，表示在选修课程 <code>a<sub>i</sub></code> 前 <strong>必须</strong> 先选修&nbsp;<code>b<sub>i</sub></code> 。</p>

<ul>
	<li>例如，想要学习课程 <code>0</code> ，你需要先完成课程&nbsp;<code>1</code> ，我们用一个匹配来表示：<code>[0,1]</code> 。</li>
</ul>

<p>返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 <strong>任意一种</strong> 就可以了。如果不可能完成所有课程，返回 <strong>一个空数组</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>numCourses = 2, prerequisites = [[1,0]]
<strong>输出：</strong>[0,1]
<strong>解释：</strong>总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 <code>[0,1] 。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
<strong>输出：</strong>[0,2,1,3]
<strong>解释：</strong>总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是&nbsp;<code>[0,1,2,3]</code> 。另一个正确的排序是&nbsp;<code>[0,2,1,3]</code> 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>numCourses = 1, prerequisites = []
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= numCourses * (numCourses - 1)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>所有<code>[a<sub>i</sub>, b<sub>i</sub>]</code> <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：拓扑排序

我们创建一个邻接表 $g$，用于存储每个节点的后继节点，同时还需要一个数组 $indeg$ 存储每个节点的入度。在构建邻接表的同时，我们也统计每个节点的入度。当入度为 $0$ 的节点代表没有任何前置课程，可以直接学习，我们将其加入队列 $q$ 中。

当队列 $q$ 不为空的时候，我们取出队首的节点 $i$：

-   我们将 $i$ 放入答案中；
-   接下来，我们将 $i$ 的所有后继节点的入度减少 $1$。如果发现某个后继节点 $j$ 的入度变为 $0$，则将 $j$ 放入队列 $q$ 中。

在广度优先搜索的结束时，如果答案中包含了这 $n$ 个节点，那么我们就找到了一种拓扑排序，否则说明图中存在环，也就不存在拓扑排序了。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        g = defaultdict(list)
        indeg = [0] * numCourses
        for a, b in prerequisites:
            g[b].append(a)
            indeg[a] += 1
        ans = []
        q = deque(i for i, x in enumerate(indeg) if x == 0)
        while q:
            i = q.popleft()
            ans.append(i)
            for j in g[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return ans if len(ans) == numCourses else []
```

#### Java

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] g = new List[numCourses];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (var p : prerequisites) {
            int a = p[0], b = p[1];
            g[b].add(a);
            ++indeg[a];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        int[] ans = new int[numCourses];
        int cnt = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            ans[cnt++] = i;
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return cnt == numCourses ? ans : new int[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> g(numCourses);
        vector<int> indeg(numCourses);
        for (auto& p : prerequisites) {
            int a = p[0], b = p[1];
            g[b].push_back(a);
            ++indeg[a];
        }
        queue<int> q;
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }
        vector<int> ans;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            ans.push_back(i);
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.push(j);
                }
            }
        }
        return ans.size() == numCourses ? ans : vector<int>();
    }
};
```

#### Go

```go
func findOrder(numCourses int, prerequisites [][]int) []int {
	g := make([][]int, numCourses)
	indeg := make([]int, numCourses)
	for _, p := range prerequisites {
		a, b := p[0], p[1]
		g[b] = append(g[b], a)
		indeg[a]++
	}
	q := []int{}
	for i, x := range indeg {
		if x == 0 {
			q = append(q, i)
		}
	}
	ans := []int{}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		ans = append(ans, i)
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	if len(ans) == numCourses {
		return ans
	}
	return []int{}
}
```

#### TypeScript

```ts
function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    const g: number[][] = Array.from({ length: numCourses }, () => []);
    const indeg: number[] = new Array(numCourses).fill(0);
    for (const [a, b] of prerequisites) {
        g[b].push(a);
        indeg[a]++;
    }
    const q: number[] = [];
    for (let i = 0; i < numCourses; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
        }
    }
    const ans: number[] = [];
    while (q.length) {
        const i = q.shift()!;
        ans.push(i);
        for (const j of g[i]) {
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return ans.length === numCourses ? ans : [];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_order(num_courses: i32, prerequisites: Vec<Vec<i32>>) -> Vec<i32> {
        let n = num_courses as usize;
        let mut adjacency = vec![vec![]; n];
        let mut entry = vec![0; n];
        // init
        for iter in prerequisites.iter() {
            let (a, b) = (iter[0], iter[1]);
            adjacency[b as usize].push(a);
            entry[a as usize] += 1;
        }
        // construct deque & reslut
        let mut deque = std::collections::VecDeque::new();
        for index in 0..n {
            if entry[index] == 0 {
                deque.push_back(index);
            }
        }
        let mut result = vec![];
        // bfs
        while !deque.is_empty() {
            let head = deque.pop_front().unwrap();
            result.push(head as i32);
            // update degree of entry
            for &out_entry in adjacency[head].iter() {
                entry[out_entry as usize] -= 1;
                if entry[out_entry as usize] == 0 {
                    deque.push_back(out_entry as usize);
                }
            }
        }
        if result.len() == n {
            result
        } else {
            vec![]
        }
    }
}
```

#### C#

```cs
public class Solution {
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        var g = new List<int>[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            g[i] = new List<int>();
        }
        var indeg = new int[numCourses];
        foreach (var p in prerequisites) {
            int a = p[0], b = p[1];
            g[b].Add(a);
            ++indeg[a];
        }
        var q = new Queue<int>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                q.Enqueue(i);
            }
        }
        var ans = new int[numCourses];
        var cnt = 0;
        while (q.Count > 0) {
            int i = q.Dequeue();
            ans[cnt++] = i;
            foreach (int j in g[i]) {
                if (--indeg[j] == 0) {
                    q.Enqueue(j);
                }
            }
        }
        return cnt == numCourses ? ans : new int[0];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
