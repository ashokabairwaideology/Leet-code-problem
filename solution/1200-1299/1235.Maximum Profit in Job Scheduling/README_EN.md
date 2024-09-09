---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/README_EN.md
rating: 2022
source: Weekly Contest 159 Q4
tags:
    - Array
    - Binary Search
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [1235. Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling)

[中文文档](/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/README.md)

## Description

<!-- description:start -->

<p>We have <code>n</code> jobs, where every job is scheduled to be done from <code>startTime[i]</code> to <code>endTime[i]</code>, obtaining a profit of <code>profit[i]</code>.</p>

<p>You&#39;re given the <code>startTime</code>, <code>endTime</code> and <code>profit</code> arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.</p>

<p>If you choose a job that ends at time <code>X</code> you will be able to start another job that starts at time <code>X</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/images/sample1_1584.png" style="width: 380px; height: 154px;" /></strong></p>

<pre>
<strong>Input:</strong> startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
<strong>Output:</strong> 120
<strong>Explanation:</strong> The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/images/sample22_1584.png" style="width: 600px; height: 112px;" /> </strong></p>

<pre>
<strong>Input:</strong> startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
<strong>Output:</strong> 150
<strong>Explanation:</strong> The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/images/sample3_1584.png" style="width: 400px; height: 112px;" /></strong></p>

