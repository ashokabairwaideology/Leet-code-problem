---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0414.Third%20Maximum%20Number/README_EN.md
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [414. Third Maximum Number](https://leetcode.com/problems/third-maximum-number)

[中文文档](/solution/0400-0499/0414.Third%20Maximum%20Number/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code>, return <em>the <strong>third distinct maximum</strong> number in this array. If the third maximum does not exist, return the <strong>maximum</strong> number</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The first distinct maximum is 3.
The second distinct maximum is 2.
The third distinct maximum is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The first distinct maximum is 2.
The second distinct maximum is 1.
The third distinct maximum does not exist, so the maximum (2) is returned instead.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,3,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The first distinct maximum is 3.
The second distinct maximum is 2 (both 2&#39;s are counted together since they have the same value).
The third distinct maximum is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Can you find an <code>O(n)</code> solution?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We can use three variables $m_1$, $m_2$, and $m_3$ to represent the first, second, and third largest numbers in the array respectively. Initially, we set these three variables to negative infinity.

Then, we iterate through each number in the array. For each number:

-   If it equals any of $m_1$, $m_2$, or $m_3$, we skip this number.
-   If it is greater than $m_1$, we update the values of $m_1$, $m_2$, and $m_3$ to $m_2$, $m_3$, and this number respectively.
-   If it is greater than $m_2$, we update the values of $m_2$ and $m_3$ to $m_3$ and this number respectively.
-   If it is greater than $m_3$, we update the value of $m_3$ to this number.

Finally, if the value of $m_3$ has not been updated, it means that there is no third largest number in the array, so we return $m_1$. Otherwise, we return $m_3$.

The time complexity is $O(n)$, where $n$ is the length of the array `nums`. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def thirdMax(self, nums: List[int]) -> int:
        m1 = m2 = m3 = -inf
        for num in nums:
            if num in [m1, m2, m3]:
                continue
            if num > m1:
                m3, m2, m1 = m2, m1, num
            elif num > m2:
                m3, m2 = m2, num
            elif num > m3:
                m3 = num
        return m3 if m3 != -inf else m1
```

#### Java

```java
class Solution {
    public int thirdMax(int[] nums) {
        long m1 = Long.MIN_VALUE;
        long m2 = Long.MIN_VALUE;
        long m3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num == m1 || num == m2 || num == m3) {
                continue;
            }
            if (num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m3 = m2;
                m2 = num;
            } else if (num > m3) {
                m3 = num;
            }
        }
        return (int) (m3 != Long.MIN_VALUE ? m3 : m1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int thirdMax(vector<int>& nums) {
        long m1 = LONG_MIN, m2 = LONG_MIN, m3 = LONG_MIN;
        for (int num : nums) {
            if (num == m1 || num == m2 || num == m3) continue;
            if (num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m3 = m2;
                m2 = num;
            } else if (num > m3) {
                m3 = num;
            }
        }
        return (int) (m3 != LONG_MIN ? m3 : m1);
    }
};
```

#### Go

```go
func thirdMax(nums []int) int {
	m1, m2, m3 := math.MinInt64, math.MinInt64, math.MinInt64
	for _, num := range nums {
		if num == m1 || num == m2 || num == m3 {
			continue
		}
		if num > m1 {
			m3, m2, m1 = m2, m1, num
		} else if num > m2 {
			m3, m2 = m2, num
		} else if num > m3 {
			m3 = num
		}
	}
	if m3 != math.MinInt64 {
		return m3
	}
	return m1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
