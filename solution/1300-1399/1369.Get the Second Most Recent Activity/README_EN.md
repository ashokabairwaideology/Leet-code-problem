---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1369.Get%20the%20Second%20Most%20Recent%20Activity/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1369. Get the Second Most Recent Activity 🔒](https://leetcode.com/problems/get-the-second-most-recent-activity)

[中文文档](/solution/1300-1399/1369.Get%20the%20Second%20Most%20Recent%20Activity/README.md)

## Description

<!-- description:start -->

<p>Table: <code>UserActivity</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| username      | varchar |
| activity      | varchar |
| startDate     | Date    |
| endDate       | Date    |
+---------------+---------+
This table may contain duplicates rows.
This table contains information about the activity performed by each user in a period of time.
A person with username performed an activity from startDate to endDate.
</pre>

<p>&nbsp;</p>

<p>Write a solution to show the <strong>second most recent activity</strong> of each user.</p>

<p>If the user only has one activity, return that one. A user cannot perform more than one activity at the same time.</p>

<p>Return the result table in <strong>any</strong> order.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
UserActivity table:
+------------+--------------+-------------+-------------+
| username   | activity     | startDate   | endDate     |
+------------+--------------+-------------+-------------+
| Alice      | Travel       | 2020-02-12  | 2020-02-20  |
| Alice      | Dancing      | 2020-02-21  | 2020-02-23  |
| Alice      | Travel       | 2020-02-24  | 2020-02-28  |
| Bob        | Travel       | 2020-02-11  | 2020-02-18  |
+------------+--------------+-------------+-------------+
<strong>Output:</strong> 
+------------+--------------+-------------+-------------+
| username   | activity     | startDate   | endDate     |
+------------+--------------+-------------+-------------+
| Alice      | Dancing      | 2020-02-21  | 2020-02-23  |
| Bob        | Travel       | 2020-02-11  | 2020-02-18  |
+------------+--------------+-------------+-------------+
<strong>Explanation:</strong> 
The most recent activity of Alice is Travel from 2020-02-24 to 2020-02-28, before that she was dancing from 2020-02-21 to 2020-02-23.
Bob only has one record, we just take that one.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    username,
    activity,
    startdate,
    enddate
FROM
    (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY username
                ORDER BY startdate DESC
            ) AS rk,
            COUNT(username) OVER (PARTITION BY username) AS cnt
        FROM UserActivity
    ) AS a
WHERE a.rk = 2 OR a.cnt = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
