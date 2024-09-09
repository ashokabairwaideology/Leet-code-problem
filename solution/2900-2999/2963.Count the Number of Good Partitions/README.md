---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2963.Count%20the%20Number%20of%20Good%20Partitions/README.md
rating: 1984
source: 第 375 场周赛 Q4
tags:
    - 数组
    - 哈希表
    - 数学
    - 组合数学
---

<!-- problem:start -->

# [2963. 统计好分割方案的数目](https://leetcode.cn/problems/count-the-number-of-good-partitions)

[English Version](/solution/2900-2999/2963.Count%20the%20Number%20of%20Good%20Partitions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始、由 <strong>正整数</strong> 组成的数组 <code>nums</code>。</p>

<p>将数组分割成一个或多个<strong> 连续</strong> 子数组，如果不存在包含了相同数字的两个子数组，则认为是一种 <strong>好分割方案</strong> 。</p>

<p>返回 <code>nums</code> 的 <strong>好分割方案</strong> 的 <strong>数目</strong>。</p>

<p>由于答案可能很大，请返回答案对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>8
<strong>解释：</strong>有 8 种 <strong>好分割方案 </strong>：([1], [2], [3], [4]), ([1], [2], [3,4]), ([1], [2,3], [4]), ([1], [2,3,4]), ([1,2], [3], [4]), ([1,2], [3,4]), ([1,2,3], [4]) 和 ([1,2,3,4]) 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1]
<strong>输出：</strong>1
<strong>解释：</strong>唯一的 <strong>好分割方案</strong> 是：([1,1,1,1]) 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,3]
<strong>输出：</strong>2
<strong>解释：</strong>有 2 种<strong> 好分割方案 </strong>：([1,2,1], [3]) 和 ([1,2,1,3]) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 分组 + 快速幂

根据题目描述，我们可以知道，相同的数字必须在同一个子数组中。因此，我们用一个哈希表 $last$ 记录每个数字最后一次出现的下标。

接下来，我们用一个下标 $j$ 标识已经出现过的元素中最后一个元素的下标，用一个变量 $k$ 记录当前可以划分的子数组的个数。

然后，我们从左到右遍历数组 $nums$，对于当前遍历到的数字 $nums[i]$，我们获取其最后一次出现的下标，更新 $j = \max(j, last[nums[i]])$。如果 $i = j$，那么说明当前位置可以是一个子数组的结尾，我们将 $k$ 增加 $1$。继续遍历，直到遍历完整个数组。

最后，我们考虑 $k$ 个子数组的划分方案数。子数组数量为 $k$，有 $k-1$ 个位置可以划分（拼接），因此方案数为 $2^{k-1}$。由于答案可能很大，我们需要对 $10^9 + 7$ 取模。这里我们可以使用快速幂来加速运算。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfGoodPartitions(self, nums: List[int]) -> int:
        last = {x: i for i, x in enumerate(nums)}
        mod = 10**9 + 7
        j, k = -1, 0
        for i, x in enumerate(nums):
            j = max(j, last[x])
            k += i == j
        return pow(2, k - 1, mod)
```

#### Java

```java
class Solution {
    public int numberOfGoodPartitions(int[] nums) {
        Map<Integer, Integer> last = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            last.put(nums[i], i);
        }
        final int mod = (int) 1e9 + 7;
        int j = -1;
        int k = 0;
        for (int i = 0; i < n; ++i) {
            j = Math.max(j, last.get(nums[i]));
            k += i == j ? 1 : 0;
        }
        return qpow(2, k - 1, mod);
    }

    private int qpow(long a, int n, int mod) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfGoodPartitions(vector<int>& nums) {
        unordered_map<int, int> last;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            last[nums[i]] = i;
        }
        const int mod = 1e9 + 7;
        int j = -1, k = 0;
        for (int i = 0; i < n; ++i) {
            j = max(j, last[nums[i]]);
            k += i == j;
        }
        auto qpow = [&](long long a, int n, int mod) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return (int) ans;
        };
        return qpow(2, k - 1, mod);
    }
};
```

#### Go

```go
func numberOfGoodPartitions(nums []int) int {
	qpow := func(a, n, mod int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	last := map[int]int{}
	for i, x := range nums {
		last[x] = i
	}
	const mod int = 1e9 + 7
	j, k := -1, 0
	for i, x := range nums {
		j = max(j, last[x])
		if i == j {
			k++
		}
	}
	return qpow(2, k-1, mod)
}
```

#### TypeScript

```ts
function numberOfGoodPartitions(nums: number[]): number {
    const qpow = (a: number, n: number, mod: number) => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    const last: Map<number, number> = new Map();
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        last.set(nums[i], i);
    }
    const mod = 1e9 + 7;
    let [j, k] = [-1, 0];
    for (let i = 0; i < n; ++i) {
        j = Math.max(j, last.get(nums[i])!);
        if (i === j) {
            ++k;
        }
    }
    return qpow(2, k - 1, mod);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
