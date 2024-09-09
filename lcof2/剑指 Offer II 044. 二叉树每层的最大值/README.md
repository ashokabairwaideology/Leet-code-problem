---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20044.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E6%AF%8F%E5%B1%82%E7%9A%84%E6%9C%80%E5%A4%A7%E5%80%BC/README.md
---

<!-- problem:start -->

# [剑指 Offer II 044. 二叉树每层的最大值](https://leetcode.cn/problems/hPov7L)

## 题目描述

<!-- description:start -->

<p>给定一棵二叉树的根节点&nbsp;<code>root</code> ，请找出该二叉树中每一层的最大值。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>

<pre>
<strong>输入: </strong>root = [1,3,2,5,3,null,9]
<strong>输出: </strong>[1,3,9]
<strong>解释:</strong>
          1
         / \
        3   2
       / \   \  
      5   3   9 
</pre>

<p><strong>示例2：</strong></p>

<pre>
<strong>输入: </strong>root = [1,2,3]
<strong>输出: </strong>[1,3]
<strong>解释:</strong>
          1
         / \
        2   3
</pre>

<p><strong>示例3：</strong></p>

<pre>
<strong>输入: </strong>root = [1]
<strong>输出: </strong>[1]
</pre>

<p><strong>示例4：</strong></p>

<pre>
<strong>输入: </strong>root = [1,null,2]
<strong>输出: </strong>[1,2]
<strong>解释:</strong>      
&nbsp;          1 
&nbsp;           \
&nbsp;            2     
</pre>

<p><strong>示例5：</strong></p>

<pre>
<strong>输入: </strong>root = []
<strong>输出: </strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[0,10<sup>4</sup>]</code></li>
	<li><meta charset="UTF-8" /><code>-2<sup>31</sup>&nbsp;&lt;= Node.val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 515&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/find-largest-value-in-each-tree-row/">https://leetcode.cn/problems/find-largest-value-in-each-tree-row/</a></p>

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
    def largestValues(self, root: TreeNode) -> List[int]:
        if root is None:
            return []
        q = deque([root])
        ans = []
        while q:
            t = -inf
            for _ in range(len(q)):
                node = q.popleft()
                t = max(t, node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            ans.append(t)
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int t = Integer.MIN_VALUE;
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.poll();
                t = Math.max(t, node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(t);
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
    vector<int> largestValues(TreeNode* root) {
        if (!root) return {};
        queue<TreeNode*> q{{root}};
        vector<int> ans;
        while (!q.empty()) {
            int t = INT_MIN;
            for (int i = q.size(); i > 0; --i) {
                auto node = q.front();
                q.pop();
                t = max(t, node->val);
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            ans.push_back(t);
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
func largestValues(root *TreeNode) []int {
	var ans []int
	if root == nil {
		return ans
	}
	var q = []*TreeNode{root}
	for len(q) > 0 {
		t := math.MinInt32
		for i := len(q); i > 0; i-- {
			node := q[0]
			q = q[1:]
			t = max(t, node.Val)
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		ans = append(ans, t)
	}
	return ans
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
    func largestValues(_ root: TreeNode?) -> [Int] {
        var ans = [Int]()
        guard let root = root else {
            return ans
        }
        var q = [TreeNode]()
        q.append(root)
        while !q.isEmpty {
            var t = Int.min
            for _ in 0..<q.count {
                let node = q.removeFirst()
                t = max(t, node.val)
                if let left = node.left {
                    q.append(left)
                }
                if let right = node.right {
                    q.append(right)
                }
            }
            ans.append(t)
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
