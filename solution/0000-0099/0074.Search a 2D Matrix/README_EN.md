---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0074.Search%20a%202D%20Matrix/README_EN.md
tags:
    - Array
    - Binary Search
    - Matrix
---

<!-- problem:start -->

# [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix)

[中文文档](/solution/0000-0099/0074.Search%20a%202D%20Matrix/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> integer matrix <code>matrix</code> with the following two properties:</p>

<ul>
	<li>Each row is sorted in non-decreasing order.</li>
	<li>The first integer of each row is greater than the last integer of the previous row.</li>
</ul>

<p>Given an integer <code>target</code>, return <code>true</code> <em>if</em> <code>target</code> <em>is in</em> <code>matrix</code> <em>or</em> <code>false</code> <em>otherwise</em>.</p>

<p>You must write a solution in <code>O(log(m * n))</code> time complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0074.Search%20a%202D%20Matrix/images/mat.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0074.Search%20a%202D%20Matrix/images/mat2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>-10<sup>4</sup> &lt;= matrix[i][j], target &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We can logically unfold the two-dimensional matrix and then perform binary search.

The time complexity is $O(\log(m \times n))$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        m, n = len(matrix), len(matrix[0])
        left, right = 0, m * n - 1
        while left < right:
            mid = (left + right) >> 1
            x, y = divmod(mid, n)
            if matrix[x][y] >= target:
                right = mid
            else:
                left = mid + 1
        return matrix[left // n][left % n] == target
```

#### Java

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int x = mid / n, y = mid % n;
            if (matrix[x][y] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return matrix[left / n][left % n] == target;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        int left = 0, right = m * n - 1;
        while (left < right) {
            int mid = left + right >> 1;
            int x = mid / n, y = mid % n;
            if (matrix[x][y] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return matrix[left / n][left % n] == target;
    }
};
```

#### Go

```go
func searchMatrix(matrix [][]int, target int) bool {
	m, n := len(matrix), len(matrix[0])
	left, right := 0, m*n-1
	for left < right {
		mid := (left + right) >> 1
		x, y := mid/n, mid%n
		if matrix[x][y] >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return matrix[left/n][left%n] == target
}
```

#### TypeScript

```ts
function searchMatrix(matrix: number[][], target: number): boolean {
    const m = matrix.length;
    const n = matrix[0].length;
    let left = 0;
    let right = m * n;
    while (left < right) {
        const mid = (left + right) >>> 1;
        const i = Math.floor(mid / n);
        const j = mid % n;
        if (matrix[i][j] === target) {
            return true;
        }

        if (matrix[i][j] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return false;
}
```

#### Rust

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut i = 0;
        let mut j = n;
        while i < m && j > 0 {
            match matrix[i][j - 1].cmp(&target) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    i += 1;
                }
                Ordering::Greater => {
                    j -= 1;
                }
            }
        }
        false
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    const m = matrix.length,
        n = matrix[0].length;
    let left = 0,
        right = m * n - 1;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        const x = Math.floor(mid / n);
        const y = mid % n;
        if (matrix[x][y] <= target) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return matrix[Math.floor(left / n)][left % n] == target;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Search from the Bottom Left or Top Right

Here, we start searching from the bottom left corner and move towards the top right direction. We compare the current element $matrix[i][j]$ with $target$:

-   If $matrix[i][j] = target$, we have found the target value and return `true`.
-   If $matrix[i][j] > target$, all elements to the right of the current position in this row are greater than target, so we should move the pointer $i$ upwards, i.e., $i = i - 1$.
-   If $matrix[i][j] < target$, all elements above the current position in this column are less than target, so we should move the pointer $j$ to the right, i.e., $j = j + 1$.

If we still can't find $target$ after the search, return `false`.

The time complexity is $O(m + n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        m, n = len(matrix), len(matrix[0])
        i, j = m - 1, 0
        while i >= 0 and j < n:
            if matrix[i][j] == target:
                return True
            if matrix[i][j] > target:
                i -= 1
            else:
                j += 1
        return False
```

#### Java

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = m - 1, j = 0; i >= 0 && j < n;) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                --i;
            } else {
                ++j;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        for (int i = m - 1, j = 0; i >= 0 && j < n;) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target)
                --i;
            else
                ++j;
        }
        return false;
    }
};
```

#### Go

```go
func searchMatrix(matrix [][]int, target int) bool {
	m, n := len(matrix), len(matrix[0])
	for i, j := m-1, 0; i >= 0 && j < n; {
		if matrix[i][j] == target {
			return true
		}
		if matrix[i][j] > target {
			i--
		} else {
			j++
		}
	}
	return false
}
```

#### Rust

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut left = 0;
        let mut right = m * n;
        while left < right {
            let mid = left + (right - left) / 2;
            let i = mid / n;
            let j = mid % n;
            match matrix[i][j].cmp(&target) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    left = mid + 1;
                }
                Ordering::Greater => {
                    right = mid;
                }
            }
        }
        false
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    const m = matrix.length,
        n = matrix[0].length;
    for (let i = m - 1, j = 0; i >= 0 && j < n; ) {
        if (matrix[i][j] == target) {
            return true;
        }
        if (matrix[i][j] > target) {
            --i;
        } else {
            ++j;
        }
    }
    return false;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
