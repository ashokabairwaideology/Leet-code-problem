---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0246.Strobogrammatic%20Number/README.md
tags:
    - 哈希表
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [246. 中心对称数 🔒](https://leetcode.cn/problems/strobogrammatic-number)

[English Version](/solution/0200-0299/0246.Strobogrammatic%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>中心对称数是指一个数字在旋转了&nbsp;180 度之后看起来依旧相同的数字（或者上下颠倒地看）。</p>

<p>请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> num = &quot;69&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> num = &quot;88&quot;
<strong>输出:</strong> true</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> num = &quot;962&quot;
<strong>输出:</strong> false</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>num = &quot;1&quot;
<strong>输出：</strong>true
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针模拟

我们定义一个数组 $d$，其中 $d[i]$ 表示数字 $i$ 旋转 180° 之后的数字。如果 $d[i]$ 为 $-1$，表示数字 $i$ 不能旋转 180° 得到一个数字。

定义两个指针 $i$ 和 $j$，分别指向字符串的左右两端，然后不断向中间移动指针，判断 $d[num[i]]$ 和 $num[j]$ 是否相等，如果不相等，说明该字符串不是中心对称数，直接返回 $false$ 即可。如果 $i \gt j$，说明遍历完了字符串，返回 $true$。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isStrobogrammatic(self, num: str) -> bool:
        d = [0, 1, -1, -1, -1, -1, 9, -1, 8, 6]
        i, j = 0, len(num) - 1
        while i <= j:
            a, b = int(num[i]), int(num[j])
            if d[a] != b:
                return False
            i, j = i + 1, j - 1
        return True
```

#### Java

```java
class Solution {
    public boolean isStrobogrammatic(String num) {
        int[] d = new int[] {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        for (int i = 0, j = num.length() - 1; i <= j; ++i, --j) {
            int a = num.charAt(i) - '0', b = num.charAt(j) - '0';
            if (d[a] != b) {
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
    bool isStrobogrammatic(string num) {
        vector<int> d = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        for (int i = 0, j = num.size() - 1; i <= j; ++i, --j) {
            int a = num[i] - '0', b = num[j] - '0';
            if (d[a] != b) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isStrobogrammatic(num string) bool {
	d := []int{0, 1, -1, -1, -1, -1, 9, -1, 8, 6}
	for i, j := 0, len(num)-1; i <= j; i, j = i+1, j-1 {
		a, b := int(num[i]-'0'), int(num[j]-'0')
		if d[a] != b {
			return false
		}
	}
	return true
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
