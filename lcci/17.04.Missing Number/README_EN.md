---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.04.Missing%20Number/README_EN.md
---

<!-- problem:start -->

# [17.04. Missing Number](https://leetcode.cn/problems/missing-number-lcci)

[中文文档](/lcci/17.04.Missing%20Number/README.md)

## Description

<!-- description:start -->

<p>An array&nbsp;contains all the integers from 0 to n, except for one number which is missing.&nbsp; Write code to find the missing integer. Can you do it in O(n) time?</p>

<p><strong>Note: </strong>This problem is slightly different from the original one the book.</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong>[3,0,1]

<strong>Output: </strong>2</pre>

<p>&nbsp;</p>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong>[9,6,4,2,3,5,7,0,1]

<strong>Output: </strong>8

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        nums.sort()
        for i, x in enumerate(nums):
            if i != x:
                return i
        return len(nums)
```

#### Java

```java
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i != nums[i]) {
                return i;
            }
        }
        return n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (i != nums[i]) {
                return i;
            }
        }
        return n;
    }
};
```

#### Go

```go
func missingNumber(nums []int) int {
	sort.Ints(nums)
	for i, x := range nums {
		if i != x {
			return i
		}
	}
	return len(nums)
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_number(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len() as i32;
        for i in 0..n {
            if i != nums[i as usize] {
                return i;
            }
        }
        n
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (i != nums[i]) {
            return i;
        }
    }
    return n;
};
```

#### Swift

```swift
class Solution {
    func missingNumber(_ nums: [Int]) -> Int {
        let nums = nums.sorted()
        for (i, x) in nums.enumerated() {
            if i != x {
                return i
            }
        }
        return nums.count
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
    def missingNumber(self, nums: List[int]) -> int:
        return sum(range(len(nums) + 1)) - sum(nums)
```

#### Java

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ans += i - nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int n = nums.size();
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ans += i - nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func missingNumber(nums []int) (ans int) {
	ans = len(nums)
	for i, x := range nums {
		ans += i - x
	}
	return
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut sum = 0;
        let mut max = 0;
        for num in nums {
            sum += num;
            max = max.max(num);
        }
        if max == n {
            ((1 + max) * max) / 2 - sum
        } else {
            n
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    const n = nums.length;
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ans += i - nums[i];
    }
    return ans;
};
```

#### Swift

```swift
class Solution {
    func missingNumber(_ nums: [Int]) -> Int {
        let n = nums.count
        return n * (n + 1) / 2 - nums.reduce(0, +)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        ans = 0
        for i, x in enumerate(nums, 1):
            ans ^= i ^ x
        return ans
```

#### Java

```java
class Solution {
    public int missingNumber(int[] nums) {
        int ans = 0;
        for (int i = 1; i <= nums.length; ++i) {
            ans ^= i ^ nums[i - 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int ans = 0;
        for (int i = 1; i <= nums.size(); ++i) {
            ans ^= i ^ nums[i - 1];
        }
        return ans;
    }
};
```

#### Go

```go
func missingNumber(nums []int) (ans int) {
	for i, x := range nums {
		ans ^= (i + 1) ^ x
	}
	return
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let mut res = 0;
        let n = nums.len();
        for i in 0..n {
            res ^= nums[i] ^ ((i + 1) as i32);
        }
        res
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    let ans = 0;
    for (let i = 1; i <= nums.length; ++i) {
        ans ^= i ^ nums[i - 1];
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
