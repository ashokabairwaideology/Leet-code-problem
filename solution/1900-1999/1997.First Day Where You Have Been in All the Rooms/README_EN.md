---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1997.First%20Day%20Where%20You%20Have%20Been%20in%20All%20the%20Rooms/README_EN.md
rating: 2260
source: Weekly Contest 257 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1997. First Day Where You Have Been in All the Rooms](https://leetcode.com/problems/first-day-where-you-have-been-in-all-the-rooms)

[中文文档](/solution/1900-1999/1997.First%20Day%20Where%20You%20Have%20Been%20in%20All%20the%20Rooms/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> rooms you need to visit, labeled from <code>0</code> to <code>n - 1</code>. Each day is labeled, starting from <code>0</code>. You will go in and visit one room a day.</p>

<p>Initially on day <code>0</code>, you visit room <code>0</code>. The <strong>order</strong> you visit the rooms for the coming days is determined by the following <strong>rules</strong> and a given <strong>0-indexed</strong> array <code>nextVisit</code> of length <code>n</code>:</p>

<ul>
	<li>Assuming that on a day, you visit room <code>i</code>,</li>
	<li>if you have been in room <code>i</code> an <strong>odd</strong> number of times (<strong>including</strong> the current visit), on the <strong>next</strong> day you will visit a room with a <strong>lower or equal room number</strong> specified by <code>nextVisit[i]</code> where <code>0 &lt;= nextVisit[i] &lt;= i</code>;</li>
	<li>if you have been in room <code>i</code> an <strong>even</strong> number of times (<strong>including</strong> the current visit), on the <strong>next</strong> day you will visit room <code>(i + 1) mod n</code>.</li>
</ul>

<p>Return <em>the label of the <strong>first</strong> day where you have been in <strong>all</strong> the rooms</em>. It can be shown that such a day exists. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nextVisit = [0,0]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- On day 0, you visit room 0. The total times you have been in room 0 is 1, which is odd.
&nbsp; On the next day you will visit room nextVisit[0] = 0
- On day 1, you visit room 0, The total times you have been in room 0 is 2, which is even.
&nbsp; On the next day you will visit room (0 + 1) mod 2 = 1
- On day 2, you visit room 1. This is the first day where you have been in all the rooms.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nextVisit = [0,0,2]
<strong>Output:</strong> 6
<strong>Explanation:</strong>
Your room visiting order for each day is: [0,0,1,0,0,1,2,...].
Day 6 is the first day where you have been in all the rooms.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nextVisit = [0,1,2,0]
<strong>Output:</strong> 6
<strong>Explanation:</strong>
Your room visiting order for each day is: [0,0,1,1,2,2,3,...].
Day 6 is the first day where you have been in all the rooms.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nextVisit.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nextVisit[i] &lt;= i</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i]$ as the date number of the first visit to the $i$-th room, so the answer is $f[n - 1]$.

Consider the date number of the first arrival at the $(i-1)$-th room, denoted as $f[i-1]$. At this time, it takes one day to return to the $nextVisit[i-1]$-th room. Why return? Because the problem restricts $0 \leq nextVisit[i] \leq i$.

After returning, the $nextVisit[i-1]$-th room is visited an odd number of times, and the rooms from $nextVisit[i-1]+1$ to $i-1$ are visited an even number of times. At this time, we go to the $(i-1)$-th room again from the $nextVisit[i-1]$-th room, which takes $f[i-1] - f[nextVisit[i-1]]$ days, and then it takes one more day to reach the $i$-th room. Therefore, $f[i] = f[i-1] + 1 + f[i-1] - f[nextVisit[i-1]] + 1$. Since $f[i]$ may be very large, we need to take the remainder of $10^9 + 7$, and to prevent negative numbers, we need to add $10^9 + 7$.

Finally, return $f[n-1]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of rooms.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstDayBeenInAllRooms(self, nextVisit: List[int]) -> int:
        n = len(nextVisit)
        f = [0] * n
        mod = 10**9 + 7
        for i in range(1, n):
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1) % mod
        return f[-1]
```

#### Java

```java
class Solution {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] f = new long[n];
        final int mod = (int) 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
        }
        return (int) f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int firstDayBeenInAllRooms(vector<int>& nextVisit) {
        int n = nextVisit.size();
        vector<long long> f(n);
        const int mod = 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func firstDayBeenInAllRooms(nextVisit []int) int {
	n := len(nextVisit)
	f := make([]int, n)
	const mod = 1e9 + 7
	for i := 1; i < n; i++ {
		f[i] = (f[i-1] + 1 + f[i-1] - f[nextVisit[i-1]] + 1 + mod) % mod
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function firstDayBeenInAllRooms(nextVisit: number[]): number {
    const n = nextVisit.length;
    const mod = 1e9 + 7;
    const f: number[] = new Array<number>(n).fill(0);
    for (let i = 1; i < n; ++i) {
        f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
    }
    return f[n - 1];
}
```

#### C#

```cs
public class Solution {
    public int FirstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.Length;
        long[] f = new long[n];
        int mod = (int)1e9 + 7;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
        }
        return (int)f[n - 1];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
