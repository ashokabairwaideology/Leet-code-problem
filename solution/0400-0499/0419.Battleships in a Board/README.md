---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0419.Battleships%20in%20a%20Board/README.md
tags:
    - 深度优先搜索
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [419. 棋盘上的战舰](https://leetcode.cn/problems/battleships-in-a-board)

[English Version](/solution/0400-0499/0419.Battleships%20in%20a%20Board/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>m x n</code> 的矩阵 <code>board</code> 表示棋盘，其中，每个单元格可以是一艘战舰 <code>'X'</code> 或者是一个空位 <code>'.'</code> ，返回在棋盘 <code>board</code> 上放置的 <strong>舰队</strong> 的数量。</p>

<p><strong>舰队</strong> 只能水平或者垂直放置在 <code>board</code> 上。换句话说，舰队只能按 <code>1 x k</code>（<code>1</code> 行，<code>k</code> 列）或 <code>k x 1</code>（<code>k</code> 行，<code>1</code> 列）的形状放置，其中 <code>k</code> 可以是任意大小。两个舰队之间至少有一个水平或垂直的空格分隔 （即没有相邻的舰队）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0419.Battleships%20in%20a%20Board/images/1719200420-KKnzye-image.png" style="width: 333px; height: 333px;" />
<pre>
<strong>输入：</strong>board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>board = [["."]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>board[i][j]</code> 是 <code>'.'</code> 或 <code>'X'</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以实现一次扫描算法，并只使用<strong> </strong><code>O(1)</code><strong> </strong>额外空间，并且不修改 <code>board</code> 的值来解决这个问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接遍历

我们可以遍历矩阵，找到每个战舰的左上角，即当前位置为 `X` 且上方和左方都不是 `X` 的位置，将答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countBattleships(self, board: List[List[str]]) -> int:
        m, n = len(board), len(board[0])
        ans = 0
        for i in range(m):
            for j in range(n):
                if board[i][j] == '.':
                    continue
                if i > 0 and board[i - 1][j] == 'X':
                    continue
                if j > 0 and board[i][j - 1] == 'X':
                    continue
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                ++ans;
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
    int countBattleships(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countBattleships(board [][]byte) (ans int) {
	for i, row := range board {
		for j, c := range row {
			if c == '.' {
				continue
			}
			if i > 0 && board[i-1][j] == 'X' {
				continue
			}
			if j > 0 && board[i][j-1] == 'X' {
				continue
			}
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countBattleships(board: string[][]): number {
    const m = board.length;
    const n = board[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === '.') {
                continue;
            }
            if (i && board[i - 1][j] === 'X') {
                continue;
            }
            if (j && board[i][j - 1] === 'X') {
                continue;
            }
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
