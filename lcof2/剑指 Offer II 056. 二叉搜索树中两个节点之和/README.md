---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20056.%20%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E4%B8%A4%E4%B8%AA%E8%8A%82%E7%82%B9%E4%B9%8B%E5%92%8C/README.md
---

<!-- problem:start -->

# [剑指 Offer II 056. 二叉搜索树中两个节点之和](https://leetcode.cn/problems/opLdQZ)

## 题目描述

<!-- description:start -->

<p>给定一个二叉搜索树的 <strong>根节点</strong> <code>root</code>&nbsp;和一个整数 <code>k</code> , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 <code>k</code> 。假设二叉搜索树中节点的值均唯一。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>root =<strong> </strong>[8,6,10,5,7,9,11], k = 12
<strong>输出: </strong>true
<strong>解释: </strong>节点 5 和节点 7 之和等于 12
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>root =<strong> </strong>[8,6,10,5,7,9,11], k = 22
<strong>输出: </strong>false
<strong>解释: </strong>不存在两个节点值之和为 22 的节点
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树的节点个数的范围是&nbsp;&nbsp;<code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li><code>root</code>&nbsp;为二叉搜索树</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 653 题相同：&nbsp;<a href="https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/">https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
    def findTarget(self, root: TreeNode, k: int) -> bool:
        def find(root):
            if not root:
                return False
            if k - root.val in nodes:
                return True
            nodes.add(root.val)
            return find(root.left) or find(root.right)

        nodes = set()
        return find(root)
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
    private Set<Integer> nodes;

    public boolean findTarget(TreeNode root, int k) {
        nodes = new HashSet<>();
        return find(root, k);
    }

    private boolean find(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (nodes.contains(k - root.val)) {
            return true;
        }
        nodes.add(root.val);
        return find(root.left, k) || find(root.right, k);
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
    unordered_set<int> nodes;

    bool findTarget(TreeNode* root, int k) {
        return find(root, k);
    }

    bool find(TreeNode* root, int k) {
        if (!root) return false;
        if (nodes.count(k - root->val)) return true;
        nodes.insert(root->val);
        return find(root->left, k) || find(root->right, k);
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
func findTarget(root *TreeNode, k int) bool {
	nodes := make(map[int]bool)

	var find func(root *TreeNode, k int) bool
	find = func(root *TreeNode, k int) bool {
		if root == nil {
			return false
		}
		if nodes[k-root.Val] {
			return true
		}
		nodes[root.Val] = true
		return find(root.Left, k) || find(root.Right, k)
	}
	return find(root, k)
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

function findTarget(root: TreeNode | null, k: number): boolean {
    let nodes: Set<number> = new Set();
    return find(root, k, nodes);
}

function find(root: TreeNode | null, k: number, nodes: Set<number>): boolean {
    if (!root) return false;
    if (nodes.has(k - root.val)) return true;
    nodes.add(root.val);
    return find(root.left, k, nodes) || find(root.right, k, nodes);
}
```

#### Swift

```swift
/* class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
*     init() {
*         self.val = 0
*         self.left = nil
*         self.right = nil
*     }
*     init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
*     init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
*         self.val = val
*         self.left = left
*         self.right = right
*     }
* }
*/

class Solution {
    private var nodes: Set<Int> = []

    func findTarget(_ root: TreeNode?, _ k: Int) -> Bool {
        nodes = []
        return find(root, k)
    }

    private func find(_ root: TreeNode?, _ k: Int) -> Bool {
        guard let node = root else {
            return false
        }
        if nodes.contains(k - node.val) {
            return true
        }
        nodes.insert(node.val)
        return find(node.left, k) || find(node.right, k)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
