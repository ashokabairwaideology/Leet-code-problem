---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1316.Distinct%20Echo%20Substrings/README_EN.md
rating: 1836
source: Biweekly Contest 17 Q4
tags:
    - Trie
    - String
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [1316. Distinct Echo Substrings](https://leetcode.com/problems/distinct-echo-substrings)

[中文文档](/solution/1300-1399/1316.Distinct%20Echo%20Substrings/README.md)

## Description

<!-- description:start -->

<p>Return the number of <strong>distinct</strong> non-empty substrings of <code>text</code>&nbsp;that can be written as the concatenation of some string with itself (i.e. it can be written as <code>a + a</code>&nbsp;where <code>a</code> is some string).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;abcabcabc&quot;
<strong>Output:</strong> 3
<b>Explanation: </b>The 3 substrings are &quot;abcabc&quot;, &quot;bcabca&quot; and &quot;cabcab&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;leetcodeleetcode&quot;
<strong>Output:</strong> 2
<b>Explanation: </b>The 2 substrings are &quot;ee&quot; and &quot;leetcodeleetcode&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 2000</code></li>
	<li><code>text</code>&nbsp;has only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def distinctEchoSubstrings(self, text: str) -> int:
        def get(l, r):
            return (h[r] - h[l - 1] * p[r - l + 1]) % mod

        n = len(text)
        base = 131
        mod = int(1e9) + 7
        h = [0] * (n + 10)
        p = [1] * (n + 10)
        for i, c in enumerate(text):
            t = ord(c) - ord('a') + 1
            h[i + 1] = (h[i] * base) % mod + t
            p[i + 1] = (p[i] * base) % mod
        vis = set()
        for i in range(n - 1):
            for j in range(i + 1, n, 2):
                k = (i + j) >> 1
                a = get(i + 1, k + 1)
                b = get(k + 2, j + 1)
                if a == b:
                    vis.add(a)
        return len(vis)
```

#### Java

```java
class Solution {
    private long[] h;
    private long[] p;

    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        int base = 131;
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = text.charAt(i) - 'a' + 1;
            h[i + 1] = h[i] * base + t;
            p[i + 1] = p[i] * base;
        }
        Set<Long> vis = new HashSet<>();
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; j += 2) {
                int k = (i + j) >> 1;
                long a = get(i + 1, k + 1);
                long b = get(k + 2, j + 1);
                if (a == b) {
                    vis.add(a);
                }
            }
        }
        return vis.size();
    }

    private long get(int i, int j) {
        return h[j] - h[i - 1] * p[j - i + 1];
    }
}
```

#### C++

```cpp
typedef unsigned long long ull;

class Solution {
public:
    int distinctEchoSubstrings(string text) {
        int n = text.size();
        int base = 131;
        vector<ull> p(n + 10);
        vector<ull> h(n + 10);
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = text[i] - 'a' + 1;
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + t;
        }
        unordered_set<ull> vis;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; j += 2) {
                int k = (i + j) >> 1;
                ull a = get(i + 1, k + 1, p, h);
                ull b = get(k + 2, j + 1, p, h);
                if (a == b) vis.insert(a);
            }
        }
        return vis.size();
    }

    ull get(int l, int r, vector<ull>& p, vector<ull>& h) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
};
```

#### Go

```go
func distinctEchoSubstrings(text string) int {
	n := len(text)
	base := 131
	h := make([]int, n+10)
	p := make([]int, n+10)
	p[0] = 1
	for i, c := range text {
		t := int(c-'a') + 1
		p[i+1] = p[i] * base
		h[i+1] = h[i]*base + t
	}
	get := func(l, r int) int {
		return h[r] - h[l-1]*p[r-l+1]
	}
	vis := map[int]bool{}
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j += 2 {
			k := (i + j) >> 1
			a, b := get(i+1, k+1), get(k+2, j+1)
			if a == b {
				vis[a] = true
			}
		}
	}
	return len(vis)
}
```

#### Rust

```rust
use std::collections::HashSet;

const BASE: u64 = 131;

impl Solution {
    #[allow(dead_code)]
    pub fn distinct_echo_substrings(text: String) -> i32 {
        let n = text.len();
        let mut vis: HashSet<u64> = HashSet::new();
        let mut base_vec: Vec<u64> = vec![1; n + 1];
        let mut hash_vec: Vec<u64> = vec![0; n + 1];

        // Initialize the base vector & hash vector
        for i in 0..n {
            let cur_char = ((text.chars().nth(i).unwrap() as u8) - ('a' as u8) + 1) as u64;
            // Update base vector
            base_vec[i + 1] = base_vec[i] * BASE;
            // Update hash vector
            hash_vec[i + 1] = hash_vec[i] * BASE + cur_char;
        }

        // Traverse the text to find the result pair, using rolling hash
        for i in 0..n - 1 {
            for j in i + 1..n {
                // Prevent overflow
                let k = i + (j - i) / 2;
                let left = Self::get_hash(i + 1, k + 1, &base_vec, &hash_vec);
                let right = Self::get_hash(k + 2, j + 1, &base_vec, &hash_vec);
                if left == right {
                    vis.insert(left);
                }
            }
        }

        vis.len() as i32
    }

    #[allow(dead_code)]
    fn get_hash(start: usize, end: usize, base_vec: &Vec<u64>, hash_vec: &Vec<u64>) -> u64 {
        hash_vec[end] - hash_vec[start - 1] * base_vec[end - start + 1]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
