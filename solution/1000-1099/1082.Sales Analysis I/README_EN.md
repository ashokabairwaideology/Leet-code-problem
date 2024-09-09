---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1082.Sales%20Analysis%20I/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1082. Sales Analysis I 🔒](https://leetcode.com/problems/sales-analysis-i)

[中文文档](/solution/1000-1099/1082.Sales%20Analysis%20I/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Product</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |
+--------------+---------+
product_id is the primary key (column with unique values) of this table.
Each row of this table indicates the name and the price of each product.
</pre>

<p>Table: <code>Sales</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| seller_id   | int     |
| product_id  | int     |
| buyer_id    | int     |
| sale_date   | date    |
| quantity    | int     |
| price       | int     |
+-------------+---------+
This table can have repeated rows.
product_id is a foreign key (reference column) to the Product table.
Each row of this table contains some information about one sale.
</pre>

<p>&nbsp;</p>

<p>Write a solution that reports the best <strong>seller</strong> by total sales price, If there is a tie, report them all.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Product table:
+------------+--------------+------------+
| product_id | product_name | unit_price |
+------------+--------------+------------+
| 1          | S8           | 1000       |
| 2          | G4           | 800        |
| 3          | iPhone       | 1400       |
+------------+--------------+------------+
Sales table:
+-----------+------------+----------+------------+----------+-------+
| seller_id | product_id | buyer_id | sale_date  | quantity | price |
+-----------+------------+----------+------------+----------+-------+
| 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
| 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
| 2         | 2          | 3        | 2019-06-02 | 1        | 800   |
| 3         | 3          | 4        | 2019-05-13 | 2        | 2800  |
+-----------+------------+----------+------------+----------+-------+
<strong>Output:</strong> 
+-------------+
| seller_id   |
+-------------+
| 1           |
| 3           |
+-------------+
<strong>Explanation:</strong> Both sellers with id 1 and 3 sold products with the most total price of 2800.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT seller_id
FROM Sales
GROUP BY seller_id
HAVING
    SUM(price) >= ALL(
        SELECT SUM(price)
        FROM Sales
        GROUP BY seller_id
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            seller_id,
            SUM(price) AS tot,
            RANK() OVER (ORDER BY SUM(price) DESC) AS rk
        FROM Sales
        GROUP BY seller_id
    )
SELECT seller_id
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
