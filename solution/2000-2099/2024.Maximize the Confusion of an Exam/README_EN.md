---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README_EN.md
rating: 1643
source: Biweekly Contest 62 Q3
tags:
    - String
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [2024. Maximize the Confusion of an Exam](https://leetcode.com/problems/maximize-the-confusion-of-an-exam)

[中文文档](/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README.md)

## Description

<!-- description:start -->

<p>A teacher is writing a test with <code>n</code> true/false questions, with <code>&#39;T&#39;</code> denoting true and <code>&#39;F&#39;</code> denoting false. He wants to confuse the students by <strong>maximizing</strong> the number of <strong>consecutive</strong> questions with the <strong>same</strong> answer (multiple trues or multiple falses in a row).</p>

<p>You are given a string <code>answerKey</code>, where <code>answerKey[i]</code> is the original answer to the <code>i<sup>th</sup></code> question. In addition, you are given an integer <code>k</code>, the maximum number of times you may perform the following operation:</p>

<ul>
	<li>Change the answer key for any question to <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code> (i.e., set <code>answerKey[i]</code> to <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code>).</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of consecutive</em> <code>&#39;T&#39;</code>s or <code>&#39;F&#39;</code>s <em>in the answer key after performing the operation at most</em> <code>k</code> <em>times</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> answerKey = &quot;TTFF&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can replace both the &#39;F&#39;s with &#39;T&#39;s to make answerKey = &quot;<u>TTTT</u>&quot;.
There are four consecutive &#39;T&#39;s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> answerKey = &quot;TFFT&quot;, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can replace the first &#39;T&#39; with an &#39;F&#39; to make answerKey = &quot;<u>FFF</u>T&quot;.
Alternatively, we can replace the second &#39;T&#39; with an &#39;F&#39; to make answerKey = &quot;T<u>FFF</u>&quot;.
In both cases, there are three consecutive &#39;F&#39;s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> answerKey = &quot;TTFTTFTT&quot;, k = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> We can replace the first &#39;F&#39; to make answerKey = &quot;<u>TTTTT</u>FTT&quot;
Alternatively, we can replace the second &#39;F&#39; to make answerKey = &quot;TTF<u>TTTTT</u>&quot;. 
In both cases, there are five consecutive &#39;T&#39;s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == answerKey.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>answerKey[i]</code> is either <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

We design a function $\textit{f}(c)$, which represents the longest length of consecutive characters under the condition that at most $k$ characters $c$ can be replaced, where $c$ can be 'T' or 'F'. The answer is $\max(\textit{f}('T'), \textit{f}('F'))$.

We iterate through the string $\textit{answerKey}$, using a variable $\textit{cnt}$ to record the number of characters $c$ within the current window. When $\textit{cnt} > k$, we move the left pointer of the window one position to the right. After the iteration ends, the length of the window is the maximum length of consecutive characters.

Time complexity is $O(n)$, where $n$ is the length of the string. Space complexity is $O(1)$.

Similar problems:

-   [487. Max Consecutive Ones II](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README_EN.md)
-   [1004. Max Consecutive Ones III](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1004.Max%20Consecutive%20Ones%20III/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxConsecutiveAnswers(self, answerKey: str, k: int) -> int:
        def f(c: str) -> int:
            cnt = l = 0
            for ch in answerKey:
                cnt += ch == c
                if cnt > k:
                    cnt -= answerKey[l] == c
                    l += 1
            return len(answerKey) - l

        return max(f("T"), f("F"))
```

#### Java

```java
class Solution {
    private char[] s;
    private int k;

    public int maxConsecutiveAnswers(String answerKey, int k) {
        s = answerKey.toCharArray();
        this.k = k;
        return Math.max(f('T'), f('F'));
    }

    private int f(char c) {
        int l = 0, cnt = 0;
        for (char ch : s) {
            cnt += ch == c ? 1 : 0;
            if (cnt > k) {
                cnt -= s[l++] == c ? 1 : 0;
            }
        }
        return s.length - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        int n = answerKey.size();
        auto f = [&](char c) {
            int l = 0, cnt = 0;
            for (char& ch : answerKey) {
                cnt += ch == c;
                if (cnt > k) {
                    cnt -= answerKey[l++] == c;
                }
            }
            return n - l;
        };
        return max(f('T'), f('F'));
    }
};
```

#### Go

```go
func maxConsecutiveAnswers(answerKey string, k int) int {
	f := func(c byte) int {
		l, cnt := 0, 0
		for _, ch := range answerKey {
			if byte(ch) == c {
				cnt++
			}
			if cnt > k {
				if answerKey[l] == c {
					cnt--
				}
				l++
			}
		}
		return len(answerKey) - l
	}
	return max(f('T'), f('F'))
}
```

#### TypeScript

```ts
function maxConsecutiveAnswers(answerKey: string, k: number): number {
    const n = answerKey.length;
    const f = (c: string): number => {
        let [l, cnt] = [0, 0];
        for (const ch of answerKey) {
            cnt += ch === c ? 1 : 0;
            if (cnt > k) {
                cnt -= answerKey[l++] === c ? 1 : 0;
            }
        }
        return n - l;
    };
    return Math.max(f('T'), f('F'));
}
```

#### Rust

```rust
impl Solution {
    pub fn max_consecutive_answers(answer_key: String, k: i32) -> i32 {
        let n = answer_key.len();
        let k = k as usize;
        let s: Vec<char> = answer_key.chars().collect();

        let f = |c: char| -> usize {
            let mut l = 0;
            let mut cnt = 0;
            for &ch in &s {
                cnt += if ch == c { 1 } else { 0 };
                if cnt > k {
                    cnt -= if s[l] == c { 1 } else { 0 };
                    l += 1;
                }
            }
            n - l
        };

        std::cmp::max(f('T'), f('F')) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
