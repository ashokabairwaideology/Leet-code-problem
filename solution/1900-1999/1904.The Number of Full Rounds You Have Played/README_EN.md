---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1904.The%20Number%20of%20Full%20Rounds%20You%20Have%20Played/README_EN.md
rating: 1498
source: Weekly Contest 246 Q2
tags:
    - Math
    - String
---

<!-- problem:start -->

# [1904. The Number of Full Rounds You Have Played](https://leetcode.com/problems/the-number-of-full-rounds-you-have-played)

[中文文档](/solution/1900-1999/1904.The%20Number%20of%20Full%20Rounds%20You%20Have%20Played/README.md)

## Description

<!-- description:start -->

<p>You are participating in an online chess tournament. There is a chess round that starts every <code>15</code> minutes. The first round of the day starts at <code>00:00</code>, and after every <code>15</code> minutes, a new round starts.</p>

<ul>
	<li>For example, the second round starts at <code>00:15</code>, the fourth round starts at <code>00:45</code>, and the seventh round starts at <code>01:30</code>.</li>
</ul>

<p>You are given two strings <code>loginTime</code> and <code>logoutTime</code> where:</p>

<ul>
	<li><code>loginTime</code> is the time you will login to the game, and</li>
	<li><code>logoutTime</code> is the time you will logout from the game.</li>
</ul>

<p>If <code>logoutTime</code> is <strong>earlier</strong> than <code>loginTime</code>, this means you have played from <code>loginTime</code> to midnight and from midnight to <code>logoutTime</code>.</p>

<p>Return <em>the number of full chess rounds you have played in the tournament</em>.</p>

<p><strong>Note:</strong>&nbsp;All the given times follow the 24-hour clock. That means the first round of the day starts at <code>00:00</code> and the last round of the day starts at <code>23:45</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> loginTime = &quot;09:31&quot;, logoutTime = &quot;10:14&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> You played one full round from 09:45 to 10:00.
You did not play the full round from 09:30 to 09:45 because you logged in at 09:31 after it began.
You did not play the full round from 10:00 to 10:15 because you logged out at 10:14 before it ended.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> loginTime = &quot;21:30&quot;, logoutTime = &quot;03:00&quot;
<strong>Output:</strong> 22
<strong>Explanation:</strong> You played 10 full rounds from 21:30 to 00:00 and 12 full rounds from 00:00 to 03:00.
10 + 12 = 22.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>loginTime</code> and <code>logoutTime</code> are in the format <code>hh:mm</code>.</li>
	<li><code>00 &lt;= hh &lt;= 23</code></li>
	<li><code>00 &lt;= mm &lt;= 59</code></li>
	<li><code>loginTime</code> and <code>logoutTime</code> are not equal.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Convert to Minutes

We can convert the input strings to minutes $a$ and $b$. If $a > b$, it means that it crosses midnight, so we need to add one day's minutes $1440$ to $b$.

Then we round $a$ up to the nearest multiple of $15$, and round $b$ down to the nearest multiple of $15$. Finally, we return the difference between $b$ and $a$. Note that we should take the larger value between $0$ and $b - a$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfRounds(self, loginTime: str, logoutTime: str) -> int:
        def f(s: str) -> int:
            return int(s[:2]) * 60 + int(s[3:])

        a, b = f(loginTime), f(logoutTime)
        if a > b:
            b += 1440
        a, b = (a + 14) // 15, b // 15
        return max(0, b - a)
```

#### Java

```java
class Solution {
    public int numberOfRounds(String loginTime, String logoutTime) {
        int a = f(loginTime), b = f(logoutTime);
        if (a > b) {
            b += 1440;
        }
        return Math.max(0, b / 15 - (a + 14) / 15);
    }

    private int f(String s) {
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));
        return h * 60 + m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfRounds(string loginTime, string logoutTime) {
        auto f = [](string& s) {
            int h, m;
            sscanf(s.c_str(), "%d:%d", &h, &m);
            return h * 60 + m;
        };
        int a = f(loginTime), b = f(logoutTime);
        if (a > b) {
            b += 1440;
        }
        return max(0, b / 15 - (a + 14) / 15);
    }
};
```

#### Go

```go
func numberOfRounds(loginTime string, logoutTime string) int {
	f := func(s string) int {
		var h, m int
		fmt.Sscanf(s, "%d:%d", &h, &m)
		return h*60 + m
	}
	a, b := f(loginTime), f(logoutTime)
	if a > b {
		b += 1440
	}
	return max(0, b/15-(a+14)/15)
}
```

#### TypeScript

```ts
function numberOfRounds(startTime: string, finishTime: string): number {
    const f = (s: string): number => {
        const [h, m] = s.split(':').map(Number);
        return h * 60 + m;
    };
    let [a, b] = [f(startTime), f(finishTime)];
    if (a > b) {
        b += 1440;
    }
    return Math.max(0, Math.floor(b / 15) - Math.ceil(a / 15));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
