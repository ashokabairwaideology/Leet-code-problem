---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20019.%20%E6%9C%80%E5%A4%9A%E5%88%A0%E9%99%A4%E4%B8%80%E4%B8%AA%E5%AD%97%E7%AC%A6%E5%BE%97%E5%88%B0%E5%9B%9E%E6%96%87/README.md
---

<!-- problem:start -->

# [剑指 Offer II 019. 最多删除一个字符得到回文](https://leetcode.cn/problems/RQku0D)

## 题目描述

<!-- description:start -->

<p>给定一个非空字符串&nbsp;<code>s</code>，请判断如果&nbsp;<strong>最多 </strong>从字符串中删除一个字符能否得到一个回文字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;aba&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;abca&quot;
<strong>输出:</strong> true
<strong>解释:</strong> 可以删除 &quot;c&quot; 字符 或者 &quot;b&quot; 字符
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;abc&quot;
<strong>输出:</strong> false</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 680&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/valid-palindrome-ii/">https://leetcode.cn/problems/valid-palindrome-ii/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 分别指向字符串 $s$ 的第一个字符和最后一个字符，然后向中间移动指针，每次判断 $s[i]$ 和 $s[j]$ 是否相等：

-   如果 $s[i] = s[j]$，则指针 $i$ 向后移动一位，指针 $j$ 向前移动一位；
-   否则，存在两种情况，即删除字符 $s[i]$ 或者删除字符 $s[j]$，然后判断删除之后的字符串是否是回文字符串。即判断子串 $s[i+1..j]$ 或者子串 $s[i..j-1]$ 是否是回文字符串。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validPalindrome(self, s: str) -> bool:
        def check(i: int, j: int) -> bool:
            while i < j:
                if s[i] != s[j]:
                    return False
                i, j = i + 1, j - 1
            return True

        i, j = 0, len(s) - 1
        while i < j:
            if s[i] != s[j]:
                return check(i + 1, j) or check(i, j - 1)
            i, j = i + 1, j - 1
        return True
```

#### Java

```java
class Solution {
    private String s;

    public boolean validPalindrome(String s) {
        this.s = s;
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return check(i + 1, j) || check(i, j - 1);
            }
        }
        return true;
    }

    private boolean check(int i, int j) {
        for (; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
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
    bool validPalindrome(string s) {
        auto check = [&](int i, int j) {
            for (; i < j; ++i, --j) {
                if (s[i] != s[j]) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (s[i] != s[j]) {
                return check(i + 1, j) || check(i, j - 1);
            }
        }
        return true;
    }
};
```

#### Go

```go
func validPalindrome(s string) bool {
	check := func(i, j int) bool {
		for ; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			return check(i+1, j) || check(i, j-1)
		}
	}
	return true
}
```

#### TypeScript

```ts
function validPalindrome(s: string): boolean {
    const check = (i: number, j: number): boolean => {
        for (; i < j; ++i, --j) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s[i] !== s[j]) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var validPalindrome = function (s) {
    const check = (i, j) => {
        for (; i < j; ++i, --j) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s[i] !== s[j]) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
};
```

#### Swift

```swift
class Solution {
    private var s: String = ""

    func validPalindrome(_ s: String) -> Bool {
        self.s = s
        var i = s.startIndex
        var j = s.index(before: s.endIndex)

        while i < j {
            if s[i] != s[j] {
                return check(s.index(after: i), j) || check(i, s.index(before: j))
            }
            i = s.index(after: i)
            j = s.index(before: j)
        }
        return true
    }

    private func check(_ i: String.Index, _ j: String.Index) -> Bool {
        var i = i
        var j = j
        while i < j {
            if s[i] != s[j] {
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
