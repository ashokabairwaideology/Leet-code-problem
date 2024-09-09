---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1770.Maximum%20Score%20from%20Performing%20Multiplication%20Operations/README.md
rating: 2068
source: 第 229 场周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [1770. 执行乘法运算的最大分数](https://leetcode.cn/problems/maximum-score-from-performing-multiplication-operations)

[English Version](/solution/1700-1799/1770.Maximum%20Score%20from%20Performing%20Multiplication%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度分别 <code>n</code> 和 <code>m</code> 的整数数组 <code>nums</code> 和 <code>multipliers</code><strong> </strong>，其中 <code>n &gt;= m</code> ，数组下标 <strong>从 1 开始</strong> 计数。</p>

<p>初始时，你的分数为 <code>0</code> 。你需要执行恰好 <code>m</code> 步操作。在第 <code>i</code> 步操作（<strong>从 1 开始</strong> 计数）中，需要：</p>

<ul>
	<li>选择数组 <code>nums</code> <strong>开头处或者末尾处</strong> 的整数 <code>x</code> 。</li>
	<li>你获得 <code>multipliers[i] * x</code> 分，并累加到你的分数中。</li>
	<li>将 <code>x</code> 从数组 <code>nums</code> 中移除。</li>
</ul>

<p>在执行<em> </em><code>m</code> 步操作后，返回 <strong>最大</strong> 分数<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3], multipliers = [3,2,1]
<strong>输出：</strong>14
<strong>解释：</strong>一种最优解决方案如下：
- 选择末尾处的整数 3 ，[1,2,<strong>3</strong>] ，得 3 * 3 = 9 分，累加到分数中。
- 选择末尾处的整数 2 ，[1,<strong>2</strong>] ，得 2 * 2 = 4 分，累加到分数中。
- 选择末尾处的整数 1 ，[<strong>1</strong>] ，得 1 * 1 = 1 分，累加到分数中。
总分数为 9 + 4 + 1 = 14 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
<strong>输出：</strong>102
<strong>解释：</strong>一种最优解决方案如下：
- 选择开头处的整数 -5 ，[<strong>-5</strong>,-3,-3,-2,7,1] ，得 -5 * -10 = 50 分，累加到分数中。
- 选择开头处的整数 -3 ，[<strong>-3</strong>,-3,-2,7,1] ，得 -3 * -5 = 15 分，累加到分数中。
- 选择开头处的整数 -3 ，[<strong>-3</strong>,-2,7,1] ，得 -3 * 3 = -9 分，累加到分数中。
- 选择末尾处的整数 1 ，[-2,7,<strong>1</strong>] ，得 1 * 4 = 4 分，累加到分数中。
- 选择末尾处的整数 7 ，[-2,<strong>7</strong>] ，得 7 * 6 = 42 分，累加到分数中。
总分数为 50 + 15 - 9 + 4 + 42 = 102 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == multipliers.length</code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>3</sup></code></li>
	<li><code>m &lt;= n &lt;= 10<sup>5</sup></code><code> </code></li>
	<li><code>-1000 &lt;= nums[i], multipliers[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i, j)$，表示从 `nums` 数组头部第 $i$ 个元素开始，从 `nums` 数组尾部第 $j$ 个元素开始，能够获得的最大分数。那么答案就是 $dfs(0, 0)$。

函数 $dfs(i, j)$ 的计算过程如下：

-   如果 $i \geq m$ 或者 $j \geq m$，或者 $i + j \geq m$，说明已经没有元素可以选择了，返回 $0$。
-   否则，我们可以选择 `nums` 数组头部第 $i$ 个元素，那么能够获取的最大分数为 $nums[i] \times multipliers[i + j] + dfs(i + 1, j)$；或者我们可以选择 `nums` 数组尾部第 $j$ 个元素，那么能够获取的最大分数为 $nums[n - j - 1] \times multipliers[i + j] + dfs(i, j + 1)$。我们取两者的最大值作为 $dfs(i, j)$ 的返回值。

我们可以使用记忆化搜索来实现上述递归过程，其中 `f` 数组用于存储函数 $dfs(i, j)$ 的返回值，防止重复计算。

时间复杂度 $O(m^2)$，空间复杂度 $O(m^2)$。其中 $m$ 为 `multipliers` 数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, nums: List[int], multipliers: List[int]) -> int:
        @cache
        def f(i, j, k):
            if k >= m or i >= n or j < 0:
                return 0
            a = f(i + 1, j, k + 1) + nums[i] * multipliers[k]
            b = f(i, j - 1, k + 1) + nums[j] * multipliers[k]
            return max(a, b)

        n = len(nums)
        m = len(multipliers)
        return f(0, n - 1, 0)
```

#### Java

```java
class Solution {
    private Integer[][] f;
    private int[] multipliers;
    private int[] nums;
    private int n;
    private int m;

