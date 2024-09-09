---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2535.Difference%20Between%20Element%20Sum%20and%20Digit%20Sum%20of%20an%20Array/README.md
rating: 1222
source: 第 328 场周赛 Q1
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [2535. 数组元素和与数字和的绝对差](https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array)

[English Version](/solution/2500-2599/2535.Difference%20Between%20Element%20Sum%20and%20Digit%20Sum%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组 <code>nums</code> 。</p>

<ul>
	<li><strong>元素和</strong> 是 <code>nums</code> 中的所有元素相加求和。</li>
	<li><strong>数字和</strong> 是&nbsp;<code>nums</code> 中每一个元素的每一数位（重复数位需多次求和）相加求和。</li>
</ul>

<p>返回 <strong>元素和</strong> 与 <strong>数字和</strong> 的绝对差。</p>

<p><strong>注意：</strong>两个整数 <code>x</code> 和 <code>y</code> 的绝对差定义为 <code>|x - y|</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,15,6,3]
<strong>输出：</strong>9
<strong>解释：</strong>
nums 的元素和是 1 + 15 + 6 + 3 = 25 。
nums 的数字和是 1 + 1 + 5 + 6 + 3 = 16 。
元素和与数字和的绝对差是 |25 - 16| = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>0
<strong>解释：</strong>
nums 的元素和是 1 + 2 + 3 + 4 = 10 。
nums 的数字和是 1 + 2 + 3 + 4 = 10 。
元素和与数字和的绝对差是 |10 - 10| = 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历数组 $nums$，计算元素和 $a$ 与数字和 $b$，最后返回 $|a - b|$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def differenceOfSum(self, nums: List[int]) -> int:
        a, b = sum(nums), 0
        for x in nums:
            while x:
                b += x % 10
                x //= 10
        return abs(a - b)
```

#### Java

```java
class Solution {
    public int differenceOfSum(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            a += x;
            for (; x > 0; x /= 10) {
                b += x % 10;
            }
        }
        return Math.abs(a - b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int differenceOfSum(vector<int>& nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            a += x;
            for (; x; x /= 10) {
                b += x % 10;
            }
        }
        return abs(a - b);
    }
};
```

#### Go

```go
func differenceOfSum(nums []int) int {
	a, b := 0, 0
	for _, x := range nums {
		a += x
		for ; x > 0; x /= 10 {
			b += x % 10
		}
	}
	return abs(a - b)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function differenceOfSum(nums: number[]): number {
    return nums.reduce((r, v) => {
        r += v;
        while (v !== 0) {
            r -= v % 10;
            v = Math.floor(v / 10);
        }
        return r;
    }, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn difference_of_sum(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for &num in nums.iter() {
            let mut num = num;
            ans += num;
            while num != 0 {
                ans -= num % 10;
                num /= 10;
            }
        }
        ans
    }
}
```

#### C

```c
int differenceOfSum(int* nums, int numsSize) {
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        ans += nums[i];
        while (nums[i]) {
            ans -= nums[i] % 10;
            nums[i] /= 10;
        }
    }
    return ans;
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
    pub fn difference_of_sum(nums: Vec<i32>) -> i32 {
        let a: i32 = nums.iter().sum();
        let b: i32 = nums
            .iter()
            .map(|&n| {
                n.to_string()
                    .chars()
                    .map(|c| c.to_digit(10).unwrap() as i32)
                    .sum::<i32>()
            })
            .sum();
        (a - b).abs()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
