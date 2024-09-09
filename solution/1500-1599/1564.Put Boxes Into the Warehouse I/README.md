---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/README.md
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [1564. 把箱子放进仓库里 I 🔒](https://leetcode.cn/problems/put-boxes-into-the-warehouse-i)

[English Version](/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个正整数数组&nbsp;<code>boxes</code>&nbsp;和&nbsp;<code>warehouse</code>&nbsp;，分别包含单位宽度的箱子的高度，以及仓库中 <code>n</code> 个房间各自的高度。仓库的房间分别从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;自左向右编号，&nbsp;<code>warehouse[i]</code>&nbsp;（索引从 0 开始）是第&nbsp;<code>i</code>&nbsp;个房间的高度。</p>

<p>箱子放进仓库时遵循下列规则：</p>

<ul>
	<li>箱子不可叠放。</li>
	<li>你可以重新调整箱子的顺序。</li>
	<li>箱子只能从左向右推进仓库中。</li>
	<li>如果仓库中某房间的高度小于某箱子的高度，则这个箱子和之后的箱子都会停在这个房间的前面。</li>
</ul>

<p>你最多可以在仓库中放进多少个箱子？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/images/11.png" style="height: 242px; width: 400px;" /></strong></p>

<pre>
<strong>输入：</strong>boxes = [4,3,4,1], warehouse = [5,3,3,4,1]
<strong>输出：</strong>3
<strong>解释：
</strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/images/12.png" style="height: 242px; width: 280px;" />
我们可以先把高度为 1 的箱子放入 4 号房间，然后再把高度为 3 的箱子放入 1 号、 2 号或 3 号房间，最后再把高度为 4 的箱子放入 0 号房间。
我们不可能把所有 4 个箱子全部放进仓库里。</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/images/21.png" style="height: 202px; width: 400px;" /></strong></p>

<pre>
<strong>输入：</strong>boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
<strong>输出：</strong>3
<strong>解释：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/images/22.png" style="height: 202px; width: 280px;" />
</strong>我们注意到，不可能把高度为 4 的箱子放入仓库中，因为它不能通过高度为 3 的房间。
而且，对于最后两个房间 2 号和 3 号来说，只有高度为 1 的箱子可以放进去。
我们最多可以放进 3 个箱子，如上图所示。黄色的箱子也可以放入 2 号房间。
交换橙色和绿色箱子的位置，或是将这两个箱子与红色箱子交换位置，也是可以的。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>boxes = [1,2,3], warehouse = [1,2,3,4]
<strong>输出：</strong>1
<strong>解释：</strong>由于第一个房间的高度为 1，我们只能放进高度为 1 的箱子。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == warehouse.length</code></li>
	<li><code>1 &lt;= boxes.length, warehouse.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= boxes[i], warehouse[i] &lt;= 10^9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 排序 + 双指针

我们可以先对仓库的房间进行预处理，得到一个数组 $left$，其中 $left[i]$ 表示下标 $i$ 可以放入的最大箱子高度。

然后对箱子的高度进行排序，从小到大依次放入仓库中。我们使用两个指针 $i$ 和 $j$ 分别指向箱子的第一个位置以及仓库的最后一个位置，如果 $left[j] \lt boxes[i]$，说明当前仓库无法放入箱子 $i$，我们将指针 $j$ 循环向左移动，直到 $left[j] \ge boxes[i]$ 或者 $j \lt 0$。如果此时 $j \lt 0$，说明仓库已经没有空间可以放入箱子 $i$，我们可以直接退出循环。否则说明仓库可以放入箱子 $i$，我们将指针 $i$ 循环向右移动，指针 $j$ 循环向左移动。继续进行上述操作，直到指针 $i$ 超出箱子的范围。

最后我们返回指针 $i$ 的值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为仓库的房间数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxBoxesInWarehouse(self, boxes: List[int], warehouse: List[int]) -> int:
        n = len(warehouse)
        left = [warehouse[0]] * n
        for i in range(1, n):
            left[i] = min(left[i - 1], warehouse[i])
        boxes.sort()
        i, j = 0, n - 1
        while i < len(boxes):
            while j >= 0 and left[j] < boxes[i]:
                j -= 1
            if j < 0:
                break
            i, j = i + 1, j - 1
        return i
```

#### Java

```java
class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int n = warehouse.length;
        int[] left = new int[n];
        left[0] = warehouse[0];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.min(left[i - 1], warehouse[i]);
        }
        Arrays.sort(boxes);
        int i = 0, j = n - 1;
        while (i < boxes.length) {
            while (j >= 0 && left[j] < boxes[i]) {
                --j;
            }
            if (j < 0) {
                break;
            }
            ++i;
            --j;
        }
        return i;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxBoxesInWarehouse(vector<int>& boxes, vector<int>& warehouse) {
        int n = warehouse.size();
        int left[n];
        left[0] = warehouse[0];
        for (int i = 1; i < n; ++i) {
            left[i] = min(left[i - 1], warehouse[i]);
        }
        sort(boxes.begin(), boxes.end());
        int i = 0, j = n - 1;
        while (i < boxes.size()) {
            while (j >= 0 && left[j] < boxes[i]) {
                --j;
            }
            if (j < 0) {
                break;
            }
            ++i;
            --j;
        }
        return i;
    }
};
```

#### Go

```go
func maxBoxesInWarehouse(boxes []int, warehouse []int) int {
	n := len(warehouse)
	left := make([]int, n)
	left[0] = warehouse[0]
	for i := 1; i < n; i++ {
		left[i] = min(left[i-1], warehouse[i])
	}
	sort.Ints(boxes)
	i, j := 0, n-1
	for i < len(boxes) {
		for j >= 0 && left[j] < boxes[i] {
			j--
		}
		if j < 0 {
			break
		}
		i, j = i+1, j-1
	}
	return i
}
```

#### TypeScript

```ts
function maxBoxesInWarehouse(boxes: number[], warehouse: number[]): number {
    const n = warehouse.length;
    const left: number[] = new Array(n);
    left[0] = warehouse[0];
    for (let i = 1; i < n; ++i) {
        left[i] = Math.min(left[i - 1], warehouse[i]);
    }
    boxes.sort((a, b) => a - b);
    let i = 0;
    let j = n - 1;
    while (i < boxes.length) {
        while (j >= 0 && left[j] < boxes[i]) {
            --j;
        }
        if (j < 0) {
            break;
        }
        ++i;
        --j;
    }
    return i;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
