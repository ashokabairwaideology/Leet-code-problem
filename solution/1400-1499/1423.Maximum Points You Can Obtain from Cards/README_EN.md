---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1423.Maximum%20Points%20You%20Can%20Obtain%20from%20Cards/README_EN.md
rating: 1573
source: Weekly Contest 186 Q2
tags:
    - Array
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [1423. Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards)

[中文文档](/solution/1400-1499/1423.Maximum%20Points%20You%20Can%20Obtain%20from%20Cards/README.md)

## Description

<!-- description:start -->

<p>There are several cards <strong>arranged in a row</strong>, and each card has an associated number of points. The points are given in the integer array <code>cardPoints</code>.</p>

<p>In one step, you can take one card from the beginning or from the end of the row. You have to take exactly <code>k</code> cards.</p>

<p>Your score is the sum of the points of the cards you have taken.</p>

<p>Given the integer array <code>cardPoints</code> and the integer <code>k</code>, return the <em>maximum score</em> you can obtain.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cardPoints = [1,2,3,4,5,6,1], k = 3
<strong>Output:</strong> 12
<strong>Explanation:</strong> After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cardPoints = [2,2,2], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> Regardless of which two cards you take, your score will always be 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> cardPoints = [9,7,7,9,7,7,9], k = 7
<strong>Output:</strong> 55
<strong>Explanation:</strong> You have to take all the cards. Your score is the sum of points of all cards.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cardPoints.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= cardPoints[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= cardPoints.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

We can use a sliding window of length $k$ to simulate this process.

Initially, we place the window at the end of the array, i.e., the $k$ positions from index $n-k$ to index $n-1$. The sum of the points of the cards in the window is denoted as $s$, and the initial value of the answer $ans$ is also $s$.

Next, we consider the situation where we take $1, 2, ..., k$ cards from the beginning of the array in turn. Suppose the card taken is $cardPoints[i]$. Then we add it to $s$. Due to the length limit of the window is $k$, we need to subtract $cardPoints[n-k+i]$ from $s$. In this way, we can calculate the sum of the points of the $k$ cards taken and update the answer $ans$.

The time complexity is $O(k)$, where $k$ is the integer given in the problem. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        ans = s = sum(cardPoints[-k:])
        for i, x in enumerate(cardPoints[:k]):
            s += x - cardPoints[-k + i]
            ans = max(ans, s)
        return ans
```

#### Java

```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int s = 0, n = cardPoints.length;
        for (int i = n - k; i < n; ++i) {
            s += cardPoints[i];
        }
        int ans = s;
        for (int i = 0; i < k; ++i) {
            s += cardPoints[i] - cardPoints[n - k + i];
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& cardPoints, int k) {
        int n = cardPoints.size();
        int s = accumulate(cardPoints.end() - k, cardPoints.end(), 0);
        int ans = s;
        for (int i = 0; i < k; ++i) {
            s += cardPoints[i] - cardPoints[n - k + i];
            ans = max(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func maxScore(cardPoints []int, k int) int {
	n := len(cardPoints)
	s := 0
	for _, x := range cardPoints[n-k:] {
		s += x
	}
	ans := s
	for i := 0; i < k; i++ {
		s += cardPoints[i] - cardPoints[n-k+i]
		ans = max(ans, s)
	}
	return ans
}
```

#### TypeScript

```ts
function maxScore(cardPoints: number[], k: number): number {
    const n = cardPoints.length;
    let s = cardPoints.slice(-k).reduce((a, b) => a + b);
    let ans = s;
    for (let i = 0; i < k; ++i) {
        s += cardPoints[i] - cardPoints[n - k + i];
        ans = Math.max(ans, s);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_score(card_points: Vec<i32>, k: i32) -> i32 {
        let n = card_points.len();
        let k = k as usize;
        let mut s: i32 = card_points[n - k..].iter().sum();
        let mut ans: i32 = s;
        for i in 0..k {
            s += card_points[i] - card_points[n - k + i];
            ans = ans.max(s);
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} cardPoints
 * @param {number} k
 * @return {number}
 */
var maxScore = function (cardPoints, k) {
    const n = cardPoints.length;
    let s = cardPoints.slice(-k).reduce((a, b) => a + b);
    let ans = s;
    for (let i = 0; i < k; ++i) {
        s += cardPoints[i] - cardPoints[n - k + i];
        ans = Math.max(ans, s);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxScore(int[] cardPoints, int k) {
        int n = cardPoints.Length;
        int s = cardPoints[^k..].Sum();
        int ans = s;
        for (int i = 0; i < k; ++i) {
            s += cardPoints[i] - cardPoints[n - k + i];
            ans = Math.Max(ans, s);
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $cardPoints
     * @param Integer $k
     * @return Integer
     */
    function maxScore($cardPoints, $k) {
        $n = count($cardPoints);
        $s = array_sum(array_slice($cardPoints, -$k));
        $ans = $s;
        for ($i = 0; $i < $k; ++$i) {
            $s += $cardPoints[$i] - $cardPoints[$n - $k + $i];
            $ans = max($ans, $s);
        }
        return $ans;
    }
}
```

#### Scala

```scala
object Solution {
    def maxScore(cardPoints: Array[Int], k: Int): Int = {
        val n = cardPoints.length
        var s = cardPoints.takeRight(k).sum
        var ans = s
        for (i <- 0 until k) {
            s += cardPoints(i) - cardPoints(n - k + i)
            ans = ans.max(s)
        }
        ans
    }
}
```

#### Swift

```swift
class Solution {
    func maxScore(_ cardPoints: [Int], _ k: Int) -> Int {
        let n = cardPoints.count
        var s = cardPoints.suffix(k).reduce(0, +)
        var ans = s
        for i in 0..<k {
            s += cardPoints[i] - cardPoints[n - k + i]
            ans = max(ans, s)
        }
        return ans
    }
}
```

#### Ruby

```rb
# @param {Integer[]} card_points
# @param {Integer} k
# @return {Integer}
def max_score(card_points, k)
  n = card_points.length
  s = card_points[-k..].sum
  ans = s
  k.times do |i|
    s += card_points[i] - card_points[n - k + i]
    ans = [ans, s].max
  end
  ans
end
```

#### Kotlin

```kotlin
class Solution {
    fun maxScore(cardPoints: IntArray, k: Int): Int {
        val n = cardPoints.size
        var s = cardPoints.sliceArray(n - k until n).sum()
        var ans = s
        for (i in 0 until k) {
            s += cardPoints[i] - cardPoints[n - k + i]
            ans = maxOf(ans, s)
        }
        return ans
    }
}
```

#### Dart

```dart
class Solution {
  int maxScore(List<int> cardPoints, int k) {
    int n = cardPoints.length;
    int s = cardPoints.sublist(n - k).reduce((a, b) => a + b);
    int ans = s;
    for (int i = 0; i < k; ++i) {
      s += cardPoints[i] - cardPoints[n - k + i];
      ans = s > ans ? s : ans;
    }
    return ans;
  }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
