---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0914.X%20of%20a%20Kind%20in%20a%20Deck%20of%20Cards/README.md
tags:
    - 数组
    - 哈希表
    - 数学
    - 计数
    - 数论
---

<!-- problem:start -->

# [914. 卡牌分组](https://leetcode.cn/problems/x-of-a-kind-in-a-deck-of-cards)

[English Version](/solution/0900-0999/0914.X%20of%20a%20Kind%20in%20a%20Deck%20of%20Cards/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一副牌，每张牌上都写着一个整数。</p>

<p>此时，你需要选定一个数字 <code>X</code>，使我们可以将整副牌按下述规则分成 1 组或更多组：</p>

<ul>
	<li>每组都有&nbsp;<code>X</code>&nbsp;张牌。</li>
	<li>组内所有的牌上都写着相同的整数。</li>
</ul>

<p>仅当你可选的 <code>X &gt;= 2</code> 时返回&nbsp;<code>true</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>deck = [1,2,3,4,4,3,2,1]
<strong>输出：</strong>true
<strong>解释：</strong>可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>deck = [1,1,1,2,2,2,3,3]
<strong>输出：</strong>false
<strong>解释：</strong>没有满足要求的分组。
</pre>

<p><br />
<strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= deck.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= deck[i] &lt; 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：最大公约数

我们先用数组或哈希表 `cnt` 统计每个数字出现的次数，只有当 $X$ 是所有数字出现次数的约数时，即 $X$ 是所有 `cnt[i]` 的最大公约数的约数时，才能满足题意。

因此，我们求出所有数字出现次数的最大公约数 $g$，然后判断其是否大于等于 $2$ 即可。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(n + \log M)$。其中 $n$ 和 $M$ 分别是数组 `deck` 的长度和数组 `deck` 中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasGroupsSizeX(self, deck: List[int]) -> bool:
        cnt = Counter(deck)
        return reduce(gcd, cnt.values()) >= 2
```

#### Java

```java
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : deck) {
            cnt.merge(x, 1, Integer::sum);
        }
        int g = cnt.get(deck[0]);
        for (int x : cnt.values()) {
            g = gcd(g, x);
        }
        return g >= 2;
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
    bool hasGroupsSizeX(vector<int>& deck) {
        unordered_map<int, int> cnt;
        for (int x : deck) {
            ++cnt[x];
        }
        int g = cnt[deck[0]];
        for (auto& [_, x] : cnt) {
            g = gcd(g, x);
        }
        return g >= 2;
    }
};
```

#### Go

```go
func hasGroupsSizeX(deck []int) bool {
	cnt := map[int]int{}
	for _, x := range deck {
		cnt[x]++
	}
	g := cnt[deck[0]]
	for _, x := range cnt {
		g = gcd(g, x)
	}
	return g >= 2
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function hasGroupsSizeX(deck: number[]): boolean {
    const cnt: Record<number, number> = {};
    for (const x of deck) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const gcd = (a: number, b: number): number => (b === 0 ? a : gcd(b, a % b));
    let g = cnt[deck[0]];
    for (const [_, x] of Object.entries(cnt)) {
        g = gcd(g, x);
    }
    return g >= 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
