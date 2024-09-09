---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2352.Equal%20Row%20and%20Column%20Pairs/README.md
rating: 1286
source: 第 303 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [2352. 相等行列对](https://leetcode.cn/problems/equal-row-and-column-pairs)

[English Version](/solution/2300-2399/2352.Equal%20Row%20and%20Column%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>n x n</code> 的整数矩阵 <code>grid</code> ，返回满足 <code>R<sub>i</sub></code><em> </em>行和<em> </em><code>C<sub>j</sub></code><em> </em>列相等的行列对<em> </em><code>(R<sub>i</sub>, C<sub>j</sub>)</code><em> </em>的数目<em>。</em></p>

<p>如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2352.Equal%20Row%20and%20Column%20Pairs/images/ex1.jpg" style="width: 150px; height: 153px;" /></p>

<pre>
<strong>输入：</strong>grid = [[3,2,1],[1,7,6],[2,7,7]]
<strong>输出：</strong>1
<strong>解释：</strong>存在一对相等行列对：
- (第 2 行，第 1 列)：[2,7,7]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2352.Equal%20Row%20and%20Column%20Pairs/images/ex2.jpg" style="width: 200px; height: 209px;" /></p>

<pre>
<strong>输入：</strong>grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
<strong>输出：</strong>3
<strong>解释：</strong>存在三对相等行列对：
- (第 0 行，第 0 列)：[3,1,2,2]
- (第 2 行, 第 2 列)：[2,4,2,2]
- (第 3 行, 第 2 列)：[2,4,2,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们直接将矩阵 $grid$ 的每一行和每一列进行比较，如果相等，那么就是一对相等行列对，答案加一。

时间复杂度 $O(n^3)$，其中 $n$ 为矩阵 $grid$ 的行数或列数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def equalPairs(self, grid: List[List[int]]) -> int:
        n = len(grid)
        ans = 0
        for i in range(n):
            for j in range(n):
                ans += all(grid[i][k] == grid[k][j] for k in range(n))
        return ans
```

#### Java

```java
class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int ok = 1;
                for (int k = 0; k < n; ++k) {
                    if (grid[i][k] != grid[k][j]) {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
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
    int equalPairs(vector<vector<int>>& grid) {
        int n = grid.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int ok = 1;
                for (int k = 0; k < n; ++k) {
                    if (grid[i][k] != grid[k][j]) {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func equalPairs(grid [][]int) (ans int) {
	for i := range grid {
		for j := range grid {
			ok := 1
			for k := range grid {
				if grid[i][k] != grid[k][j] {
					ok = 0
					break
				}
			}
			ans += ok
		}
	}
	return
}
```

#### TypeScript

```ts
function equalPairs(grid: number[][]): number {
    const n = grid.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            let ok = 1;
            for (let k = 0; k < n; ++k) {
                if (grid[i][k] !== grid[k][j]) {
                    ok = 0;
                    break;
                }
            }
            ans += ok;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
