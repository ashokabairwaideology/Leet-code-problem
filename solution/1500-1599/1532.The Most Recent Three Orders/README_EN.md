---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1532.The%20Most%20Recent%20Three%20Orders/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1532. The Most Recent Three Orders 🔒](https://leetcode.com/problems/the-most-recent-three-orders)

[中文文档](/solution/1500-1599/1532.The%20Most%20Recent%20Three%20Orders/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| name          | varchar |
+---------------+---------+
customer_id is the column with unique values for this table.
This table contains information about customers.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| customer_id   | int     |
| cost          | int     |
+---------------+---------+
order_id is the column with unique values for this table.
This table contains information about the orders made by customer_id.
Each customer has <strong>one order per day</strong>.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the most recent three orders of each user. If a user ordered less than three orders, return all of their orders.</p>

<p>Return the result table ordered by <code>customer_name</code> in <strong>ascending order</strong> and in case of a tie by the <code>customer_id</code> in <strong>ascending order</strong>. If there is still a tie, order them by <code>order_date</code> in <strong>descending order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Customers table:
+-------------+-----------+
| customer_id | name      |
+-------------+-----------+
| 1           | Winston   |
| 2           | Jonathan  |
| 3           | Annabelle |
| 4           | Marwan    |
| 5           | Khaled    |
+-------------+-----------+
Orders table:
+----------+------------+-------------+------+
| order_id | order_date | customer_id | cost |
+----------+------------+-------------+------+
| 1        | 2020-07-31 | 1           | 30   |
| 2        | 2020-07-30 | 2           | 40   |
| 3        | 2020-07-31 | 3           | 70   |
| 4        | 2020-07-29 | 4           | 100  |
| 5        | 2020-06-10 | 1           | 1010 |
| 6        | 2020-08-01 | 2           | 102  |
| 7        | 2020-08-01 | 3           | 111  |
| 8        | 2020-08-03 | 1           | 99   |
| 9        | 2020-08-07 | 2           | 32   |
| 10       | 2020-07-15 | 1           | 2    |
+----------+------------+-------------+------+
<strong>Output:</strong> 
+---------------+-------------+----------+------------+
| customer_name | customer_id | order_id | order_date |
+---------------+-------------+----------+------------+
| Annabelle     | 3           | 7        | 2020-08-01 |
| Annabelle     | 3           | 3        | 2020-07-31 |
| Jonathan      | 2           | 9        | 2020-08-07 |
| Jonathan      | 2           | 6        | 2020-08-01 |
| Jonathan      | 2           | 2        | 2020-07-30 |
| Marwan        | 4           | 4        | 2020-07-29 |
| Winston       | 1           | 8        | 2020-08-03 |
| Winston       | 1           | 1        | 2020-07-31 |
| Winston       | 1           | 10       | 2020-07-15 |
+---------------+-------------+----------+------------+
<strong>Explanation:</strong> 
Winston has 4 orders, we discard the order of &quot;2020-06-10&quot; because it is the oldest order.
Annabelle has only 2 orders, we return them.
Jonathan has exactly 3 orders.
Marwan ordered only one time.
We sort the result table by customer_name in ascending order, by customer_id in ascending order, and by order_date in descending order in case of a tie.
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you write a general solution for the most recent <code>n</code> orders?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Equi-Join + Window Function

We can use an equi-join to join the `Customers` table and the `Orders` table based on `customer_id`, and then use the window function `row_number()` to sort the orders for each customer by `order_date` in descending order and assign a row number to each order. Finally, we can filter out the orders with a row number less than or equal to $3$.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            ROW_NUMBER() OVER (
                PARTITION BY customer_id
                ORDER BY order_date DESC
            ) AS rk
        FROM
            Orders
            JOIN Customers USING (customer_id)
    )
SELECT name AS customer_name, customer_id, order_id, order_date
FROM T
WHERE rk <= 3
ORDER BY 1, 2, 4 DESC;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
