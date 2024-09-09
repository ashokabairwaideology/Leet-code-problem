---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0603.Consecutive%20Available%20Seats/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [603. Consecutive Available Seats 🔒](https://leetcode.com/problems/consecutive-available-seats)

[中文文档](/solution/0600-0699/0603.Consecutive%20Available%20Seats/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Cinema</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| seat_id     | int  |
| free        | bool |
+-------------+------+
seat_id is an auto-increment column for this table.
Each row of this table indicates whether the i<sup>th</sup> seat is free or not. 1 means free while 0 means occupied.
</pre>

<p>&nbsp;</p>

<p>Find all the consecutive available seats in the cinema.</p>

<p>Return the result table <strong>ordered</strong> by <code>seat_id</code> <strong>in ascending order</strong>.</p>

<p>The test cases are generated so that more than two seats are consecutively available.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Cinema table:
+---------+------+
| seat_id | free |
+---------+------+
| 1       | 1    |
| 2       | 0    |
| 3       | 1    |
| 4       | 1    |
| 5       | 1    |
+---------+------+
<strong>Output:</strong> 
+---------+
| seat_id |
+---------+
| 3       |
| 4       |
| 5       |
+---------+
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Self-Join

We can use a self-join to join the `Seat` table with itself, and then filter out the records where the `id` of the left seat is equal to the `id` of the right seat minus $1$, and where both seats are empty.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT DISTINCT a.seat_id
FROM
    Cinema AS a
    JOIN Cinema AS b ON ABS(a.seat_id - b.seat_id) = 1 AND a.free AND b.free
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Window Function

We can use the `LAG` and `LEAD` functions (or `SUM() OVER(ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING)`) to obtain the information of adjacent seats, and then filter out the consecutive empty seats and sort them in a unique way.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            seat_id,
            (free + (LAG(free) OVER (ORDER BY seat_id))) AS a,
            (free + (LEAD(free) OVER (ORDER BY seat_id))) AS b
        FROM Cinema
    )
SELECT seat_id
FROM T
WHERE a = 2 OR b = 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            SUM(free = 1) OVER (
                ORDER BY seat_id
                ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING
            ) AS cnt
        FROM Cinema
    )
SELECT seat_id
FROM T
WHERE free = 1 AND cnt > 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
