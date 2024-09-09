---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/README_EN.md
rating: 2036
source: Weekly Contest 289 Q3
tags:
    - Array
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [2245. Maximum Trailing Zeros in a Cornered Path](https://leetcode.com/problems/maximum-trailing-zeros-in-a-cornered-path)

[中文文档](/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>grid</code> of size <code>m x n</code>, where each cell contains a positive integer.</p>

<p>A <strong>cornered path</strong> is defined as a set of adjacent cells with <strong>at most</strong> one turn. More specifically, the path should exclusively move either <strong>horizontally</strong> or <strong>vertically</strong> up to the turn (if there is one), without returning to a previously visited cell. After the turn, the path will then move exclusively in the <strong>alternate</strong> direction: move vertically if it moved horizontally, and vice versa, also without returning to a previously visited cell.</p>

<p>The <strong>product</strong> of a path is defined as the product of all the values in the path.</p>

<p>Return <em>the <strong>maximum</strong> number of <strong>trailing zeros</strong> in the product of a cornered path found in </em><code>grid</code>.</p>

<p>Note:</p>

<ul>
	<li><strong>Horizontal</strong> movement means moving in either the left or right direction.</li>
	<li><strong>Vertical</strong> movement means moving in either the up or down direction.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/images/ex1new2.jpg" style="width: 577px; height: 190px;" />
<pre>
<strong>Input:</strong> grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The grid on the left shows a valid cornered path.
It has a product of 15 * 20 * 6 * 1 * 10 = 18000 which has 3 trailing zeros.
It can be shown that this is the maximum trailing zeros in the product of a cornered path.

The grid in the middle is not a cornered path as it has more than one turn.
The grid on the right is not a cornered path as it requires a return to a previously visited cell.

</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/images/ex2.jpg" style="width: 150px; height: 157px;" />
<pre>
<strong>Input:</strong> grid = [[4,3,2],[7,6,1],[8,8,8]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The grid is shown in the figure above.
There are no cornered paths in the grid that result in a product with a trailing zero.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Enumerate Turning Point

Firstly, we need to understand that for a product, the number of trailing zeros depends on the smaller count of $2$ and $5$ in its factors. Also, each corner path should cover as many numbers as possible, so it must start from a boundary, reach a turning point, and then reach another boundary.

Therefore, we can create four two-dimensional arrays $r2$, $c2$, $r5$, $c5$ to record the counts of $2$ and $5$ in each row and column. Where:

-   `r2[i][j]` represents the count of $2$ from the first column to the $j$-th column in the $i$-th row;
-   `c2[i][j]` represents the count of $2$ from the first row to the $i$-th row in the $j$-th column;
-   `r5[i][j]` represents the count of $5$ from the first column to the $j$-th column in the $i$-th row;
-   `c5[i][j]` represents the count of $5$ from the first row to the $i$-th row in the $j$-th column.

Next, we traverse the two-dimensional array `grid`. For each number, we calculate its counts of $2$ and $5$, and then update the four two-dimensional arrays.

Then, we enumerate the turning point $(i, j)$. For each turning point, we calculate four values:

-   `a` represents the smaller count of $2$ and $5$ in the path that moves right from $(i, 1)$ to $(i, j)$, then turns and moves up to $(1, j)$;
-   `b` represents the smaller count of $2$ and $5$ in the path that moves right from $(i, 1)$ to $(i, j)$, then turns and moves down to $(m, j)$;
-   `c` represents the smaller count of $2$ and $5$ in the path that moves left from $(i, n)$ to $(i, j)$, then turns and moves up to $(1, j)$;
-   `d` represents the smaller count of $2$ and $5$ in the path that moves left from $(i, n)$ to $(i, j)$, then turns and moves down to $(m, j)$.

Each time we enumerate, we take the maximum of these four values, and then update the answer.

Finally, we return the answer.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the `grid` array, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTrailingZeros(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        r2 = [[0] * (n + 1) for _ in range(m + 1)]
        c2 = [[0] * (n + 1) for _ in range(m + 1)]
        r5 = [[0] * (n + 1) for _ in range(m + 1)]
        c5 = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid, 1):
            for j, x in enumerate(row, 1):
                s2 = s5 = 0
                while x % 2 == 0:
                    x //= 2
                    s2 += 1
                while x % 5 == 0:
                    x //= 5
                    s5 += 1
                r2[i][j] = r2[i][j - 1] + s2
                c2[i][j] = c2[i - 1][j] + s2
                r5[i][j] = r5[i][j - 1] + s5
                c5[i][j] = c5[i - 1][j] + s5
        ans = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                a = min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j])
                b = min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j])
                c = min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j])
                d = min(
                    r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j],
                    r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j],
                )
                ans = max(ans, a, b, c, d)
        return ans
