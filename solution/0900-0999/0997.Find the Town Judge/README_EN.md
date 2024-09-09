---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0997.Find%20the%20Town%20Judge/README_EN.md
tags:
    - Graph
    - Array
    - Hash Table
---

<!-- problem:start -->

# [997. Find the Town Judge](https://leetcode.com/problems/find-the-town-judge)

[中文文档](/solution/0900-0999/0997.Find%20the%20Town%20Judge/README.md)

## Description

<!-- description:start -->

<p>In a town, there are <code>n</code> people labeled from <code>1</code> to <code>n</code>. There is a rumor that one of these people is secretly the town judge.</p>

<p>If the town judge exists, then:</p>

<ol>
	<li>The town judge trusts nobody.</li>
	<li>Everybody (except for the town judge) trusts the town judge.</li>
	<li>There is exactly one person that satisfies properties <strong>1</strong> and <strong>2</strong>.</li>
</ol>

<p>You are given an array <code>trust</code> where <code>trust[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> representing that the person labeled <code>a<sub>i</sub></code> trusts the person labeled <code>b<sub>i</sub></code>. If a trust relationship does not exist in <code>trust</code> array, then such a trust relationship does not exist.</p>

<p>Return <em>the label of the town judge if the town judge exists and can be identified, or return </em><code>-1</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, trust = [[1,2]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, trust = [[1,3],[2,3]]
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, trust = [[1,3],[2,3],[3,1]]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= trust.length &lt;= 10<sup>4</sup></code></li>
	<li><code>trust[i].length == 2</code></li>
	<li>All the pairs of <code>trust</code> are <strong>unique</strong>.</li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We create two arrays $cnt1$ and $cnt2$ of length $n + 1$, representing the number of people each person trusts and the number of people who trust each person, respectively.

Next, we traverse the array $trust$, for each item $[a_i, b_i]$, we increment $cnt1[a_i]$ and $cnt2[b_i]$ by $1$.

Finally, we enumerate each person $i$ in the range $[1,..n]$. If $cnt1[i] = 0$ and $cnt2[i] = n - 1$, it means that $i$ is the town judge, and we return $i$. Otherwise, if no such person is found after the traversal, we return $-1$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $trust$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        cnt1 = [0] * (n + 1)
        cnt2 = [0] * (n + 1)
        for a, b in trust:
            cnt1[a] += 1
            cnt2[b] += 1
        for i in range(1, n + 1):
            if cnt1[i] == 0 and cnt2[i] == n - 1:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] cnt1 = new int[n + 1];
        int[] cnt2 = new int[n + 1];
        for (var t : trust) {
            int a = t[0], b = t[1];
            ++cnt1[a];
            ++cnt2[b];
        }
        for (int i = 1; i <= n; ++i) {
            if (cnt1[i] == 0 && cnt2[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findJudge(int n, vector<vector<int>>& trust) {
        vector<int> cnt1(n + 1);
        vector<int> cnt2(n + 1);
        for (auto& t : trust) {
            int a = t[0], b = t[1];
            ++cnt1[a];
            ++cnt2[b];
        }
        for (int i = 1; i <= n; ++i) {
            if (cnt1[i] == 0 && cnt2[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func findJudge(n int, trust [][]int) int {
	cnt1 := make([]int, n+1)
	cnt2 := make([]int, n+1)
	for _, t := range trust {
		a, b := t[0], t[1]
		cnt1[a]++
		cnt2[b]++
	}
	for i := 1; i <= n; i++ {
		if cnt1[i] == 0 && cnt2[i] == n-1 {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function findJudge(n: number, trust: number[][]): number {
    const cnt1: number[] = new Array(n + 1).fill(0);
    const cnt2: number[] = new Array(n + 1).fill(0);
    for (const [a, b] of trust) {
        ++cnt1[a];
        ++cnt2[b];
    }
    for (let i = 1; i <= n; ++i) {
        if (cnt1[i] === 0 && cnt2[i] === n - 1) {
            return i;
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_judge(n: i32, trust: Vec<Vec<i32>>) -> i32 {
        let mut cnt1 = vec![0; (n + 1) as usize];
        let mut cnt2 = vec![0; (n + 1) as usize];

        for t in trust.iter() {
            let a = t[0] as usize;
            let b = t[1] as usize;
            cnt1[a] += 1;
            cnt2[b] += 1;
        }

        for i in 1..=n as usize {
            if cnt1[i] == 0 && cnt2[i] == (n as usize) - 1 {
                return i as i32;
            }
        }

        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
