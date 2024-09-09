---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1599.Maximum%20Profit%20of%20Operating%20a%20Centennial%20Wheel/README_EN.md
rating: 1548
source: Weekly Contest 208 Q2
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [1599. Maximum Profit of Operating a Centennial Wheel](https://leetcode.com/problems/maximum-profit-of-operating-a-centennial-wheel)

[中文文档](/solution/1500-1599/1599.Maximum%20Profit%20of%20Operating%20a%20Centennial%20Wheel/README.md)

## Description

<!-- description:start -->

<p>You are the operator of a Centennial Wheel that has <strong>four gondolas</strong>, and each gondola has room for <strong>up</strong> <strong>to</strong> <strong>four people</strong>. You have the ability to rotate the gondolas <strong>counterclockwise</strong>, which costs you <code>runningCost</code> dollars.</p>

<p>You are given an array <code>customers</code> of length <code>n</code> where <code>customers[i]</code> is the number of new customers arriving just before the <code>i<sup>th</sup></code> rotation (0-indexed). This means you <strong>must rotate the wheel </strong><code>i</code><strong> times before the </strong><code>customers[i]</code><strong> customers arrive</strong>. <strong>You cannot make customers wait if there is room in the gondola</strong>. Each customer pays <code>boardingCost</code> dollars when they board on the gondola closest to the ground and will exit once that gondola reaches the ground again.</p>

<p>You can stop the wheel at any time, including <strong>before</strong> <strong>serving</strong> <strong>all</strong> <strong>customers</strong>. If you decide to stop serving customers, <strong>all subsequent rotations are free</strong> in order to get all the customers down safely. Note that if there are currently more than four customers waiting at the wheel, only four will board the gondola, and the rest will wait <strong>for the next rotation</strong>.</p>

<p>Return<em> the minimum number of rotations you need to perform to maximize your profit.</em> If there is <strong>no scenario</strong> where the profit is positive, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1599.Maximum%20Profit%20of%20Operating%20a%20Centennial%20Wheel/images/wheeldiagram12.png" style="width: 700px; height: 225px;" />
<pre>
<strong>Input:</strong> customers = [8,3], boardingCost = 5, runningCost = 6
<strong>Output:</strong> 3
<strong>Explanation:</strong> The numbers written on the gondolas are the number of people currently there.
1. 8 customers arrive, 4 board and 4 wait for the next gondola, the wheel rotates. Current profit is 4 * $5 - 1 * $6 = $14.
2. 3 customers arrive, the 4 waiting board the wheel and the other 3 wait, the wheel rotates. Current profit is 8 * $5 - 2 * $6 = $28.
3. The final 3 customers board the gondola, the wheel rotates. Current profit is 11 * $5 - 3 * $6 = $37.
The highest profit was $37 after rotating the wheel 3 times.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> customers = [10,9,6], boardingCost = 6, runningCost = 4
<strong>Output:</strong> 7
<strong>Explanation:</strong>
1. 10 customers arrive, 4 board and 6 wait for the next gondola, the wheel rotates. Current profit is 4 * $6 - 1 * $4 = $20.
2. 9 customers arrive, 4 board and 11 wait (2 originally waiting, 9 newly waiting), the wheel rotates. Current profit is 8 * $6 - 2 * $4 = $40.
3. The final 6 customers arrive, 4 board and 13 wait, the wheel rotates. Current profit is 12 * $6 - 3 * $4 = $60.
4. 4 board and 9 wait, the wheel rotates. Current profit is 16 * $6 - 4 * $4 = $80.
5. 4 board and 5 wait, the wheel rotates. Current profit is 20 * $6 - 5 * $4 = $100.
6. 4 board and 1 waits, the wheel rotates. Current profit is 24 * $6 - 6 * $4 = $120.
7. 1 boards, the wheel rotates. Current profit is 25 * $6 - 7 * $4 = $122.
The highest profit was $122 after rotating the wheel 7 times.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> customers = [3,4,0,5,1], boardingCost = 1, runningCost = 92
<strong>Output:</strong> -1
<strong>Explanation:</strong>
1. 3 customers arrive, 3 board and 0 wait, the wheel rotates. Current profit is 3 * $1 - 1 * $92 = -$89.
2. 4 customers arrive, 4 board and 0 wait, the wheel rotates. Current profit is 7 * $1 - 2 * $92 = -$177.
3. 0 customers arrive, 0 board and 0 wait, the wheel rotates. Current profit is 7 * $1 - 3 * $92 = -$269.
4. 5 customers arrive, 4 board and 1 waits, the wheel rotates. Current profit is 11 * $1 - 4 * $92 = -$357.
5. 1 customer arrives, 2 board and 0 wait, the wheel rotates. Current profit is 13 * $1 - 5 * $92 = -$447.
The profit was never positive, so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == customers.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= customers[i] &lt;= 50</code></li>
	<li><code>1 &lt;= boardingCost, runningCost &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We directly simulate the rotation process of the Ferris wheel. Each time it rotates, we add up the waiting customers and the newly arrived customers, then at most $4$ people get on the ride, update the number of waiting customers and profit, and record the maximum profit and its corresponding number of rotations.

The time complexity is $O(n)$, where $n$ is the length of the `customers` array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperationsMaxProfit(
        self, customers: List[int], boardingCost: int, runningCost: int
    ) -> int:
        ans = -1
        mx = t = 0
        wait = 0
        i = 0
        while wait or i < len(customers):
            wait += customers[i] if i < len(customers) else 0
            up = wait if wait < 4 else 4
            wait -= up
            t += up * boardingCost - runningCost
            i += 1
            if t > mx:
                mx = t
                ans = i
        return ans
```

#### Java

```java
class Solution {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int ans = -1;
        int mx = 0, t = 0;
        int wait = 0, i = 0;
        while (wait > 0 || i < customers.length) {
            wait += i < customers.length ? customers[i] : 0;
            int up = Math.min(4, wait);
            wait -= up;
            ++i;
            t += up * boardingCost - runningCost;
            if (t > mx) {
                mx = t;
                ans = i;
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
    int minOperationsMaxProfit(vector<int>& customers, int boardingCost, int runningCost) {
        int ans = -1;
        int mx = 0, t = 0;
        int wait = 0, i = 0;
        while (wait || i < customers.size()) {
            wait += i < customers.size() ? customers[i] : 0;
            int up = min(4, wait);
            wait -= up;
            ++i;
            t += up * boardingCost - runningCost;
            if (t > mx) {
                mx = t;
                ans = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minOperationsMaxProfit(customers []int, boardingCost int, runningCost int) int {
	ans := -1
	t, mx := 0, 0
	wait, i := 0, 0
	for wait > 0 || i < len(customers) {
		if i < len(customers) {
			wait += customers[i]
		}
		up := min(4, wait)
		wait -= up
		t += up*boardingCost - runningCost
		i++
		if t > mx {
			mx = t
			ans = i
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minOperationsMaxProfit(
    customers: number[],
    boardingCost: number,
    runningCost: number,
): number {
    let ans: number = -1;
    let [mx, t, wait, i] = [0, 0, 0, 0];
    while (wait > 0 || i < customers.length) {
        wait += i < customers.length ? customers[i] : 0;
        let up: number = Math.min(4, wait);
        wait -= up;
        ++i;
        t += up * boardingCost - runningCost;

        if (t > mx) {
            mx = t;
            ans = i;
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_operations_max_profit(
        customers: Vec<i32>,
        boarding_cost: i32,
        running_cost: i32,
    ) -> i32 {
        let mut ans = -1;
        let mut mx = 0;
        let mut t = 0;
        let mut wait = 0;
        let mut i = 0;

        while wait > 0 || i < customers.len() {
            wait += if i < customers.len() { customers[i] } else { 0 };
            let up = std::cmp::min(4, wait);
            wait -= up;
            i += 1;
            t += up * boarding_cost - running_cost;

            if t > mx {
                mx = t;
                ans = i as i32;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
