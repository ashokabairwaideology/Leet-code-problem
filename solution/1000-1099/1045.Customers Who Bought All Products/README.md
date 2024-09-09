---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1045.Customers%20Who%20Bought%20All%20Products/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1045. 买下所有产品的客户](https://leetcode.cn/problems/customers-who-bought-all-products)

[English Version](/solution/1000-1099/1045.Customers%20Who%20Bought%20All%20Products/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>Customer</code>&nbsp;表：</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| customer_id | int     |
| product_key | int     |
+-------------+---------+
该表可能包含重复的行。
customer_id 不为 NULL。
product_key 是 Product 表的外键(reference 列)。
</pre>

<p><code>Product</code>&nbsp;表：</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_key | int     |
+-------------+---------+
product_key 是这张表的主键（具有唯一值的列）。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，报告&nbsp;<code>Customer</code> 表中购买了 <code>Product</code> 表中所有产品的客户的 id。</p>

<p>返回结果表 <strong>无顺序要求</strong> 。</p>

<p>返回结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Customer 表：
+-------------+-------------+
| customer_id | product_key |
+-------------+-------------+
| 1           | 5           |
| 2           | 6           |
| 3           | 5           |
| 3           | 6           |
| 1           | 6           |
+-------------+-------------+
Product 表：
+-------------+
| product_key |
+-------------+
| 5           |
| 6           |
+-------------+
<strong>输出：</strong>
+-------------+
| customer_id |
+-------------+
| 1           |
| 3           |
+-------------+
<strong>解释：</strong>
购买了所有产品（5 和 6）的客户的 id 是 1 和 3 。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计 + 子查询

我们将 `Customer` 表按照 `customer_id` 进行分组，然后使用 `HAVING` 子句筛选出购买了所有产品的客户。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT customer_id
FROM Customer
GROUP BY 1
HAVING COUNT(DISTINCT product_key) = (SELECT COUNT(1) FROM Product);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
