---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2206.Divide%20Array%20Into%20Equal%20Pairs/README.md
rating: 1223
source: 第 74 场双周赛 Q1
tags:
    - 位运算
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [2206. 将数组划分成相等数对](https://leetcode.cn/problems/divide-array-into-equal-pairs)

[English Version](/solution/2200-2299/2206.Divide%20Array%20Into%20Equal%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，它包含&nbsp;<code>2 * n</code>&nbsp;个整数。</p>

<p>你需要将&nbsp;<code>nums</code> 划分成&nbsp;<code>n</code>&nbsp;个数对，满足：</p>

<ul>
	<li>每个元素 <strong>只属于一个 </strong>数对。</li>
	<li>同一数对中的元素 <strong>相等</strong>&nbsp;。</li>
</ul>

<p>如果可以将 <code>nums</code>&nbsp;划分成 <code>n</code>&nbsp;个数对，请你返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,3,2,2,2]
<b>输出：</b>true
<b>解释：</b>
nums<code>&nbsp;中总共有 6 个元素，所以它们应该被划分成</code> 6 / 2 = 3 个数对。
nums 可以划分成 (2, 2) ，(3, 3) 和 (2, 2) ，满足所有要求。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4]
<b>输出：</b>false
<b>解释：</b>
无法将 nums 划分成 4 / 2 = 2 个数对且满足所有要求。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == 2 * n</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 500</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def divideArray(self, nums: List[int]) -> bool:
        cnt = Counter(nums)
        return all(v % 2 == 0 for v in cnt.values())
```

#### Java

```java
class Solution {
    public boolean divideArray(int[] nums) {
        int[] cnt = new int[510];
        for (int v : nums) {
            ++cnt[v];
        }
        for (int v : cnt) {
            if (v % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool divideArray(vector<int>& nums) {
        vector<int> cnt(510);
        for (int& v : nums) ++cnt[v];
        for (int& v : cnt)
            if (v % 2)
                return false;
        return true;
    }
};
```

#### Go

```go
func divideArray(nums []int) bool {
	cnt := make([]int, 510)
	for _, v := range nums {
		cnt[v]++
	}
	for _, v := range cnt {
		if v%2 == 1 {
			return false
		}
	}
	return true
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
