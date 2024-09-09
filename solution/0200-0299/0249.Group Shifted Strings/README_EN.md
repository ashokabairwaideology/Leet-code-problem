---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0249.Group%20Shifted%20Strings/README_EN.md
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [249. Group Shifted Strings 🔒](https://leetcode.com/problems/group-shifted-strings)

[中文文档](/solution/0200-0299/0249.Group%20Shifted%20Strings/README.md)

## Description

<!-- description:start -->

<p>Perform the following shift operations on a string:</p>

<ul>
	<li><strong>Right shift</strong>: Replace every letter with the <strong>successive</strong> letter of the English alphabet, where &#39;z&#39; is replaced by &#39;a&#39;. For example, <code>&quot;abc&quot;</code> can be right-shifted to <code>&quot;bcd&quot; </code>or <code>&quot;xyz&quot;</code> can be right-shifted to <code>&quot;yza&quot;</code>.</li>
	<li><strong>Left shift</strong>: Replace every letter with the <strong>preceding</strong> letter of the English alphabet, where &#39;a&#39; is replaced by &#39;z&#39;. For example, <code>&quot;bcd&quot;</code> can be left-shifted to <code>&quot;abc&quot;<font face="Times New Roman"> or </font></code><code>&quot;yza&quot;</code> can be left-shifted to <code>&quot;xyz&quot;</code>.</li>
</ul>

<p>We can keep shifting the string in both directions to form an <strong>endless</strong> <strong>shifting sequence</strong>.</p>

<ul>
	<li>For example, shift <code>&quot;abc&quot;</code> to form the sequence: <code>... &lt;-&gt; &quot;abc&quot; &lt;-&gt; &quot;bcd&quot; &lt;-&gt; ... &lt;-&gt; &quot;xyz&quot; &lt;-&gt; &quot;yza&quot; &lt;-&gt; ...</code>.<code> &lt;-&gt; &quot;zab&quot; &lt;-&gt; &quot;abc&quot; &lt;-&gt; ...</code></li>
</ul>

<p>You are given an array of strings <code>strings</code>, group together all <code>strings[i]</code> that belong to the same shifting sequence. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strings = [&quot;abc&quot;,&quot;bcd&quot;,&quot;acef&quot;,&quot;xyz&quot;,&quot;az&quot;,&quot;ba&quot;,&quot;a&quot;,&quot;z&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;acef&quot;],[&quot;a&quot;,&quot;z&quot;],[&quot;abc&quot;,&quot;bcd&quot;,&quot;xyz&quot;],[&quot;az&quot;,&quot;ba&quot;]]</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strings = [&quot;a&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;a&quot;]]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strings.length &lt;= 200</code></li>
	<li><code>1 &lt;= strings[i].length &lt;= 50</code></li>
	<li><code>strings[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $g$ to store each string after shifting and with the first character as '`a`'. That is, $g[t]$ represents the set of all strings that become $t$ after shifting.

We iterate through each string. For each string, we calculate its shifted string $t$, and then add it to $g[t]$.

Finally, we take out all the values in $g$, which is the answer.

The time complexity is $O(L)$ and the space complexity is $O(L)$, where $L$ is the sum of the lengths of all strings.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def groupStrings(self, strings: List[str]) -> List[List[str]]:
        g = defaultdict(list)
        for s in strings:
            diff = ord(s[0]) - ord("a")
            t = []
            for c in s:
                c = ord(c) - diff
                if c < ord("a"):
                    c += 26
                t.append(chr(c))
            g["".join(t)].append(s)
        return list(g.values())
```

#### Java

```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> g = new HashMap<>();
        for (var s : strings) {
            char[] t = s.toCharArray();
            int diff = t[0] - 'a';
            for (int i = 0; i < t.length; ++i) {
                t[i] = (char) (t[i] - diff);
                if (t[i] < 'a') {
                    t[i] += 26;
                }
            }
            g.computeIfAbsent(new String(t), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(g.values());
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> groupStrings(vector<string>& strings) {
        unordered_map<string, vector<string>> g;
        for (auto& s : strings) {
            string t;
            int diff = s[0] - 'a';
            for (int i = 0; i < s.size(); ++i) {
                char c = s[i] - diff;
                if (c < 'a') {
                    c += 26;
                }
                t.push_back(c);
            }
            g[t].emplace_back(s);
        }
        vector<vector<string>> ans;
        for (auto& p : g) {
            ans.emplace_back(move(p.second));
        }
        return ans;
    }
};
```

#### Go

```go
func groupStrings(strings []string) [][]string {
	g := make(map[string][]string)
	for _, s := range strings {
		t := []byte(s)
		diff := t[0] - 'a'
		for i := range t {
			t[i] -= diff
			if t[i] < 'a' {
				t[i] += 26
			}
		}
		g[string(t)] = append(g[string(t)], s)
	}
	ans := make([][]string, 0, len(g))
	for _, v := range g {
		ans = append(ans, v)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
