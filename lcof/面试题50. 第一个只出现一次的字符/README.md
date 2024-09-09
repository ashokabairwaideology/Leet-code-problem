---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9850.%20%E7%AC%AC%E4%B8%80%E4%B8%AA%E5%8F%AA%E5%87%BA%E7%8E%B0%E4%B8%80%E6%AC%A1%E7%9A%84%E5%AD%97%E7%AC%A6/README.md
---

<!-- problem:start -->

# [面试题 50. 第一个只出现一次的字符](https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/)

## 题目描述

<!-- description:start -->

<p>在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。</p>

<p><strong>示例 1:</strong></p>

<pre>
输入：s = "abaccdeff"
输出：'b'
</pre>

<p><strong>示例 2:</strong></p>

<pre>
输入：s = "" 
输出：' '
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= s 的长度 &lt;= 50000</code></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数组或哈希表

我们可以使用哈希表或数组 $cnt$ 来统计每个字符出现的次数，然后再遍历一遍字符串，找到第一个出现次数为 $1$ 的字符。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串长度；而 $C$ 为字符集大小，本题中 $C=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstUniqChar(self, s: str) -> str:
        cnt = Counter(s)
        for c in s:
            if cnt[c] == 1:
                return c
        return " "
```

#### Java

```java
class Solution {
    public char firstUniqChar(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
```

#### C++

```cpp
class Solution {
public:
    char firstUniqChar(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        for (char& c : s) {
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
};
```

#### Go

```go
func firstUniqChar(s string) byte {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, c := range s {
		if cnt[c-'a'] == 1 {
			return byte(c)
		}
	}
	return ' '
}
```

#### TypeScript

```ts
function firstUniqChar(s: string): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (const c of s) {
        if (cnt[c.charCodeAt(0) - 97] === 1) {
            return c;
        }
    }
    return ' ';
}
```

#### Rust

```rust
impl Solution {
    pub fn first_uniq_char(s: String) -> char {
        let mut cnt = [0; 26];
        for c in s.chars() {
            cnt[(c as usize) - ('a' as usize)] += 1;
        }
        for c in s.chars() {
            if cnt[(c as usize) - ('a' as usize)] == 1 {
                return c;
            }
        }
        ' '
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {character}
 */
var firstUniqChar = function (s) {
    const cnt = Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (const c of s) {
        if (cnt[c.charCodeAt(0) - 97] === 1) {
            return c;
        }
    }
    return ' ';
};
```

#### C#

```cs
public class Solution {
    public char FirstUniqChar(string s) {
        var cnt = new int[26];
        foreach(var c in s) {
            cnt[c - 'a'] ++;
        }
        foreach(var c in s) {
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
```

#### Swift

```swift
class Solution {
    func firstUniqChar(_ s: String) -> Character {
        var count = [Int](repeating: 0, count: 26)
        let aAsciiValue = Int(Character("a").asciiValue!)

        for char in s {
            count[Int(char.asciiValue!) - aAsciiValue] += 1
        }

        for char in s {
            if count[Int(char.asciiValue!) - aAsciiValue] == 1 {
                return char
            }
        }

        return " "
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
