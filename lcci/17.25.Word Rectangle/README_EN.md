---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.25.Word%20Rectangle/README_EN.md
---

<!-- problem:start -->

# [17.25. Word Rectangle](https://leetcode.cn/problems/word-rectangle-lcci)

[中文文档](/lcci/17.25.Word%20Rectangle/README.md)

## Description

<!-- description:start -->

<p>Given a list of millions of words, design an algorithm to create the largest possible rectangle of letters such that every row forms a word (reading left to right) and every column forms a word (reading top to bottom). The words need not be chosen consecutively from the list but all rows must be the same length and all columns must be the same height.</p>
<p>If there are more than one answer, return any one of them. A word can be used more than once.</p>
<p><strong>Example 1:</strong></p>
<pre>

<strong>Input:</strong> [&quot;this&quot;, &quot;real&quot;, &quot;hard&quot;, &quot;trh&quot;, &quot;hea&quot;, &quot;iar&quot;, &quot;sld&quot;]

<strong>Output:

</strong>[

&nbsp; &quot;this&quot;,

&nbsp; &quot;real&quot;,

&nbsp; &quot;hard&quot;

]</pre>

<p><strong>Example 2:</strong></p>
<pre>

<strong>Input:</strong> [&quot;aa&quot;]

<strong>Output: </strong>[&quot;aa&quot;,&quot;aa&quot;]</pre>

<p><strong>Notes: </strong></p>
<ul>
	<li><code>words.length &lt;= 1000</code></li>
	<li><code>words[i].length &lt;= 100</code></li>
	<li>It&#39;s guaranteed that&nbsp;all the words are randomly generated.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True


class Solution:
    def maxRectangle(self, words: List[str]) -> List[str]:
        def check(mat):
            m, n = len(mat), len(mat[0])
            ans = 1
            for j in range(n):
                node = trie
                for i in range(m):
                    idx = ord(mat[i][j]) - ord("a")
                    if node.children[idx] is None:
                        return 0
                    node = node.children[idx]
                if not node.is_end:
                    ans = 2
            return ans

        def dfs(ws):
            nonlocal ans, max_s, max_l
            if len(ws[0]) * max_l <= max_s or len(t) >= max_l:
                return

            for w in ws:
                t.append(w)
                st = check(t)
                if st == 0:
                    t.pop()
                    continue
                if st == 1 and max_s < len(t) * len(t[0]):
                    ans = t[:]
                    max_s = len(t) * len(t[0])
                dfs(ws)
                t.pop()

        d = defaultdict(list)
        trie = Trie()
        max_l = 0
        for w in words:
            trie.insert(w)
            max_l = max(max_l, len(w))
            d[len(w)].append(w)

        max_s = 0
        ans = []
        for ws in d.values():
            t = []
            dfs(ws)
        return ans
```

#### Java

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;

    void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }
}

class Solution {
    private int maxL;
    private int maxS;
    private String[] ans;
    private Trie trie = new Trie();
    private List<String> t = new ArrayList<>();

    public String[] maxRectangle(String[] words) {
        Map<Integer, List<String>> d = new HashMap<>(100);
        for (String w : words) {
            maxL = Math.max(maxL, w.length());
            trie.insert(w);
            d.computeIfAbsent(w.length(), k -> new ArrayList<>()).add(w);
        }
        for (List<String> ws : d.values()) {
            t.clear();
            dfs(ws);
        }
        return ans;
    }

    private void dfs(List<String> ws) {
        if (ws.get(0).length() * maxL <= maxS || t.size() >= maxL) {
            return;
        }
        for (String w : ws) {
            t.add(w);
            int st = check(t);
            if (st == 0) {
                t.remove(t.size() - 1);
                continue;
            }
            if (st == 1 && maxS < t.size() * t.get(0).length()) {
                maxS = t.size() * t.get(0).length();
                ans = t.toArray(new String[0]);
            }
            dfs(ws);
            t.remove(t.size() - 1);
        }
    }

    private int check(List<String> mat) {
        int m = mat.size(), n = mat.get(0).length();
        int ans = 1;
        for (int j = 0; j < n; ++j) {
            Trie node = trie;
            for (int i = 0; i < m; ++i) {
                int idx = mat.get(i).charAt(j) - 'a';
                if (node.children[idx] == null) {
                    return 0;
                }
                node = node.children[idx];
            }
            if (!node.isEnd) {
                ans = 2;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Trie {
public:
    vector<Trie*> children;
    bool is_end;

    Trie() {
        children = vector<Trie*>(26, nullptr);
        is_end = false;
    }

    void insert(const string& word) {
        Trie* cur = this;
        for (char c : word) {
            c -= 'a';
            if (cur->children[c] == nullptr) {
                cur->children[c] = new Trie;
            }
            cur = cur->children[c];
        }
        cur->is_end = true;
    }
};

class Solution {
public:
    vector<string> maxRectangle(vector<string>& words) {
        unordered_map<int, vector<string>> d;
        int maxL = 0, maxS = 0;
        vector<string> ans;
        vector<string> t;
        Trie* trie = new Trie();
        for (auto& w : words) {
            maxL = max(maxL, (int) w.size());
            d[w.size()].emplace_back(w);
            trie->insert(w);
        }
        auto check = [&](vector<string>& mat) {
            int m = mat.size(), n = mat[0].size();
            int ans = 1;
            for (int j = 0; j < n; ++j) {
                Trie* node = trie;
                for (int i = 0; i < m; ++i) {
                    int idx = mat[i][j] - 'a';
                    if (!node->children[idx]) {
                        return 0;
                    }
                    node = node->children[idx];
                }
                if (!node->is_end) {
                    ans = 2;
                }
            }
            return ans;
        };

        function<void(vector<string>&)> dfs = [&](vector<string>& ws) {
            if (ws[0].size() * maxL <= maxS || t.size() >= maxL) {
                return;
            }
            for (auto& w : ws) {
                t.emplace_back(w);
                int st = check(t);
                if (st == 0) {
                    t.pop_back();
                    continue;
                }
                if (st == 1 && maxS < t.size() * t[0].size()) {
                    maxS = t.size() * t[0].size();
                    ans = t;
                }
                dfs(ws);
                t.pop_back();
            }
        };
        for (auto& [_, ws] : d) {
            t.clear();
            dfs(ws);
        }
        return ans;
    }
};
```

