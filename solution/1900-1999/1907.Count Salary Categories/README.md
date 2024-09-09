---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1907.Count%20Salary%20Categories/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1907. 按分类统计薪水](https://leetcode.cn/problems/count-salary-categories)

[English Version](/solution/1900-1999/1907.Count%20Salary%20Categories/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Accounts</code></p>

<pre>
+-------------+------+
| 列名        | 类型  |
+-------------+------+
| account_id  | int  |
| income      | int  |
+-------------+------+
在 SQL 中，account_id&nbsp;是这个表的主键。
每一行都包含一个银行帐户的月收入的信息。
</pre>

<p>&nbsp;</p>

<p>查询每个工资类别的银行账户数量。&nbsp;工资类别如下：</p>

<ul>
	<li><code>"Low Salary"</code>：所有工资 <strong>严格低于</strong> <code>20000</code> 美元。</li>
	<li><code>"Average Salary"</code>： <strong>包含</strong> 范围内的所有工资&nbsp;<code>[$20000,&nbsp;$50000]</code> 。</li>
	<li>
	<p><code>"High Salary"</code>：所有工资 <strong>严格大于</strong> <code>50000</code> 美元。</p>
	</li>
</ul>

<p>结果表 <strong>必须</strong> 包含所有三个类别。&nbsp;如果某个类别中没有帐户，则报告&nbsp;<code>0</code> 。</p>

<p>按 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下示例。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
Accounts 表:
+------------+--------+
| account_id | income |
+------------+--------+
| 3          | 108939 |
| 2          | 12747  |
| 8          | 87709  |
| 6          | 91796  |
+------------+--------+
<strong>输出：</strong>
+----------------+----------------+
| category       | accounts_count |
+----------------+----------------+
| Low Salary     | 1              |
| Average Salary | 0              |
| High Salary    | 3              |
+----------------+----------------+
<strong>解释：</strong>
低薪: 有一个账户 2.
中等薪水: 没有.
高薪: 有三个账户，他们是 3, 6和 8.</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：构建临时表 + 分组统计 + 左连接

我们可以先构建一个临时表，包含所有工资类别，然后再统计每个工资类别的银行账户数量。最后我们使用左连接，将临时表和统计结果表连接起来，这样就可以保证结果表中包含所有工资类别。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT 'Low Salary' AS category
        UNION
        SELECT 'Average Salary'
        UNION
        SELECT 'High Salary'
    ),
    T AS (
        SELECT
            CASE
                WHEN income < 20000 THEN "Low Salary"
                WHEN income > 50000 THEN 'High Salary'
                ELSE 'Average Salary'
            END AS category,
            COUNT(1) AS accounts_count
        FROM Accounts
        GROUP BY 1
    )
SELECT category, IFNULL(accounts_count, 0) AS accounts_count
FROM
    S
    LEFT JOIN T USING (category);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：筛选 + 合并

我们可以分别筛选出每个工资类别的银行账户数量，然后再将结果合并起来。这里我们使用 `UNION` 来合并结果。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT 'Low Salary' AS category, IFNULL(SUM(income < 20000), 0) AS accounts_count FROM Accounts
UNION
SELECT
    'Average Salary' AS category,
    IFNULL(SUM(income BETWEEN 20000 AND 50000), 0) AS accounts_count
FROM Accounts
UNION
SELECT 'High Salary' AS category, IFNULL(SUM(income > 50000), 0) AS accounts_count FROM Accounts;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
