---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0493.Reverse%20Pairs/README.md
tags:
    - 树状数组
    - 线段树
    - 数组
    - 二分查找
    - 分治
    - 有序集合
    - 归并排序
---

<!-- problem:start -->

# [493. 翻转对](https://leetcode.cn/problems/reverse-pairs)

[English Version](/solution/0400-0499/0493.Reverse%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个数组&nbsp;<code>nums</code>&nbsp;，如果&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>nums[i] &gt; 2*nums[j]</code>&nbsp;我们就将&nbsp;<code>(i, j)</code>&nbsp;称作一个<strong><em>重要翻转对</em></strong>。</p>

<p>你需要返回给定数组中的重要翻转对的数量。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入</strong>: [1,3,2,3,1]
<strong>输出</strong>: 2
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入</strong>: [2,4,3,5,1]
<strong>输出</strong>: 3
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>给定数组的长度不会超过<code>50000</code>。</li>
	<li>输入数组中的所有数字都在32位整数的表示范围内。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：归并排序

归并排序的过程中，如果左边的数大于右边的数，则右边的数与左边的数之后的数都构成逆序对。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        def merge_sort(l, r):
            if l >= r:
                return 0
            mid = (l + r) >> 1
            ans = merge_sort(l, mid) + merge_sort(mid + 1, r)
            t = []
            i, j = l, mid + 1
            while i <= mid and j <= r:
                if nums[i] <= 2 * nums[j]:
                    i += 1
                else:
                    ans += mid - i + 1
                    j += 1
            i, j = l, mid + 1
            while i <= mid and j <= r:
                if nums[i] <= nums[j]:
                    t.append(nums[i])
                    i += 1
                else:
                    t.append(nums[j])
                    j += 1
            t.extend(nums[i : mid + 1])
            t.extend(nums[j : r + 1])
            nums[l : r + 1] = t
            return ans

        return merge_sort(0, len(nums) - 1)
```

#### Java

```java
class Solution {
    private int[] nums;
    private int[] t;

    public int reversePairs(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        this.t = new int[n];
        return mergeSort(0, n - 1);
    }

    private int mergeSort(int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) >> 1;
        int ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j] * 2L) {
                ++i;
            } else {
                ans += mid - i + 1;
                ++j;
            }
        }
        i = l;
        j = mid + 1;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                t[k++] = nums[i++];
            } else {
                t[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            t[k++] = nums[i++];
        }
        while (j <= r) {
            t[k++] = nums[j++];
        }
        for (i = l; i <= r; ++i) {
            nums[i] = t[i - l];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int reversePairs(vector<int>& nums) {
        int n = nums.size();
        int t[n];
        function<int(int, int)> mergeSort = [&](int l, int r) -> int {
            if (l >= r) {
                return 0;
            }
            int mid = (l + r) >> 1;
            int ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
            int i = l, j = mid + 1, k = 0;
            while (i <= mid && j <= r) {
                if (nums[i] <= nums[j] * 2LL) {
                    ++i;
                } else {
                    ans += mid - i + 1;
                    ++j;
                }
            }
            i = l;
            j = mid + 1;
            while (i <= mid && j <= r) {
                if (nums[i] <= nums[j]) {
                    t[k++] = nums[i++];
                } else {
                    t[k++] = nums[j++];
                }
            }
            while (i <= mid) {
                t[k++] = nums[i++];
            }
            while (j <= r) {
                t[k++] = nums[j++];
            }
            for (i = l; i <= r; ++i) {
                nums[i] = t[i - l];
            }
            return ans;
        };
        return mergeSort(0, n - 1);
    }
};
```

#### Go

```go
func reversePairs(nums []int) int {
	n := len(nums)
	t := make([]int, n)
	var mergeSort func(l, r int) int
	mergeSort = func(l, r int) int {
		if l >= r {
			return 0
		}
		mid := (l + r) >> 1
		ans := mergeSort(l, mid) + mergeSort(mid+1, r)
		i, j, k := l, mid+1, 0
		for i <= mid && j <= r {
			if nums[i] <= nums[j]*2 {
				i++
			} else {
				ans += mid - i + 1
				j++
			}
		}
		i, j = l, mid+1
		for i <= mid && j <= r {
			if nums[i] <= nums[j] {
				t[k] = nums[i]
				k, i = k+1, i+1
			} else {
				t[k] = nums[j]
				k, j = k+1, j+1
			}
		}
		for ; i <= mid; i, k = i+1, k+1 {
			t[k] = nums[i]
		}
		for ; j <= r; j, k = j+1, k+1 {
			t[k] = nums[j]
		}
		for i = l; i <= r; i++ {
			nums[i] = t[i-l]
		}
		return ans
	}
	return mergeSort(0, n-1)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：树状数组

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。

树状数组最基本的功能就是求比某点 x 小的点的个数（这里的比较是抽象的概念，可以是数的大小、坐标的大小、质量的大小等等）。

比如给定数组 `a[5] = {2, 5, 3, 4, 1}`，求 `b[i] = 位置 i 左边小于等于 a[i] 的数的个数`。对于此例，`b[5] = {0, 1, 1, 2, 0}`。

解决方案是直接遍历数组，每个位置先求出 `query(a[i])`，然后再修改树状数组 `update(a[i], 1)` 即可。当数的范围比较大时，需要进行离散化，即先进行去重并排序，然后对每个数字进行编号。

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        s = set()
        for num in nums:
            s.add(num)
            s.add(num * 2)
        alls = sorted(s)
        m = {v: i for i, v in enumerate(alls, 1)}
        ans = 0
        tree = BinaryIndexedTree(len(m))
        for num in nums[::-1]:
            ans += tree.query(m[num] - 1)
            tree.update(m[num * 2], 1)
        return ans
```

#### Java

```java
class Solution {
    public int reversePairs(int[] nums) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int num : nums) {
            ts.add((long) num);
            ts.add((long) num * 2);
        }
        Map<Long, Integer> m = new HashMap<>();
        int idx = 0;
        for (long num : ts) {
            m.put(num, ++idx);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            int x = m.get((long) nums[i]);
            ans += tree.query(x - 1);
            tree.update(m.get((long) nums[i] * 2), 1);
        }
        return ans;
    }
}

