---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1814.Count%20Nice%20Pairs%20in%20an%20Array/README_EN.md
rating: 1737
source: Biweekly Contest 49 Q3
tags:
    - Array
    - Hash Table
    - Math
    - Counting
---

<!-- problem:start -->

# [1814. Count Nice Pairs in an Array](https://leetcode.com/problems/count-nice-pairs-in-an-array)

[中文文档](/solution/1800-1899/1814.Count%20Nice%20Pairs%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> that consists of non-negative integers. Let us define <code>rev(x)</code> as the reverse of the non-negative integer <code>x</code>. For example, <code>rev(123) = 321</code>, and <code>rev(120) = 21</code>. A pair of indices <code>(i, j)</code> is <strong>nice</strong> if it satisfies all of the following conditions:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; nums.length</code></li>
	<li><code>nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])</code></li>
</ul>

<p>Return <em>the number of nice pairs of indices</em>. Since that number can be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [42,11,1,97]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [13,10,35,24,76]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Equation Transformation + Hash Table

For the index pair $(i, j)$, if it satisfies the condition, then we have $nums[i] + rev(nums[j]) = nums[j] + rev(nums[i])$, which means $nums[i] - nums[j] = rev(nums[j]) - rev(nums[i])$.

Therefore, we can use $nums[i] - rev(nums[i])$ as the key of a hash table and count the number of occurrences of each key. Finally, we calculate the combination of values corresponding to each key, add them up, and get the final answer.

Note that we need to perform modulo operation on the answer.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length of the $nums$ array and the maximum value in the $nums$ array, respectively. The space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countNicePairs(self, nums: List[int]) -> int:
        def rev(x):
            y = 0
            while x:
                y = y * 10 + x % 10
                x //= 10
            return y

        cnt = Counter(x - rev(x) for x in nums)
        mod = 10**9 + 7
        return sum(v * (v - 1) // 2 for v in cnt.values()) % mod
```

#### Java

```java
class Solution {
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            int y = x - rev(x);
            cnt.merge(y, 1, Integer::sum);
        }
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        for (int v : cnt.values()) {
            ans = (ans + (long) v * (v - 1) / 2) % mod;
        }
        return (int) ans;
    }

    private int rev(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countNicePairs(vector<int>& nums) {
        auto rev = [](int x) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        unordered_map<int, int> cnt;
        for (int& x : nums) {
            int y = x - rev(x);
            cnt[y]++;
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (auto& [_, v] : cnt) {
            ans = (ans + 1ll * v * (v - 1) / 2) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func countNicePairs(nums []int) (ans int) {
	rev := func(x int) (y int) {
		for ; x > 0; x /= 10 {
			y = y*10 + x%10
		}
		return
	}
	cnt := map[int]int{}
	for _, x := range nums {
		y := x - rev(x)
		cnt[y]++
	}
	const mod int = 1e9 + 7
	for _, v := range cnt {
		ans = (ans + v*(v-1)/2) % mod
	}
	return
}
```

#### TypeScript

```ts
function countNicePairs(nums: number[]): number {
    const rev = (x: number): number => {
        let y = 0;
        while (x) {
            y = y * 10 + (x % 10);
            x = Math.floor(x / 10);
        }
        return y;
    };
    const mod = 10 ** 9 + 7;
    const cnt = new Map<number, number>();
    let ans = 0;
    for (const x of nums) {
        const y = x - rev(x);
        ans = (ans + (cnt.get(y) ?? 0)) % mod;
        cnt.set(y, (cnt.get(y) ?? 0) + 1);
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var countNicePairs = function (nums) {
    const rev = x => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    const cnt = new Map();
    for (const x of nums) {
        const y = x - rev(x);
        cnt.set(y, (cnt.get(y) | 0) + 1);
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (const [_, v] of cnt) {
        ans = (ans + Math.floor((v * (v - 1)) / 2)) % mod;
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int CountNicePairs(int[] nums) {
        Dictionary<int, int> cnt = new Dictionary<int, int>();
        foreach (int x in nums) {
            int y = x - Rev(x);
            cnt[y] = cnt.GetValueOrDefault(y, 0) + 1;
        }
        int mod = (int)1e9 + 7;
        long ans = 0;
        foreach (int v in cnt.Values) {
            ans = (ans + (long)v * (v - 1) / 2) % mod;
        }
        return (int)ans;
    }

    private int Rev(int x) {
        int y = 0;
        while (x > 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countNicePairs(self, nums: List[int]) -> int:
        def rev(x):
            y = 0
            while x:
                y = y * 10 + x % 10
                x //= 10
            return y

        ans = 0
        mod = 10**9 + 7
        cnt = Counter()
        for x in nums:
            y = x - rev(x)
            ans += cnt[y]
            cnt[y] += 1
        return ans % mod
```

#### Java

```java
class Solution {
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        final int mod = (int) 1e9 + 7;
        int ans = 0;
        for (int x : nums) {
            int y = x - rev(x);
            ans = (ans + cnt.getOrDefault(y, 0)) % mod;
            cnt.merge(y, 1, Integer::sum);
        }
        return ans;
    }

    private int rev(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countNicePairs(vector<int>& nums) {
        auto rev = [](int x) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        unordered_map<int, int> cnt;
        int ans = 0;
        const int mod = 1e9 + 7;
        for (int& x : nums) {
            int y = x - rev(x);
            ans = (ans + cnt[y]++) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func countNicePairs(nums []int) (ans int) {
	rev := func(x int) (y int) {
		for ; x > 0; x /= 10 {
			y = y*10 + x%10
		}
		return
	}
	cnt := map[int]int{}
	const mod int = 1e9 + 7
	for _, x := range nums {
		y := x - rev(x)
		ans = (ans + cnt[y]) % mod
		cnt[y]++
	}
	return
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var countNicePairs = function (nums) {
    const rev = x => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    let ans = 0;
    const mod = 1e9 + 7;
    const cnt = new Map();
    for (const x of nums) {
        const y = x - rev(x);
        const v = cnt.get(y) | 0;
        ans = (ans + v) % mod;
        cnt.set(y, v + 1);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
