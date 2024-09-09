---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0403.Frog%20Jump/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [403. 青蛙过河](https://leetcode.cn/problems/frog-jump)

[English Version](/solution/0400-0499/0403.Frog%20Jump/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。</p>

<p>给你石子的位置列表 <code>stones</code>（用单元格序号 <strong>升序</strong> 表示），&nbsp;请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。开始时，&nbsp;青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃 <code>1</code> 个单位（即只能从单元格 1 跳至单元格 2 ）。</p>

<p>如果青蛙上一步跳跃了&nbsp;<code>k</code><em>&nbsp;</em>个单位，那么它接下来的跳跃距离只能选择为&nbsp;<code>k - 1</code>、<code>k</code><em>&nbsp;</em>或&nbsp;<code>k + 1</code> 个单位。&nbsp;另请注意，青蛙只能向前方（终点的方向）跳跃。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>stones = [0,1,3,5,6,8,12,17]
<strong>输出：</strong>true
<strong>解释：</strong>青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>stones = [0,1,2,3,4,8,9,11]
<strong>输出：</strong>false
<strong>解释：</strong>这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= stones.length &lt;= 2000</code></li>
	<li><code>0 &lt;= stones[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>stones[0] == 0</code></li>
	<li><code>stones</code>&nbsp;按严格升序排列</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 记忆化搜索

我们用哈希表 $pos$ 记录每个石子的下标，接下来设计一个函数 $dfs(i, k)$，表示青蛙从第 $i$ 个石子跳跃且上一次跳跃距离为 $k$，如果青蛙能够到达终点，那么函数返回 `true`，否则返回 `false`。

函数 $dfs(i, k)$ 的计算过程如下：

如果 $i$ 是最后一个石子的下标，那么青蛙已经到达终点，返回 `true`；

否则，我们需要枚举青蛙接下来的跳跃距离 $j$，其中 $j \in [k-1, k, k+1]$。如果 $j$ 是正数，并且哈希表 $pos$ 中存在位置 $stones[i] + j$，那么青蛙在第 $i$ 个石子上可以选择跳跃 $j$ 个单位，如果 $dfs(pos[stones[i] + j], j)$ 返回 `true`，那么青蛙可以从第 $i$ 个石子成功跳跃到终点，我们就可以返回 `true`。

枚举结束，说明青蛙在第 $i$ 个石子上无法选择合适的跳跃距离跳到终点，我们就返回 `false`。

为了防止函数 $dfs(i, k)$ 中出现重复计算，我们可以使用记忆化搜索，将 $dfs(i, k)$ 的结果记录在一个数组 $f$ 中，每当函数 $dfs(i, k)$ 返回结果，我们就将 $f[i][k]$ 进行赋值，并在下次遇到 $dfs(i, k)$ 时直接返回 $f[i][k]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是石子的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canCross(self, stones: List[int]) -> bool:
        @cache
        def dfs(i, k):
            if i == n - 1:
                return True
            for j in range(k - 1, k + 2):
                if j > 0 and stones[i] + j in pos and dfs(pos[stones[i] + j], j):
                    return True
            return False

        n = len(stones)
        pos = {s: i for i, s in enumerate(stones)}
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Boolean[][] f;
    private Map<Integer, Integer> pos = new HashMap<>();
    private int[] stones;
    private int n;

    public boolean canCross(int[] stones) {
        n = stones.length;
        f = new Boolean[n][n];
        this.stones = stones;
        for (int i = 0; i < n; ++i) {
            pos.put(stones[i], i);
        }
        return dfs(0, 0);
    }

    private boolean dfs(int i, int k) {
        if (i == n - 1) {
            return true;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        for (int j = k - 1; j <= k + 1; ++j) {
            if (j > 0) {
                int h = stones[i] + j;
                if (pos.containsKey(h) && dfs(pos.get(h), j)) {
                    return f[i][k] = true;
                }
            }
        }
        return f[i][k] = false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canCross(vector<int>& stones) {
        int n = stones.size();
        int f[n][n];
        memset(f, -1, sizeof(f));
        unordered_map<int, int> pos;
        for (int i = 0; i < n; ++i) {
            pos[stones[i]] = i;
        }
        function<bool(int, int)> dfs = [&](int i, int k) -> bool {
            if (i == n - 1) {
                return true;
            }
            if (f[i][k] != -1) {
                return f[i][k];
            }
            for (int j = k - 1; j <= k + 1; ++j) {
                if (j > 0 && pos.count(stones[i] + j) && dfs(pos[stones[i] + j], j)) {
                    return f[i][k] = true;
                }
            }
            return f[i][k] = false;
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func canCross(stones []int) bool {
	n := len(stones)
	f := make([][]int, n)
	pos := map[int]int{}
	for i := range f {
		pos[stones[i]] = i
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) bool
	dfs = func(i, k int) bool {
		if i == n-1 {
			return true
		}
		if f[i][k] != -1 {
			return f[i][k] == 1
		}
		for j := k - 1; j <= k+1; j++ {
			if j > 0 {
				if p, ok := pos[stones[i]+j]; ok {
					if dfs(p, j) {
						f[i][k] = 1
						return true
					}
				}
			}
		}
		f[i][k] = 0
		return false
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function canCross(stones: number[]): boolean {
    const n = stones.length;
    const pos: Map<number, number> = new Map();
    for (let i = 0; i < n; ++i) {
        pos.set(stones[i], i);
    }
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(-1));
    const dfs = (i: number, k: number): boolean => {
        if (i === n - 1) {
            return true;
        }
        if (f[i][k] !== -1) {
            return f[i][k] === 1;
        }
        for (let j = k - 1; j <= k + 1; ++j) {
            if (j > 0 && pos.has(stones[i] + j)) {
                if (dfs(pos.get(stones[i] + j)!, j)) {
                    f[i][k] = 1;
                    return true;
                }
            }
        }
        f[i][k] = 0;
        return false;
    };
    return dfs(0, 0);
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    #[allow(dead_code)]
    pub fn can_cross(stones: Vec<i32>) -> bool {
        let n = stones.len();
        let mut record = vec![vec![-1; n]; n];
        let mut pos = HashMap::new();
        for (i, &s) in stones.iter().enumerate() {
            pos.insert(s, i);
        }

        Self::dfs(&mut record, 0, 0, n, &pos, &stones)
    }

    #[allow(dead_code)]
    fn dfs(
        record: &mut Vec<Vec<i32>>,
        i: usize,
        k: usize,
        n: usize,
        pos: &HashMap<i32, usize>,
        stones: &Vec<i32>,
    ) -> bool {
        if i == n - 1 {
            return true;
        }

        if record[i][k] != -1 {
            return record[i][k] == 1;
        }

        let k = k as i32;
        for j in k - 1..=k + 1 {
            if j > 0
                && pos.contains_key(&(stones[i] + j))
                && Self::dfs(record, pos[&(stones[i] + j)], j as usize, n, pos, stones)
            {
                record[i][k as usize] = 1;
                return true;
            }
        }

        record[i][k as usize] = 0;
        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们定义 $f[i][k]$ 表示青蛙能否达到「现在所处的石子编号」为 $i$，「上一次跳跃距离」为 $k$ 的状态。初始时 $f[0][0] = true$，其余均为 `false`。

考虑 $f[i]$，我们可以枚举上一块石子的编号 $j$，那么上一次跳跃的距离 $k=stones[i]-stones[j]$。如果 $k-1 \gt j$，那么青蛙无法从第 $j$ 块石子跳跃到第 $i$ 块石子，我们可以直接跳过这种情况。否则，青蛙可以从第 $j$ 块石子跳跃到第 $i$ 块石子，那么 $f[i][k] = f[j][k-1] \lor f[j][k] \lor f[j][k+1]$。如果 $i=n-1$，且 $f[i][k]=true$，那么青蛙可以成功过河，我们就可以返回 `true`。

否则，我们最后返回 `false`。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是石子的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canCross(self, stones: List[int]) -> bool:
        n = len(stones)
        f = [[False] * n for _ in range(n)]
        f[0][0] = True
        for i in range(1, n):
            for j in range(i - 1, -1, -1):
                k = stones[i] - stones[j]
                if k - 1 > j:
                    break
                f[i][k] = f[j][k - 1] or f[j][k] or f[j][k + 1]
                if i == n - 1 and f[i][k]:
                    return True
        return False
```

#### Java

```java
class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] f = new boolean[n][n];
        f[0][0] = true;
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k - 1 > j) {
                    break;
                }
                f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
                if (i == n - 1 && f[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canCross(vector<int>& stones) {
        int n = stones.size();
        bool f[n][n];
        memset(f, false, sizeof(f));
        f[0][0] = true;
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k - 1 > j) {
                    break;
                }
                f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
                if (i == n - 1 && f[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

#### Go

```go
func canCross(stones []int) bool {
	n := len(stones)
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
	}
	f[0][0] = true
	for i := 1; i < n; i++ {
		for j := i - 1; j >= 0; j-- {
			k := stones[i] - stones[j]
			if k-1 > j {
				break
			}
			f[i][k] = f[j][k-1] || f[j][k] || f[j][k+1]
			if i == n-1 && f[i][k] {
				return true
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function canCross(stones: number[]): boolean {
    const n = stones.length;
    const f: boolean[][] = new Array(n).fill(0).map(() => new Array(n).fill(false));
    f[0][0] = true;
    for (let i = 1; i < n; ++i) {
        for (let j = i - 1; j >= 0; --j) {
            const k = stones[i] - stones[j];
            if (k - 1 > j) {
                break;
            }
            f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
            if (i == n - 1 && f[i][k]) {
                return true;
            }
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn can_cross(stones: Vec<i32>) -> bool {
        let n = stones.len();
        let mut dp = vec![vec![false; n]; n];

        // Initialize the dp vector
        dp[0][0] = true;

        // Begin the actual dp process
        for i in 1..n {
            for j in (0..=i - 1).rev() {
                let k = (stones[i] - stones[j]) as usize;
                if k - 1 > j {
                    break;
                }
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                if i == n - 1 && dp[i][k] {
                    return true;
                }
            }
        }

        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
