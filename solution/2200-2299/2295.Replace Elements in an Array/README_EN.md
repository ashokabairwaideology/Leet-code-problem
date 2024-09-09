---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2295.Replace%20Elements%20in%20an%20Array/README_EN.md
rating: 1445
source: Weekly Contest 296 Q3
tags:
    - Array
    - Hash Table
    - Simulation
---

<!-- problem:start -->

# [2295. Replace Elements in an Array](https://leetcode.com/problems/replace-elements-in-an-array)

[中文文档](/solution/2200-2299/2295.Replace%20Elements%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> that consists of <code>n</code> <strong>distinct</strong> positive integers. Apply <code>m</code> operations to this array, where in the <code>i<sup>th</sup></code> operation you replace the number <code>operations[i][0]</code> with <code>operations[i][1]</code>.</p>

<p>It is guaranteed that in the <code>i<sup>th</sup></code> operation:</p>

<ul>
	<li><code>operations[i][0]</code> <strong>exists</strong> in <code>nums</code>.</li>
	<li><code>operations[i][1]</code> does <strong>not</strong> exist in <code>nums</code>.</li>
</ul>

<p>Return <em>the array obtained after applying all the operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
<strong>Output:</strong> [3,2,7,1]
<strong>Explanation:</strong> We perform the following operations on nums:
- Replace the number 1 with 3. nums becomes [<u><strong>3</strong></u>,2,4,6].
- Replace the number 4 with 7. nums becomes [3,2,<u><strong>7</strong></u>,6].
- Replace the number 6 with 1. nums becomes [3,2,7,<u><strong>1</strong></u>].
We return the final array [3,2,7,1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2], operations = [[1,3],[2,1],[3,2]]
<strong>Output:</strong> [2,1]
<strong>Explanation:</strong> We perform the following operations to nums:
- Replace the number 1 with 3. nums becomes [<u><strong>3</strong></u>,2].
- Replace the number 2 with 1. nums becomes [3,<u><strong>1</strong></u>].
- Replace the number 3 with 2. nums becomes [<u><strong>2</strong></u>,1].
We return the array [2,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == operations.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li>All the values of <code>nums</code> are <strong>distinct</strong>.</li>
	<li><code>operations[i].length == 2</code></li>
	<li><code>1 &lt;= nums[i], operations[i][0], operations[i][1] &lt;= 10<sup>6</sup></code></li>
	<li><code>operations[i][0]</code> will exist in <code>nums</code> when applying the <code>i<sup>th</sup></code> operation.</li>
	<li><code>operations[i][1]</code> will not exist in <code>nums</code> when applying the <code>i<sup>th</sup></code> operation.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

First, we use a hash table $d$ to record the indices of each number in the array $\textit{nums}$. Then, we iterate through the operation array $\textit{operations}$. For each operation $[x, y]$, we replace the number at index $d[x]$ in $\textit{nums}$ with $y$, and update the index of $y$ in $d$ to $d[x]$.

Finally, we return $\textit{nums}$.

The time complexity is $O(n + m)$, and the space complexity is $O(n)$. Here, $n$ and $m$ are the lengths of the array $\textit{nums}$ and the operation array $\textit{operations}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def arrayChange(self, nums: List[int], operations: List[List[int]]) -> List[int]:
        d = {x: i for i, x in enumerate(nums)}
        for x, y in operations:
            nums[d[x]] = y
            d[y] = d[x]
        return nums
```

#### Java

```java
class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        int n = nums.length;
        Map<Integer, Integer> d = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            d.put(nums[i], i);
        }
        for (var op : operations) {
            int x = op[0], y = op[1];
            nums[d.get(x)] = y;
            d.put(y, d.get(x));
        }
        return nums;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> arrayChange(vector<int>& nums, vector<vector<int>>& operations) {
        unordered_map<int, int> d;
        for (int i = 0; i < nums.size(); ++i) {
            d[nums[i]] = i;
        }
        for (auto& op : operations) {
            int x = op[0], y = op[1];
            nums[d[x]] = y;
            d[y] = d[x];
        }
        return nums;
    }
};
```

#### Go

```go
func arrayChange(nums []int, operations [][]int) []int {
	d := map[int]int{}
	for i, x := range nums {
		d[x] = i
	}
	for _, op := range operations {
		x, y := op[0], op[1]
		nums[d[x]] = y
		d[y] = d[x]
	}
	return nums
}
```

#### TypeScript

```ts
function arrayChange(nums: number[], operations: number[][]): number[] {
    const d: Map<number, number> = new Map(nums.map((x, i) => [x, i]));
    for (const [x, y] of operations) {
        nums[d.get(x)!] = y;
        d.set(y, d.get(x)!);
    }
    return nums;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
