---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0096.Unique%20Binary%20Search%20Trees/README.md
tags:
    - 树
    - 二叉搜索树
    - 数学
    - 动态规划
    - 二叉树
---

<!-- problem:start -->

# [96. 不同的二叉搜索树](https://leetcode.cn/problems/unique-binary-search-trees)

[English Version](/solution/0000-0099/0096.Unique%20Binary%20Search%20Trees/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> ，求恰由 <code>n</code> 个节点组成且节点值从 <code>1</code> 到 <code>n</code> 互不相同的 <strong>二叉搜索树</strong> 有多少种？返回满足题意的二叉搜索树的种数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0096.Unique%20Binary%20Search%20Trees/images/uniquebstn3.jpg" style="width: 600px; height: 148px;" />
<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 19</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i]$ 表示 $[1, i]$ 能产生的二叉搜索树的个数，初始时 $f[0] = 1$，答案为 $f[n]$。

我们可以枚举节点数 $i$，那么左子树节点数 $j \in [0, i - 1]$，右子树节点数 $k = i - j - 1$，左子树节点数和右子树节点数的组合数为 $f[j] \times f[k]$，因此 $f[i] = \sum_{j = 0}^{i - 1} f[j] \times f[i - j - 1]$。

最后返回 $f[n]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numTrees(self, n: int) -> int:
        f = [1] + [0] * n
        for i in range(n + 1):
            for j in range(i):
                f[i] += f[j] * f[i - j - 1]
        return f[n]
```

#### Java

```java
class Solution {
    public int numTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numTrees(int n) {
        vector<int> f(n + 1);
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func numTrees(n int) int {
	f := make([]int, n+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		for j := 0; j < i; j++ {
			f[i] += f[j] * f[i-j-1]
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function numTrees(n: number): number {
    const f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < i; ++j) {
            f[i] += f[j] * f[i - j - 1];
        }
    }
    return f[n];
}
```

#### Rust

```rust
impl Solution {
    pub fn num_trees(n: i32) -> i32 {
        let n = n as usize;
        let mut f = vec![0; n + 1];
        f[0] = 1;
        for i in 1..=n {
            for j in 0..i {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        f[n] as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int NumTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        return f[n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
