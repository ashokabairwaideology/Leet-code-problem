---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.14.Boolean%20Evaluation/README_EN.md
---

<!-- problem:start -->

# [08.14. Boolean Evaluation](https://leetcode.cn/problems/boolean-evaluation-lcci)

[中文文档](/lcci/08.14.Boolean%20Evaluation/README.md)

## Description

<!-- description:start -->

<p>Given a boolean expression consisting of the symbols <code>0</code> (false), <code>1</code> (true), <code>&amp;</code> (AND), <code>|</code> (OR), and <code>^</code>&nbsp;(XOR), and a desired boolean result value result, implement a function to count the number of ways of parenthesizing the expression such that it evaluates to result.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>s = &quot;1^0|0|1&quot;, result = 0



<strong>Output: </strong>2

<strong>Explanation:</strong>&nbsp;Two possible parenthesizing ways are:

1^(0|(0|1))

1^((0|0)|1)

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>s = &quot;0&amp;0&amp;0&amp;1^1|0&quot;, result = 1



<strong>Output: </strong>10</pre>

<p><strong>Note: </strong></p>

<ul>
	<li>There are no more than&nbsp;19 operators in <code>s</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countEval(self, s: str, result: int) -> int:
        @cache
        def dfs(s):
            res = [0] * 2
            if s in '01':
                res[int(s)] = 1
                return res
            for k, op in enumerate(s):
                if op in '&^|':
                    left, right = dfs(s[:k]), dfs(s[k + 1 :])
                    for i, v1 in enumerate(left):
                        for j, v2 in enumerate(right):
                            if op == '&':
                                v = i & j
                            elif op == '^':
                                v = i ^ j
                            elif op == '|':
                                v = i | j
                            res[v] += v1 * v2
            return res

        ans = dfs(s)
        return ans[result] if 0 <= result < 2 else 0
```

#### Java

```java
class Solution {
    private Map<String, int[]> memo;

    public int countEval(String s, int result) {
        memo = new HashMap<>();
        int[] ans = dfs(s);
        return result == 0 || result == 1 ? ans[result] : 0;
    }

    private int[] dfs(String s) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        int[] res = new int[2];
        if (s.length() == 1) {
            res[Integer.parseInt(s)] = 1;
            return res;
        }
        for (int k = 0; k < s.length(); ++k) {
            char op = s.charAt(k);
            if (op == '&' || op == '|' || op == '^') {
                int[] left = dfs(s.substring(0, k));
                int[] right = dfs(s.substring(k + 1));
                for (int i = 0; i < 2; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        int v = 0;
                        if (op == '&') {
                            v = i & j;
                        } else if (op == '|') {
                            v = i | j;
                        } else if (op == '^') {
                            v = i ^ j;
                        }
                        res[v] += left[i] * right[j];
                    }
                }
            }
        }
        memo.put(s, res);
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    unordered_map<string, vector<int>> memo;

    int countEval(string s, int result) {
        vector<int> ans = dfs(s);
        return result == 0 || result == 1 ? ans[result] : 0;
    }

    vector<int> dfs(string s) {
        if (memo.count(s)) return memo[s];
        vector<int> res(2);
        if (s.size() == 1) {
            res[s[0] - '0'] = 1;
            return res;
        }
        for (int k = 0; k < s.size(); ++k) {
            if (s[k] == '0' || s[k] == '1') continue;
            vector<int> left = dfs(s.substr(0, k));
            vector<int> right = dfs(s.substr(k + 1, s.size() - k));
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 2; ++j) {
                    int v = 0;
                    if (s[k] == '&')
                        v = i & j;
                    else if (s[k] == '|')
                        v = i | j;
                    else if (s[k] == '^')
                        v = i ^ j;
                    res[v] += left[i] * right[j];
                }
            }
        }
        memo[s] = res;
        return res;
    }
};
```

#### Go

```go
func countEval(s string, result int) int {
	memo := map[string][]int{}
	var dfs func(string) []int
	dfs = func(s string) []int {
		if v, ok := memo[s]; ok {
			return v
		}
		res := make([]int, 2)
		if len(s) == 1 {
			res[s[0]-'0'] = 1
			return res
		}
		for k, c := range s {
			if c == '0' || c == '1' {
				continue
			}
			left, right := dfs(s[:k]), dfs(s[k+1:])
			for i, v1 := range left {
				for j, v2 := range right {
					v := 0
					if c == '&' {
						v = i & j
					} else if c == '|' {
						v = i | j
					} else if c == '^' {
						v = i ^ j
					}
					res[v] += v1 * v2
				}
			}
		}
		memo[s] = res
		return res
	}
	ans := dfs(s)
	if result == 0 || result == 1 {
		return ans[result]
	}
	return 0
}
```

#### Swift

```swift
class Solution {
    private var memo = [String: [Int]]()

    func countEval(_ s: String, _ result: Int) -> Int {
        memo = [:]
        let ans = dfs(s)
        return result == 0 || result == 1 ? ans[result] : 0
    }

    private func dfs(_ s: String) -> [Int] {
        if let res = memo[s] {
            return res
        }

        var res = [0, 0]
        if s.count == 1 {
            res[Int(String(s))!] = 1
            return res
        }

        for k in 0..<s.count {
            let index = s.index(s.startIndex, offsetBy: k)
            let op = String(s[index])

            if op == "&" || op == "|" || op == "^" {
                let left = dfs(String(s[s.startIndex..<index]))
                let right = dfs(String(s[s.index(after: index)...]))

                for i in 0...1 {
                    for j in 0...1 {
                        var v = 0
                        if op == "&" {
                            v = i & j
                        } else if op == "|" {
                            v = i | j
                        } else if op == "^" {
                            v = i ^ j
                        }
                        res[v] += left[i] * right[j]
                    }
                }
            }
        }

        memo[s] = res
        return res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
