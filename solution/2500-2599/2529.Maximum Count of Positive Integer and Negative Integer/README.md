---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2529.Maximum%20Count%20of%20Positive%20Integer%20and%20Negative%20Integer/README.md
rating: 1195
source: 第 327 场周赛 Q1
tags:
    - 数组
    - 二分查找
    - 计数
---

<!-- problem:start -->

# [2529. 正整数和负整数的最大计数](https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer)

[English Version](/solution/2500-2599/2529.Maximum%20Count%20of%20Positive%20Integer%20and%20Negative%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个按 <strong>非递减顺序</strong> 排列的数组 <code>nums</code> ，返回正整数数目和负整数数目中的最大值。</p>

<ul>
	<li>换句话讲，如果 <code>nums</code> 中正整数的数目是 <code>pos</code> ，而负整数的数目是 <code>neg</code> ，返回 <code>pos</code> 和 <code>neg</code>二者中的最大值。</li>
</ul>

<p><strong>注意：</strong><code>0</code> 既不是正整数也不是负整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-2,-1,-1,1,2,3]
<strong>输出：</strong>3
<strong>解释：</strong>共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-3,-2,-1,0,0,1,2]
<strong>输出：</strong>3
<strong>解释：</strong>共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,20,66,1314]
<strong>输出：</strong>4
<strong>解释：</strong>共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-2000 &lt;= nums[i] &lt;= 2000</code></li>
	<li><code>nums</code> 按 <strong>非递减顺序</strong> 排列。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计并实现时间复杂度为 <code>O(log(n))</code> 的算法解决此问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们可以直接遍历数组，统计正整数和负整数的个数 $a$ 和 $b$，返回 $a$ 和 $b$ 中的较大值即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumCount(self, nums: List[int]) -> int:
        a = sum(x > 0 for x in nums)
        b = sum(x < 0 for x in nums)
        return max(a, b)
```

#### Java

```java
class Solution {
    public int maximumCount(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x > 0) {
                ++a;
            } else if (x < 0) {
                ++b;
            }
        }
        return Math.max(a, b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x > 0) {
                ++a;
            } else if (x < 0) {
                ++b;
            }
        }
        return max(a, b);
    }
};
```

#### Go

```go
func maximumCount(nums []int) int {
	var a, b int
	for _, x := range nums {
		if x > 0 {
			a++
		} else if x < 0 {
			b++
		}
	}
	return max(a, b)
}
```

#### TypeScript

```ts
function maximumCount(nums: number[]): number {
    let [a, b] = [0, 0];
    for (const x of nums) {
        if (x > 0) {
            ++a;
        } else if (x < 0) {
            ++b;
        }
    }
    return Math.max(a, b);
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_count(nums: Vec<i32>) -> i32 {
        let mut a = 0;
        let mut b = 0;

        for x in nums {
            if x > 0 {
                a += 1;
            } else if x < 0 {
                b += 1;
            }
        }

        std::cmp::max(a, b)
    }
}
```

#### C

```c
#define max(a, b) (a > b ? a : b)

int maximumCount(int* nums, int numsSize) {
    int a = 0, b = 0;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] > 0) {
            ++a;
        } else if (nums[i] < 0) {
            ++b;
        }
    }
    return max(a, b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：二分查找

由于数组是按非递减顺序排列的，因此可以使用二分查找找到第一个大于等于 $1$ 的元素的下标 $i$ 以及第一个大于等于 $0$ 的元素的下标 $j$，那么正整数的个数 $a = n - i$，负整数的个数 $b = j$，返回 $a$ 和 $b$ 中的较大值即可。

时间复杂度 $O(\log n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumCount(self, nums: List[int]) -> int:
        a = len(nums) - bisect_left(nums, 1)
        b = bisect_left(nums, 0)
        return max(a, b)
```

#### Java

```java
class Solution {
    public int maximumCount(int[] nums) {
        int a = nums.length - search(nums, 1);
        int b = search(nums, 0);
        return Math.max(a, b);
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int a = nums.end() - lower_bound(nums.begin(), nums.end(), 1);
        int b = lower_bound(nums.begin(), nums.end(), 0) - nums.begin();
        return max(a, b);
    }
};
```

#### Go

```go
func maximumCount(nums []int) int {
	a := len(nums) - sort.SearchInts(nums, 1)
	b := sort.SearchInts(nums, 0)
	return max(a, b)
}
```

#### TypeScript

```ts
function maximumCount(nums: number[]): number {
    const search = (x: number): number => {
        let [left, right] = [0, nums.length];
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const i = search(1);
    const j = search(0);
    const [a, b] = [nums.length - i, j];
    return Math.max(a, b);
}
```

#### Rust

```rust
impl Solution {
    fn search(nums: &Vec<i32>, x: i32) -> usize {
        let mut left = 0;
        let mut right = nums.len();
        while left < right {
            let mid = (left + right) >> 1;
            if nums[mid] >= x {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        left
    }

    pub fn maximum_count(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let i = Self::search(&nums, 1);
        let j = Self::search(&nums, 0);
        (n - i).max(j) as i32
    }
}
```

#### C

```c
#define max(a, b) (a > b ? a : b)

int search(int* nums, int numsSize, int x) {
    int left = 0;
    int right = numsSize;
    while (left < right) {
        int mid = (left + right) >> 1;
        if (nums[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

int maximumCount(int* nums, int numsSize) {
    int i = search(nums, numsSize, 1);
    int j = search(nums, numsSize, 0);
    return max(numsSize - i, j);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
