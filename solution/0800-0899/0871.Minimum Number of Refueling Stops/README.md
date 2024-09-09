---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0871.Minimum%20Number%20of%20Refueling%20Stops/README.md
tags:
    - 贪心
    - 数组
    - 动态规划
    - 堆（优先队列）
---

<!-- problem:start -->

# [871. 最低加油次数](https://leetcode.cn/problems/minimum-number-of-refueling-stops)

[English Version](/solution/0800-0899/0871.Minimum%20Number%20of%20Refueling%20Stops/README_EN.md)

## 题目描述

<!-- description:start -->

<p>汽车从起点出发驶向目的地，该目的地位于出发位置东面 <code>target</code>&nbsp;英里处。</p>

<p>沿途有加油站，用数组&nbsp;<code>stations</code> 表示。其中 <code>stations[i] = [position<sub>i</sub>, fuel<sub>i</sub>]</code> 表示第 <code>i</code> 个加油站位于出发位置东面&nbsp;<code>position<sub>i</sub></code> 英里处，并且有&nbsp;<code>fuel<sub>i</sub></code>&nbsp;升汽油。</p>

<p>假设汽车油箱的容量是无限的，其中最初有&nbsp;<code>startFuel</code>&nbsp;升燃料。它每行驶 1 英里就会用掉 1 升汽油。当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。</p>

<p>为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 <code>-1</code> 。</p>

<p>注意：如果汽车到达加油站时剩余燃料为 <code>0</code>，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 <code>0</code>，仍然认为它已经到达目的地。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 1, startFuel = 1, stations = []
<strong>输出：</strong>0
<strong>解释：</strong>可以在不加油的情况下到达目的地。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 100, startFuel = 1, stations = [[10,100]]
<strong>输出：</strong>-1
<strong>解释：</strong>无法抵达目的地，甚至无法到达第一个加油站。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
<strong>输出：</strong>2
<strong>解释：</strong>
出发时有 10 升燃料。
开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
然后，从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
并将汽油从 10 升加到 50 升。然后开车抵达目的地。
沿途在两个加油站停靠，所以返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target, startFuel &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= stations.length &lt;= 500</code></li>
	<li><code>1 &lt;= position<sub>i</sub> &lt; position<sub>i+1</sub> &lt; target</code></li>
	<li><code>1 &lt;= fuel<sub>i</sub> &lt; 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（大根堆）

利用优先队列记录所有已经到达过的加油站的加油量，每次当油量不足时，从队列中取出最大加油量，并累计加油次数 ans。

时间复杂度 $O(nlogn)$。其中 $n$ 表示数组 `stations` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRefuelStops(
        self, target: int, startFuel: int, stations: List[List[int]]
    ) -> int:
        q = []
        prev = ans = 0
        stations.append([target, 0])
        for a, b in stations:
            d = a - prev
            startFuel -= d
            while startFuel < 0 and q:
                startFuel -= heappop(q)
                ans += 1
            if startFuel < 0:
                return -1
            heappush(q, -b)
            prev = a
        return ans
```

#### Java

```java
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int n = stations.length;
        int prev = 0, ans = 0;
        for (int i = 0; i < n + 1; ++i) {
            int d = (i < n ? stations[i][0] : target) - prev;
            startFuel -= d;
            while (startFuel < 0 && !q.isEmpty()) {
                startFuel += q.poll();
                ++ans;
            }
            if (startFuel < 0) {
                return -1;
            }
            if (i < n) {
                q.offer(stations[i][1]);
                prev = stations[i][0];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minRefuelStops(int target, int startFuel, vector<vector<int>>& stations) {
        priority_queue<int> q;
        stations.push_back({target, 0});
        int ans = 0, prev = 0;
        for (auto& s : stations) {
            int d = s[0] - prev;
            startFuel -= d;
            while (startFuel < 0 && !q.empty()) {
                startFuel += q.top();
                q.pop();
                ++ans;
            }
            if (startFuel < 0) return -1;
            q.push(s[1]);
            prev = s[0];
        }
        return ans;
    }
};
```

#### Go

```go
func minRefuelStops(target int, startFuel int, stations [][]int) int {
	stations = append(stations, []int{target, 0})
	ans, prev := 0, 0
	q := &hp{}
	heap.Init(q)
	for _, s := range stations {
		d := s[0] - prev
		startFuel -= d
		for startFuel < 0 && q.Len() > 0 {
			startFuel += q.pop()
			ans++
		}
		if startFuel < 0 {
			return -1
		}
		q.push(s[1])
		prev = s[0]
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
