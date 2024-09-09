---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/README.md
rating: 1324
source: 第 19 场双周赛 Q3
tags:
    - 数学
---

<!-- problem:start -->

# [1344. 时钟指针的夹角](https://leetcode.cn/problems/angle-between-hands-of-a-clock)

[English Version](/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个数&nbsp;<code>hour</code>&nbsp;和&nbsp;<code>minutes</code>&nbsp;。请你返回在时钟上，由给定时间的时针和分针组成的较小角的角度（60 单位制）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/images/sample_1_1673.png" style="height: 225px; width: 230px;"></p>

<pre><strong>输入：</strong>hour = 12, minutes = 30
<strong>输出：</strong>165
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/images/sample_2_1673.png" style="height: 225px; width: 230px;"></p>

<pre><strong>输入：</strong>hour = 3, minutes = 30
<strong>输出；</strong>75
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/images/sample_3_1673.png" style="height: 231px; width: 230px;"></strong></p>

<pre><strong>输入：</strong>hour = 3, minutes = 15
<strong>输出：</strong>7.5
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>hour = 4, minutes = 50
<strong>输出：</strong>155
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>hour = 12, minutes = 0
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= hour &lt;= 12</code></li>
	<li><code>0 &lt;= minutes &lt;= 59</code></li>
	<li>与标准答案误差在&nbsp;<code>10^-5</code>&nbsp;以内的结果都被视为正确结果。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

时针每小时移动 30 度，每分钟移动 0.5 度。分针每分钟移动 6 度。如果指针之间的夹角大于 180 度，则取其与 360 度的差值，以确保获得最小的夹角。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def angleClock(self, hour: int, minutes: int) -> float:
        h = 30 * hour + 0.5 * minutes
        m = 6 * minutes
        diff = abs(h - m)
        return min(diff, 360 - diff)
```

#### Java

```java
class Solution {
    public double angleClock(int hour, int minutes) {
        double h = 30 * hour + 0.5 * minutes;
        double m = 6 * minutes;
        double diff = Math.abs(h - m);
        return Math.min(diff, 360 - diff);
    }
}
```

#### C++

```cpp
class Solution {
public:
    double angleClock(int hour, int minutes) {
        double h = 30 * hour + 0.5 * minutes;
        double m = 6 * minutes;
        double diff = abs(h - m);
        return min(diff, 360 - diff);
    }
};
```

#### Go

```go
func angleClock(hour int, minutes int) float64 {
	h := 30*float64(hour) + 0.5*float64(minutes)
	m := 6 * float64(minutes)
	diff := math.Abs(h - m)
	return math.Min(diff, 360-diff)
}
```

#### TypeScript

```ts
function angleClock(hour: number, minutes: number): number {
    const h = 30 * hour + 0.5 * minutes;
    const m = 6 * minutes;
    const diff = Math.abs(h - m);
    return Math.min(diff, 360 - diff);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
