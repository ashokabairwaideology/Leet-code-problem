---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0808.Soup%20Servings/README_EN.md
tags:
    - Math
    - Dynamic Programming
    - Probability and Statistics
---

<!-- problem:start -->

# [808. Soup Servings](https://leetcode.com/problems/soup-servings)

[中文文档](/solution/0800-0899/0808.Soup%20Servings/README.md)

## Description

<!-- description:start -->

<p>There are two types of soup: <strong>type A</strong> and <strong>type B</strong>. Initially, we have <code>n</code> ml of each type of soup. There are four kinds of operations:</p>

<ol>
	<li>Serve <code>100</code> ml of <strong>soup A</strong> and <code>0</code> ml of <strong>soup B</strong>,</li>
	<li>Serve <code>75</code> ml of <strong>soup A</strong> and <code>25</code> ml of <strong>soup B</strong>,</li>
	<li>Serve <code>50</code> ml of <strong>soup A</strong> and <code>50</code> ml of <strong>soup B</strong>, and</li>
	<li>Serve <code>25</code> ml of <strong>soup A</strong> and <code>75</code> ml of <strong>soup B</strong>.</li>
</ol>

<p>When we serve some soup, we give it to someone, and we no longer have it. Each turn, we will choose from the four operations with an equal probability <code>0.25</code>. If the remaining volume of soup is not enough to complete the operation, we will serve as much as possible. We stop once we no longer have some quantity of both types of soup.</p>

<p><strong>Note</strong> that we do not have an operation where all <code>100</code> ml&#39;s of <strong>soup B</strong> are used first.</p>

<p>Return <em>the probability that <strong>soup A</strong> will be empty first, plus half the probability that <strong>A</strong> and <strong>B</strong> become empty at the same time</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 50
<strong>Output:</strong> 0.62500
<strong>Explanation:</strong> If we choose the first two operations, A will become empty first.
For the third operation, A and B will become empty at the same time.
For the fourth operation, B will become empty first.
So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 100
<strong>Output:</strong> 0.71875
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def soupServings(self, n: int) -> float:
        @cache
        def dfs(i: int, j: int) -> float:
            if i <= 0 and j <= 0:
                return 0.5
            if i <= 0:
                return 1
            if j <= 0:
                return 0
            return 0.25 * (
                dfs(i - 4, j)
                + dfs(i - 3, j - 1)
                + dfs(i - 2, j - 2)
                + dfs(i - 1, j - 3)
            )

        return 1 if n > 4800 else dfs((n + 24) // 25, (n + 24) // 25)
```

#### Java

```java
class Solution {
    private double[][] f = new double[200][200];

    public double soupServings(int n) {
        return n > 4800 ? 1 : dfs((n + 24) / 25, (n + 24) / 25);
    }

    private double dfs(int i, int j) {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1.0;
        }
        if (j <= 0) {
            return 0;
        }
        if (f[i][j] > 0) {
            return f[i][j];
        }
        double ans
            = 0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
        f[i][j] = ans;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double soupServings(int n) {
        double f[200][200] = {0.0};
        function<double(int, int)> dfs = [&](int i, int j) -> double {
            if (i <= 0 && j <= 0) return 0.5;
            if (i <= 0) return 1;
            if (j <= 0) return 0;
            if (f[i][j] > 0) return f[i][j];
            double ans = 0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
            f[i][j] = ans;
            return ans;
        };
        return n > 4800 ? 1 : dfs((n + 24) / 25, (n + 24) / 25);
    }
};
```

#### Go

```go
func soupServings(n int) float64 {
	if n > 4800 {
		return 1
	}
	f := [200][200]float64{}
	var dfs func(i, j int) float64
	dfs = func(i, j int) float64 {
		if i <= 0 && j <= 0 {
			return 0.5
		}
		if i <= 0 {
			return 1.0
		}
		if j <= 0 {
			return 0
		}
		if f[i][j] > 0 {
			return f[i][j]
		}
		ans := 0.25 * (dfs(i-4, j) + dfs(i-3, j-1) + dfs(i-2, j-2) + dfs(i-1, j-3))
		f[i][j] = ans
		return ans
	}
	return dfs((n+24)/25, (n+24)/25)
}
```

#### TypeScript

```ts
function soupServings(n: number): number {
    const f = new Array(200).fill(0).map(() => new Array(200).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1;
        }
        if (j <= 0) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] =
            0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
        return f[i][j];
    };
    return n >= 4800 ? 1 : dfs(Math.ceil(n / 25), Math.ceil(n / 25));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
