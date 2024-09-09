---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2309.Greatest%20English%20Letter%20in%20Upper%20and%20Lower%20Case/README_EN.md
rating: 1242
source: Weekly Contest 298 Q1
tags:
    - Hash Table
    - String
    - Enumeration
---

<!-- problem:start -->

# [2309. Greatest English Letter in Upper and Lower Case](https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case)

[中文文档](/solution/2300-2399/2309.Greatest%20English%20Letter%20in%20Upper%20and%20Lower%20Case/README.md)

## Description

<!-- description:start -->

<p>Given a string of English letters <code>s</code>, return <em>the <strong>greatest </strong>English letter which occurs as <strong>both</strong> a lowercase and uppercase letter in</em> <code>s</code>. The returned letter should be in <strong>uppercase</strong>. If no such letter exists, return <em>an empty string</em>.</p>

<p>An English letter <code>b</code> is <strong>greater</strong> than another letter <code>a</code> if <code>b</code> appears <strong>after</strong> <code>a</code> in the English alphabet.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;l<strong><u>Ee</u></strong>TcOd<u><strong>E</strong></u>&quot;
<strong>Output:</strong> &quot;E&quot;
<strong>Explanation:</strong>
The letter &#39;E&#39; is the only letter to appear in both lower and upper case.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a<strong><u>rR</u></strong>AzFif&quot;
<strong>Output:</strong> &quot;R&quot;
<strong>Explanation:</strong>
The letter &#39;R&#39; is the greatest letter to appear in both lower and upper case.
Note that &#39;A&#39; and &#39;F&#39; also appear in both lower and upper case, but &#39;R&#39; is greater than &#39;F&#39; or &#39;A&#39;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AbCdEfGhIjK&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong>
There is no letter that appears in both lower and upper case.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase and uppercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

First, we use a hash table $ss$ to record all the letters that appear in the string $s$. Then we start enumerating from the last letter of the uppercase alphabet. If both the uppercase and lowercase forms of the current letter are in $ss$, we return that letter.

At the end of the enumeration, if no letter that meets the conditions is found, we return an empty string.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ and $C$ are the length of the string $s$ and the size of the character set, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def greatestLetter(self, s: str) -> str:
        ss = set(s)
        for c in ascii_uppercase[::-1]:
            if c in ss and c.lower() in ss:
                return c
        return ''
```

#### Java

```java
class Solution {
    public String greatestLetter(String s) {
        Set<Character> ss = new HashSet<>();
        for (char c : s.toCharArray()) {
            ss.add(c);
        }
        for (char a = 'Z'; a >= 'A'; --a) {
            if (ss.contains(a) && ss.contains((char) (a + 32))) {
                return String.valueOf(a);
            }
        }
        return "";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string greatestLetter(string s) {
        unordered_set<char> ss(s.begin(), s.end());
        for (char c = 'Z'; c >= 'A'; --c) {
            if (ss.count(c) && ss.count(char(c + 32))) {
                return string(1, c);
            }
        }
        return "";
    }
};
```

#### Go

```go
func greatestLetter(s string) string {
	ss := map[rune]bool{}
	for _, c := range s {
		ss[c] = true
	}
	for c := 'Z'; c >= 'A'; c-- {
		if ss[c] && ss[rune(c+32)] {
			return string(c)
		}
	}
	return ""
}
```

#### TypeScript

```ts
function greatestLetter(s: string): string {
    const ss = new Array(128).fill(false);
    for (const c of s) {
        ss[c.charCodeAt(0)] = true;
    }
    for (let i = 90; i >= 65; --i) {
        if (ss[i] && ss[i + 32]) {
            return String.fromCharCode(i);
        }
    }
    return '';
}
```

#### Rust

```rust
impl Solution {
    pub fn greatest_letter(s: String) -> String {
        let mut arr = [0; 26];
        for &c in s.as_bytes().iter() {
            if c >= b'a' {
                arr[(c - b'a') as usize] |= 1;
            } else {
                arr[(c - b'A') as usize] |= 2;
            }
        }
        for i in (0..26).rev() {
            if arr[i] == 3 {
                return char::from(b'A' + (i as u8)).to_string();
            }
        }
        "".to_string()
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {string}
 */
var greatestLetter = function (s) {
    const ss = new Array(128).fill(false);
    for (const c of s) {
        ss[c.charCodeAt(0)] = true;
    }
    for (let i = 90; i >= 65; --i) {
        if (ss[i] && ss[i + 32]) {
            return String.fromCharCode(i);
        }
    }
    return '';
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Bit Manipulation (Space Optimization)

We can use two integers $mask1$ and $mask2$ to record the lowercase and uppercase letters that appear in the string $s$, respectively. The $i$-th bit of $mask1$ indicates whether the $i$-th lowercase letter appears, and the $i$-th bit of $mask2$ indicates whether the $i$-th uppercase letter appears.

Then we perform a bitwise AND operation on $mask1$ and $mask2$. The $i$-th bit of the resulting $mask$ indicates whether the $i$-th letter appears in both uppercase and lowercase.

Next, we just need to get the position of the highest $1$ in the binary representation of $mask$, and convert it to the corresponding uppercase letter. If all binary bits are not $1$, it means that there is no letter that appears in both uppercase and lowercase, so we return an empty string.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def greatestLetter(self, s: str) -> str:
        mask1 = mask2 = 0
        for c in s:
            if c.islower():
                mask1 |= 1 << (ord(c) - ord("a"))
            else:
                mask2 |= 1 << (ord(c) - ord("A"))
        mask = mask1 & mask2
        return chr(mask.bit_length() - 1 + ord("A")) if mask else ""
```

#### Java

```java
class Solution {
    public String greatestLetter(String s) {
        int mask1 = 0, mask2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                mask1 |= 1 << (c - 'a');
            } else {
                mask2 |= 1 << (c - 'A');
            }
        }
        int mask = mask1 & mask2;
        return mask > 0 ? String.valueOf((char) (31 - Integer.numberOfLeadingZeros(mask) + 'A'))
                        : "";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string greatestLetter(string s) {
        int mask1 = 0, mask2 = 0;
        for (char& c : s) {
            if (islower(c)) {
                mask1 |= 1 << (c - 'a');
            } else {
                mask2 |= 1 << (c - 'A');
            }
        }
        int mask = mask1 & mask2;
        return mask ? string(1, 31 - __builtin_clz(mask) + 'A') : "";
    }
};
```

#### Go

```go
func greatestLetter(s string) string {
	mask1, mask2 := 0, 0
	for _, c := range s {
		if unicode.IsLower(c) {
			mask1 |= 1 << (c - 'a')
		} else {
			mask2 |= 1 << (c - 'A')
		}
	}
	mask := mask1 & mask2
	if mask == 0 {
		return ""
	}
	return string(byte(bits.Len(uint(mask))-1) + 'A')
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
