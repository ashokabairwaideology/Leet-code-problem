---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2456.Most%20Popular%20Video%20Creator/README.md
rating: 1548
source: 第 317 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 字符串
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2456. 最流行的视频创作者](https://leetcode.cn/problems/most-popular-video-creator)

[English Version](/solution/2400-2499/2456.Most%20Popular%20Video%20Creator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串数组 <code>creators</code> 和 <code>ids</code> ，和一个整数数组 <code>views</code> ，所有数组的长度都是 <code>n</code> 。平台上第 <code>i</code> 个视频者是&nbsp;<code>creator[i]</code> ，视频分配的 id 是 <code>ids[i]</code> ，且播放量为 <code>views[i]</code> 。</p>

<p>视频创作者的 <strong>流行度</strong> 是该创作者的 <strong>所有</strong> 视频的播放量的 <strong>总和</strong> 。请找出流行度 <strong>最高</strong> 创作者以及该创作者播放量 <strong>最大</strong> 的视频的 id 。</p>

<ul>
	<li>如果存在多个创作者流行度都最高，则需要找出所有符合条件的创作者。</li>
	<li>如果某个创作者存在多个播放量最高的视频，则只需要找出字典序最小的 <code>id</code> 。</li>
</ul>

<p>返回一个二维字符串数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer[i] = [creator<sub>i</sub>, id<sub>i</sub>]</code><em> </em>表示<em> </em><code>creator<sub>i</sub></code> 的流行度 <strong>最高</strong> 且其最流行的视频 id 是<em> </em><code>id<sub>i</sub></code><em> </em>，可以按任何顺序返回该结果<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
<strong>输出：</strong>[["alice","one"],["bob","two"]]
<strong>解释：</strong>
alice 的流行度是 5 + 5 = 10 。
bob 的流行度是 10 。
chris 的流行度是 4 。
alice 和 bob 是流行度最高的创作者。
bob 播放量最高的视频 id 为 "two" 。
alice 播放量最高的视频 id 是 "one" 和 "three" 。由于 "one" 的字典序比 "three" 更小，所以结果中返回的 id 是 "one" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
<strong>输出：</strong>[["alice","b"]]
<strong>解释：</strong>
id 为 "b" 和 "c" 的视频都满足播放量最高的条件。
由于 "b" 的字典序比 "c" 更小，所以结果中返回的 id 是 "b" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == creators.length == ids.length == views.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= creators[i].length, ids[i].length &lt;= 5</code></li>
	<li><code>creators[i]</code> 和 <code>ids[i]</code> 仅由小写英文字母组成</li>
	<li><code>0 &lt;= views[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们遍历三个数组，用哈希表 $cnt$ 统计每个创作者的播放量总和，用哈希表 $d$ 记录每个创作者播放量最大的视频的下标。

然后，我们遍历哈希表 $cnt$，找出最大的播放量 $mx$；接着再次遍历哈希表 $cnt$，找出播放量为 $mx$ 的创作者，将其加入答案数组中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为视频数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mostPopularCreator(
        self, creators: List[str], ids: List[str], views: List[int]
    ) -> List[List[str]]:
        cnt = defaultdict(int)
        d = defaultdict(int)
        for k, (c, i, v) in enumerate(zip(creators, ids, views)):
            cnt[c] += v
            if c not in d or views[d[c]] < v or (views[d[c]] == v and ids[d[c]] > i):
                d[c] = k
        mx = max(cnt.values())
        return [[c, ids[d[c]]] for c, x in cnt.items() if x == mx]
```

#### Java

```java
class Solution {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        int n = ids.length;
        Map<String, Long> cnt = new HashMap<>(n);
        Map<String, Integer> d = new HashMap<>(n);
        for (int k = 0; k < n; ++k) {
            String c = creators[k], i = ids[k];
            long v = views[k];
            cnt.merge(c, v, Long::sum);
            if (!d.containsKey(c) || views[d.get(c)] < v
                || (views[d.get(c)] == v && ids[d.get(c)].compareTo(i) > 0)) {
                d.put(c, k);
            }
        }
        long mx = 0;
        for (long x : cnt.values()) {
            mx = Math.max(mx, x);
        }
        List<List<String>> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            if (e.getValue() == mx) {
                String c = e.getKey();
                ans.add(List.of(c, ids[d.get(c)]));
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
    vector<vector<string>> mostPopularCreator(vector<string>& creators, vector<string>& ids, vector<int>& views) {
        unordered_map<string, long long> cnt;
        unordered_map<string, int> d;
        int n = ids.size();
        for (int k = 0; k < n; ++k) {
            auto c = creators[k], id = ids[k];
            int v = views[k];
            cnt[c] += v;
            if (!d.count(c) || views[d[c]] < v || (views[d[c]] == v && ids[d[c]] > id)) {
                d[c] = k;
            }
        }
        long long mx = 0;
        for (auto& [_, x] : cnt) {
            mx = max(mx, x);
        }
        vector<vector<string>> ans;
        for (auto& [c, x] : cnt) {
            if (x == mx) {
                ans.push_back({c, ids[d[c]]});
            }
        }
        return ans;
    }
};
```

#### Go

```go
func mostPopularCreator(creators []string, ids []string, views []int) (ans [][]string) {
	cnt := map[string]int{}
	d := map[string]int{}
	for k, c := range creators {
		i, v := ids[k], views[k]
		cnt[c] += v
		if j, ok := d[c]; !ok || views[j] < v || (views[j] == v && ids[j] > i) {
			d[c] = k
		}
	}
	mx := 0
	for _, x := range cnt {
		if mx < x {
			mx = x
		}
	}
	for c, x := range cnt {
		if x == mx {
			ans = append(ans, []string{c, ids[d[c]]})
		}
	}
	return
}
```

#### TypeScript

```ts
function mostPopularCreator(creators: string[], ids: string[], views: number[]): string[][] {
    const cnt: Map<string, number> = new Map();
    const d: Map<string, number> = new Map();
    const n = ids.length;
    for (let k = 0; k < n; ++k) {
        const [c, i, v] = [creators[k], ids[k], views[k]];
        cnt.set(c, (cnt.get(c) ?? 0) + v);
        if (!d.has(c) || views[d.get(c)!] < v || (views[d.get(c)!] === v && ids[d.get(c)!] > i)) {
            d.set(c, k);
        }
    }
    const mx = Math.max(...cnt.values());
    const ans: string[][] = [];
    for (const [c, x] of cnt) {
        if (x === mx) {
            ans.push([c, ids[d.get(c)!]]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
