---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/README.md
tags:
    - 递归
    - 链表
---

<!-- problem:start -->

# [25. K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group)

[English Version](/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你链表的头节点 <code>head</code> ，每&nbsp;<code>k</code><em>&nbsp;</em>个节点一组进行翻转，请你返回修改后的链表。</p>

<p><code>k</code> 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是&nbsp;<code>k</code><em>&nbsp;</em>的整数倍，那么请将最后剩余的节点保持原有顺序。</p>

<p>你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 2
<strong>输出：</strong>[2,1,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex2.jpg" style="width: 542px; height: 222px;" /></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 3
<strong>输出：</strong>[3,2,1,4,5]
</pre>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li>链表中的节点数目为 <code>n</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 5000</code></li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个只用 <code>O(1)</code> 额外内存空间的算法解决此问题吗？</p>

<ul>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：迭代

时间复杂度为 $O(n)$，空间复杂度为 $O(1)$，其中 $n$ 是链表的长度。

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        def reverseList(head):
            pre, p = None, head
            while p:
                q = p.next
                p.next = pre
                pre = p
                p = q
            return pre

        dummy = ListNode(next=head)
        pre = cur = dummy
        while cur.next:
            for _ in range(k):
                cur = cur.next
                if cur is None:
                    return dummy.next
            t = cur.next
            cur.next = None
            start = pre.next
            pre.next = reverseList(start)
            start.next = t
            pre = start
            cur = pre
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = dummy;
        while (cur.next != null) {
            for (int i = 0; i < k && cur != null; ++i) {
                cur = cur.next;
            }
            if (cur == null) {
                return dummy.next;
            }
            ListNode t = cur.next;
            cur.next = null;
            ListNode start = pre.next;
            pre.next = reverseList(start);
            start.next = t;
            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }
}
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
func reverseKGroup(head *ListNode, k int) *ListNode {
	var dummy *ListNode = &ListNode{}
	p, cur := dummy, head
	for cur != nil {
		start := cur
		for i := 0; i < k; i++ {
			if cur == nil {
				p.Next = start
				return dummy.Next
			}
			cur = cur.Next
		}
		p.Next, p = reverse(start, cur), start
	}
	return dummy.Next
}

func reverse(start, end *ListNode) *ListNode {
	var pre *ListNode = nil
	for start != end {
		tmp := start.Next
		start.Next, pre = pre, start
		start = tmp
	}
	return pre
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

function reverseKGroup(head: ListNode | null, k: number): ListNode | null {
    let dummy = new ListNode(0, head);
    let pre = dummy;
    // pre->head-> ... ->tail-> next
    while (head != null) {
        let tail = pre;
        for (let i = 0; i < k; ++i) {
            tail = tail.next;
            if (tail == null) {
                return dummy.next;
            }
        }
        let t = tail.next;
        [head, tail] = reverse(head, tail);
        // set next
        pre.next = head;
        tail.next = t;
        // set new pre and new head
        pre = tail;
        head = t;
    }
    return dummy.next;
}

function reverse(head: ListNode, tail: ListNode) {
    let cur = head;
    let pre = tail.next;
    // head -> next -> ... -> tail -> pre
    while (pre != tail) {
        let t = cur.next;
        cur.next = pre;
        pre = cur;
        cur = t;
    }
    return [tail, head];
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
    pub fn reverse_k_group(head: Option<Box<ListNode>>, k: i32) -> Option<Box<ListNode>> {
        fn reverse(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
            let mut head = head;
            let mut pre = None;
            while let Some(mut node) = head {
                head = node.next.take();
                node.next = pre.take();
                pre = Some(node);
            }
            pre
        }

        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut pre = &mut dummy;
        let mut cur = head;
        while cur.is_some() {
            let mut q = &mut cur;
            for _ in 0..k - 1 {
                if q.is_none() {
                    break;
                }
                q = &mut q.as_mut().unwrap().next;
            }
            if q.is_none() {
                pre.as_mut().unwrap().next = cur;
                return dummy.unwrap().next;
            }

            let b = q.as_mut().unwrap().next.take();
            pre.as_mut().unwrap().next = reverse(cur);
            while pre.is_some() && pre.as_mut().unwrap().next.is_some() {
                pre = &mut pre.as_mut().unwrap().next;
            }
            cur = b;
        }
        dummy.unwrap().next
    }
}
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
    public ListNode ReverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = dummy;
        while (cur.next != null)
        {
            for (int i = 0; i < k && cur != null; ++i)
            {
                cur = cur.next;
            }
            if (cur == null)
            {
                return dummy.next;
            }
            ListNode t = cur.next;
            cur.next = null;
            ListNode start = pre.next;
            pre.next = ReverseList(start);
            start.next = t;
            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    private ListNode ReverseList(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null)
        {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }
}
```

#### PHP

```php
# Definition for singly-linked list.
# class ListNode {
#     public $val;
#     public $next;
#     public function __construct($val = 0, $next = null)
#     {
#         $this->val = $val;
#         $this->next = $next;
#     }
# }

class Solution {
    /**
     * @param ListNode $head
     * @param int $k
     * @return ListNode
     */

    function reverseKGroup($head, $k) {
        $dummy = new ListNode(0);
        $dummy->next = $head;
        $prevGroupTail = $dummy;

        while ($head !== null) {
            $count = 0;
            $groupHead = $head;
            $groupTail = $head;

            while ($count < $k && $head !== null) {
                $head = $head->next;
                $count++;
            }
            if ($count < $k) {
                $prevGroupTail->next = $groupHead;
                break;
            }

            $prev = null;
            for ($i = 0; $i < $k; $i++) {
                $next = $groupHead->next;
                $groupHead->next = $prev;
                $prev = $groupHead;
                $groupHead = $next;
            }
            $prevGroupTail->next = $prev;
            $prevGroupTail = $groupTail;
        }

        return $dummy->next;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：递归

时间复杂度为 $O(n)$，空间复杂度为 $O(\log _k n)$，其中 $n$ 是链表的长度。

<!-- tabs:start -->

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseKGroup(head *ListNode, k int) *ListNode {
	start, end := head, head
	for i := 0; i < k; i++ {
		if end == nil {
			return head
		}
		end = end.Next
	}
	res := reverse(start, end)
	start.Next = reverseKGroup(end, k)
	return res
}

func reverse(start, end *ListNode) *ListNode {
	var pre *ListNode = nil
	for start != end {
		tmp := start.Next
		start.Next, pre = pre, start
		start = tmp
	}
	return pre
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

function reverseKGroup(head: ListNode | null, k: number): ListNode | null {
    if (k === 1) {
        return head;
    }

    const dummy = new ListNode(0, head);
    let root = dummy;
    while (root != null) {
        let pre = root;
        let cur = root;

        let count = 0;
        while (count !== k) {
            count++;
            cur = cur.next;
            if (cur == null) {
                return dummy.next;
            }
        }

        const nextRoot = pre.next;
        pre.next = cur;

        let node = nextRoot;
        let next = node.next;
        node.next = cur.next;
        while (node != cur) {
            [next.next, node, next] = [node, next, next.next];
        }
        root = nextRoot;
    }

    return dummy.next;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
