---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1672.Richest%20Customer%20Wealth/README_EN.md
rating: 1182
source: Weekly Contest 217 Q1
tags:
    - Array
    - Matrix
---

<!-- problem:start -->

# [1672. Richest Customer Wealth](https://leetcode.com/problems/richest-customer-wealth)

[中文文档](/solution/1600-1699/1672.Richest%20Customer%20Wealth/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> integer grid <code>accounts</code> where <code>accounts[i][j]</code> is the amount of money the <code>i​​​​​<sup>​​​​​​th</sup>​​​​</code> customer has in the <code>j​​​​​<sup>​​​​​​th</sup></code>​​​​ bank. Return<em> the <strong>wealth</strong> that the richest customer has.</em></p>

<p>A customer&#39;s <strong>wealth</strong> is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum <strong>wealth</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[1,2,3],[3,2,1]]
<strong>Output:</strong> 6
<strong>Explanation</strong><strong>:</strong>
<code>1st customer has wealth = 1 + 2 + 3 = 6
</code><code>2nd customer has wealth = 3 + 2 + 1 = 6
</code>Both customers are considered the richest with a wealth of 6 each, so return 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[1,5],[7,3],[3,5]]
<strong>Output:</strong> 10
<strong>Explanation</strong>: 
1st customer has wealth = 6
2nd customer has wealth = 10 
3rd customer has wealth = 8
The 2nd customer is the richest with a wealth of 10.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[2,8,7],[7,1,3],[1,9,5]]
<strong>Output:</strong> 17
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;accounts.length</code></li>
	<li><code>n ==&nbsp;accounts[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= accounts[i][j] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Summation

We traverse `accounts` and find the maximum sum of each row.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns in the grid, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumWealth(self, accounts: List[List[int]]) -> int:
        return max(sum(v) for v in accounts)
```

#### Java

```java
class Solution {
    public int maximumWealth(int[][] accounts) {
        int ans = 0;
        for (var e : accounts) {
            // int s = Arrays.stream(e).sum();
            int s = 0;
            for (int v : e) {
                s += v;
            }
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumWealth(vector<vector<int>>& accounts) {
        int ans = 0;
        for (auto& v : accounts) {
            ans = max(ans, accumulate(v.begin(), v.end(), 0));
        }
        return ans;
    }
};
```

#### Go

```go
func maximumWealth(accounts [][]int) int {
	ans := 0
	for _, e := range accounts {
		s := 0
		for _, v := range e {
			s += v
		}
		if ans < s {
			ans = s
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maximumWealth(accounts: number[][]): number {
    return accounts.reduce(
        (r, v) =>
            Math.max(
                r,
                v.reduce((r, v) => r + v),
            ),
        0,
    );
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_wealth(accounts: Vec<Vec<i32>>) -> i32 {
        accounts.iter().map(|v| v.iter().sum()).max().unwrap()
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[][] $accounts
     * @return Integer
     */
    function maximumWealth($accounts) {
        $rs = 0;
        for ($i = 0; $i < count($accounts); $i++) {
            $sum = 0;
            for ($j = 0; $j < count($accounts[$i]); $j++) {
                $sum += $accounts[$i][$j];
            }
            if ($sum > $rs) {
                $rs = $sum;
            }
        }
        return $rs;
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int maximumWealth(int** accounts, int accountsSize, int* accountsColSize) {
    int ans = INT_MIN;
    for (int i = 0; i < accountsSize; i++) {
        int sum = 0;
        for (int j = 0; j < accountsColSize[i]; j++) {
            sum += accounts[i][j];
        }
        ans = max(ans, sum);
    }
    return ans;
}
```

#### Kotlin

```kotlin
class Solution {
    fun maximumWealth(accounts: Array<IntArray>): Int {
        var max = 0
        for (account in accounts) {
            val sum = account.sum()
            if (sum > max) {
                max = sum
            }
        }
        return max
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
