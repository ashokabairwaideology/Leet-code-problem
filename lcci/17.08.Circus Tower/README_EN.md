---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.08.Circus%20Tower/README_EN.md
---

<!-- problem:start -->

# [17.08. Circus Tower](https://leetcode.cn/problems/circus-tower-lcci)

[中文文档](/lcci/17.08.Circus%20Tower/README.md)

## Description

<!-- description:start -->

<p>A circus is designing a tower routine consisting of people standing atop one anoth&shy;er&#39;s shoulders. For practical and aesthetic reasons, each person must be both shorter and lighter than the person below him or her. Given the heights and weights of each person in the circus, write a method to compute the largest possible number of people in such a tower.</p>
<p><strong>Example: </strong></p>
<pre>

<strong>Input: </strong>height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]

<strong>Output: </strong>6

<strong>Explanation: </strong>The longest tower is length 6 and includes from top to bottom: (56,90), (60,95), (65,100), (68,110), (70,150), (75,190)</pre>

<p>Note:</p>
<ul>
	<li><code>height.length == weight.length &lt;= 10000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Discretization + Binary Indexed Tree

First, we sort all people in ascending order by height. If the heights are the same, we sort them in descending order by weight. This way, we can transform the problem into finding the longest increasing subsequence of the weight array.

The longest increasing subsequence problem can be solved using dynamic programming with a time complexity of $O(n^2)$. However, we can optimize the solution process using a Binary Indexed Tree, which reduces the time complexity to $O(n \log n)$.

The space complexity is $O(n)$, where $n$ is the number of people.

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] = max(self.c[x], delta)
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s = max(s, self.c[x])
            x -= x & -x
        return s


class Solution:
    def bestSeqAtIndex(self, height: List[int], weight: List[int]) -> int:
        arr = list(zip(height, weight))
        arr.sort(key=lambda x: (x[0], -x[1]))
        alls = sorted({w for _, w in arr})
        m = {w: i for i, w in enumerate(alls, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 1
        for _, w in arr:
            x = m[w]
            t = tree.query(x - 1) + 1
            ans = max(ans, t)
            tree.update(x, t)
        return ans
```

#### Java

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int val) {
        while (x <= n) {
            this.c[x] = Math.max(this.c[x], val);
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, this.c[x]);
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int n = height.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {height[i], weight[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        Set<Integer> s = new HashSet<>();
        for (int[] e : arr) {
            s.add(e[1]);
        }
        List<Integer> alls = new ArrayList<>(s);
        Collections.sort(alls);
        Map<Integer, Integer> m = new HashMap<>(alls.size());
        for (int i = 0; i < alls.size(); ++i) {
            m.put(alls.get(i), i + 1);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(alls.size());
        int ans = 1;
        for (int[] e : arr) {
            int x = m.get(e[1]);
            int t = tree.query(x - 1) + 1;
            ans = Math.max(ans, t);
            tree.update(x, t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int val) {
        while (x <= n) {
            c[x] = max(c[x], val);
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s = max(s, c[x]);
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int bestSeqAtIndex(vector<int>& height, vector<int>& weight) {
        int n = height.size();
        vector<pair<int, int>> people;
        for (int i = 0; i < n; ++i) {
            people.emplace_back(height[i], weight[i]);
        }
        sort(people.begin(), people.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
            if (a.first == b.first) {
                return a.second > b.second;
            }
            return a.first < b.first;
        });
        vector<int> alls = weight;
        sort(alls.begin(), alls.end());
        alls.erase(unique(alls.begin(), alls.end()), alls.end());
        BinaryIndexedTree tree(alls.size());
        int ans = 1;
        for (auto& [_, w] : people) {
            int x = lower_bound(alls.begin(), alls.end(), w) - alls.begin() + 1;
            int t = tree.query(x - 1) + 1;
            ans = max(ans, t);
            tree.update(x, t);
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

func (this *BinaryIndexedTree) update(x, val int) {
	for x <= this.n {
		if this.c[x] < val {
			this.c[x] = val
		}
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		if s < this.c[x] {
			s = this.c[x]
		}
		x -= x & -x
	}
	return s
}

func bestSeqAtIndex(height []int, weight []int) int {
	n := len(height)
	people := make([][2]int, n)
	s := map[int]bool{}
	for i := range people {
		people[i] = [2]int{height[i], weight[i]}
		s[weight[i]] = true
	}
	sort.Slice(people, func(i, j int) bool {
		a, b := people[i], people[j]
		return a[0] < b[0] || a[0] == b[0] && a[1] > b[1]
	})
	alls := make([]int, 0, len(s))
	for k := range s {
		alls = append(alls, k)
	}
	sort.Ints(alls)
	tree := newBinaryIndexedTree(len(alls))
	ans := 1
	for _, p := range people {
		x := sort.SearchInts(alls, p[1]) + 1
		t := tree.query(x-1) + 1
		ans = max(ans, t)
		tree.update(x, t)
	}
	return ans
}
```

#### Swift

```swift
class BinaryIndexedTree {
    private var n: Int
    private var c: [Int]

    init(_ n: Int) {
        self.n = n
        self.c = [Int](repeating: 0, count: n + 1)
    }

    func update(_ x: Int, _ val: Int) {
        var x = x
        while x <= n {
            c[x] = max(c[x], val)
            x += x & -x
        }
    }

    func query(_ x: Int) -> Int {
        var x = x
        var s = 0
        while x > 0 {
            s = max(s, c[x])
            x -= x & -x
        }
        return s
    }
}

class Solution {
    func bestSeqAtIndex(_ height: [Int], _ weight: [Int]) -> Int {
        let n = height.count
        var arr: [(Int, Int)] = []
        for i in 0..<n {
            arr.append((height[i], weight[i]))
        }
        arr.sort {
            if $0.0 == $1.0 {
                return $1.1 < $0.1
            }
            return $0.0 < $1.0
        }

        let weights = Set(arr.map { $1 })
        let sortedWeights = Array(weights).sorted()
        let m = sortedWeights.enumerated().reduce(into: [Int: Int]()) {
            $0[$1.element] = $1.offset + 1
        }

        let tree = BinaryIndexedTree(sortedWeights.count)
        var ans = 1
        for (_, w) in arr {
            let x = m[w]!
            let t = tree.query(x - 1) + 1
            ans = max(ans, t)
            tree.update(x, t)
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
