---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1917.Leetcodify%20Friends%20Recommendations/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1917. Leetcodify Friends Recommendations 🔒](https://leetcode.com/problems/leetcodify-friends-recommendations)

[中文文档](/solution/1900-1999/1917.Leetcodify%20Friends%20Recommendations/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Listens</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| song_id     | int     |
| day         | date    |
+-------------+---------+
This table may contain duplicates (In other words, there is no primary key for this table in SQL).
Each row of this table indicates that the user user_id listened to the song song_id on the day day.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Friendship</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user1_id      | int     |
| user2_id      | int     |
+---------------+---------+
In SQL,(user1_id, user2_id) is the primary key for this table.
Each row of this table indicates that the users user1_id and user2_id are friends.
Note that user1_id &lt; user2_id.
</pre>

<p>&nbsp;</p>

<p>Recommend friends to Leetcodify users. We recommend user <code>x</code> to user <code>y</code> if:</p>

<ul>
	<li>Users <code>x</code> and <code>y</code> are not friends, and</li>
	<li>Users <code>x</code> and <code>y</code> listened to the same three or more different songs <strong>on the same day</strong>.</li>
</ul>

<p>Note that friend recommendations are <strong>unidirectional</strong>, meaning if user <code>x</code> and user <code>y</code> should be recommended to each other, the result table should have both user <code>x</code> recommended to user <code>y</code> and user <code>y</code> recommended to user <code>x</code>. Also, note that the result table should not contain duplicates (i.e., user <code>y</code> should not be recommended to user <code>x</code> multiple times.).</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Listens table:
+---------+---------+------------+
| user_id | song_id | day        |
+---------+---------+------------+
| 1       | 10      | 2021-03-15 |
| 1       | 11      | 2021-03-15 |
| 1       | 12      | 2021-03-15 |
| 2       | 10      | 2021-03-15 |
| 2       | 11      | 2021-03-15 |
| 2       | 12      | 2021-03-15 |
| 3       | 10      | 2021-03-15 |
| 3       | 11      | 2021-03-15 |
| 3       | 12      | 2021-03-15 |
| 4       | 10      | 2021-03-15 |
| 4       | 11      | 2021-03-15 |
| 4       | 13      | 2021-03-15 |
| 5       | 10      | 2021-03-16 |
| 5       | 11      | 2021-03-16 |
| 5       | 12      | 2021-03-16 |
+---------+---------+------------+
Friendship table:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
+----------+----------+
<strong>Output:</strong> 
+---------+----------------+
| user_id | recommended_id |
+---------+----------------+
| 1       | 3              |
| 2       | 3              |
| 3       | 1              |
| 3       | 2              |
+---------+----------------+
<strong>Explanation:</strong> 
Users 1 and 2 listened to songs 10, 11, and 12 on the same day, but they are already friends.
Users 1 and 3 listened to songs 10, 11, and 12 on the same day. Since they are not friends, we recommend them to each other.
Users 1 and 4 did not listen to the same three songs.
Users 1 and 5 listened to songs 10, 11, and 12, but on different days.

Similarly, we can see that users 2 and 3 listened to songs 10, 11, and 12 on the same day and are not friends, so we recommend them to each other.
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
    T AS (
        SELECT user1_id, user2_id FROM Friendship
        UNION
        SELECT user2_id AS user1_id, user1_id AS user2_id FROM Friendship
    )
SELECT DISTINCT l1.user_id, l2.user_id AS recommended_id
FROM
    Listens AS l1,
    Listens AS l2
WHERE
    l1.day = l2.day
    AND l1.song_id = l2.song_id
    AND l1.user_id != l2.user_id
    AND NOT EXISTS (
        SELECT 1
        FROM T AS t
        WHERE l1.user_id = t.user1_id AND l2.user_id = t.user2_id
    )
GROUP BY l1.day, l1.user_id, l2.user_id
HAVING COUNT(DISTINCT l1.song_id) >= 3;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
