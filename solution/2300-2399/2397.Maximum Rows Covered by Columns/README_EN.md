---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2397.Maximum%20Rows%20Covered%20by%20Columns/README_EN.md
rating: 1718
source: Biweekly Contest 86 Q3
tags:
    - Bit Manipulation
    - Array
    - Backtracking
    - Enumeration
    - Matrix
---

<!-- problem:start -->

# [2397. Maximum Rows Covered by Columns](https://leetcode.com/problems/maximum-rows-covered-by-columns)

[中文文档](/solution/2300-2399/2397.Maximum%20Rows%20Covered%20by%20Columns/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> binary matrix <code>matrix</code> and an integer <code>numSelect</code>, which denotes the number of <strong>distinct</strong> columns you must select from <code>matrix</code>.</p>

<p>Let us consider <code>s = {c<sub>1</sub>, c<sub>2</sub>, ...., c<sub>numSelect</sub>}</code> as the set of columns selected by you. A row <code>row</code> is <strong>covered</strong> by <code>s</code> if:</p>

<ul>
	<li>For each cell <code>matrix[row][col]</code> (<code>0 &lt;= col &lt;= n - 1</code>) where <code>matrix[row][col] == 1</code>, <code>col</code> is present in <code>s</code> or,</li>
	<li><strong>No cell</strong> in <code>row</code> has a value of <code>1</code>.</li>
</ul>

<p>You need to choose <code>numSelect</code> columns such that the number of rows that are covered is <strong>maximized</strong>.</p>

<p>Return <em>the <strong>maximum</strong> number of rows that can be <strong>covered</strong> by a set of </em><code>numSelect</code><em> columns.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2397.Maximum%20Rows%20Covered%20by%20Columns/images/rowscovered.png" style="width: 240px; height: 400px;" />
<pre>
<strong>Input:</strong> matrix = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], numSelect = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> One possible way to cover 3 rows is shown in the diagram above.
We choose s = {0, 2}.
- Row 0 is covered because it has no occurrences of 1.
- Row 1 is covered because the columns with value 1, i.e. 0 and 2 are present in s.
- Row 2 is not covered because matrix[2][1] == 1 but 1 is not present in s.
- Row 3 is covered because matrix[2][2] == 1 and 2 is present in s.
Thus, we can cover three rows.
Note that s = {1, 2} will also cover 3 rows, but it can be shown that no more than three rows can be covered.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2397.Maximum%20Rows%20Covered%20by%20Columns/images/rowscovered2.png" style="height: 250px; width: 84px;" />
<pre>
<strong>Input:</strong> matrix = [[1],[0]], numSelect = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> Selecting the only column will result in both rows being covered since the entire matrix is selected.
Therefore, we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 12</code></li>
	<li><code>matrix[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= numSelect&nbsp;&lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Enumeration

First, we convert each row of the matrix into a binary number and record it in the array $rows$. Here, $rows[i]$ represents the binary number corresponding to the $i$-th row, and the $j$-th bit of this binary number $rows[i]$ represents the value of the $i$-th row and $j$-th column.

Next, we enumerate all $2^n$ column selection schemes, where $n$ is the number of columns in the matrix. For each column selection scheme, we check whether `numSelect` columns have been selected. If not, we skip it. Otherwise, we count how many rows in the matrix are covered by the selected columns, i.e., how many binary numbers $rows[i]$ are equal to the bitwise AND of $rows[i]$ and the column selection scheme $mask$. We then update the maximum number of rows.

The time complexity is $O(2^n \times m)$, and the space complexity is $O(m)$. Where $m$ and $n$ are the number of rows and columns in the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumRows(self, matrix: List[List[int]], numSelect: int) -> int:
        rows = []
        for row in matrix:
            mask = reduce(or_, (1 << j for j, x in enumerate(row) if x), 0)
            rows.append(mask)

        ans = 0
        for mask in range(1 << len(matrix[0])):
            if mask.bit_count() != numSelect:
                continue
            t = sum((x & mask) == x for x in rows)
            ans = max(ans, t)
        return ans
```

#### Java

```java
class Solution {
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length, n = matrix[0].length;
        int[] rows = new int[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    rows[i] |= 1 << j;
                }
            }
        }
        int ans = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            if (Integer.bitCount(mask) != numSelect) {
                continue;
            }
            int t = 0;
            for (int x : rows) {
                if ((x & mask) == x) {
                    ++t;
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumRows(vector<vector<int>>& matrix, int numSelect) {
        int m = matrix.size(), n = matrix[0].size();
        int rows[m];
        memset(rows, 0, sizeof(rows));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j]) {
                    rows[i] |= 1 << j;
                }
            }
        }
        int ans = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            if (__builtin_popcount(mask) != numSelect) {
                continue;
            }
            int t = 0;
            for (int x : rows) {
                t += (x & mask) == x;
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumRows(matrix [][]int, numSelect int) (ans int) {
	m, n := len(matrix), len(matrix[0])
	rows := make([]int, m)
	for i, row := range matrix {
		for j, x := range row {
			if x == 1 {
				rows[i] |= 1 << j
			}
		}
	}
	for mask := 1; mask < 1<<n; mask++ {
		if bits.OnesCount(uint(mask)) != numSelect {
			continue
		}
		t := 0
		for _, x := range rows {
			if (x & mask) == x {
				t++
			}
		}
		if ans < t {
			ans = t
		}
	}
	return
}
```

#### TypeScript

```ts
function maximumRows(matrix: number[][], numSelect: number): number {
    const [m, n] = [matrix.length, matrix[0].length];
    const rows: number[] = Array(m).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (matrix[i][j]) {
                rows[i] |= 1 << j;
            }
        }
    }
    let ans = 0;
    for (let mask = 1; mask < 1 << n; ++mask) {
        if (bitCount(mask) !== numSelect) {
            continue;
        }
        let t = 0;
        for (const x of rows) {
            if ((x & mask) === x) {
                ++t;
            }
        }
        ans = Math.max(ans, t);
    }
    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
