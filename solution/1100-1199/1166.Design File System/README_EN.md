---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1166.Design%20File%20System/README_EN.md
rating: 1479
source: Biweekly Contest 7 Q2
tags:
    - Design
    - Trie
    - Hash Table
    - String
---

<!-- problem:start -->

# [1166. Design File System 🔒](https://leetcode.com/problems/design-file-system)

[中文文档](/solution/1100-1199/1166.Design%20File%20System/README.md)

## Description

<!-- description:start -->

<p>You are asked to design a file system&nbsp;that allows you to create new paths and associate them with different values.</p>

<p>The format of a path is&nbsp;one or more concatenated strings of the form:&nbsp;<code>/</code> followed by one or more lowercase English letters. For example, &quot;<code>/leetcode&quot;</code>&nbsp;and &quot;<code>/leetcode/problems&quot;</code>&nbsp;are valid paths while an empty&nbsp;string <code>&quot;&quot;</code> and <code>&quot;/&quot;</code>&nbsp;are not.</p>

<p>Implement the&nbsp;<code>FileSystem</code> class:</p>

<ul>
	<li><code>bool createPath(string path, int value)</code>&nbsp;Creates a new <code>path</code> and associates a <code>value</code> to it if possible and returns <code>true</code>.&nbsp;Returns <code>false</code>&nbsp;if the path <strong>already exists</strong> or its parent path <strong>doesn&#39;t exist</strong>.</li>
	<li><code>int get(string path)</code>&nbsp;Returns the value associated with <code>path</code> or returns&nbsp;<code>-1</code>&nbsp;if the path doesn&#39;t exist.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
[&quot;FileSystem&quot;,&quot;createPath&quot;,&quot;get&quot;]
[[],[&quot;/a&quot;,1],[&quot;/a&quot;]]
<strong>Output:</strong> 
[null,true,1]
<strong>Explanation:</strong> 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath(&quot;/a&quot;, 1); // return true
fileSystem.get(&quot;/a&quot;); // return 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
[&quot;FileSystem&quot;,&quot;createPath&quot;,&quot;createPath&quot;,&quot;get&quot;,&quot;createPath&quot;,&quot;get&quot;]
[[],[&quot;/leet&quot;,1],[&quot;/leet/code&quot;,2],[&quot;/leet/code&quot;],[&quot;/c/d&quot;,1],[&quot;/c&quot;]]
<strong>Output:</strong> 
[null,true,true,2,false,-1]
<strong>Explanation:</strong> 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath(&quot;/leet&quot;, 1); // return true
fileSystem.createPath(&quot;/leet/code&quot;, 2); // return true
fileSystem.get(&quot;/leet/code&quot;); // return 2
fileSystem.createPath(&quot;/c/d&quot;, 1); // return false because the parent path &quot;/c&quot; doesn&#39;t exist.
fileSystem.get(&quot;/c&quot;); // return -1 because this path doesn&#39;t exist.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= path.length &lt;= 100</code></li>
	<li><code>1 &lt;= value &lt;= 10<sup>9</sup></code></li>
	<li>Each <code>path</code> is <strong>valid</strong> and consists of lowercase English letters and <code>&#39;/&#39;</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>createPath</code> and <code>get</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Trie

We can use a trie to store the paths, where each node stores a value, representing the value of the path corresponding to the node.

The structure of the trie node is defined as follows:

-   `children`: Child nodes, stored in a hash table, where the key is the path of the child node, and the value is the reference to the child node.
-   `v`: The value of the path corresponding to the current node.

The methods of the trie are defined as follows:

-   `insert(w, v)`: Insert the path $w$ and set its corresponding value to $v$. If the path $w$ already exists or its parent path does not exist, return `false`, otherwise return `true`. The time complexity is $O(|w|)$, where $|w|$ is the length of the path $w$.
-   `search(w)`: Return the value corresponding to the path $w$. If the path $w$ does not exist, return $-1$. The time complexity is $O(|w|)$.

The total time complexity is $O(\sum_{w \in W}|w|)$, and the total space complexity is $O(\sum_{w \in W}|w|)$, where $W$ is the set of all inserted paths.

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self, v: int = -1):
        self.children = {}
        self.v = v

    def insert(self, w: str, v: int) -> bool:
        node = self
        ps = w.split("/")
        for p in ps[1:-1]:
            if p not in node.children:
                return False
            node = node.children[p]
        if ps[-1] in node.children:
            return False
        node.children[ps[-1]] = Trie(v)
        return True

    def search(self, w: str) -> int:
        node = self
        for p in w.split("/")[1:]:
            if p not in node.children:
                return -1
            node = node.children[p]
        return node.v


class FileSystem:
    def __init__(self):
        self.trie = Trie()

    def createPath(self, path: str, value: int) -> bool:
        return self.trie.insert(path, value)

    def get(self, path: str) -> int:
        return self.trie.search(path)


