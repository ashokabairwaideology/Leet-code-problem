---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1832.Check%20if%20the%20Sentence%20Is%20Pangram/README_EN.md
rating: 1166
source: Weekly Contest 237 Q1
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [1832. Check if the Sentence Is Pangram](https://leetcode.com/problems/check-if-the-sentence-is-pangram)

[中文文档](/solution/1800-1899/1832.Check%20if%20the%20Sentence%20Is%20Pangram/README.md)

## Description

<!-- description:start -->

<p>A <strong>pangram</strong> is a sentence where every letter of the English alphabet appears at least once.</p>

<p>Given a string <code>sentence</code> containing only lowercase English letters, return<em> </em><code>true</code><em> if </em><code>sentence</code><em> is a <strong>pangram</strong>, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;thequickbrownfoxjumpsoverthelazydog&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> sentence contains at least one of every letter of the English alphabet.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;leetcode&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 1000</code></li>
	<li><code>sentence</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Array or Hash Table

Traverse the string `sentence`, use an array or hash table to record the letters that have appeared, and finally check whether there are $26$ letters in the array or hash table.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Where $n$ is the length of the string `sentence`, and $C$ is the size of the character set. In this problem, $C = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkIfPangram(self, sentence: str) -> bool:
        return len(set(sentence)) == 26
```

#### Java

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        boolean[] vis = new boolean[26];
        for (int i = 0; i < sentence.length(); ++i) {
            vis[sentence.charAt(i) - 'a'] = true;
        }
        for (boolean v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkIfPangram(string sentence) {
        int vis[26] = {0};
        for (char& c : sentence) vis[c - 'a'] = 1;
        for (int& v : vis)
            if (!v) return false;
        return true;
    }
};
```

#### Go

```go
func checkIfPangram(sentence string) bool {
	vis := [26]bool{}
	for _, c := range sentence {
		vis[c-'a'] = true
	}
	for _, v := range vis {
		if !v {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function checkIfPangram(sentence: string): boolean {
    const vis = new Array(26).fill(false);
    for (const c of sentence) {
        vis[c.charCodeAt(0) - 'a'.charCodeAt(0)] = true;
    }
    return vis.every(v => v);
}
```

#### Rust

```rust
impl Solution {
    pub fn check_if_pangram(sentence: String) -> bool {
        let mut vis = [false; 26];
        for c in sentence.as_bytes() {
            vis[(*c - b'a') as usize] = true;
        }
        vis.iter().all(|v| *v)
    }
}
```

#### C

```c
bool checkIfPangram(char* sentence) {
    int vis[26] = {0};
    for (int i = 0; sentence[i]; i++) {
        vis[sentence[i] - 'a'] = 1;
    }
    for (int i = 0; i < 26; i++) {
        if (!vis[i]) {
            return 0;
        }
    }
    return 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Bit Manipulation

We can also use an integer $mask$ to record the letters that have appeared, where the $i$-th bit of $mask$ indicates whether the $i$-th letter has appeared.

Finally, check whether there are $26$ $1$s in the binary representation of $mask$, that is, check whether $mask$ is equal to $2^{26} - 1$. If so, return `true`, otherwise return `false`.

The time complexity is $O(n)$, where $n$ is the length of the string `sentence`. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkIfPangram(self, sentence: str) -> bool:
        mask = 0
        for c in sentence:
            mask |= 1 << (ord(c) - ord('a'))
        return mask == (1 << 26) - 1
```

#### Java

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        int mask = 0;
        for (int i = 0; i < sentence.length(); ++i) {
            mask |= 1 << (sentence.charAt(i) - 'a');
        }
        return mask == (1 << 26) - 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkIfPangram(string sentence) {
        int mask = 0;
        for (char& c : sentence) mask |= 1 << (c - 'a');
        return mask == (1 << 26) - 1;
    }
};
```

#### Go

```go
func checkIfPangram(sentence string) bool {
	mask := 0
	for _, c := range sentence {
		mask |= 1 << int(c-'a')
	}
	return mask == 1<<26-1
}
```

#### TypeScript

```ts
function checkIfPangram(sentence: string): boolean {
    let mark = 0;
    for (const c of sentence) {
        mark |= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
    }
    return mark === (1 << 26) - 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn check_if_pangram(sentence: String) -> bool {
        let mut mark = 0;
        for c in sentence.as_bytes() {
            mark |= 1 << (*c - b'a');
        }
        mark == (1 << 26) - 1
    }
}
```

#### C

```c
bool checkIfPangram(char* sentence) {
    int mark = 0;
    for (int i = 0; sentence[i]; i++) {
        mark |= 1 << (sentence[i] - 'a');
    }
    return mark == (1 << 26) - 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
