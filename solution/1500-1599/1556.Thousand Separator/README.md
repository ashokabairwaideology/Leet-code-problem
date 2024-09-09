---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1556.Thousand%20Separator/README.md
rating: 1271
source: 第 33 场双周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [1556. 千位分隔数](https://leetcode.cn/problems/thousand-separator)

[English Version](/solution/1500-1599/1556.Thousand%20Separator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>n</code>，请你每隔三位添加点（即 &quot;.&quot; 符号）作为千位分隔符，并将结果以字符串格式返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 987
<strong>输出：</strong>&quot;987&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 1234
<strong>输出：</strong>&quot;1.234&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 123456789
<strong>输出：</strong>&quot;123.456.789&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 0
<strong>输出：</strong>&quot;0&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt; 2^31</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

直接按照题目要求模拟即可。

时间复杂度 $O(\log n)$，忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def thousandSeparator(self, n: int) -> str:
        cnt = 0
        ans = []
        while 1:
            n, v = divmod(n, 10)
            ans.append(str(v))
            cnt += 1
            if n == 0:
                break
            if cnt == 3:
                ans.append('.')
                cnt = 0
        return ''.join(ans[::-1])
```

#### Java

```java
class Solution {
    public String thousandSeparator(int n) {
        int cnt = 0;
        StringBuilder ans = new StringBuilder();
        while (true) {
            int v = n % 10;
            n /= 10;
            ans.append(v);
            ++cnt;
            if (n == 0) {
                break;
            }
            if (cnt == 3) {
                ans.append('.');
                cnt = 0;
            }
        }
        return ans.reverse().toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string thousandSeparator(int n) {
        int cnt = 0;
        string ans;
        while (1) {
            int v = n % 10;
            n /= 10;
            ans += to_string(v);
            if (n == 0) break;
            if (++cnt == 3) {
                ans += '.';
                cnt = 0;
            }
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

#### Go

```go
func thousandSeparator(n int) string {
	cnt := 0
	ans := []byte{}
	for {
		v := n % 10
		n /= 10
		ans = append(ans, byte('0'+v))
		if n == 0 {
			break
		}
		cnt++
		if cnt == 3 {
			ans = append(ans, '.')
			cnt = 0
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
