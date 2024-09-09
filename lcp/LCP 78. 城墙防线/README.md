---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2078.%20%E5%9F%8E%E5%A2%99%E9%98%B2%E7%BA%BF/README.md
---

<!-- problem:start -->

# [LCP 78. 城墙防线](https://leetcode.cn/problems/Nsibyl)

## 题目描述

<!-- description:start -->

在探险营地间，小扣意外发现了一片城墙遗迹，在探索期间，却不巧遇到迁徙中的兽群向他迎面冲来。情急之下小扣吹响了他的苍蓝笛，随着笛声响起，遗迹中的城墙逐渐发生了横向膨胀。
已知 `rampart[i] = [x,y]` 表示第 `i` 段城墙的初始所在区间。当城墙发生膨胀时，将遵循以下规则：

-   所有的城墙会同时膨胀相等的长度；
-   每个城墙可以向左、向右或向两个方向膨胀。

小扣为了确保自身的安全，需要在所有城墙均无重叠的情况下，让城墙尽可能的膨胀。请返回城墙可以膨胀的 **最大值** 。

**注意：**

-   初始情况下，所有城墙均不重叠，且 `rampart` 中的元素升序排列；
-   两侧的城墙可以向外无限膨胀。

**示例 1：**

> 输入：`rampart = [[0,3],[4,5],[7,9]]`
>
> 输出：`3`
>
> 解释：如下图所示：
> `rampart[0]` 向左侧膨胀 3 个单位；
> `rampart[2]` 向右侧膨胀 3 个单位；
> `rampart[1]` 向左侧膨胀 1 个单位，向右膨胀 2 个单位。
> 不存在膨胀更多的方案，返回 3。
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2078.%20%E5%9F%8E%E5%A2%99%E9%98%B2%E7%BA%BF/images/1681717918-tWywrp-image.png){:width=750px}

**示例 2：**

> 输入：`rampart = [[1,2],[5,8],[11,15],[18,25]]`
>
> 输出：`4`

**提示：**

-   `3 <= rampart.length <= 10^4`
-   `rampart[i].length == 2`
-   `0 <= rampart[i][0] < rampart[i][1] <= rampart[i+1][0] <= 10^8`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，如果一个膨胀值 $x$ 满足条件，那么所有小于 $x$ 的值也都满足条件，这存在着单调性。因此我们可以使用二分查找的方法，找到最大的满足条件的膨胀值。

我们定义二分查找的左边界 $left=0$, $right=rampart[1][0]-rampart[0][1]+rampart[2][0]-rampart[1][1]$，其中 $rampart[i][0]$ 表示第 $i$ 段城墙的左端点，$rampart[i][1]$ 表示第 $i$ 段城墙的右端点。

接下来，我们开始进行二分查找。每一次，我们求出当前的中间值 $mid$，并判断其是否满足条件。如果满足条件，那么我们就将左边界 $left$ 更新为 $mid$，否则将右边界 $right$ 更新为 $mid-1$。在二分查找结束后，我们返回左边界 $left$ 即可。

那么问题的关键在于如何判断一个值 $w$ 是否满足条件。显然，我们可以用贪心的策略，每次先尽可能往左膨胀，如果还有剩余的膨胀值，再往右膨胀，如果在向右膨胀的过程中，发生了重叠，那么说明当前的膨胀值 $w$ 不满足条件，直接返回 `false` 即可。否则，遍历结束，返回 `true`。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组 $rampart$ 的长度和二分查找的右边界，本题中右边界最大为 $range=rampart[1][0]-rampart[0][1]+rampart[2][0]-rampart[1][1]$。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rampartDefensiveLine(self, rampart: List[List[int]]) -> int:
        def check(w: int) -> bool:
            last = rampart[0][1]
            for i in range(1, len(rampart) - 1):
                x, y = rampart[i]
                a = x - last
                b = max(w - a, 0)
                if y + b > rampart[i + 1][0]:
                    return False
                last = y + b
            return True

        left, right = 0, rampart[1][0] - rampart[0][1] + rampart[2][0] - rampart[1][1]
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

#### Java

```java
class Solution {
    private int[][] rampart;

    public int rampartDefensiveLine(int[][] rampart) {
        this.rampart = rampart;
        int left = 0, right = rampart[1][0] - rampart[0][1] + rampart[2][0] - rampart[1][1];
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int w) {
        int last = rampart[0][1];
        for (int i = 1; i < rampart.length - 1; ++i) {
            int x = rampart[i][0], y = rampart[i][1];
            int a = x - last;
            int b = Math.max(w - a, 0);
            if (y + b > rampart[i + 1][0]) {
                return false;
            }
            last = y + b;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rampartDefensiveLine(vector<vector<int>>& rampart) {
        int left = 0, right = rampart[1][0] - rampart[0][1] + rampart[2][0] - rampart[1][1];
        auto check = [&](int w) {
            int last = rampart[0][1];
            for (int i = 1; i < rampart.size() - 1; ++i) {
                int x = rampart[i][0], y = rampart[i][1];
                int a = x - last;
                int b = max(w - a, 0);
                if (y + b > rampart[i + 1][0]) {
                    return false;
                }
                last = y + b;
            }
            return true;
        };

        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

#### Go

```go
func rampartDefensiveLine(rampart [][]int) int {
	check := func(w int) bool {
		last := rampart[0][1]
		for i := 1; i < len(rampart)-1; i++ {
			x, y := rampart[i][0], rampart[i][1]
			a := x - last
			b := max(w-a, 0)
			if y+b > rampart[i+1][0] {
				return false
			}
			last = y + b
		}
		return true
	}

	left, right := 0, rampart[1][0]-rampart[0][1]+rampart[2][0]-rampart[1][1]
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

#### TypeScript

```ts
function rampartDefensiveLine(rampart: number[][]): number {
    const check = (w: number): boolean => {
        let last = rampart[0][1];
        for (let i = 1; i < rampart.length - 1; ++i) {
            const [x, y] = rampart[i];
            const a = x - last;
            const b = Math.max(w - a, 0);
            if (y + b > rampart[i + 1][0]) {
                return false;
            }
            last = y + b;
        }
        return true;
    };
    let left = 0;
    let right = rampart[1][0] - rampart[0][1] + rampart[2][0] - rampart[1][1];
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
