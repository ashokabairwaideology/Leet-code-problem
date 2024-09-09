---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0335.Self%20Crossing/README.md
tags:
    - 几何
    - 数组
    - 数学
---

<!-- problem:start -->

# [335. 路径交叉](https://leetcode.cn/problems/self-crossing)

[English Version](/solution/0300-0399/0335.Self%20Crossing/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>distance</code><em> </em>。</p>

<p>从 <strong>X-Y</strong> 平面上的点&nbsp;<code>(0,0)</code>&nbsp;开始，先向北移动 <code>distance[0]</code> 米，然后向西移动 <code>distance[1]</code> 米，向南移动 <code>distance[2]</code> 米，向东移动 <code>distance[3]</code> 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。</p>

<p>判断你所经过的路径是否相交。如果相交，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0335.Self%20Crossing/images/selfcross1-plane.jpg" style="width: 400px; height: 435px;" />
<pre>
<strong>输入：</strong>distance = [2,1,1,2]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0335.Self%20Crossing/images/selfcross2-plane.jpg" style="width: 400px; height: 435px;" />
<pre>
<strong>输入：</strong>distance = [1,2,3,4]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0335.Self%20Crossing/images/selfcross3-plane.jpg" style="width: 400px; height: 435px;" />
<pre>
<strong>输入：</strong>distance = [1,1,1,1]
<strong>输出：</strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;distance.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;=&nbsp;distance[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isSelfCrossing(self, distance: List[int]) -> bool:
        d = distance
        for i in range(3, len(d)):
            if d[i] >= d[i - 2] and d[i - 1] <= d[i - 3]:
                return True
            if i >= 4 and d[i - 1] == d[i - 3] and d[i] + d[i - 4] >= d[i - 2]:
                return True
            if (
                i >= 5
                and d[i - 2] >= d[i - 4]
                and d[i - 1] <= d[i - 3]
                and d[i] >= d[i - 2] - d[i - 4]
                and d[i - 1] + d[i - 5] >= d[i - 3]
            ):
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean isSelfCrossing(int[] distance) {
        int[] d = distance;
        for (int i = 3; i < d.length; ++i) {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) {
                return true;
            }
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) {
                return true;
            }
            if (i >= 5 && d[i - 2] >= d[i - 4] && d[i - 1] <= d[i - 3]
                && d[i] >= d[i - 2] - d[i - 4] && d[i - 1] + d[i - 5] >= d[i - 3]) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isSelfCrossing(vector<int>& distance) {
        vector<int> d = distance;
        for (int i = 3; i < d.size(); ++i) {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) return true;
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) return true;
            if (i >= 5 && d[i - 2] >= d[i - 4] && d[i - 1] <= d[i - 3] && d[i] >= d[i - 2] - d[i - 4] && d[i - 1] + d[i - 5] >= d[i - 3]) return true;
        }
        return false;
    }
};
```

#### Go

```go
func isSelfCrossing(distance []int) bool {
	d := distance
	for i := 3; i < len(d); i++ {
		if d[i] >= d[i-2] && d[i-1] <= d[i-3] {
			return true
		}
		if i >= 4 && d[i-1] == d[i-3] && d[i]+d[i-4] >= d[i-2] {
			return true
		}
		if i >= 5 && d[i-2] >= d[i-4] && d[i-1] <= d[i-3] && d[i] >= d[i-2]-d[i-4] && d[i-1]+d[i-5] >= d[i-3] {
			return true
		}
	}
	return false
}
```

#### C#

```cs
public class Solution {
    public bool IsSelfCrossing(int[] x) {
        for (var i = 3; i < x.Length; ++i)
        {
            if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) return true;
            if (i > 3 && x[i] + x[i - 4] >= x[i - 2])
            {
                if (x[i - 1] == x[i - 3]) return true;
                if (i > 4 && x[i - 2] >= x[i - 4] && x[i - 1] <= x[i - 3] && x[i - 1] + x[i - 5] >= x[i - 3]) return true;
            }
        }
        return false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
