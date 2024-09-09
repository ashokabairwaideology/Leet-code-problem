---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1777.Product%27s%20Price%20for%20Each%20Store/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1777. Product's Price for Each Store 🔒](https://leetcode.com/problems/products-price-for-each-store)

[中文文档](/solution/1700-1799/1777.Product%27s%20Price%20for%20Each%20Store/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Products</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| store       | enum    |
| price       | int     |
+-------------+---------+
In SQL, (product_id, store) is the primary key for this table.
store is a category of type (&#39;store1&#39;, &#39;store2&#39;, &#39;store3&#39;) where each represents the store this product is available at.
price is the price of the product at this store.
</pre>

<p>&nbsp;</p>

<p>Find the price of each product in each store.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Products table:
+-------------+--------+-------+
| product_id  | store  | price |
+-------------+--------+-------+
| 0           | store1 | 95    |
| 0           | store3 | 105   |
| 0           | store2 | 100   |
| 1           | store1 | 70    |
| 1           | store3 | 80    |
+-------------+--------+-------+
<strong>Output:</strong> 
+-------------+--------+--------+--------+
| product_id  | store1 | store2 | store3 |
+-------------+--------+--------+--------+
| 0           | 95     | 100    | 105    |
| 1           | 70     | null   | 80     |
+-------------+--------+--------+--------+
<strong>Explanation:</strong> 
Product 0 price&#39;s are 95 for store1, 100 for store2 and, 105 for store3.
Product 1 price&#39;s are 70 for store1, 80 for store3 and, it&#39;s not sold in store2.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    product_id,
    SUM(IF(store = 'store1', price, NULL)) AS store1,
    SUM(IF(store = 'store2', price, NULL)) AS store2,
    SUM(IF(store = 'store3', price, NULL)) AS store3
FROM Products
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
