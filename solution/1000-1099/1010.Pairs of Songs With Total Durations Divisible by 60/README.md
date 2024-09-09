---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1010.Pairs%20of%20Songs%20With%20Total%20Durations%20Divisible%20by%2060/README.md
rating: 1377
source: 第 128 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [1010. 总持续时间可被 60 整除的歌曲](https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60)

[English Version](/solution/1000-1099/1010.Pairs%20of%20Songs%20With%20Total%20Durations%20Divisible%20by%2060/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在歌曲列表中，第 <code>i</code> 首歌曲的持续时间为 <code>time[i]</code> 秒。</p>

<p>返回其总持续时间（以秒为单位）可被 <code>60</code> 整除的歌曲对的数量。形式上，我们希望下标数字 <code>i</code> 和 <code>j</code> 满足&nbsp; <code>i &lt; j</code> 且有&nbsp;<code>(time[i] + time[j]) % 60 == 0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>time = [30,20,150,100,40]
<strong>输出：</strong>3
<strong>解释：</strong>这三对的总持续时间可被 60 整除：
(time[0] = 30, time[2] = 150): 总持续时间 180
(time[1] = 20, time[3] = 100): 总持续时间 120
(time[1] = 20, time[4] = 40): 总持续时间 60
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>time = [60,60,60]
<strong>输出：</strong>3
<strong>解释：</strong>所有三对的总持续时间都是 120，可以被 60 整除。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 6 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= time[i] &lt;= 500</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学 + 计数

如果一个数对 $(a, b)$ 之和能被 $60$ 整除，即 $(a + b) \bmod 60 = 0$，那么 $(a \bmod 60 + b \bmod 60) \bmod 60 = 0$，不妨记 $x=a \bmod 60$, $y = b \bmod 60$，那么有 $(x + y) \bmod 60 = 0$，即 $y=(60 - x) \bmod 60$。

因此，我们可以遍历歌曲列表，用一个长度为 $60$ 的数组 $cnt$ 记录每个余数 $x$ 出现的次数。对于当前的 $x$，如果数组 $cnt$ 中存在余数 $y = (60 - x) \bmod 60$，那么将 $cnt[y]$ 累加进答案中。然后，将 $x$ 在数组 $cnt$ 中的出现次数加 $1$。继续遍历，直到遍历完整个歌曲列表。

遍历结束后，即可得到满足条件的歌曲对数目。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是歌曲列表的长度；而 $C$ 是余数的可能取值，这里 $C=60$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        cnt = Counter(t % 60 for t in time)
        ans = sum(cnt[x] * cnt[60 - x] for x in range(1, 30))
        ans += cnt[0] * (cnt[0] - 1) // 2
        ans += cnt[30] * (cnt[30] - 1) // 2
        return ans
```

#### Java

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] cnt = new int[60];
        for (int t : time) {
            ++cnt[t % 60];
        }
        int ans = 0;
        for (int x = 1; x < 30; ++x) {
            ans += cnt[x] * cnt[60 - x];
        }
        ans += (long) cnt[0] * (cnt[0] - 1) / 2;
        ans += (long) cnt[30] * (cnt[30] - 1) / 2;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        int cnt[60]{};
        for (int& t : time) {
            ++cnt[t % 60];
        }
        int ans = 0;
        for (int x = 1; x < 30; ++x) {
            ans += cnt[x] * cnt[60 - x];
        }
        ans += 1LL * cnt[0] * (cnt[0] - 1) / 2;
        ans += 1LL * cnt[30] * (cnt[30] - 1) / 2;
        return ans;
    }
};
```

#### Go

```go
func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [60]int{}
	for _, t := range time {
		cnt[t%60]++
	}
	for x := 1; x < 30; x++ {
		ans += cnt[x] * cnt[60-x]
	}
	ans += cnt[0] * (cnt[0] - 1) / 2
	ans += cnt[30] * (cnt[30] - 1) / 2
	return
}
```

#### TypeScript

```ts
function numPairsDivisibleBy60(time: number[]): number {
    const cnt: number[] = new Array(60).fill(0);
    for (const t of time) {
        ++cnt[t % 60];
    }
    let ans = 0;
    for (let x = 1; x < 30; ++x) {
        ans += cnt[x] * cnt[60 - x];
    }
    ans += (cnt[0] * (cnt[0] - 1)) / 2;
    ans += (cnt[30] * (cnt[30] - 1)) / 2;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        cnt = Counter()
        ans = 0
        for x in time:
            x %= 60
            y = (60 - x) % 60
            ans += cnt[y]
            cnt[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] cnt = new int[60];
        int ans = 0;
        for (int x : time) {
            x %= 60;
            int y = (60 - x) % 60;
            ans += cnt[y];
            ++cnt[x];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        int cnt[60]{};
        int ans = 0;
        for (int x : time) {
            x %= 60;
            int y = (60 - x) % 60;
            ans += cnt[y];
            ++cnt[x];
        }
        return ans;
    }
};
```

#### Go

```go
func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [60]int{}
	for _, x := range time {
		x %= 60
		y := (60 - x) % 60
		ans += cnt[y]
		cnt[x]++
	}
	return
}
```

#### TypeScript

```ts
function numPairsDivisibleBy60(time: number[]): number {
    const cnt: number[] = new Array(60).fill(0);
    let ans: number = 0;
    for (let x of time) {
        x %= 60;
        const y = (60 - x) % 60;
        ans += cnt[y];
        ++cnt[x];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
