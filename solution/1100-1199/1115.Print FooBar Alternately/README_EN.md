---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1115.Print%20FooBar%20Alternately/README_EN.md
tags:
    - Concurrency
---

<!-- problem:start -->

# [1115. Print FooBar Alternately](https://leetcode.com/problems/print-foobar-alternately)

[中文文档](/solution/1100-1199/1115.Print%20FooBar%20Alternately/README.md)

## Description

<!-- description:start -->

<p>Suppose you are given the following code:</p>

<pre>
class FooBar {
  public void foo() {
    for (int i = 0; i &lt; n; i++) {
      print(&quot;foo&quot;);
    }
  }

  public void bar() {
    for (int i = 0; i &lt; n; i++) {
      print(&quot;bar&quot;);
    }
  }
}
</pre>

<p>The same instance of <code>FooBar</code> will be passed to two different threads:</p>

<ul>
	<li>thread <code>A</code> will call <code>foo()</code>, while</li>
	<li>thread <code>B</code> will call <code>bar()</code>.</li>
</ul>

<p>Modify the given program to output <code>&quot;foobar&quot;</code> <code>n</code> times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> &quot;foobar&quot;
<strong>Explanation:</strong> There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar().
&quot;foobar&quot; is being output 1 time.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> &quot;foobarfoobar&quot;
<strong>Explanation:</strong> &quot;foobar&quot; is being output 2 times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Multithreading + Semaphore

We use two semaphores $f$ and $b$ to control the execution order of the two threads, where $f$ is initially set to $1$ and $b$ is set to $0$, indicating that thread $A$ executes first.

When thread $A$ executes, it first performs the $acquire$ operation on $f$, which changes the value of $f$ to $0$. Thread $A$ then gains the right to use $f$ and can execute the $foo$ function. After that, it performs the $release$ operation on $b$, changing the value of $b$ to $1$. This allows thread $B$ to gain the right to use $b$ and execute the $bar$ function.

When thread $B$ executes, it first performs the $acquire$ operation on $b$, which changes the value of $b$ to $0$. Thread $B$ then gains the right to use $b$ and can execute the $bar$ function. After that, it performs the $release$ operation on $f$, changing the value of $f$ to $1$. This allows thread $A$ to gain the right to use $f$ and execute the $foo$ function.

Therefore, we only need to loop $n$ times, each time executing the $foo$ and $bar$ functions, first performing the $acquire$ operation, and then the $release$ operation.

The time complexity is $O(n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
from threading import Semaphore


class FooBar:
    def __init__(self, n):
        self.n = n
        self.f = Semaphore(1)
        self.b = Semaphore(0)

    def foo(self, printFoo: "Callable[[], None]") -> None:
        for _ in range(self.n):
            self.f.acquire()
            # printFoo() outputs "foo". Do not change or remove this line.
            printFoo()
            self.b.release()

    def bar(self, printBar: "Callable[[], None]") -> None:
        for _ in range(self.n):
            self.b.acquire()
            # printBar() outputs "bar". Do not change or remove this line.
            printBar()
            self.f.release()
```

#### Java

```java
class FooBar {
    private int n;
    private Semaphore f = new Semaphore(1);
    private Semaphore b = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            f.acquire(1);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            b.release(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            b.acquire(1);
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            f.release(1);
        }
    }
}
```

#### C++

```cpp
#include <semaphore.h>

class FooBar {
private:
    int n;
    sem_t f, b;

public:
    FooBar(int n) {
        this->n = n;
        sem_init(&f, 0, 1);
        sem_init(&b, 0, 0);
    }

    void foo(function<void()> printFoo) {
        for (int i = 0; i < n; i++) {
            sem_wait(&f);
            // printFoo() outputs "foo". Do not change or remove this line.
            printFoo();
            sem_post(&b);
        }
    }

    void bar(function<void()> printBar) {
        for (int i = 0; i < n; i++) {
            sem_wait(&b);
            // printBar() outputs "bar". Do not change or remove this line.
            printBar();
            sem_post(&f);
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
