---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1710.Maximum%20Units%20on%20a%20Truck/README_EN.md
rating: 1309
source: Weekly Contest 222 Q1
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [1710. Maximum Units on a Truck](https://leetcode.com/problems/maximum-units-on-a-truck)

[中文文档](/solution/1700-1799/1710.Maximum%20Units%20on%20a%20Truck/README.md)

## Description

<!-- description:start -->

<p>You are assigned to put some amount of boxes onto <strong>one truck</strong>. You are given a 2D array <code>boxTypes</code>, where <code>boxTypes[i] = [numberOfBoxes<sub>i</sub>, numberOfUnitsPerBox<sub>i</sub>]</code>:</p>

<ul>
	<li><code>numberOfBoxes<sub>i</sub></code> is the number of boxes of type <code>i</code>.</li>
	<li><code>numberOfUnitsPerBox<sub>i</sub></code><sub> </sub>is the number of units in each box of the type <code>i</code>.</li>
</ul>

<p>You are also given an integer <code>truckSize</code>, which is the <strong>maximum</strong> number of <strong>boxes</strong> that can be put on the truck. You can choose any boxes to put on the truck as long as the number&nbsp;of boxes does not exceed <code>truckSize</code>.</p>

<p>Return <em>the <strong>maximum</strong> total number of <strong>units</strong> that can be put on the truck.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
<strong>Output:</strong> 8
<strong>Explanation:</strong> There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
<strong>Output:</strong> 91
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= boxTypes.length &lt;= 1000</code></li>
	<li><code>1 &lt;= numberOfBoxes<sub>i</sub>, numberOfUnitsPerBox<sub>i</sub> &lt;= 1000</code></li>
	<li><code>1 &lt;= truckSize &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

According to the problem, we should choose as many units as possible. Therefore, we first sort `boxTypes` in descending order of the number of units.

Then we traverse `boxTypes` from front to back, choose up to `truckSize` boxes, and accumulate the number of units.

The time complexity is $O(n \times \log n)$, where $n$ is the length of the two-dimensional array `boxTypes`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumUnits(self, boxTypes: List[List[int]], truckSize: int) -> int:
        ans = 0
        for a, b in sorted(boxTypes, key=lambda x: -x[1]):
            ans += b * min(truckSize, a)
            truckSize -= a
            if truckSize <= 0:
                break
        return ans
```

#### Java

```java
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int ans = 0;
        for (var e : boxTypes) {
            int a = e[0], b = e[1];
            ans += b * Math.min(truckSize, a);
            truckSize -= a;
            if (truckSize <= 0) {
                break;
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
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        sort(boxTypes.begin(), boxTypes.end(), [](auto& a, auto& b) { return a[1] > b[1]; });
        int ans = 0;
        for (auto& e : boxTypes) {
            int a = e[0], b = e[1];
            ans += b * min(truckSize, a);
            truckSize -= a;
            if (truckSize <= 0) break;
        }
        return ans;
    }
};
```

#### Go

```go
func maximumUnits(boxTypes [][]int, truckSize int) (ans int) {
	sort.Slice(boxTypes, func(i, j int) bool { return boxTypes[i][1] > boxTypes[j][1] })
	for _, e := range boxTypes {
		a, b := e[0], e[1]
		ans += b * min(truckSize, a)
		truckSize -= a
		if truckSize <= 0 {
			break
		}
	}
	return
}
```

#### TypeScript

```ts
export function maximumUnits(boxTypes: number[][], truckSize: number): number {
    boxTypes.sort(([_, a], [__, b]) => b - a);
    let ans = 0;
    for (const [count, size] of boxTypes) {
        ans += Math.min(truckSize, count) * size;
        truckSize -= count;
        if (truckSize < 0) break;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_units(mut box_types: Vec<Vec<i32>>, truck_size: i32) -> i32 {
        box_types.sort_by(|a, b| b[1].cmp(&a[1]));
        let mut sum = 0;
        let mut ans = 0;
        for box_type in box_types.iter() {
            if sum + box_type[0] < truck_size {
                sum += box_type[0];
                ans += box_type[0] * box_type[1];
            } else {
                ans += (truck_size - sum) * box_type[1];
                break;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Counting Sort

We can also use the idea of counting sort, create an array $cnt$ of length $1001$, where $cnt[b]$ represents the number of boxes with $b$ units.

Then starting from the box with the maximum number of units, choose up to `truckSize` boxes, and accumulate the number of units.

The time complexity is $O(M)$, where $M$ is the maximum number of units. In this problem, $M=1000$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumUnits(self, boxTypes: List[List[int]], truckSize: int) -> int:
        cnt = [0] * 1001
        for a, b in boxTypes:
            cnt[b] += a
        ans = 0
        for b in range(1000, 0, -1):
            a = cnt[b]
            if a:
                ans += b * min(truckSize, a)
                truckSize -= a
                if truckSize <= 0:
                    break
        return ans
```

#### Java

```java
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[] cnt = new int[1001];
        for (var e : boxTypes) {
            int a = e[0], b = e[1];
            cnt[b] += a;
        }
        int ans = 0;
        for (int b = 1000; b > 0 && truckSize > 0; --b) {
            int a = cnt[b];
            if (a > 0) {
                ans += b * Math.min(truckSize, a);
                truckSize -= a;
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
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        int cnt[1001] = {0};
        for (auto& e : boxTypes) {
            int a = e[0], b = e[1];
            cnt[b] += a;
        }
        int ans = 0;
        for (int b = 1000; b > 0 && truckSize > 0; --b) {
            int a = cnt[b];
            if (a) {
                ans += b * min(truckSize, a);
                truckSize -= a;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumUnits(boxTypes [][]int, truckSize int) (ans int) {
	cnt := [1001]int{}
	for _, e := range boxTypes {
		a, b := e[0], e[1]
		cnt[b] += a
	}
	for b := 1000; b > 0 && truckSize > 0; b-- {
		a := cnt[b]
		if a > 0 {
			ans += b * min(truckSize, a)
			truckSize -= a
		}
	}
	return
}
```

#### TypeScript

```ts
function maximumUnits(boxTypes: number[][], truckSize: number): number {
    boxTypes.sort(([_, a], [__, b]) => b - a);
    let ans = 0;
    for (const [count, size] of boxTypes) {
        ans += Math.min(truckSize, count) * size;
        truckSize -= count;
        if (truckSize < 0) {
            break;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
