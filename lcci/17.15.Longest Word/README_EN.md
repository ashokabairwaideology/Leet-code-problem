---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.15.Longest%20Word/README_EN.md
---

<!-- problem:start -->

# [17.15. Longest Word](https://leetcode.cn/problems/longest-word-lcci)

[中文文档](/lcci/17.15.Longest%20Word/README.md)

## Description

<!-- description:start -->

<p>Given a list of words, write a program to find the longest word made of other words in the list. If there are more than one answer, return the one that has smallest lexicographic order. If no answer, return an empty string.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong> [&quot;cat&quot;,&quot;banana&quot;,&quot;dog&quot;,&quot;nana&quot;,&quot;walk&quot;,&quot;walker&quot;,&quot;dogwalker&quot;]

<strong>Output: </strong> &quot;dogwalker&quot;

<strong>Explanation: </strong> &quot;dogwalker&quot; can be made of &quot;dog&quot; and &quot;walker&quot;.

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>0 &lt;= len(words) &lt;= 100</code></li>
	<li><code>1 &lt;= len(words[i]) &lt;= 100</code></li>
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

    def insert(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return False
            node = node.children[idx]
        return node.is_end


class Solution:
    def longestWord(self, words: List[str]) -> str:
        def cmp(a, b):
            if len(a) != len(b):
                return len(a) - len(b)
            return -1 if a > b else 1

        def dfs(w):
            return not w or any(
                trie.search(w[:i]) and dfs(w[i:]) for i in range(1, len(w) + 1)
            )

        words.sort(key=cmp_to_key(cmp))
        trie = Trie()
        ans = ""
        for w in words:
            if dfs(w):
                ans = w
            trie.insert(w)
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

    boolean search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return false;
            }
            node = node.children[c];
        }
        return node.isEnd;
    }
}

class Solution {
    private Trie trie = new Trie();

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return b.compareTo(a);
        });
        String ans = "";
        for (String w : words) {
            if (dfs(w)) {
                ans = w;
            }
            trie.insert(w);
        }
        return ans;
    }

    private boolean dfs(String w) {
        if ("".equals(w)) {
            return true;
        }
        for (int i = 1; i <= w.length(); ++i) {
            if (trie.search(w.substring(0, i)) && dfs(w.substring(i))) {
                return true;
            }
        }
        return false;
    }
}
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

func (this *Trie) search(word string) bool {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return false
		}
		node = node.children[c]
	}
	return node.isEnd
}

func longestWord(words []string) string {
	sort.Slice(words, func(i, j int) bool {
		a, b := words[i], words[j]
		if len(a) != len(b) {
			return len(a) < len(b)
		}
		return a > b
	})
	trie := newTrie()
	var dfs func(string) bool
	dfs = func(w string) bool {
		if len(w) == 0 {
			return true
		}
		for i := 1; i <= len(w); i++ {
			if trie.search(w[:i]) && dfs(w[i:]) {
				return true
			}
		}
		return false
	}
	ans := ""
	for _, w := range words {
		if dfs(w) {
			ans = w
		}
		trie.insert(w)
	}
	return ans
}
```

#### Swift

```swift
class Trie {
    var children = [Trie?](repeating: nil, count: 26)
    var isEnd = false

    func insert(_ word: String) {
        var node = self
        for ch in word {
            let index = Int(ch.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                node.children[index] = Trie()
            }
            node = node.children[index]!
        }
        node.isEnd = true
    }

    func search(_ word: String) -> Bool {
        var node = self
        for ch in word {
            let index = Int(ch.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                return false
            }
            node = node.children[index]!
        }
        return node.isEnd
    }
}

class Solution {
    func longestWord(_ words: [String]) -> String {
        var words = words.sorted(by: { $0.count < $1.count || ($0.count == $1.count && $0 > $1) })
        let trie = Trie()

        var dfs: ((String) -> Bool)!
        dfs = { w in
            if w.isEmpty {
                return true
            }
            for i in 1...w.count {
                if trie.search(String(w.prefix(i))) && dfs(String(w.suffix(w.count - i))) {
                    return true
                }
            }
            return false
        }

        var ans = ""
        for w in words {
            if dfs(w) {
                ans = w
            }
            trie.insert(w)
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
