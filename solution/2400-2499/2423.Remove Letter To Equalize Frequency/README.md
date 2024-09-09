---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2423.Remove%20Letter%20To%20Equalize%20Frequency/README.md
rating: 1648
source: 第 88 场双周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [2423. 删除字符使频率相同](https://leetcode.cn/problems/remove-letter-to-equalize-frequency)

[English Version](/solution/2400-2499/2423.Remove%20Letter%20To%20Equalize%20Frequency/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>word</code>&nbsp;，字符串只包含小写英文字母。你需要选择 <strong>一个</strong>&nbsp;下标并 <strong>删除</strong>&nbsp;下标处的字符，使得 <code>word</code>&nbsp;中剩余每个字母出现 <strong>频率</strong>&nbsp;相同。</p>

<p>如果删除一个字母后，<code>word</code>&nbsp;中剩余所有字母的出现频率都相同，那么返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>字母&nbsp;<code>x</code>&nbsp;的 <strong>频率</strong><strong>&nbsp;</strong>是这个字母在字符串中出现的次数。</li>
	<li>你 <strong>必须</strong>&nbsp;恰好删除一个字母，不能一个字母都不删除。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>word = "abcc"
<b>输出：</b>true
<b>解释：</b>选择下标 3 并删除该字母：word 变成 "abc" 且每个字母出现频率都为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>word = "aazz"
<b>输出：</b>false
<b>解释：</b>我们必须删除一个字母，所以要么 "a" 的频率变为 1 且 "z" 的频率为 2 ，要么两个字母频率反过来。所以不可能让剩余所有字母出现频率相同。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 枚举

我们先用哈希表或者一个长度为 $26$ 的数组 $cnt$ 统计字符串中每个字母出现的次数。

接下来，枚举 $26$ 个字母，如果字母 $c$ 在字符串中出现过，我们将其出现次数减一，然后判断剩余的字母出现次数是否相同。如果相同，返回 `true`，否则将 $c$ 的出现次数加一，继续枚举下一个字母。

枚举结束，说明无法通过删除一个字母使得剩余字母出现次数相同，返回 `false`。

时间复杂度 $O(n + C^2)$，空间复杂度 $O(C)$，其中 $n$ 为字符串 $word$ 的长度，而 $C$ 为字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def equalFrequency(self, word: str) -> bool:
        cnt = Counter(word)
        for c in cnt.keys():
            cnt[c] -= 1
            if len(set(v for v in cnt.values() if v)) == 1:
                return True
            cnt[c] += 1
        return False
```

#### Java

```java
class Solution {
    public boolean equalFrequency(String word) {
        int[] cnt = new int[26];
        for (int i = 0; i < word.length(); ++i) {
            ++cnt[word.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                --cnt[i];
                int x = 0;
                boolean ok = true;
                for (int v : cnt) {
                    if (v == 0) {
                        continue;
                    }
                    if (x > 0 && v != x) {
                        ok = false;
                        break;
                    }
                    x = v;
                }
                if (ok) {
                    return true;
                }
                ++cnt[i];
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
    bool equalFrequency(string word) {
        int cnt[26]{};
        for (char& c : word) {
            ++cnt[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i]) {
                --cnt[i];
                int x = 0;
                bool ok = true;
                for (int v : cnt) {
                    if (v == 0) {
                        continue;
                    }
                    if (x && v != x) {
                        ok = false;
                        break;
                    }
                    x = v;
                }
                if (ok) {
                    return true;
                }
                ++cnt[i];
            }
        }
        return false;
    }
};
```

#### Go

```go
func equalFrequency(word string) bool {
	cnt := [26]int{}
	for _, c := range word {
		cnt[c-'a']++
	}
	for i := range cnt {
		if cnt[i] > 0 {
			cnt[i]--
			x := 0
			ok := true
			for _, v := range cnt {
				if v == 0 {
					continue
				}
				if x > 0 && v != x {
					ok = false
					break
				}
				x = v
			}
			if ok {
				return true
			}
			cnt[i]++
		}
	}
	return false
}
```

#### TypeScript

```ts
function equalFrequency(word: string): boolean {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of word) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (let i = 0; i < 26; ++i) {
        if (cnt[i]) {
            cnt[i]--;
            let x = 0;
            let ok = true;
            for (const v of cnt) {
                if (v === 0) {
                    continue;
                }
                if (x && v !== x) {
                    ok = false;
                    break;
                }
                x = v;
            }
            if (ok) {
                return true;
            }
            cnt[i]++;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
