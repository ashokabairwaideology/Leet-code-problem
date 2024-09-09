---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/README_EN.md
rating: 1336
source: Weekly Contest 165 Q1
tags:
    - Array
    - Hash Table
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [1275. Find Winner on a Tic Tac Toe Game](https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game)

[中文文档](/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/README.md)

## Description

<!-- description:start -->

<p><strong>Tic-tac-toe</strong> is played by two players <code>A</code> and <code>B</code> on a <code>3 x 3</code> grid. The rules of Tic-Tac-Toe are:</p>

<ul>
	<li>Players take turns placing characters into empty squares <code>&#39; &#39;</code>.</li>
	<li>The first player <code>A</code> always places <code>&#39;X&#39;</code> characters, while the second player <code>B</code> always places <code>&#39;O&#39;</code> characters.</li>
	<li><code>&#39;X&#39;</code> and <code>&#39;O&#39;</code> characters are always placed into empty squares, never on filled ones.</li>
	<li>The game ends when there are <strong>three</strong> of the same (non-empty) character filling any row, column, or diagonal.</li>
	<li>The game also ends if all squares are non-empty.</li>
	<li>No more moves can be played if the game is over.</li>
</ul>

<p>Given a 2D integer array <code>moves</code> where <code>moves[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> move will be played on <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code>. return <em>the winner of the game if it exists</em> (<code>A</code> or <code>B</code>). In case the game ends in a draw return <code>&quot;Draw&quot;</code>. If there are still movements to play return <code>&quot;Pending&quot;</code>.</p>

<p>You can assume that <code>moves</code> is valid (i.e., it follows the rules of <strong>Tic-Tac-Toe</strong>), the grid is initially empty, and <code>A</code> will play first.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/images/xo1-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
<strong>Output:</strong> &quot;A&quot;
<strong>Explanation:</strong> A wins, they always play first.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/images/xo2-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
<strong>Output:</strong> &quot;B&quot;
<strong>Explanation:</strong> B wins.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/images/xo3-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
<strong>Output:</strong> &quot;Draw&quot;
<strong>Explanation:</strong> The game ends in a draw since there are no moves to make.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= moves.length &lt;= 9</code></li>
	<li><code>moves[i].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, col<sub>i</sub> &lt;= 2</code></li>
	<li>There are no repeated elements on <code>moves</code>.</li>
	<li><code>moves</code> follow the rules of tic tac toe.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Determine if the last player to move can win

Since all `moves` are valid, that is, there is no situation where a person continues to play after someone has won. Therefore, we only need to determine whether the last player to move can win.

We use an array `cnt` of length $8$ to record the number of moves in rows, columns, and diagonals. Where $cnt[0, 1, 2]$ represent the number of moves in the $0, 1, 2$ rows respectively, and $cnt[3, 4, 5]$ represent the number of moves in the $0, 1, 2$ columns respectively. Additionally, $cnt[6]$ and $cnt[7]$ represent the number of moves on the two diagonals respectively. During the game, if a player makes $3$ moves in a row, column, or diagonal, that player wins.

If the last player to move does not win, then we determine whether the board is full. If it is full, it is a draw; otherwise, the game is not over yet.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of `moves`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def tictactoe(self, moves: List[List[int]]) -> str:
        n = len(moves)
        cnt = [0] * 8
        for k in range(n - 1, -1, -2):
            i, j = moves[k]
            cnt[i] += 1
            cnt[j + 3] += 1
            if i == j:
                cnt[6] += 1
            if i + j == 2:
                cnt[7] += 1
            if any(v == 3 for v in cnt):
                return "B" if k & 1 else "A"
        return "Draw" if n == 9 else "Pending"
```

#### Java

```java
class Solution {
    public String tictactoe(int[][] moves) {
        int n = moves.length;
        int[] cnt = new int[8];
        for (int k = n - 1; k >= 0; k -= 2) {
            int i = moves[k][0], j = moves[k][1];
            cnt[i]++;
            cnt[j + 3]++;
            if (i == j) {
                cnt[6]++;
            }
            if (i + j == 2) {
                cnt[7]++;
            }
            if (cnt[i] == 3 || cnt[j + 3] == 3 || cnt[6] == 3 || cnt[7] == 3) {
                return k % 2 == 0 ? "A" : "B";
            }
        }
        return n == 9 ? "Draw" : "Pending";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string tictactoe(vector<vector<int>>& moves) {
        int n = moves.size();
        int cnt[8]{};
        for (int k = n - 1; k >= 0; k -= 2) {
            int i = moves[k][0], j = moves[k][1];
            cnt[i]++;
            cnt[j + 3]++;
            if (i == j) {
                cnt[6]++;
            }
            if (i + j == 2) {
                cnt[7]++;
            }
            if (cnt[i] == 3 || cnt[j + 3] == 3 || cnt[6] == 3 || cnt[7] == 3) {
                return k % 2 == 0 ? "A" : "B";
            }
        }
        return n == 9 ? "Draw" : "Pending";
    }
};
```

#### Go

```go
func tictactoe(moves [][]int) string {
	n := len(moves)
	cnt := [8]int{}
	for k := n - 1; k >= 0; k -= 2 {
		i, j := moves[k][0], moves[k][1]
		cnt[i]++
		cnt[j+3]++
		if i == j {
			cnt[6]++
		}
		if i+j == 2 {
			cnt[7]++
		}
		if cnt[i] == 3 || cnt[j+3] == 3 || cnt[6] == 3 || cnt[7] == 3 {
			if k%2 == 0 {
				return "A"
			}
			return "B"
		}
	}
	if n == 9 {
		return "Draw"
	}
	return "Pending"
}
```

#### TypeScript

```ts
function tictactoe(moves: number[][]): string {
    const n = moves.length;
    const cnt = new Array(8).fill(0);
    for (let k = n - 1; k >= 0; k -= 2) {
        const [i, j] = moves[k];
        cnt[i]++;
        cnt[j + 3]++;
        if (i == j) {
            cnt[6]++;
        }
        if (i + j == 2) {
            cnt[7]++;
        }
        if (cnt[i] == 3 || cnt[j + 3] == 3 || cnt[6] == 3 || cnt[7] == 3) {
            return k % 2 == 0 ? 'A' : 'B';
        }
    }
    return n == 9 ? 'Draw' : 'Pending';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
