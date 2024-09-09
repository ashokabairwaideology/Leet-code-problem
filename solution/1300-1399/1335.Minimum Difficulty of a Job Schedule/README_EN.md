---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1335.Minimum%20Difficulty%20of%20a%20Job%20Schedule/README_EN.md
rating: 2034
source: Weekly Contest 173 Q4
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1335. Minimum Difficulty of a Job Schedule](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule)

[中文文档](/solution/1300-1399/1335.Minimum%20Difficulty%20of%20a%20Job%20Schedule/README.md)

## Description

<!-- description:start -->

<p>You want to schedule a list of jobs in <code>d</code> days. Jobs are dependent (i.e To work on the <code>i<sup>th</sup></code> job, you have to finish all the jobs <code>j</code> where <code>0 &lt;= j &lt; i</code>).</p>

<p>You have to finish <strong>at least</strong> one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the <code>d</code> days. The difficulty of a day is the maximum difficulty of a job done on that day.</p>

<p>You are given an integer array <code>jobDifficulty</code> and an integer <code>d</code>. The difficulty of the <code>i<sup>th</sup></code> job is <code>jobDifficulty[i]</code>.</p>

<p>Return <em>the minimum difficulty of a job schedule</em>. If you cannot find a schedule for the jobs return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1335.Minimum%20Difficulty%20of%20a%20Job%20Schedule/images/untitled.png" style="width: 365px; height: 370px;" />
<pre>
<strong>Input:</strong> jobDifficulty = [6,5,4,3,2,1], d = 2
<strong>Output:</strong> 7
<strong>Explanation:</strong> First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> jobDifficulty = [9,9,9], d = 4
<strong>Output:</strong> -1
<strong>Explanation:</strong> If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> jobDifficulty = [1,1,1], d = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> The schedule is one job per day. total difficulty will be 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= jobDifficulty.length &lt;= 300</code></li>
	<li><code>0 &lt;= jobDifficulty[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= d &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the minimum difficulty to finish the first $i$ jobs within $j$ days. Initially $f[0][0] = 0$, and all other $f[i][j]$ are $\infty$.

For the $j$-th day, we can choose to finish jobs $[k,..i]$ on this day. Therefore, we have the following state transition equation:

$$
f[i][j] = \min_{k \in [1,i]} \{f[k-1][j-1] + \max_{k \leq t \leq i} \{jobDifficulty[t]\}\}
$$

The final answer is $f[n][d]$.

The time complexity is $O(n^2 \times d)$, and the space complexity is $O(n \times d)$. Here $n$ and $d$ are the number of jobs and the number of days respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDifficulty(self, jobDifficulty: List[int], d: int) -> int:
        n = len(jobDifficulty)
        f = [[inf] * (d + 1) for _ in range(n + 1)]
        f[0][0] = 0
        for i in range(1, n + 1):
            for j in range(1, min(d + 1, i + 1)):
                mx = 0
                for k in range(i, 0, -1):
                    mx = max(mx, jobDifficulty[k - 1])
                    f[i][j] = min(f[i][j], f[k - 1][j - 1] + mx)
        return -1 if f[n][d] >= inf else f[n][d]
```

#### Java

```java
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        final int inf = 1 << 30;
        int n = jobDifficulty.length;
        int[][] f = new int[n + 1][d + 1];
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= Math.min(d, i); ++j) {
                int mx = 0;
                for (int k = i; k > 0; --k) {
                    mx = Math.max(mx, jobDifficulty[k - 1]);
                    f[i][j] = Math.min(f[i][j], f[k - 1][j - 1] + mx);
                }
            }
        }
        return f[n][d] >= inf ? -1 : f[n][d];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDifficulty(vector<int>& jobDifficulty, int d) {
        int n = jobDifficulty.size();
        int f[n + 1][d + 1];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= min(d, i); ++j) {
                int mx = 0;
                for (int k = i; k; --k) {
                    mx = max(mx, jobDifficulty[k - 1]);
                    f[i][j] = min(f[i][j], f[k - 1][j - 1] + mx);
                }
            }
        }
        return f[n][d] == 0x3f3f3f3f ? -1 : f[n][d];
    }
};
```

#### Go

```go
func minDifficulty(jobDifficulty []int, d int) int {
	n := len(jobDifficulty)
	f := make([][]int, n+1)
	const inf = 1 << 30
	for i := range f {
		f[i] = make([]int, d+1)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 1; j <= min(d, i); j++ {
			mx := 0
			for k := i; k > 0; k-- {
				mx = max(mx, jobDifficulty[k-1])
				f[i][j] = min(f[i][j], f[k-1][j-1]+mx)
			}
		}
	}
	if f[n][d] == inf {
		return -1
	}
	return f[n][d]
}
```

#### TypeScript

```ts
function minDifficulty(jobDifficulty: number[], d: number): number {
    const n = jobDifficulty.length;
    const inf = 1 << 30;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(d + 1).fill(inf));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= Math.min(d, i); ++j) {
            let mx = 0;
            for (let k = i; k > 0; --k) {
                mx = Math.max(mx, jobDifficulty[k - 1]);
                f[i][j] = Math.min(f[i][j], f[k - 1][j - 1] + mx);
            }
        }
    }
    return f[n][d] < inf ? f[n][d] : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
