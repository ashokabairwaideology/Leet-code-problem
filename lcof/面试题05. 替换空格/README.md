---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9805.%20%E6%9B%BF%E6%8D%A2%E7%A9%BA%E6%A0%BC/README.md
---

<!-- problem:start -->

# [面试题 05. 替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/)

## 题目描述

<!-- description:start -->

<p>请实现一个函数，把字符串 <code>s</code> 中的每个空格替换成&quot;%20&quot;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;We are happy.&quot;
<strong>输出：</strong>&quot;We%20are%20happy.&quot;</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= s 的长度 &lt;= 10000</code></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字符串内置方法

使用 `replace()` 方法。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def replaceSpace(self, s: str) -> str:
        return s.replace(' ', '%20')
```

#### Java

```java
class Solution {
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }
}
```

#### C++

```cpp
class Solution {
public:
    string replaceSpace(string s) {
        string ans;
        for (char ch : s) {
            if (ch == ' ')
                ans += "%20";
            else
                ans += ch;
        }
        return ans;
    }
};
```

#### Go

```go
func replaceSpace(s string) string {
	return strings.Replace(s, " ", "%20", -1)
}
```

#### TypeScript

```ts
function replaceSpace(s: string): string {
    return s.replace(/\s/g, '%20');
}
```

#### Rust

```rust
impl Solution {
    pub fn replace_space(s: String) -> String {
        s.replace(' ', "%20")
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {string}
 */
var replaceSpace = function (s) {
    return s.split(' ').join('%20');
};
```

#### C#

```cs
public class Solution {
    public string ReplaceSpace(string s) {
        return s.Replace(" ", "%20");
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return String
     */
    function replaceSpace($s) {
        $rs = '';
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] === ' ') {
                $rs = $rs . '%20';
            } else {
                $rs = $rs . $s[$i];
            }
        }
        return $rs;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：遍历替换

我们直接遍历字符串，遇到空格就替换成 `%20` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def replaceSpace(self, s: str) -> str:
        ans = []
        for c in s:
            ans.append('%20' if c == ' ' else c)
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String replaceSpace(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            ans.append(c == ' ' ? "%20" : c);
        }
        return ans.toString();
    }
}
```

#### Go

```go
func replaceSpace(s string) string {
	ans := strings.Builder{}
	for _, c := range s {
		if c == ' ' {
			ans.WriteString("%20")
		} else {
			ans.WriteRune(c)
		}
	}
	return ans.String()
}
```

#### TypeScript

```ts
function replaceSpace(s: string): string {
    const strArr = [];
    for (const c of s) {
        strArr.push(c === ' ' ? '%20' : c);
    }
    return strArr.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn replace_space(s: String) -> String {
        let mut result = String::new();
        for c in s.chars() {
            if c == ' ' {
                result.push_str("%20");
            } else {
                result.push(c);
            }
        }
        result
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {string}
 */
var replaceSpace = function (s) {
    return s.replace(/\s/g, '%20');
};
```

#### C#

```cs
public class Solution {
    public string ReplaceSpace(string s) {
        StringBuilder res = new StringBuilder();
        foreach (var c in s) {
            if (c == ' ') {
                res.Append("%20");
            } else {
                res.Append(c);
            }
        }
        return res.ToString();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法三

<!-- tabs:start -->

#### JavaScript

```js
/**
 * @param {string} s
 * @return {string}
 */
var replaceSpace = function (s) {
    const ans = [];
    for (const c of s) {
        ans.push(c === ' ' ? '%20' : c);
    }
    return ans.join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
