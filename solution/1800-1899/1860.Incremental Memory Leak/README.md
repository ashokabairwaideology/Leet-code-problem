---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1860.Incremental%20Memory%20Leak/README.md
rating: 1387
source: 第 52 场双周赛 Q2
tags:
    - 数学
    - 模拟
---

<!-- problem:start -->

# [1860. 增长的内存泄露](https://leetcode.cn/problems/incremental-memory-leak)

[English Version](/solution/1800-1899/1860.Incremental%20Memory%20Leak/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>memory1</code> 和 <code>memory2</code> 分别表示两个内存条剩余可用内存的位数。现在有一个程序每秒递增的速度消耗着内存。</p>

<p>在第 <code>i</code> 秒（秒数从 1 开始），有 <code>i</code> 位内存被分配到 <strong>剩余内存较多</strong> 的内存条（如果两者一样多，则分配到第一个内存条）。如果两者剩余内存都不足 <code>i</code> 位，那么程序将 <b>意外退出</b> 。</p>

<p>请你返回一个数组，包含<em> </em><code>[crashTime, memory1<sub>crash</sub>, memory2<sub>crash</sub>]</code> ，其中 <code>crashTime</code>是程序意外退出的时间（单位为秒），<em> </em><code>memory1<sub>crash</sub></code><em> </em>和<em> </em><code>memory2<sub>crash</sub></code><em> </em>分别是两个内存条最后剩余内存的位数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>memory1 = 2, memory2 = 2
<b>输出：</b>[3,1,0]
<b>解释：</b>内存分配如下：
- 第 1 秒，内存条 1 被占用 1 位内存。内存条 1 现在有 1 位剩余可用内存。
- 第 2 秒，内存条 2 被占用 2 位内存。内存条 2 现在有 0 位剩余可用内存。
- 第 3 秒，程序意外退出，两个内存条分别有 1 位和 0 位剩余可用内存。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>memory1 = 8, memory2 = 11
<b>输出：</b>[6,0,4]
<b>解释：</b>内存分配如下：
- 第 1 秒，内存条 2 被占用 1 位内存，内存条 2 现在有 10 位剩余可用内存。
- 第 2 秒，内存条 2 被占用 2 位内存，内存条 2 现在有 8 位剩余可用内存。
- 第 3 秒，内存条 1 被占用 3 位内存，内存条 1 现在有 5 位剩余可用内存。
- 第 4 秒，内存条 2 被占用 4 位内存，内存条 2 现在有 4 位剩余可用内存。
- 第 5 秒，内存条 1 被占用 5 位内存，内存条 1 现在有 0 位剩余可用内存。
- 第 6 秒，程序意外退出，两个内存条分别有 0 位和 4 位剩余可用内存。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= memory1, memory2 &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们直接模拟内存的分配。

假设 $t$ 为意外退出的时刻，那么两个内存条一定可以容纳 $t-1$ 时刻及以前消耗的内存，因此有：

$$
\sum_{i=1}^{t-1} i = \frac{t\times (t-1)}{2}  \leq (m_1+m_2)
$$

时间复杂度 $O(\sqrt{m_1+m_2})$，其中 $m_1$, $m_2$ 分别为两个内存条的内存大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def memLeak(self, memory1: int, memory2: int) -> List[int]:
        i = 1
        while i <= max(memory1, memory2):
            if memory1 >= memory2:
                memory1 -= i
            else:
                memory2 -= i
            i += 1
        return [i, memory1, memory2]
```

#### Java

```java
class Solution {
    public int[] memLeak(int memory1, int memory2) {
        int i = 1;
        for (; i <= Math.max(memory1, memory2); ++i) {
            if (memory1 >= memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
        }
        return new int[] {i, memory1, memory2};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> memLeak(int memory1, int memory2) {
        int i = 1;
        for (; i <= max(memory1, memory2); ++i) {
            if (memory1 >= memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
        }
        return {i, memory1, memory2};
    }
};
```

#### Go

```go
func memLeak(memory1 int, memory2 int) []int {
	i := 1
	for ; i <= memory1 || i <= memory2; i++ {
		if memory1 >= memory2 {
			memory1 -= i
		} else {
			memory2 -= i
		}
	}
	return []int{i, memory1, memory2}
}
```

#### TypeScript

```ts
function memLeak(memory1: number, memory2: number): number[] {
    let i = 1;
    for (; i <= Math.max(memory1, memory2); ++i) {
        if (memory1 >= memory2) {
            memory1 -= i;
        } else {
            memory2 -= i;
        }
    }
    return [i, memory1, memory2];
}
```

#### JavaScript

```js
/**
 * @param {number} memory1
 * @param {number} memory2
 * @return {number[]}
 */
var memLeak = function (memory1, memory2) {
    let i = 1;
    for (; i <= Math.max(memory1, memory2); ++i) {
        if (memory1 >= memory2) {
            memory1 -= i;
        } else {
            memory2 -= i;
        }
    }
    return [i, memory1, memory2];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
