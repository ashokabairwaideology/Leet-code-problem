---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1652.Defuse%20the%20Bomb/README_EN.md
rating: 1416
source: Biweekly Contest 39 Q1
tags:
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [1652. Defuse the Bomb](https://leetcode.com/problems/defuse-the-bomb)

[中文文档](/solution/1600-1699/1652.Defuse%20the%20Bomb/README.md)

## Description

<!-- description:start -->

<p>You have a bomb to defuse, and your time is running out! Your informer will provide you with a <strong>circular</strong> array <code>code</code>&nbsp;of length of <code>n</code>&nbsp;and a key <code>k</code>.</p>

<p>To decrypt the code, you must replace every number. All the numbers are replaced <strong>simultaneously</strong>.</p>

<ul>
	<li>If <code>k &gt; 0</code>, replace the <code>i<sup>th</sup></code> number with the sum of the <strong>next</strong> <code>k</code> numbers.</li>
	<li>If <code>k &lt; 0</code>, replace the <code>i<sup>th</sup></code> number with the sum of the <strong>previous</strong> <code>k</code> numbers.</li>
	<li>If <code>k == 0</code>, replace the <code>i<sup>th</sup></code> number with <code>0</code>.</li>
</ul>

<p>As <code>code</code> is circular, the next element of <code>code[n-1]</code> is <code>code[0]</code>, and the previous element of <code>code[0]</code> is <code>code[n-1]</code>.</p>

<p>Given the <strong>circular</strong> array <code>code</code> and an integer key <code>k</code>, return <em>the decrypted code to defuse the bomb</em>!</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> code = [5,7,1,4], k = 3
<strong>Output:</strong> [12,10,16,13]
<strong>Explanation:</strong> Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> code = [1,2,3,4], k = 0
<strong>Output:</strong> [0,0,0,0]
<strong>Explanation:</strong> When k is zero, the numbers are replaced by 0. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> code = [2,4,9,3], k = -2
<strong>Output:</strong> [12,5,6,13]
<strong>Explanation:</strong> The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the <strong>previous</strong> numbers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == code.length</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 100</code></li>
	<li><code>1 &lt;= code[i] &lt;= 100</code></li>
	<li><code>-(n - 1) &lt;= k &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We define an answer array `ans` of length `n`, initially all elements are `0`. According to the problem, if `k` is `0`, return `ans` directly.

Otherwise, we traverse each position `i`:

-   If `k` is a positive number, then the value at position `i` is the sum of the values at the `k` positions after position `i`, that is:

$$
ans[i] = \sum_{j=i+1}^{i+k} code[j \bmod n]
$$

-   If `k` is a negative number, then the value at position `i` is the sum of the values at the `|k|` positions before position `i`, that is:

$$
ans[i] = \sum_{j=i+k}^{i-1} code[(j+n) \bmod n]
$$

The time complexity is $O(n \times |k|)$, ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def decrypt(self, code: List[int], k: int) -> List[int]:
        n = len(code)
        ans = [0] * n
        if k == 0:
            return ans
        for i in range(n):
            if k > 0:
                for j in range(i + 1, i + k + 1):
                    ans[i] += code[j % n]
            else:
                for j in range(i + k, i):
                    ans[i] += code[(j + n) % n]
        return ans
```

#### Java

```java
class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                for (int j = i + 1; j < i + k + 1; ++j) {
                    ans[i] += code[j % n];
                }
            } else {
                for (int j = i + k; j < i; ++j) {
                    ans[i] += code[(j + n) % n];
                }
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
    vector<int> decrypt(vector<int>& code, int k) {
        int n = code.size();
        vector<int> ans(n);
        if (k == 0) {
            return ans;
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                for (int j = i + 1; j < i + k + 1; ++j) {
                    ans[i] += code[j % n];
                }
            } else {
                for (int j = i + k; j < i; ++j) {
                    ans[i] += code[(j + n) % n];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func decrypt(code []int, k int) []int {
	n := len(code)
	ans := make([]int, n)
	if k == 0 {
		return ans
	}
	for i := 0; i < n; i++ {
		if k > 0 {
			for j := i + 1; j < i+k+1; j++ {
				ans[i] += code[j%n]
			}
		} else {
			for j := i + k; j < i; j++ {
				ans[i] += code[(j+n)%n]
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function decrypt(code: number[], k: number): number[] {
    const n: number = code.length;
    const ans: number[] = Array(n).fill(0);

    if (k === 0) {
        return ans;
    }

    for (let i = 0; i < n; ++i) {
        if (k > 0) {
            for (let j = i + 1; j < i + k + 1; ++j) {
                ans[i] += code[j % n];
            }
        } else {
            for (let j = i + k; j < i; ++j) {
                ans[i] += code[(j + n) % n];
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Prefix Sum

In Solution 1, for each position $i$, we need to traverse $k$ positions, which involves a lot of repeated calculations. We can optimize this by using prefix sums.

We duplicate the `code` array (this can be achieved without actually duplicating the array, but by cyclically traversing with modulo operation), resulting in an array of twice the length. We then calculate the prefix sum of this array, resulting in a prefix sum array $s$ of length $2 \times n + 1$.

If $k$ is a positive number, then the value at position $i$ is the sum of the values at the $k$ positions after position $i$, i.e., $ans[i] = s[i + k + 1] - s[i + 1]$.

If $k$ is a negative number, then the value at position $i$ is the sum of the values at the $|k|$ positions before position $i$, i.e., $ans[i] = s[i + n] - s[i + k + n]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the `code` array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def decrypt(self, code: List[int], k: int) -> List[int]:
        n = len(code)
        ans = [0] * n
        if k == 0:
            return ans
        s = list(accumulate(code + code, initial=0))
        for i in range(n):
            if k > 0:
                ans[i] = s[i + k + 1] - s[i + 1]
            else:
                ans[i] = s[i + n] - s[i + k + n]
        return ans
```

#### Java

```java
class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        int[] s = new int[n << 1 | 1];
        for (int i = 0; i < n << 1; ++i) {
            s[i + 1] = s[i] + code[i % n];
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                ans[i] = s[i + k + 1] - s[i + 1];
            } else {
                ans[i] = s[i + n] - s[i + k + n];
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
    vector<int> decrypt(vector<int>& code, int k) {
        int n = code.size();
        vector<int> ans(n);
        if (k == 0) {
            return ans;
        }
        vector<int> s(n << 1 | 1);
        for (int i = 0; i < n << 1; ++i) {
            s[i + 1] = s[i] + code[i % n];
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                ans[i] = s[i + k + 1] - s[i + 1];
            } else {
                ans[i] = s[i + n] - s[i + k + n];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func decrypt(code []int, k int) []int {
	n := len(code)
	ans := make([]int, n)
	if k == 0 {
		return ans
	}
	s := make([]int, n<<1|1)
	for i := 0; i < n<<1; i++ {
		s[i+1] = s[i] + code[i%n]
	}
	for i := range code {
		if k > 0 {
			ans[i] = s[i+k+1] - s[i+1]
		} else {
			ans[i] = s[i+n] - s[i+k+n]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function decrypt(code: number[], k: number): number[] {
    const n: number = code.length;
    const ans: number[] = Array(n).fill(0);

    if (k === 0) {
        return ans;
    }

    const s: number[] = Array((n << 1) | 1).fill(0);
    for (let i = 0; i < n << 1; ++i) {
        s[i + 1] = s[i] + code[i % n];
    }

    for (let i = 0; i < n; ++i) {
        if (k > 0) {
            ans[i] = s[i + k + 1] - s[i + 1];
        } else {
            ans[i] = s[i + n] - s[i + k + n];
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
