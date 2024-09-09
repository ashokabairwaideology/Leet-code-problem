---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1510.Stone%20Game%20IV/README_EN.md
rating: 1786
source: Biweekly Contest 30 Q4
tags:
    - Math
    - Dynamic Programming
    - Game Theory
---

<!-- problem:start -->

# [1510. Stone Game IV](https://leetcode.com/problems/stone-game-iv)

[中文文档](/solution/1500-1599/1510.Stone%20Game%20IV/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob take turns playing a game, with Alice starting first.</p>

<p>Initially, there are <code>n</code> stones in a pile. On each player&#39;s turn, that player makes a <em>move</em> consisting of removing <strong>any</strong> non-zero <strong>square number</strong> of stones in the pile.</p>

<p>Also, if a player cannot make a move, he/she loses the game.</p>

<p>Given a positive integer <code>n</code>, return <code>true</code> if and only if Alice wins the game otherwise return <code>false</code>, assuming both players play optimally.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> true
<strong>Explanation: </strong>Alice can remove 1 stone winning the game because Bob doesn&#39;t have any moves.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> false
<strong>Explanation: </strong>Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -&gt; 1 -&gt; 0).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> n is already a perfect square, Alice can win with one move, removing 4 stones (4 -&gt; 0).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def winnerSquareGame(self, n: int) -> bool:
        @cache
        def dfs(i: int) -> bool:
            if i == 0:
                return False
            j = 1
            while j * j <= i:
                if not dfs(i - j * j):
                    return True
                j += 1
            return False

        return dfs(n)
```

#### Java

```java
class Solution {
    private Boolean[] f;

    public boolean winnerSquareGame(int n) {
        f = new Boolean[n + 1];
        return dfs(n);
    }

    private boolean dfs(int i) {
        if (i <= 0) {
            return false;
        }
        if (f[i] != null) {
            return f[i];
        }
        for (int j = 1; j <= i / j; ++j) {
            if (!dfs(i - j * j)) {
                return f[i] = true;
            }
        }
        return f[i] = false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool winnerSquareGame(int n) {
        int f[n + 1];
        memset(f, 0, sizeof(f));
        function<bool(int)> dfs = [&](int i) -> bool {
            if (i <= 0) {
                return false;
            }
            if (f[i] != 0) {
                return f[i] == 1;
            }
            for (int j = 1; j <= i / j; ++j) {
                if (!dfs(i - j * j)) {
                    f[i] = 1;
                    return true;
                }
            }
            f[i] = -1;
            return false;
        };
        return dfs(n);
    }
};
```

#### Go

```go
func winnerSquareGame(n int) bool {
	f := make([]int, n+1)
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i <= 0 {
			return false
		}
		if f[i] != 0 {
			return f[i] == 1
		}
		for j := 1; j <= i/j; j++ {
			if !dfs(i - j*j) {
				f[i] = 1
				return true
			}
		}
		f[i] = -1
		return false
	}
	return dfs(n)
}
```

#### TypeScript

```ts
function winnerSquareGame(n: number): boolean {
    const f: number[] = new Array(n + 1).fill(0);
    const dfs = (i: number): boolean => {
        if (i <= 0) {
            return false;
        }
        if (f[i] !== 0) {
            return f[i] === 1;
        }
        for (let j = 1; j * j <= i; ++j) {
            if (!dfs(i - j * j)) {
                f[i] = 1;
                return true;
            }
        }
        f[i] = -1;
        return false;
    };
    return dfs(n);
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
    def winnerSquareGame(self, n: int) -> bool:
        f = [False] * (n + 1)
        for i in range(1, n + 1):
            j = 1
            while j <= i // j:
                if not f[i - j * j]:
                    f[i] = True
                    break
                j += 1
        return f[n]
```

#### Java

```java
class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] f = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i / j; ++j) {
                if (!f[i - j * j]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool winnerSquareGame(int n) {
        bool f[n + 1];
        memset(f, false, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i / j; ++j) {
                if (!f[i - j * j]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func winnerSquareGame(n int) bool {
	f := make([]bool, n+1)
	for i := 1; i <= n; i++ {
		for j := 1; j <= i/j; j++ {
			if !f[i-j*j] {
				f[i] = true
				break
			}
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function winnerSquareGame(n: number): boolean {
    const f: boolean[] = new Array(n + 1).fill(false);
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j * j <= i; ++j) {
            if (!f[i - j * j]) {
                f[i] = true;
                break;
            }
        }
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
