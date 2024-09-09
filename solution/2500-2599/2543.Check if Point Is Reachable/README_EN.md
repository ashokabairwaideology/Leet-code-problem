---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2543.Check%20if%20Point%20Is%20Reachable/README_EN.md
rating: 2220
source: Biweekly Contest 96 Q4
tags:
    - Math
    - Number Theory
---

<!-- problem:start -->

# [2543. Check if Point Is Reachable](https://leetcode.com/problems/check-if-point-is-reachable)

[中文文档](/solution/2500-2599/2543.Check%20if%20Point%20Is%20Reachable/README.md)

## Description

<!-- description:start -->

<p>There exists an infinitely large grid. You are currently at point <code>(1, 1)</code>, and you need to reach the point <code>(targetX, targetY)</code> using a finite number of steps.</p>

<p>In one <strong>step</strong>, you can move from point <code>(x, y)</code> to any one of the following points:</p>

<ul>
	<li><code>(x, y - x)</code></li>
	<li><code>(x - y, y)</code></li>
	<li><code>(2 * x, y)</code></li>
	<li><code>(x, 2 * y)</code></li>
</ul>

<p>Given two integers <code>targetX</code> and <code>targetY</code> representing the X-coordinate and Y-coordinate of your final position, return <code>true</code> <em>if you can reach the point from</em> <code>(1, 1)</code> <em>using some number of steps, and </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> targetX = 6, targetY = 9
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to reach (6,9) from (1,1) using any sequence of moves, so false is returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> targetX = 4, targetY = 7
<strong>Output:</strong> true
<strong>Explanation:</strong> You can follow the path (1,1) -&gt; (1,2) -&gt; (1,4) -&gt; (1,8) -&gt; (1,7) -&gt; (2,7) -&gt; (4,7).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= targetX, targetY&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

We notice that the first two types of moves do not change the greatest common divisor (gcd) of the horizontal and vertical coordinates, while the last two types of moves can multiply the gcd of the horizontal and vertical coordinates by a power of $2$. In other words, the final gcd of the horizontal and vertical coordinates must be a power of $2$. If the gcd is not a power of $2$, then it is impossible to reach.

Next, we prove that any $(x, y)$ that satisfies $gcd(x, y)=2^k$ can be reached.

We reverse the direction of movement, that is, move from the end point back. Then $(x, y)$ can move to $(x, x+y)$, $(x+y, y)$, $(\frac{x}{2}, y)$, and $(x, \frac{y}{2})$.

As long as $x$ or $y$ is even, we divide it by $2$ until both $x$ and $y$ are odd. At this point, if $x \neq y$, without loss of generality, let $x \gt y$, then $\frac{x+y}{2} \lt x$. Since $x+y$ is even, we can move from $(x, y)$ to $(x+y, y)$, and then to $(\frac{x+y}{2}, y)$ through operations. That is to say, we can always make $x$ and $y$ continuously decrease. When the loop ends, if $x=y=1$, it means it can be reached.

The time complexity is $O(\log(\min(targetX, targetY)))$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isReachable(self, targetX: int, targetY: int) -> bool:
        x = gcd(targetX, targetY)
        return x & (x - 1) == 0
```

#### Java

```java
class Solution {
    public boolean isReachable(int targetX, int targetY) {
        int x = gcd(targetX, targetY);
        return (x & (x - 1)) == 0;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isReachable(int targetX, int targetY) {
        int x = gcd(targetX, targetY);
        return (x & (x - 1)) == 0;
    }
};
```

#### Go

```go
func isReachable(targetX int, targetY int) bool {
	x := gcd(targetX, targetY)
	return x&(x-1) == 0
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function isReachable(targetX: number, targetY: number): boolean {
    const x = gcd(targetX, targetY);
    return (x & (x - 1)) === 0;
}

function gcd(a: number, b: number): number {
    return b == 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
