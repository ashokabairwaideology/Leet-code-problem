---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0092.Reverse%20Linked%20List%20II/README_EN.md
tags:
    - Linked List
---

<!-- problem:start -->

# [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii)

[中文文档](/solution/0000-0099/0092.Reverse%20Linked%20List%20II/README.md)

## Description

<!-- description:start -->

<p>Given the <code>head</code> of a singly linked list and two integers <code>left</code> and <code>right</code> where <code>left &lt;= right</code>, reverse the nodes of the list from position <code>left</code> to position <code>right</code>, and return <em>the reversed list</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0092.Reverse%20Linked%20List%20II/images/rev2ex2.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5], left = 2, right = 4
<strong>Output:</strong> [1,4,3,2,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [5], left = 1, right = 1
<strong>Output:</strong> [5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is <code>n</code>.</li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>-500 &lt;= Node.val &lt;= 500</code></li>
	<li><code>1 &lt;= left &lt;= right &lt;= n</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you do it in one pass?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Define a dummy head node `dummy`, pointing to the head node `head` of the linked list. Then define a pointer `pre` pointing to `dummy`. Start traversing the linked list from the dummy head node. When you traverse to the `left` node, point `pre` to this node. Then start traversing `right - left + 1` times from this node, and insert the nodes you traverse into the back of `pre`. Finally, return `dummy.next`.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the linked list.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseBetween(
        self, head: Optional[ListNode], left: int, right: int
    ) -> Optional[ListNode]:
        if head.next is None or left == right:
            return head
        dummy = ListNode(0, head)
        pre = dummy
        for _ in range(left - 1):
            pre = pre.next
        p, q = pre, pre.next
        cur = q
        for _ in range(right - left + 1):
            t = cur.next
            cur.next = pre
            pre, cur = cur, t
        p.next = pre
        q.next = cur
        return dummy.next
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; ++i) {
            pre = pre.next;
        }
        ListNode p = pre;
        ListNode q = pre.next;
        ListNode cur = q;
        for (int i = 0; i < right - left + 1; ++i) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        p.next = pre;
        q.next = cur;
        return dummy.next;
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
    ListNode* reverseBetween(ListNode* head, int left, int right) {
        if (!head->next || left == right) {
            return head;
        }
        ListNode* dummy = new ListNode(0, head);
        ListNode* pre = dummy;
        for (int i = 0; i < left - 1; ++i) {
            pre = pre->next;
        }
        ListNode *p = pre, *q = pre->next;
        ListNode* cur = q;
        for (int i = 0; i < right - left + 1; ++i) {
            ListNode* t = cur->next;
            cur->next = pre;
            pre = cur;
            cur = t;
        }
        p->next = pre;
        q->next = cur;
        return dummy->next;
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
func reverseBetween(head *ListNode, left int, right int) *ListNode {
	if head.Next == nil || left == right {
		return head
	}
	dummy := &ListNode{0, head}
	pre := dummy
	for i := 0; i < left-1; i++ {
		pre = pre.Next
	}
	p, q := pre, pre.Next
	cur := q
	for i := 0; i < right-left+1; i++ {
		t := cur.Next
		cur.Next = pre
		pre = cur
		cur = t
	}
	p.Next = pre
	q.Next = cur
	return dummy.Next
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

function reverseBetween(head: ListNode | null, left: number, right: number): ListNode | null {
    const n = right - left;
    if (n === 0) {
        return head;
    }

    const dummy = new ListNode(0, head);
    let pre = null;
    let cur = dummy;
    for (let i = 0; i < left; i++) {
        pre = cur;
        cur = cur.next;
    }
    const h = pre;
    pre = null;
    for (let i = 0; i <= n; i++) {
        const next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
    }
    h.next.next = cur;
    h.next = pre;
    return dummy.next;
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
    pub fn reverse_between(
        head: Option<Box<ListNode>>,
        left: i32,
        right: i32,
    ) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode { val: 0, next: head }));
        let mut pre = &mut dummy;
        for _ in 1..left {
            pre = &mut pre.as_mut().unwrap().next;
        }
        let mut cur = pre.as_mut().unwrap().next.take();
        for _ in 0..right - left + 1 {
            let mut next = cur.as_mut().unwrap().next.take();
            cur.as_mut().unwrap().next = pre.as_mut().unwrap().next.take();
            pre.as_mut().unwrap().next = cur.take();
            cur = next;
        }
        for _ in 0..right - left + 1 {
            pre = &mut pre.as_mut().unwrap().next;
        }
        pre.as_mut().unwrap().next = cur;
        dummy.unwrap().next
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
 * @param {number} left
 * @param {number} right
 * @return {ListNode}
 */
var reverseBetween = function (head, left, right) {
    if (!head.next || left == right) {
        return head;
    }
    const dummy = new ListNode(0, head);
    let pre = dummy;
    for (let i = 0; i < left - 1; ++i) {
        pre = pre.next;
    }
    const p = pre;
    const q = pre.next;
    let cur = q;
    for (let i = 0; i < right - left + 1; ++i) {
        const t = cur.next;
        cur.next = pre;
        pre = cur;
        cur = t;
    }
    p.next = pre;
    q.next = cur;
    return dummy.next;
};
```

#### C#

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode ReverseBetween(ListNode head, int left, int right) {
        if (head.next == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; ++i) {
            pre = pre.next;
        }
        ListNode p = pre;
        ListNode q = pre.next;
        ListNode cur = q;
        for (int i = 0; i < right - left + 1; ++i) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        p.next = pre;
        q.next = cur;
        return dummy.next;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
