---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2842.Count%20K-Subsequences%20of%20a%20String%20With%20Maximum%20Beauty/README_EN.md
rating: 2091
source: Biweekly Contest 112 Q4
tags:
    - Greedy
    - Hash Table
    - Math
    - String
    - Combinatorics
---

<!-- problem:start -->

# [2842. Count K-Subsequences of a String With Maximum Beauty](https://leetcode.com/problems/count-k-subsequences-of-a-string-with-maximum-beauty)

[中文文档](/solution/2800-2899/2842.Count%20K-Subsequences%20of%20a%20String%20With%20Maximum%20Beauty/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>A <strong>k-subsequence</strong> is a <strong>subsequence</strong> of <code>s</code>, having length <code>k</code>, and all its characters are <strong>unique</strong>, <strong>i.e</strong>., every character occurs once.</p>

<p>Let <code>f(c)</code> denote the number of times the character <code>c</code> occurs in <code>s</code>.</p>

<p>The <strong>beauty</strong> of a <strong>k-subsequence</strong> is the <strong>sum</strong> of <code>f(c)</code> for every character <code>c</code> in the k-subsequence.</p>

<p>For example, consider <code>s = &quot;abbbdd&quot;</code> and <code>k = 2</code>:</p>

<ul>
	<li><code>f(&#39;a&#39;) = 1</code>, <code>f(&#39;b&#39;) = 3</code>, <code>f(&#39;d&#39;) = 2</code></li>
	<li>Some k-subsequences of <code>s</code> are:
	<ul>
		<li><code>&quot;<u><strong>ab</strong></u>bbdd&quot;</code> -&gt; <code>&quot;ab&quot;</code> having a beauty of <code>f(&#39;a&#39;) + f(&#39;b&#39;) = 4</code></li>
		<li><code>&quot;<u><strong>a</strong></u>bbb<strong><u>d</u></strong>d&quot;</code> -&gt; <code>&quot;ad&quot;</code> having a beauty of <code>f(&#39;a&#39;) + f(&#39;d&#39;) = 3</code></li>
		<li><code>&quot;a<strong><u>b</u></strong>bb<u><strong>d</strong></u>d&quot;</code> -&gt; <code>&quot;bd&quot;</code> having a beauty of <code>f(&#39;b&#39;) + f(&#39;d&#39;) = 5</code></li>
	</ul>
	</li>
</ul>

<p>Return <em>an integer denoting the number of k-subsequences </em><em>whose <strong>beauty</strong> is the <strong>maximum</strong> among all <strong>k-subsequences</strong></em>. Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>A subsequence of a string is a new string formed from the original string by deleting some (possibly none) of the characters without disturbing the relative positions of the remaining characters.</p>

<p><strong>Notes</strong></p>

<ul>
	<li><code>f(c)</code> is the number of times a character <code>c</code> occurs in <code>s</code>, not a k-subsequence.</li>
	<li>Two k-subsequences are considered different if one is formed by an index that is not present in the other. So, two k-subsequences may form the same string.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bcca&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> <span style="white-space: normal">From s we have f(&#39;a&#39;) = 1, f(&#39;b&#39;) = 1, and f(&#39;c&#39;) = 2.</span>
The k-subsequences of s are: 
<strong><u>bc</u></strong>ca having a beauty of f(&#39;b&#39;) + f(&#39;c&#39;) = 3 
<strong><u>b</u></strong>c<u><strong>c</strong></u>a having a beauty of f(&#39;b&#39;) + f(&#39;c&#39;) = 3 
<strong><u>b</u></strong>cc<strong><u>a</u></strong> having a beauty of f(&#39;b&#39;) + f(&#39;a&#39;) = 2 
b<strong><u>c</u></strong>c<u><strong>a</strong></u><strong> </strong>having a beauty of f(&#39;c&#39;) + f(&#39;a&#39;) = 3
bc<strong><u>ca</u></strong> having a beauty of f(&#39;c&#39;) + f(&#39;a&#39;) = 3 
There are 4 k-subsequences that have the maximum beauty, 3. 
Hence, the answer is 4. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbcd&quot;, k = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> From s we have f(&#39;a&#39;) = 1, f(&#39;b&#39;) = 2, f(&#39;c&#39;) = 1, and f(&#39;d&#39;) = 1. 
The k-subsequences of s are: 
<u><strong>ab</strong></u>b<strong><u>cd</u></strong> having a beauty of f(&#39;a&#39;) + f(&#39;b&#39;) + f(&#39;c&#39;) + f(&#39;d&#39;) = 5
<u style="white-space: normal;"><strong>a</strong></u>b<u><strong>bcd</strong></u> having a beauty of f(&#39;a&#39;) + f(&#39;b&#39;) + f(&#39;c&#39;) + f(&#39;d&#39;) = 5 
There are 2 k-subsequences that have the maximum beauty, 5. 
Hence, the answer is 2. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Combinatorial Mathematics

First, we use a hash table $f$ to count the occurrence of each character in the string $s$, i.e., $f[c]$ represents the number of times character $c$ appears in the string $s$.

Since a $k$-subsequence is a subsequence of length $k$ in the string $s$ with unique characters, if the number of different characters in $f$ is less than $k$, then there is no $k$-subsequence, and we can directly return $0$.

Otherwise, to maximize the beauty value of the $k$-subsequence, we need to make characters with high beauty values appear as much as possible in the $k$-subsequence. Therefore, we can sort the values in $f$ in reverse order to get an array $vs$.

We denote the occurrence of the $k$th character in the array $vs$ as $val$, and there are $x$ characters with an occurrence of $val$.

Then we first find out the characters with occurrences greater than $val$, multiply the occurrences of each character to get the initial answer $ans$, and update the remaining number of characters to be selected to $k$. We need to select $k$ characters from $x$ characters, so the answer needs to be multiplied by the combination number $C_x^k$, and finally multiplied by $val^k$, i.e., $ans = ans \times C_x^k \times val^k$.

Note that we need to use fast power and modulo operations here.

The time complexity is $O(n)$, and the space complexity is $O(|\Sigma|)$. Here, $n$ is the length of the string, and $\Sigma$ is the character set. In this problem, the character set is lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countKSubsequencesWithMaxBeauty(self, s: str, k: int) -> int:
        f = Counter(s)
        if len(f) < k:
            return 0
        mod = 10**9 + 7
        vs = sorted(f.values(), reverse=True)
        val = vs[k - 1]
        x = vs.count(val)
        ans = 1
        for v in vs:
            if v == val:
                break
            k -= 1
            ans = ans * v % mod
        ans = ans * comb(x, k) * pow(val, k, mod) % mod
        return ans
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int[] f = new int[26];
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (++f[s.charAt(i) - 'a'] == 1) {
                ++cnt;
            }
        }
        if (cnt < k) {
            return 0;
        }
        Integer[] vs = new Integer[cnt];
        for (int i = 0, j = 0; i < 26; ++i) {
            if (f[i] > 0) {
                vs[j++] = f[i];
            }
        }
        Arrays.sort(vs, (a, b) -> b - a);
        long ans = 1;
        int val = vs[k - 1];
        int x = 0;
        for (int v : vs) {
            if (v == val) {
                ++x;
            }
        }
        for (int v : vs) {
            if (v == val) {
                break;
            }
            --k;
            ans = ans * v % mod;
        }
        int[][] c = new int[x + 1][x + 1];
        for (int i = 0; i <= x; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
            }
        }
        ans = ((ans * c[x][k]) % mod) * qpow(val, k) % mod;
        return (int) ans;
    }

    private long qpow(long a, int n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countKSubsequencesWithMaxBeauty(string s, int k) {
        int f[26]{};
        int cnt = 0;
        for (char& c : s) {
            if (++f[c - 'a'] == 1) {
                ++cnt;
            }
        }
        if (cnt < k) {
            return 0;
        }
        vector<int> vs(cnt);
        for (int i = 0, j = 0; i < 26; ++i) {
            if (f[i]) {
                vs[j++] = f[i];
            }
        }
        sort(vs.rbegin(), vs.rend());
        const int mod = 1e9 + 7;
        long long ans = 1;
        int val = vs[k - 1];
        int x = 0;
        for (int v : vs) {
            x += v == val;
        }
        for (int v : vs) {
            if (v == val) {
                break;
            }
            --k;
            ans = ans * v % mod;
        }
        int c[x + 1][x + 1];
        memset(c, 0, sizeof(c));
        for (int i = 0; i <= x; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        auto qpow = [&](long long a, int n) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        ans = (ans * c[x][k] % mod) * qpow(val, k) % mod;
        return ans;
    }
};
```

#### Go

```go
func countKSubsequencesWithMaxBeauty(s string, k int) int {
	f := [26]int{}
	cnt := 0
	for _, c := range s {
		f[c-'a']++
		if f[c-'a'] == 1 {
			cnt++
		}
	}
	if cnt < k {
		return 0
	}
	vs := []int{}
	for _, x := range f {
		if x > 0 {
			vs = append(vs, x)
		}
	}
	sort.Slice(vs, func(i, j int) bool {
		return vs[i] > vs[j]
	})
	const mod int = 1e9 + 7
	ans := 1
	val := vs[k-1]
	x := 0
	for _, v := range vs {
		if v == val {
			x++
		}
	}
	for _, v := range vs {
		if v == val {
			break
		}
		k--
		ans = ans * v % mod
	}
	c := make([][]int, x+1)
	for i := range c {
		c[i] = make([]int, x+1)
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = (c[i-1][j-1] + c[i-1][j]) % mod
		}
	}
	qpow := func(a, n int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	ans = (ans * c[x][k] % mod) * qpow(val, k) % mod
	return ans
}
```

#### TypeScript

```ts
function countKSubsequencesWithMaxBeauty(s: string, k: number): number {
    const f: number[] = new Array(26).fill(0);
    let cnt = 0;
    for (const c of s) {
        const i = c.charCodeAt(0) - 97;
        if (++f[i] === 1) {
            ++cnt;
        }
    }
    if (cnt < k) {
        return 0;
    }
    const mod = BigInt(10 ** 9 + 7);
    const vs: number[] = f.filter(v => v > 0).sort((a, b) => b - a);
    const val = vs[k - 1];
    const x = vs.filter(v => v === val).length;
    let ans = 1n;
    for (const v of vs) {
        if (v === val) {
            break;
        }
        --k;
        ans = (ans * BigInt(v)) % mod;
    }
    const c: number[][] = new Array(x + 1).fill(0).map(() => new Array(k + 1).fill(0));
    for (let i = 0; i <= x; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= Math.min(i, k); ++j) {
            c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % Number(mod);
        }
    }
    const qpow = (a: bigint, n: number): bigint => {
        let ans = 1n;
        for (; n; n >>>= 1) {
            if (n & 1) {
                ans = (ans * a) % BigInt(mod);
            }
            a = (a * a) % BigInt(mod);
        }
        return ans;
    };
    ans = (((ans * BigInt(c[x][k])) % mod) * qpow(BigInt(val), k)) % mod;
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
