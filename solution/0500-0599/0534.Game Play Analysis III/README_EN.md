---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0534.Game%20Play%20Analysis%20III/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [534. Game Play Analysis III 🔒](https://leetcode.com/problems/game-play-analysis-iii)

[中文文档](/solution/0500-0599/0534.Game%20Play%20Analysis%20III/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Activity</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
(player_id, event_date) is the primary key (column with unique values) of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report for each player and date, how many games played <strong>so far</strong> by the player. That is, the total number of games played by the player until that date. Check the example for clarity.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 1         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+
<strong>Output:</strong> 
+-----------+------------+---------------------+
| player_id | event_date | games_played_so_far |
+-----------+------------+---------------------+
| 1         | 2016-03-01 | 5                   |
| 1         | 2016-05-02 | 11                  |
| 1         | 2017-06-25 | 12                  |
| 3         | 2016-03-02 | 0                   |
| 3         | 2018-07-03 | 5                   |
+-----------+------------+---------------------+
<strong>Explanation:</strong> 
For the player with id 1, 5 + 6 = 11 games played by 2016-05-02, and 5 + 6 + 1 = 12 games played by 2017-06-25.
For the player with id 3, 0 + 5 = 5 games played by 2018-07-03.
Note that for each player we only care about the days when the player logged in.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Window Function

We can use the window function `SUM() OVER()` to group by `player_id`, sort by `event_date`, and calculate the total number of games played by each user up to the current date.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    player_id,
    event_date,
    SUM(games_played) OVER (
        PARTITION BY player_id
        ORDER BY event_date
    ) AS games_played_so_far
FROM Activity;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Self-Join + Group By

We can also use a self-join to join the `Activity` table with itself on the condition of `t1.player_id = t2.player_id AND t1.event_date >= t2.event_date`, and then group by `t1.player_id` and `t1.event_date`, and calculate the cumulative sum of `t2.games_played`. This will give us the total number of games played by each user up to the current date.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    t1.player_id,
    t1.event_date,
    SUM(t2.games_played) AS games_played_so_far
FROM
    Activity AS t1,
    Activity AS t2
WHERE t1.player_id = t2.player_id AND t1.event_date >= t2.event_date
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    t1.player_id,
    t1.event_date,
    SUM(t2.games_played) AS games_played_so_far
FROM
    Activity AS t1
    CROSS JOIN Activity AS t2 ON t1.player_id = t2.player_id AND t1.event_date >= t2.event_date
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
