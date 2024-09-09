---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1817.Finding%20the%20Users%20Active%20Minutes/README_EN.md
rating: 1360
source: Weekly Contest 235 Q2
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1817. Finding the Users Active Minutes](https://leetcode.com/problems/finding-the-users-active-minutes)

[中文文档](/solution/1800-1899/1817.Finding%20the%20Users%20Active%20Minutes/README.md)

## Description

<!-- description:start -->

<p>You are given the logs for users&#39; actions on LeetCode, and an integer <code>k</code>. The logs are represented by a 2D integer array <code>logs</code> where each <code>logs[i] = [ID<sub>i</sub>, time<sub>i</sub>]</code> indicates that the user with <code>ID<sub>i</sub></code> performed an action at the minute <code>time<sub>i</sub></code>.</p>

<p><strong>Multiple users</strong> can perform actions simultaneously, and a single user can perform <strong>multiple actions</strong> in the same minute.</p>

<p>The <strong>user active minutes (UAM)</strong> for a given user is defined as the <strong>number of unique minutes</strong> in which the user performed an action on LeetCode. A minute can only be counted once, even if multiple actions occur during it.</p>

<p>You are to calculate a <strong>1-indexed</strong> array <code>answer</code> of size <code>k</code> such that, for each <code>j</code> (<code>1 &lt;= j &lt;= k</code>), <code>answer[j]</code> is the <strong>number of users</strong> whose <strong>UAM</strong> equals <code>j</code>.</p>

<p>Return <i>the array </i><code>answer</code><i> as described above</i>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> logs = [[0,5],[1,2],[0,2],[0,5],[1,3]], k = 5
<strong>Output:</strong> [0,2,0,0,0]
<strong>Explanation:</strong>
The user with ID=0 performed actions at minutes 5, 2, and 5 again. Hence, they have a UAM of 2 (minute 5 is only counted once).
The user with ID=1 performed actions at minutes 2 and 3. Hence, they have a UAM of 2.
Since both users have a UAM of 2, answer[2] is 2, and the remaining answer[j] values are 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> logs = [[1,1],[2,2],[2,3]], k = 4
<strong>Output:</strong> [1,1,0,0]
<strong>Explanation:</strong>
The user with ID=1 performed a single action at minute 1. Hence, they have a UAM of 1.
The user with ID=2 performed actions at minutes 2 and 3. Hence, they have a UAM of 2.
There is one user with a UAM of 1 and one with a UAM of 2.
Hence, answer[1] = 1, answer[2] = 1, and the remaining values are 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= logs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= ID<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>k</code> is in the range <code>[The maximum <strong>UAM</strong> for a user, 10<sup>5</sup>]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $d$ to record all the unique operation times of each user, and then traverse the hash table to count the number of active minutes for each user. Finally, we count the distribution of the number of active minutes for each user.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the $logs$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findingUsersActiveMinutes(self, logs: List[List[int]], k: int) -> List[int]:
        d = defaultdict(set)
        for i, t in logs:
            d[i].add(t)
        ans = [0] * k
        for ts in d.values():
            ans[len(ts) - 1] += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> d = new HashMap<>();
        for (var log : logs) {
            int i = log[0], t = log[1];
            d.computeIfAbsent(i, key -> new HashSet<>()).add(t);
        }
        int[] ans = new int[k];
        for (var ts : d.values()) {
            ++ans[ts.size() - 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findingUsersActiveMinutes(vector<vector<int>>& logs, int k) {
        unordered_map<int, unordered_set<int>> d;
        for (auto& log : logs) {
            int i = log[0], t = log[1];
            d[i].insert(t);
        }
        vector<int> ans(k);
        for (auto& [_, ts] : d) {
            ++ans[ts.size() - 1];
        }
        return ans;
    }
};
```

#### Go

```go
func findingUsersActiveMinutes(logs [][]int, k int) []int {
	d := map[int]map[int]bool{}
	for _, log := range logs {
		i, t := log[0], log[1]
		if _, ok := d[i]; !ok {
			d[i] = make(map[int]bool)
		}
		d[i][t] = true
	}
	ans := make([]int, k)
	for _, ts := range d {
		ans[len(ts)-1]++
	}
	return ans
}
```

#### TypeScript

```ts
function findingUsersActiveMinutes(logs: number[][], k: number): number[] {
    const d: Map<number, Set<number>> = new Map();
    for (const [i, t] of logs) {
        if (!d.has(i)) {
            d.set(i, new Set<number>());
        }
        d.get(i)!.add(t);
    }
    const ans: number[] = Array(k).fill(0);
    for (const [_, ts] of d) {
        ++ans[ts.size - 1];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
