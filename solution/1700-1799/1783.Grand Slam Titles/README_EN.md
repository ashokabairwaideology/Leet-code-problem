---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1783.Grand%20Slam%20Titles/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1783. Grand Slam Titles 🔒](https://leetcode.com/problems/grand-slam-titles)

[中文文档](/solution/1700-1799/1783.Grand%20Slam%20Titles/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Players</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| player_id      | int     |
| player_name    | varchar |
+----------------+---------+
player_id is the primary key (column with unique values) for this table.
Each row in this table contains the name and the ID of a tennis player.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Championships</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| year          | int     |
| Wimbledon     | int     |
| Fr_open       | int     |
| US_open       | int     |
| Au_open       | int     |
+---------------+---------+
year is the primary key (column with unique values) for this table.
Each row of this table contains the IDs of the players who won one each tennis tournament of the grand slam.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the number of grand slam tournaments won by each player. Do not include the players who did not win any tournament.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Players table:
+-----------+-------------+
| player_id | player_name |
+-----------+-------------+
| 1         | Nadal       |
| 2         | Federer     |
| 3         | Novak       |
+-----------+-------------+
Championships table:
+------+-----------+---------+---------+---------+
| year | Wimbledon | Fr_open | US_open | Au_open |
+------+-----------+---------+---------+---------+
| 2018 | 1         | 1       | 1       | 1       |
| 2019 | 1         | 1       | 2       | 2       |
| 2020 | 2         | 1       | 2       | 2       |
+------+-----------+---------+---------+---------+
<strong>Output:</strong> 
+-----------+-------------+-------------------+
| player_id | player_name | grand_slams_count |
+-----------+-------------+-------------------+
| 2         | Federer     | 5                 |
| 1         | Nadal       | 7                 |
+-----------+-------------+-------------------+
<strong>Explanation:</strong> 
Player 1 (Nadal) won 7 titles: Wimbledon (2018, 2019), Fr_open (2018, 2019, 2020), US_open (2018), and Au_open (2018).
Player 2 (Federer) won 5 titles: Wimbledon (2020), US_open (2019, 2020), and Au_open (2019, 2020).
Player 3 (Novak) did not win anything, we did not include them in the result table.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union All + Equi-Join + Group By

We can use `UNION ALL` to merge all player IDs who won Grand Slam titles into a table `T`, then use an equi-join `JOIN` to join `T` table with `Players` table on `player_id`, and finally use `GROUP BY` and `COUNT` to count the number of Grand Slam titles won by each player.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT Wimbledon AS player_id
        FROM Championships
        UNION ALL
        SELECT Fr_open AS player_id
        FROM Championships
        UNION ALL
        SELECT US_open AS player_id
        FROM Championships
        UNION ALL
        SELECT Au_open AS player_id
        FROM Championships
    )
SELECT player_id, player_name, COUNT(1) AS grand_slams_count
FROM
    T
    JOIN Players USING (player_id)
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    player_id,
    player_name,
    SUM(
        (
            CASE
                WHEN Wimbledon = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN Fr_open = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN US_open = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN Au_open = player_id THEN 1
                ELSE 0
            END
        )
    ) AS grand_slams_count
FROM
    Championships
    CROSS JOIN Players
GROUP BY player_id
HAVING grand_slams_count > 0;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
