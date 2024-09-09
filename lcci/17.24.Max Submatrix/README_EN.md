---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.24.Max%20Submatrix/README_EN.md
---

<!-- problem:start -->

# [17.24. Max Submatrix](https://leetcode.cn/problems/max-submatrix-lcci)

[中文文档](/lcci/17.24.Max%20Submatrix/README.md)

## Description

<!-- description:start -->

<p>Given an NxN matrix of positive and negative integers, write code to find the submatrix with the largest possible sum.</p>

<p>Return an array&nbsp;<code>[r1, c1, r2, c2]</code>, where&nbsp;<code>r1</code>, <code>c1</code> are the row number and the column number of the submatrix&#39;s upper left corner respectively, and&nbsp;<code>r2</code>, <code>c2</code> are the row number of and the column number of lower right corner. If there are more than one answers, return any one of them.</p>

<p><b>Note:&nbsp;</b>This problem is slightly different from the original one in the book.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:

</strong><code>[

&nbsp;  [-1,<strong>0</strong>],

&nbsp;  [0,-1]

]</code>

<strong>Output: </strong>[0,1,0,1]</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>1 &lt;= matrix.length, matrix[0].length &lt;= 200</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getMaxMatrix(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        s = [[0] * n for _ in range(m + 1)]
        for i in range(m):
            for j in range(n):
                # 构造列前缀和
                s[i + 1][j] = s[i][j] + matrix[i][j]

        mx = matrix[0][0]
        ans = [0, 0, 0, 0]
        for i1 in range(m):
            for i2 in range(i1, m):
                nums = [0] * n
                for j in range(n):
                    nums[j] = s[i2 + 1][j] - s[i1][j]

                start = 0
                f = nums[0]
                for j in range(1, n):
                    if f > 0:
                        f += nums[j]
                    else:
                        f = nums[j]
                        start = j
                    if f > mx:
                        mx = f
                        ans = [i1, start, i2, j]
        return ans
```

#### Java

```java
class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j] = s[i][j] + matrix[i][j];
            }
        }
        int mx = matrix[0][0];
        int[] ans = new int[] {0, 0, 0, 0};
        for (int i1 = 0; i1 < m; ++i1) {
            for (int i2 = i1; i2 < m; ++i2) {
                int[] nums = new int[n];
                for (int j = 0; j < n; ++j) {
                    nums[j] = s[i2 + 1][j] - s[i1][j];
                }
                int start = 0;
                int f = nums[0];
                for (int j = 1; j < n; ++j) {
                    if (f > 0) {
                        f += nums[j];
                    } else {
                        f = nums[j];
                        start = j;
                    }
                    if (f > mx) {
                        mx = f;
                        ans = new int[] {i1, start, i2, j};
                    }
                }
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
    vector<int> getMaxMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                s[i + 1][j] = s[i][j] + matrix[i][j];
        int mx = matrix[0][0];
        vector<int> ans(4);
        for (int i1 = 0; i1 < m; ++i1) {
            for (int i2 = i1; i2 < m; ++i2) {
                vector<int> nums;
                for (int j = 0; j < n; ++j)
                    nums.push_back(s[i2 + 1][j] - s[i1][j]);
                int start = 0;
                int f = nums[0];
                for (int j = 1; j < n; ++j) {
                    if (f > 0)
                        f += nums[j];
                    else {
                        f = nums[j];
                        start = j;
                    }
                    if (f > mx) {
                        mx = f;
                        ans[0] = i1;
                        ans[1] = start;
                        ans[2] = i2;
                        ans[3] = j;
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getMaxMatrix(matrix [][]int) []int {
	m, n := len(matrix), len(matrix[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			s[i+1][j] = s[i][j] + matrix[i][j]
		}
	}
	mx := matrix[0][0]
	ans := make([]int, 4)
	for i1 := 0; i1 < m; i1++ {
		for i2 := i1; i2 < m; i2++ {
			var nums []int
			for j := 0; j < n; j++ {
				nums = append(nums, s[i2+1][j]-s[i1][j])
			}
			start := 0
			f := nums[0]
			for j := 1; j < n; j++ {
				if f > 0 {
					f += nums[j]
				} else {
					f = nums[j]
					start = j
				}
				if f > mx {
					mx = f
					ans = []int{i1, start, i2, j}
				}
			}
		}
	}
	return ans
}
```

#### Swift

```swift
class Solution {
    func getMaxMatrix(_ matrix: [[Int]]) -> [Int] {
        let m = matrix.count, n = matrix[0].count
        var s = Array(repeating: Array(repeating: 0, count: n), count: m + 1)

        for i in 0..<m {
            for j in 0..<n {
                s[i + 1][j] = s[i][j] + matrix[i][j]
            }
        }

        var mx = matrix[0][0]
        var ans = [0, 0, 0, 0]

        for i1 in 0..<m {
            for i2 in i1..<m {
                var nums = [Int](repeating: 0, count: n)
                for j in 0..<n {
                    nums[j] = s[i2 + 1][j] - s[i1][j]
                }

                var start = 0
                var f = nums[0]
                for j in 1..<n {
                    if f > 0 {
                        f += nums[j]
                    } else {
                        f = nums[j]
                        start = j
                    }
                    if f > mx {
                        mx = f
                        ans = [i1, start, i2, j]
                    }
                }
            }
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