```

#### Java

```java
class Solution {
    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] r2 = new int[m + 1][n + 1];
        int[][] c2 = new int[m + 1][n + 1];
        int[][] r5 = new int[m + 1][n + 1];
        int[][] c5 = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = grid[i - 1][j - 1];
                int s2 = 0, s5 = 0;
                for (; x % 2 == 0; x /= 2) {
                    ++s2;
                }
                for (; x % 5 == 0; x /= 5) {
                    ++s5;
                }
                r2[i][j] = r2[i][j - 1] + s2;
                c2[i][j] = c2[i - 1][j] + s2;
                r5[i][j] = r5[i][j - 1] + s5;
                c5[i][j] = c5[i - 1][j] + s5;
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int a = Math.min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j]);
                int b = Math.min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j]);
                int c = Math.min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j]);
                int d = Math.min(r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j],
                    r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j]);
                ans = Math.max(ans, Math.max(a, Math.max(b, Math.max(c, d))));
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
    int maxTrailingZeros(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> r2(m + 1, vector<int>(n + 1));
        vector<vector<int>> c2(m + 1, vector<int>(n + 1));
        vector<vector<int>> r5(m + 1, vector<int>(n + 1));
        vector<vector<int>> c5(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = grid[i - 1][j - 1];
                int s2 = 0, s5 = 0;
                for (; x % 2 == 0; x /= 2) {
                    ++s2;
                }
                for (; x % 5 == 0; x /= 5) {
                    ++s5;
                }
                r2[i][j] = r2[i][j - 1] + s2;
                c2[i][j] = c2[i - 1][j] + s2;
                r5[i][j] = r5[i][j - 1] + s5;
                c5[i][j] = c5[i - 1][j] + s5;
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int a = min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j]);
                int b = min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j]);
                int c = min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j]);
                int d = min(r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j], r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j]);
                ans = max({ans, a, b, c, d});
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxTrailingZeros(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	r2 := get(m+1, n+1)
	c2 := get(m+1, n+1)
	r5 := get(m+1, n+1)
	c5 := get(m+1, n+1)
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			x := grid[i-1][j-1]
			s2, s5 := 0, 0
			for ; x%2 == 0; x /= 2 {
				s2++
			}
			for ; x%5 == 0; x /= 5 {
				s5++
			}
			r2[i][j] = r2[i][j-1] + s2
			c2[i][j] = c2[i-1][j] + s2
			r5[i][j] = r5[i][j-1] + s5
			c5[i][j] = c5[i-1][j] + s5
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			a := min(r2[i][j]+c2[i-1][j], r5[i][j]+c5[i-1][j])
			b := min(r2[i][j]+c2[m][j]-c2[i][j], r5[i][j]+c5[m][j]-c5[i][j])
			c := min(r2[i][n]-r2[i][j]+c2[i][j], r5[i][n]-r5[i][j]+c5[i][j])
			d := min(r2[i][n]-r2[i][j-1]+c2[m][j]-c2[i][j], r5[i][n]-r5[i][j-1]+c5[m][j]-c5[i][j])
			ans = max(ans, max(a, max(b, max(c, d))))
		}
	}
	return
}

func get(m, n int) [][]int {
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	return f
}
```

#### TypeScript

```ts
function maxTrailingZeros(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const r2 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const c2 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const r5 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    const c5 = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            let x = grid[i - 1][j - 1];
            let s2 = 0;
            let s5 = 0;
            for (; x % 2 == 0; x = Math.floor(x / 2)) {
                ++s2;
            }
            for (; x % 5 == 0; x = Math.floor(x / 5)) {
                ++s5;
            }
            r2[i][j] = r2[i][j - 1] + s2;
            c2[i][j] = c2[i - 1][j] + s2;
            r5[i][j] = r5[i][j - 1] + s5;
            c5[i][j] = c5[i - 1][j] + s5;
        }
    }
    let ans = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            const a = Math.min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j]);
            const b = Math.min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j]);
            const c = Math.min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j]);
            const d = Math.min(
                r2[i][n] - r2[i][j - 1] + c2[m][j] - c2[i][j],
                r5[i][n] - r5[i][j - 1] + c5[m][j] - c5[i][j],
            );
            ans = Math.max(ans, a, b, c, d);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
