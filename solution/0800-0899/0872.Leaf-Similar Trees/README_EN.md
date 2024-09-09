---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0872.Leaf-Similar%20Trees/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [872. Leaf-Similar Trees](https://leetcode.com/problems/leaf-similar-trees)

[中文文档](/solution/0800-0899/0872.Leaf-Similar%20Trees/README.md)

## Description

<!-- description:start -->

<p>Consider all the leaves of a binary tree, from&nbsp;left to right order, the values of those&nbsp;leaves form a <strong>leaf value sequence</strong><em>.</em></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/tree.png" style="width: 400px; height: 336px;" /></p>

<p>For example, in the given tree above, the leaf value sequence is <code>(6, 7, 4, 9, 8)</code>.</p>

<p>Two binary trees are considered <em>leaf-similar</em>&nbsp;if their leaf value sequence is the same.</p>

<p>Return <code>true</code> if and only if the two given trees with head nodes <code>root1</code> and <code>root2</code> are leaf-similar.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/leaf-similar-1.jpg" style="width: 600px; height: 237px;" />
<pre>
<strong>Input:</strong> root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/leaf-similar-2.jpg" style="width: 300px; height: 110px;" />
<pre>
<strong>Input:</strong> root1 = [1,2,3], root2 = [1,3,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in each tree will be in the range <code>[1, 200]</code>.</li>
	<li>Both of the given trees will have values in the range <code>[0, 200]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
    def leafSimilar(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        def dfs(root):
            if root is None:
                return []
            ans = dfs(root.left) + dfs(root.right)
            return ans or [root.val]

        return dfs(root1) == dfs(root2)
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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = dfs(root1);
        List<Integer> l2 = dfs(root2);
        return l1.equals(l2);
    }

    private List<Integer> dfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = dfs(root.left);
        ans.addAll(dfs(root.right));
        if (ans.isEmpty()) {
            ans.add(root.val);
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
    bool leafSimilar(TreeNode* root1, TreeNode* root2) {
        return dfs(root1) == dfs(root2);
    }

    vector<int> dfs(TreeNode* root) {
        if (!root) return {};
        auto ans = dfs(root->left);
        auto right = dfs(root->right);
        ans.insert(ans.end(), right.begin(), right.end());
        if (ans.empty()) ans.push_back(root->val);
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
func leafSimilar(root1 *TreeNode, root2 *TreeNode) bool {
	var dfs func(*TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{}
		}
		ans := dfs(root.Left)
		ans = append(ans, dfs(root.Right)...)
		if len(ans) == 0 {
			ans = append(ans, root.Val)
		}
		return ans
	}
	return reflect.DeepEqual(dfs(root1), dfs(root2))
}
```

#### Rust

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::cell::RefCell;
use std::rc::Rc;
impl Solution {
    #[allow(dead_code)]
    pub fn leaf_similar(
        root1: Option<Rc<RefCell<TreeNode>>>,
        root2: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        let mut one_vec: Vec<i32> = Vec::new();
        let mut two_vec: Vec<i32> = Vec::new();

        // Initialize the two vector
        Self::traverse(&mut one_vec, root1);
        Self::traverse(&mut two_vec, root2);

        one_vec == two_vec
    }

    #[allow(dead_code)]
    fn traverse(v: &mut Vec<i32>, root: Option<Rc<RefCell<TreeNode>>>) {
        if root.is_none() {
            return;
        }
        if Self::is_leaf_node(&root) {
            v.push(root.as_ref().unwrap().borrow().val);
        }
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        Self::traverse(v, left);
        Self::traverse(v, right);
    }

    #[allow(dead_code)]
    fn is_leaf_node(node: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        node.as_ref().unwrap().borrow().left.is_none()
            && node.as_ref().unwrap().borrow().right.is_none()
    }
}
```

#### JavaScript

```js
var leafSimilar = function (root1, root2) {
    const dfs = root => {
        if (!root) {
            return [];
        }
        let ans = [...dfs(root.left), ...dfs(root.right)];
        if (!ans.length) {
            ans = [root.val];
        }
        return ans;
    };
    const l1 = dfs(root1);
    const l2 = dfs(root2);
    return l1.toString() === l2.toString();
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
