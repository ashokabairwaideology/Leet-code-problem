---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0419.Battleships%20in%20a%20Board/README_EN.md
tags:
    - Depth-First Search
    - Array
    - Matrix
---

<!-- problem:start -->

# [419. Battleships in a Board](https://leetcode.com/problems/battleships-in-a-board)

[中文文档](/solution/0400-0499/0419.Battleships%20in%20a%20Board/README.md)

## Description

<!-- description:start -->

<p>Given an <code>m x n</code> matrix <code>board</code> where each cell is a battleship <code>&#39;X&#39;</code> or empty <code>&#39;.&#39;</code>, return <em>the number of the <strong>battleships</strong> on</em> <code>board</code>.</p>

<p><strong>Battleships</strong> can only be placed horizontally or vertically on <code>board</code>. In other words, they can only be made of the shape <code>1 x k</code> (<code>1</code> row, <code>k</code> columns) or <code>k x 1</code> (<code>k</code> rows, <code>1</code> column), where <code>k</code> can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img height="333" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0419.Battleships%20in%20a%20Board/images/image.png" width="333" />
<pre>
<strong>Input:</strong> board = [[&quot;X&quot;,&quot;.&quot;,&quot;.&quot;,&quot;X&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;X&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;X&quot;]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> board = [[&quot;.&quot;]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>board[i][j]</code> is either <code>&#39;.&#39;</code> or <code>&#39;X&#39;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you do it in one-pass, using only <code>O(1)</code> extra memory and without modifying the values <code>board</code>?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Iteration

We can iterate through the matrix, find the top-left corner of each battleship, i.e., the position where the current position is `X` and both the top and left are not `X`, and increment the answer by one.

After the iteration ends, return the answer.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. The space complexity is $O(1)$.

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
