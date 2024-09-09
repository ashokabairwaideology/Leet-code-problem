---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/README.md
tags:
    - 贪心
    - 哈希表
    - 字符串
    - 计数
    - 排序
---

<!-- problem:start -->

# [2268. 最少按键次数 🔒](https://leetcode.cn/problems/minimum-number-of-keypresses)

[English Version](/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一个 9 键键盘，按键按 1 到 9 编号，每个按键对应着几个英文小写字母。你可以决定每个按键对应哪些英文字母，但要满足如下条件：</p>

<ul>
	<li>26 个英文小写字母必须全部映射到这 9 个按键上。</li>
	<li>每个英文字母只能映射到 <strong>恰好</strong> 一个按键上。</li>
	<li>每个按键 <strong>最多</strong> 对应 3 个英文字母。</li>
</ul>

<p>如果想打出按键上的第一个字母，只需要按一次。如果想打出按键上的第二个字母，则需要按两次，依次类推。</p>

<p>给你一个字符串 <code>s</code> ，返回基于你设计的键盘打出 <code>s</code> 需要的<strong> 最少</strong> 按键次数。</p>

<p><b>注意：</b>字母映射到每个按键上，映射的顺序无法进行更改。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/images/image-20220505184346-1.png" style="width: 300px; height: 293px;" />
<pre>
<strong>输入：</strong>s = "apple"
<strong>输出：</strong>5
<strong>解释：</strong>上图所示为设置键盘的最佳方法之一。
按按键 1 一次输入 'a' 。
按按键 6 一次输入 'p' 。
按按键 6 一次输入 'p' 。
按按键 5 一次输入 'l' 。
按按键 3 一次输入 'e' 。
总共按按键 5 次，所以返回 5 。</pre>

<p><strong>示例 2 ：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/images/image-20220505203823-1.png" style="width: 300px; height: 288px;" />
<pre>
<strong>输入：</strong>s = "abcdefghijkl"
<strong>输出：</strong>15
<strong>解释：</strong>上图所示为设置键盘的最佳方法之一。
字母 'a' 到 'i' 每个只需要按一次按键。
按按键 1 两次输入 'j' 。
按按键 2 两次输入 'k' 。
按按键 3 两次输入 'l' 。
总共按按键 15 次，所以返回 15 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 贪心

我们首先统计字符串 $s$ 中每个字符出现的次数，记录在数组或者哈希表 $\textit{cnt}$ 中。

题目要求按键次数最少，那么出现最多的 $9$ 个字符应该对应按键 $1$ 到按键 $9$，出现次数第 $10$ 到第 $18$ 多的字符再次对应按键 $1$ 到按键 $9$，以此类推。

因此，我们可以将 $\textit{cnt}$ 中的值按照从大到小的顺序排序，然后按照 $1$ 到 $9$ 的顺序依次分配给按键，每次分配完 $9$ 个字符后，按键次数加 $1$。

时间复杂度 $O(n + |\Sigma| \times \log |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $s$ 的长度，而 $\Sigma$ 是字符串 $s$ 中出现的字符集合，本题中 $\Sigma$ 是小写字母集合，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumKeypresses(self, s: str) -> int:
        cnt = Counter(s)
        ans, k = 0, 1
        for i, x in enumerate(sorted(cnt.values(), reverse=True), 1):
            ans += k * x
            if i % 9 == 0:
                k += 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumKeypresses(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        Arrays.sort(cnt);
        int ans = 0, k = 1;
        for (int i = 1; i <= 26; ++i) {
            ans += k * cnt[26 - i];
            if (i % 9 == 0) {
                ++k;
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
    int minimumKeypresses(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        sort(begin(cnt), end(cnt), greater<int>());
        int ans = 0, k = 1;
        for (int i = 1; i <= 26; ++i) {
            ans += k * cnt[i - 1];
            if (i % 9 == 0) {
                ++k;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumKeypresses(s string) (ans int) {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Ints(cnt)
	k := 1
	for i := 1; i <= 26; i++ {
		ans += k * cnt[26-i]
		if i%9 == 0 {
			k++
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumKeypresses(s: string): number {
    const cnt: number[] = Array(26).fill(0);
    const a = 'a'.charCodeAt(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - a];
    }
    cnt.sort((a, b) => b - a);
    let [ans, k] = [0, 1];
    for (let i = 1; i <= 26; ++i) {
        ans += k * cnt[i - 1];
        if (i % 9 === 0) {
            ++k;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
