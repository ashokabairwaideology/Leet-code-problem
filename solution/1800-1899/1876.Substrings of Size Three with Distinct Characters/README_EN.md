---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1876.Substrings%20of%20Size%20Three%20with%20Distinct%20Characters/README_EN.md
rating: 1248
source: Biweekly Contest 53 Q1
tags:
    - Hash Table
    - String
    - Counting
    - Sliding Window
---

<!-- problem:start -->

# [1876. Substrings of Size Three with Distinct Characters](https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters)

[中文文档](/solution/1800-1899/1876.Substrings%20of%20Size%20Three%20with%20Distinct%20Characters/README.md)

## Description

<!-- description:start -->

<p>A string is <strong>good</strong> if there are no repeated characters.</p>

<p>Given a string <code>s</code>​​​​​, return <em>the number of <strong>good substrings</strong> of length <strong>three </strong>in </em><code>s</code>​​​​​​.</p>

<p>Note that if there are multiple occurrences of the same substring, every occurrence should be counted.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;xyzzaz&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> There are 4 substrings of size 3: &quot;xyz&quot;, &quot;yzz&quot;, &quot;zza&quot;, and &quot;zaz&quot;. 
The only good substring of length 3 is &quot;xyz&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aababcabc&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 7 substrings of size 3: &quot;aab&quot;, &quot;aba&quot;, &quot;bab&quot;, &quot;abc&quot;, &quot;bca&quot;, &quot;cab&quot;, and &quot;abc&quot;.
The good substrings are &quot;abc&quot;, &quot;bca&quot;, &quot;cab&quot;, and &quot;abc&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>​​​​​​ consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodSubstrings(self, s: str) -> int:
        count, n = 0, len(s)
        for i in range(n - 2):
            count += s[i] != s[i + 1] and s[i] != s[i + 2] and s[i + 1] != s[i + 2]
        return count
```

#### Java

```java
class Solution {
    public int countGoodSubstrings(String s) {
        int count = 0, n = s.length();
        for (int i = 0; i < n - 2; ++i) {
            char a = s.charAt(i), b = s.charAt(i + 1), c = s.charAt(i + 2);
            if (a != b && a != c && b != c) {
                ++count;
            }
        }
        return count;
    }
}
```

#### TypeScript

```ts
function countGoodSubstrings(s: string): number {
    const n: number = s.length;
    let count: number = 0;
    for (let i: number = 0; i < n - 2; ++i) {
        let a: string = s.charAt(i),
            b: string = s.charAt(i + 1),
            c: string = s.charAt(i + 2);
        if (a != b && a != c && b != c) {
            ++count;
        }
    }
    return count;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countGoodSubstrings($s) {
        $cnt = 0;
        for ($i = 0; $i < strlen($s) - 2; $i++) {
            if ($s[$i] != $s[$i + 1] && $s[$i] != $s[$i + 2] && $s[$i + 1] != $s[$i + 2]) {
                $cnt++;
            }
        }
        return $cnt++;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
