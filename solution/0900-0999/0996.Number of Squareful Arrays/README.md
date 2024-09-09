---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0996.Number%20of%20Squareful%20Arrays/README.md
tags:
    - 位运算
    - 数组
    - 哈希表
    - 数学
    - 动态规划
    - 回溯
    - 状态压缩
---

<!-- problem:start -->

# [996. 平方数组的数目](https://leetcode.cn/problems/number-of-squareful-arrays)

[English Version](/solution/0900-0999/0996.Number%20of%20Squareful%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果一个数组的任意两个相邻元素之和都是 <strong>完全平方数 </strong>，则该数组称为 <strong>平方数组 </strong>。</p>

<p>给定一个整数数组 <code>nums</code>，返回所有属于 <strong>平方数组 </strong>的 <code>nums</code> 的排列数量。</p>

<p>如果存在某个索引 <code>i</code> 使得 <code>perm1[i] != perm2[i]</code>，则认为两个排列 <code>perm1</code> 和 <code>perm2</code> 不同。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,17,8]
<strong>输出：</strong>2
<strong>解释：</strong>[1,8,17] 和 [17,8,1] 是有效的排列。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 12</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二进制状态压缩 + 动态规划

注意到，数组 $nums$ 的长度 $n$ 不超过 $12$，因此我们可以用一个二进制数表示当前所选的数字的状态，若第 $i$ 位为 $1$，则表示当前选择了第 $i$ 个数字，否则表示当前没有选择第 $i$ 个数字。

我们定义 $f[i][j]$ 表示当前所选的数字的状态为 $i$，且最后一个数字为 $nums[j]$ 的方案数。那么答案就是 $\sum_{j=0}^{n-1} f[2^n-1][j]$。由于最后求解的是排列数，因此还需要除以每个数字出现的次数的阶乘。

接下来，我们考虑如何进行状态转移。

假设当前所选的数字的状态为 $i$，最后一个数字为 $nums[j]$，那么我们可以枚举 $i$ 的每一位为 $1$ 的数字作为倒数第二个数，不妨设为 $nums[k]$，那么我们只需要判断 $nums[j]+nums[k]$ 是否为完全平方数即可，若是，方案数 $f[i][j]$ 就可以加上 $f[i \oplus 2^j][k]$，其中 $i \oplus 2^j$ 表示将 $i$ 的第 $j$ 位取反，即表示将 $nums[j]$ 从当前所选的数字中去除。

最后，我们还需要除以每个数字出现的次数的阶乘，因为我们在枚举 $i$ 的每一位为 $1$ 的数字时，可能会重复计算某些排列，因此需要除以每个数字出现的次数的阶乘。

时间复杂度 $O(2^n \times n^2)，空间复杂度 O(2^n \times n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSquarefulPerms(self, nums: List[int]) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(1 << n)]
        for j in range(n):
            f[1 << j][j] = 1
        for i in range(1 << n):
            for j in range(n):
                if i >> j & 1:
                    for k in range(n):
                        if (i >> k & 1) and k != j:
                            s = nums[j] + nums[k]
                            t = int(sqrt(s))
                            if t * t == s:
                                f[i][j] += f[i ^ (1 << j)][k]

        ans = sum(f[(1 << n) - 1][j] for j in range(n))
        for v in Counter(nums).values():
            ans //= factorial(v)
        return ans
```

#### Java

```java
class Solution {
    public int numSquarefulPerms(int[] nums) {
        int n = nums.length;
        int[][] f = new int[1 << n][n];
        for (int j = 0; j < n; ++j) {
            f[1 << j][j] = 1;
        }
        for (int i = 0; i < 1 << n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    for (int k = 0; k < n; ++k) {
                        if ((i >> k & 1) == 1 && k != j) {
                            int s = nums[j] + nums[k];
                            int t = (int) Math.sqrt(s);
                            if (t * t == s) {
                                f[i][j] += f[i ^ (1 << j)][k];
                            }
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int j = 0; j < n; ++j) {
            ans += f[(1 << n) - 1][j];
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int[] g = new int[13];
        g[0] = 1;
        for (int i = 1; i < 13; ++i) {
            g[i] = g[i - 1] * i;
        }
        for (int v : cnt.values()) {
            ans /= g[v];
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numSquarefulPerms(vector<int>& nums) {
        int n = nums.size();
        int f[1 << n][n];
        memset(f, 0, sizeof(f));
        for (int j = 0; j < n; ++j) {
            f[1 << j][j] = 1;
        }
        for (int i = 0; i < 1 << n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    for (int k = 0; k < n; ++k) {
                        if ((i >> k & 1) == 1 && k != j) {
                            int s = nums[j] + nums[k];
                            int t = sqrt(s);
                            if (t * t == s) {
                                f[i][j] += f[i ^ (1 << j)][k];
                            }
                        }
                    }
                }
            }
        }
        long long ans = 0;
        for (int j = 0; j < n; ++j) {
            ans += f[(1 << n) - 1][j];
        }
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int g[13] = {1};
        for (int i = 1; i < 13; ++i) {
            g[i] = g[i - 1] * i;
        }
        for (auto& [_, v] : cnt) {
            ans /= g[v];
        }
        return ans;
    }
};
```

#### Go

```go
func numSquarefulPerms(nums []int) (ans int) {
	n := len(nums)
	f := make([][]int, 1<<n)
	for i := range f {
		f[i] = make([]int, n)
	}
	for j := range nums {
		f[1<<j][j] = 1
	}
	for i := 0; i < 1<<n; i++ {
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				for k := 0; k < n; k++ {
					if i>>k&1 == 1 && k != j {
						s := nums[j] + nums[k]
						t := int(math.Sqrt(float64(s)))
						if t*t == s {
							f[i][j] += f[i^(1<<j)][k]
						}
					}
				}
			}
		}
	}
	for j := 0; j < n; j++ {
		ans += f[(1<<n)-1][j]
	}
	g := [13]int{1}
	for i := 1; i < 13; i++ {
		g[i] = g[i-1] * i
	}
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for _, v := range cnt {
		ans /= g[v]
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
