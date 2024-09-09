---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2455.Average%20Value%20of%20Even%20Numbers%20That%20Are%20Divisible%20by%20Three/README.md
rating: 1151
source: 第 317 场周赛 Q1
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [2455. 可被三整除的偶数的平均值](https://leetcode.cn/problems/average-value-of-even-numbers-that-are-divisible-by-three)

[English Version](/solution/2400-2499/2455.Average%20Value%20of%20Even%20Numbers%20That%20Are%20Divisible%20by%20Three/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由正整数组成的整数数组 <code>nums</code> ，返回其中可被 <code>3</code> 整除的所有偶数的平均值。</p>

<p>注意：<code>n</code> 个元素的平均值等于 <code>n</code> 个元素 <strong>求和</strong> 再除以 <code>n</code> ，结果 <strong>向下取整</strong> 到最接近的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,6,10,12,15]
<strong>输出：</strong>9
<strong>解释：</strong>6 和 12 是可以被 3 整除的偶数。(6 + 12) / 2 = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,7,10]
<strong>输出：</strong>0
<strong>解释：</strong>不存在满足题目要求的整数，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们注意到，可被 $3$ 整除的偶数一定是 $6$ 的倍数，因此我们只需要遍历数组，统计所有 $6$ 的倍数的和与个数，然后计算平均值即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def averageValue(self, nums: List[int]) -> int:
        s = n = 0
        for x in nums:
            if x % 6 == 0:
                s += x
                n += 1
        return 0 if n == 0 else s // n
```

#### Java

```java
class Solution {
    public int averageValue(int[] nums) {
        int s = 0, n = 0;
        for (int x : nums) {
            if (x % 6 == 0) {
                s += x;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int averageValue(vector<int>& nums) {
        int s = 0, n = 0;
        for (int x : nums) {
            if (x % 6 == 0) {
                s += x;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
};
```

#### Go

```go
func averageValue(nums []int) int {
	var s, n int
	for _, x := range nums {
		if x%6 == 0 {
			s += x
			n++
		}
	}
	if n == 0 {
		return 0
	}
	return s / n
}
```

#### TypeScript

```ts
function averageValue(nums: number[]): number {
    let s = 0;
    let n = 0;
    for (const x of nums) {
        if (x % 6 === 0) {
            s += x;
            ++n;
        }
    }
    return n === 0 ? 0 : ~~(s / n);
}
```

#### Rust

```rust
impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let mut s = 0;
        let mut n = 0;
        for x in nums.iter() {
            if x % 6 == 0 {
                s += x;
                n += 1;
            }
        }
        if n == 0 {
            return 0;
        }
        s / n
    }
}
```

#### C

```c
int averageValue(int* nums, int numsSize) {
    int s = 0, n = 0;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] % 6 == 0) {
            s += nums[i];
            ++n;
        }
    }
    return n == 0 ? 0 : s / n;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Rust

```rust
impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let filtered_nums: Vec<i32> = nums.iter().cloned().filter(|&n| n % 6 == 0).collect();

        if filtered_nums.is_empty() {
            return 0;
        }

        filtered_nums.iter().sum::<i32>() / (filtered_nums.len() as i32)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
