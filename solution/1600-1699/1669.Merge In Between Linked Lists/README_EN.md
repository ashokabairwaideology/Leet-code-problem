---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/README_EN.md
rating: 1428
source: Biweekly Contest 40 Q2
tags:
    - Linked List
---

<!-- problem:start -->

# [1669. Merge In Between Linked Lists](https://leetcode.com/problems/merge-in-between-linked-lists)

[中文文档](/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/README.md)

## Description

<!-- description:start -->

<p>You are given two linked lists: <code>list1</code> and <code>list2</code> of sizes <code>n</code> and <code>m</code> respectively.</p>

<p>Remove <code>list1</code>&#39;s nodes from the <code>a<sup>th</sup></code> node to the <code>b<sup>th</sup></code> node, and put <code>list2</code> in their place.</p>

<p>The blue edges and nodes in the following figure indicate the result:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/images/fig1.png" style="height: 130px; width: 504px;" />
<p><em>Build the result list and return its head.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/images/ll.png" style="width: 609px; height: 210px;" />
<pre>
<strong>Input:</strong> list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
<strong>Output:</strong> [10,1,13,1000000,1000001,1000002,5]
<strong>Explanation:</strong> We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1669.Merge%20In%20Between%20Linked%20Lists/images/merge_linked_list_ex2.png" style="width: 463px; height: 140px;" />
<pre>
<strong>Input:</strong> list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
<strong>Output:</strong> [0,1,1000000,1000001,1000002,1000003,1000004,6]
<strong>Explanation:</strong> The blue edges and nodes in the above figure indicate the result.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= list1.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= a &lt;= b &lt; list1.length - 1</code></li>
	<li><code>1 &lt;= list2.length &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the operations described in the problem.

In the implementation, we use two pointers $p$ and $q$, both initially pointing to the head node of `list1`.

Then we move pointers $p$ and $q$ forward, until pointer $p$ points to the node before the $a$-th node in `list1`, and pointer $q$ points to the $b$-th node in `list1`. At this point, we set the `next` pointer of $p$ to the head node of `list2`, and set the `next` pointer of the tail node of `list2` to the node pointed to by the `next` pointer of $q$. This completes the operation required by the problem.

The time complexity is $O(m + n)$, and the space complexity is $O(1)$. Where $m$ and $n$ are the lengths of `list1` and `list2` respectively.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeInBetween(
        self, list1: ListNode, a: int, b: int, list2: ListNode
    ) -> ListNode:
        p = q = list1
        for _ in range(a - 1):
            p = p.next
        for _ in range(b):
            q = q.next
        p.next = list2
        while p.next:
            p = p.next
        p.next = q.next
        q.next = None
        return list1
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
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode p = list1, q = list1;
        while (--a > 0) {
            p = p.next;
        }
        while (b-- > 0) {
            q = q.next;
        }
        p.next = list2;
        while (p.next != null) {
            p = p.next;
        }
        p.next = q.next;
        q.next = null;
        return list1;
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
    ListNode* mergeInBetween(ListNode* list1, int a, int b, ListNode* list2) {
        auto p = list1, q = list1;
        while (--a) {
            p = p->next;
        }
        while (b--) {
            q = q->next;
        }
        p->next = list2;
        while (p->next) {
            p = p->next;
        }
        p->next = q->next;
        q->next = nullptr;
        return list1;
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
func mergeInBetween(list1 *ListNode, a int, b int, list2 *ListNode) *ListNode {
	p, q := list1, list1
	for ; a > 1; a-- {
		p = p.Next
	}
	for ; b > 0; b-- {
		q = q.Next
	}
	p.Next = list2
	for p.Next != nil {
		p = p.Next
	}
	p.Next = q.Next
	q.Next = nil
	return list1
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

function mergeInBetween(
    list1: ListNode | null,
    a: number,
    b: number,
    list2: ListNode | null,
): ListNode | null {
    let p = list1;
    let q = list1;
    while (--a > 0) {
        p = p.next;
    }
    while (b-- > 0) {
        q = q.next;
    }
    p.next = list2;
    while (p.next) {
        p = p.next;
    }
    p.next = q.next;
    q.next = null;
    return list1;
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
    public ListNode MergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode p = list1, q = list1;
        while (--a > 0) {
            p = p.next;
        }
        while (b-- > 0) {
            q = q.next;
        }
        p.next = list2;
        while (p.next != null) {
            p = p.next;
        }
        p.next = q.next;
        q.next = null;
        return list1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
