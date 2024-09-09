---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1620.Coordinate%20With%20Maximum%20Network%20Quality/README.md
rating: 1665
source: 第 37 场双周赛 Q2
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [1620. 网络信号最好的坐标](https://leetcode.cn/problems/coordinate-with-maximum-network-quality)

[English Version](/solution/1600-1699/1620.Coordinate%20With%20Maximum%20Network%20Quality/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>towers</code>&nbsp;和一个整数 <code>radius</code> 。</p>

<p>数组&nbsp; <code>towers</code>&nbsp; 中包含一些网络信号塔，其中&nbsp;<code>towers[i] = [x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个网络信号塔的坐标是&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;且信号强度参数为&nbsp;<code>q<sub>i</sub></code><sub>&nbsp;</sub>。所有坐标都是在&nbsp; X-Y 坐标系内的&nbsp;<strong>整数</strong>&nbsp;坐标。两个坐标之间的距离用 <strong>欧几里得距离</strong>&nbsp;计算。</p>

<p>整数&nbsp;<code>radius</code>&nbsp;表示一个塔 <strong>能到达&nbsp;</strong>的 <strong>最远距离</strong>&nbsp;。如果一个坐标跟塔的距离在 <code>radius</code>&nbsp;以内，那么该塔的信号可以到达该坐标。在这个范围以外信号会很微弱，所以 <code>radius</code>&nbsp;以外的距离该塔是 <strong>不能到达的</strong>&nbsp;。</p>

<p>如果第 <code>i</code>&nbsp;个塔能到达 <code>(x, y)</code>&nbsp;，那么该塔在此处的信号为&nbsp;<code>⌊q<sub>i</sub> / (1 + d)⌋</code>&nbsp;，其中&nbsp;<code>d</code>&nbsp;是塔跟此坐标的距离。一个坐标的 <b>信号强度</b> 是所有 <strong>能到达&nbsp;</strong>该坐标的塔的信号强度之和。</p>

<p>请你返回数组 <code>[c<sub>x</sub>, c<sub>y</sub>]</code> ，表示 <strong>信号强度</strong> 最大的 <strong>整数</strong> 坐标点&nbsp;<code>(c<sub>x</sub>, c<sub>y</sub>)</code> 。如果有多个坐标网络信号一样大，请你返回字典序最小的 <strong>非负</strong> 坐标。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>坐标&nbsp;<code>(x1, y1)</code>&nbsp;字典序比另一个坐标&nbsp;<code>(x2, y2)</code> 小，需满足以下条件之一：

    <ul>
    	<li>要么&nbsp;<code>x1 &lt; x2</code>&nbsp;，</li>
    	<li>要么&nbsp;<code>x1 == x2</code> 且&nbsp;<code>y1 &lt; y2</code>&nbsp;。</li>
    </ul>
    </li>
    <li><code>⌊val⌋</code>&nbsp;表示小于等于&nbsp;<code>val</code>&nbsp;的最大整数（向下取整函数）。</li>

</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1620.Coordinate%20With%20Maximum%20Network%20Quality/images/untitled-diagram.png" style="width: 176px; height: 176px;" />
<pre>
<b>输入：</b>towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2
<b>输出：</b>[2,1]
<strong>解释：</strong>
坐标 (2, 1) 信号强度之和为 13
- 塔 (2, 1) 强度参数为 7 ，在该点强度为 ⌊7 / (1 + sqrt(0)⌋ = ⌊7⌋ = 7
- 塔 (1, 2) 强度参数为 5 ，在该点强度为 ⌊5 / (1 + sqrt(2)⌋ = ⌊2.07⌋ = 2
- 塔 (3, 1) 强度参数为 9 ，在该点强度为 ⌊9 / (1 + sqrt(1)⌋ = ⌊4.5⌋ = 4
没有别的坐标有更大的信号强度。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>towers = [[23,11,21]], radius = 9
<b>输出：</b>[23,11]
<strong>解释：</strong>由于仅存在一座信号塔，所以塔的位置信号强度最大。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2
<b>输出：</b>[1,2]
<strong>解释：</strong>坐标 (1, 2) 的信号强度最大。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= towers.length &lt;= 50</code></li>
	<li><code>towers[i].length == 3</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub> &lt;= 50</code></li>
	<li><code>1 &lt;= radius &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：暴力枚举

由于坐标点的范围是 $[0,.. 50]$，因此我们可以直接暴力枚举所有的坐标点 $(i, j)$，计算每个坐标点的信号强度，然后找出信号强度最大的坐标点。

时间复杂度 $O(n \times C^2)$，其中 $n$ 是信号塔的数量，而 $C$ 是坐标点的范围大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bestCoordinate(self, towers: List[List[int]], radius: int) -> List[int]:
        mx = 0
        ans = [0, 0]
        for i in range(51):
            for j in range(51):
                t = 0
                for x, y, q in towers:
                    d = ((x - i) ** 2 + (y - j) ** 2) ** 0.5
                    if d <= radius:
                        t += floor(q / (1 + d))
                if t > mx:
                    mx = t
                    ans = [i, j]
        return ans
```

#### Java

```java
class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int mx = 0;
        int[] ans = new int[] {0, 0};
        for (int i = 0; i < 51; ++i) {
            for (int j = 0; j < 51; ++j) {
                int t = 0;
                for (var e : towers) {
                    double d = Math.sqrt((i - e[0]) * (i - e[0]) + (j - e[1]) * (j - e[1]));
                    if (d <= radius) {
                        t += Math.floor(e[2] / (1 + d));
                    }
                }
                if (mx < t) {
                    mx = t;
                    ans = new int[] {i, j};
                }
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
    vector<int> bestCoordinate(vector<vector<int>>& towers, int radius) {
        int mx = 0;
        vector<int> ans = {0, 0};
        for (int i = 0; i < 51; ++i) {
            for (int j = 0; j < 51; ++j) {
                int t = 0;
                for (auto& e : towers) {
                    double d = sqrt((i - e[0]) * (i - e[0]) + (j - e[1]) * (j - e[1]));
                    if (d <= radius) {
                        t += floor(e[2] / (1 + d));
                    }
                }
                if (mx < t) {
                    mx = t;
                    ans = {i, j};
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func bestCoordinate(towers [][]int, radius int) []int {
	ans := []int{0, 0}
	mx := 0
	for i := 0; i < 51; i++ {
		for j := 0; j < 51; j++ {
			t := 0
			for _, e := range towers {
				d := math.Sqrt(float64((i-e[0])*(i-e[0]) + (j-e[1])*(j-e[1])))
				if d <= float64(radius) {
					t += int(float64(e[2]) / (1 + d))
				}
			}
			if mx < t {
				mx = t
				ans = []int{i, j}
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
