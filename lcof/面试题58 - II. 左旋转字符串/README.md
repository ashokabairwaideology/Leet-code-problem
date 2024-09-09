---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9858%20-%20II.%20%E5%B7%A6%E6%97%8B%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2/README.md
---

<!-- problem:start -->

# [面试题 58 - II. 左旋转字符串](https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/)

## 题目描述

<!-- description:start -->

<p>字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串&quot;abcdefg&quot;和数字2，该函数将返回左旋转两位得到的结果&quot;cdefgab&quot;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> s = &quot;abcdefg&quot;, k = 2
<strong>输出:&nbsp;</strong>&quot;cdefgab&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> s = &quot;lrloseumgh&quot;, k = 6
<strong>输出:&nbsp;</strong>&quot;umghlrlose&quot;
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt; s.length &lt;= 10000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以将字符串分为两部分，分别对两部分进行翻转，然后再对整个字符串进行翻转，即可得到结果。或者直接截取两个子串，然后拼接起来。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseLeftWords(self, s: str, n: int) -> str:
        return s[n:] + s[:n]
```

#### Java

```java
class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reverseLeftWords(string s, int n) {
        return s.substr(n) + s.substr(0, n);
    }
};
```

#### Go

```go
func reverseLeftWords(s string, n int) string {
	return s[n:] + s[:n]
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_left_words(s: String, n: i32) -> String {
        let n = n as usize;
        String::from(&s[n..]) + &s[..n]
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {number} n
 * @return {string}
 */
var reverseLeftWords = function (s, n) {
    return s.substring(n) + s.substring(0, n);
};
```

#### C#

```cs
public class Solution {
    public string ReverseLeftWords(string s, int n) {
        return s.Substring(n) + s.Substring(0, n);
    }
}
```

#### Swift

```swift
class Solution {
    func reverseLeftWords(_ s: String, _ n: Int) -> String {
        let leftIndex = s.index(s.startIndex, offsetBy: n)
        let rightPart = s[leftIndex..<s.endIndex]
        let leftPart = s[s.startIndex..<leftIndex]
        return String(rightPart) + String(leftPart)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
