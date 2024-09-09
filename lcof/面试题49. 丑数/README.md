---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9849.%20%E4%B8%91%E6%95%B0/README.md
---

<!-- problem:start -->

# [面试题 49. 丑数](https://leetcode.cn/problems/chou-shu-lcof/)

## 题目描述

<!-- description:start -->

<p>我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> n = 10
<strong>输出:</strong> 12
<strong>解释: </strong><code>1, 2, 3, 4, 5, 6, 8, 9, 10, 12</code> 是前 10 个丑数。</pre>

<p><strong>说明:&nbsp;</strong>&nbsp;</p>

<ol>
	<li><code>1</code>&nbsp;是丑数。</li>
	<li><code>n</code>&nbsp;<strong>不超过</strong>1690。</li>
</ol>

<p>注意：本题与主站 264 题相同：<a href="https://leetcode.cn/problems/ugly-number-ii/">https://leetcode.cn/problems/ugly-number-ii/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（最小堆）

初始时，将第一个丑数 $1$ 加入堆。每次取出堆顶元素 $x$，由于 $2x$, $3x$, $5x$ 也是丑数，因此将它们加入堆中。为了避免重复元素，可以用哈希表 $vis$ 去重。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        h = [1]
        vis = {1}
        ans = 1
        for _ in range(n):
            ans = heappop(h)
            for v in [2, 3, 5]:
                nxt = ans * v
                if nxt not in vis:
                    vis.add(nxt)
                    heappush(h, nxt)
        return ans
