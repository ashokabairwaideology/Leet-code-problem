---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1173.Immediate%20Food%20Delivery%20I/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1173. Immediate Food Delivery I 🔒](https://leetcode.com/problems/immediate-food-delivery-i)

[中文文档](/solution/1100-1199/1173.Immediate%20Food%20Delivery%20I/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Delivery</code></p>

<pre>
+-----------------------------+---------+
| Column Name                 | Type    |
+-----------------------------+---------+
| delivery_id                 | int     |
| customer_id                 | int     |
| order_date                  | date    |
| customer_pref_delivery_date | date    |
+-----------------------------+---------+
delivery_id is the primary key (column with unique values) of this table.
The table holds information about food delivery to customers that make orders at some date and specify a preferred delivery date (on the same order date or after it).
</pre>

<p>&nbsp;</p>

<p>If the customer&#39;s preferred delivery date is the same as the order date, then the order is called <strong>immediate;</strong> otherwise, it is called <strong>scheduled</strong>.</p>

<p>Write a solution to find the percentage of immediate orders in the table, <strong>rounded to 2 decimal places</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Delivery table:
+-------------+-------------+------------+-----------------------------+
| delivery_id | customer_id | order_date | customer_pref_delivery_date |
+-------------+-------------+------------+-----------------------------+
| 1           | 1           | 2019-08-01 | 2019-08-02                  |
| 2           | 5           | 2019-08-02 | 2019-08-02                  |
| 3           | 1           | 2019-08-11 | 2019-08-11                  |
| 4           | 3           | 2019-08-24 | 2019-08-26                  |
| 5           | 4           | 2019-08-21 | 2019-08-22                  |
| 6           | 2           | 2019-08-11 | 2019-08-13                  |
+-------------+-------------+------------+-----------------------------+
<strong>Output:</strong> 
+----------------------+
| immediate_percentage |
+----------------------+
| 33.33                |
+----------------------+
<strong>Explanation:</strong> The orders with delivery id 2 and 3 are immediate while the others are scheduled.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sum

We can use the `sum` function to count the number of instant orders, and then divide it by the total number of orders. Since the problem requires a percentage, we need to multiply by 100. Finally, we can use the `round` function to keep two decimal places.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    ROUND(SUM(order_date = customer_pref_delivery_date) / COUNT(1) * 100, 2) AS immediate_percentage
FROM Delivery;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
