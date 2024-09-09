---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0823.Binary%20Trees%20With%20Factors/README_EN.md
tags:
    - Array
    - Hash Table
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [823. Binary Trees With Factors](https://leetcode.com/problems/binary-trees-with-factors)

[中文文档](/solution/0800-0899/0823.Binary%20Trees%20With%20Factors/README.md)

## Description

<!-- description:start -->

<p>Given an array of unique integers, <code>arr</code>, where each integer <code>arr[i]</code> is strictly greater than <code>1</code>.</p>

<p>We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node&#39;s value should be equal to the product of the values of its children.</p>

<p>Return <em>the number of binary trees we can make</em>. The answer may be too large so return the answer <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can make these trees: <code>[2], [4], [4, 2, 2]</code></pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,4,5,10]
<strong>Output:</strong> 7
<strong>Explanation:</strong> We can make these trees: <code>[2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2]</code>.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>2 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
	<li>All the values of <code>arr</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numFactoredBinaryTrees(self, arr: List[int]) -> int:
        mod = 10**9 + 7
        n = len(arr)
        arr.sort()
        idx = {v: i for i, v in enumerate(arr)}
        f = [1] * n
        for i, a in enumerate(arr):
            for j in range(i):
                b = arr[j]
                if a % b == 0 and (c := (a // b)) in idx:
                    f[i] = (f[i] + f[j] * f[idx[c]]) % mod
        return sum(f) % mod
```

#### Java

```java
class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        final int mod = (int) 1e9 + 7;
        Arrays.sort(arr);
        int n = arr.length;
        long[] f = new long[n];
        Arrays.fill(f, 1);
        Map<Integer, Integer> idx = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            idx.put(arr[i], i);
        }
        for (int i = 0; i < n; ++i) {
            int a = arr[i];
            for (int j = 0; j < i; ++j) {
                int b = arr[j];
                if (a % b == 0) {
                    int c = a / b;
                    if (idx.containsKey(c)) {
                        int k = idx.get(c);
                        f[i] = (f[i] + f[j] * f[k]) % mod;
                    }
                }
            }
        }
        long ans = 0;
        for (long v : f) {
            ans = (ans + v) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numFactoredBinaryTrees(vector<int>& arr) {
        const int mod = 1e9 + 7;
        sort(arr.begin(), arr.end());
        unordered_map<int, int> idx;
        int n = arr.size();
        for (int i = 0; i < n; ++i) {
            idx[arr[i]] = i;
        }
        vector<long> f(n, 1);
        for (int i = 0; i < n; ++i) {
            int a = arr[i];
            for (int j = 0; j < i; ++j) {
                int b = arr[j];
                if (a % b == 0) {
                    int c = a / b;
                    if (idx.count(c)) {
                        int k = idx[c];
                        f[i] = (f[i] + 1l * f[j] * f[k]) % mod;
                    }
                }
            }
        }
        long ans = 0;
        for (long v : f) {
            ans = (ans + v) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func numFactoredBinaryTrees(arr []int) int {
	const mod int = 1e9 + 7
	sort.Ints(arr)
	f := make([]int, len(arr))
	idx := map[int]int{}
	for i, v := range arr {
		f[i] = 1
		idx[v] = i
	}
	for i, a := range arr {
		for j := 0; j < i; j++ {
			b := arr[j]
			if c := a / b; a%b == 0 {
				if k, ok := idx[c]; ok {
					f[i] = (f[i] + f[j]*f[k]) % mod
				}
			}
		}
	}
	ans := 0
	for _, v := range f {
		ans = (ans + v) % mod
	}
	return ans
}
```

#### TypeScript

```ts
function numFactoredBinaryTrees(arr: number[]): number {
    const mod = 10 ** 9 + 7;
    arr.sort((a, b) => a - b);
    const idx: Map<number, number> = new Map();
    const n = arr.length;
    for (let i = 0; i < n; ++i) {
        idx.set(arr[i], i);
    }
    const f: number[] = new Array(n).fill(1);
    for (let i = 0; i < n; ++i) {
        const a = arr[i];
        for (let j = 0; j < i; ++j) {
            const b = arr[j];
            if (a % b === 0) {
                const c = a / b;
                if (idx.has(c)) {
                    const k = idx.get(c)!;
                    f[i] = (f[i] + f[j] * f[k]) % mod;
                }
            }
        }
    }
    return f.reduce((a, b) => a + b) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