<pre>
<strong>Input:</strong> startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= startTime.length == endTime.length == profit.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= startTime[i] &lt; endTime[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= profit[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search + Binary Search

First, we sort the jobs by start time in ascending order, then design a function $dfs(i)$ to represent the maximum profit that can be obtained starting from the $i$-th job. The answer is $dfs(0)$.

The calculation process of function $dfs(i)$ is as follows:

For the $i$-th job, we can choose to do it or not. If we don't do it, the maximum profit is $dfs(i + 1)$; if we do it, we can use binary search to find the first job that starts after the end time of the $i$-th job, denoted as $j$, then the maximum profit is $profit[i] + dfs(j)$. We take the larger of the two. That is:

$$
dfs(i)=\max(dfs(i+1),profit[i]+dfs(j))
$$

Where $j$ is the smallest index that satisfies $startTime[j] \ge endTime[i]$.

In this process, we can use memoization search to save the answer of each state to avoid repeated calculations.

The time complexity is $O(n \times \log n)$, where $n$ is the number of jobs.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def jobScheduling(
        self, startTime: List[int], endTime: List[int], profit: List[int]
    ) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            _, e, p = jobs[i]
            j = bisect_left(jobs, e, lo=i + 1, key=lambda x: x[0])
            return max(dfs(i + 1), p + dfs(j))

        jobs = sorted(zip(startTime, endTime, profit))
        n = len(profit)
        return dfs(0)
```

#### Java

```java
class Solution {
    private int[][] jobs;
    private int[] f;
    private int n;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        n = profit.length;
        jobs = new int[n][3];
        for (int i = 0; i < n; ++i) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        f = new int[n];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != 0) {
            return f[i];
        }
        int e = jobs[i][1], p = jobs[i][2];
        int j = search(jobs, e, i + 1);
        int ans = Math.max(dfs(i + 1), p + dfs(j));
        f[i] = ans;
        return ans;
    }

    private int search(int[][] jobs, int x, int i) {
        int left = i, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (jobs[mid][0] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit) {
        int n = profit.size();
        vector<tuple<int, int, int>> jobs(n);
        for (int i = 0; i < n; ++i) jobs[i] = {startTime[i], endTime[i], profit[i]};
        sort(jobs.begin(), jobs.end());
        vector<int> f(n);
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i]) return f[i];
            auto [_, e, p] = jobs[i];
            tuple<int, int, int> t{e, 0, 0};
            int j = lower_bound(jobs.begin() + i + 1, jobs.end(), t, [&](auto& l, auto& r) -> bool { return get<0>(l) < get<0>(r); }) - jobs.begin();
            int ans = max(dfs(i + 1), p + dfs(j));
            f[i] = ans;
            return ans;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func jobScheduling(startTime []int, endTime []int, profit []int) int {
	n := len(profit)
	type tuple struct{ s, e, p int }
	jobs := make([]tuple, n)
	for i, p := range profit {
		jobs[i] = tuple{startTime[i], endTime[i], p}
	}
	sort.Slice(jobs, func(i, j int) bool { return jobs[i].s < jobs[j].s })
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		j := sort.Search(n, func(j int) bool { return jobs[j].s >= jobs[i].e })
		ans := max(dfs(i+1), jobs[i].p+dfs(j))
		f[i] = ans
		return ans
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function jobScheduling(startTime: number[], endTime: number[], profit: number[]): number {
    const n = startTime.length;
    const f = new Array(n).fill(0);
    const idx = new Array(n).fill(0).map((_, i) => i);
    idx.sort((i, j) => startTime[i] - startTime[j]);
    const search = (x: number) => {
        let l = 0;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (startTime[idx[mid]] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== 0) {
            return f[i];
        }
        const j = search(endTime[idx[i]]);
        return (f[i] = Math.max(dfs(i + 1), dfs(j) + profit[idx[i]]));
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming + Binary Search

We can also change the memoization search in Solution 1 to dynamic programming.

First, sort the jobs, this time we sort by end time in ascending order, then define $dp[i]$, which represents the maximum profit that can be obtained from the first $i$ jobs. The answer is $dp[n]$. Initialize $dp[0]=0$.

For the $i$-th job, we can choose to do it or not. If we don't do it, the maximum profit is $dp[i]$; if we do it, we can use binary search to find the last job that ends before the start time of the $i$-th job, denoted as $j$, then the maximum profit is $profit[i] + dp[j]$. We take the larger of the two. That is:

$$
dp[i+1] = \max(dp[i], profit[i] + dp[j])
$$

Where $j$ is the largest index that satisfies $endTime[j] \leq startTime[i]$.

The time complexity is $O(n \times \log n)$, where $n$ is the number of jobs.

Similar problems:

-   [2008. Maximum Earnings From Taxi](https://github.com/doocs/leetcode/blob/main/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README.md)
-   [1751. Maximum Number of Events That Can Be Attended II](https://github.com/doocs/leetcode/blob/main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def jobScheduling(
        self, startTime: List[int], endTime: List[int], profit: List[int]
    ) -> int:
        jobs = sorted(zip(endTime, startTime, profit))
        n = len(profit)
        dp = [0] * (n + 1)
        for i, (_, s, p) in enumerate(jobs):
            j = bisect_right(jobs, s, hi=i, key=lambda x: x[0])
            dp[i + 1] = max(dp[i], dp[j] + p)
        return dp[n]
```

#### Java

```java
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; ++i) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int j = search(jobs, jobs[i][0], i);
            dp[i + 1] = Math.max(dp[i], dp[j] + jobs[i][2]);
        }
        return dp[n];
    }

    private int search(int[][] jobs, int x, int n) {
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (jobs[mid][1] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit) {
        int n = profit.size();
        vector<tuple<int, int, int>> jobs(n);
        for (int i = 0; i < n; ++i) jobs[i] = {endTime[i], startTime[i], profit[i]};
        sort(jobs.begin(), jobs.end());
        vector<int> dp(n + 1);
        for (int i = 0; i < n; ++i) {
            auto [_, s, p] = jobs[i];
            int j = upper_bound(jobs.begin(), jobs.begin() + i, s, [&](int x, auto& job) -> bool { return x < get<0>(job); }) - jobs.begin();
            dp[i + 1] = max(dp[i], dp[j] + p);
        }
        return dp[n];
    }
};
```

#### Go

```go
func jobScheduling(startTime []int, endTime []int, profit []int) int {
	n := len(profit)
	type tuple struct{ s, e, p int }
	jobs := make([]tuple, n)
	for i, p := range profit {
		jobs[i] = tuple{startTime[i], endTime[i], p}
	}
	sort.Slice(jobs, func(i, j int) bool { return jobs[i].e < jobs[j].e })
	dp := make([]int, n+1)
	for i, job := range jobs {
		j := sort.Search(i, func(k int) bool { return jobs[k].e > job.s })
		dp[i+1] = max(dp[i], dp[j]+job.p)
	}
	return dp[n]
}
```

#### TypeScript

```ts
function jobScheduling(startTime: number[], endTime: number[], profit: number[]): number {
    const n = profit.length;
    const jobs: [number, number, number][] = Array.from({ length: n }, (_, i) => [
        startTime[i],
        endTime[i],
        profit[i],
    ]);
    jobs.sort((a, b) => a[1] - b[1]);
    const dp: number[] = Array.from({ length: n + 1 }, () => 0);
    const search = (x: number, right: number): number => {
        let left = 0;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (jobs[mid][1] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    for (let i = 0; i < n; ++i) {
        const j = search(jobs[i][0], i);
        dp[i + 1] = Math.max(dp[i], dp[j] + jobs[i][2]);
    }
    return dp[n];
}
```

#### Swift

```swift
class Solution {

    func binarySearch<T: Comparable>(inputArr: [T], searchItem: T) -> Int? {
        var lowerIndex = 0
        var upperIndex = inputArr.count - 1

        while lowerIndex < upperIndex {
            let currentIndex = (lowerIndex + upperIndex) / 2
            if inputArr[currentIndex] <= searchItem {
                lowerIndex = currentIndex + 1
            } else {
                upperIndex = currentIndex
            }
        }

        if inputArr[upperIndex] <= searchItem {
            return upperIndex + 1
        }
        return lowerIndex
    }

    func jobScheduling(_ startTime: [Int], _ endTime: [Int], _ profit: [Int]) -> Int {
        let zipList = zip(zip(startTime, endTime), profit)
        var table: [(startTime: Int, endTime: Int, profit: Int, cumsum: Int)] = []

        for ((x, y), z) in zipList {
            table.append((x, y, z, 0))
        }
        table.sort(by: { $0.endTime < $1.endTime })
        let sortedEndTime = endTime.sorted()

        var profits: [Int] = [0]
        for iJob in table {
            let index: Int! = binarySearch(inputArr: sortedEndTime, searchItem: iJob.startTime)
            if profits.last! < profits[index] + iJob.profit {
                profits.append(profits[index] + iJob.profit)
            } else {
                profits.append(profits.last!)
            }
        }
        return profits.last!
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
