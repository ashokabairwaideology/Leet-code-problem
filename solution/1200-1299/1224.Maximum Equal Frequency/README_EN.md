---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1224.Maximum%20Equal%20Frequency/README_EN.md
rating: 2050
source: Weekly Contest 158 Q4
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1224. Maximum Equal Frequency](https://leetcode.com/problems/maximum-equal-frequency)

[中文文档](/solution/1200-1299/1224.Maximum%20Equal%20Frequency/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code> of positive integers, return the longest possible length of an array prefix of <code>nums</code>, such that it is possible to remove <strong>exactly one</strong> element from this prefix so that every number that has appeared in it will have the same number of occurrences.</p>

<p>If after removing one element there are no remaining elements, it&#39;s still considered that every appeared number has the same number of ocurrences (0).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,1,1,5,3,3,5]
<strong>Output:</strong> 7
<strong>Explanation:</strong> For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4] = 5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
<strong>Output:</strong> 13
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Array or Hash Table

We use $cnt$ to record the number of times each element $v$ appears in $nums$, and $ccnt$ to record the number of times each count appears. The maximum number of times an element appears is represented by $mx$.

While traversing $nums$:

-   If the maximum count $mx=1$, it means that each number in the current prefix appears $1$ time. If we delete any one of them, the remaining numbers will all have the same count.
-   If all numbers appear $mx$ and $mx-1$ times, and only one number appears $mx$ times, then we can delete one occurrence of the number that appears $mx$ times. The remaining numbers will all have a count of $mx-1$, which meets the condition.
-   If, except for one number, all other numbers appear $mx$ times, then we can delete the number that appears once. The remaining numbers will all have a count of $mx$, which meets the condition.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the $nums$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxEqualFreq(self, nums: List[int]) -> int:
        cnt = Counter()
        ccnt = Counter()
        ans = mx = 0
        for i, v in enumerate(nums, 1):
            if v in cnt:
                ccnt[cnt[v]] -= 1
            cnt[v] += 1
            mx = max(mx, cnt[v])
            ccnt[cnt[v]] += 1
            if mx == 1:
                ans = i
            elif ccnt[mx] * mx + ccnt[mx - 1] * (mx - 1) == i and ccnt[mx] == 1:
                ans = i
            elif ccnt[mx] * mx + 1 == i and ccnt[1] == 1:
                ans = i
        return ans
```

#### Java

```java
class Solution {
    private static int[] cnt = new int[100010];
    private static int[] ccnt = new int[100010];

    public int maxEqualFreq(int[] nums) {
        Arrays.fill(cnt, 0);
        Arrays.fill(ccnt, 0);
        int ans = 0;
        int mx = 0;
        for (int i = 1; i <= nums.length; ++i) {
            int v = nums[i - 1];
            if (cnt[v] > 0) {
                --ccnt[cnt[v]];
            }
            ++cnt[v];
            mx = Math.max(mx, cnt[v]);
            ++ccnt[cnt[v]];
            if (mx == 1) {
                ans = i;
            } else if (ccnt[mx] * mx + ccnt[mx - 1] * (mx - 1) == i && ccnt[mx] == 1) {
                ans = i;
            } else if (ccnt[mx] * mx + 1 == i && ccnt[1] == 1) {
                ans = i;
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
    int maxEqualFreq(vector<int>& nums) {
        unordered_map<int, int> cnt;
        unordered_map<int, int> ccnt;
        int ans = 0, mx = 0;
        for (int i = 1; i <= nums.size(); ++i) {
            int v = nums[i - 1];
            if (cnt[v]) --ccnt[cnt[v]];
            ++cnt[v];
            mx = max(mx, cnt[v]);
            ++ccnt[cnt[v]];
            if (mx == 1)
                ans = i;
            else if (ccnt[mx] * mx + ccnt[mx - 1] * (mx - 1) == i && ccnt[mx] == 1)
                ans = i;
            else if (ccnt[mx] * mx + 1 == i && ccnt[1] == 1)
                ans = i;
        }
        return ans;
    }
};
```

#### Go

```go
func maxEqualFreq(nums []int) int {
	cnt := map[int]int{}
	ccnt := map[int]int{}
	ans, mx := 0, 0
	for i, v := range nums {
		i++
		if cnt[v] > 0 {
			ccnt[cnt[v]]--
		}
		cnt[v]++
		mx = max(mx, cnt[v])
		ccnt[cnt[v]]++
		if mx == 1 {
			ans = i
		} else if ccnt[mx]*mx+ccnt[mx-1]*(mx-1) == i && ccnt[mx] == 1 {
			ans = i
		} else if ccnt[mx]*mx+1 == i && ccnt[1] == 1 {
			ans = i
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxEqualFreq(nums: number[]): number {
    const n = nums.length;
    const map = new Map();
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
    }

    for (let i = n - 1; i > 0; i--) {
        for (const k of map.keys()) {
            map.set(k, map.get(k) - 1);
            let num = 0;
            for (const v of map.values()) {
                if (v !== 0) {
                    num = v;
                    break;
                }
            }
            let isOk = true;
            let sum = 1;
            for (const v of map.values()) {
                if (v !== 0 && v !== num) {
                    isOk = false;
                    break;
                }
                sum += v;
            }
            if (isOk) {
                return sum;
            }
            map.set(k, map.get(k) + 1);
        }
        map.set(nums[i], map.get(nums[i]) - 1);
    }
    return 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
