---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0881.Boats%20to%20Save%20People/README.md
tags:
    - 贪心
    - 数组
    - 双指针
    - 排序
---

<!-- problem:start -->

# [881. 救生艇](https://leetcode.cn/problems/boats-to-save-people)

[English Version](/solution/0800-0899/0881.Boats%20to%20Save%20People/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定数组<meta charset="UTF-8" />&nbsp;<code>people</code>&nbsp;。<code>people[i]</code>表示第 <code>i</code><sup>&nbsp;</sup>个人的体重&nbsp;，<strong>船的数量不限</strong>，每艘船可以承载的最大重量为&nbsp;<code>limit</code>。</p>

<p>每艘船最多可同时载两人，但条件是这些人的重量之和最多为&nbsp;<code>limit</code>。</p>

<p>返回 <em>承载所有人所需的最小船数</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>people = [1,2], limit = 3
<strong>输出：</strong>1
<strong>解释：</strong>1 艘船载 (1, 2)
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>people = [3,2,2,1], limit = 3
<strong>输出：</strong>3
<strong>解释：</strong>3 艘船分别载 (1, 2), (2) 和 (3)
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>people = [3,5,3,4], limit = 5
<strong>输出：</strong>4
<strong>解释：</strong>4 艘船分别载 (3), (3), (4), (5)</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= people.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= people[i] &lt;= limit &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 双指针

排序后，使用双指针分别指向数组首尾，每次取两个指针指向的元素之和与 `limit` 比较，如果小于等于 `limit`，则两个指针同时向中间移动一位，否则只移动右指针。累加答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `people` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numRescueBoats(self, people: List[int], limit: int) -> int:
        people.sort()
        ans = 0
        i, j = 0, len(people) - 1
        while i <= j:
            if people[i] + people[j] <= limit:
                i += 1
            j -= 1
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        for (int i = 0, j = people.length - 1; i <= j; --j) {
            if (people[i] + people[j] <= limit) {
                ++i;
            }
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numRescueBoats(vector<int>& people, int limit) {
        sort(people.begin(), people.end());
        int ans = 0;
        for (int i = 0, j = people.size() - 1; i <= j; --j) {
            if (people[i] + people[j] <= limit) {
                ++i;
            }
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func numRescueBoats(people []int, limit int) int {
	sort.Ints(people)
	ans := 0
	for i, j := 0, len(people)-1; i <= j; j-- {
		if people[i]+people[j] <= limit {
			i++
		}
		ans++
	}
	return ans
}
```

#### TypeScript

```ts
function numRescueBoats(people: number[], limit: number): number {
    people.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0, j = people.length - 1; i <= j; --j) {
        if (people[i] + people[j] <= limit) {
            ++i;
        }
        ++ans;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
