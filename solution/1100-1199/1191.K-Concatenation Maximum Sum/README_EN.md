---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1191.K-Concatenation%20Maximum%20Sum/README_EN.md
rating: 1747
source: Weekly Contest 154 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1191. K-Concatenation Maximum Sum](https://leetcode.com/problems/k-concatenation-maximum-sum)

[中文文档](/solution/1100-1199/1191.K-Concatenation%20Maximum%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>arr</code> and an integer <code>k</code>, modify the array by repeating it <code>k</code> times.</p>

<p>For example, if <code>arr = [1, 2]</code> and <code>k = 3 </code>then the modified array will be <code>[1, 2, 1, 2, 1, 2]</code>.</p>

<p>Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be <code>0</code> and its sum in that case is <code>0</code>.</p>

<p>As the answer can be very large, return the answer <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2], k = 3
<strong>Output:</strong> 9
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,-2,1], k = 5
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [-1,-2], k = 7
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Case Discussion

We denote the sum of all elements in the array $arr$ as $s$, the maximum prefix sum as $mxPre$, the minimum prefix sum as $miPre$, and the maximum subarray sum as $mxSub$.

We traverse the array $arr$. For each element $x$, we update $s = s + x$, $mxPre = \max(mxPre, s)$, $miPre = \min(miPre, s)$, $mxSub = \max(mxSub, s - miPre)$.

Next, we consider the value of $k$:

-   When $k = 1$, the answer is $mxSub$.
-   When $k \ge 2$, if the maximum subarray spans two $arr$, then the answer is $mxPre + mxSuf$, where $mxSuf = s - miPre$.
-   When $k \ge 2$ and $s > 0$, if the maximum subarray spans three $arr$, then the answer is $(k - 2) \times s + mxPre + mxSuf$.

Finally, we return the result of the answer modulo $10^9 + 7$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $arr$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kConcatenationMaxSum(self, arr: List[int], k: int) -> int:
        s = mx_pre = mi_pre = mx_sub = 0
        for x in arr:
            s += x
            mx_pre = max(mx_pre, s)
            mi_pre = min(mi_pre, s)
            mx_sub = max(mx_sub, s - mi_pre)
        ans = mx_sub
        mod = 10**9 + 7
        if k == 1:
            return ans % mod
        mx_suf = s - mi_pre
        ans = max(ans, mx_pre + mx_suf)
        if s > 0:
            ans = max(ans, (k - 2) * s + mx_pre + mx_suf)
        return ans % mod
```

#### Java

```java
class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long s = 0, mxPre = 0, miPre = 0, mxSub = 0;
        for (int x : arr) {
            s += x;
            mxPre = Math.max(mxPre, s);
            miPre = Math.min(miPre, s);
            mxSub = Math.max(mxSub, s - miPre);
        }
        long ans = mxSub;
        final int mod = (int) 1e9 + 7;
        if (k == 1) {
            return (int) (ans % mod);
        }
        long mxSuf = s - miPre;
        ans = Math.max(ans, mxPre + mxSuf);
        if (s > 0) {
            ans = Math.max(ans, (k - 2) * s + mxPre + mxSuf);
        }
        return (int) (ans % mod);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kConcatenationMaxSum(vector<int>& arr, int k) {
        long s = 0, mxPre = 0, miPre = 0, mxSub = 0;
        for (int x : arr) {
            s += x;
            mxPre = max(mxPre, s);
            miPre = min(miPre, s);
            mxSub = max(mxSub, s - miPre);
        }
        long ans = mxSub;
        const int mod = 1e9 + 7;
        if (k == 1) {
            return ans % mod;
        }
        long mxSuf = s - miPre;
        ans = max(ans, mxPre + mxSuf);
        if (s > 0) {
            ans = max(ans, mxPre + (k - 2) * s + mxSuf);
        }
        return ans % mod;
    }
};
```

#### Go

```go
func kConcatenationMaxSum(arr []int, k int) int {
	var s, mxPre, miPre, mxSub int
	for _, x := range arr {
		s += x
		mxPre = max(mxPre, s)
		miPre = min(miPre, s)
		mxSub = max(mxSub, s-miPre)
	}
	const mod = 1e9 + 7
	ans := mxSub
	if k == 1 {
		return ans % mod
	}
	mxSuf := s - miPre
	ans = max(ans, mxSuf+mxPre)
	if s > 0 {
		ans = max(ans, mxSuf+(k-2)*s+mxPre)
	}
	return ans % mod
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
