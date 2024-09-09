---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2978.Symmetric%20Coordinates/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2978. 对称坐标 🔒](https://leetcode.cn/problems/symmetric-coordinates)

[English Version](/solution/2900-2999/2978.Symmetric%20Coordinates/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<font face="monospace"><code>Coordinates</code></font></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| X           | int  |
| Y           | int  |
+-------------+------+
每一行包括 X 和 Y，都是整数。表格可能包含重复值。
</pre>

<p>如果两个坐标 <code>(X1, Y1)</code>&nbsp;和&nbsp;<code>(X2, Y2)</code> 满足条件 <code>X1 == Y2</code>&nbsp;和&nbsp;<code>X2 == Y1</code>，则它们被称为 <strong>对称</strong> 坐标。</p>

<p>编写一个解决方案，找出在所有这些对称坐标中，满足条件 <code>X1 &lt;= Y1</code>&nbsp;的唯一坐标。</p>

<p>按照<em> </em><code>X</code>&nbsp;和<em>&nbsp;</em><code>Y</code> 分别&nbsp;<strong>升序</strong> 排列结果表。</p>

<p>结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Coordinates table:
+----+----+
| X  | Y  |
+----+----+
| 20 | 20 |
| 20 | 20 |
| 20 | 21 |
| 23 | 22 |
| 22 | 23 |
| 21 | 20 |
+----+----+
<b>输出：</b>
+----+----+
| x  | y  |
+----+----+
| 20 | 20 |
| 20 | 21 |
| 22 | 23 |
+----+----+
<b>解释：</b>
- (20, 20) 和 (20, 20) 是对称坐标，因为 X1 == Y2 和 X2 == Y1。所以 (20, 20) 被显示为独特的坐标。
- (20, 21) 和 (21, 20) 是对称坐标，因为 X1 == Y2 和 X2 == Y1。然而，只有 (20, 21) 会被显示，因为 X1 &lt;= Y1。
- (23, 22) 和 (22, 23) 是对称坐标，因为 X1 == Y2 和 X2 == Y1。然而，只有 (22, 23) 会被显示，因为 X1 &lt;= Y1。
输出表按照 X 和 Y 升序排列。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数 + 自连接

我们可以使用窗口函数 `ROW_NUMBER()` 来为每一行添加一个自增的序号，然后再自连接两张表，连接条件为 `p1.x = p2.y AND p1.y = p2.x AND p1.x <= p1.y AND p1.id != p2.id`，最后再排序去重即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT
            ROW_NUMBER() OVER () AS id,
            x,
            y
        FROM Coordinates
    )
SELECT DISTINCT
    p1.x,
    p1.y
FROM
    P AS p1
    JOIN P AS p2 ON p1.x = p2.y AND p1.y = p2.x AND p1.x <= p1.y AND p1.id != p2.id
ORDER BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
