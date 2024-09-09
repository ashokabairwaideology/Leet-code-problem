---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2372.Calculate%20the%20Influence%20of%20Each%20Salesperson/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2372. Calculate the Influence of Each Salesperson 🔒](https://leetcode.com/problems/calculate-the-influence-of-each-salesperson)

[中文文档](/solution/2300-2399/2372.Calculate%20the%20Influence%20of%20Each%20Salesperson/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Salesperson</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| salesperson_id | int     |
| name           | varchar |
+----------------+---------+
salesperson_id contains unique values.
Each row in this table shows the ID of a salesperson.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Customer</code></p>

<pre>
+----------------+------+
| Column Name    | Type |
+----------------+------+
| customer_id    | int  |
| salesperson_id | int  |
+----------------+------+
customer_id contains unique values.
salesperson_id is a foreign key (reference column) from the Salesperson table.
Each row in this table shows the ID of a customer and the ID of the salesperson. 
</pre>

<p>&nbsp;</p>

<p>Table: <code>Sales</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| sale_id     | int  |
| customer_id | int  |
| price       | int  |
+-------------+------+
sale_id contains unique values.
customer_id is a foreign key (reference column) from the Customer table.
Each row in this table shows ID of a customer and the price they paid for the sale with sale_id.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the sum of prices paid by the customers of each salesperson. If a salesperson does not have any customers, the total value should be <code>0</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Salesperson table:
+----------------+-------+
| salesperson_id | name  |
+----------------+-------+
| 1              | Alice |
| 2              | Bob   |
| 3              | Jerry |
+----------------+-------+
Customer table:
+-------------+----------------+
| customer_id | salesperson_id |
+-------------+----------------+
| 1           | 1              |
| 2           | 1              |
| 3           | 2              |
+-------------+----------------+
Sales table:
+---------+-------------+-------+
| sale_id | customer_id | price |
+---------+-------------+-------+
| 1       | 2           | 892   |
| 2       | 1           | 354   |
| 3       | 3           | 988   |
| 4       | 3           | 856   |
+---------+-------------+-------+
<strong>Output:</strong> 
+----------------+-------+-------+
| salesperson_id | name  | total |
+----------------+-------+-------+
| 1              | Alice | 1246  |
| 2              | Bob   | 1844  |
| 3              | Jerry | 0     |
+----------------+-------+-------+
<strong>Explanation:</strong> 
Alice is the salesperson for customers 1 and 2.
  - Customer 1 made one purchase with 354.
  - Customer 2 made one purchase with 892.
The total for Alice is 354 + 892 = 1246.

Bob is the salesperson for customers 3.
  - Customer 1 made one purchase with 988 and 856.
The total for Bob is 988 + 856 = 1844.

Jerry is not the salesperson of any customer.
The total for Jerry is 0.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT sp.salesperson_id, name, IFNULL(SUM(price), 0) AS total
FROM
    Salesperson AS sp
    LEFT JOIN Customer AS c ON sp.salesperson_id = c.salesperson_id
    LEFT JOIN Sales AS s ON s.customer_id = c.customer_id
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
