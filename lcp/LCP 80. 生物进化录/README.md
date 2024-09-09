---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2080.%20%E7%94%9F%E7%89%A9%E8%BF%9B%E5%8C%96%E5%BD%95/README.md
---

<!-- problem:start -->

# [LCP 80. 生物进化录](https://leetcode.cn/problems/qoQAMX)

## 题目描述

<!-- description:start -->

在永恒之森中，存在着一本生物进化录，以 **一个树形结构** 记载了所有生物的演化过程。经过观察并整理了各节点间的关系，`parents[i]` 表示编号 `i` 节点的父节点编号(根节点的父节点为 `-1`)。

为了探索和记录其中的演化规律，队伍中的炼金术师提出了一种方法，可以以字符串的形式将其复刻下来，规则如下：

-   初始只有一个根节点，表示演化的起点，依次记录 `01` 字符串中的字符，
-   如果记录 `0`，则在当前节点下添加一个子节点，并将指针指向新添加的子节点；
-   如果记录 `1`，则将指针回退到当前节点的父节点处。

现在需要应用上述的记录方法，复刻下它的演化过程。请返回能够复刻演化过程的字符串中， **字典序最小** 的 `01` 字符串。

**注意：**

-   节点指针最终可以停在任何节点上，不一定要回到根节点。

**示例 1：**

> 输入：`parents = [-1,0,0,2]`
>
> 输出：`"00110"`
>
> 解释：树结构如下图所示，共存在 2 种记录方案：
> 第 1 种方案为：0(记录编号 1 的节点) -> 1(回退至节点 0) -> 0(记录编号 2 的节点) -> 0((记录编号 3 的节点))
> 第 2 种方案为：0(记录编号 2 的节点) -> 0(记录编号 3 的节点) -> 1(回退至节点 2) -> 1(回退至节点 0) -> 0(记录编号 1 的节点)
> 返回字典序更小的 `"00110"` > ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2080.%20%E7%94%9F%E7%89%A9%E8%BF%9B%E5%8C%96%E5%BD%95/images/1682319485-cRVudI-image.png){:width=120px}![进化 (3).gif](<https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2080.%20%E7%94%9F%E7%89%A9%E8%BF%9B%E5%8C%96%E5%BD%95/images/1682412701-waHdnm-%E8%BF%9B%E5%8C%96%20(3).gif>){:width=320px}

**示例 2：**

> 输入：`parents = [-1,0,0,1,2,2]`
>
> 输出：`"00101100"`

**提示：**

-   `1 <= parents.length <= 10^4`
-   `-1 <= parents[i] < i` (即父节点编号小于子节点)

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def evolutionaryRecord(self, parents: List[int]) -> str:
        def dfs(i: int) -> str:
            t = sorted(dfs(j) for j in g[i])
            return "0" + "".join(t) + "1"

        n = len(parents)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)
        return dfs(0)[1:].rstrip("1")
```

#### Java

```java
class Solution {
    private List<Integer>[] g;

    public String evolutionaryRecord(int[] parents) {
        int n = parents.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parents[i]].add(i);
        }
        return dfs(0).substring(1).replaceAll("1+$", "");
    }

    private String dfs(int i) {
        List<String> t = new ArrayList<>();
        for (int j : g[i]) {
            t.add(dfs(j));
        }
        Collections.sort(t);
        return "0" + String.join("", t) + "1";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string evolutionaryRecord(vector<int>& parents) {
        int n = parents.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; ++i) {
            g[parents[i]].push_back(i);
        }

        function<string(int)> dfs = [&](int i) -> string {
            vector<string> t;
            for (int j : g[i]) {
                t.push_back(dfs(j));
            }
            sort(t.begin(), t.end());
            string res = "0";
            for (const string& s : t) {
                res += s;
            }
            res += "1";
            return res;
        };

        string ans = dfs(0);
        return ans.substr(1, ans.find_last_not_of('1'));
    }
};
```

#### Go

```go
func evolutionaryRecord(parents []int) string {
	n := len(parents)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parents[i]] = append(g[parents[i]], i)
	}

	var dfs func(int) string
	dfs = func(i int) string {
		var t []string
		for _, j := range g[i] {
			t = append(t, dfs(j))
		}
		sort.Strings(t)
		return "0" + strings.Join(t, "") + "1"
	}

	ans := dfs(0)[1:]
	return strings.TrimRight(ans, "1")
}
```

<!-- tabs:end -->

<!-- problem:end -->
