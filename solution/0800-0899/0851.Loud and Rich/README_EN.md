---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0851.Loud%20and%20Rich/README_EN.md
tags:
    - Depth-First Search
    - Graph
    - Topological Sort
    - Array
---

<!-- problem:start -->

# [851. Loud and Rich](https://leetcode.com/problems/loud-and-rich)

[中文文档](/solution/0800-0899/0851.Loud%20and%20Rich/README.md)

## Description

<!-- description:start -->

<p>There is a group of <code>n</code> people labeled from <code>0</code> to <code>n - 1</code> where each person has a different amount of money and a different level of quietness.</p>

<p>You are given an array <code>richer</code> where <code>richer[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that <code>a<sub>i</sub></code> has more money than <code>b<sub>i</sub></code> and an integer array <code>quiet</code> where <code>quiet[i]</code> is the quietness of the <code>i<sup>th</sup></code> person. All the given data in richer are <strong>logically correct</strong> (i.e., the data will not lead you to a situation where <code>x</code> is richer than <code>y</code> and <code>y</code> is richer than <code>x</code> at the same time).</p>

<p>Return <em>an integer array </em><code>answer</code><em> where </em><code>answer[x] = y</code><em> if </em><code>y</code><em> is the least quiet person (that is, the person </em><code>y</code><em> with the smallest value of </em><code>quiet[y]</code><em>) among all people who definitely have equal to or more money than the person </em><code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
<strong>Output:</strong> [5,5,2,5,4,5,6,7]
<strong>Explanation:</strong> 
answer[0] = 5.
Person 5 has more money than 3, which has more money than 1, which has more money than 0.
The only person who is quieter (has lower quiet[x]) is person 7, but it is not clear if they have more money than person 0.
answer[7] = 7.
Among all people that definitely have equal to or more money than person 7 (which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x]) is person 7.
The other answers can be filled out with similar reasoning.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> richer = [], quiet = [0]
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == quiet.length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>0 &lt;= quiet[i] &lt; n</code></li>
	<li>All the values of <code>quiet</code> are <strong>unique</strong>.</li>
	<li><code>0 &lt;= richer.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i </sub>!= b<sub>i</sub></code></li>
	<li>All the pairs of <code>richer</code> are <strong>unique</strong>.</li>
	<li>The observations in <code>richer</code> are all logically consistent.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def loudAndRich(self, richer: List[List[int]], quiet: List[int]) -> List[int]:
        def dfs(i: int):
            if ans[i] != -1:
                return
            ans[i] = i
            for j in g[i]:
                dfs(j)
                if quiet[ans[j]] < quiet[ans[i]]:
                    ans[i] = ans[j]

        g = defaultdict(list)
        for a, b in richer:
            g[b].append(a)
        n = len(quiet)
        ans = [-1] * n
        for i in range(n):
            dfs(i)
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int n;
    private int[] quiet;
    private int[] ans;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        n = quiet.length;
        this.quiet = quiet;
        g = new List[n];
        ans = new int[n];
        Arrays.fill(ans, -1);
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var r : richer) {
            g[r[1]].add(r[0]);
        }
        for (int i = 0; i < n; ++i) {
            dfs(i);
        }
        return ans;
    }

    private void dfs(int i) {
        if (ans[i] != -1) {
            return;
        }
        ans[i] = i;
        for (int j : g[i]) {
            dfs(j);
            if (quiet[ans[j]] < quiet[ans[i]]) {
                ans[i] = ans[j];
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet) {
        int n = quiet.size();
        vector<vector<int>> g(n);
        for (auto& r : richer) {
            g[r[1]].push_back(r[0]);
        }
        vector<int> ans(n, -1);
        function<void(int)> dfs = [&](int i) {
            if (ans[i] != -1) {
                return;
            }
            ans[i] = i;
            for (int j : g[i]) {
                dfs(j);
                if (quiet[ans[j]] < quiet[ans[i]]) {
                    ans[i] = ans[j];
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            dfs(i);
        }
        return ans;
    }
};
```

#### Go

```go
func loudAndRich(richer [][]int, quiet []int) []int {
	n := len(quiet)
	g := make([][]int, n)
	ans := make([]int, n)
	for i := range g {
		ans[i] = -1
	}
	for _, r := range richer {
		a, b := r[0], r[1]
		g[b] = append(g[b], a)
	}
	var dfs func(int)
	dfs = func(i int) {
		if ans[i] != -1 {
			return
		}
		ans[i] = i
		for _, j := range g[i] {
			dfs(j)
			if quiet[ans[j]] < quiet[ans[i]] {
				ans[i] = ans[j]
			}
		}
	}
	for i := range ans {
		dfs(i)
	}
	return ans
}
```

#### TypeScript

```ts
function loudAndRich(richer: number[][], quiet: number[]): number[] {
    const n = quiet.length;
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (const [a, b] of richer) {
        g[b].push(a);
    }
    const ans: number[] = new Array(n).fill(-1);
    const dfs = (i: number) => {
        if (ans[i] != -1) {
            return ans;
        }
        ans[i] = i;
        for (const j of g[i]) {
            dfs(j);
            if (quiet[ans[j]] < quiet[ans[i]]) {
                ans[i] = ans[j];
            }
        }
    };
    for (let i = 0; i < n; ++i) {
        dfs(i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
