---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1470.Shuffle%20the%20Array/README.md
rating: 1120
source: 第 192 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [1470. 重新排列数组](https://leetcode.cn/problems/shuffle-the-array)

[English Version](/solution/1400-1499/1470.Shuffle%20the%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>nums</code> ，数组中有 <code>2n</code> 个元素，按 <code>[x<sub>1</sub>,x<sub>2</sub>,...,x<sub>n</sub>,y<sub>1</sub>,y<sub>2</sub>,...,y<sub>n</sub>]</code> 的格式排列。</p>

<p>请你将数组按 <code>[x<sub>1</sub>,y<sub>1</sub>,x<sub>2</sub>,y<sub>2</sub>,...,x<sub>n</sub>,y<sub>n</sub>]</code> 格式重新排列，返回重排后的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,5,1,3,4,7], n = 3
<strong>输出：</strong>[2,3,5,4,1,7] 
<strong>解释：</strong>由于 x<sub>1</sub>=2, x<sub>2</sub>=5, x<sub>3</sub>=1, y<sub>1</sub>=3, y<sub>2</sub>=4, y<sub>3</sub>=7 ，所以答案为 [2,3,5,4,1,7]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,4,3,2,1], n = 4
<strong>输出：</strong>[1,4,2,3,3,2,4,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,2,2], n = 2
<strong>输出：</strong>[1,2,1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>nums.length == 2n</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^3</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shuffle(self, nums: List[int], n: int) -> List[int]:
        ans = []
        for i in range(n):
            ans.append(nums[i])
            ans.append(nums[i + n])
        return ans
```

#### Java

```java
class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[n << 1];
        for (int i = 0, j = 0; i < n; ++i) {
            ans[j++] = nums[i];
            ans[j++] = nums[i + n];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> shuffle(vector<int>& nums, int n) {
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            ans.push_back(nums[i]);
            ans.push_back(nums[i + n]);
        }
        return ans;
    }
};
```

#### Go

```go
func shuffle(nums []int, n int) []int {
	var ans []int
	for i := 0; i < n; i++ {
		ans = append(ans, nums[i])
		ans = append(ans, nums[i+n])
	}
	return ans
}
```

#### TypeScript

```ts
function shuffle(nums: number[], n: number): number[] {
    let ans = [];
    for (let i = 0; i < n; i++) {
        ans.push(nums[i], nums[n + i]);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn shuffle(nums: Vec<i32>, n: i32) -> Vec<i32> {
        let n = n as usize;
        let mut res = Vec::new();
        for i in 0..n {
            res.push(nums[i]);
            res.push(nums[n + i]);
        }
        res
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* shuffle(int* nums, int numsSize, int n, int* returnSize) {
    int* res = (int*) malloc(sizeof(int) * n * 2);
    for (int i = 0; i < n; i++) {
        res[2 * i] = nums[i];
        res[2 * i + 1] = nums[i + n];
    }
    *returnSize = n * 2;
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shuffle(self, nums: List[int], n: int) -> List[int]:
        nums[::2], nums[1::2] = nums[:n], nums[n:]
        return nums
```

#### Rust

```rust
impl Solution {
    pub fn shuffle(mut nums: Vec<i32>, n: i32) -> Vec<i32> {
        let n = n as usize;
        for i in 0..n * 2 {
            let mut j = i;
            while nums[i] > 0 {
                j = if j < n { 2 * j } else { 2 * (j - n) + 1 };
                nums.swap(i, j);
                nums[j] *= -1;
            }
        }
        for i in 0..n * 2 {
            nums[i] *= -1;
        }
        nums
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
