---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1116.Print%20Zero%20Even%20Odd/README.md
tags:
    - 多线程
---

<!-- problem:start -->

# [1116. 打印零与奇偶数](https://leetcode.cn/problems/print-zero-even-odd)

[English Version](/solution/1100-1199/1116.Print%20Zero%20Even%20Odd/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现有函数 <code>printNumber</code> 可以用一个整数参数调用，并输出该整数到控制台。</p>

<ul>
	<li>例如，调用 <code>printNumber(7)</code> 将会输出 <code>7</code> 到控制台。</li>
</ul>

<p>给你类 <code>ZeroEvenOdd</code> 的一个实例，该类中有三个函数：<code>zero</code>、<code>even</code> 和 <code>odd</code> 。<code>ZeroEvenOdd</code> 的相同实例将会传递给三个不同线程：</p>

<ul>
	<li><strong>线程 A：</strong>调用 <code>zero()</code> ，只输出 <code>0</code></li>
	<li><strong>线程 B：</strong>调用 <code>even()</code> ，只输出偶数</li>
	<li><strong>线程 C：</strong>调用 <code>odd()</code> ，只输出奇数</li>
</ul>

<p>修改给出的类，以输出序列 <code>"010203040506..."</code> ，其中序列的长度必须为 <code>2n</code> 。</p>

<p>实现 <code>ZeroEvenOdd</code> 类：</p>

<ul>
	<li><code>ZeroEvenOdd(int n)</code> 用数字 <code>n</code> 初始化对象，表示需要输出的数。</li>
	<li><code>void zero(printNumber)</code> 调用 <code>printNumber</code> 以输出一个 0 。</li>
	<li><code>void even(printNumber)</code> 调用<code>printNumber</code> 以输出偶数。</li>
	<li><code>void odd(printNumber)</code> 调用 <code>printNumber</code> 以输出奇数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>"0102"
<strong>解释：</strong>三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>"0102030405"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：多线程 + 信号量

我们用三个信号量 $z$, $e$, $o$ 来控制三个线程的执行顺序，其中 $z$ 的初始值为 $1$，$e$ 和 $o$ 的初始值为 $0$。

-   信号量 $x$ 控制 `zero` 函数的执行，当 $z$ 信号量的值为 $1$ 时，`zero` 函数可以执行，执行完毕后将 $z$ 信号量的值设为 $0$，并将 $e$ 信号量的值设为 $1$ 或 $o$ 信号量的值设为 $1$，具体取决于下一次需要执行的是 `even` 函数还是 `odd` 函数。
-   信号量 $e$ 控制 `even` 函数的执行，当 $e$ 信号量的值为 $1$ 时，`even` 函数可以执行，执行完毕后将 $z$ 信号量的值设为 $1$，并将 $e$ 信号量的值设为 $0$。
-   信号量 $o$ 控制 `odd` 函数的执行，当 $o$ 信号量的值为 $1$ 时，`odd` 函数可以执行，执行完毕后将 $z$ 信号量的值设为 $1$，并将 $o$ 信号量的值设为 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
from threading import Semaphore


class ZeroEvenOdd:
    def __init__(self, n):
        self.n = n
        self.z = Semaphore(1)
        self.e = Semaphore(0)
        self.o = Semaphore(0)

    # printNumber(x) outputs "x", where x is an integer.
    def zero(self, printNumber: 'Callable[[int], None]') -> None:
        for i in range(self.n):
            self.z.acquire()
            printNumber(0)
            if i % 2 == 0:
                self.o.release()
            else:
                self.e.release()

    def even(self, printNumber: 'Callable[[int], None]') -> None:
        for i in range(2, self.n + 1, 2):
            self.e.acquire()
            printNumber(i)
            self.z.release()

    def odd(self, printNumber: 'Callable[[int], None]') -> None:
        for i in range(1, self.n + 1, 2):
            self.o.acquire()
            printNumber(i)
            self.z.release()
```

#### Java

```java
class ZeroEvenOdd {
    private int n;
    private Semaphore z = new Semaphore(1);
    private Semaphore e = new Semaphore(0);
    private Semaphore o = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; ++i) {
            z.acquire(1);
            printNumber.accept(0);
            if (i % 2 == 0) {
                o.release(1);
            } else {
                e.release(1);
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            e.acquire(1);
            printNumber.accept(i);
            z.release(1);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            o.acquire(1);
            printNumber.accept(i);
            z.release(1);
        }
    }
}
```

#### C++

```cpp
#include <semaphore.h>

class ZeroEvenOdd {
private:
    int n;
    sem_t z, e, o;

public:
    ZeroEvenOdd(int n) {
        this->n = n;
        sem_init(&z, 0, 1);
        sem_init(&e, 0, 0);
        sem_init(&o, 0, 0);
    }

    // printNumber(x) outputs "x", where x is an integer.
    void zero(function<void(int)> printNumber) {
        for (int i = 0; i < n; ++i) {
            sem_wait(&z);
            printNumber(0);
            if (i % 2 == 0) {
                sem_post(&o);
            } else {
                sem_post(&e);
            }
        }
    }

    void even(function<void(int)> printNumber) {
        for (int i = 2; i <= n; i += 2) {
            sem_wait(&e);
            printNumber(i);
            sem_post(&z);
        }
    }

    void odd(function<void(int)> printNumber) {
        for (int i = 1; i <= n; i += 2) {
            sem_wait(&o);
            printNumber(i);
            sem_post(&z);
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
