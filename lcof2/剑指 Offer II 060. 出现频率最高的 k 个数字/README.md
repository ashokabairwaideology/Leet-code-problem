---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20060.%20%E5%87%BA%E7%8E%B0%E9%A2%91%E7%8E%87%E6%9C%80%E9%AB%98%E7%9A%84%20k%20%E4%B8%AA%E6%95%B0%E5%AD%97/README.md
---

<!-- problem:start -->

# [剑指 Offer II 060. 出现频率最高的 k 个数字](https://leetcode.cn/problems/g5c51o)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>nums</code> 和一个整数 <code>k</code>&nbsp;，请返回其中出现频率前 <code>k</code> 高的元素。可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>nums = [1,1,1,2,2,3], k = 2
<strong>输出: </strong>[1,2]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>nums = [1], k = 1
<strong>输出: </strong>[1]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>k</code> 的取值范围是 <code>[1, 数组中不相同的元素的个数]</code></li>
	<li>题目数据保证答案唯一，换句话说，数组中前 <code>k</code> 个高频元素的集合是唯一的</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>所设计算法的时间复杂度 <strong>必须</strong> 优于 <code>O(n log n)</code> ，其中 <code>n</code><em>&nbsp;</em>是数组大小。</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 347&nbsp;题相同：<a href="https://leetcode.cn/problems/top-k-frequent-elements/">https://leetcode.cn/problems/top-k-frequent-elements/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 优先队列（小根堆）

使用哈希表统计每个元素出现的次数，然后使用优先队列（小根堆）维护前 $k$ 个出现次数最多的元素。

时间复杂度 $O(n\log k)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums)
        return [v[0] for v in cnt.most_common(k)]
```

#### Java

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> frequency = Arrays.stream(nums).boxed().collect(
            Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Queue<Map.Entry<Integer, Long>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (var entry : frequency.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.stream().mapToInt(Map.Entry::getKey).toArray();
    }
}
```

#### C++

```cpp
using pii = pair<int, int>;

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int v : nums) ++cnt[v];
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        for (auto& [num, freq] : cnt) {
            pq.push({freq, num});
            if (pq.size() > k) {
                pq.pop();
            }
        }
        vector<int> ans(k);
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.top().second;
            pq.pop();
        }
        return ans;
    }
};
```

#### Go

```go
func topKFrequent(nums []int, k int) []int {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	h := hp{}
	for v, freq := range cnt {
		heap.Push(&h, pair{v, freq})
		if len(h) > k {
			heap.Pop(&h)
		}
	}
	ans := make([]int, k)
	for i := range ans {
		ans[i] = heap.Pop(&h).(pair).v
	}
	return ans
}

type pair struct{ v, cnt int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].cnt < h[j].cnt }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function topKFrequent(nums: number[], k: number): number[] {
    let hashMap = new Map();
    for (let num of nums) {
        hashMap.set(num, (hashMap.get(num) || 0) + 1);
    }
    let list = [...hashMap];
    list.sort((a, b) => b[1] - a[1]);
    let ans = [];
    for (let i = 0; i < k; i++) {
        ans.push(list[i][0]);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;
impl Solution {
    pub fn top_k_frequent(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut map = HashMap::new();
        let mut max_count = 0;
        for &num in nums.iter() {
            let val = map.get(&num).unwrap_or(&0) + 1;
            map.insert(num, val);
            max_count = max_count.max(val);
        }
        let mut k = k as usize;
        let mut res = vec![0; k];
        while k > 0 {
            let mut next = 0;
            for key in map.keys() {
                let val = map[key];
                if val == max_count {
                    res[k - 1] = *key;
                    k -= 1;
                } else if val < max_count {
                    next = next.max(val);
                }
            }
            max_count = next;
        }
        res
    }
}
```

#### Swift

```swift
import HeapModule

class Solution {
    func topKFrequent(_ nums: [Int], _ k: Int) -> [Int] {
        var frequency: [Int: Int] = [:]
        for num in nums {
            frequency[num, default: 0] += 1
        }

        var freqHeap = Heap<FreqElement>()
        for (key, value) in frequency {
            freqHeap.insert(.init(val: key, freq: value))
            if freqHeap.count > k {
                freqHeap.removeMin()
            }
        }
        var ans = [Int]()
        while let element = freqHeap.popMax() {
            ans.append(element.val)
        }
        return ans
    }
}

struct FreqElement: Comparable {
    let val: Int
    let freq: Int

    static func < (lhs: FreqElement, rhs: FreqElement) -> Bool {
        lhs.freq < rhs.freq
    }

    static func == (lhs: FreqElement, rhs: FreqElement) -> Bool {
        lhs.freq == rhs.freq
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums)
        hp = []
        for num, freq in cnt.items():
            heappush(hp, (freq, num))
            if len(hp) > k:
                heappop(hp)
        return [v[1] for v in hp]
```

#### Java

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (var e : cnt.entrySet()) {
            pq.offer(new int[] {e.getKey(), e.getValue()});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}
```

#### TypeScript

```ts
function topKFrequent(nums: number[], k: number): number[] {
    const map = new Map<number, number>();
    let maxCount = 0;
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
        maxCount = Math.max(maxCount, map.get(num));
    }

    const res = [];
    while (k > 0) {
        for (const key of map.keys()) {
            if (map.get(key) === maxCount) {
                res.push(key);
                k--;
            }
        }
        maxCount--;
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
