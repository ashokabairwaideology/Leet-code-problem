---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0109.Convert%20Sorted%20List%20to%20Binary%20Search%20Tree/README.md
tags:
    - 树
    - 二叉搜索树
    - 链表
    - 分治
    - 二叉树
---

<!-- problem:start -->

# [109. 有序链表转换二叉搜索树](https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree)

[English Version](/solution/0100-0199/0109.Convert%20Sorted%20List%20to%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个单链表的头节点 &nbsp;<code>head</code>&nbsp;，其中的元素 <strong>按升序排序</strong> ，将其转换为 <span data-keyword="height-balanced">平衡</span> 二叉搜索树。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0109.Convert%20Sorted%20List%20to%20Binary%20Search%20Tree/images/linked.jpg" style="height: 388px; width: 500px;" /></p>

<pre>
<strong>输入:</strong> head = [-10,-3,0,5,9]
<strong>输出:</strong> [0,-3,9,-10,null,5]
<strong>解释:</strong> 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> head = []
<strong>输出:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>head</code>&nbsp;中的节点数在<code>[0, 2 * 10<sup>4</sup>]</code>&nbsp;范围内</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedListToBST(self, head: ListNode) -> TreeNode:
        def buildBST(nums, start, end):
            if start > end:
                return None
            mid = (start + end) >> 1
            return TreeNode(
                nums[mid], buildBST(nums, start, mid - 1), buildBST(nums, mid + 1, end)
            )

        nums = []
        while head:
            nums.append(head.val)
            head = head.next
        return buildBST(nums, 0, len(nums) - 1)
```

#### Java

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        for (; head != null; head = head.next) {
            nums.add(head.val);
        }
        return buildBST(nums, 0, nums.size() - 1);
    }

    private TreeNode buildBST(List<Integer> nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) >> 1;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = buildBST(nums, start, mid - 1);
        root.right = buildBST(nums, mid + 1, end);
        return root;
    }
}
```

#### C++

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
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
    TreeNode* sortedListToBST(ListNode* head) {
        vector<int> nums;
        for (; head != nullptr; head = head->next) {
            nums.push_back(head->val);
        }
        return buildBST(nums, 0, nums.size() - 1);
    }

private:
    TreeNode* buildBST(vector<int>& nums, int start, int end) {
        if (start > end) {
            return nullptr;
        }
        int mid = (start + end) / 2;
        TreeNode* root = new TreeNode(nums[mid]);
        root->left = buildBST(nums, start, mid - 1);
        root->right = buildBST(nums, mid + 1, end);
        return root;
    }
};
```

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sortedListToBST(head *ListNode) *TreeNode {
	nums := []int{}
	for head != nil {
		nums = append(nums, head.Val)
		head = head.Next
	}
	return buildBST(nums, 0, len(nums)-1)
}

func buildBST(nums []int, start, end int) *TreeNode {
	if start > end {
		return nil
	}
	mid := (start + end) >> 1
	return &TreeNode{
		Val:   nums[mid],
		Left:  buildBST(nums, start, mid-1),
		Right: buildBST(nums, mid+1, end),
	}
}
```

#### TypeScript

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

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

const find = (start: ListNode | null, end: ListNode | null) => {
    let fast = start;
    let slow = start;
    while (fast !== end && fast.next !== end) {
        fast = fast.next.next;
        slow = slow.next;
    }
    return slow;
};

const build = (start: ListNode | null, end: ListNode | null) => {
    if (start == end) {
        return null;
    }
    const node = find(start, end);
    return new TreeNode(node.val, build(start, node), build(node.next, end));
};

function sortedListToBST(head: ListNode | null): TreeNode | null {
    return build(head, null);
}
```

#### Rust

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
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
    fn build(vals: &Vec<i32>, start: usize, end: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if start == end {
            return None;
        }
        let mid = (start + end) >> 1;
        Some(Rc::new(RefCell::new(TreeNode {
            val: vals[mid],
            left: Self::build(vals, start, mid),
            right: Self::build(vals, mid + 1, end),
        })))
    }

    pub fn sorted_list_to_bst(head: Option<Box<ListNode>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut vals = Vec::new();
        let mut cur = &head;
        while let Some(node) = cur {
            vals.push(node.val);
            cur = &node.next;
        }
        Self::build(&vals, 0, vals.len())
    }
}
```

#### JavaScript

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {ListNode} head
 * @return {TreeNode}
 */
var sortedListToBST = function (head) {
    const buildBST = (nums, start, end) => {
        if (start > end) {
            return null;
        }
        const mid = (start + end) >> 1;
        const root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, start, mid - 1);
        root.right = buildBST(nums, mid + 1, end);
        return root;
    };

    const nums = new Array();
    for (; head != null; head = head.next) {
        nums.push(head.val);
    }
    return buildBST(nums, 0, nums.length - 1);
};
```

#### C

```c
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
struct ListNode* find(struct ListNode* start, struct ListNode* end) {
    struct ListNode* fast = start;
    struct ListNode* slow = start;
    while (fast != end && fast->next != end) {
        fast = fast->next->next;
        slow = slow->next;
    }
    return slow;
}

struct TreeNode* bulid(struct ListNode* start, struct ListNode* end) {
    if (start == end) {
        return NULL;
    }
    struct ListNode* node = find(start, end);
    struct TreeNode* ans = malloc(sizeof(struct TreeNode));
    ans->val = node->val;
    ans->left = bulid(start, node);
    ans->right = bulid(node->next, end);
    return ans;
}

struct TreeNode* sortedListToBST(struct ListNode* head) {
    return bulid(head, NULL);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
