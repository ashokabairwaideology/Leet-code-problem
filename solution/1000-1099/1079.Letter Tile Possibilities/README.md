---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1079.Letter%20Tile%20Possibilities/README.md
rating: 1740
source: 第 140 场周赛 Q2
tags:
    - 哈希表
    - 字符串
    - 回溯
    - 计数
---

<!-- problem:start -->

# [1079. 活字印刷](https://leetcode.cn/problems/letter-tile-possibilities)

[English Version](/solution/1000-1099/1079.Letter%20Tile%20Possibilities/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一套活字字模&nbsp;<code>tiles</code>，其中每个字模上都刻有一个字母&nbsp;<code>tiles[i]</code>。返回你可以印出的非空字母序列的数目。</p>

<p><strong>注意：</strong>本题中，每个活字字模只能使用一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>"AAB"
<strong>输出：</strong>8
<strong>解释：</strong>可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>"AAABBC"
<strong>输出：</strong>188
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>"V"
<strong>输出：</strong>1</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tiles.length &lt;= 7</code></li>
	<li><code>tiles</code> 由大写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 回溯

我们先用一个哈希表或数组 $cnt$ 统计每个字母出现的次数。

接下来定义一个函数 $dfs(cnt)$，表示当前剩余字母的计数为 $cnt$ 时，能够组成的不同序列的个数。

在 $dfs$ 中，我们枚举 $cnt$ 中每个大于 $0$ 的值 $cnt[i]$，将 $cnt[i]$ 减 $1$ 表示使用了这个字母，序列个数加 $1$，然后进行下一层搜索，在搜索结束后，累加返回的序列个数，然后将 $cnt[i]$ 加 $1$。最后返回序列个数。

时间复杂度 $O(n \times n!)$，空间复杂度 $O(n)$。其中 $n$ 为字母种类数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numTilePossibilities(self, tiles: str) -> int:
        def dfs(cnt: Counter) -> int:
            ans = 0
            for i, x in cnt.items():
                if x > 0:
                    ans += 1
                    cnt[i] -= 1
                    ans += dfs(cnt)
                    cnt[i] += 1
            return ans

        cnt = Counter(tiles)
        return dfs(cnt)
```

#### Java

```java
class Solution {
    public int numTilePossibilities(String tiles) {
        int[] cnt = new int[26];
        for (char c : tiles.toCharArray()) {
            ++cnt[c - 'A'];
        }
        return dfs(cnt);
    }

    private int dfs(int[] cnt) {
        int res = 0;
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                ++res;
                --cnt[i];
                res += dfs(cnt);
                ++cnt[i];
            }
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numTilePossibilities(string tiles) {
        int cnt[26]{};
        for (char c : tiles) {
            ++cnt[c - 'A'];
        }
        function<int(int* cnt)> dfs = [&](int* cnt) -> int {
            int res = 0;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > 0) {
                    ++res;
                    --cnt[i];
                    res += dfs(cnt);
                    ++cnt[i];
                }
            }
            return res;
        };
        return dfs(cnt);
    }
};
```

#### Go

```go
func numTilePossibilities(tiles string) int {
	cnt := [26]int{}
	for _, c := range tiles {
		cnt[c-'A']++
	}
	var dfs func(cnt [26]int) int
	dfs = func(cnt [26]int) (res int) {
		for i, x := range cnt {
			if x > 0 {
				res++
				cnt[i]--
				res += dfs(cnt)
				cnt[i]++
			}
		}
		return
	}
	return dfs(cnt)
}
```

#### TypeScript

```ts
function numTilePossibilities(tiles: string): number {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of tiles) {
        ++cnt[c.charCodeAt(0) - 'A'.charCodeAt(0)];
    }
    const dfs = (cnt: number[]): number => {
        let res = 0;
        for (let i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                ++res;
                --cnt[i];
                res += dfs(cnt);
                ++cnt[i];
            }
        }
        return res;
    };
    return dfs(cnt);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
