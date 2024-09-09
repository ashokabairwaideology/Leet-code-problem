---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0011.Container%20With%20Most%20Water/README_EN.md
tags:
    - Greedy
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water)

[中文文档](/solution/0000-0099/0011.Container%20With%20Most%20Water/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>height</code> of length <code>n</code>. There are <code>n</code> vertical lines drawn such that the two endpoints of the <code>i<sup>th</sup></code> line are <code>(i, 0)</code> and <code>(i, height[i])</code>.</p>

<p>Find two lines that together with the x-axis form a container, such that the container contains the most water.</p>

<p>Return <em>the maximum amount of water a container can store</em>.</p>

<p><strong>Notice</strong> that you may not slant the container.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0011.Container%20With%20Most%20Water/images/question_11.jpg" style="width: 600px; height: 287px;" />
<pre>
<strong>Input:</strong> height = [1,8,6,2,5,4,8,3,7]
<strong>Output:</strong> 49
<strong>Explanation:</strong> The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [1,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

Initially, we consider the capacity of the water that the two farthest pillars can hold. The width of the water is the distance between the two pillars, and the height of the water depends on the shorter one between the two pillars.

The current pillars are the pillars on the farthest sides, so the width of the water is the largest. For other combinations, the width of the water is smaller. Suppose the height of the left pillar is less than or equal to the height of the right pillar, then the height of the water is the height of the left pillar. If we move the right pillar, the width of the water will decrease, but the height of the water will not increase, so the capacity of the water will definitely decrease. Therefore, we move the left pillar and update the maximum capacity.

Repeat this process until the two pillars meet.

The time complexity is $O(n)$, where $n$ is the length of the array `height`. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        i, j = 0, len(height) - 1
        ans = 0
        while i < j:
            t = (j - i) * min(height[i], height[j])
            ans = max(ans, t)
            if height[i] < height[j]:
                i += 1
            else:
                j -= 1
        return ans
```

#### Java

```java
class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int ans = 0;
        while (i < j) {
            int t = Math.min(height[i], height[j]) * (j - i);
            ans = Math.max(ans, t);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
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
    int maxArea(vector<int>& height) {
        int i = 0, j = height.size() - 1;
        int ans = 0;
        while (i < j) {
            int t = min(height[i], height[j]) * (j - i);
            ans = max(ans, t);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxArea(height []int) (ans int) {
	i, j := 0, len(height)-1
	for i < j {
		t := min(height[i], height[j]) * (j - i)
		ans = max(ans, t)
		if height[i] < height[j] {
			i++
		} else {
			j--
		}
	}
	return
}
```

#### TypeScript

```ts
function maxArea(height: number[]): number {
    let i = 0;
    let j = height.length - 1;
    let ans = 0;
    while (i < j) {
        const t = Math.min(height[i], height[j]) * (j - i);
        ans = Math.max(ans, t);
        if (height[i] < height[j]) {
            ++i;
        } else {
            --j;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut i = 0;
        let mut j = height.len() - 1;
        let mut ans = 0;
        while i < j {
            ans = ans.max(height[i].min(height[j]) * ((j - i) as i32));
            if height[i] <= height[j] {
                i += 1;
            } else {
                j -= 1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function (height) {
    let i = 0;
    let j = height.length - 1;
    let ans = 0;
    while (i < j) {
        const t = Math.min(height[i], height[j]) * (j - i);
        ans = Math.max(ans, t);
        if (height[i] < height[j]) {
            ++i;
        } else {
            --j;
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxArea(int[] height) {
        int i = 0, j = height.Length - 1;
        int ans = 0;
        while (i < j) {
            int t = Math.Min(height[i], height[j]) * (j - i);
            ans = Math.Max(ans, t);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $height
     * @return Integer
     */
    function maxArea($height) {
        $i = 0;
        $j = count($height) - 1;
        $ans = 0;
        while ($i < $j) {
            $t = min($height[$i], $height[$j]) * ($j - $i);
            $ans = max($ans, $t);
            if ($height[$i] < $height[$j]) {
                ++$i;
            } else {
                --$j;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
