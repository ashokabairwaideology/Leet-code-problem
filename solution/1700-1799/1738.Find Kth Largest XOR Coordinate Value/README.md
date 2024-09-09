---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1738.Find%20Kth%20Largest%20XOR%20Coordinate%20Value/README.md
rating: 1671
source: 第 225 场周赛 Q3
tags:
    - 位运算
    - 数组
    - 分治
    - 矩阵
    - 前缀和
    - 快速选择
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [1738. 找出第 K 大的异或坐标值](https://leetcode.cn/problems/find-kth-largest-xor-coordinate-value)

[English Version](/solution/1700-1799/1738.Find%20Kth%20Largest%20XOR%20Coordinate%20Value/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维矩阵 <code>matrix</code> 和一个整数 <code>k</code> ，矩阵大小为&nbsp;<code>m x n</code> 由非负整数组成。</p>

<p>矩阵中坐标 <code>(a, b)</code> 的 <strong>目标值</strong>&nbsp;可以通过对所有元素 <code>matrix[i][j]</code>&nbsp;执行异或运算得到，其中&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code> 满足 <code>0 &lt;= i &lt;= a &lt; m</code> 且 <code>0 &lt;= j &lt;= b &lt; n</code>（<strong>下标从 0 开始计数</strong>）。</p>

<p>请你找出&nbsp;<code>matrix</code> 的所有坐标中第 <code>k</code> 大的目标值（<strong><code>k</code> 的值从 1 开始计数</strong>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[5,2],[1,6]], k = 1
<strong>输出：</strong>7
<strong>解释：</strong>坐标 (0,1) 的目标值是 5 XOR 2 = 7 ，为最大的目标值。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[5,2],[1,6]], k = 2
<strong>输出：</strong>5
<strong>解释：</strong>坐标 (0,0) 的目标值是 5 = 5 ，为第 2 大的目标值。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[5,2],[1,6]], k = 3
<strong>输出：</strong>4
<strong>解释：</strong>坐标 (1,0) 的目标值是 5 XOR 1 = 4 ，为第 3 大的目标值。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[5,2],[1,6]], k = 4
<strong>输出：</strong>0
<strong>解释：</strong>坐标 (1,1) 的目标值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的目标值。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>0 &lt;= matrix[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= m * n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二维前缀异或 + 排序或快速选择

我们定义一个二维前缀异或数组 $s$，其中 $s[i][j]$ 表示矩阵前 $i$ 行和前 $j$ 列的元素异或运算的结果，即：

$$
s[i][j] = \bigoplus_{0 \leq x \leq i, 0 \leq y \leq j} matrix[x][y]
$$

而 $s[i][j]$ 可以由 $s[i - 1][j]$, $s[i][j - 1]$ 和 $s[i - 1][j - 1]$ 三个元素计算得到，即：

$$
s[i][j] = s[i - 1][j] \oplus s[i][j - 1] \oplus s[i - 1][j - 1] \oplus matrix[i - 1][j - 1]
$$

我们遍历矩阵，计算出所有的 $s[i][j]$，然后将其排序，最后返回第 $k$ 大的元素即可。如果不想使用排序，也可以使用快速选择算法，这样可以优化时间复杂度。

时间复杂度 $O(m \times n \times \log (m \times n))$ 或 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthLargestValue(self, matrix: List[List[int]], k: int) -> int:
        m, n = len(matrix), len(matrix[0])
        s = [[0] * (n + 1) for _ in range(m + 1)]
        ans = []
        for i in range(m):
            for j in range(n):
                s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j]
                ans.append(s[i + 1][j + 1])
        return nlargest(k, ans)[-1]
```

#### Java

```java
class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n + 1];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j];
                ans.add(s[i + 1][j + 1]);
            }
        }
        Collections.sort(ans);
        return ans.get(ans.size() - k);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kthLargestValue(vector<vector<int>>& matrix, int k) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n + 1));
        vector<int> ans;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j];
                ans.push_back(s[i + 1][j + 1]);
            }
        }
        sort(ans.begin(), ans.end());
        return ans[ans.size() - k];
    }
};
```

#### Go

```go
func kthLargestValue(matrix [][]int, k int) int {
	m, n := len(matrix), len(matrix[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
	}
	var ans []int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			s[i+1][j+1] = s[i+1][j] ^ s[i][j+1] ^ s[i][j] ^ matrix[i][j]
			ans = append(ans, s[i+1][j+1])
		}
	}
	sort.Ints(ans)
	return ans[len(ans)-k]
}
```

#### TypeScript

```ts
function kthLargestValue(matrix: number[][], k: number): number {
    const m: number = matrix.length;
    const n: number = matrix[0].length;
    const s = Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => 0));
    const ans: number[] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j];
            ans.push(s[i + 1][j + 1]);
        }
    }
    ans.sort((a, b) => b - a);
    return ans[k - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
