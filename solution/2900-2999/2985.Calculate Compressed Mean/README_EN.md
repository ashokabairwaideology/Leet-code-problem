---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2985.Calculate%20Compressed%20Mean/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2985. Calculate Compressed Mean 🔒](https://leetcode.com/problems/calculate-compressed-mean)

[中文文档](/solution/2900-2999/2985.Calculate%20Compressed%20Mean/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Orders</code></p>

<pre>
+-------------------+------+
| Column Name       | Type |
+-------------------+------+
| order_id          | int  |
| item_count        | int  |
| order_occurrences | int  |
+-------------------+------+
order_id is column of unique values for this table.
This table contains order_id, item_count, and order_occurrences.
</pre>

<p>Write a solution to calculate the <strong>average</strong> number of items per order, rounded to <code>2</code> <strong>decimal places</strong>.</p>

<p>Return <em>the result table</em><em> in <strong>any</strong> order</em><em>.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Orders table:
+----------+------------+-------------------+
| order_id | item_count | order_occurrences | 
+----------+------------+-------------------+
| 10       | 1          | 500               | 
| 11       | 2          | 1000              |     
| 12       | 3          | 800               |  
| 13       | 4          | 1000              | 
+----------+------------+-------------------+
<strong>Output</strong>
+-------------------------+
| average_items_per_order | 
+-------------------------+
| 2.70                    |
+-------------------------+
<strong>Explanation</strong>
The calculation is as follows:
 - Total items: (1 * 500) + (2 * 1000) + (3 * 800) + (4 * 1000) = 8900 
 - Total orders: 500 + 1000 + 800 + 1000 = 3300 
 - Therefore, the average items per order is 8900 / 3300 = 2.70</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Summation

We use the `SUM` function to calculate the total quantity of products and the total number of orders, then divide the total quantity by the total number of orders to get the average. Finally, we use the `ROUND` function to round the result to two decimal places.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    ROUND(
        SUM(item_count * order_occurrences) / SUM(order_occurrences),
        2
    ) AS average_items_per_order
FROM Orders;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
