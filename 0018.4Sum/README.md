---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0018.4Sum/README.md
tags:
    - 数组
    - 双指针
    - 排序
---

<!-- problem:start -->

# [18. 四数之和](https://leetcode.cn/problems/4sum)

[English Version](/solution/0000-0099/0018.4Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <code>n</code> 个整数组成的数组&nbsp;<code>nums</code> ，和一个目标值 <code>target</code> 。请你找出并返回满足下述全部条件且<strong>不重复</strong>的四元组&nbsp;<code>[nums[a], nums[b], nums[c], nums[d]]</code>&nbsp;（若两个四元组元素一一对应，则认为两个四元组重复）：</p>

<ul>
	<li><code>0 &lt;= a, b, c, d&nbsp;&lt; n</code></li>
	<li><code>a</code>、<code>b</code>、<code>c</code> 和 <code>d</code> <strong>互不相同</strong></li>
	<li><code>nums[a] + nums[b] + nums[c] + nums[d] == target</code></li>
</ul>

<p>你可以按 <strong>任意顺序</strong> 返回答案 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,-1,0,-2,2], target = 0
<strong>输出：</strong>[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2,2,2], target = 8
<strong>输出：</strong>[[2,2,2,2]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 双指针

我们注意到，题目中要求找到不重复的四元组，那么我们可以先对数组进行排序，这样就可以方便地跳过重复的元素。

接下来，我们枚举四元组的前两个元素 $nums[i]$ 和 $nums[j]$，其中 $i \lt j$，在枚举的过程中，我们跳过重复的 $nums[i]$ 和 $nums[j]$。然后，我们用两个指针 $k$ 和 $l$ 分别指向 $nums[i]$ 和 $nums[j]$ 后面的两端，令 $x = nums[i] + nums[j] + nums[k] + nums[l]$，我们将 $x$ 与 $target$ 比较，进行如下操作：

-   如果 $x \lt target$，则更新 $k = k + 1$ 以得到更大的 $x$；
-   如果 $x \gt target$，则更新 $l = l - 1$ 以得到更小的 $x$；
-   否则，说明找到了一个四元组 $(nums[i], nums[j], nums[k], nums[l])$，将其加入答案，然后我们更新指针 $k$ 和 $l$，并跳过所有重复的元素，防止答案中包含重复的四元组，继续寻找下一个四元组。

时间复杂度为 $O(n^3)$，空间复杂度为 $O(\log n)$，其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        n = len(nums)
        ans = []
        if n < 4:
            return ans
        nums.sort()
        for i in range(n - 3):
            if i and nums[i] == nums[i - 1]:
                continue
            for j in range(i + 1, n - 2):
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                k, l = j + 1, n - 1
                while k < l:
                    x = nums[i] + nums[j] + nums[k] + nums[l]
                    if x < target:
                        k += 1
                    elif x > target:
                        l -= 1
                    else:
                        ans.append([nums[i], nums[j], nums[k], nums[l]])
                        k, l = k + 1, l - 1
                        while k < l and nums[k] == nums[k - 1]:
                            k += 1
                        while k < l and nums[l] == nums[l + 1]:
                            l -= 1
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (n < 4) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = j + 1, l = n - 1;
                while (k < l) {
                    long x = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    if (x < target) {
                        ++k;
                    } else if (x > target) {
                        --l;
                    } else {
                        ans.add(List.of(nums[i], nums[j], nums[k++], nums[l--]));
                        while (k < l && nums[k] == nums[k - 1]) {
                            ++k;
                        }
                        while (k < l && nums[l] == nums[l + 1]) {
                            --l;
                        }
                    }
                }
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
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        int n = nums.size();
        vector<vector<int>> ans;
        if (n < 4) {
            return ans;
        }
        sort(nums.begin(), nums.end());
        for (int i = 0; i < n - 3; ++i) {
            if (i && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = j + 1, l = n - 1;
                while (k < l) {
                    long long x = (long long) nums[i] + nums[j] + nums[k] + nums[l];
                    if (x < target) {
                        ++k;
                    } else if (x > target) {
                        --l;
                    } else {
                        ans.push_back({nums[i], nums[j], nums[k++], nums[l--]});
                        while (k < l && nums[k] == nums[k - 1]) {
                            ++k;
                        }
                        while (k < l && nums[l] == nums[l + 1]) {
                            --l;
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func fourSum(nums []int, target int) (ans [][]int) {
	n := len(nums)
	if n < 4 {
		return
	}
	sort.Ints(nums)
	for i := 0; i < n-3; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		for j := i + 1; j < n-2; j++ {
			if j > i+1 && nums[j] == nums[j-1] {
				continue
			}
			k, l := j+1, n-1
			for k < l {
				x := nums[i] + nums[j] + nums[k] + nums[l]
				if x < target {
					k++
				} else if x > target {
					l--
				} else {
					ans = append(ans, []int{nums[i], nums[j], nums[k], nums[l]})
					k++
					l--
					for k < l && nums[k] == nums[k-1] {
						k++
					}
					for k < l && nums[l] == nums[l+1] {
						l--
					}
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function fourSum(nums: number[], target: number): number[][] {
    const n = nums.length;
    const ans: number[][] = [];
    if (n < 4) {
        return ans;
    }
    nums.sort((a, b) => a - b);
    for (let i = 0; i < n - 3; ++i) {
        if (i > 0 && nums[i] === nums[i - 1]) {
            continue;
        }
        for (let j = i + 1; j < n - 2; ++j) {
            if (j > i + 1 && nums[j] === nums[j - 1]) {
                continue;
            }
            let [k, l] = [j + 1, n - 1];
            while (k < l) {
                const x = nums[i] + nums[j] + nums[k] + nums[l];
                if (x < target) {
                    ++k;
                } else if (x > target) {
                    --l;
                } else {
                    ans.push([nums[i], nums[j], nums[k++], nums[l--]]);
                    while (k < l && nums[k] === nums[k - 1]) {
                        ++k;
                    }
                    while (k < l && nums[l] === nums[l + 1]) {
                        --l;
                    }
                }
            }
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[][]}
 */
var fourSum = function (nums, target) {
    const n = nums.length;
    const ans = [];
    if (n < 4) {
        return ans;
    }
    nums.sort((a, b) => a - b);
    for (let i = 0; i < n - 3; ++i) {
        if (i > 0 && nums[i] === nums[i - 1]) {
            continue;
        }
        for (let j = i + 1; j < n - 2; ++j) {
            if (j > i + 1 && nums[j] === nums[j - 1]) {
                continue;
            }
            let [k, l] = [j + 1, n - 1];
            while (k < l) {
                const x = nums[i] + nums[j] + nums[k] + nums[l];
                if (x < target) {
                    ++k;
                } else if (x > target) {
                    --l;
                } else {
                    ans.push([nums[i], nums[j], nums[k++], nums[l--]]);
                    while (k < l && nums[k] === nums[k - 1]) {
                        ++k;
                    }
                    while (k < l && nums[l] === nums[l + 1]) {
                        --l;
                    }
                }
            }
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<IList<int>> FourSum(int[] nums, int target) {
        int n = nums.Length;
        var ans = new List<IList<int>>();
        if (n < 4) {
            return ans;
        }
        Array.Sort(nums);
        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = j + 1, l = n - 1;
                while (k < l) {
                    long x = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    if (x < target) {
                        ++k;
                    } else if (x > target) {
                        --l;
                    } else {
                        ans.Add(new List<int> {nums[i], nums[j], nums[k++], nums[l--]});
                        while (k < l && nums[k] == nums[k - 1]) {
                            ++k;
                        }
                        while (k < l && nums[l] == nums[l + 1]) {
                            --l;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param int[] $nums
     * @param int $target
     * @return int[][]
     */

    function fourSum($nums, $target) {
        $result = [];
        $n = count($nums);

        sort($nums);

        for ($i = 0; $i < $n - 3; $i++) {
            if ($i > 0 && $nums[$i] === $nums[$i - 1]) {
                continue;
            }

            for ($j = $i + 1; $j < $n - 2; $j++) {
                if ($j > $i + 1 && $nums[$j] === $nums[$j - 1]) {
                    continue;
                }

                $left = $j + 1;
                $right = $n - 1;

                while ($left < $right) {
                    $sum = $nums[$i] + $nums[$j] + $nums[$left] + $nums[$right];

                    if ($sum === $target) {
                        $result[] = [$nums[$i], $nums[$j], $nums[$left], $nums[$right]];

                        while ($left < $right && $nums[$left] === $nums[$left + 1]) {
                            $left++;
                        }

                        while ($left < $right && $nums[$right] === $nums[$right - 1]) {
                            $right--;
                        }

                        $left++;
                        $right--;
                    } elseif ($sum < $target) {
                        $left++;
                    } else {
                        $right--;
                    }
                }
            }
        }
        return $result;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