class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class Solution {
public:
    int reversePairs(vector<int>& nums) {
        set<long long> s;
        for (int num : nums) {
            s.insert(num);
            s.insert(num * 2ll);
        }
        unordered_map<long long, int> m;
        int idx = 0;
        for (long long num : s) m[num] = ++idx;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int ans = 0;
        for (int i = nums.size() - 1; i >= 0; --i) {
            ans += tree->query(m[nums[i]] - 1);
            tree->update(m[nums[i] * 2ll], 1);
        }
        return ans;
    }
};
```

#### Go

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func reversePairs(nums []int) int {
	s := make(map[int]bool)
	for _, num := range nums {
		s[num] = true
		s[num*2] = true
	}
	var alls []int
	for num := range s {
		alls = append(alls, num)
	}
	sort.Ints(alls)
	m := make(map[int]int)
	for i, num := range alls {
		m[num] = i + 1
	}
	tree := newBinaryIndexedTree(len(m))
	ans := 0
	for i := len(nums) - 1; i >= 0; i-- {
		ans += tree.query(m[nums[i]] - 1)
		tree.update(m[nums[i]*2], 1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：线段树

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 `log(width)`。更新某个元素的值，只需要更新 `log(width)` 个区间，并且这些区间都包含在一个包含该元素的大区间内。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 `[1, N]`；
-   线段树的每个叶子节点代表一个长度为 1 的元区间 `[x, x]`；
-   对于每个内部节点 `[l, r]`，它的左儿子是 `[l, mid]`，右儿子是 `[mid + 1, r]`, 其中 `mid = ⌊(l + r) / 2⌋` (即向下取整)。

<!-- tabs:start -->

#### Python3

```python
class Node:
    def __init__(self):
        self.l = 0
        self.r = 0
        self.v = 0


class SegmentTree:
    def __init__(self, n):
        self.tr = [Node() for _ in range(4 * n)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l = l
        self.tr[u].r = r
        if l == r:
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)

    def modify(self, u, x, v):
        if self.tr[u].l == x and self.tr[u].r == x:
            self.tr[u].v += 1
            return
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def pushup(self, u):
        self.tr[u].v = self.tr[u << 1].v + self.tr[u << 1 | 1].v

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].v
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        v = 0
        if l <= mid:
            v += self.query(u << 1, l, r)
        if r > mid:
            v += self.query(u << 1 | 1, l, r)
        return v


class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        s = set()
        for num in nums:
            s.add(num)
            s.add(num * 2)
        alls = sorted(s)
        m = {v: i for i, v in enumerate(alls, 1)}
        tree = SegmentTree(len(m))
        ans = 0
        for v in nums[::-1]:
            x = m[v]
            ans += tree.query(1, 1, x - 1)
            tree.modify(1, m[v * 2], 1)
        return ans
```

#### Java

```java
class Solution {
    public int reversePairs(int[] nums) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int num : nums) {
            ts.add((long) num);
            ts.add((long) num * 2);
        }
        Map<Long, Integer> m = new HashMap<>();
        int idx = 0;
        for (long num : ts) {
            m.put(num, ++idx);
        }
        SegmentTree tree = new SegmentTree(m.size());
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            int x = m.get((long) nums[i]);
            ans += tree.query(1, 1, x - 1);
            tree.modify(1, m.get((long) nums[i] * 2), 1);
        }
        return ans;
    }
}

class Node {
    int l;
    int r;
    int v;
}

class SegmentTree {
    private Node[] tr;

    public SegmentTree(int n) {
        tr = new Node[4 * n];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    public void modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v += v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    public void pushup(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].v;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int v = 0;
        if (l <= mid) {
            v += query(u << 1, l, r);
        }
        if (r > mid) {
            v += query(u << 1 | 1, l, r);
        }
        return v;
    }
}
```

#### C++

```cpp
class Node {
public:
    int l;
    int r;
    int v;
};

class SegmentTree {
public:
    vector<Node*> tr;

    SegmentTree(int n) {
        tr.resize(4 * n);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    void modify(int u, int x, int v) {
        if (tr[u]->l == x && tr[u]->r == x) {
            tr[u]->v += v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid)
            modify(u << 1, x, v);
        else
            modify(u << 1 | 1, x, v);
        pushup(u);
    }

    void pushup(int u) {
        tr[u]->v = tr[u << 1]->v + tr[u << 1 | 1]->v;
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) return tr[u]->v;
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int v = 0;
        if (l <= mid) v = query(u << 1, l, r);
        if (r > mid) v += query(u << 1 | 1, l, r);
        return v;
    }
};

class Solution {
public:
    int reversePairs(vector<int>& nums) {
        set<long long> s;
        for (int num : nums) {
            s.insert(num);
            s.insert(num * 2ll);
        }
        unordered_map<long long, int> m;
        int idx = 0;
        for (long long num : s) m[num] = ++idx;
        SegmentTree* tree = new SegmentTree(m.size());
        int ans = 0;
        for (int i = nums.size() - 1; i >= 0; --i) {
            ans += tree->query(1, 1, m[nums[i]] - 1);
            tree->modify(1, m[nums[i] * 2ll], 1);
        }
        return ans;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
