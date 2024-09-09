---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1581.Customer%20Who%20Visited%20but%20Did%20Not%20Make%20Any%20Transactions/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1581. 进店却未进行过交易的顾客](https://leetcode.cn/problems/customer-who-visited-but-did-not-make-any-transactions)

[English Version](/solution/1500-1599/1581.Customer%20Who%20Visited%20but%20Did%20Not%20Make%20Any%20Transactions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Visits</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| visit_id    | int     |
| customer_id | int     |
+-------------+---------+
visit_id 是该表中具有唯一值的列。
该表包含有关光临过购物中心的顾客的信息。
</pre>

<p>&nbsp;</p>

<p>表：<code>Transactions</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| transaction_id | int     |
| visit_id       | int     |
| amount         | int     |
+----------------+---------+
transaction_id 是该表中具有唯一值的列。
此表包含 visit_id 期间进行的交易的信息。
</pre>

<p>&nbsp;</p>

<p>有一些顾客可能光顾了购物中心但没有进行交易。请你编写一个解决方案，来查找这些顾客的 ID ，以及他们只光顾不交易的次数。</p>

<p>返回以 <strong>任何顺序</strong> 排序的结果表。</p>

<p>返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入:</strong>
Visits</code>
+----------+-------------+
| visit_id | customer_id |
+----------+-------------+
| 1        | 23          |
| 2        | 9           |
| 4        | 30          |
| 5        | 54          |
| 6        | 96          |
| 7        | 54          |
| 8        | 54          |
+----------+-------------+
<code>Transactions</code>
+----------------+----------+--------+
| transaction_id | visit_id | amount |
+----------------+----------+--------+
| 2              | 5        | 310    |
| 3              | 5        | 300    |
| 9              | 5        | 200    |
| 12             | 1        | 910    |
| 13             | 2        | 970    |
+----------------+----------+--------+
<b>输出:</b>
+-------------+----------------+
| customer_id | count_no_trans |
+-------------+----------------+
| 54          | 2              |
| 30          | 1              |
| 96          | 1              |
+-------------+----------------+
<b>解释:</b>
ID = 23 的顾客曾经逛过一次购物中心，并在 ID = 12 的访问期间进行了一笔交易。
ID = 9 的顾客曾经逛过一次购物中心，并在 ID = 13 的访问期间进行了一笔交易。
ID = 30 的顾客曾经去过购物中心，并且没有进行任何交易。
ID = 54 的顾客三度造访了购物中心。在 2 次访问中，他们没有进行任何交易，在 1 次访问中，他们进行了 3 次交易。
ID = 96 的顾客曾经去过购物中心，并且没有进行任何交易。
如我们所见，ID 为 30 和 96 的顾客一次没有进行任何交易就去了购物中心。顾客 54 也两次访问了购物中心并且没有进行任何交易。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：子查询 + 分组统计

我们可以使用子查询，先找出所有没有进行交易的 `visit_id`，然后按照 `customer_id` 进行分组，统计每个顾客的没有进行交易的次数。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT customer_id, COUNT(1) AS count_no_trans
FROM Visits
WHERE visit_id NOT IN (SELECT visit_id FROM Transactions)
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：左连接 + 分组统计

我们也可以使用左连接，将 `Visits` 表和 `Transactions` 表按照 `visit_id` 进行连接，然后筛选出 `amount` 为 `NULL` 的记录，按照 `customer_id` 进行分组，统计每个顾客的没有进行交易的次数。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT customer_id, COUNT(1) AS count_no_trans
FROM
    Visits
    LEFT JOIN Transactions USING (visit_id)
WHERE amount IS NULL
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
