---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [3157. Find the Level of Tree with Minimum Sum 🔒](https://leetcode.com/problems/find-the-level-of-tree-with-minimum-sum)

[中文文档](/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given the root of a binary tree <code>root</code> where each node has a value, return the level of the tree that has the <strong>minimum</strong> sum of values among all the levels (in case of a tie, return the <strong>lowest</strong> level).</p>

<p><strong>Note</strong> that the root of the tree is at level 1 and the level of any other node is its distance from the root + 1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [50,6,2,30,80,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/images/image_2024-05-17_16-15-46.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 265px; height: 129px;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [36,17,10,null,null,24]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/images/image_2024-05-17_16-14-18.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 170px; height: 135px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [5,null,5,null,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3157.Find%20the%20Level%20of%20Tree%20with%20Minimum%20Sum/images/image_2024-05-19_19-07-20.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 170px; height: 135px;" /></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

We can use Breadth-First Search (BFS) to traverse the binary tree level by level, record the sum of the node values at each level, and find the level with the smallest sum of node values, then return the level number.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes in the binary tree.

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
    def minimumLevel(self, root: Optional[TreeNode]) -> int:
        q = deque([root])
        ans = 0
        level, s = 1, inf
        while q:
            t = 0
            for _ in range(len(q)):
                node = q.popleft()
                t += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            if s > t:
                s = t
                ans = level
            level += 1
        return ans
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
    public int minimumLevel(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int ans = 0;
        long s = Long.MAX_VALUE;
        for (int level = 1; !q.isEmpty(); ++level) {
            long t = 0;
            for (int m = q.size(); m > 0; --m) {
                TreeNode node = q.poll();
                t += node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (s > t) {
                s = t;
                ans = level;
            }
        }
        return ans;
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
    int minimumLevel(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        int ans = 0;
        long long s = 1LL << 60;
        for (int level = 1; q.size(); ++level) {
            long long t = 0;
            for (int m = q.size(); m; --m) {
                TreeNode* node = q.front();
                q.pop();
                t += node->val;
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
            if (s > t) {
                s = t;
                ans = level;
            }
        }
        return ans;
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
func minimumLevel(root *TreeNode) (ans int) {
	q := []*TreeNode{root}
	s := math.MaxInt64
	for level := 1; len(q) > 0; level++ {
		t := 0
		for m := len(q); m > 0; m-- {
			node := q[0]
			q = q[1:]
			t += node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		if s > t {
			s = t
			ans = level
		}
	}
	return
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

function minimumLevel(root: TreeNode | null): number {
    const q: TreeNode[] = [root];
    let s = Infinity;
    let ans = 0;
    for (let level = 1; q.length; ++level) {
        const qq: TreeNode[] = [];
        let t = 0;
        for (const { val, left, right } of q) {
            t += val;
            left && qq.push(left);
            right && qq.push(right);
        }
        if (s > t) {
            s = t;
            ans = level;
        }
        q.splice(0, q.length, ...qq);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
