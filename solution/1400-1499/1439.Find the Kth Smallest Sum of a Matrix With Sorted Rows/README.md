---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1439.Find%20the%20Kth%20Smallest%20Sum%20of%20a%20Matrix%20With%20Sorted%20Rows/README.md
rating: 2133
source: 第 187 场周赛 Q4
tags:
    - 数组
    - 二分查找
    - 矩阵
    - 堆（优先队列）
---

<!-- problem:start -->

# [1439. 有序矩阵中的第 k 个最小数组和](https://leetcode.cn/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows)

[English Version](/solution/1400-1499/1439.Find%20the%20Kth%20Smallest%20Sum%20of%20a%20Matrix%20With%20Sorted%20Rows/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m&nbsp;* n</code> 的矩阵 <code>mat</code>，以及一个整数 <code>k</code> ，矩阵中的每一行都以非递减的顺序排列。</p>

<p>你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 <strong>最小</strong> 数组和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>mat = [[1,3,11],[2,4,6]], k = 5
<strong>输出：</strong>7
<strong>解释：</strong>从每一行中选出一个元素，前 k 个和最小的数组分别是：
[1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。  </pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>mat = [[1,3,11],[2,4,6]], k = 9
<strong>输出：</strong>17
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
<strong>输出：</strong>9
<strong>解释：</strong>从每一行中选出一个元素，前 k 个和最小的数组分别是：
[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。 
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>mat = [[1,1,10],[2,2,9]], k = 7
<strong>输出：</strong>12
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat.length[i]</code></li>
	<li><code>1 &lt;= m, n &lt;= 40</code></li>
	<li><code>1 &lt;= k &lt;= min(200, n ^&nbsp;m)</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 5000</code></li>
	<li><code>mat[i]</code> 是一个非递减数组</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逐行遍历 + 排序

根据题目描述，我们需要找出前 $m$ 行的所有可能数组中的第 $k$ 个最小数组和。

如果我们能够找出前 $m - 1$ 行的所有可能数组中的前 $k$ 个最小数组和，那么我们可以将第 $m$ 行的每个元素与前 $m - 1$ 行的前 $k$ 个最小数组和相加，将得到的所有结果排序后，取前 $k$ 个最小值，即为前 $m$ 行的所有可能数组中的前 $k$ 个最小值。

因此，我们可以定义一个数组 $pre$，用于存储此前遍历到的行的前 $k$ 个最小数组和，初始时 $pre$ 只有一个元素 $0$。

然后，我们遍历 $mat$ 的每一行 $cur$，将 $cur$ 中的每个元素与 $pre$ 中的每个元素相加，将得到的所有结果排序后，取前 $k$ 个最小值作为新的 $pre$。继续遍历下一行，直到遍历完所有行。

最后返回 $pre$ 中的第 $k$ 个数（下标 $k-1$）即可。

时间复杂度 $O(m \times n \times k \times \log (n \times k))$，空间复杂度 $O(n \times k)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthSmallest(self, mat: List[List[int]], k: int) -> int:
        pre = [0]
        for cur in mat:
            pre = sorted(a + b for a in pre for b in cur[:k])[:k]
        return pre[-1]
```

#### Java

```java
class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        List<Integer> pre = new ArrayList<>(k);
        List<Integer> cur = new ArrayList<>(n * k);
        pre.add(0);
        for (int[] row : mat) {
            cur.clear();
            for (int a : pre) {
                for (int b : row) {
                    cur.add(a + b);
                }
            }
            Collections.sort(cur);
            pre.clear();
            for (int i = 0; i < Math.min(k, cur.size()); ++i) {
                pre.add(cur.get(i));
            }
        }
        return pre.get(k - 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kthSmallest(vector<vector<int>>& mat, int k) {
        int pre[k];
        int cur[mat[0].size() * k];
        memset(pre, 0, sizeof pre);
        int size = 1;
        for (auto& row : mat) {
            int i = 0;
            for (int j = 0; j < size; ++j) {
                for (int& v : row) {
                    cur[i++] = pre[j] + v;
                }
            }
            sort(cur, cur + i);
            size = min(i, k);
            for (int j = 0; j < size; ++j) {
                pre[j] = cur[j];
            }
        }
        return pre[k - 1];
    }
};
```

#### Go

```go
func kthSmallest(mat [][]int, k int) int {
	pre := []int{0}
	for _, row := range mat {
		cur := []int{}
		for _, a := range pre {
			for _, b := range row {
				cur = append(cur, a+b)
			}
		}
		sort.Ints(cur)
		pre = cur[:min(k, len(cur))]
	}
	return pre[k-1]
}
```

#### TypeScript

```ts
function kthSmallest(mat: number[][], k: number): number {
    let pre: number[] = [0];
    for (const cur of mat) {
        const next: number[] = [];
        for (const a of pre) {
            for (const b of cur) {
                next.push(a + b);
            }
        }
        pre = next.sort((a, b) => a - b).slice(0, k);
    }
    return pre[k - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
