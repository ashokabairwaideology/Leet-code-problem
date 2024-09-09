---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0869.Reordered%20Power%20of%202/README_EN.md
tags:
    - Hash Table
    - Math
    - Counting
    - Enumeration
    - Sorting
---

<!-- problem:start -->

# [869. Reordered Power of 2](https://leetcode.com/problems/reordered-power-of-2)

[中文文档](/solution/0800-0899/0869.Reordered%20Power%20of%202/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>. We reorder the digits in any order (including the original order) such that the leading digit is not zero.</p>

<p>Return <code>true</code> <em>if and only if we can do this so that the resulting number is a power of two</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reorderedPowerOf2(self, n: int) -> bool:
        def convert(n):
            cnt = [0] * 10
            while n:
                n, v = divmod(n, 10)
                cnt[v] += 1
            return cnt

        i, s = 1, convert(n)
        while i <= 10**9:
            if convert(i) == s:
                return True
            i <<= 1
        return False
```

#### Java

```java
class Solution {
    public boolean reorderedPowerOf2(int n) {
        String s = convert(n);
        for (int i = 1; i <= Math.pow(10, 9); i <<= 1) {
            if (s.equals(convert(i))) {
                return true;
            }
        }
        return false;
    }

    private String convert(int n) {
        char[] cnt = new char[10];
        for (; n > 0; n /= 10) {
            cnt[n % 10]++;
        }
        return new String(cnt);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool reorderedPowerOf2(int n) {
        vector<int> s = convert(n);
        for (int i = 1; i <= pow(10, 9); i <<= 1)
            if (s == convert(i))
                return true;
        return false;
    }

    vector<int> convert(int n) {
        vector<int> cnt(10);
        for (; n; n /= 10) ++cnt[n % 10];
        return cnt;
    }
};
```

#### Go

```go
func reorderedPowerOf2(n int) bool {
	convert := func(n int) []byte {
		cnt := make([]byte, 10)
		for ; n > 0; n /= 10 {
			cnt[n%10]++
		}
		return cnt
	}
	s := convert(n)
	for i := 1; i <= 1e9; i <<= 1 {
		if bytes.Equal(s, convert(i)) {
			return true
		}
	}
	return false
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
