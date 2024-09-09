---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1953.Maximum%20Number%20of%20Weeks%20for%20Which%20You%20Can%20Work/README_EN.md
rating: 1803
source: Weekly Contest 252 Q2
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [1953. Maximum Number of Weeks for Which You Can Work](https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work)

[中文文档](/solution/1900-1999/1953.Maximum%20Number%20of%20Weeks%20for%20Which%20You%20Can%20Work/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> projects numbered from <code>0</code> to <code>n - 1</code>. You are given an integer array <code>milestones</code> where each <code>milestones[i]</code> denotes the number of milestones the <code>i<sup>th</sup></code> project has.</p>

<p>You can work on the projects following these two rules:</p>

<ul>
	<li>Every week, you will finish <strong>exactly one</strong> milestone of <strong>one</strong> project. You&nbsp;<strong>must</strong>&nbsp;work every week.</li>
	<li>You <strong>cannot</strong> work on two milestones from the same project for two <strong>consecutive</strong> weeks.</li>
</ul>

<p>Once all the milestones of all the projects are finished, or if the only milestones that you can work on will cause you to violate the above rules, you will <strong>stop working</strong>. Note that you may not be able to finish every project&#39;s milestones due to these constraints.</p>

<p>Return <em>the <strong>maximum</strong> number of weeks you would be able to work on the projects without violating the rules mentioned above</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> milestones = [1,2,3]
<strong>Output:</strong> 6
<strong>Explanation:</strong> One possible scenario is:
​​​​- During the 1<sup>st</sup> week, you will work on a milestone of project 0.
- During the 2<sup>nd</sup> week, you will work on a milestone of project 2.
- During the 3<sup>rd</sup> week, you will work on a milestone of project 1.
- During the 4<sup>th</sup> week, you will work on a milestone of project 2.
- During the 5<sup>th</sup> week, you will work on a milestone of project 1.
- During the 6<sup>th</sup> week, you will work on a milestone of project 2.
The total number of weeks is 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> milestones = [5,2,1]
<strong>Output:</strong> 7
<strong>Explanation:</strong> One possible scenario is:
- During the 1<sup>st</sup> week, you will work on a milestone of project 0.
- During the 2<sup>nd</sup> week, you will work on a milestone of project 1.
- During the 3<sup>rd</sup> week, you will work on a milestone of project 0.
- During the 4<sup>th</sup> week, you will work on a milestone of project 1.
- During the 5<sup>th</sup> week, you will work on a milestone of project 0.
- During the 6<sup>th</sup> week, you will work on a milestone of project 2.
- During the 7<sup>th</sup> week, you will work on a milestone of project 0.
The total number of weeks is 7.
Note that you cannot work on the last milestone of project 0 on 8<sup>th</sup> week because it would violate the rules.
Thus, one milestone in project 0 will remain unfinished.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == milestones.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= milestones[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We consider under what circumstances we cannot complete all stage tasks. If there is a project $i$ whose number of stage tasks is greater than the sum of the number of stage tasks of all other projects plus $1$, then we cannot complete all stage tasks. Otherwise, we can definitely complete all stage tasks by interlacing between different projects.

We denote the sum of the number of stage tasks of all projects as $s$, and the maximum number of stage tasks as $mx$, then the sum of the number of stage tasks of all other projects is $rest = s - mx$.

If $mx > rest + 1$, then we cannot complete all stage tasks, and at most we can complete $rest \times 2 + 1$ stage tasks. Otherwise, we can complete all stage tasks, the number is $s$.

The time complexity is $O(n)$, where $n$ is the number of projects. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWeeks(self, milestones: List[int]) -> int:
        mx, s = max(milestones), sum(milestones)
        rest = s - mx
        return rest * 2 + 1 if mx > rest + 1 else s
```

#### Java

```java
class Solution {
    public long numberOfWeeks(int[] milestones) {
        int mx = 0;
        long s = 0;
        for (int e : milestones) {
            s += e;
            mx = Math.max(mx, e);
        }
        long rest = s - mx;
        return mx > rest + 1 ? rest * 2 + 1 : s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfWeeks(vector<int>& milestones) {
        int mx = *max_element(milestones.begin(), milestones.end());
        long long s = accumulate(milestones.begin(), milestones.end(), 0LL);
        long long rest = s - mx;
        return mx > rest + 1 ? rest * 2 + 1 : s;
    }
};
```

#### Go

```go
func numberOfWeeks(milestones []int) int64 {
	mx := slices.Max(milestones)
	s := 0
	for _, x := range milestones {
		s += x
	}
	rest := s - mx
	if mx > rest+1 {
		return int64(rest*2 + 1)
	}
	return int64(s)
}
```

#### TypeScript

```ts
function numberOfWeeks(milestones: number[]): number {
    const mx = Math.max(...milestones);
    const s = milestones.reduce((a, b) => a + b, 0);
    const rest = s - mx;
    return mx > rest + 1 ? rest * 2 + 1 : s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
