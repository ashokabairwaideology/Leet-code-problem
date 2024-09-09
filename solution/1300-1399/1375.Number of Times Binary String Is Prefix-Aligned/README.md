---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1375.Number%20of%20Times%20Binary%20String%20Is%20Prefix-Aligned/README.md
rating: 1438
source: 第 179 场周赛 Q2
tags:
    - 数组
---

<!-- problem:start -->

# [1375. 二进制字符串前缀一致的次数](https://leetcode.cn/problems/number-of-times-binary-string-is-prefix-aligned)

[English Version](/solution/1300-1399/1375.Number%20of%20Times%20Binary%20String%20Is%20Prefix-Aligned/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 、下标从 <strong>1</strong> 开始的二进制字符串，所有位最开始都是 <code>0</code> 。我们会按步翻转该二进制字符串的所有位（即，将 <code>0</code> 变为 <code>1</code>）。</p>

<p>给你一个下标从 <strong>1</strong> 开始的整数数组 <code>flips</code> ，其中 <code>flips[i]</code> 表示对应下标 <code>i</code> 的位将会在第 <code>i</code> 步翻转。</p>

<p>二进制字符串 <strong>前缀一致</strong> 需满足：在第 <code>i</code> 步之后，在 <strong>闭</strong> 区间&nbsp;<code>[1, i]</code> 内的所有位都是 1 ，而其他位都是 0 。</p>

<p>返回二进制字符串在翻转过程中 <strong>前缀一致</strong> 的次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>flips = [3,2,4,1,5]
<strong>输出：</strong>2
<strong>解释：</strong>二进制字符串最开始是 "00000" 。
执行第 1 步：字符串变为 "00100" ，不属于前缀一致的情况。
执行第 2 步：字符串变为 "01100" ，不属于前缀一致的情况。
执行第 3 步：字符串变为 "01110" ，不属于前缀一致的情况。
执行第 4 步：字符串变为 "11110" ，属于前缀一致的情况。
执行第 5 步：字符串变为 "11111" ，属于前缀一致的情况。
在翻转过程中，前缀一致的次数为 2 ，所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>flips = [4,1,2,3]
<strong>输出：</strong>1
<strong>解释：</strong>二进制字符串最开始是 "0000" 。
执行第 1 步：字符串变为 "0001" ，不属于前缀一致的情况。
执行第 2 步：字符串变为 "1001" ，不属于前缀一致的情况。
执行第 3 步：字符串变为 "1101" ，不属于前缀一致的情况。
执行第 4 步：字符串变为 "1111" ，属于前缀一致的情况。
在翻转过程中，前缀一致的次数为 1 ，所以返回 1 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == flips.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>flips</code> 是范围 <code>[1, n]</code> 中所有整数构成的一个排列</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接遍历

我们可以遍历数组 $flips$，记录当前遍历过的元素的最大值 $mx$，若 $mx$ 等于当前遍历到的下标 $i$，则说明前 $i$ 个元素都被翻转过了，即前缀一致，答案累加。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $flips$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numTimesAllBlue(self, flips: List[int]) -> int:
        ans = mx = 0
        for i, x in enumerate(flips, 1):
            mx = max(mx, x)
            ans += mx == i
        return ans
```

#### Java

```java
class Solution {
    public int numTimesAllBlue(int[] flips) {
        int ans = 0, mx = 0;
        for (int i = 1; i <= flips.length; ++i) {
            mx = Math.max(mx, flips[i - 1]);
            if (mx == i) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numTimesAllBlue(vector<int>& flips) {
        int ans = 0, mx = 0;
        for (int i = 1; i <= flips.size(); ++i) {
            mx = max(mx, flips[i - 1]);
            ans += mx == i;
        }
        return ans;
    }
};
```

#### Go

```go
func numTimesAllBlue(flips []int) (ans int) {
	mx := 0
	for i, x := range flips {
		mx = max(mx, x)
		if mx == i+1 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function numTimesAllBlue(flips: number[]): number {
    let ans = 0;
    let mx = 0;
    for (let i = 1; i <= flips.length; ++i) {
        mx = Math.max(mx, flips[i - 1]);
        if (mx === i) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
