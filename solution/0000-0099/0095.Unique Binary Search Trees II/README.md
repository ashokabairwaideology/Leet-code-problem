---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0095.Unique%20Binary%20Search%20Trees%20II/README.md
tags:
    - 树
    - 二叉搜索树
    - 动态规划
    - 回溯
    - 二叉树
---

<!-- problem:start -->

# [95. 不同的二叉搜索树 II](https://leetcode.cn/problems/unique-binary-search-trees-ii)

[English Version](/solution/0000-0099/0095.Unique%20Binary%20Search%20Trees%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> ，请你生成并返回所有由 <code>n</code> 个节点组成且节点值从 <code>1</code> 到 <code>n</code> 互不相同的不同 <strong>二叉搜索树</strong><em> </em>。可以按 <strong>任意顺序</strong> 返回答案。</p>

<p> </p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0095.Unique%20Binary%20Search%20Trees%20II/images/uniquebstn3.jpg" style="width: 600px; height: 148px;" />
<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>[[1]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 8</code></li>
</ul>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们设计一个函数 $dfs(i, j)$，返回由 $[i, j]$ 组成的所有可行的二叉搜索树，那么答案就是 $dfs(1, n)$。

函数 $dfs(i, j)$ 的执行步骤如下：

1. 如果 $i > j$，那么说明此时没有数字可以构成二叉搜索树，返回由一个空节点组成的列表。
2. 如果 $i \leq j$，那么我们枚举 $[i, j]$ 中的数字 $v$ 作为根节点，那么根节点 $v$ 的左子树由 $[i, v - 1]$ 组成，右子树由 $[v + 1, j]$ 组成，最后将左右子树的所有组合笛卡尔积，即 $left \times right$，加上根节点 $v$，得到以 $v$ 为根节点的所有二叉搜索树。

时间复杂度 $O(n \times G(n))$，空间复杂度 $O(n \times G(n))$。其中 $G(n)$ 是卡特兰数。

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
    def generateTrees(self, n: int) -> List[Optional[TreeNode]]:
        def dfs(i: int, j: int) -> List[Optional[TreeNode]]:
            if i > j:
                return [None]
            ans = []
            for v in range(i, j + 1):
                left = dfs(i, v - 1)
                right = dfs(v + 1, j)
                for l in left:
                    for r in right:
                        ans.append(TreeNode(v, l, r))
            return ans

        return dfs(1, n)
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
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int i, int j) {
        List<TreeNode> ans = new ArrayList<>();
        if (i > j) {
            ans.add(null);
            return ans;
        }
        for (int v = i; v <= j; ++v) {
            var left = dfs(i, v - 1);
            var right = dfs(v + 1, j);
            for (var l : left) {
                for (var r : right) {
                    ans.add(new TreeNode(v, l, r));
                }
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
    vector<TreeNode*> generateTrees(int n) {
        function<vector<TreeNode*>(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return vector<TreeNode*>{nullptr};
            }
            vector<TreeNode*> ans;
            for (int v = i; v <= j; ++v) {
                auto left = dfs(i, v - 1);
                auto right = dfs(v + 1, j);
                for (auto l : left) {
                    for (auto r : right) {
                        ans.push_back(new TreeNode(v, l, r));
                    }
                }
            }
            return ans;
        };
        return dfs(1, n);
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
func generateTrees(n int) []*TreeNode {
	var dfs func(int, int) []*TreeNode
	dfs = func(i, j int) []*TreeNode {
		if i > j {
			return []*TreeNode{nil}
		}
		ans := []*TreeNode{}
		for v := i; v <= j; v++ {
			left := dfs(i, v-1)
			right := dfs(v+1, j)
			for _, l := range left {
				for _, r := range right {
					ans = append(ans, &TreeNode{v, l, r})
				}
			}
		}
		return ans
	}
	return dfs(1, n)
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

function generateTrees(n: number): Array<TreeNode | null> {
    const dfs = (i: number, j: number): Array<TreeNode | null> => {
        if (i > j) {
            return [null];
        }
        const ans: Array<TreeNode | null> = [];
        for (let v = i; v <= j; ++v) {
            const left = dfs(i, v - 1);
            const right = dfs(v + 1, j);
            for (const l of left) {
                for (const r of right) {
                    ans.push(new TreeNode(v, l, r));
                }
            }
        }
        return ans;
    };
    return dfs(1, n);
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
    pub fn generate_trees(n: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        Self::dfs(1, n)
    }

    fn dfs(i: i32, j: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut ans = Vec::new();
        if i > j {
            ans.push(None);
            return ans;
        }
        for v in i..=j {
            let left = Self::dfs(i, v - 1);
            let right = Self::dfs(v + 1, j);
            for l in &left {
                for r in &right {
                    ans.push(Some(Rc::new(RefCell::new(TreeNode {
                        val: v,
                        left: l.clone(),
                        right: r.clone(),
                    }))));
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
