---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1154.Day%20of%20the%20Year/README.md
rating: 1199
source: 第 149 场周赛 Q1
tags:
    - 数学
    - 字符串
---

<!-- problem:start -->

# [1154. 一年中的第几天](https://leetcode.cn/problems/day-of-the-year)

[English Version](/solution/1100-1199/1154.Day%20of%20the%20Year/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>date</code> ，按 <code>YYYY-MM-DD</code> 格式表示一个 <a href="https://baike.baidu.com/item/公元/17855" target="_blank">现行公元纪年法</a> 日期。返回该日期是当年的第几天。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>date = "2019-01-09"
<strong>输出：</strong>9
<strong>解释：</strong>给定日期是2019年的第九天。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>date = "2019-02-10"
<strong>输出：</strong>41
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>date.length == 10</code></li>
	<li><code>date[4] == date[7] == '-'</code>，其他的&nbsp;<code>date[i]</code>&nbsp;都是数字</li>
	<li><code>date</code> 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接计算

根据题意，给定的日期是公元纪年法的日期，因此可以直接计算出该日期是当年的第几天。

首先，根据给定的日期计算出年月日，分别为 $y$, $m$, $d$。

然后，根据公元纪年法的闰年规则，计算出当年二月份的天数，闰年的二月份有 $29$ 天，平年的二月份有 $28$ 天。

> 闰年的计算规则是：年份能被 $400$ 整除，或者年份能被 $4$ 整除且不能被 $100$ 整除。

最后，根据给定的日期计算出当年的第几天，即把前面每个月的天数累加起来，再加上当月的天数即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dayOfYear(self, date: str) -> int:
        y, m, d = (int(s) for s in date.split('-'))
        v = 29 if y % 400 == 0 or (y % 4 == 0 and y % 100) else 28
        days = [31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return sum(days[: m - 1]) + d
```

#### Java

```java
class Solution {
    public int dayOfYear(String date) {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(5, 7));
        int d = Integer.parseInt(date.substring(8));
        int v = y % 400 == 0 || (y % 4 == 0 && y % 100 != 0) ? 29 : 28;
        int[] days = {31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = d;
        for (int i = 0; i < m - 1; ++i) {
            ans += days[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int dayOfYear(string date) {
        int y, m, d;
        sscanf(date.c_str(), "%d-%d-%d", &y, &m, &d);
        int v = y % 400 == 0 || (y % 4 == 0 && y % 100) ? 29 : 28;
        int days[] = {31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = d;
        for (int i = 0; i < m - 1; ++i) {
            ans += days[i];
        }
        return ans;
    }
};
```

#### Go

```go
func dayOfYear(date string) (ans int) {
	var y, m, d int
	fmt.Sscanf(date, "%d-%d-%d", &y, &m, &d)
	days := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	if y%400 == 0 || (y%4 == 0 && y%100 != 0) {
		days[1] = 29
	}
	ans += d
	for _, v := range days[:m-1] {
		ans += v
	}
	return
}
```

#### TypeScript

```ts
function dayOfYear(date: string): number {
    const y = +date.slice(0, 4);
    const m = +date.slice(5, 7);
    const d = +date.slice(8);
    const v = y % 400 == 0 || (y % 4 == 0 && y % 100) ? 29 : 28;
    const days = [31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days.slice(0, m - 1).reduce((a, b) => a + b, d);
}
```

#### JavaScript

```js
/**
 * @param {string} date
 * @return {number}
 */
var dayOfYear = function (date) {
    const y = +date.slice(0, 4);
    const m = +date.slice(5, 7);
    const d = +date.slice(8);
    const v = y % 400 == 0 || (y % 4 == 0 && y % 100) ? 29 : 28;
    const days = [31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days.slice(0, m - 1).reduce((a, b) => a + b, d);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
