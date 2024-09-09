---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2344.Minimum%20Deletions%20to%20Make%20Array%20Divisible/README.md
rating: 1640
source: 第 302 场周赛 Q4
tags:
    - 数组
    - 数学
    - 数论
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2344. 使数组可以被整除的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-array-divisible)

[English Version](/solution/2300-2399/2344.Minimum%20Deletions%20to%20Make%20Array%20Divisible/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数数组&nbsp;<code>nums</code> 和&nbsp;<code>numsDivide</code>&nbsp;。你可以从&nbsp;<code>nums</code>&nbsp;中删除任意数目的元素。</p>

<p>请你返回使 <code>nums</code>&nbsp;中 <strong>最小</strong>&nbsp;元素可以整除 <code>numsDivide</code>&nbsp;中所有元素的 <strong>最少</strong>&nbsp;删除次数。如果无法得到这样的元素，返回 <code>-1</code>&nbsp;。</p>

<p>如果&nbsp;<code>y % x == 0</code>&nbsp;，那么我们说整数&nbsp;<code>x</code>&nbsp;整除&nbsp;<code>y</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,3,2,4,3], numsDivide = [9,6,9,3,15]
<b>输出：</b>2
<b>解释：</b>
[2,3,2,4,3] 中最小元素是 2 ，它无法整除 numsDivide 中所有元素。
我们从 nums 中删除 2 个大小为 2 的元素，得到 nums = [3,4,3] 。
[3,4,3] 中最小元素为 3 ，它可以整除 numsDivide 中所有元素。
可以证明 2 是最少删除次数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [4,3,6], numsDivide = [8,2,6,10]
<b>输出：</b>-1
<b>解释：</b>
我们想 nums 中的最小元素可以整除 numsDivide 中的所有元素。
没有任何办法可以达到这一目的。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, numsDivide.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], numsDivide[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学 + 排序

如果一个元素能整除数组 `numsDivide` 所有元素，那么这个元素是所有 $numsDivide[i]$ 的最大公约数 $x$ 的因子。因此，我们可以先求出 `numsDivide` 的最大公约数 $x$。

接下来，将数组 `nums` 排序，然后从头到尾遍历数组 `nums`，找到第一个是最大公约数 $x$ 的因子的元素，返回当前元素下标即可。

时间复杂度 $O(m + \log M + n \times \log n)$，其中 $n$ 和 $m$ 分别是数组 `nums` 和 `numsDivide` 的长度，而 $M$ 是数组 `numsDivide` 中的最大值。

实际上，我们也可以不用排序数组 `nums`，而是直接遍历数组 `nums`，找到最小的能整除 $x$ 的元素，然后我们再遍历一次数组 `nums`，统计小于等于这个元素的元素个数即可。

时间复杂度 $O(m + \log M + n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], numsDivide: List[int]) -> int:
        x = numsDivide[0]
        for v in numsDivide[1:]:
            x = gcd(x, v)
        nums.sort()
        for i, v in enumerate(nums):
            if x % v == 0:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int x = 0;
        for (int v : numsDivide) {
            x = gcd(x, v);
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (x % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, vector<int>& numsDivide) {
        int x = 0;
        for (int& v : numsDivide) {
            x = gcd(x, v);
        }
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size(); ++i) {
            if (x % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func minOperations(nums []int, numsDivide []int) int {
	x := 0
	for _, v := range numsDivide {
		x = gcd(x, v)
	}
	sort.Ints(nums)
	for i, v := range nums {
		if x%v == 0 {
			return i
		}
	}
	return -1
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
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
    def minOperations(self, nums: List[int], numsDivide: List[int]) -> int:
        x = gcd(*numsDivide)
        nums.sort()
        return next((i for i, v in enumerate(nums) if x % v == 0), -1)
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int x = 0;
        for (int v : numsDivide) {
            x = gcd(x, v);
        }
        int y = 1 << 30;
        for (int v : nums) {
            if (x % v == 0) {
                y = Math.min(y, v);
            }
        }
        if (y == 1 << 30) {
            return -1;
        }
        int ans = 0;
        for (int v : nums) {
            if (v < y) {
                ++ans;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, vector<int>& numsDivide) {
        int x = 0;
        for (int& v : numsDivide) {
            x = gcd(x, v);
        }
        int y = 1 << 30;
        for (int& v : nums) {
            if (x % v == 0) {
                y = min(y, v);
            }
        }
        if (y == 1 << 30) {
            return -1;
        }
        int ans = 0;
        for (int& v : nums) {
            ans += v < y;
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int, numsDivide []int) int {
	x := 0
	for _, v := range numsDivide {
		x = gcd(x, v)
	}
	y := 1 << 30
	for _, v := range nums {
		if x%v == 0 {
			y = min(y, v)
		}
	}
	if y == 1<<30 {
		return -1
	}
	ans := 0
	for _, v := range nums {
		if v < y {
			ans++
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], numsDivide: List[int]) -> int:
        x = gcd(*numsDivide)
        y = min((v for v in nums if x % v == 0), default=0)
        return sum(v < y for v in nums) if y else -1
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
