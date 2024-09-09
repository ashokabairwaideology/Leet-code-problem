---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2898.Maximum%20Linear%20Stock%20Score/README_EN.md
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [2898. Maximum Linear Stock Score 🔒](https://leetcode.com/problems/maximum-linear-stock-score)

[中文文档](/solution/2800-2899/2898.Maximum%20Linear%20Stock%20Score/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>1-indexed</strong> integer array <code>prices</code>, where <code>prices[i]</code> is the price of a particular stock on the <code>i<sup>th</sup></code> day, your task is to select some of the elements of <code>prices</code> such that your selection is <strong>linear</strong>.</p>

<p>A selection <code>indexes</code>, where <code>indexes</code> is a <strong>1-indexed</strong> integer array of length <code>k</code> which is a subsequence of the array <code>[1, 2, ..., n]</code>, is <strong>linear</strong> if:</p>

<ul>
	<li>For every <code>1 &lt; j &lt;= k</code>, <code>prices[indexes[j]] - prices[indexes[j - 1]] == indexes[j] - indexes[j - 1]</code>.</li>
</ul>

<p>A <b>subsequence</b> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>The <strong>score</strong> of a selection <code>indexes</code>, is equal to the sum of the following array: <code>[prices[indexes[1]], prices[indexes[2]], ..., prices[indexes[k]]</code>.</p>

<p>Return <em>the <strong>maximum</strong> <strong>score</strong> that a linear selection can have</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,5,3,7,8]
<strong>Output:</strong> 20
<strong>Explanation:</strong> We can select the indexes [2,4,5]. We show that our selection is linear:
For j = 2, we have:
indexes[2] - indexes[1] = 4 - 2 = 2.
prices[4] - prices[2] = 7 - 5 = 2.
For j = 3, we have:
indexes[3] - indexes[2] = 5 - 4 = 1.
prices[5] - prices[4] = 8 - 7 = 1.
The sum of the elements is: prices[2] + prices[4] + prices[5] = 20.
It can be shown that the maximum sum a linear selection can have is 20.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [5,6,7,8,9]
<strong>Output:</strong> 35
<strong>Explanation:</strong> We can select all of the indexes [1,2,3,4,5]. Since each element has a difference of exactly 1 from its previous element, our selection is linear.
The sum of all the elements is 35 which is the maximum possible some out of every selection.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can transform the equation as follows:

$$
prices[i] - i = prices[j] - j
$$

In fact, the problem is to find the maximum sum of all $prices[i]$ under the same $prices[i] - i$.

Therefore, we can use a hash table $cnt$ to store the sum of all $prices[i]$ under the same $prices[i] - i$, and finally take the maximum value in the hash table.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the $prices$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, prices: List[int]) -> int:
        cnt = Counter()
        for i, x in enumerate(prices):
            cnt[x - i] += x
        return max(cnt.values())
```

#### Java

```java
class Solution {
    public long maxScore(int[] prices) {
        Map<Integer, Long> cnt = new HashMap<>();
        for (int i = 0; i < prices.length; ++i) {
            cnt.merge(prices[i] - i, (long) prices[i], Long::sum);
        }
        long ans = 0;
        for (long v : cnt.values()) {
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& prices) {
        unordered_map<int, long long> cnt;
        for (int i = 0; i < prices.size(); ++i) {
            cnt[prices[i] - i] += prices[i];
        }
        long long ans = 0;
        for (auto& [_, v] : cnt) {
            ans = max(ans, v);
        }
        return ans;
    }
};
```

#### Go

```go
func maxScore(prices []int) (ans int64) {
	cnt := map[int]int{}
	for i, x := range prices {
		cnt[x-i] += x
	}
	for _, v := range cnt {
		ans = max(ans, int64(v))
	}
	return
}
```

#### TypeScript

```ts
function maxScore(prices: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (let i = 0; i < prices.length; ++i) {
        const j = prices[i] - i;
        cnt.set(j, (cnt.get(j) || 0) + prices[i]);
    }
    return Math.max(...cnt.values());
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn max_score(prices: Vec<i32>) -> i64 {
        let mut cnt: HashMap<i32, i64> = HashMap::new();

        for (i, x) in prices.iter().enumerate() {
            let key = (*x as i32) - (i as i32);
            let count = cnt.entry(key).or_insert(0);
            *count += *x as i64;
        }

        *cnt.values().max().unwrap_or(&0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
