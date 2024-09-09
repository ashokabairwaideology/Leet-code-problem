---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1287.Element%20Appearing%20More%20Than%2025%25%20In%20Sorted%20Array/README.md
rating: 1179
source: 第 15 场双周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [1287. 有序数组中出现次数超过25%的元素](https://leetcode.cn/problems/element-appearing-more-than-25-in-sorted-array)

[English Version](/solution/1200-1299/1287.Element%20Appearing%20More%20Than%2025%25%20In%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个非递减的&nbsp;<strong>有序&nbsp;</strong>整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。</p>

<p>请你找到并返回这个整数</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,2,6,6,6,6,7,10]
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10^4</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10^5</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSpecialInteger(self, arr: List[int]) -> int:
        n = len(arr)
        for i, val in enumerate(arr):
            if val == arr[i + (n >> 2)]:
                return val
        return 0
```

#### Java

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            if (arr[i] == arr[i + (n >> 2)]) {
                return arr[i];
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findSpecialInteger(vector<int>& arr) {
        int n = arr.size();
        for (int i = 0; i < n; ++i)
            if (arr[i] == arr[i + (n >> 2)]) return arr[i];
        return 0;
    }
};
```

#### Go

```go
func findSpecialInteger(arr []int) int {
	n := len(arr)
	for i, val := range arr {
		if val == arr[i+(n>>2)] {
			return val
		}
	}
	return 0
}
```

#### JavaScript

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var findSpecialInteger = function (arr) {
    const n = arr.length;
    for (let i = 0; i < n; ++i) {
        if (arr[i] == arr[i + (n >> 2)]) {
            return arr[i];
        }
    }
    return 0;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function findSpecialInteger($arr) {
        $len = count($arr);
        for ($i = 0; $i < $len; $i++) {
            if ($arr[$i] == $arr[$i + ($len >> 2)]) {
                return $arr[$i];
            }
        }
        return -1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
