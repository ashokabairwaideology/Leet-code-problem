---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1050.Actors%20and%20Directors%20Who%20Cooperated%20At%20Least%20Three%20Times/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1050. Actors and Directors Who Cooperated At Least Three Times](https://leetcode.com/problems/actors-and-directors-who-cooperated-at-least-three-times)

[中文文档](/solution/1000-1099/1050.Actors%20and%20Directors%20Who%20Cooperated%20At%20Least%20Three%20Times/README.md)

## Description

<!-- description:start -->

<p>Table: <code>ActorDirector</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| actor_id    | int     |
| director_id | int     |
| timestamp   | int     |
+-------------+---------+
timestamp is the primary key (column with unique values) for this table.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find all the pairs <code>(actor_id, director_id)</code> where the actor has cooperated with the director at least three times.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
ActorDirector table:
+-------------+-------------+-------------+
| actor_id    | director_id | timestamp   |
+-------------+-------------+-------------+
| 1           | 1           | 0           |
| 1           | 1           | 1           |
| 1           | 1           | 2           |
| 1           | 2           | 3           |
| 1           | 2           | 4           |
| 2           | 1           | 5           |
| 2           | 1           | 6           |
+-------------+-------------+-------------+
<strong>Output:</strong> 
+-------------+-------------+
| actor_id    | director_id |
+-------------+-------------+
| 1           | 1           |
+-------------+-------------+
<strong>Explanation:</strong> The only pair is (1, 1) where they cooperated exactly 3 times.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Group By + Having

We can use the `GROUP BY` statement to group the data by the `actor_id` and `director_id` fields, and then use the `HAVING` statement to filter out the `actor_id` and `director_id` that appear at least three times.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT actor_id, director_id
FROM ActorDirector
GROUP BY 1, 2
HAVING COUNT(1) >= 3;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
