---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README.md
tags:
    - 贪心
    - 数组
    - 字符串
---

<!-- problem:start -->

# [955. 删列造序 II](https://leetcode.cn/problems/delete-columns-to-make-sorted-ii)

[English Version](/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定由 <code>n</code> 个字符串组成的数组 <code>strs</code>，其中每个字符串长度相等。</p>

<p>选取一个删除索引序列，对于 <code>strs</code> 中的每个字符串，删除对应每个索引处的字符。</p>

<p>比如，有 <code>strs = ["abcdef", "uvwxyz"]</code>，删除索引序列 <code>{0, 2, 3}</code>，删除后 <code>strs</code> 为<code>["bef", "vyz"]</code>。</p>

<p>假设，我们选择了一组删除索引 <code>answer</code>，那么在执行删除操作之后，最终得到的数组的元素是按 <strong>字典序</strong>（<code>strs[0] <= strs[1] <= strs[2] ... <= strs[n - 1]</code>）排列的，然后请你返回 <code>answer.length</code> 的最小可能值。</p>

<p> </p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["ca","bb","ac"]
<strong>输出：</strong>1
<strong>解释： </strong>
删除第一列后，strs = ["a", "b", "c"]。
现在 strs 中元素是按字典排列的 (即，strs[0] <= strs[1] <= strs[2])。
我们至少需要进行 1 次删除，因为最初 strs 不是按字典序排列的，所以答案是 1。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["xc","yb","za"]
<strong>输出：</strong>0
<strong>解释：</strong>
strs 的列已经是按字典序排列了，所以我们不需要删除任何东西。
注意 strs 的行不需要按字典序排列。
也就是说，strs[0][0] <= strs[0][1] <= ... 不一定成立。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>strs = ["zyx","wvu","tsr"]
<strong>输出：</strong>3
<strong>解释：</strong>
我们必须删掉每一列。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>1 <= strs[i].length <= 100</code></li>
	<li><code>strs[i]</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int minDeletionSize(String[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        int len = A.length, wordLen = A[0].length(), res = 0;
        boolean[] cut = new boolean[len];
    search:
        for (int j = 0; j < wordLen; j++) {
            // 判断第 j 列是否应当保留
            for (int i = 0; i < len - 1; i++) {
                if (!cut[i] && A[i].charAt(j) > A[i + 1].charAt(j)) {
                    res += 1;
                    continue search;
                }
            }
            // 更新 cut 的信息
            for (int i = 0; i < len - 1; i++) {
                if (A[i].charAt(j) < A[i + 1].charAt(j)) {
                    cut[i] = true;
                }
            }
        }
        return res;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
