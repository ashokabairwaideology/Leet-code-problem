---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0086.Partition%20List/README.md
tags:
    - 链表
    - 双指针
---

<!-- problem:start -->

# [86. 分隔链表](https://leetcode.cn/problems/partition-list)

[English Version](/solution/0000-0099/0086.Partition%20List/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个链表的头节点 <code>head</code> 和一个特定值<em> </em><code>x</code> ，请你对链表进行分隔，使得所有 <strong>小于</strong> <code>x</code> 的节点都出现在 <strong>大于或等于</strong> <code>x</code> 的节点之前。</p>

<p>你应当 <strong>保留</strong> 两个分区中每个节点的初始相对位置。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0086.Partition%20List/images/partition.jpg" style="width: 662px; height: 222px;" />
<pre>
<strong>输入：</strong>head = [1,4,3,2,5,2], x = 3
<strong>输出</strong>：[1,2,2,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [2,1], x = 2
<strong>输出</strong>：[1,2]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点的数目在范围 <code>[0, 200]</code> 内</li>
	<li><code>-100 <= Node.val <= 100</code></li>
	<li><code>-200 <= x <= 200</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们创建两个链表，一个存放小于 $x$ 的节点，另一个存放大于等于 $x$ 的节点，之后进行拼接即可。

时间复杂度 $O(n)，其中 $n$ 是原链表的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
        d1, d2 = ListNode(), ListNode()
        t1, t2 = d1, d2
        while head:
            if head.val < x:
                t1.next = head
                t1 = t1.next
            else:
                t2.next = head
                t2 = t2.next
            head = head.next
        t1.next = d2.next
        t2.next = None
        return d1.next
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
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode d1 = new ListNode();
        ListNode d2 = new ListNode();
        ListNode t1 = d1, t2 = d2;
        while (head != null) {
            if (head.val < x) {
                t1.next = head;
                t1 = t1.next;
            } else {
                t2.next = head;
                t2 = t2.next;
            }
            head = head.next;
        }
        t1.next = d2.next;
        t2.next = null;
        return d1.next;
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
class Solution {
public:
    ListNode* partition(ListNode* head, int x) {
        ListNode* d1 = new ListNode();
        ListNode* d2 = new ListNode();
        ListNode* t1 = d1;
        ListNode* t2 = d2;
        while (head) {
            if (head->val < x) {
                t1->next = head;
                t1 = t1->next;
            } else {
                t2->next = head;
                t2 = t2->next;
            }
            head = head->next;
        }
        t1->next = d2->next;
        t2->next = nullptr;
        return d1->next;
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
func partition(head *ListNode, x int) *ListNode {
	d1, d2 := &ListNode{}, &ListNode{}
	t1, t2 := d1, d2
	for head != nil {
		if head.Val < x {
			t1.Next = head
			t1 = t1.Next
		} else {
			t2.Next = head
			t2 = t2.Next
		}
		head = head.Next
	}
	t1.Next = d2.Next
	t2.Next = nil
	return d1.Next
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
impl Solution {
    pub fn partition(head: Option<Box<ListNode>>, x: i32) -> Option<Box<ListNode>> {
        let mut head = head;
        let mut d1 = Some(Box::new(ListNode::new(0)));
        let mut d2 = Some(Box::new(ListNode::new(0)));
        let (mut t1, mut t2) = (&mut d1, &mut d2);
        while let Some(mut node) = head {
            head = node.next.take();
            if node.val < x {
                t1.as_mut().unwrap().next = Some(node);
                t1 = &mut t1.as_mut().unwrap().next;
            } else {
                t2.as_mut().unwrap().next = Some(node);
                t2 = &mut t2.as_mut().unwrap().next;
            }
        }
        t1.as_mut().unwrap().next = d2.unwrap().next;
        d1.unwrap().next
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
 * @param {ListNode} head
 * @param {number} x
 * @return {ListNode}
 */
var partition = function (head, x) {
    const d1 = new ListNode();
    const d2 = new ListNode();
    let t1 = d1,
        t2 = d2;
    while (head) {
        if (head.val < x) {
            t1.next = head;
            t1 = t1.next;
        } else {
            t2.next = head;
            t2 = t2.next;
        }
        head = head.next;
    }
    t1.next = d2.next;
    t2.next = null;
    return d1.next;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
