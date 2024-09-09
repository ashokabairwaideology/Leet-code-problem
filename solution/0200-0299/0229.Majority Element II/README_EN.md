---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0229.Majority%20Element%20II/README_EN.md
tags:
    - Array
    - Hash Table
    - Counting
    - Sorting
---

<!-- problem:start -->

# [229. Majority Element II](https://leetcode.com/problems/majority-element-ii)

[中文文档](/solution/0200-0299/0229.Majority%20Element%20II/README.md)

## Description

<!-- description:start -->

<p>Given an integer array of size <code>n</code>, find all elements that appear more than <code>&lfloor; n/3 &rfloor;</code> times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,3]
<strong>Output:</strong> [3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> [1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2]
<strong>Output:</strong> [1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve the problem in linear time and in <code>O(1)</code> space?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> List[int]:
        n1 = n2 = 0
        m1, m2 = 0, 1
        for m in nums:
            if m == m1:
                n1 += 1
            elif m == m2:
                n2 += 1
            elif n1 == 0:
                m1, n1 = m, 1
            elif n2 == 0:
                m2, n2 = m, 1
            else:
                n1, n2 = n1 - 1, n2 - 1
        return [m for m in [m1, m2] if nums.count(m) > len(nums) // 3]
```

#### Java

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n1 = 0, n2 = 0;
        int m1 = 0, m2 = 1;
        for (int m : nums) {
            if (m == m1) {
                ++n1;
            } else if (m == m2) {
                ++n2;
            } else if (n1 == 0) {
                m1 = m;
                ++n1;
            } else if (n2 == 0) {
                m2 = m;
                ++n2;
            } else {
                --n1;
                --n2;
            }
        }
        List<Integer> ans = new ArrayList<>();
        n1 = 0;
        n2 = 0;
        for (int m : nums) {
            if (m == m1) {
                ++n1;
            } else if (m == m2) {
                ++n2;
            }
        }
        if (n1 > nums.length / 3) {
            ans.add(m1);
        }
        if (n2 > nums.length / 3) {
            ans.add(m2);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> majorityElement(vector<int>& nums) {
        int n1 = 0, n2 = 0;
        int m1 = 0, m2 = 1;
        for (int m : nums) {
            if (m == m1)
                ++n1;
            else if (m == m2)
                ++n2;
            else if (n1 == 0) {
                m1 = m;
                ++n1;
            } else if (n2 == 0) {
                m2 = m;
                ++n2;
            } else {
                --n1;
                --n2;
            }
        }
        vector<int> ans;
        if (count(nums.begin(), nums.end(), m1) > nums.size() / 3) ans.push_back(m1);
        if (count(nums.begin(), nums.end(), m2) > nums.size() / 3) ans.push_back(m2);
        return ans;
    }
};
```

#### Go

```go
func majorityElement(nums []int) []int {
	var n1, n2 int
	m1, m2 := 0, 1
	for _, m := range nums {
		if m == m1 {
			n1++
		} else if m == m2 {
			n2++
		} else if n1 == 0 {
			m1, n1 = m, 1
		} else if n2 == 0 {
			m2, n2 = m, 1
		} else {
			n1, n2 = n1-1, n2-1
		}
	}
	n1, n2 = 0, 0
	for _, m := range nums {
		if m == m1 {
			n1++
		} else if m == m2 {
			n2++
		}
	}
	var ans []int
	if n1 > len(nums)/3 {
		ans = append(ans, m1)
	}
	if n2 > len(nums)/3 {
		ans = append(ans, m2)
	}
	return ans
}
```

#### C#

```cs
public class Solution {
    public IList<int> MajorityElement(int[] nums) {
        int n1 = 0, n2 = 0;
        int m1 = 0, m2 = 1;
        foreach (int m in nums)
        {
            if (m == m1)
            {
                ++n1;
            }
            else if (m == m2)
            {
                ++n2;
            }
            else if (n1 == 0)
            {
                m1 = m;
                ++n1;
            }
            else if (n2 == 0)
            {
                m2 = m;
                ++n2;
            }
            else
            {
                --n1;
                --n2;
            }
        }
        var ans = new List<int>();
        ans.Add(m1);
        ans.Add(m2);
        return ans.Where(m => nums.Count(n => n == m) > nums.Length / 3).ToList();
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function majorityElement($nums) {
        $rs = [];
        $n = count($nums);
        for ($i = 0; $i < $n; $i++) {
            $hashmap[$nums[$i]] += 1;
            if ($hashmap[$nums[$i]] > $n / 3) {
                array_push($rs, $nums[$i]);
            }
        }
        return array_unique($rs);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
