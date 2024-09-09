---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.21.Volume%20of%20Histogram/README_EN.md
---

<!-- problem:start -->

# [17.21. Volume of Histogram](https://leetcode.cn/problems/volume-of-histogram-lcci)

[中文文档](/lcci/17.21.Volume%20of%20Histogram/README.md)

## Description

<!-- description:start -->

<p>Imagine a histogram (bar graph). Design an algorithm to compute the volume of water it could hold if someone poured water across the top. You can assume that each histogram bar has width 1.</p>

![](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcci/17.21.Volume%20of%20Histogram/images/rainwatertrap.png)

<p><small>The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of water (blue section) are being trapped. Thanks <strong>Marcos</strong> for contributing this image!</small></p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> [0,1,0,2,1,0,1,3,2,1,2,1]

<strong>Output:</strong> 6</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        if n < 3:
            return 0
        left = [height[0]] * n
        right = [height[-1]] * n
        for i in range(1, n):
            left[i] = max(left[i - 1], height[i])
            right[n - i - 1] = max(right[n - i], height[n - i - 1])
        return sum(min(l, r) - h for l, r, h in zip(left, right, height))
```

#### Java

```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.max(left[i - 1], height[i]);
            right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        if (n < 3) {
            return 0;
        }
        int left[n], right[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = max(left[i - 1], height[i]);
            right[n - i - 1] = max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += min(left[i], right[i]) - height[i];
        }
        return ans;
    }
};
```

#### Go

```go
func trap(height []int) (ans int) {
	n := len(height)
	if n < 3 {
		return 0
	}
	left := make([]int, n)
	right := make([]int, n)
	left[0], right[n-1] = height[0], height[n-1]
	for i := 1; i < n; i++ {
		left[i] = max(left[i-1], height[i])
		right[n-i-1] = max(right[n-i], height[n-i-1])
	}
	for i, h := range height {
		ans += min(left[i], right[i]) - h
	}
	return
}
```

#### TypeScript

```ts
function trap(height: number[]): number {
    const n = height.length;
    if (n < 3) {
        return 0;
    }
    const left: number[] = new Array(n).fill(height[0]);
    const right: number[] = new Array(n).fill(height[n - 1]);
    for (let i = 1; i < n; ++i) {
        left[i] = Math.max(left[i - 1], height[i]);
        right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.min(left[i], right[i]) - height[i];
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int Trap(int[] height) {
        int n = height.Length;
        if (n < 3) {
            return 0;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.Max(left[i - 1], height[i]);
            right[n - i - 1] = Math.Max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
```

#### Swift

```swift
class Solution {
    func trap(_ height: [Int]) -> Int {
        let n = height.count
        if n < 3 {
            return 0
        }

        var left = [Int](repeating: 0, count: n)
        var right = [Int](repeating: 0, count: n)

        left[0] = height[0]
        right[n - 1] = height[n - 1]

        for i in 1..<n {
            left[i] = max(left[i - 1], height[i])
        }

        for i in stride(from: n - 2, through: 0, by: -1) {
            right[i] = max(right[i + 1], height[i])
        }

        var ans = 0
        for i in 0..<n {
            ans += min(left[i], right[i]) - height[i]
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
