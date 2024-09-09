---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1407.Top%20Travellers/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1407. Top Travellers](https://leetcode.com/problems/top-travellers)

[中文文档](/solution/1400-1499/1407.Top%20Travellers/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Users</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id is the column with unique values for this table.
name is the name of the user.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Rides</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| user_id       | int     |
| distance      | int     |
+---------------+---------+
id is the column with unique values for this table.
user_id is the id of the user who traveled the distance &quot;distance&quot;.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to report the distance traveled by each user.</p>

<p>Return the result table ordered by <code>travelled_distance</code> in <strong>descending order</strong>, if two or more users traveled the same distance, order them by their <code>name</code> in <strong>ascending order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Users table:
+------+-----------+
| id   | name      |
+------+-----------+
| 1    | Alice     |
| 2    | Bob       |
| 3    | Alex      |
| 4    | Donald    |
| 7    | Lee       |
| 13   | Jonathan  |
| 19   | Elvis     |
+------+-----------+
Rides table:
+------+----------+----------+
| id   | user_id  | distance |
+------+----------+----------+
| 1    | 1        | 120      |
| 2    | 2        | 317      |
| 3    | 3        | 222      |
| 4    | 7        | 100      |
| 5    | 13       | 312      |
| 6    | 19       | 50       |
| 7    | 7        | 120      |
| 8    | 19       | 400      |
| 9    | 7        | 230      |
+------+----------+----------+
<strong>Output:</strong> 
+----------+--------------------+
| name     | travelled_distance |
+----------+--------------------+
| Elvis    | 450                |
| Lee      | 450                |
| Bob      | 317                |
| Jonathan | 312                |
| Alex     | 222                |
| Alice    | 120                |
| Donald   | 0                  |
+----------+--------------------+
<strong>Explanation:</strong> 
Elvis and Lee traveled 450 miles, Elvis is the top traveler as his name is alphabetically smaller than Lee.
Bob, Jonathan, Alex, and Alice have only one ride and we just order them by the total distances of the ride.
Donald did not have any rides, the distance traveled by him is 0.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: LEFT JOIN + GROUP BY

We can use a left join to join the `Users` table with the `Rides` table on the condition of user id, and then group by user id to calculate the travel distance for each user. Note that if a user has no travel records, the travel distance is $0$.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT name, IFNULL(SUM(distance), 0) AS travelled_distance
FROM
    Users AS u
    LEFT JOIN Rides AS r ON u.id = r.user_id
GROUP BY u.id
ORDER BY 2 DESC, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
