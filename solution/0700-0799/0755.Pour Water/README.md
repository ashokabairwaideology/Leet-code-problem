---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0755.Pour%20Water/README.md
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [755. 倒水 🔒](https://leetcode.cn/problems/pour-water)

[English Version](/solution/0700-0799/0755.Pour%20Water/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出一个地形高度图， <code>heights[i]</code> 表示该索引处的高度。每个索引的宽度为 1。在 <code>V</code> 个单位的水落在索引 <code>K</code> 处以后，每个索引位置有多少水？</p>

<p>水最先会在索引 <code>K</code> 处下降并且落在该索引位置的最高地形或水面之上。然后按如下方式流动：</p>

<ul>
	<li>如果液滴最终可以通过向左流动而下降，则向左流动。</li>
	<li>否则，如果液滴最终可以通过向右流动而下降，则向右流动。</li>
	<li>否则，在当前的位置上升。</li>
	<li>这里，“最终下降” 的意思是液滴如果按此方向移动的话，最终可以下降到一个较低的水平。而且，“水平”的意思是当前列的地形的高度加上水的高度。</li>
</ul>

<p>我们可以假定在数组两侧的边界外有无限高的地形。而且，不能有部分水在多于 1 个的网格块上均匀分布 - 每个单位的水必须要位于一个块中。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>heights = [2,1,1,2,1,2,2], V = 4, K = 3
<strong>输出：</strong>[2,2,2,3,2,2,2]
<strong>解释：</strong>
#       #
#       #
##  # ###
#########
 0123456    <- 索引

第一个水滴降落在索引 K = 3 上：

#       #
#   w   #
##  # ###
#########
 0123456    

当向左或向右移动时，水可以移动到相同或更低的高度。When moving left or right, the water can only move to the same level or a lower level.
（从水平上看，意思是该列的地形高度加上水的高度）
由于向左移动可以最终下落，因此向左移动。
（一个水滴 “下落” 的意思是可以相比之前可以进入更低的高度）

#       #
#       #
## w# ###
#########
 0123456    

由于向左移动不会使其降落，所以停在该位置上。下一个水滴下落：

#       #
#   w   #
## w# ###
#########
 0123456  


由于新水滴向左移动可以最终下落，因此向左移动。
注意水滴仍然是优先选择向左移动，
尽管可以向右移动（而且向右移动可以下落更快）


#       #
#  w    #
## w# ###
#########
 0123456  

#       #
#       #
##ww# ###
#########
 0123456  

经过刚才的阶段后，第三个水滴下落。
由于向左移动不会最终下落，因此尝试向右移动。
由于向右移动可以最终下落，因此向右移动。


#       #
#   w   #
##ww# ###
#########
 0123456  

#       #
#       #
##ww#w###
#########
 0123456  

最终，第四个水滴下落。
由于向左移动不会最终下落，因此尝试向右移动。
由于向右移动不会最终下落，因此停在当前位置：

#       #
#   w   #
##ww#w###
#########
 0123456  

最终的答案为 [2,2,2,3,2,2,2]:

    #    
 ####### 
 ####### 
 0123456 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>heights = [1,2,3,4], V = 2, K = 2
<strong>输出：</strong>[2,3,3,4]
<strong>解释：</strong>
最后的水滴落在索引 1 位置，因为继续向左移动不会使其下降到更低的高度。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>heights = [3,1,3], V = 5, K = 1
<strong>输出：</strong>[4,4,4]
</pre>

<p> </p>

<p><strong>注：</strong></p>

<ol>
	<li><code>heights</code> 的长度为 <code>[1, 100]</code> ，并且每个数的范围为<code>[0, 99]</code>。</li>
	<li><code>V</code> 的范围 <code>[0, 2000]</code>。</li>
	<li><code>K</code> 的范围 <code>[0, heights.length - 1]</code>。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以模拟每一单位的水滴下落的过程，每次下落时，我们首先尝试向左移动，如果可以移动到更低的高度，则移动到最低的高度处；如果不能移动到更低的高度，则尝试向右移动，如果可以移动到更低的高度，则移动到最低的高度处；如果不能移动到更低的高度，则在当前位置上升。

时间复杂度 $O(v \times n)，空间复杂度 O(1)$。其中 $v$ 和 $n$ 分别是水滴的数量和高度数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pourWater(self, heights: List[int], volume: int, k: int) -> List[int]:
        for _ in range(volume):
            for d in (-1, 1):
                i = j = k
                while 0 <= i + d < len(heights) and heights[i + d] <= heights[i]:
                    if heights[i + d] < heights[i]:
                        j = i + d
                    i += d
                if j != k:
                    heights[j] += 1
                    break
            else:
                heights[k] += 1
        return heights
```

#### Java

```java
class Solution {
    public int[] pourWater(int[] heights, int volume, int k) {
        while (volume-- > 0) {
            boolean find = false;
            for (int d = -1; d < 2 && !find; d += 2) {
                int i = k, j = k;
                while (i + d >= 0 && i + d < heights.length && heights[i + d] <= heights[i]) {
                    if (heights[i + d] < heights[i]) {
                        j = i + d;
                    }
                    i += d;
                }
                if (j != k) {
                    find = true;
                    ++heights[j];
                }
            }
            if (!find) {
                ++heights[k];
            }
        }
        return heights;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> pourWater(vector<int>& heights, int volume, int k) {
        while (volume--) {
            bool find = false;
            for (int d = -1; d < 2 && !find; d += 2) {
                int i = k, j = k;
                while (i + d >= 0 && i + d < heights.size() && heights[i + d] <= heights[i]) {
                    if (heights[i + d] < heights[i]) {
                        j = i + d;
                    }
                    i += d;
                }
                if (j != k) {
                    find = true;
                    ++heights[j];
                }
            }
            if (!find) {
                ++heights[k];
            }
        }
        return heights;
    }
};
```

#### Go

```go
func pourWater(heights []int, volume int, k int) []int {
	for ; volume > 0; volume-- {
		find := false
		for _, d := range [2]int{-1, 1} {
			i, j := k, k
			for i+d >= 0 && i+d < len(heights) && heights[i+d] <= heights[i] {
				if heights[i+d] < heights[i] {
					j = i + d
				}
				i += d
			}
			if j != k {
				find = true
				heights[j]++
				break
			}
		}
		if !find {
			heights[k]++
		}
	}
	return heights
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
