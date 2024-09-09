---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1509.Minimum%20Difference%20Between%20Largest%20and%20Smallest%20Value%20in%20Three%20Moves/README.md
rating: 1653
source: 第 30 场双周赛 Q3
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [1509. 三次操作后最大值与最小值的最小差](https://leetcode.cn/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves)

[English Version](/solution/1500-1599/1509.Minimum%20Difference%20Between%20Largest%20and%20Smallest%20Value%20in%20Three%20Moves/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>每次操作你可以选择&nbsp;<code>nums</code>&nbsp;中的任意一个元素并将它改成 <strong>任意值</strong> 。</p>

<p>在&nbsp;<strong>执行最多三次移动后&nbsp;</strong>，返回&nbsp;<code>nums</code>&nbsp;中最大值与最小值的最小差值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,3,2,4]
<strong>输出：</strong>0
<strong>解释：</strong>我们最多可以走 3 步。
第一步，将 2 变为 3 。 nums 变成 [5,3,3,4] 。
第二步，将 4 改为 3 。 nums 变成 [5,3,3,3] 。
第三步，将 5 改为 3 。 nums 变成 [3,3,3,3] 。
执行 3 次移动后，最小值和最大值之间的差值为 3 - 3 = 0 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,0,10,14]
<strong>输出：</strong>1
<strong>解释：</strong>我们最多可以走 3 步。
第一步，将 5 改为 0 。 nums变成 [1,0,0,10,14] 。
第二步，将 10 改为 0 。 nums变成 [1,0,0,0,14] 。
第三步，将 14 改为 1 。 nums变成 [1,0,0,0,1] 。
执行 3 步后，最小值和最大值之间的差值为 1 - 0 = 1 。
可以看出，没有办法可以在 3 步内使差值变为0。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,100,20]
<strong>输出：</strong>0
<strong>解释：</strong>我们最多可以走 3 步。
第一步，将 100 改为 7 。 nums 变成 [3,7,20] 。
第二步，将 20 改为 7 。 nums 变成 [3,7,7] 。
第三步，将 3 改为 7 。 nums 变成 [7,7,7] 。
执行 3 步后，最小值和最大值之间的差值是 7 - 7 = 0。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心

我们可以先判断数组长度是否小于 $5$，如果小于 $5$，那么直接返回 $0$。

否则，我们将数组排序，然后贪心地选择数组左边最小的 $l=[0,..3]$ 个数和右边最小的 $r = 3 - l$ 个数，取其中最小的差值 $nums[n - 1 - r] - nums[l]$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

相似题目：

-   [2567. 修改两个元素的最小分数](https://github.com/doocs/leetcode/blob/main/solution/2500-2599/2567.Minimum%20Score%20by%20Changing%20Two%20Elements/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDifference(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 5:
            return 0
        nums.sort()
        ans = inf
        for l in range(4):
            r = 3 - l
            ans = min(ans, nums[n - 1 - r] - nums[l])
        return ans
```

#### Java

```java
class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n < 5) {
            return 0;
        }
        Arrays.sort(nums);
        long ans = 1L << 60;
        for (int l = 0; l <= 3; ++l) {
            int r = 3 - l;
            ans = Math.min(ans, (long) nums[n - 1 - r] - nums[l]);
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDifference(vector<int>& nums) {
        int n = nums.size();
        if (n < 5) {
            return 0;
        }
        sort(nums.begin(), nums.end());
        long long ans = 1L << 60;
        for (int l = 0; l <= 3; ++l) {
            int r = 3 - l;
            ans = min(ans, 1LL * nums[n - 1 - r] - nums[l]);
        }
        return ans;
    }
};
```

#### Go

```go
func minDifference(nums []int) int {
	n := len(nums)
	if n < 5 {
		return 0
	}
	sort.Ints(nums)
	ans := 1 << 60
	for l := 0; l <= 3; l++ {
		r := 3 - l
		ans = min(ans, nums[n-1-r]-nums[l])
	}
	return ans
}
```

#### TypeScript

```ts
function minDifference(nums: number[]): number {
    if (nums.length < 5) {
        return 0;
    }
    nums.sort((a, b) => a - b);
    let ans = Number.POSITIVE_INFINITY;
    for (let i = 0; i < 4; i++) {
        ans = Math.min(ans, nums.at(i - 4)! - nums[i]);
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var minDifference = function (nums) {
    if (nums.length < 5) {
        return 0;
    }
    nums.sort((a, b) => a - b);
    let ans = Number.POSITIVE_INFINITY;
    for (let i = 0; i < 4; i++) {
        ans = Math.min(ans, nums.at(i - 4) - nums[i]);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
