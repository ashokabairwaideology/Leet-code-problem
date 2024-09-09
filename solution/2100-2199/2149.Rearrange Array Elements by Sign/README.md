---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2149.Rearrange%20Array%20Elements%20by%20Sign/README.md
rating: 1235
source: 第 277 场周赛 Q2
tags:
    - 数组
    - 双指针
    - 模拟
---

<!-- problem:start -->

# [2149. 按符号重排数组](https://leetcode.cn/problems/rearrange-array-elements-by-sign)

[English Version](/solution/2100-2199/2149.Rearrange%20Array%20Elements%20by%20Sign/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，数组长度为 <strong>偶数</strong> ，由数目 <strong>相等</strong> 的正整数和负整数组成。</p>

<p>你需要返回满足下述条件的数组&nbsp;<code>nums</code>：</p>

<ol>
	<li>任意&nbsp;<strong>连续</strong> 的两个整数 <strong>符号相反</strong></li>
	<li>对于符号相同的所有整数，<strong>保留</strong> 它们在 <code>nums</code> 中的 <strong>顺序</strong> 。</li>
	<li>重排后数组以正整数开头。</li>
</ol>

<p>重排元素满足上述条件后，返回修改后的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,-2,-5,2,-4]
<strong>输出：</strong>[3,-2,1,-5,2,-4]
<strong>解释：</strong>
nums 中的正整数是 [3,1,2] ，负整数是 [-2,-5,-4] 。
重排的唯一可行方案是 [3,-2,1,-5,2,-4]，能满足所有条件。
像 [1,-2,2,-5,3,-4]、[3,1,2,-2,-5,-4]、[-2,3,-5,1,-4,2] 这样的其他方案是不正确的，因为不满足一个或者多个条件。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,1]
<strong>输出：</strong>[1,-1]
<strong>解释：</strong>
1 是 nums 中唯一一个正整数，-1 是 nums 中唯一一个负整数。
所以 nums 重排为 [1,-1] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>nums.length</code> 是 <strong>偶数</strong></li>
	<li><code>1 &lt;= |nums[i]| &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> 由 <strong>相等</strong> 数量的正整数和负整数组成</li>
</ul>

<p>&nbsp;</p>

<p>不需要原地进行修改。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们先创建一个长度为 $n$ 的数组 $\textit{ans}$，然后使用两个指针 $i$ 和 $j$ 分别指向 $\textit{ans}$ 的偶数下标和奇数下标，初始时 $i = 0$, $j = 1$。

遍历数组 $\textit{nums}$，如果当前元素 $x$ 为正整数，则将 $x$ 放入 $\textit{ans}[i]$，并将 $i$ 增加 $2$；否则将 $x$ 放入 $\textit{ans}[j]$，并将 $j$ 增加 $2$。

最后返回 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeArray(self, nums: List[int]) -> List[int]:
        ans = [0] * len(nums)
        i, j = 0, 1
        for x in nums:
            if x > 0:
                ans[i] = x
                i += 2
            else:
                ans[j] = x
                j += 2
        return ans
```

#### Java

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] ans = new int[nums.length];
        int i = 0, j = 1;
        for (int x : nums) {
            if (x > 0) {
                ans[i] = x;
                i += 2;
            } else {
                ans[j] = x;
                j += 2;
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
    vector<int> rearrangeArray(vector<int>& nums) {
        vector<int> ans(nums.size());
        int i = 0, j = 1;
        for (int x : nums) {
            if (x > 0) {
                ans[i] = x;
                i += 2;
            } else {
                ans[j] = x;
                j += 2;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rearrangeArray(nums []int) []int {
	ans := make([]int, len(nums))
	i, j := 0, 1
	for _, x := range nums {
		if x > 0 {
			ans[i] = x
			i += 2
		} else {
			ans[j] = x
			j += 2
		}
	}
	return ans
}
```

#### TypeScript

```ts
function rearrangeArray(nums: number[]): number[] {
    const ans: number[] = Array(nums.length);
    let [i, j] = [0, 1];
    for (const x of nums) {
        if (x > 0) {
            ans[i] = x;
            i += 2;
        } else {
            ans[j] = x;
            j += 2;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
