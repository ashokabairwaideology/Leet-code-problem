---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0001.Two%20Sum/README.md
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [1. 两数之和](https://leetcode.cn/problems/two-sum)

[English Version](/solution/0000-0099/0001.Two%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数目标值 <code>target</code>，请你在该数组中找出 <strong>和为目标值 </strong><em><code>target</code></em>&nbsp; 的那&nbsp;<strong>两个</strong>&nbsp;整数，并返回它们的数组下标。</p>

<p>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。</p>

<p>你可以按任意顺序返回答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,7,11,15], target = 9
<strong>输出：</strong>[0,1]
<strong>解释：</strong>因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,4], target = 6
<strong>输出：</strong>[1,2]
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3], target = 6
<strong>输出：</strong>[0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><strong>只会存在一个有效答案</strong></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以想出一个时间复杂度小于 <code>O(n<sup>2</sup>)</code> 的算法吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用哈希表 $m$ 存放数组值以及对应的下标。

遍历数组 `nums`，当发现 `target - nums[i]` 在哈希表中，说明找到了目标值，返回 `target - nums[i]` 的下标以及 $i$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        m = {}
        for i, x in enumerate(nums):
            y = target - x
            if y in m:
                return [m[y], i]
            m[x] = i
```

#### Java

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.containsKey(y)) {
                return new int[] {m.get(y), i};
            }
            m.put(x, i);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> m;
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.count(y)) {
                return {m[y], i};
            }
            m[x] = i;
        }
    }
};
```

#### Go

```go
func twoSum(nums []int, target int) []int {
	m := map[int]int{}
	for i := 0; ; i++ {
		x := nums[i]
		y := target - x
		if j, ok := m[y]; ok {
			return []int{j, i}
		}
		m[x] = i
	}
}
```

#### TypeScript

```ts
function twoSum(nums: number[], target: number): number[] {
    const m: Map<number, number> = new Map();

    for (let i = 0; ; ++i) {
        const x = nums[i];
        const y = target - x;

        if (m.has(y)) {
            return [m.get(y)!, i];
        }

        m.set(x, i);
    }
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut m = HashMap::new();
        for (i, &x) in nums.iter().enumerate() {
            let y = target - x;
            if let Some(&j) = m.get(&y) {
                return vec![j as i32, i as i32];
            }
            m.insert(x, i as i32);
        }
        unreachable!()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    const m = new Map();
    for (let i = 0; ; ++i) {
        const x = nums[i];
        const y = target - x;
        if (m.has(y)) {
            return [m.get(y), i];
        }
        m.set(x, i);
    }
};
```

#### C#

```cs
public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        var m = new Dictionary<int, int>();
        for (int i = 0, j; ; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.TryGetValue(y, out j)) {
                return new [] {j, i};
            }
            if (!m.ContainsKey(x)) {
                m.Add(x, i);
            }
        }
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function twoSum($nums, $target) {
        $m = [];
        foreach ($nums as $i => $x) {
            $y = $target - $x;
            if (isset($m[$y])) {
                return [$m[$y], $i];
            }
            $m[$x] = $i;
        }
    }
}
```

#### Scala

```scala
import scala.collection.mutable

object Solution {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    var map = new mutable.HashMap[Int, Int]()
    for (i <- 0 to nums.length) {
      if (map.contains(target - nums(i))) {
        return Array(map(target - nums(i)), i)
      } else {
        map += (nums(i) -> i)
      }
    }
    Array(0, 0)
  }
}
```

#### Swift

```swift
class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var m = [Int: Int]()
        var i = 0
        while true {
            let x = nums[i]
            let y = target - nums[i]
            if let j = m[target - nums[i]] {
                return [j, i]
            }
            m[nums[i]] = i
            i += 1
        }
    }
}
```

#### Ruby

```rb
# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[]}
def two_sum(nums, target)
  nums.each_with_index do |x, idx|
    if nums.include? target - x
      return [idx, nums.index(target - x)] if nums.index(target - x) != idx
    end
    next
  end
end
```

#### Nim

```nim
import std/enumerate

proc twoSum(nums: seq[int], target: int): seq[int] =
    var
        bal: int
        tdx: int
    for idx, val in enumerate(nums):
        bal = target - val
        if bal in nums:
            tdx = nums.find(bal)
            if idx != tdx:
                return @[idx, tdx]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
