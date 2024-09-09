---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1431.Kids%20With%20the%20Greatest%20Number%20of%20Candies/README_EN.md
rating: 1176
source: Biweekly Contest 25 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1431. Kids With the Greatest Number of Candies](https://leetcode.com/problems/kids-with-the-greatest-number-of-candies)

[中文文档](/solution/1400-1499/1431.Kids%20With%20the%20Greatest%20Number%20of%20Candies/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> kids with candies. You are given an integer array <code>candies</code>, where each <code>candies[i]</code> represents the number of candies the <code>i<sup>th</sup></code> kid has, and an integer <code>extraCandies</code>, denoting the number of extra candies that you have.</p>

<p>Return <em>a boolean array </em><code>result</code><em> of length </em><code>n</code><em>, where </em><code>result[i]</code><em> is </em><code>true</code><em> if, after giving the </em><code>i<sup>th</sup></code><em> kid all the </em><code>extraCandies</code><em>, they will have the <strong>greatest</strong> number of candies among all the kids</em><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>Note that <strong>multiple</strong> kids can have the <strong>greatest</strong> number of candies.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> candies = [2,3,5,1,3], extraCandies = 3
<strong>Output:</strong> [true,true,true,false,true] 
<strong>Explanation:</strong> If you give all extraCandies to:
- Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
- Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
- Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
- Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
- Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> candies = [4,2,1,1,2], extraCandies = 1
<strong>Output:</strong> [true,false,false,false,false] 
<strong>Explanation:</strong> There is only 1 extra candy.
Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> candies = [12,1,12], extraCandies = 10
<strong>Output:</strong> [true,false,true]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == candies.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= candies[i] &lt;= 100</code></li>
	<li><code>1 &lt;= extraCandies &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kidsWithCandies(self, candies: List[int], extraCandies: int) -> List[bool]:
        mx = max(candies)
        return [candy + extraCandies >= mx for candy in candies]
```

#### Java

```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int mx = 0;
        for (int candy : candies) {
            mx = Math.max(mx, candy);
        }
        List<Boolean> res = new ArrayList<>();
        for (int candy : candies) {
            res.add(candy + extraCandies >= mx);
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<bool> kidsWithCandies(vector<int>& candies, int extraCandies) {
        int mx = *max_element(candies.begin(), candies.end());
        vector<bool> res;
        for (int candy : candies) {
            res.push_back(candy + extraCandies >= mx);
        }
        return res;
    }
};
```

#### Go

```go
func kidsWithCandies(candies []int, extraCandies int) (ans []bool) {
	mx := slices.Max(candies)
	for _, candy := range candies {
		ans = append(ans, candy+extraCandies >= mx)
	}
	return
}
```

#### TypeScript

```ts
function kidsWithCandies(candies: number[], extraCandies: number): boolean[] {
    const max = candies.reduce((r, v) => Math.max(r, v));
    return candies.map(v => v + extraCandies >= max);
}
```

#### Rust

```rust
impl Solution {
    pub fn kids_with_candies(candies: Vec<i32>, extra_candies: i32) -> Vec<bool> {
        let max = *candies.iter().max().unwrap();
        candies.iter().map(|v| v + extra_candies >= max).collect()
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $candies
     * @param Integer $extraCandies
     * @return Boolean[]
     */
    function kidsWithCandies($candies, $extraCandies) {
        $max = max($candies);
        $rs = [];
        for ($i = 0; $i < count($candies); $i++) {
            array_push($rs, $candies[$i] + $extraCandies >= $max);
        }
        return $rs;
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
bool* kidsWithCandies(int* candies, int candiesSize, int extraCandies, int* returnSize) {
    int mx = 0;
    for (int i = 0; i < candiesSize; i++) {
        mx = max(mx, candies[i]);
    }
    bool* ans = malloc(candiesSize * sizeof(bool));
    for (int i = 0; i < candiesSize; i++) {
        ans[i] = candies[i] + extraCandies >= mx;
    }
    *returnSize = candiesSize;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
