---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2432.The%20Employee%20That%20Worked%20on%20the%20Longest%20Task/README.md
rating: 1266
source: 第 314 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [2432. 处理用时最长的那个任务的员工](https://leetcode.cn/problems/the-employee-that-worked-on-the-longest-task)

[English Version](/solution/2400-2499/2432.The%20Employee%20That%20Worked%20on%20the%20Longest%20Task/README_EN.md)

## 题目描述

<!-- description:start -->

<p>共有 <code>n</code> 位员工，每位员工都有一个从 <code>0</code> 到 <code>n - 1</code> 的唯一 id 。</p>

<p>给你一个二维整数数组 <code>logs</code> ，其中 <code>logs[i] = [id<sub>i</sub>, leaveTime<sub>i</sub>]</code> ：</p>

<ul>
	<li><code>id<sub>i</sub></code> 是处理第 <code>i</code> 个任务的员工的 id ，且</li>
	<li><code>leaveTime<sub>i</sub></code> 是员工完成第 <code>i</code> 个任务的时刻。所有 <code>leaveTime<sub>i</sub></code> 的值都是 <strong>唯一</strong> 的。</li>
</ul>

<p>注意，第 <code>i</code> 个任务在第 <code>(i - 1)</code> 个任务结束后立即开始，且第 <code>0</code> 个任务从时刻 <code>0</code> 开始。</p>

<p>返回处理用时最长的那个任务的员工的 id 。如果存在两个或多个员工同时满足，则返回几人中 <strong>最小</strong> 的 id 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10, logs = [[0,3],[2,5],[0,9],[1,15]]
<strong>输出：</strong>1
<strong>解释：</strong>
任务 0 于时刻 0 开始，且在时刻 3 结束，共计 3 个单位时间。
任务 1 于时刻 3 开始，且在时刻 5 结束，共计 2 个单位时间。
任务 2 于时刻 5 开始，且在时刻 9 结束，共计 4 个单位时间。
任务 3 于时刻 9 开始，且在时刻 15 结束，共计 6 个单位时间。
时间最长的任务是任务 3 ，而 id 为 1 的员工是处理此任务的员工，所以返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 26, logs = [[1,1],[3,7],[2,12],[7,17]]
<strong>输出：</strong>3
<strong>解释：</strong>
任务 0 于时刻 0 开始，且在时刻 1 结束，共计 1 个单位时间。
任务 1 于时刻 1 开始，且在时刻 7 结束，共计 6 个单位时间。
任务 2 于时刻 7 开始，且在时刻 12 结束，共计 5 个单位时间。
任务 3 于时刻 12 开始，且在时刻 17 结束，共计 5 个单位时间。
时间最长的任务是任务 1 ，而 id 为 3 的员工是处理此任务的员工，所以返回 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2, logs = [[0,10],[1,20]]
<strong>输出：</strong>0
<strong>解释：</strong>
任务 0 于时刻 0 开始，且在时刻 10 结束，共计 10 个单位时间。
任务 1 于时刻 10 开始，且在时刻 20 结束，共计 10 个单位时间。
时间最长的任务是任务 0 和 1 ，处理这两个任务的员工的 id 分别是 0 和 1 ，所以返回最小的 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= logs.length &lt;= 500</code></li>
	<li><code>logs[i].length == 2</code></li>
	<li><code>0 &lt;= id<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= leaveTime<sub>i</sub> &lt;= 500</code></li>
	<li><code>id<sub>i</sub> != id<sub>i + 1</sub></code></li>
	<li><code>leaveTime<sub>i</sub></code> 按严格递增顺序排列</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接遍历

我们用变量 $last$ 记录上一个任务的结束时间，用变量 $mx$ 记录最长的工作时间，用变量 $ans$ 记录工作时间最长且 $id$ 最小的员工。初始时，三个变量均为 $0$。

接下来，遍历数组 $logs$，对于每个员工，我们将员工完成任务的时间减去上一个任务的结束时间，即可得到该员工的工作时间 $t$。如果 $mx$ 小于 $t$，或者 $mx$ 等于 $t$ 且该员工的 $id$ 小于 $ans$，则更新 $mx$ 和 $ans$。然后我们将 $last$ 更新为上一个任务的结束时间加上 $t$。继续遍历，直到遍历完整个数组。

最后返回答案 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $logs$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hardestWorker(self, n: int, logs: List[List[int]]) -> int:
        last = mx = ans = 0
        for uid, t in logs:
            t -= last
            if mx < t or (mx == t and ans > uid):
                ans, mx = uid, t
            last += t
        return ans
```

#### Java

```java
class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int ans = 0;
        int last = 0, mx = 0;
        for (int[] log : logs) {
            int uid = log[0], t = log[1];
            t -= last;
            if (mx < t || (mx == t && ans > uid)) {
                ans = uid;
                mx = t;
            }
            last += t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int hardestWorker(int n, vector<vector<int>>& logs) {
        int ans = 0, mx = 0, last = 0;
        for (auto& log : logs) {
            int uid = log[0], t = log[1];
            t -= last;
            if (mx < t || (mx == t && ans > uid)) {
                mx = t;
                ans = uid;
            }
            last += t;
        }
        return ans;
    }
};
```

#### Go

```go
func hardestWorker(n int, logs [][]int) (ans int) {
	var mx, last int
	for _, log := range logs {
		uid, t := log[0], log[1]
		t -= last
		if mx < t || (mx == t && uid < ans) {
			mx = t
			ans = uid
		}
		last += t
	}
	return
}
```

#### TypeScript

```ts
function hardestWorker(n: number, logs: number[][]): number {
    let [ans, mx, last] = [0, 0, 0];
    for (let [uid, t] of logs) {
        t -= last;
        if (mx < t || (mx == t && ans > uid)) {
            ans = uid;
            mx = t;
        }
        last += t;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn hardest_worker(n: i32, logs: Vec<Vec<i32>>) -> i32 {
        let mut res = 0;
        let mut max = 0;
        let mut pre = 0;
        for log in logs.iter() {
            let t = log[1] - pre;
            if t > max || (t == max && res > log[0]) {
                res = log[0];
                max = t;
            }
            pre = log[1];
        }
        res
    }
}
```

#### C

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

int hardestWorker(int n, int** logs, int logsSize, int* logsColSize) {
    int res = 0;
    int max = 0;
    int pre = 0;
    for (int i = 0; i < logsSize; i++) {
        int t = logs[i][1] - pre;
        if (t > max || (t == max && res > logs[i][0])) {
            res = logs[i][0];
            max = t;
        }
        pre = logs[i][1];
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Rust

```rust
impl Solution {
    pub fn hardest_worker(n: i32, logs: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        let mut last = 0;

        for log in logs {
            let uid = log[0];
            let t = log[1];

            let diff = t - last;
            last = t;

            if diff > mx || (diff == mx && uid < ans) {
                ans = uid;
                mx = diff;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
