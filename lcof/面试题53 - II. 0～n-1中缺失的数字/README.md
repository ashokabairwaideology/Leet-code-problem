---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9853%20-%20II.%200%EF%BD%9En-1%E4%B8%AD%E7%BC%BA%E5%A4%B1%E7%9A%84%E6%95%B0%E5%AD%97/README.md
---

<!-- problem:start -->

# [面试题 53 - II. 0 ～ n-1 中缺失的数字](https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/)

## 题目描述

<!-- description:start -->

<p>一个长度为 n-1 的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [0,1,3]
<strong>输出:</strong> 2
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> [0,1,2,3,4,5,6,7,9]
<strong>输出:</strong> 8</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>1 &lt;= 数组长度 &lt;= 10000</code></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们可以使用二分查找的方法找到这个缺失的数字。初始化左边界 $l=0$，右边界 $r=n$，其中 $n$ 是数组的长度。

每次计算中间元素的下标 $mid$，如果 $nums[mid] \gt mid$，则缺失的数字一定在区间 $[l,..mid]$ 中，否则缺失的数字一定在区间 $[mid+1,..r]$ 中。

最后返回左边界 $l$ 即可。

时间复杂度 $O(\log n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        l, r = 0, len(nums)
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] > mid:
                r = mid
            else:
                l = mid + 1
        return l
```

#### Java

```java
class Solution {
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func missingNumber(nums []int) int {
	l, r := 0, len(nums)
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] > mid {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let (mut l, mut r) = (0, nums.len() as i32);
        while l < r {
            let mut mid = (l + r) >> 1;
            if nums[mid as usize] > mid {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    let l = 0;
    let r = nums.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > mid) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
};
```

#### C#

```cs
public class Solution {
    public int MissingNumber(int[] nums) {
        int l = 0, r = nums.Length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### Swift

```swift
class Solution {
    func missingNumber(_ nums: [Int]) -> Int {
        var left = 0
        var right = nums.count

        while left < right {
            let mid = (left + right) / 2
            if nums[mid] > mid {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
