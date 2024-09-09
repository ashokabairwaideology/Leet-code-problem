---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/README.md
rating: 1525
source: 第 284 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 模拟
---

<!-- problem:start -->

# [2201. 统计可以提取的工件](https://leetcode.cn/problems/count-artifacts-that-can-be-extracted)

[English Version](/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/README_EN.md)

## 题目描述

<!-- description:start -->

<p>存在一个 <code>n x n</code> 大小、下标从 <strong>0</strong> 开始的网格，网格中埋着一些工件。给你一个整数 <code>n</code> 和一个下标从 <strong>0</strong> 开始的二维整数数组 <code>artifacts</code> ，<code>artifacts</code> 描述了矩形工件的位置，其中 <code>artifacts[i] = [r1<sub>i</sub>, c1<sub>i</sub>, r2<sub>i</sub>, c2<sub>i</sub>]</code> 表示第 <code>i</code> 个工件在子网格中的填埋情况：</p>

<ul>
	<li><code>(r1<sub>i</sub>, c1<sub>i</sub>)</code> 是第 <code>i</code> 个工件 <strong>左上</strong> 单元格的坐标，且</li>
	<li><code>(r2<sub>i</sub>, c2<sub>i</sub>)</code> 是第 <code>i</code> 个工件 <strong>右下</strong> 单元格的坐标。</li>
</ul>

<p>你将会挖掘网格中的一些单元格，并清除其中的填埋物。如果单元格中埋着工件的一部分，那么该工件这一部分将会裸露出来。如果一个工件的所有部分都都裸露出来，你就可以提取该工件。</p>

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>dig</code> ，其中 <code>dig[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> 表示你将会挖掘单元格 <code>(r<sub>i</sub>, c<sub>i</sub>)</code> ，返回你可以提取的工件数目。</p>

<p>生成的测试用例满足：</p>

<ul>
	<li>不存在重叠的两个工件。</li>
	<li>每个工件最多只覆盖 <code>4</code> 个单元格。</li>
	<li><code>dig</code> 中的元素互不相同。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/images/untitled-diagram.jpg" style="width: 216px; height: 216px;">
<pre><strong>输入：</strong>n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1]]
<strong>输出：</strong>1
<strong>解释：</strong> 
不同颜色表示不同的工件。挖掘的单元格用 'D' 在网格中进行标记。
有 1 个工件可以提取，即红色工件。
蓝色工件在单元格 (1,1) 的部分尚未裸露出来，所以无法提取该工件。
因此，返回 1 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/images/untitled-diagram-1.jpg" style="width: 216px; height: 216px;">
<pre><strong>输入：</strong>n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1],[1,1]]
<strong>输出：</strong>2
<strong>解释：</strong>红色工件和蓝色工件的所有部分都裸露出来（用 'D' 标记），都可以提取。因此，返回 2 。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= artifacts.length, dig.length &lt;= min(n<sup>2</sup>, 10<sup>5</sup>)</code></li>
	<li><code>artifacts[i].length == 4</code></li>
	<li><code>dig[i].length == 2</code></li>
	<li><code>0 &lt;= r1<sub>i</sub>, c1<sub>i</sub>, r2<sub>i</sub>, c2<sub>i</sub>, r<sub>i</sub>, c<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>r1<sub>i</sub> &lt;= r2<sub>i</sub></code></li>
	<li><code>c1<sub>i</sub> &lt;= c2<sub>i</sub></code></li>
	<li>不存在重叠的两个工件</li>
	<li>每个工件 <strong>最多</strong> 只覆盖 <code>4</code> 个单元格</li>
	<li><code>dig</code> 中的元素互不相同</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用哈希表 $s$ 记录所有挖掘的单元格，然后遍历所有工件，判断工件的所有部分是否都在哈希表中，若是则可以提取该工件，答案加一。

时间复杂度 $O(m + k)$，空间复杂度 $O(k)$，其中 $m$ 是工件的数量，而 $k$ 是挖掘的单元格的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def digArtifacts(
        self, n: int, artifacts: List[List[int]], dig: List[List[int]]
    ) -> int:
        def check(a: List[int]) -> bool:
            x1, y1, x2, y2 = a
            return all(
                (x, y) in s for x in range(x1, x2 + 1) for y in range(y1, y2 + 1)
            )

        s = {(i, j) for i, j in dig}
        return sum(check(a) for a in artifacts)
```

#### Java

```java
class Solution {
    private Set<Integer> s = new HashSet<>();
    private int n;

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        this.n = n;
        for (var p : dig) {
            s.add(p[0] * n + p[1]);
        }
        int ans = 0;
        for (var a : artifacts) {
            ans += check(a);
        }
        return ans;
    }

    private int check(int[] a) {
        int x1 = a[0], y1 = a[1], x2 = a[2], y2 = a[3];
        for (int x = x1; x <= x2; ++x) {
            for (int y = y1; y <= y2; ++y) {
                if (!s.contains(x * n + y)) {
                    return 0;
                }
            }
        }
        return 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int digArtifacts(int n, vector<vector<int>>& artifacts, vector<vector<int>>& dig) {
        unordered_set<int> s;
        for (auto& p : dig) {
            s.insert(p[0] * n + p[1]);
        }
        auto check = [&](vector<int>& a) {
            int x1 = a[0], y1 = a[1], x2 = a[2], y2 = a[3];
            for (int x = x1; x <= x2; ++x) {
                for (int y = y1; y <= y2; ++y) {
                    if (!s.count(x * n + y)) {
                        return 0;
                    }
                }
            }
            return 1;
        };
        int ans = 0;
        for (auto& a : artifacts) {
            ans += check(a);
        }
        return ans;
    }
};
```

#### Go

```go
func digArtifacts(n int, artifacts [][]int, dig [][]int) (ans int) {
	s := map[int]bool{}
	for _, p := range dig {
		s[p[0]*n+p[1]] = true
	}
	check := func(a []int) int {
		x1, y1, x2, y2 := a[0], a[1], a[2], a[3]
		for x := x1; x <= x2; x++ {
			for y := y1; y <= y2; y++ {
				if !s[x*n+y] {
					return 0
				}
			}
		}
		return 1
	}
	for _, a := range artifacts {
		ans += check(a)
	}
	return
}
```

#### TypeScript

```ts
function digArtifacts(n: number, artifacts: number[][], dig: number[][]): number {
    const s: Set<number> = new Set();
    for (const [x, y] of dig) {
        s.add(x * n + y);
    }
    let ans = 0;
    const check = (a: number[]): number => {
        const [x1, y1, x2, y2] = a;
        for (let x = x1; x <= x2; ++x) {
            for (let y = y1; y <= y2; ++y) {
                if (!s.has(x * n + y)) {
                    return 0;
                }
            }
        }
        return 1;
    };
    for (const a of artifacts) {
        ans += check(a);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn dig_artifacts(n: i32, artifacts: Vec<Vec<i32>>, dig: Vec<Vec<i32>>) -> i32 {
        let mut s: HashSet<i32> = HashSet::new();
        for p in dig {
            s.insert(p[0] * n + p[1]);
        }
        let check = |a: &[i32]| -> i32 {
            let x1 = a[0];
            let y1 = a[1];
            let x2 = a[2];
            let y2 = a[3];
            for x in x1..=x2 {
                for y in y1..=y2 {
                    if !s.contains(&(x * n + y)) {
                        return 0;
                    }
                }
            }
            1
        };
        let mut ans = 0;
        for a in artifacts {
            ans += check(&a);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
