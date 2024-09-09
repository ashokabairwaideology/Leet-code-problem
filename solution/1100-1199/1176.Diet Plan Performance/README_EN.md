---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1176.Diet%20Plan%20Performance/README_EN.md
rating: 1397
source: Weekly Contest 152 Q2
tags:
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [1176. Diet Plan Performance 🔒](https://leetcode.com/problems/diet-plan-performance)

[中文文档](/solution/1100-1199/1176.Diet%20Plan%20Performance/README.md)

## Description

<!-- description:start -->

<p>A dieter consumes&nbsp;<code>calories[i]</code>&nbsp;calories on the <code>i</code>-th day.&nbsp;</p>

<p>Given an integer <code>k</code>, for <strong>every</strong> consecutive sequence of <code>k</code> days (<code>calories[i], calories[i+1], ..., calories[i+k-1]</code>&nbsp;for all <code>0 &lt;= i &lt;= n-k</code>), they look at <em>T</em>, the total calories consumed during that sequence of <code>k</code> days (<code>calories[i] + calories[i+1] + ... + calories[i+k-1]</code>):</p>

<ul>
	<li>If <code>T &lt; lower</code>, they performed poorly on their diet and lose 1 point;&nbsp;</li>
	<li>If <code>T &gt; upper</code>, they performed well on their diet and gain 1 point;</li>
	<li>Otherwise, they performed normally and there is no change in points.</li>
</ul>

<p>Initially, the dieter has zero points. Return the total number of points the dieter has after dieting for <code>calories.length</code>&nbsp;days.</p>

<p>Note that the total points can be negative.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
<strong>Output:</strong> 0
<strong>Explanation</strong>: Since k = 1, we consider each element of the array separately and compare it to lower and upper.
calories[0] and calories[1] are less than lower so 2 points are lost.
calories[3] and calories[4] are greater than upper so 2 points are gained.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> calories = [3,2], k = 2, lower = 0, upper = 1
<strong>Output:</strong> 1
<strong>Explanation</strong>: Since k = 2, we consider subarrays of length 2.
calories[0] + calories[1] &gt; upper so 1 point is gained.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> calories = [6,5,0,0], k = 2, lower = 1, upper = 5
<strong>Output:</strong> 0
<strong>Explanation</strong>:
calories[0] + calories[1] &gt; upper so 1 point is gained.
lower &lt;= calories[1] + calories[2] &lt;= upper so no change in points.
calories[2] + calories[3] &lt; lower so 1 point is lost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= calories.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= calories[i] &lt;= 20000</code></li>
	<li><code>0 &lt;= lower &lt;= upper</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

First, we preprocess a prefix sum array $s$ of length $n+1$, where $s[i]$ represents the total calories of the first $i$ days.

Then we traverse the prefix sum array $s$. For each position $i$, we calculate $s[i+k]-s[i]$, which is the total calories for the consecutive $k$ days starting from the $i$th day. According to the problem description, for each $s[i+k]-s[i]$, we judge its value with $lower$ and $upper$, and update the answer accordingly.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the `calories` array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dietPlanPerformance(
        self, calories: List[int], k: int, lower: int, upper: int
    ) -> int:
        s = list(accumulate(calories, initial=0))
        ans, n = 0, len(calories)
        for i in range(n - k + 1):
            t = s[i + k] - s[i]
            if t < lower:
                ans -= 1
            elif t > upper:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int n = calories.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + calories[i];
        }
        int ans = 0;
        for (int i = 0; i < n - k + 1; ++i) {
            int t = s[i + k] - s[i];
            if (t < lower) {
                --ans;
            } else if (t > upper) {
                ++ans;
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
    int dietPlanPerformance(vector<int>& calories, int k, int lower, int upper) {
        int n = calories.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + calories[i];
        }
        int ans = 0;
        for (int i = 0; i < n - k + 1; ++i) {
            int t = s[i + k] - s[i];
            if (t < lower) {
                --ans;
            } else if (t > upper) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func dietPlanPerformance(calories []int, k int, lower int, upper int) (ans int) {
	n := len(calories)
	s := make([]int, n+1)
	for i, x := range calories {
		s[i+1] = s[i] + x
	}
	for i := 0; i < n-k+1; i++ {
		t := s[i+k] - s[i]
		if t < lower {
			ans--
		} else if t > upper {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function dietPlanPerformance(calories: number[], k: number, lower: number, upper: number): number {
    const n = calories.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + calories[i];
    }
    let ans = 0;
    for (let i = 0; i < n - k + 1; ++i) {
        const t = s[i + k] - s[i];
        if (t < lower) {
            --ans;
        } else if (t > upper) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sliding Window

We maintain a sliding window of length $k$, and the sum of the elements in the window is denoted as $s$. If $s \lt lower$, the score decreases by $1$; if $s > upper$, the score increases by $1$.

The time complexity is $O(n)$, where $n$ is the length of the `calories` array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dietPlanPerformance(
        self, calories: List[int], k: int, lower: int, upper: int
    ) -> int:
        def check(s):
            if s < lower:
                return -1
            if s > upper:
                return 1
            return 0

        s, n = sum(calories[:k]), len(calories)
        ans = check(s)
        for i in range(k, n):
            s += calories[i] - calories[i - k]
            ans += check(s)
        return ans
```

#### Java

```java
class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int s = 0, n = calories.length;
        for (int i = 0; i < k; ++i) {
            s += calories[i];
        }
        int ans = 0;
        if (s < lower) {
            --ans;
        } else if (s > upper) {
            ++ans;
        }
        for (int i = k; i < n; ++i) {
            s += calories[i] - calories[i - k];
            if (s < lower) {
                --ans;
            } else if (s > upper) {
                ++ans;
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
    int dietPlanPerformance(vector<int>& calories, int k, int lower, int upper) {
        int n = calories.size();
        int s = accumulate(calories.begin(), calories.begin() + k, 0);
        int ans = 0;
        if (s < lower) {
            --ans;
        } else if (s > upper) {
            ++ans;
        }
        for (int i = k; i < n; ++i) {
            s += calories[i] - calories[i - k];
            if (s < lower) {
                --ans;
            } else if (s > upper) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func dietPlanPerformance(calories []int, k int, lower int, upper int) (ans int) {
	n := len(calories)
	s := 0
	for _, x := range calories[:k] {
		s += x
	}
	if s < lower {
		ans--
	} else if s > upper {
		ans++
	}
	for i := k; i < n; i++ {
		s += calories[i] - calories[i-k]
		if s < lower {
			ans--
		} else if s > upper {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function dietPlanPerformance(calories: number[], k: number, lower: number, upper: number): number {
    const n = calories.length;
    let s = calories.slice(0, k).reduce((a, b) => a + b);
    let ans = 0;
    if (s < lower) {
        --ans;
    } else if (s > upper) {
        ++ans;
    }
    for (let i = k; i < n; ++i) {
        s += calories[i] - calories[i - k];
        if (s < lower) {
            --ans;
        } else if (s > upper) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
