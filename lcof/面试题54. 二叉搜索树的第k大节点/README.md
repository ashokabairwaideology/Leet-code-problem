---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9854.%20%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E7%AC%ACk%E5%A4%A7%E8%8A%82%E7%82%B9/README.md
---

<!-- problem:start -->

# [面试题 54. 二叉搜索树的第 k 大节点](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/)

## 题目描述

<!-- description:start -->

<p>给定一棵二叉搜索树，请找出其中第 <code>k</code> 大的节点的值。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
&nbsp;  2
<strong>输出:</strong> 4</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
<strong>输出:</strong> 4</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li>1 ≤ k ≤ 二叉搜索树元素个数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：反序中序遍历

由于二叉搜索树的中序遍历是升序的，因此可以反序中序遍历，即先递归遍历右子树，再访问根节点，最后递归遍历左子树。

这样就可以得到一个降序的序列，第 $k$ 个节点就是第 $k$ 大的节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉搜索树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def kthLargest(self, root: TreeNode, k: int) -> int:
        def dfs(root):
            nonlocal k, ans
            if root is None or k == 0:
                return
            dfs(root.right)
            k -= 1
            if k == 0:
                ans = root.val
            dfs(root.left)

        ans = 0
        dfs(root)
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int k;
    private int ans;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null || k == 0) {
            return;
        }
        dfs(root.right);
        if (--k == 0) {
            ans = root.val;
        }
        dfs(root.left);
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int kthLargest(TreeNode* root, int k) {
        int ans = 0;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root || !k) {
                return;
            }
            dfs(root->right);
            if (--k == 0) {
                ans = root->val;
            }
            dfs(root->left);
        };
        dfs(root);
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
func kthLargest(root *TreeNode, k int) (ans int) {
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil || k == 0 {
			return
		}
		dfs(root.Right)
		k--
		if k == 0 {
			ans = root.Val
		}
		dfs(root.Left)
	}
	dfs(root)
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

function kthLargest(root: TreeNode | null, k: number): number {
    let res = 0;
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return;
        }
        dfs(root.right);
        if (--k === 0) {
            res = root.val;
            return;
        }
        dfs(root.left);
    };
    dfs(root);
    return res;
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, arr: &mut Vec<i32>) {
        if let Some(node) = root {
            let node = node.borrow();
            Solution::dfs(&node.right, arr);
            arr.push(node.val);
            Solution::dfs(&node.left, arr)
        }
    }
    pub fn kth_largest(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> i32 {
        let mut arr = vec![];
        Solution::dfs(&root, &mut arr);
        arr[(k - 1) as usize]
    }
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} k
 * @return {number}
 */
var kthLargest = function (root, k) {
    let ans = 0;
    const dfs = root => {
        if (!root || !k) {
            return;
        }
        dfs(root.right);
        if (--k == 0) {
            ans = root.val;
        }
        dfs(root.left);
    };
    dfs(root);
    return ans;
};
```

#### C#

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private int ans;
    private int k;

    public int KthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null || k == 0) {
            return;
        }
        dfs(root.right);
        if (--k == 0) {
            ans = root.val;
        }
        dfs(root.left);
    }
}
```

#### Swift

```swift
/* public class TreeNode {
*     public var val: Int
*     public var left: TreeNode?
*     public var right: TreeNode?
*     public init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
* }
*/

class Solution {
    private var k: Int = 0
    private var ans: Int = 0

    func kthLargest(_ root: TreeNode?, _ k: Int) -> Int {
        self.k = k
        dfs(root)
        return ans
    }

    private func dfs(_ root: TreeNode?) {
        guard let root = root, k > 0 else { return }
        dfs(root.right)
        k -= 1
        if k == 0 {
            ans = root.val
            return
        }
        dfs(root.left)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
