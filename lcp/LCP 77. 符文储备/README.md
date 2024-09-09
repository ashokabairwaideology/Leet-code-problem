---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2077.%20%E7%AC%A6%E6%96%87%E5%82%A8%E5%A4%87/README.md
---

<!-- problem:start -->

# [LCP 77. 符文储备](https://leetcode.cn/problems/W2ZX4X)

## 题目描述

<!-- description:start -->

远征队在出发前需要携带一些「符文」，作为后续的冒险储备。`runes[i]` 表示第 `i` 枚符文的魔力值。

他们将从中选取若干符文进行携带，并对这些符文进行重新排列，以确保任意相邻的两块符文之间的魔力值相差不超过 `1`。

请返回他们能够携带的符文 **最大数量**。

**示例 1：**

> 输入：`runes = [1,3,5,4,1,7]`
>
> 输出：`3`
>
> 解释：最佳的选择方案为[3,5,4]
> 将其排列为 [3,4,5] 后，任意相邻的两块符文魔力值均不超过 `1`，携带数量为 `3`
> 其他满足条件的方案为 [1,1] 和 [7]，数量均小于 3。
> 因此返回可携带的最大数量 `3`。

**示例 2：**

> 输入：`runes = [1,1,3,3,2,4]`
>
> 输出：`6`
>
> 解释：排列为 [1,1,2,3,3,4]，可携带所有的符文

**提示：**

-   `1 <= runes.length <= 10^4`
-   `0 <= runes[i] <= 10^4`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们可以将符文按照魔力值从小到大排序，然后使用双指针维护一个滑动窗口，使得滑动窗口中的任意相邻的两块符文之间的魔力值相差不超过，找出满足条件的最大窗口长度即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $runes$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def runeReserve(self, runes: List[int]) -> int:
        runes.sort()
        ans = i = 0
        for j, x in enumerate(runes):
            if j and runes[j] - runes[j - 1] > 1:
                i = j
            else:
                ans = max(ans, j - i + 1)
        return ans
```

#### Java

```java
class Solution {
    public int runeReserve(int[] runes) {
        Arrays.sort(runes);
        int ans = 0;
        for (int i = 0, j = 0; j < runes.length; ++j) {
            if (j > 0 && runes[j] - runes[j - 1] > 1) {
                i = j;
            } else {
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int runeReserve(vector<int>& runes) {
        sort(runes.begin(), runes.end());
        int ans = 0;
        for (int i = 0, j = 0; j < runes.size(); ++j) {
            if (j && runes[j] - runes[j - 1] > 1) {
                i = j;
            } else {
                ans = max(ans, j - i + 1);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func runeReserve(runes []int) (ans int) {
	sort.Ints(runes)
	i := 0
	for j, x := range runes {
		if j > 0 && x-runes[j-1] > 1 {
			i = j
		} else if t := j - i + 1; ans < t {
			ans = t
		}
	}
	return
}
```

#### TypeScript

```ts
function runeReserve(runes: number[]): number {
    runes.sort((a, b) => a - b);
    let ans = 0;
    let i = 0;
    for (let j = 0; j < runes.length; ++j) {
        if (j > 0 && runes[j] - runes[j - 1] > 1) {
            i = j;
        } else {
            ans = Math.max(ans, j - i + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
