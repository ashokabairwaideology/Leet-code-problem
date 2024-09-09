---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0841.Keys%20and%20Rooms/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
---

<!-- problem:start -->

# [841. Keys and Rooms](https://leetcode.com/problems/keys-and-rooms)

[中文文档](/solution/0800-0899/0841.Keys%20and%20Rooms/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> rooms labeled from <code>0</code> to <code>n - 1</code>&nbsp;and all the rooms are locked except for room <code>0</code>. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.</p>

<p>When you visit a room, you may find a set of <strong>distinct keys</strong> in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.</p>

<p>Given an array <code>rooms</code> where <code>rooms[i]</code> is the set of keys that you can obtain if you visited room <code>i</code>, return <code>true</code> <em>if you can visit <strong>all</strong> the rooms, or</em> <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[1],[2],[3],[]]
<strong>Output:</strong> true
<strong>Explanation:</strong> 
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[1,3],[3,0,1],[2],[0]]
<strong>Output:</strong> false
<strong>Explanation:</strong> We can not enter room number 2 since the only key that unlocks it is in that room.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == rooms.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= rooms[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(rooms[i].length) &lt;= 3000</code></li>
	<li><code>0 &lt;= rooms[i][j] &lt; n</code></li>
	<li>All the values of <code>rooms[i]</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Depth-First Search (DFS)

We can use the Depth-First Search (DFS) method to traverse the entire graph, count the number of reachable nodes, and use an array `vis` to mark whether the current node has been visited to prevent repeated visits.

Finally, we count the number of visited nodes. If it is the same as the total number of nodes, it means that all nodes can be visited; otherwise, there are nodes that cannot be reached.

The time complexity is $O(n + m)$, and the space complexity is $O(n)$, where $n$ is the number of nodes, and $m$ is the number of edges.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        def dfs(i: int):
            if i in vis:
                return
            vis.add(i)
            for j in rooms[i]:
                dfs(j)

        vis = set()
        dfs(0)
        return len(vis) == len(rooms)
```

#### Java

```java
class Solution {
    private int cnt;
    private boolean[] vis;
    private List<List<Integer>> g;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        g = rooms;
        vis = new boolean[g.size()];
        dfs(0);
        return cnt == g.size();
    }

    private void dfs(int i) {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        ++cnt;
        for (int j : g.get(i)) {
            dfs(j);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size();
        int cnt = 0;
        bool vis[n];
        memset(vis, false, sizeof(vis));
        function<void(int)> dfs = [&](int i) {
            if (vis[i]) {
                return;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms[i]) {
                dfs(j);
            }
        };
        dfs(0);
        return cnt == n;
    }
};
```

#### Go

```go
func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	cnt := 0
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if vis[i] {
			return
		}
		vis[i] = true
		cnt++
		for _, j := range rooms[i] {
			dfs(j)
		}
	}
	dfs(0)
	return cnt == n
}
```

#### TypeScript

```ts
function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number) => {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        for (const j of rooms[i]) {
            dfs(j);
        }
    };
    dfs(0);
    return vis.every(v => v);
}
```

#### Rust

```rust
impl Solution {
    pub fn can_visit_all_rooms(rooms: Vec<Vec<i32>>) -> bool {
        let n = rooms.len();
        let mut is_open = vec![false; n];
        let mut keys = vec![0];
        while !keys.is_empty() {
            let i = keys.pop().unwrap();
            if is_open[i] {
                continue;
            }
            is_open[i] = true;
            rooms[i].iter().for_each(|&key| keys.push(key as usize));
        }
        is_open.iter().all(|&v| v)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: BFS

We can also use the Breadth-First Search (BFS) method to traverse the entire graph. We use a hash table or an array `vis` to mark whether the current node has been visited to prevent repeated visits.

Specifically, we define a queue $q$, initially put node $0$ into the queue, and then continuously traverse the queue. Each time we take out the front node $i$ of the queue, if $i$ has been visited, we skip it directly; otherwise, we mark it as visited, and then add the nodes that $i$ can reach to the queue.

Finally, we count the number of visited nodes. If it is the same as the total number of nodes, it means that all nodes can be visited; otherwise, it means that there are unreachable nodes.

The time complexity is $O(n + m)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes, and $m$ is the number of edges.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        vis = set()
        q = deque([0])
        while q:
            i = q.popleft()
            if i in vis:
                continue
            vis.add(i)
            q.extend(j for j in rooms[i])
        return len(vis) == len(rooms)
```

#### Java

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int cnt = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms.get(i)) {
                q.offer(j);
            }
        }
        return cnt == n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size();
        vector<bool> vis(n);
        queue<int> q{{0}};
        int cnt = 0;
        while (q.size()) {
            int i = q.front();
            q.pop();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms[i]) {
                q.push(j);
            }
        }
        return cnt == n;
    }
};
```

#### Go

```go
func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	vis := make([]bool, n)
	cnt := 0
	q := []int{0}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if vis[i] {
			continue
		}
		vis[i] = true
		cnt++
		for _, j := range rooms[i] {
			q = append(q, j)
		}
	}
	return cnt == n
}
```

#### TypeScript

```ts
function canVisitAllRooms(rooms: number[][]): boolean {
    const vis = new Set<number>();
    const q: number[] = [0];

    while (q.length) {
        const i = q.pop()!;
        if (vis.has(i)) {
            continue;
        }
        vis.add(i);
        q.push(...rooms[i]);
    }

    return vis.size == rooms.length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
