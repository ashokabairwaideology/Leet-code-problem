---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1402.Reducing%20Dishes/README.md
rating: 1679
source: 第 23 场双周赛 Q4
tags:
    - 贪心
    - 数组
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [1402. 做菜顺序](https://leetcode.cn/problems/reducing-dishes)

[English Version](/solution/1400-1499/1402.Reducing%20Dishes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个厨师收集了他&nbsp;<code>n</code>&nbsp;道菜的满意程度&nbsp;<code>satisfaction</code>&nbsp;，这个厨师做出每道菜的时间都是 1 单位时间。</p>

<p>一道菜的 「&nbsp;<strong>like-time 系数&nbsp;</strong>」定义为烹饪这道菜结束的时间（包含之前每道菜所花费的时间）乘以这道菜的满意程度，也就是&nbsp;<code>time[i]</code>*<code>satisfaction[i]</code>&nbsp;。</p>

<p>返回厨师在准备了一定数量的菜肴后可以获得的最大 <strong>like-time 系数</strong> 总和。</p>

<p>你可以按&nbsp;<strong>任意</strong>&nbsp;顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>satisfaction = [-1,-8,0,5,-9]
<strong>输出：</strong>14
<strong>解释：</strong>去掉第二道和最后一道菜，最大的 like-time 系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>satisfaction = [4,3,2]
<strong>输出：</strong>20
<strong>解释：可以</strong>按照任意顺序做菜 (2*1 + 3*2 + 4*3 = 20)
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>satisfaction = [-1,-4,-5]
<strong>输出：</strong>0
<strong>解释：</strong>大家都不喜欢这些菜，所以不做任何菜就可以获得最大的 like-time 系数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == satisfaction.length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>-1000 &lt;= satisfaction[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

假如我们只选择一道菜，那么我们应该选择满意度最大的那道菜 $s_0$，并且判断 $s_0$ 是否大于 0，如果 $s_0 \leq 0$，那么我们就不做菜了，否则我们做这道菜，得到的总满意度为 $s_0$。

假如我们选择两道菜，那么我们应该选择满足度最大的两道菜 $s_0$ 和 $s_1$，满意度为 $s_1 + 2 \times s_0$，此时要保证选择之后的满意度大于选择之前的满意度，即 $s_1 + 2 \times s_0 > s_0$，即 只要满足 $s_1 + s_0 > 0$，我们就可以选择这两道菜。

依此类推，我们可以得到一个规律，即我们应该选择满意度最大的 $k$ 道菜，并且保证前 $k$ 道菜的满意度之和大于 $0$。

在实现上，我们可以先对所有菜的满意度进行排序，然后从满意度最大的菜开始选择，每次累加当前这道菜的满意度，如果累加的结果小于等于 $0$，那么我们就不再选择后面的菜了，否则我们就选择这道菜。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        satisfaction.sort(reverse=True)
        ans = s = 0
        for x in satisfaction:
            s += x
            if s <= 0:
                break
            ans += s
        return ans
```

#### Java

```java
class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int ans = 0, s = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            s += satisfaction[i];
            if (s <= 0) {
                break;
            }
            ans += s;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSatisfaction(vector<int>& satisfaction) {
        sort(rbegin(satisfaction), rend(satisfaction));
        int ans = 0, s = 0;
        for (int x : satisfaction) {
            s += x;
            if (s <= 0) {
                break;
            }
            ans += s;
        }
        return ans;
    }
};
```

#### Go

```go
func maxSatisfaction(satisfaction []int) (ans int) {
	sort.Slice(satisfaction, func(i, j int) bool { return satisfaction[i] > satisfaction[j] })
	s := 0
	for _, x := range satisfaction {
		s += x
		if s <= 0 {
			break
		}
		ans += s
	}
	return
}
```

#### TypeScript

```ts
function maxSatisfaction(satisfaction: number[]): number {
    satisfaction.sort((a, b) => b - a);
    let [ans, s] = [0, 0];
    for (const x of satisfaction) {
        s += x;
        if (s <= 0) {
            break;
        }
        ans += s;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
