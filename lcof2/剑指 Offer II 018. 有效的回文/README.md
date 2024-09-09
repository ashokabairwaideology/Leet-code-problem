---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20018.%20%E6%9C%89%E6%95%88%E7%9A%84%E5%9B%9E%E6%96%87/README.md
---

<!-- problem:start -->

# [剑指 Offer II 018. 有效的回文](https://leetcode.cn/problems/XltzEq)

## 题目描述

<!-- description:start -->

<p>给定一个字符串 <code>s</code> ，验证 <code>s</code>&nbsp;是否是&nbsp;<strong>回文串&nbsp;</strong>，只考虑字母和数字字符，可以忽略字母的大小写。</p>

<p>本题中，将空字符串定义为有效的&nbsp;<strong>回文串&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>s =<strong> </strong>&quot;A man, a plan, a canal: Panama&quot;
<strong>输出:</strong> true
<strong>解释：</strong>&quot;amanaplanacanalpanama&quot; 是回文串</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;race a car&quot;
<strong>输出:</strong> false
解释：&quot;raceacar&quot; 不是回文串</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li>字符串 <code>s</code> 由 ASCII 字符组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 125&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/valid-palindrome/">https://leetcode.cn/problems/valid-palindrome/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们定义两个指针 $i$ 和 $j$，初始时分别指向字符串的首尾位置，每次判断两个指针指向的字符是否为数字或字母，如果两个指针指向的字符都为数字或字母时，判断两个指针指向的字符是否相同（忽略大小写），如果不相同则返回 `false`，否则将两个指针向中间移动一位，直到两个指针相遇时返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPalindrome(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        while i < j:
            while i < j and not s[i].isalnum():
                i += 1
            while i < j and not s[j].isalnum():
                j -= 1
            if s[i].lower() != s[j].lower():
                return False
            i, j = i + 1, j - 1
        return True
```

#### Java

```java
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPalindrome(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            while (i < j && !isalnum(s[i])) {
                ++i;
            }
            while (i < j && !isalnum(s[j])) {
                --j;
            }
            if (tolower(s[i]) != tolower(s[j])) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }
};
```

#### Go

```go
func isPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for i < j {
		for i < j && !isalnum(s[i]) {
			i++
		}
		for i < j && !isalnum(s[j]) {
			j--
		}
		if tolower(s[i]) != tolower(s[j]) {
			return false
		}
		i, j = i+1, j-1
	}
	return true
}

func tolower(b byte) byte {
	if b >= 'A' && b <= 'Z' {
		return b - 'A' + 'a'
	}
	return b
}

func isalnum(b byte) bool {
	return b >= '0' && b <= '9' ||
		b >= 'a' && b <= 'z' ||
		b >= 'A' && b <= 'Z'
}
```

#### TypeScript

```ts
function isPalindrome(s: string): boolean {
    const str = s.replace(/[^a-zA-Z0-9]/g, '');
    let l = 0;
    let r = str.length - 1;
    while (l < r) {
        if (str[l].toLocaleLowerCase() !== str[r].toLocaleLowerCase()) {
            return false;
        }
        l++;
        r--;
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_palindrome(s: String) -> bool {
        let ss: Vec<char> = s.chars().collect();
        let mut l = 0;
        let mut r = ss.len() - 1;
        while l < r {
            while l < r && !(ss[l].is_alphabetic() || ss[l].is_numeric()) {
                l += 1;
            }
            while l < r && !(ss[r].is_alphabetic() || ss[r].is_numeric()) {
                r -= 1;
            }
            if ss[l].to_ascii_lowercase() != ss[r].to_ascii_lowercase() {
                return false;
            }
            // 防止 usize 破界
            if r == 0 {
                return true;
            }
            l += 1;
            r -= 1;
        }
        true
    }
}
```

#### Swift

```swift
class Solution {
    func isPalindrome(_ s: String) -> Bool {
        var i = s.startIndex
        var j = s.index(before: s.endIndex)

        while i < j {
            while i < j && !s[i].isLetter && !s[i].isNumber {
                i = s.index(after: i)
            }
            while i < j && !s[j].isLetter && !s[j].isNumber {
                j = s.index(before: j)
            }
            if i >= j {
                break
            }
            if s[i].lowercased() != s[j].lowercased() {
                return false
            }
            i = s.index(after: i)
            j = s.index(before: j)
        }
        return true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
