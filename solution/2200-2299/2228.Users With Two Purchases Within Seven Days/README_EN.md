---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2228.Users%20With%20Two%20Purchases%20Within%20Seven%20Days/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2228. Users With Two Purchases Within Seven Days 🔒](https://leetcode.com/problems/users-with-two-purchases-within-seven-days)

[中文文档](/solution/2200-2299/2228.Users%20With%20Two%20Purchases%20Within%20Seven%20Days/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Purchases</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| purchase_id   | int  |
| user_id       | int  |
| purchase_date | date |
+---------------+------+
purchase_id contains unique values.
This table contains logs of the dates that users purchased from a certain retailer.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the IDs of the users that made any two purchases <strong>at most</strong> <code>7</code> days apart.</p>

<p>Return the result table ordered by <code>user_id</code>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Purchases table:
+-------------+---------+---------------+
| purchase_id | user_id | purchase_date |
+-------------+---------+---------------+
| 4           | 2       | 2022-03-13    |
| 1           | 5       | 2022-02-11    |
| 3           | 7       | 2022-06-19    |
| 6           | 2       | 2022-03-20    |
| 5           | 7       | 2022-06-19    |
| 2           | 2       | 2022-06-08    |
+-------------+---------+---------------+
<strong>Output:</strong> 
+---------+
| user_id |
+---------+
| 2       |
| 7       |
+---------+
<strong>Explanation:</strong> 
User 2 had two purchases on 2022-03-13 and 2022-03-20. Since the second purchase is within 7 days of the first purchase, we add their ID.
User 5 had only 1 purchase.
User 7 had two purchases on the same day so we add their ID.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            user_id,
            DATEDIFF(
                purchase_date,
                LAG(purchase_date, 1) OVER (
                    PARTITION BY user_id
                    ORDER BY purchase_date
                )
            ) AS d
        FROM Purchases
    )
SELECT DISTINCT user_id
FROM t
WHERE d <= 7
ORDER BY user_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
