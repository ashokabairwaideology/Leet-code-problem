---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2869.Minimum%20Operations%20to%20Collect%20Elements/README_EN.md
rating: 1272
source: Biweekly Contest 114 Q1
tags:
    - Bit Manipulation
    - Array
    - Hash Table
---

<!-- problem:start -->

# [2869. Minimum Operations to Collect Elements](https://leetcode.com/problems/minimum-operations-to-collect-elements)

[中文文档](/solution/2800-2899/2869.Minimum%20Operations%20to%20Collect%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of positive integers and an integer <code>k</code>.</p>

<p>In one operation, you can remove the last element of the array and add it to your collection.</p>

<p>Return <em>the <strong>minimum number of operations</strong> needed to collect elements</em> <code>1, 2, ..., k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,5,4,2], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> After 4 operations, we collect elements 2, 4, 5, and 1, in this order. Our collection contains elements 1 and 2. Hence, the answer is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,5,4,2], k = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong> After 5 operations, we collect elements 2, 4, 5, 1, and 3, in this order. Our collection contains elements 1 through 5. Hence, the answer is 5.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,5,3,1], k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> After 4 operations, we collect elements 1, 3, 5, and 2, in this order. Our collection contains elements 1 through 3. Hence, the answer is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li>The input is generated such that you can collect elements <code>1, 2, ..., k</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traverse in Reverse Order

We can traverse the array in reverse order. For each element encountered during the traversal that is less than or equal to $k$ and has not been added to the set yet, we add it to the set until the set contains elements from $1$ to $k$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        is_added = [False] * k
        count = 0
        n = len(nums)
        for i in range(n - 1, -1, -1):
            if nums[i] > k or is_added[nums[i] - 1]:
                continue
            is_added[nums[i] - 1] = True
            count += 1
            if count == k:
                return n - i
```

#### Java

```java
class Solution {
    public int minOperations(List<Integer> nums, int k) {
        boolean[] isAdded = new boolean[k];
        int n = nums.size();
        int count = 0;
        for (int i = n - 1;; i--) {
            if (nums.get(i) > k || isAdded[nums.get(i) - 1]) {
                continue;
            }
            isAdded[nums.get(i) - 1] = true;
            count++;
            if (count == k) {
                return n - i;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        int n = nums.size();
        vector<bool> isAdded(n);
        int count = 0;
        for (int i = n - 1;; --i) {
            if (nums[i] > k || isAdded[nums[i] - 1]) {
                continue;
            }
            isAdded[nums[i] - 1] = true;
            if (++count == k) {
                return n - i;
            }
        }
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) int {
	isAdded := make([]bool, k)
	count := 0
	n := len(nums)
	for i := n - 1; ; i-- {
		if nums[i] > k || isAdded[nums[i]-1] {
			continue
		}
		isAdded[nums[i]-1] = true
		count++
		if count == k {
			return n - i
		}
	}
}
```

#### TypeScript

```ts
function minOperations(nums: number[], k: number): number {
    const n = nums.length;
    const isAdded = Array(k).fill(false);
    let count = 0;
    for (let i = n - 1; ; --i) {
        if (nums[i] > k || isAdded[nums[i] - 1]) {
            continue;
        }
        isAdded[nums[i] - 1] = true;
        ++count;
        if (count === k) {
            return n - i;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
