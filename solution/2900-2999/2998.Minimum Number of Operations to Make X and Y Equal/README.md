---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2998.Minimum%20Number%20of%20Operations%20to%20Make%20X%20and%20Y%20Equal/README.md
rating: 1794
source: 第 121 场双周赛 Q3
tags:
    - 广度优先搜索
    - 记忆化搜索
    - 动态规划
---

<!-- problem:start -->

# [2998. 使 X 和 Y 相等的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-x-and-y-equal)

[English Version](/solution/2900-2999/2998.Minimum%20Number%20of%20Operations%20to%20Make%20X%20and%20Y%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;。</p>

<p>一次操作中，你可以执行以下四种操作之一：</p>

<ol>
	<li>如果 <code>x</code>&nbsp;是 <code>11</code>&nbsp;的倍数，将&nbsp;<code>x</code>&nbsp;除以&nbsp;<code>11</code>&nbsp;。</li>
	<li>如果 <code>x</code>&nbsp;是 <code>5</code>&nbsp;的倍数，将 <code>x</code>&nbsp;除以 <code>5</code>&nbsp;。</li>
	<li>将&nbsp;<code>x</code> 减&nbsp;<code>1</code>&nbsp;。</li>
	<li>将&nbsp;<code>x</code>&nbsp;加&nbsp;<code>1</code>&nbsp;。</li>
</ol>

<p>请你返回让 <code>x</code>&nbsp;和 <code>y</code>&nbsp;相等的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>x = 26, y = 1
<b>输出：</b>3
<b>解释</b><strong>：</strong>我们可以通过以下操作将 26 变为 1 ：
1. 将 x 减 1
2. 将 x 除以 5
3. 将 x 除以 5
将 26 变为 1 最少需要 3 次操作。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>x = 54, y = 2
<b>输出：</b>4
<b>解释：</b>我们可以通过以下操作将 54 变为 2 ：
1. 将 x 加 1
2. 将 x 除以 11
3. 将 x 除以 5
4. 将 x 加 1
将 54 变为 2 最少需要 4 次操作。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>x = 25, y = 30
<b>输出：</b>5
<b>解释：</b>我们可以通过以下操作将 25 变为 30 ：
1. 将 x 加 1
2. 将 x 加 1
3. 将 x 加 1
4. 将 x 加 1
5. 将 x 加 1
将 25 变为 30 最少需要 5 次操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x, y &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperationsToMakeEqual(self, x: int, y: int) -> int:
        @cache
        def dfs(x: int) -> int:
            if y >= x:
                return y - x
            ans = x - y
            ans = min(ans, x % 5 + 1 + dfs(x // 5))
            ans = min(ans, 5 - x % 5 + 1 + dfs(x // 5 + 1))
            ans = min(ans, x % 11 + 1 + dfs(x // 11))
            ans = min(ans, 11 - x % 11 + 1 + dfs(x // 11 + 1))
            return ans

        return dfs(x)
```

#### Java

```java
class Solution {
    private Map<Integer, Integer> f = new HashMap<>();
    private int y;

    public int minimumOperationsToMakeEqual(int x, int y) {
        this.y = y;
        return dfs(x);
    }

    private int dfs(int x) {
        if (y >= x) {
            return y - x;
        }
        if (f.containsKey(x)) {
            return f.get(x);
        }
        int ans = x - y;
        int a = x % 5 + 1 + dfs(x / 5);
        int b = 5 - x % 5 + 1 + dfs(x / 5 + 1);
        int c = x % 11 + 1 + dfs(x / 11);
        int d = 11 - x % 11 + 1 + dfs(x / 11 + 1);
        ans = Math.min(ans, Math.min(a, Math.min(b, Math.min(c, d))));
        f.put(x, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOperationsToMakeEqual(int x, int y) {
        unordered_map<int, int> f;
        function<int(int)> dfs = [&](int x) {
            if (y >= x) {
                return y - x;
            }
            if (f.count(x)) {
                return f[x];
            }
            int a = x % 5 + 1 + dfs(x / 5);
            int b = 5 - x % 5 + 1 + dfs(x / 5 + 1);
            int c = x % 11 + 1 + dfs(x / 11);
            int d = 11 - x % 11 + 1 + dfs(x / 11 + 1);
            return f[x] = min({x - y, a, b, c, d});
        };
        return dfs(x);
    }
};
```

#### Go

```go
func minimumOperationsToMakeEqual(x int, y int) int {
	f := map[int]int{}
	var dfs func(int) int
	dfs = func(x int) int {
		if y >= x {
			return y - x
		}
		if v, ok := f[x]; ok {
			return v
		}
		a := x%5 + 1 + dfs(x/5)
		b := 5 - x%5 + 1 + dfs(x/5+1)
		c := x%11 + 1 + dfs(x/11)
		d := 11 - x%11 + 1 + dfs(x/11+1)
		f[x] = min(x-y, a, b, c, d)
		return f[x]
	}
	return dfs(x)
}
```

#### TypeScript

```ts
function minimumOperationsToMakeEqual(x: number, y: number): number {
    const f: Map<number, number> = new Map();
    const dfs = (x: number): number => {
        if (y >= x) {
            return y - x;
        }
        if (f.has(x)) {
            return f.get(x)!;
        }
        const a = (x % 5) + 1 + dfs((x / 5) | 0);
        const b = 5 - (x % 5) + 1 + dfs(((x / 5) | 0) + 1);
        const c = (x % 11) + 1 + dfs((x / 11) | 0);
        const d = 11 - (x % 11) + 1 + dfs(((x / 11) | 0) + 1);
        const ans = Math.min(x - y, a, b, c, d);
        f.set(x, ans);
        return ans;
    };
    return dfs(x);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
