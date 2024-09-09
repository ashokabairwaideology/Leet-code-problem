---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1701.Average%20Waiting%20Time/README_EN.md
rating: 1436
source: Biweekly Contest 42 Q2
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [1701. Average Waiting Time](https://leetcode.com/problems/average-waiting-time)

[中文文档](/solution/1700-1799/1701.Average%20Waiting%20Time/README.md)

## Description

<!-- description:start -->

<p>There is a restaurant with a single chef. You are given an array <code>customers</code>, where <code>customers[i] = [arrival<sub>i</sub>, time<sub>i</sub>]:</code></p>

<ul>
	<li><code>arrival<sub>i</sub></code> is the arrival time of the <code>i<sup>th</sup></code> customer. The arrival times are sorted in <strong>non-decreasing</strong> order.</li>
	<li><code>time<sub>i</sub></code> is the time needed to prepare the order of the <code>i<sup>th</sup></code> customer.</li>
</ul>

<p>When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle. The customer waits till the chef finishes preparing his order. The chef does not prepare food for more than one customer at a time. The chef prepares food for customers <strong>in the order they were given in the input</strong>.</p>

<p>Return <em>the <strong>average</strong> waiting time of all customers</em>. Solutions within <code>10<sup>-5</sup></code> from the actual answer are considered accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> customers = [[1,2],[2,5],[4,3]]
<strong>Output:</strong> 5.00000
<strong>Explanation:
</strong>1) The first customer arrives at time 1, the chef takes his order and starts preparing it immediately at time 1, and finishes at time 3, so the waiting time of the first customer is 3 - 1 = 2.
2) The second customer arrives at time 2, the chef takes his order and starts preparing it at time 3, and finishes at time 8, so the waiting time of the second customer is 8 - 2 = 6.
3) The third customer arrives at time 4, the chef takes his order and starts preparing it at time 8, and finishes at time 11, so the waiting time of the third customer is 11 - 4 = 7.
So the average waiting time = (2 + 6 + 7) / 3 = 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> customers = [[5,2],[5,4],[10,3],[20,1]]
<strong>Output:</strong> 3.25000
<strong>Explanation:
</strong>1) The first customer arrives at time 5, the chef takes his order and starts preparing it immediately at time 5, and finishes at time 7, so the waiting time of the first customer is 7 - 5 = 2.
2) The second customer arrives at time 5, the chef takes his order and starts preparing it at time 7, and finishes at time 11, so the waiting time of the second customer is 11 - 5 = 6.
3) The third customer arrives at time 10, the chef takes his order and starts preparing it at time 11, and finishes at time 14, so the waiting time of the third customer is 14 - 10 = 4.
4) The fourth customer arrives at time 20, the chef takes his order and starts preparing it immediately at time 20, and finishes at time 21, so the waiting time of the fourth customer is 21 - 20 = 1.
So the average waiting time = (2 + 6 + 4 + 1) / 4 = 3.25.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= customers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arrival<sub>i</sub>, time<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>arrival<sub>i&nbsp;</sub>&lt;= arrival<sub>i+1</sub></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a variable `tot` to record the total waiting time of the customers, and a variable `t` to record the time when each customer's order is completed. The initial values of both are $0$.

We traverse the customer array `customers`. For each customer:

If the current time `t` is less than or equal to the customer's arrival time `customers[i][0]`, it means that the chef is not cooking, so the chef can start cooking immediately. The time to complete this dish is $t = customers[i][0] + customers[i][1]$, and the customer's waiting time is `customers[i][1]`.

Otherwise, it means that the chef is cooking, so the customer needs to wait for the chef to finish the previous dishes before starting to cook their own dishes. The time to complete this dish is $t = t + customers[i][1]$, and the customer's waiting time is $t - customers[i][0]$.

The time complexity is $O(n)$, where $n$ is the length of the customer array `customers`. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def averageWaitingTime(self, customers: List[List[int]]) -> float:
        tot = t = 0
        for a, b in customers:
            t = max(t, a) + b
            tot += t - a
        return tot / len(customers)
```

#### Java

```java
class Solution {
    public double averageWaitingTime(int[][] customers) {
        double tot = 0;
        int t = 0;
        for (var e : customers) {
            int a = e[0], b = e[1];
            t = Math.max(t, a) + b;
            tot += t - a;
        }
        return tot / customers.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double averageWaitingTime(vector<vector<int>>& customers) {
        double tot = 0;
        int t = 0;
        for (auto& e : customers) {
            int a = e[0], b = e[1];
            t = max(t, a) + b;
            tot += t - a;
        }
        return tot / customers.size();
    }
};
```

#### Go

```go
func averageWaitingTime(customers [][]int) float64 {
	tot, t := 0, 0
	for _, e := range customers {
		a, b := e[0], e[1]
		t = max(t, a) + b
		tot += t - a
	}
	return float64(tot) / float64(len(customers))
}
```

#### TypeScript

```ts
function averageWaitingTime(customers: number[][]): number {
    let [tot, t] = [0, 0];
    for (const [a, b] of customers) {
        t = Math.max(t, a) + b;
        tot += t - a;
    }
    return tot / customers.length;
}
```

#### Rust

```rust
impl Solution {
    pub fn average_waiting_time(customers: Vec<Vec<i32>>) -> f64 {
        let mut tot = 0.0;
        let mut t = 0;

        for e in customers.iter() {
            let a = e[0];
            let b = e[1];
            t = t.max(a) + b;
            tot += (t - a) as f64;
        }

        tot / customers.len() as f64
    }
}
```

#### JavaScript

```js
function averageWaitingTime(customers) {
    let [tot, t] = [0, 0];
    for (const [a, b] of customers) {
        t = Math.max(t, a) + b;
        tot += t - a;
    }
    return tot / customers.length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