    public int maximumScore(int[] nums, int[] multipliers) {
        n = nums.length;
        m = multipliers.length;
        f = new Integer[m][m];
        this.nums = nums;
        this.multipliers = multipliers;
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m || j >= m || (i + j) >= m) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int k = i + j;
        int a = dfs(i + 1, j) + nums[i] * multipliers[k];
        int b = dfs(i, j + 1) + nums[n - 1 - j] * multipliers[k];
        f[i][j] = Math.max(a, b);
        return f[i][j];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumScore(vector<int>& nums, vector<int>& multipliers) {
        int n = nums.size(), m = multipliers.size();
        int f[m][m];
        memset(f, 0x3f, sizeof f);
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= m || j >= m || (i + j) >= m) return 0;
            if (f[i][j] != 0x3f3f3f3f) return f[i][j];
            int k = i + j;
            int a = dfs(i + 1, j) + nums[i] * multipliers[k];
            int b = dfs(i, j + 1) + nums[n - j - 1] * multipliers[k];
            return f[i][j] = max(a, b);
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func maximumScore(nums []int, multipliers []int) int {
	n, m := len(nums), len(multipliers)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = 1 << 30
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m || j >= m || i+j >= m {
			return 0
		}
		if f[i][j] != 1<<30 {
			return f[i][j]
		}
		k := i + j
		a := dfs(i+1, j) + nums[i]*multipliers[k]
		b := dfs(i, j+1) + nums[n-j-1]*multipliers[k]
		f[i][j] = max(a, b)
		return f[i][j]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function maximumScore(nums: number[], multipliers: number[]): number {
    const inf = 1 << 30;
    const n = nums.length;
    const m = multipliers.length;
    const f = new Array(m + 1).fill(0).map(() => new Array(m + 1).fill(-inf));
    f[0][0] = 0;
    let ans = -inf;
    for (let i = 0; i <= m; ++i) {
        for (let j = 0; j <= m - i; ++j) {
            const k = i + j - 1;
            if (i > 0) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j] + nums[i - 1] * multipliers[k]);
            }
            if (j > 0) {
                f[i][j] = Math.max(f[i][j], f[i][j - 1] + nums[n - j] * multipliers[k]);
            }
            if (i + j === m) {
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们可以将方法一中的记忆化搜索改写为动态规划的形式。

我们用 $f[i][j]$ 表示取数组 $nums$ 的前 $i$ 个元素，以及取数组 $nums$ 的后 $j$ 个元素，能够获得的最大分数。初始时 $f[0][0] = 0$，其余元素均为 $-\infty$。答案为 $\max_{0 \leq i \leq m} f[i][m-i]$。

考虑 $f[i][j]$，那么当前我们可以选择 `nums` 数组头部的第 $i$ 个元素，或者选择 `nums` 数组尾部的第 $j$ 个元素。如果选择了 `nums` 数组头部的第 $i$ 个元素，那么能够获得的最大分数为 $f[i-1][j] + nums[i-1] \times multipliers[i+j-1]$；如果选择了 `nums` 数组尾部的第 $j$ 个元素，那么能够获得的最大分数为 $f[i][j-1] + nums[n-j] \times multipliers[i+j-1]$。我们取两者的最大值作为 $f[i][j]$ 的值。如果 $i + j = m$，我们我们更新答案 $ans = \max(ans, f[i][j])$。

最后返回答案 $ans$ 即可。

时间复杂度 $O(m^2)$，空间复杂度 $O(m^2)$。其中 $m$ 为 `multipliers` 数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, nums: List[int], multipliers: List[int]) -> int:
        n, m = len(nums), len(multipliers)
        f = [[-inf] * (m + 1) for _ in range(m + 1)]
        f[0][0] = 0
        ans = -inf
        for i in range(m + 1):
            for j in range(m - i + 1):
                k = i + j - 1
                if i > 0:
                    f[i][j] = max(f[i][j], f[i - 1][j] + multipliers[k] * nums[i - 1])
                if j > 0:
                    f[i][j] = max(f[i][j], f[i][j - 1] + multipliers[k] * nums[n - j])
                if i + j == m:
                    ans = max(ans, f[i][j])
        return ans
```

#### Java

```java
class Solution {
    public int maximumScore(int[] nums, int[] multipliers) {
        final int inf = 1 << 30;
        int n = nums.length, m = multipliers.length;
        int[][] f = new int[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(f[i], -inf);
        }
        f[0][0] = 0;
        int ans = -inf;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= m - i; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + multipliers[k] * nums[i - 1]);
                }
                if (j > 0) {
                    f[i][j] = Math.max(f[i][j], f[i][j - 1] + multipliers[k] * nums[n - j]);
                }
                if (i + j == m) {
                    ans = Math.max(ans, f[i][j]);
                }
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
    int maximumScore(vector<int>& nums, vector<int>& multipliers) {
        const int inf = 1 << 30;
        int n = nums.size(), m = multipliers.size();
        vector<vector<int>> f(m + 1, vector<int>(m + 1, -inf));
        f[0][0] = 0;
        int ans = -inf;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= m - i; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[i][j] = max(f[i][j], f[i - 1][j] + multipliers[k] * nums[i - 1]);
                }
                if (j > 0) {
                    f[i][j] = max(f[i][j], f[i][j - 1] + multipliers[k] * nums[n - j]);
                }
                if (i + j == m) {
                    ans = max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumScore(nums []int, multipliers []int) int {
	const inf int = 1 << 30
	n, m := len(nums), len(multipliers)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, m+1)
		for j := range f {
			f[i][j] = -inf
		}
	}
	f[0][0] = 0
	ans := -inf
	for i := 0; i <= m; i++ {
		for j := 0; j <= m-i; j++ {
			k := i + j - 1
			if i > 0 {
				f[i][j] = max(f[i][j], f[i-1][j]+multipliers[k]*nums[i-1])
			}
			if j > 0 {
				f[i][j] = max(f[i][j], f[i][j-1]+multipliers[k]*nums[n-j])
			}
			if i+j == m {
				ans = max(ans, f[i][j])
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
