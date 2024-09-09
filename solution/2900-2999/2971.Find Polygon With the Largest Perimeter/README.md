---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2971.Find%20Polygon%20With%20the%20Largest%20Perimeter/README.md
rating: 1521
source: 第 120 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 前缀和
    - 排序
---

<!-- problem:start -->

# [2971. 找到最大周长的多边形](https://leetcode.cn/problems/find-polygon-with-the-largest-perimeter)

[English Version](/solution/2900-2999/2971.Find%20Polygon%20With%20the%20Largest%20Perimeter/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的&nbsp;<strong>正</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p><strong>多边形</strong>&nbsp;指的是一个至少有 <code>3</code>&nbsp;条边的封闭二维图形。多边形的 <strong>最长边</strong>&nbsp;一定 <strong>小于</strong>&nbsp;所有其他边长度之和。</p>

<p>如果你有&nbsp;<code>k</code>&nbsp;（<code>k &gt;= 3</code>）个&nbsp;<strong>正</strong>&nbsp;数&nbsp;<code>a<sub>1</sub></code>，<code>a<sub>2</sub></code>，<code>a<sub>3</sub></code>, ...，<code>a<sub>k</sub></code> 满足&nbsp;<code>a<sub>1</sub> &lt;= a<sub>2</sub> &lt;= a<sub>3</sub> &lt;= ... &lt;= a<sub>k</sub></code> <strong>且</strong> <code>a<sub>1</sub> + a<sub>2</sub> + a<sub>3</sub> + ... + a<sub>k-1</sub> &gt; a<sub>k</sub></code><sub>&nbsp;</sub>，那么 <strong>一定</strong>&nbsp;存在一个&nbsp;<code>k</code>&nbsp;条边的多边形，每条边的长度分别为&nbsp;<code>a<sub>1</sub></code>&nbsp;，<code>a<sub>2</sub></code>&nbsp;，<code>a<sub>3</sub></code>&nbsp;，&nbsp;...，<code>a<sub>k</sub></code>&nbsp;。</p>

<p>一个多边形的 <strong>周长</strong>&nbsp;指的是它所有边之和。</p>

<p>请你返回从 <code>nums</code>&nbsp;中可以构造的 <strong>多边形&nbsp;</strong>的 <strong>最大周长</strong>&nbsp;。如果不能构造出任何多边形，请你返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,5]
<b>输出：</b>15
<b>解释：</b>nums 中唯一可以构造的多边形为三角形，每条边的长度分别为 5 ，5 和 5 ，周长为 5 + 5 + 5 = 15 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,12,1,2,5,50,3]
<b>输出：</b>12
<b>解释：</b>最大周长多边形为五边形，每条边的长度分别为 1 ，1 ，2 ，3 和 5 ，周长为 1 + 1 + 2 + 3 + 5 = 12 。
我们无法构造一个包含变长为 12 或者 50 的多边形，因为其他边之和没法大于两者中的任何一个。
所以最大周长为 12 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,50]
<b>输出：</b>-1
<b>解释：</b>无法构造任何多边形，因为多边形至少要有 3 条边且 50 &gt; 5 + 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 前缀和

我们可以将数组 $nums$ 排序，然后定义一个答案变量 $ans$，初始值为 $-1$。

接下来，我们在 $[3, n]$ 的范围内枚举最长边 $a_k$，如果 $a_1 + a_2 + \cdots + a_{k-1} > a_k$，那么就可以构成一个周长为 $a_1 + a_2 + \cdots + a_k$ 的多边形。这里，我们可以使用前缀和数组 $s$，其中 $s_i = a_1 + a_2 + \cdots + a_i$，那么 $a_1 + a_2 + \cdots + a_{k-1} = s_{k-1}$，判断是否 $s_{k-1} > a_k$，如果是，那么更新答案 $ans = \max(ans, s_k)$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestPerimeter(self, nums: List[int]) -> int:
        nums.sort()
        s = list(accumulate(nums, initial=0))
        ans = -1
        for k in range(3, len(nums) + 1):
            if s[k - 1] > nums[k - 1]:
                ans = max(ans, s[k])
        return ans
```

#### Java

```java
class Solution {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        long ans = -1;
        for (int k = 3; k <= n; ++k) {
            if (s[k - 1] > nums[k - 1]) {
                ans = Math.max(ans, s[k]);
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
    long long largestPerimeter(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        long long ans = -1;
        for (int k = 3; k <= n; ++k) {
            if (s[k - 1] > nums[k - 1]) {
                ans = max(ans, s[k]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func largestPerimeter(nums []int) int64 {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	ans := -1
	for k := 3; k <= n; k++ {
		if s[k-1] > nums[k-1] {
			ans = max(ans, s[k])
		}
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function largestPerimeter(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = -1;
    for (let k = 3; k <= n; ++k) {
        if (s[k - 1] > nums[k - 1]) {
            ans = Math.max(ans, s[k]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
