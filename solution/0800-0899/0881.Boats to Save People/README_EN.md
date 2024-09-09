---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0881.Boats%20to%20Save%20People/README_EN.md
tags:
    - Greedy
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [881. Boats to Save People](https://leetcode.com/problems/boats-to-save-people)

[中文文档](/solution/0800-0899/0881.Boats%20to%20Save%20People/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>people</code> where <code>people[i]</code> is the weight of the <code>i<sup>th</sup></code> person, and an <strong>infinite number of boats</strong> where each boat can carry a maximum weight of <code>limit</code>. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most <code>limit</code>.</p>

<p>Return <em>the minimum number of boats to carry every given person</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> people = [1,2], limit = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> 1 boat (1, 2)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> people = [3,2,2,1], limit = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 boats (1, 2), (2) and (3)
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> people = [3,5,3,4], limit = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> 4 boats (3), (3), (4), (5)
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= people.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= people[i] &lt;= limit &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Two Pointers

After sorting, use two pointers to point to the beginning and end of the array respectively. Each time, compare the sum of the elements pointed to by the two pointers with `limit`. If it is less than or equal to `limit`, then both pointers move one step towards the middle. Otherwise, only the right pointer moves. Accumulate the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array `people`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numRescueBoats(self, people: List[int], limit: int) -> int:
        people.sort()
        ans = 0
        i, j = 0, len(people) - 1
        while i <= j:
            if people[i] + people[j] <= limit:
                i += 1
            j -= 1
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        for (int i = 0, j = people.length - 1; i <= j; --j) {
            if (people[i] + people[j] <= limit) {
                ++i;
            }
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numRescueBoats(vector<int>& people, int limit) {
        sort(people.begin(), people.end());
        int ans = 0;
        for (int i = 0, j = people.size() - 1; i <= j; --j) {
            if (people[i] + people[j] <= limit) {
                ++i;
            }
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func numRescueBoats(people []int, limit int) int {
	sort.Ints(people)
	ans := 0
	for i, j := 0, len(people)-1; i <= j; j-- {
		if people[i]+people[j] <= limit {
			i++
		}
		ans++
	}
	return ans
}
```

#### TypeScript

```ts
function numRescueBoats(people: number[], limit: number): number {
    people.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0, j = people.length - 1; i <= j; --j) {
        if (people[i] + people[j] <= limit) {
            ++i;
        }
        ++ans;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
