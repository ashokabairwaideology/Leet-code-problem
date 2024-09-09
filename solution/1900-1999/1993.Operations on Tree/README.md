---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1993.Operations%20on%20Tree/README.md
rating: 1861
source: 第 60 场双周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 设计
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [1993. 树上的操作](https://leetcode.cn/problems/operations-on-tree)

[English Version](/solution/1900-1999/1993.Operations%20on%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵&nbsp;<code>n</code>&nbsp;个节点的树，编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，以父节点数组&nbsp;<code>parent</code>&nbsp;的形式给出，其中&nbsp;<code>parent[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个节点的父节点。树的根节点为 <code>0</code>&nbsp;号节点，所以&nbsp;<code>parent[0] = -1</code>&nbsp;，因为它没有父节点。你想要设计一个数据结构实现树里面对节点的加锁，解锁和升级操作。</p>

<p>数据结构需要支持如下函数：</p>

<ul>
	<li><strong>Lock：</strong>指定用户给指定节点 <strong>上锁</strong>&nbsp;，上锁后其他用户将无法给同一节点上锁。只有当节点处于未上锁的状态下，才能进行上锁操作。</li>
	<li><strong>Unlock：</strong>指定用户给指定节点 <strong>解锁</strong>&nbsp;，只有当指定节点当前正被指定用户锁住时，才能执行该解锁操作。</li>
	<li><b>Upgrade：</b>指定用户给指定节点&nbsp;<strong>上锁</strong>&nbsp;，并且将该节点的所有子孙节点&nbsp;<strong>解锁</strong>&nbsp;。只有如下 3 个条件 <strong>全部</strong> 满足时才能执行升级操作：
	<ul>
		<li>指定节点当前状态为未上锁。</li>
		<li>指定节点至少有一个上锁状态的子孙节点（可以是 <strong>任意</strong>&nbsp;用户上锁的）。</li>
		<li>指定节点没有任何上锁的祖先节点。</li>
	</ul>
	</li>
</ul>

<p>请你实现&nbsp;<code>LockingTree</code>&nbsp;类：</p>

<ul>
	<li><code>LockingTree(int[] parent)</code>&nbsp;用父节点数组初始化数据结构。</li>
	<li><code>lock(int num, int user)</code> 如果&nbsp;id 为&nbsp;<code>user</code>&nbsp;的用户可以给节点&nbsp;<code>num</code>&nbsp;上锁，那么返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。如果可以执行此操作，节点&nbsp;<code>num</code>&nbsp;会被 id 为 <code>user</code>&nbsp;的用户 <strong>上锁</strong>&nbsp;。</li>
	<li><code>unlock(int num, int user)</code>&nbsp;如果 id 为 <code>user</code>&nbsp;的用户可以给节点 <code>num</code>&nbsp;解锁，那么返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。如果可以执行此操作，节点 <code>num</code>&nbsp;变为 <strong>未上锁</strong>&nbsp;状态。</li>
	<li><code>upgrade(int num, int user)</code>&nbsp;如果 id 为 <code>user</code>&nbsp;的用户可以给节点 <code>num</code>&nbsp;升级，那么返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。如果可以执行此操作，节点 <code>num</code>&nbsp;会被&nbsp;<strong>升级 </strong>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1993.Operations%20on%20Tree/images/untitled.png" style="width: 375px; height: 246px;"></p>

<pre><strong>输入：</strong>
["LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock"]
[[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
<strong>输出：</strong>
[null, true, false, true, true, true, false]

<strong>解释：</strong>
LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
lockingTree.lock(2, 2);    // 返回 true ，因为节点 2 未上锁。
                           // 节点 2 被用户 2 上锁。
lockingTree.unlock(2, 3);  // 返回 false ，因为用户 3 无法解锁被用户 2 上锁的节点。
lockingTree.unlock(2, 2);  // 返回 true ，因为节点 2 之前被用户 2 上锁。
                           // 节点 2 现在变为未上锁状态。
lockingTree.lock(4, 5);    // 返回 true ，因为节点 4 未上锁。
                           // 节点 4 被用户 5 上锁。
lockingTree.upgrade(0, 1); // 返回 true ，因为节点 0 未上锁且至少有一个被上锁的子孙节点（节点 4）。
                           // 节点 0 被用户 1 上锁，节点 4 变为未上锁。
lockingTree.lock(0, 1);    // 返回 false ，因为节点 0 已经被上锁了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == parent.length</code></li>
	<li><code>2 &lt;= n &lt;= 2000</code></li>
	<li>对于&nbsp;<code>i != 0</code>&nbsp;，满足&nbsp;<code>0 &lt;= parent[i] &lt;= n - 1</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>0 &lt;= num &lt;= n - 1</code></li>
	<li><code>1 &lt;= user &lt;= 10<sup>4</sup></code></li>
	<li><code>parent</code>&nbsp;表示一棵合法的树。</li>
	<li><code>lock</code>&nbsp;，<code>unlock</code>&nbsp;和&nbsp;<code>upgrade</code>&nbsp;的调用&nbsp;<strong>总共&nbsp;</strong>不超过&nbsp;<code>2000</code>&nbsp;次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们定义以下几个变量：

-   $locked$：记录每个节点的锁定状态，其中 $locked[i]$ 表示节点 $i$ 的锁定状态，如果节点 $i$ 未被上锁，则 $locked[i] = -1$，否则 $locked[i]$ 为锁定节点 $i$ 的用户编号。
-   $parent$：记录每个节点的父节点。
-   $children$：记录每个节点的子节点。

调用 $lock$ 函数时，如果节点 $num$ 未被上锁，则将节点 $num$ 上锁，返回 `true`，否则返回 `false`。

调用 $unlock$ 函数时，如果节点 $num$ 被上锁且上锁的用户编号为 $user$，则将节点 $num$ 解锁，返回 `true`，否则返回 `false`。

调用 $upgrade$ 函数时，我们首先判断节点 $num$ 及其祖先节点是否被上锁，如果是，则返回 $false$。否则，我们判断节点 $num$ 的子孙节点是否有被上锁的，如果没有，则返回 `false`。否则，我们将节点 $num$ 及其子孙节点解锁，然后将节点 $num$ 上锁，返回 `true`。

时间复杂度方面，初始化和 $upgrade$ 函数的时间复杂度均为 $O(n)$，而 $lock$ 和 $unlock$ 函数的时间复杂度均为 $O(1)$。空间复杂度 $O(n)$。其中 $n$ 是节点的数量。

<!-- tabs:start -->

#### Python3

```python
class LockingTree:
    def __init__(self, parent: List[int]):
        n = len(parent)
        self.locked = [-1] * n
        self.parent = parent
        self.children = [[] for _ in range(n)]
        for son, fa in enumerate(parent[1:], 1):
            self.children[fa].append(son)

    def lock(self, num: int, user: int) -> bool:
        if self.locked[num] == -1:
            self.locked[num] = user
            return True
        return False

    def unlock(self, num: int, user: int) -> bool:
        if self.locked[num] == user:
            self.locked[num] = -1
            return True
        return False

    def upgrade(self, num: int, user: int) -> bool:
        def dfs(x: int):
            nonlocal find
            for y in self.children[x]:
                if self.locked[y] != -1:
                    self.locked[y] = -1
                    find = True
                dfs(y)

        x = num
        while x != -1:
            if self.locked[x] != -1:
                return False
            x = self.parent[x]

        find = False
        dfs(num)
        if not find:
            return False
        self.locked[num] = user
        return True


# Your LockingTree object will be instantiated and called as such:
# obj = LockingTree(parent)
# param_1 = obj.lock(num,user)
# param_2 = obj.unlock(num,user)
# param_3 = obj.upgrade(num,user)
```

#### Java

```java
class LockingTree {
    private int[] locked;
    private int[] parent;
    private List<Integer>[] children;

    public LockingTree(int[] parent) {
        int n = parent.length;
        locked = new int[n];
        this.parent = parent;
        children = new List[n];
        Arrays.fill(locked, -1);
        Arrays.setAll(children, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }
    }

    public boolean lock(int num, int user) {
        if (locked[num] == -1) {
            locked[num] = user;
            return true;
        }
        return false;
    }

    public boolean unlock(int num, int user) {
        if (locked[num] == user) {
            locked[num] = -1;
            return true;
        }
        return false;
    }

    public boolean upgrade(int num, int user) {
        int x = num;
        while (x != -1) {
            if (locked[x] != -1) {
                return false;
            }
            x = parent[x];
        }
        boolean[] find = new boolean[1];
        dfs(num, find);
        if (!find[0]) {
            return false;
        }
        locked[num] = user;
        return true;
    }

    private void dfs(int x, boolean[] find) {
        for (int y : children[x]) {
            if (locked[y] != -1) {
                locked[y] = -1;
                find[0] = true;
            }
            dfs(y, find);
        }
    }
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree obj = new LockingTree(parent);
 * boolean param_1 = obj.lock(num,user);
 * boolean param_2 = obj.unlock(num,user);
 * boolean param_3 = obj.upgrade(num,user);
 */
```

#### C++

```cpp
class LockingTree {
public:
    LockingTree(vector<int>& parent) {
        int n = parent.size();
        locked = vector<int>(n, -1);
        this->parent = parent;
        children.resize(n);
        for (int i = 1; i < n; ++i) {
            children[parent[i]].push_back(i);
        }
    }

    bool lock(int num, int user) {
        if (locked[num] == -1) {
            locked[num] = user;
            return true;
        }
        return false;
    }

    bool unlock(int num, int user) {
        if (locked[num] == user) {
            locked[num] = -1;
            return true;
        }
        return false;
    }

    bool upgrade(int num, int user) {
        int x = num;
        while (x != -1) {
            if (locked[x] != -1) {
                return false;
            }
            x = parent[x];
        }
        bool find = false;
        function<void(int)> dfs = [&](int x) {
            for (int y : children[x]) {
                if (locked[y] != -1) {
                    find = true;
                    locked[y] = -1;
                }
                dfs(y);
            }
        };
        dfs(num);
        if (!find) {
            return false;
        }
        locked[num] = user;
        return true;
    }

private:
    vector<int> locked;
    vector<int> parent;
    vector<vector<int>> children;
};

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree* obj = new LockingTree(parent);
 * bool param_1 = obj->lock(num,user);
 * bool param_2 = obj->unlock(num,user);
 * bool param_3 = obj->upgrade(num,user);
 */
```

#### Go

```go
type LockingTree struct {
	locked   []int
	parent   []int
	children [][]int
}

func Constructor(parent []int) LockingTree {
	n := len(parent)
	locked := make([]int, n)
	for i := range locked {
		locked[i] = -1
	}
	children := make([][]int, n)
	for i := 1; i < n; i++ {
		children[parent[i]] = append(children[parent[i]], i)
	}
	return LockingTree{locked, parent, children}
}

func (this *LockingTree) Lock(num int, user int) bool {
	if this.locked[num] == -1 {
		this.locked[num] = user
		return true
	}
	return false
}

func (this *LockingTree) Unlock(num int, user int) bool {
	if this.locked[num] == user {
		this.locked[num] = -1
		return true
	}
	return false
}

func (this *LockingTree) Upgrade(num int, user int) bool {
	x := num
	for ; x != -1; x = this.parent[x] {
		if this.locked[x] != -1 {
			return false
		}
	}
	find := false
	var dfs func(int)
	dfs = func(x int) {
		for _, y := range this.children[x] {
			if this.locked[y] != -1 {
				find = true
				this.locked[y] = -1
			}
			dfs(y)
		}
	}
	dfs(num)
	if !find {
		return false
	}
	this.locked[num] = user
	return true
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * obj := Constructor(parent);
 * param_1 := obj.Lock(num,user);
 * param_2 := obj.Unlock(num,user);
 * param_3 := obj.Upgrade(num,user);
 */
```

#### TypeScript

```ts
class LockingTree {
    private locked: number[];
    private parent: number[];
    private children: number[][];

    constructor(parent: number[]) {
        const n = parent.length;
        this.locked = Array(n).fill(-1);
        this.parent = parent;
        this.children = Array(n)
            .fill(0)
            .map(() => []);
        for (let i = 1; i < n; i++) {
            this.children[parent[i]].push(i);
        }
    }

    lock(num: number, user: number): boolean {
        if (this.locked[num] === -1) {
            this.locked[num] = user;
            return true;
        }
        return false;
    }

    unlock(num: number, user: number): boolean {
        if (this.locked[num] === user) {
            this.locked[num] = -1;
            return true;
        }
        return false;
    }

    upgrade(num: number, user: number): boolean {
        let x = num;
        for (; x !== -1; x = this.parent[x]) {
            if (this.locked[x] !== -1) {
                return false;
            }
        }
        let find = false;
        const dfs = (x: number) => {
            for (const y of this.children[x]) {
                if (this.locked[y] !== -1) {
                    this.locked[y] = -1;
                    find = true;
                }
                dfs(y);
            }
        };
        dfs(num);
        if (!find) {
            return false;
        }
        this.locked[num] = user;
        return true;
    }
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * var obj = new LockingTree(parent)
 * var param_1 = obj.lock(num,user)
 * var param_2 = obj.unlock(num,user)
 * var param_3 = obj.upgrade(num,user)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
