---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1345.Jump%20Game%20IV/README.md
rating: 1809
source: 第 19 场双周赛 Q4
tags:
    - 广度优先搜索
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [1345. 跳跃游戏 IV](https://leetcode.cn/problems/jump-game-iv)

[English Version](/solution/1300-1399/1345.Jump%20Game%20IV/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>arr</code>&nbsp;，你一开始在数组的第一个元素处（下标为 0）。</p>

<p>每一步，你可以从下标&nbsp;<code>i</code>&nbsp;跳到下标&nbsp;<code>i + 1</code> 、<code>i - 1</code> 或者 <code>j</code> ：</p>

<ul>
	<li><code>i + 1</code> 需满足：<code>i + 1 &lt; arr.length</code></li>
	<li><code>i - 1</code>&nbsp;需满足：<code>i - 1 &gt;= 0</code></li>
	<li><code>j</code>&nbsp;需满足：<code>arr[i] == arr[j]</code>&nbsp;且&nbsp;<code>i != j</code></li>
</ul>

<p>请你返回到达数组最后一个元素的下标处所需的&nbsp;<strong>最少操作次数</strong>&nbsp;。</p>

<p>注意：任何时候你都不能跳到数组外面。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [100,-23,-23,404,100,23,23,23,3,404]
<strong>输出：</strong>3
<strong>解释：</strong>那你需要跳跃 3 次，下标依次为 0 --&gt; 4 --&gt; 3 --&gt; 9 。下标 9 为数组的最后一个元素的下标。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [7]
<strong>输出：</strong>0
<strong>解释：</strong>一开始就在最后一个元素处，所以你不需要跳跃。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [7,6,9,6,9,6,9,7]
<strong>输出：</strong>1
<strong>解释：</strong>你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>8</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minJumps(self, arr: List[int]) -> int:
        idx = defaultdict(list)
        for i, v in enumerate(arr):
            idx[v].append(i)
        q = deque([(0, 0)])
        vis = {0}
        while q:
            i, step = q.popleft()
            if i == len(arr) - 1:
                return step
            v = arr[i]
            step += 1
            for j in idx[v]:
                if j not in vis:
                    vis.add(j)
                    q.append((j, step))
            del idx[v]
            if i + 1 < len(arr) and (i + 1) not in vis:
                vis.add(i + 1)
                q.append((i + 1, step))
            if i - 1 >= 0 and (i - 1) not in vis:
                vis.add(i - 1)
                q.append((i - 1, step))
```

#### Java

```java
class Solution {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> idx = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            idx.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        Deque<int[]> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();
        vis.add(0);
        q.offer(new int[] {0, 0});
        while (!q.isEmpty()) {
            int[] e = q.pollFirst();
            int i = e[0], step = e[1];
            if (i == n - 1) {
                return step;
            }
            int v = arr[i];
            ++step;
            for (int j : idx.getOrDefault(v, new ArrayList<>())) {
                if (!vis.contains(j)) {
                    vis.add(j);
                    q.offer(new int[] {j, step});
                }
            }
            idx.remove(v);
            if (i + 1 < n && !vis.contains(i + 1)) {
                vis.add(i + 1);
                q.offer(new int[] {i + 1, step});
            }
            if (i - 1 >= 0 && !vis.contains(i - 1)) {
                vis.add(i - 1);
                q.offer(new int[] {i - 1, step});
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minJumps(vector<int>& arr) {
        unordered_map<int, vector<int>> idx;
        int n = arr.size();
        for (int i = 0; i < n; ++i) idx[arr[i]].push_back(i);
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        unordered_set<int> vis;
        vis.insert(0);
        while (!q.empty()) {
            auto e = q.front();
            q.pop();
            int i = e.first, step = e.second;
            if (i == n - 1) return step;
            int v = arr[i];
            ++step;
            if (idx.count(v)) {
                for (int j : idx[v]) {
                    if (!vis.count(j)) {
                        vis.insert(j);
                        q.emplace(j, step);
                    }
                }
                idx.erase(v);
            }
            if (i + 1 < n && !vis.count(i + 1)) {
                vis.insert(i + 1);
                q.emplace(i + 1, step);
            }
            if (i - 1 >= 0 && !vis.count(i - 1)) {
                vis.insert(i - 1);
                q.emplace(i - 1, step);
            }
        }
        return -1;
    }
};
```

#### Go

```go
func minJumps(arr []int) int {
	idx := map[int][]int{}
	for i, v := range arr {
		idx[v] = append(idx[v], i)
	}
	vis := map[int]bool{0: true}
	type pair struct{ idx, step int }
	q := []pair{{0, 0}}
	for len(q) > 0 {
		e := q[0]
		q = q[1:]
		i, step := e.idx, e.step
		if i == len(arr)-1 {
			return step
		}
		step++
		for _, j := range idx[arr[i]] {
			if !vis[j] {
				vis[j] = true
				q = append(q, pair{j, step})
			}
		}
		delete(idx, arr[i])
		if i+1 < len(arr) && !vis[i+1] {
			vis[i+1] = true
			q = append(q, pair{i + 1, step})
		}
		if i-1 >= 0 && !vis[i-1] {
			vis[i-1] = true
			q = append(q, pair{i - 1, step})
		}
	}
	return -1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