#### Go

```go
type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func maxRectangle(words []string) (ans []string) {
	trie := newTrie()
	d := map[int][]string{}
	t := []string{}
	var maxL, maxS int
	for _, w := range words {
		maxL = max(maxL, len(w))
		d[len(w)] = append(d[len(w)], w)
		trie.insert(w)
	}
	check := func(mat []string) int {
		m, n := len(mat), len(mat[0])
		ans := 1
		for j := 0; j < n; j++ {
			node := trie
			for i := 0; i < m; i++ {
				idx := mat[i][j] - 'a'
				if node.children[idx] == nil {
					return 0
				}
				node = node.children[idx]
			}
			if !node.isEnd {
				ans = 2
			}
		}
		return ans
	}
	var dfs func([]string)
	dfs = func(ws []string) {
		if len(ws[0])*maxL <= maxS || len(t) >= maxL {
			return
		}
		for _, w := range ws {
			t = append(t, w)
			st := check(t)
			if st == 0 {
				t = t[:len(t)-1]
				continue
			}
			if st == 1 && maxS < len(t)*len(t[0]) {
				maxS = len(t) * len(t[0])
				ans = append([]string{}, t...)
			}
			dfs(ws)
			t = t[:len(t)-1]
		}
	}
	for _, ws := range d {
		dfs(ws)
	}
	return
}
```

#### Swift

```swift
class Trie {
    var children = [Trie?](repeating: nil, count: 26)
    var isEnd = false

    func insert(_ word: String) {
        var node = self
        for c in word {
            let index = Int(c.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                node.children[index] = Trie()
            }
            node = node.children[index]!
        }
        node.isEnd = true
    }
}

class Solution {
    private var maxL = 0
    private var maxS = 0
    private var ans: [String] = []
    private var trie = Trie()
    private var t = [String]()

    func maxRectangle(_ words: [String]) -> [String] {
        var d = [Int: [String]]()
        for word in words {
            maxL = max(maxL, word.count)
            trie.insert(word)
            d[word.count, default: []].append(word)
        }

        for ws in d.values {
            t.removeAll()
            dfs(ws)
        }
        return ans
    }

    private func dfs(_ ws: [String]) {
        guard let first = ws.first, first.count * maxL > maxS, t.count < maxL else { return }
        for w in ws {
            t.append(w)
            let st = check(t)
            switch st {
            case 0:
                t.removeLast()
            case 1:
                if maxS < t.count * t[0].count {
                    maxS = t.count * t[0].count
                    ans = t
                }
                dfs(ws)
                t.removeLast()
            default:
                dfs(ws)
                t.removeLast()
            }
        }
    }

    private func check(_ mat: [String]) -> Int {
        let m = mat.count, n = mat[0].count
        var result = 1
        for j in 0..<n {
            var node = trie
            for i in 0..<m {
                let index = Int(mat[i][mat[i].index(mat[i].startIndex, offsetBy: j)].asciiValue! - Character("a").asciiValue!)
                guard let nextNode = node.children[index] else { return 0 }
                node = nextNode
            }
            if !node.isEnd {
                result = 2
            }
        }
        return result
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
