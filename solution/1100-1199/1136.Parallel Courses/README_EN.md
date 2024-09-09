---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1136.Parallel%20Courses/README_EN.md
rating: 1710
source: Biweekly Contest 5 Q4
tags:
    - Graph
    - Topological Sort
---

<!-- problem:start -->

# [1136. Parallel Courses 🔒](https://leetcode.com/problems/parallel-courses)

[中文文档](/solution/1100-1199/1136.Parallel%20Courses/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>, which indicates that there are <code>n</code> courses labeled from <code>1</code> to <code>n</code>. You are also given an array <code>relations</code> where <code>relations[i] = [prevCourse<sub>i</sub>, nextCourse<sub>i</sub>]</code>, representing a prerequisite relationship between course <code>prevCourse<sub>i</sub></code> and course <code>nextCourse<sub>i</sub></code>: course <code>prevCourse<sub>i</sub></code> has to be taken before course <code>nextCourse<sub>i</sub></code>.</p>

<p>In one semester, you can take <strong>any number</strong> of courses as long as you have taken all the prerequisites in the <strong>previous</strong> semester for the courses you are taking.</p>

<p>Return <em>the <strong>minimum</strong> number of semesters needed to take all courses</em>. If there is no way to take all the courses, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1136.Parallel%20Courses/images/course1graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 3, relations = [[1,3],[2,3]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The figure above represents the given graph.
In the first semester, you can take courses 1 and 2.
In the second semester, you can take course 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1136.Parallel%20Courses/images/course2graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 3, relations = [[1,2],[2,3],[3,1]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> No course can be studied because they are prerequisites of each other.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>1 &lt;= relations.length &lt;= 5000</code></li>
	<li><code>relations[i].length == 2</code></li>
	<li><code>1 &lt;= prevCourse<sub>i</sub>, nextCourse<sub>i</sub> &lt;= n</code></li>
	<li><code>prevCourse<sub>i</sub> != nextCourse<sub>i</sub></code></li>
	<li>All the pairs <code>[prevCourse<sub>i</sub>, nextCourse<sub>i</sub>]</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Topological Sorting

We can first build a graph $g$ to represent the prerequisite relationships between courses, and count the in-degree $indeg$ of each course.

Then we enqueue the courses with an in-degree of $0$ and start topological sorting. Each time, we dequeue a course from the queue, reduce the in-degree of the courses that it points to by $1$, and if the in-degree becomes $0$ after reduction, we enqueue that course. When the queue is empty, if there are still courses that have not been completed, it means that it is impossible to complete all courses, so we return $-1$. Otherwise, we return the number of semesters required to complete all courses.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$. Here, $n$ and $m$ are the number of courses and the number of prerequisite relationships, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSemesters(self, n: int, relations: List[List[int]]) -> int:
        g = defaultdict(list)
        indeg = [0] * n
        for prev, nxt in relations:
            prev, nxt = prev - 1, nxt - 1
            g[prev].append(nxt)
            indeg[nxt] += 1
        q = deque(i for i, v in enumerate(indeg) if v == 0)
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                i = q.popleft()
                n -= 1
                for j in g[i]:
                    indeg[j] -= 1
                    if indeg[j] == 0:
                        q.append(j)
        return -1 if n else ans
```

#### Java

```java
class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] indeg = new int[n];
        for (var r : relations) {
            int prev = r[0] - 1, nxt = r[1] - 1;
            g[prev].add(nxt);
            ++indeg[nxt];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                int i = q.poll();
                --n;
                for (int j : g[i]) {
                    if (--indeg[j] == 0) {
                        q.offer(j);
                    }
                }
            }
        }
        return n == 0 ? ans : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSemesters(int n, vector<vector<int>>& relations) {
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& r : relations) {
            int prev = r[0] - 1, nxt = r[1] - 1;
            g[prev].push_back(nxt);
            ++indeg[nxt];
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                --n;
                for (int& j : g[i]) {
                    if (--indeg[j] == 0) {
                        q.push(j);
                    }
                }
            }
        }
        return n == 0 ? ans : -1;
    }
};
```

#### Go

```go
func minimumSemesters(n int, relations [][]int) (ans int) {
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, r := range relations {
		prev, nxt := r[0]-1, r[1]-1
		g[prev] = append(g[prev], nxt)
		indeg[nxt]++
	}
	q := []int{}
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		ans++
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			n--
			for _, j := range g[i] {
				indeg[j]--
				if indeg[j] == 0 {
					q = append(q, j)
				}
			}
		}
	}
	if n == 0 {
		return
	}
	return -1
}
```

#### TypeScript

```ts
function minimumSemesters(n: number, relations: number[][]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    const indeg = new Array(n).fill(0);
    for (const [prev, nxt] of relations) {
        g[prev - 1].push(nxt - 1);
        indeg[nxt - 1]++;
    }
    const q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
        }
    }
    let ans = 0;
    while (q.length) {
        ++ans;
        for (let k = q.length; k; --k) {
            const i = q.shift()!;
            --n;
            for (const j of g[i]) {
                if (--indeg[j] === 0) {
                    q.push(j);
                }
            }
        }
    }
    return n === 0 ? ans : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