```

#### Java

```java
class Solution {
    public int nthUglyNumber(int n) {
        Set<Long> vis = new HashSet<>();
        PriorityQueue<Long> q = new PriorityQueue<>();
        int[] f = new int[] {2, 3, 5};
        q.offer(1L);
        vis.add(1L);
        long ans = 0;
        while (n-- > 0) {
            ans = q.poll();
            for (int v : f) {
                long next = ans * v;
                if (vis.add(next)) {
                    q.offer(next);
                }
            }
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int nthUglyNumber(int n) {
        priority_queue<long, vector<long>, greater<long>> q;
        q.push(1l);
        unordered_set<long> vis{{1l}};
        long ans = 1;
        vector<int> f = {2, 3, 5};
        while (n--) {
            ans = q.top();
            q.pop();
            for (int& v : f) {
                long nxt = ans * v;
                if (!vis.count(nxt)) {
                    vis.insert(nxt);
                    q.push(nxt);
                }
            }
        }
        return (int) ans;
    }
};
```

#### Go

```go
func nthUglyNumber(n int) int {
	h := IntHeap([]int{1})
	heap.Init(&h)
	ans := 1
	vis := map[int]bool{1: true}
	for n > 0 {
		ans = heap.Pop(&h).(int)
		for _, v := range []int{2, 3, 5} {
			nxt := ans * v
			if !vis[nxt] {
				vis[nxt] = true
				heap.Push(&h, nxt)
			}
		}
		n--
	}
	return ans
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x any) {
	*h = append(*h, x.(int))
}
func (h *IntHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}
```

#### Rust

```rust
impl Solution {
    pub fn nth_ugly_number(n: i32) -> i32 {
        let n = n as usize;
        let mut dp = vec![1; n];
        let mut p2 = 0;
        let mut p3 = 0;
        let mut p5 = 0;
        for i in 1..n {
            let n2 = dp[p2] * 2;
            let n3 = dp[p3] * 3;
            let n5 = dp[p5] * 5;
            dp[i] = n2.min(n3.min(n5));

            if dp[i] == n2 {
                p2 += 1;
            }
            if dp[i] == n3 {
                p3 += 1;
            }
            if dp[i] == n5 {
                p5 += 1;
            }
        }
        dp[n - 1]
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var nthUglyNumber = function (n) {
    let dp = [1];
    let p2 = 0,
        p3 = 0,
        p5 = 0;
    for (let i = 1; i < n; ++i) {
        const next2 = dp[p2] * 2,
            next3 = dp[p3] * 3,
            next5 = dp[p5] * 5;
        dp[i] = Math.min(next2, Math.min(next3, next5));
        if (dp[i] == next2) ++p2;
        if (dp[i] == next3) ++p3;
        if (dp[i] == next5) ++p5;
        dp.push(dp[i]);
    }
    return dp[n - 1];
};
```

#### C#

```cs
public class Solution {
    public int NthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.Min(next2, Math.Min(next3, next5));
            if (dp[i] == next2) {
                ++p2;
            }
            if (dp[i] == next3) {
                ++p3;
            }
            if (dp[i] == next5) {
                ++p5;
            }
        }
        return dp[n - 1];
    }
}
```

#### Swift

```swift
class Solution {
    func nthUglyNumber(_ n: Int) -> Int {
        var vis = Set<Int64>()
        var pq = PriorityQueue<Int64>()
        let factors: [Int64] = [2, 3, 5]

        pq.push(1)
        vis.insert(1)
        var ans: Int64 = 0

        for _ in 0..<n {
            ans = pq.pop()!
            for factor in factors {
                let next = ans * factor
                if vis.insert(next).inserted {
                    pq.push(next)
                }
            }
        }

        return Int(ans)
    }
}

struct PriorityQueue<T: Comparable> {
    private var heap: [T] = []

    var isEmpty: Bool {
        return heap.isEmpty
    }

    mutating func push(_ element: T) {
        heap.append(element)
        heapifyUp(from: heap.count - 1)
    }

    mutating func pop() -> T? {
        guard !heap.isEmpty else {
            return nil
        }
        if heap.count == 1 {
            return heap.removeLast()
        }
        let value = heap[0]
        heap[0] = heap.removeLast()
        heapifyDown(from: 0)
        return value
    }

    private mutating func heapifyUp(from index: Int) {
        var index = index
        let element = heap[index]
        while index > 0 {
            let parentIndex = (index - 1) / 2
            if element >= heap[parentIndex] {
                break
            }
            heap[index] = heap[parentIndex]
            index = parentIndex
        }
        heap[index] = element
    }

    private mutating func heapifyDown(from index: Int) {
        var index = index
        let element = heap[index]
        let count = heap.count
        while index < count / 2 {
            var childIndex = index * 2 + 1
            if childIndex + 1 < count && heap[childIndex + 1] < heap[childIndex] {
                childIndex += 1
            }
            if element <= heap[childIndex] {
                break
            }
            heap[index] = heap[childIndex]
            index = childIndex
        }
        heap[index] = element
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：动态规划

我们定义数组 $dp$，其中 $dp[i-1]$ 表示第 $i$ 个丑数，那么第 $n$ 个丑数就是 $dp[n - 1]$。最小的丑数是 $1$，所以 $dp[0]=1$。

定义 $3$ 个指针 $p_2$, $p_3$ 和 $p_5$，表示下一个丑数是当前指针指向的丑数乘以对应的质因数。初始时，三个指针的值都指向 $0$。

当 $i$ 在 $[1,2..n-1]$ 范围内，我们更新 $dp[i]=\min(dp[p_2] \times 2, dp[p_3] \times 3, dp[p_5] \times 5)$，然后分别比较 $dp[i]$ 与 $dp[p_2] \times 2$, $dp[p_3] \times 3$, $dp[p_5] \times 5$ 是否相等，若是，则对应的指针加 $1$。

最后返回 $dp[n - 1]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        dp = [1] * n
        p2 = p3 = p5 = 0
        for i in range(1, n):
            next2, next3, next5 = dp[p2] * 2, dp[p3] * 3, dp[p5] * 5
            dp[i] = min(next2, next3, next5)
            if dp[i] == next2:
                p2 += 1
            if dp[i] == next3:
                p3 += 1
            if dp[i] == next5:
                p5 += 1
        return dp[-1]
```

#### Java

```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) ++p2;
            if (dp[i] == next3) ++p3;
            if (dp[i] == next5) ++p5;
        }
        return dp[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int nthUglyNumber(int n) {
        vector<int> dp(n);
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = min(next2, min(next3, next5));
            if (dp[i] == next2) ++p2;
            if (dp[i] == next3) ++p3;
            if (dp[i] == next5) ++p5;
        }
        return dp[n - 1];
    }
};
```

#### Go

```go
func nthUglyNumber(n int) int {
	dp := make([]int, n)
	dp[0] = 1
	p2, p3, p5 := 0, 0, 0
	for i := 1; i < n; i++ {
		next2, next3, next5 := dp[p2]*2, dp[p3]*3, dp[p5]*5
		dp[i] = min(next2, min(next3, next5))
		if dp[i] == next2 {
			p2++
		}
		if dp[i] == next3 {
			p3++
		}
		if dp[i] == next5 {
			p5++
		}
	}
	return dp[n-1]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
