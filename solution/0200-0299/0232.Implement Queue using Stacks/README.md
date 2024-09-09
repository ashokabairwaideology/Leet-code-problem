---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0232.Implement%20Queue%20using%20Stacks/README.md
tags:
    - 栈
    - 设计
    - 队列
---

<!-- problem:start -->

# [232. 用栈实现队列](https://leetcode.cn/problems/implement-queue-using-stacks)

[English Version](/solution/0200-0299/0232.Implement%20Queue%20using%20Stacks/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（<code>push</code>、<code>pop</code>、<code>peek</code>、<code>empty</code>）：</p>

<p>实现 <code>MyQueue</code> 类：</p>

<ul>
	<li><code>void push(int x)</code> 将元素 x 推到队列的末尾</li>
	<li><code>int pop()</code> 从队列的开头移除并返回元素</li>
	<li><code>int peek()</code> 返回队列开头的元素</li>
	<li><code>boolean empty()</code> 如果队列为空，返回 <code>true</code> ；否则，返回 <code>false</code></li>
</ul>

<p><strong>说明：</strong></p>

<ul>
	<li>你 <strong>只能</strong> 使用标准的栈操作 —— 也就是只有&nbsp;<code>push to top</code>,&nbsp;<code>peek/pop from top</code>,&nbsp;<code>size</code>, 和&nbsp;<code>is empty</code>&nbsp;操作是合法的。</li>
	<li>你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
<strong>输出：</strong>
[null, null, null, 1, 1, false]

<strong>解释：</strong>
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
</pre>

<ul>
</ul>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x &lt;= 9</code></li>
	<li>最多调用 <code>100</code> 次 <code>push</code>、<code>pop</code>、<code>peek</code> 和 <code>empty</code></li>
	<li>假设所有操作都是有效的 （例如，一个空的队列不会调用 <code>pop</code> 或者 <code>peek</code> 操作）</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你能否实现每个操作均摊时间复杂度为 <code>O(1)</code> 的队列？换句话说，执行 <code>n</code> 个操作的总时间复杂度为 <code>O(n)</code> ，即使其中一个操作可能花费较长时间。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双栈

我们使用两个栈，其中栈 `stk1`用于入队，另一个栈 `stk2` 用于出队。

入队时，直接将元素入栈 `stk1`。时间复杂度 $O(1)$。

出队时，先判断栈 `stk2` 是否为空，如果为空，则将栈 `stk1` 中的元素全部出栈并入栈 `stk2`，然后再从栈 `stk2` 中出栈一个元素。如果栈 `stk2` 不为空，则直接从栈 `stk2` 中出栈一个元素。均摊时间复杂度 $O(1)$。

获取队首元素时，先判断栈 `stk2` 是否为空，如果为空，则将栈 `stk1` 中的元素全部出栈并入栈 `stk2`，然后再从栈 `stk2` 中获取栈顶元素。如果栈 `stk2` 不为空，则直接从栈 `stk2` 中获取栈顶元素。均摊时间复杂度 $O(1)$。

判断队列是否为空时，只要判断两个栈是否都为空即可。时间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class MyQueue:
    def __init__(self):
        self.stk1 = []
        self.stk2 = []

    def push(self, x: int) -> None:
        self.stk1.append(x)

    def pop(self) -> int:
        self.move()
        return self.stk2.pop()

    def peek(self) -> int:
        self.move()
        return self.stk2[-1]

    def empty(self) -> bool:
        return not self.stk1 and not self.stk2

    def move(self):
        if not self.stk2:
            while self.stk1:
                self.stk2.append(self.stk1.pop())


# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
```

#### Java

```java
class MyQueue {
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    public MyQueue() {
    }

    public void push(int x) {
        stk1.push(x);
    }

    public int pop() {
        move();
        return stk2.pop();
    }

    public int peek() {
        move();
        return stk2.peek();
    }

    public boolean empty() {
        return stk1.isEmpty() && stk2.isEmpty();
    }

    private void move() {
        while (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

#### C++

```cpp
class MyQueue {
public:
    MyQueue() {
    }

    void push(int x) {
        stk1.push(x);
    }

    int pop() {
        move();
        int ans = stk2.top();
        stk2.pop();
        return ans;
    }

    int peek() {
        move();
        return stk2.top();
    }

    bool empty() {
        return stk1.empty() && stk2.empty();
    }

private:
    stack<int> stk1;
    stack<int> stk2;

    void move() {
        if (stk2.empty()) {
            while (!stk1.empty()) {
                stk2.push(stk1.top());
                stk1.pop();
            }
        }
    }
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue* obj = new MyQueue();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->empty();
 */
```

#### Go

```go
type MyQueue struct {
	stk1 []int
	stk2 []int
}

func Constructor() MyQueue {
	return MyQueue{[]int{}, []int{}}
}

func (this *MyQueue) Push(x int) {
	this.stk1 = append(this.stk1, x)
}

func (this *MyQueue) Pop() int {
	this.move()
	ans := this.stk2[len(this.stk2)-1]
	this.stk2 = this.stk2[:len(this.stk2)-1]
	return ans
}

func (this *MyQueue) Peek() int {
	this.move()
	return this.stk2[len(this.stk2)-1]
}

func (this *MyQueue) Empty() bool {
	return len(this.stk1) == 0 && len(this.stk2) == 0
}

func (this *MyQueue) move() {
	if len(this.stk2) == 0 {
		for len(this.stk1) > 0 {
			this.stk2 = append(this.stk2, this.stk1[len(this.stk1)-1])
			this.stk1 = this.stk1[:len(this.stk1)-1]
		}
	}
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(x);
 * param_2 := obj.Pop();
 * param_3 := obj.Peek();
 * param_4 := obj.Empty();
 */
```

#### TypeScript

```ts
class MyQueue {
    stk1: number[];
    stk2: number[];

    constructor() {
        this.stk1 = [];
        this.stk2 = [];
    }

    push(x: number): void {
        this.stk1.push(x);
    }

    pop(): number {
        this.move();
        return this.stk2.pop();
    }

    peek(): number {
        this.move();
        return this.stk2.at(-1);
    }

    empty(): boolean {
        return !this.stk1.length && !this.stk2.length;
    }

    move(): void {
        if (!this.stk2.length) {
            while (this.stk1.length) {
                this.stk2.push(this.stk1.pop()!);
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = new MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
```

#### Rust

```rust
use std::collections::VecDeque;

struct MyQueue {
    stk1: Vec<i32>,
    stk2: Vec<i32>,
}

impl MyQueue {
    fn new() -> Self {
        MyQueue {
            stk1: Vec::new(),
            stk2: Vec::new(),
        }
    }

    fn push(&mut self, x: i32) {
        self.stk1.push(x);
    }

    fn pop(&mut self) -> i32 {
        self.move_elements();
        self.stk2.pop().unwrap()
    }

    fn peek(&mut self) -> i32 {
        self.move_elements();
        *self.stk2.last().unwrap()
    }

    fn empty(&self) -> bool {
        self.stk1.is_empty() && self.stk2.is_empty()
    }

    fn move_elements(&mut self) {
        if self.stk2.is_empty() {
            while let Some(element) = self.stk1.pop() {
                self.stk2.push(element);
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
