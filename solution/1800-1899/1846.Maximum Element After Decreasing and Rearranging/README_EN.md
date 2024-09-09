---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1846.Maximum%20Element%20After%20Decreasing%20and%20Rearranging/README_EN.md
rating: 1454
source: Biweekly Contest 51 Q3
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging)

[中文文档](/solution/1800-1899/1846.Maximum%20Element%20After%20Decreasing%20and%20Rearranging/README.md)

## Description

<!-- description:start -->

<p>You are given an array of positive integers <code>arr</code>. Perform some operations (possibly none) on <code>arr</code> so that it satisfies these conditions:</p>

<ul>
	<li>The value of the <strong>first</strong> element in <code>arr</code> must be <code>1</code>.</li>
	<li>The absolute difference between any 2 adjacent elements must be <strong>less than or equal to </strong><code>1</code>. In other words, <code>abs(arr[i] - arr[i - 1]) &lt;= 1</code> for each <code>i</code> where <code>1 &lt;= i &lt; arr.length</code> (<strong>0-indexed</strong>). <code>abs(x)</code> is the absolute value of <code>x</code>.</li>
</ul>

<p>There are 2 types of operations that you can perform any number of times:</p>

<ul>
	<li><strong>Decrease</strong> the value of any element of <code>arr</code> to a <strong>smaller positive integer</strong>.</li>
	<li><strong>Rearrange</strong> the elements of <code>arr</code> to be in any order.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> possible value of an element in </em><code>arr</code><em> after performing the operations to satisfy the conditions</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,1,2,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
We can satisfy the conditions by rearranging <code>arr</code> so it becomes <code>[1,2,2,2,1]</code>.
The largest element in <code>arr</code> is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [100,1,1000]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
One possible way to satisfy the conditions is by doing the following:
1. Rearrange <code>arr</code> so it becomes <code>[1,100,1000]</code>.
2. Decrease the value of the second element to 2.
3. Decrease the value of the third element to 3.
Now <code>arr = [1,2,3]</code>, which<code> </code>satisfies the conditions.
The largest element in <code>arr is 3.</code>
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The array already satisfies the conditions, and the largest element is 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Greedy Algorithm

First, we sort the array and then set the first element of the array to $1$.

Next, we start traversing the array from the second element. If the difference between the current element and the previous one is more than $1$, we greedily reduce the current element to the previous element plus $1$.

Finally, we return the maximum element in the array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumElementAfterDecrementingAndRearranging(self, arr: List[int]) -> int:
        arr.sort()
        arr[0] = 1
        for i in range(1, len(arr)):
            d = max(0, arr[i] - arr[i - 1] - 1)
            arr[i] -= d
        return max(arr)
```

#### Java

```java
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        int ans = 1;
        for (int i = 1; i < arr.length; ++i) {
            int d = Math.max(0, arr[i] - arr[i - 1] - 1);
            arr[i] -= d;
            ans = Math.max(ans, arr[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumElementAfterDecrementingAndRearranging(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        arr[0] = 1;
        int ans = 1;
        for (int i = 1; i < arr.size(); ++i) {
            int d = max(0, arr[i] - arr[i - 1] - 1);
            arr[i] -= d;
            ans = max(ans, arr[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumElementAfterDecrementingAndRearranging(arr []int) int {
	sort.Ints(arr)
	ans := 1
	arr[0] = 1
	for i := 1; i < len(arr); i++ {
		d := max(0, arr[i]-arr[i-1]-1)
		arr[i] -= d
		ans = max(ans, arr[i])
	}
	return ans
}
```

#### TypeScript

```ts
function maximumElementAfterDecrementingAndRearranging(arr: number[]): number {
    arr.sort((a, b) => a - b);
    arr[0] = 1;
    let ans = 1;
    for (let i = 1; i < arr.length; ++i) {
        const d = Math.max(0, arr[i] - arr[i - 1] - 1);
        arr[i] -= d;
        ans = Math.max(ans, arr[i]);
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int MaximumElementAfterDecrementingAndRearranging(int[] arr) {
        Array.Sort(arr);
        int n = arr.Length;
        arr[0] = 1;
        for (int i = 1; i < n; ++i) {
            arr[i] = Math.Min(arr[i], arr[i - 1] + 1);
        }
        return arr[n - 1];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
