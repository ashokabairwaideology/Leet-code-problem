---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0469.Convex%20Polygon/README_EN.md
tags:
    - Geometry
    - Array
    - Math
---

<!-- problem:start -->

# [469. Convex Polygon 🔒](https://leetcode.com/problems/convex-polygon)

[中文文档](/solution/0400-0499/0469.Convex%20Polygon/README.md)

## Description

<!-- description:start -->

<p>You are given an array of points on the <strong>X-Y</strong> plane <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>. The points form a polygon when joined sequentially.</p>

<p>Return <code>true</code> if this polygon is <a href="http://en.wikipedia.org/wiki/Convex_polygon" target="_blank">convex</a> and <code>false</code> otherwise.</p>

<p>You may assume the polygon formed by given points is always a <a href="http://en.wikipedia.org/wiki/Simple_polygon" target="_blank">simple polygon</a>. In other words, we ensure that exactly two edges intersect at each vertex and that edges otherwise don&#39;t intersect each other.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0469.Convex%20Polygon/images/covpoly1-plane.jpg" style="width: 300px; height: 294px;" />
<pre>
<strong>Input:</strong> points = [[0,0],[0,5],[5,5],[5,0]]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0469.Convex%20Polygon/images/covpoly2-plane.jpg" style="width: 300px; height: 303px;" />
<pre>
<strong>Input:</strong> points = [[0,0],[0,10],[10,10],[10,0],[5,5]]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= points.length &lt;= 10<sup>4</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>All the given points are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isConvex(self, points: List[List[int]]) -> bool:
        n = len(points)
        pre = cur = 0
        for i in range(n):
            x1 = points[(i + 1) % n][0] - points[i][0]
            y1 = points[(i + 1) % n][1] - points[i][1]
            x2 = points[(i + 2) % n][0] - points[i][0]
            y2 = points[(i + 2) % n][1] - points[i][1]
            cur = x1 * y2 - x2 * y1
            if cur != 0:
                if cur * pre < 0:
                    return False
                pre = cur
        return True
```

#### Java

```java
class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        long pre = 0, cur = 0;
        for (int i = 0; i < n; ++i) {
            var p1 = points.get(i);
            var p2 = points.get((i + 1) % n);
            var p3 = points.get((i + 2) % n);
            int x1 = p2.get(0) - p1.get(0);
            int y1 = p2.get(1) - p1.get(1);
            int x2 = p3.get(0) - p1.get(0);
            int y2 = p3.get(1) - p1.get(1);
            cur = x1 * y2 - x2 * y1;
            if (cur != 0) {
                if (cur * pre < 0) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isConvex(vector<vector<int>>& points) {
        int n = points.size();
        long long pre = 0, cur = 0;
        for (int i = 0; i < n; ++i) {
            int x1 = points[(i + 1) % n][0] - points[i][0];
            int y1 = points[(i + 1) % n][1] - points[i][1];
            int x2 = points[(i + 2) % n][0] - points[i][0];
            int y2 = points[(i + 2) % n][1] - points[i][1];
            cur = 1L * x1 * y2 - x2 * y1;
            if (cur != 0) {
                if (cur * pre < 0) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isConvex(points [][]int) bool {
	n := len(points)
	pre, cur := 0, 0
	for i := range points {
		x1 := points[(i+1)%n][0] - points[i][0]
		y1 := points[(i+1)%n][1] - points[i][1]
		x2 := points[(i+2)%n][0] - points[i][0]
		y2 := points[(i+2)%n][1] - points[i][1]
		cur = x1*y2 - x2*y1
		if cur != 0 {
			if cur*pre < 0 {
				return false
			}
			pre = cur
		}
	}
	return true
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
