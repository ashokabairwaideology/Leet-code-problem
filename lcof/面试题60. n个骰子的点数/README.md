---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9860.%20n%E4%B8%AA%E9%AA%B0%E5%AD%90%E7%9A%84%E7%82%B9%E6%95%B0/README.md
---

<!-- problem:start -->

# [面试题 60. n 个骰子的点数](https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/)

## 题目描述

<!-- description:start -->

<p>把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。</p>

<p>&nbsp;</p>

<p>你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 1
<strong>输出:</strong> [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 2
<strong>输出:</strong> [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>1 &lt;= n &lt;= 11</code></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示投掷 $i$ 个骰子，点数和为 $j$ 的方案数。那么我们可以写出状态转移方程：

$$
f[i][j] = \sum_{k=1}^6 f[i-1][j-k]
$$

其中 $k$ 表示当前骰子的点数，而 $f[i-1][j-k]$ 表示投掷 $i-1$ 个骰子，点数和为 $j-k$ 的方案数。

初始条件为 $f[1][j] = 1$，表示投掷一个骰子，点数和为 $j$ 的方案数为 $1$。

最终，我们要求的答案即为 $\frac{f[n][j]}{6^n}$，其中 $n$ 为骰子个数，而 $j$ 的取值范围为 $[n, 6n]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(6n)$。其中 $n$ 为骰子个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dicesProbability(self, n: int) -> List[float]:
        f = [[0] * (6 * n + 1) for _ in range(n + 1)]
        for j in range(1, 7):
            f[1][j] = 1
        for i in range(2, n + 1):
            for j in range(i, 6 * i + 1):
                for k in range(1, 7):
                    if j - k >= 0:
                        f[i][j] += f[i - 1][j - k]
        m = pow(6, n)
        return [f[n][j] / m for j in range(n, 6 * n + 1)]
```

#### Java

```java
class Solution {
    public double[] dicesProbability(int n) {
        int[][] f = new int[n + 1][6 * n + 1];
        for (int j = 1; j <= 6; ++j) {
            f[1][j] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j >= k) {
                        f[i][j] += f[i - 1][j - k];
                    }
                }
            }
        }
        double m = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int j = n; j <= 6 * n; ++j) {
            ans[j - n] = f[n][j] / m;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<double> dicesProbability(int n) {
        int f[n + 1][6 * n + 1];
        memset(f, 0, sizeof f);
        for (int j = 1; j <= 6; ++j) {
            f[1][j] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j >= k) {
                        f[i][j] += f[i - 1][j - k];
                    }
                }
            }
        }
        vector<double> ans;
        double m = pow(6, n);
        for (int j = n; j <= 6 * n; ++j) {
            ans.push_back(f[n][j] / m);
        }
        return ans;
    }
};
```

#### Go

```go
func dicesProbability(n int) (ans []float64) {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 6*n+1)
	}
	for j := 1; j <= 6; j++ {
		f[1][j] = 1
	}
	for i := 2; i <= n; i++ {
		for j := i; j <= 6*i; j++ {
			for k := 1; k <= 6; k++ {
				if j >= k {
					f[i][j] += f[i-1][j-k]
				}
			}
		}
	}
	m := math.Pow(6, float64(n))
	for j := n; j <= 6*n; j++ {
		ans = append(ans, float64(f[n][j])/m)
	}
	return
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var dicesProbability = function (n) {
    const f = Array.from({ length: n + 1 }, () => Array(6 * n + 1).fill(0));
    for (let j = 1; j <= 6; ++j) {
        f[1][j] = 1;
    }
    for (let i = 2; i <= n; ++i) {
        for (let j = i; j <= 6 * i; ++j) {
            for (let k = 1; k <= 6; ++k) {
                if (j >= k) {
                    f[i][j] += f[i - 1][j - k];
                }
            }
        }
    }
    const ans = [];
    const m = Math.pow(6, n);
    for (let j = n; j <= 6 * n; ++j) {
        ans.push(f[n][j] / m);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public double[] DicesProbability(int n) {
        int[,] f = new int[n + 1, 6 * n + 1];

        for (int j = 1; j <= 6; ++j) {
            f[1, j] = 1;
        }

        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j >= k) {
                        f[i, j] += f[i - 1, j - k];
                    }
                }
            }
        }

        double m = Math.Pow(6, n);
        double[] ans = new double[5 * n + 1];

        for (int j = n; j <= 6 * n; ++j) {
            ans[j - n] = f[n, j] / m;
        }

        return ans;
    }
}
```

#### Swift

```swift
class Solution {
    func dicesProbability(_ n: Int) -> [Double] {
        var f = Array(repeating: Array(repeating: 0, count: 6 * n + 1), count: n + 1)
        for j in 1...6 {
            f[1][j] = 1
        }
        if n > 1 {
            for i in 2...n {
                for j in i...(6 * i) {
                    for k in 1...6 {
                        if j >= k {
                            f[i][j] += f[i - 1][j - k]
                        }
                    }
                }
            }
        }
        var m = 1.0
        for _ in 0..<n {
            m *= 6.0
        }
        var ans = Array(repeating: 0.0, count: 5 * n + 1)
        for j in n...(6 * n) {
            ans[j - n] = Double(f[n][j]) / m
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：动态规划（空间优化）

我们可以发现，上述方法中的 $f[i][j]$ 的值仅与 $f[i-1][j-k]$ 有关，因此我们可以使用滚动数组的方式，将空间复杂度优化至 $O(6n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dicesProbability(self, n: int) -> List[float]:
        f = [0] + [1] * 6
        for i in range(2, n + 1):
            g = [0] * (6 * i + 1)
            for j in range(i, 6 * i + 1):
                for k in range(1, 7):
                    if 0 <= j - k < len(f):
                        g[j] += f[j - k]
            f = g
        m = pow(6, n)
        return [f[j] / m for j in range(n, 6 * n + 1)]
```

#### Java

```java
class Solution {
    public double[] dicesProbability(int n) {
        int[] f = new int[7];
        Arrays.fill(f, 1);
        f[0] = 0;
        for (int i = 2; i <= n; ++i) {
            int[] g = new int[6 * i + 1];
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j - k >= 0 && j - k < f.length) {
                        g[j] += f[j - k];
                    }
                }
            }
            f = g;
        }
        double m = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int j = n; j <= 6 * n; ++j) {
            ans[j - n] = f[j] / m;
        }
        return ans;
    }
}
```

#### Go

```go
func dicesProbability(n int) (ans []float64) {
	f := make([]int, 7)
	for i := 1; i <= 6; i++ {
		f[i] = 1
	}

	for i := 2; i <= n; i++ {
		g := make([]int, 6*i+1)
		for j := i; j <= 6*i; j++ {
			for k := 1; k <= 6; k++ {
				if j-k >= 0 && j-k < len(f) {
					g[j] += f[j-k]
				}
			}
		}
		f = g
	}

	m := math.Pow(6, float64(n))
	for j := n; j <= 6*n; j++ {
		ans = append(ans, float64(f[j])/m)
	}
	return
}
```

#### JavaScript

```js
/**
 * @param {number} num
 * @return {number[]}
 */
var dicesProbability = function (n) {
    let f = Array(7).fill(1);
    f[0] = 0;
    for (let i = 2; i <= n; ++i) {
        let g = Array(6 * i + 1).fill(0);
        for (let j = i; j <= 6 * i; ++j) {
            for (let k = 1; k <= 6; ++k) {
                if (j - k >= 0 && j - k < f.length) {
                    g[j] += f[j - k];
                }
            }
        }
        f = g;
    }

    const ans = [];
    const m = Math.pow(6, n);
    for (let j = n; j <= 6 * n; ++j) {
        ans.push(f[j] / m);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public double[] DicesProbability(int n) {
        int[] f = new int[7];
        for (int i = 1; i <= 6; ++i) {
            f[i] = 1;
        }
        f[0] = 0;

        for (int i = 2; i <= n; ++i) {
            int[] g = new int[6 * i + 1];
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j - k >= 0 && j - k < f.Length) {
                        g[j] += f[j - k];
                    }
                }
            }
            f = g;
        }

        double m = Math.Pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int j = n; j <= 6 * n; ++j) {
            ans[j - n] = f[j] / m;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