# Your FileSystem object will be instantiated and called as such:
# obj = FileSystem()
# param_1 = obj.createPath(path,value)
# param_2 = obj.get(path)
```

#### Java

```java
class Trie {
    Map<String, Trie> children = new HashMap<>();
    int v;

    Trie(int v) {
        this.v = v;
    }

    boolean insert(String w, int v) {
        Trie node = this;
        var ps = w.split("/");
        for (int i = 1; i < ps.length - 1; ++i) {
            var p = ps[i];
            if (!node.children.containsKey(p)) {
                return false;
            }
            node = node.children.get(p);
        }
        if (node.children.containsKey(ps[ps.length - 1])) {
            return false;
        }
        node.children.put(ps[ps.length - 1], new Trie(v));
        return true;
    }

    int search(String w) {
        Trie node = this;
        var ps = w.split("/");
        for (int i = 1; i < ps.length; ++i) {
            var p = ps[i];
            if (!node.children.containsKey(p)) {
                return -1;
            }
            node = node.children.get(p);
        }
        return node.v;
    }
}

class FileSystem {
    private Trie trie = new Trie(-1);

    public FileSystem() {
    }

    public boolean createPath(String path, int value) {
        return trie.insert(path, value);
    }

    public int get(String path) {
        return trie.search(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
```

#### C++

```cpp
class Trie {
public:
    unordered_map<string, Trie*> children;
    int v;

    Trie(int v) {
        this->v = v;
    }

    bool insert(string& w, int v) {
        Trie* node = this;
        auto ps = split(w, '/');
        for (int i = 1; i < ps.size() - 1; ++i) {
            auto p = ps[i];
            if (!node->children.count(p)) {
                return false;
            }
            node = node->children[p];
        }
        if (node->children.count(ps.back())) {
            return false;
        }
        node->children[ps.back()] = new Trie(v);
        return true;
    }

    int search(string& w) {
        Trie* node = this;
        auto ps = split(w, '/');
        for (int i = 1; i < ps.size(); ++i) {
            auto p = ps[i];
            if (!node->children.count(p)) {
                return -1;
            }
            node = node->children[p];
        }
        return node->v;
    }

private:
    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }
};

class FileSystem {
public:
    FileSystem() {
        trie = new Trie(-1);
    }

    bool createPath(string path, int value) {
        return trie->insert(path, value);
    }

    int get(string path) {
        return trie->search(path);
    }

private:
    Trie* trie;
};

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem* obj = new FileSystem();
 * bool param_1 = obj->createPath(path,value);
 * int param_2 = obj->get(path);
 */
```

#### Go

```go
type trie struct {
	children map[string]*trie
	v        int
}

func newTrie(v int) *trie {
	return &trie{map[string]*trie{}, v}
}

func (t *trie) insert(w string, v int) bool {
	node := t
	ps := strings.Split(w, "/")
	for _, p := range ps[1 : len(ps)-1] {
		if _, ok := node.children[p]; !ok {
			return false
		}
		node = node.children[p]
	}
	if _, ok := node.children[ps[len(ps)-1]]; ok {
		return false
	}
	node.children[ps[len(ps)-1]] = newTrie(v)
	return true
}

func (t *trie) search(w string) int {
	node := t
	ps := strings.Split(w, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			return -1
		}
		node = node.children[p]
	}
	return node.v
}

type FileSystem struct {
	trie *trie
}

func Constructor() FileSystem {
	return FileSystem{trie: newTrie(-1)}
}

func (this *FileSystem) CreatePath(path string, value int) bool {
	return this.trie.insert(path, value)
}

func (this *FileSystem) Get(path string) int {
	return this.trie.search(path)
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.CreatePath(path,value);
 * param_2 := obj.Get(path);
 */
```

#### TypeScript

```ts
class Trie {
    children: Map<string, Trie>;
    v: number;

    constructor(v: number) {
        this.children = new Map<string, Trie>();
        this.v = v;
    }

    insert(w: string, v: number): boolean {
        let node: Trie = this;
        const ps = w.split('/').slice(1);
        for (let i = 0; i < ps.length - 1; ++i) {
            const p = ps[i];
            if (!node.children.has(p)) {
                return false;
            }
            node = node.children.get(p)!;
        }
        if (node.children.has(ps[ps.length - 1])) {
            return false;
        }
        node.children.set(ps[ps.length - 1], new Trie(v));
        return true;
    }

    search(w: string): number {
        let node: Trie = this;
        const ps = w.split('/').slice(1);
        for (const p of ps) {
            if (!node.children.has(p)) {
                return -1;
            }
            node = node.children.get(p)!;
        }
        return node.v;
    }
}

class FileSystem {
    trie: Trie;

    constructor() {
        this.trie = new Trie(-1);
    }

    createPath(path: string, value: number): boolean {
        return this.trie.insert(path, value);
    }

    get(path: string): number {
        return this.trie.search(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * var obj = new FileSystem()
 * var param_1 = obj.createPath(path,value)
 * var param_2 = obj.get(path)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
