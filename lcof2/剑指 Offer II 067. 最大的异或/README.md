---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20067.%20%E6%9C%80%E5%A4%A7%E7%9A%84%E5%BC%82%E6%88%96/README.md
---

<!-- problem:start -->

# [剑指 Offer II 067. 最大的异或](https://leetcode.cn/problems/ms70jA)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>nums</code> ，返回<em> </em><code>nums[i] XOR nums[j]</code> 的最大运算结果，其中 <code>0 &le; i &le; j &lt; n</code> 。</p>

<p>&nbsp;</p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,10,5,25,2,8]
<strong>输出：</strong>28
<strong>解释：</strong>最大运算结果是 5 XOR 25 = 28.</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4]
<strong>输出：</strong>6
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums = [8,10,2]
<strong>输出：</strong>10
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>nums = [14,70,53,83,49,91,36,80,92,51,66,70]
<strong>输出：</strong>127
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>
</div>
</div>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以在 <code>O(n)</code> 的时间解决这个问题吗？</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 421&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/">https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        max = 0
        mask = 0
        for i in range(30, -1, -1):
            current = 1 << i
            # 期望的二进制前缀
            mask = mask ^ current
            # 在当前前缀下, 数组内的前缀位数所有情况集合
            s = set()
            for num in nums:
                s.add(num & mask)
            # 期望最终异或值的从右数第i位为1, 再根据异或运算的特性推算假设是否成立
            flag = max | current
            for prefix in s:
                if prefix ^ flag in s:
                    max = flag
                    break
        return max
```

#### Java

```java
class Solution {

    public int findMaximumXOR(int[] numbers) {
        int max = 0;
        int mask = 0;
        for (int i = 30; i >= 0; i--) {
            int current = 1 << i;
            // 期望的二进制前缀
            mask = mask ^ current;
            // 在当前前缀下, 数组内的前缀位数所有情况集合
            Set<Integer> set = new HashSet<>();
            for (int j = 0, k = numbers.length; j < k; j++) {
                set.add(mask & numbers[j]);
            }
            // 期望最终异或值的从右数第i位为1, 再根据异或运算的特性推算假设是否成立
            int flag = max | current;
            for (Integer prefix : set) {
                if (set.contains(prefix ^ flag)) {
                    max = flag;
                    break;
                }
            }
        }
        return max;
    }
}
```

#### C++

```cpp
class Trie {
public:
    vector<Trie*> children;
    string v;
    Trie()
        : children(2) {}

    void insert(int x) {
        Trie* node = this;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (!node->children[v]) node->children[v] = new Trie();
            node = node->children[v];
        }
    }

    int search(int x) {
        Trie* node = this;
        int res = 0;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (node->children[v ^ 1]) {
                res = res << 1 | 1;
                node = node->children[v ^ 1];
            } else {
                res <<= 1;
                node = node->children[v];
            }
        }
        return res;
    }
};

class Solution {
public:
    int findMaximumXOR(vector<int>& nums) {
        Trie* trie = new Trie();
        int ans = 0;
        for (int v : nums) {
            trie->insert(v);
            ans = max(ans, trie->search(v));
        }
        return ans;
    }
};
```

#### Go

```go
type Trie struct {
	children [26]*Trie
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(x int) {
	node := this
	for i := 30; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
		}
		node = node.children[v]
	}
}

func (this *Trie) search(x int) int {
	node := this
	res := 0
	for i := 30; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v^1] != nil {
			res = res<<1 | 1
			node = node.children[v^1]
		} else {
			res <<= 1
			node = node.children[v]
		}
	}
	return res
}

func findMaximumXOR(nums []int) int {
	trie := newTrie()
	ans := 0
	for _, v := range nums {
		trie.insert(v)
		ans = max(ans, trie.search(v))
	}
	return ans
}
```

#### Swift

```swift
class Solution {
    func findMaximumXOR(_ numbers: [Int]) -> Int {
        var max = 0
        var mask = 0

        for i in stride(from: 30, through: 0, by: -1) {
            let current = 1 << i
            mask ^= current
            var set = Set<Int>()
            for num in numbers {
                set.insert(mask & num)
            }
            let flag = max | current
            for prefix in set {
                if set.contains(prefix ^ flag) {
                    max = flag
                    break
                }
            }
        }
        return max
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：前缀树

题目是求两个元素的异或最大值，可以从最高位开始考虑。

我们把数组中的每个元素 $x$ 看作一个 $32$ 位的 $01$ 串，按二进制从高位到低位的顺序，插入前缀树（最低位为叶子节点）。

搜索 $x$ 时，尽量走相反的 $01$ 字符指针的策略，因为异或运算的法则是相同得 $0$，不同得 $1$，所以我们尽可能往与 $x$ 当前位相反的字符方向走，才能得到能和 $x$ 产生最大值的结果。

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children = [None] * 2

    def insert(self, x):
        node = self
        for i in range(30, -1, -1):
            v = (x >> i) & 1
            if node.children[v] is None:
                node.children[v] = Trie()
            node = node.children[v]

    def search(self, x):
        node = self
        res = 0
        for i in range(30, -1, -1):
            v = (x >> i) & 1
            if node.children[v ^ 1]:
                res = res << 1 | 1
                node = node.children[v ^ 1]
            else:
                res <<= 1
                node = node.children[v]
        return res


class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        trie = Trie()
        for v in nums:
            trie.insert(v)
        return max(trie.search(v) for v in nums)
```

#### Java

```java
class Trie {
    Trie[] children = new Trie[2];

    void insert(int x) {
        Trie node = this;
        for (int i = 30; i >= 0; --i) {
            int v = (x >> i) & 1;
            if (node.children[v] == null) {
                node.children[v] = new Trie();
            }
            node = node.children[v];
        }
    }

    int search(int x) {
        Trie node = this;
        int res = 0;
        for (int i = 30; i >= 0; --i) {
            int v = (x >> i) & 1;
            if (node.children[v ^ 1] != null) {
                res = res << 1 | 1;
                node = node.children[v ^ 1];
            } else {
                res <<= 1;
                node = node.children[v];
            }
        }
        return res;
    }
}

class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int ans = 0;
        for (int v : nums) {
            trie.insert(v);
            ans = Math.max(ans, trie.search(v));
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
