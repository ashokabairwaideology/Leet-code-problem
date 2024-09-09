---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1145.Binary%20Tree%20Coloring%20Game/README_EN.md
rating: 1741
source: Weekly Contest 148 Q2
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [1145. Binary Tree Coloring Game](https://leetcode.com/problems/binary-tree-coloring-game)

[中文文档](/solution/1100-1199/1145.Binary%20Tree%20Coloring%20Game/README.md)

## Description

<!-- description:start -->

<p>Two players play a turn based game on a binary tree. We are given the <code>root</code> of this binary tree, and the number of nodes <code>n</code> in the tree. <code>n</code> is odd, and each node has a distinct value from <code>1</code> to <code>n</code>.</p>

<p>Initially, the first player names a value <code>x</code> with <code>1 &lt;= x &lt;= n</code>, and the second player names a value <code>y</code> with <code>1 &lt;= y &lt;= n</code> and <code>y != x</code>. The first player colors the node with value <code>x</code> red, and the second player colors the node with value <code>y</code> blue.</p>

<p>Then, the players take turns starting with the first player. In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an <strong>uncolored</strong> neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)</p>

<p>If (and only if) a player cannot choose such a node in this way, they must pass their turn. If both players pass their turn, the game ends, and the winner is the player that colored more nodes.</p>

<p>You are the second player. If it is possible to choose such a <code>y</code> to ensure you win the game, return <code>true</code>. If it is not possible, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1145.Binary%20Tree%20Coloring%20Game/images/1480-binary-tree-coloring-game.png" style="width: 500px; height: 310px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
<strong>Output:</strong> true
<strong>Explanation: </strong>The second player can choose the node with value 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2,3], n = 3, x = 1
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>1 &lt;= x &lt;= n &lt;= 100</code></li>
	<li><code>n</code> is odd.</li>
	<li>1 &lt;= Node.val &lt;= n</li>
	<li>All the values of the tree are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

First, we use DFS to find the node where player 1's colored point $x$ is located, denoted as $node$.

Next, we count the number of nodes in the left and right subtrees of $node$, denoted as $l$ and $r$ respectively, and the number of nodes in the direction of $node$'s parent node is $n - l - r - 1$. As long as $\max(l, r, n - l - r - 1) > \frac{n}{2}$, player 2 has a winning strategy.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the total number of nodes.

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def btreeGameWinningMove(self, root: Optional[TreeNode], n: int, x: int) -> bool:
        def dfs(root):
            if root is None or root.val == x:
                return root
            return dfs(root.left) or dfs(root.right)

        def count(root):
            if root is None:
                return 0
            return 1 + count(root.left) + count(root.right)

        node = dfs(root)
        l, r = count(node.left), count(node.right)
        return max(l, r, n - l - r - 1) > n // 2
```

#### Java

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = dfs(root, x);
        int l = count(node.left);
        int r = count(node.right);
        return Math.max(Math.max(l, r), n - l - r - 1) > n / 2;
    }

    private TreeNode dfs(TreeNode root, int x) {
        if (root == null || root.val == x) {
            return root;
        }
        TreeNode node = dfs(root.left, x);
        return node == null ? dfs(root.right, x) : node;
    }

    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }
}
```

#### C++

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool btreeGameWinningMove(TreeNode* root, int n, int x) {
        auto node = dfs(root, x);
        int l = count(node->left), r = count(node->right);
        return max({l, r, n - l - r - 1}) > n / 2;
    }

    TreeNode* dfs(TreeNode* root, int x) {
        if (!root || root->val == x) {
            return root;
        }
        auto node = dfs(root->left, x);
        return node ? node : dfs(root->right, x);
    }

    int count(TreeNode* root) {
        if (!root) {
            return 0;
        }
        return 1 + count(root->left) + count(root->right);
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func btreeGameWinningMove(root *TreeNode, n int, x int) bool {
	var dfs func(*TreeNode) *TreeNode
	dfs = func(root *TreeNode) *TreeNode {
		if root == nil || root.Val == x {
			return root
		}
		node := dfs(root.Left)
		if node != nil {
			return node
		}
		return dfs(root.Right)
	}

	var count func(*TreeNode) int
	count = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		return 1 + count(root.Left) + count(root.Right)
	}

	node := dfs(root)
	l, r := count(node.Left), count(node.Right)
	return max(max(l, r), n-l-r-1) > n/2
}
```

#### TypeScript

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function btreeGameWinningMove(root: TreeNode | null, n: number, x: number): boolean {
    const dfs = (root: TreeNode | null): TreeNode | null => {
        if (!root || root.val === x) {
            return root;
        }
        return dfs(root.left) || dfs(root.right);
    };

    const count = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    };

    const node = dfs(root);
    const l = count(node.left);
    const r = count(node.right);
    return Math.max(l, r, n - l - r - 1) > n / 2;
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} n
 * @param {number} x
 * @return {boolean}
 */
var btreeGameWinningMove = function (root, n, x) {
    const dfs = root => {
        if (!root || root.val === x) {
            return root;
        }
        return dfs(root.left) || dfs(root.right);
    };

    const count = root => {
        if (!root) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    };

    const node = dfs(root);
    const l = count(node.left);
    const r = count(node.right);
    return Math.max(l, r, n - l - r - 1) > n / 2;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
