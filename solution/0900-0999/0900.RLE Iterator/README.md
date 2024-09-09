---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0900.RLE%20Iterator/README.md
tags:
    - 设计
    - 数组
    - 计数
    - 迭代器
---

<!-- problem:start -->

# [900. RLE 迭代器](https://leetcode.cn/problems/rle-iterator)

[English Version](/solution/0900-0999/0900.RLE%20Iterator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们可以使用游程编码(即&nbsp;<strong>RLE&nbsp;</strong>)来编码一个整数序列。在偶数长度&nbsp;<code>encoding</code>&nbsp;( <strong>从 0 开始</strong> )的游程编码数组中，对于所有偶数 <code>i</code> ，<code>encoding[i]</code>&nbsp;告诉我们非负整数&nbsp;<code>encoding[i + 1]</code>&nbsp;在序列中重复的次数。</p>

<ul>
	<li>例如，序列&nbsp;<code>arr = [8,8,8,5,5]</code>&nbsp;可以被编码为 <code>encoding =[3,8,2,5]</code> 。<code>encoding =[3,8,0,9,2,5]</code>&nbsp;和 <code>encoding =[2,8,1,8,2,5]</code> 也是&nbsp;<code>arr</code> 有效的 <strong>RLE</strong> 。</li>
</ul>

<p>给定一个游程长度的编码数组，设计一个迭代器来遍历它。</p>

<p>实现 <code>RLEIterator</code> 类:</p>

<ul>
	<li><code>RLEIterator(int[] encoded)</code>&nbsp;用编码后的数组初始化对象。</li>
	<li><code>int next(int n)</code> 以这种方式耗尽后 <code>n</code> 个元素并返回最后一个耗尽的元素。如果没有剩余的元素要耗尽，则返回 <code>-1</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：
</strong>["RLEIterator","next","next","next","next"]
[[[3,8,0,9,2,5]],[2],[1],[1],[2]]
<strong>输出：
</strong>[null,8,8,5,-1]
<strong>解释：</strong>
RLEIterator rLEIterator = new RLEIterator([3, 8, 0, 9, 2, 5]); // 这映射到序列 [8,8,8,5,5]。
rLEIterator.next(2); // 耗去序列的 2 个项，返回 8。现在剩下的序列是 [8, 5, 5]。
rLEIterator.next(1); // 耗去序列的 1 个项，返回 8。现在剩下的序列是 [5, 5]。
rLEIterator.next(1); // 耗去序列的 1 个项，返回 5。现在剩下的序列是 [5]。
rLEIterator.next(2); // 耗去序列的 2 个项，返回 -1。 这是由于第一个被耗去的项是 5，
但第二个项并不存在。由于最后一个要耗去的项不存在，我们返回 -1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= encoding.length &lt;= 1000</code></li>
	<li><code>encoding.length</code>&nbsp;为偶</li>
	<li><code>0 &lt;= encoding[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li>每个测试用例调用<code>next </code>不高于&nbsp;<code>1000</code>&nbsp;次&nbsp;</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：维护两个指针

我们定义两个指针 $i$ 和 $j$，其中指针 $i$ 指向当前读取的游程编码，指针 $j$ 指向当前读取的游程编码中的第几个字符。初始时 $i = 0$, $j = 0$。

每次调用 `next(n)` 时，我们判断当前游程编码中剩余的字符数 $encoding[i] - j$ 是否小于 $n$，若是，则将 $n$ 减去 $encoding[i] - j$，并将 $i$ 加 $2$，$j$ 置为 $0$，然后继续判断下一个游程编码；若不是，则将 $j$ 加 $n$，并返回 $encoding[i + 1]$。

若 $i$ 超出了游程编码的长度，依然没有返回值，则说明没有剩余的元素要耗尽，返回 $-1$。

时间复杂度 $O(n + q)$，空间复杂度 $O(n)$。其中 $n$ 是游程编码的长度，而 $q$ 是调用 `next(n)` 的次数。

<!-- tabs:start -->

#### Python3

```python
class RLEIterator:
    def __init__(self, encoding: List[int]):
        self.encoding = encoding
        self.i = 0
        self.j = 0

    def next(self, n: int) -> int:
        while self.i < len(self.encoding):
            if self.encoding[self.i] - self.j < n:
                n -= self.encoding[self.i] - self.j
                self.i += 2
                self.j = 0
            else:
                self.j += n
                return self.encoding[self.i + 1]
        return -1


# Your RLEIterator object will be instantiated and called as such:
# obj = RLEIterator(encoding)
# param_1 = obj.next(n)
```

#### Java

```java
class RLEIterator {
    private int[] encoding;
    private int i;
    private int j;

    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
    }

    public int next(int n) {
        while (i < encoding.length) {
            if (encoding[i] - j < n) {
                n -= (encoding[i] - j);
                i += 2;
                j = 0;
            } else {
                j += n;
                return encoding[i + 1];
            }
        }
        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */
```

#### C++

```cpp
class RLEIterator {
public:
    RLEIterator(vector<int>& encoding) {
        this->encoding = encoding;
    }

    int next(int n) {
        while (i < encoding.size()) {
            if (encoding[i] - j < n) {
                n -= (encoding[i] - j);
                i += 2;
                j = 0;
            } else {
                j += n;
                return encoding[i + 1];
            }
        }
        return -1;
    }

private:
    vector<int> encoding;
    int i = 0;
    int j = 0;
};

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator* obj = new RLEIterator(encoding);
 * int param_1 = obj->next(n);
 */
```

#### Go

```go
type RLEIterator struct {
	encoding []int
	i, j     int
}

func Constructor(encoding []int) RLEIterator {
	return RLEIterator{encoding, 0, 0}
}

func (this *RLEIterator) Next(n int) int {
	for this.i < len(this.encoding) {
		if this.encoding[this.i]-this.j < n {
			n -= (this.encoding[this.i] - this.j)
			this.i += 2
			this.j = 0
		} else {
			this.j += n
			return this.encoding[this.i+1]
		}
	}
	return -1
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * obj := Constructor(encoding);
 * param_1 := obj.Next(n);
 */
```

#### TypeScript

```ts
class RLEIterator {
    private encoding: number[];
    private i: number;
    private j: number;

    constructor(encoding: number[]) {
        this.encoding = encoding;
        this.i = 0;
        this.j = 0;
    }

    next(n: number): number {
        while (this.i < this.encoding.length) {
            if (this.encoding[this.i] - this.j < n) {
                n -= this.encoding[this.i] - this.j;
                this.i += 2;
                this.j = 0;
            } else {
                this.j += n;
                return this.encoding[this.i + 1];
            }
        }
        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * var obj = new RLEIterator(encoding)
 * var param_1 = obj.next(n)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
