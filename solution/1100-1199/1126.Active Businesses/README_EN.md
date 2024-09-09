---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1126.Active%20Businesses/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1126. Active Businesses 🔒](https://leetcode.com/problems/active-businesses)

[中文文档](/solution/1100-1199/1126.Active%20Businesses/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Events</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| business_id   | int     |
| event_type    | varchar |
| occurrences   | int     | 
+---------------+---------+
(business_id, event_type) is the primary key (combination of columns with unique values) of this table.
Each row in the table logs the info that an event of some type occurred at some business for a number of times.
</pre>

<p>The <strong>average activity</strong> for a particular <code>event_type</code> is the average <code>occurrences</code> across all companies that have this event.</p>

<p>An <strong>active business</strong> is a business that has <strong>more than one</strong> <code>event_type</code> such that their <code>occurrences</code> is <strong>strictly greater</strong> than the average activity for that event.</p>

<p>Write a solution to find all <strong>active businesses</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Events table:
+-------------+------------+-------------+
| business_id | event_type | occurrences |
+-------------+------------+-------------+
| 1           | reviews    | 7           |
| 3           | reviews    | 3           |
| 1           | ads        | 11          |
| 2           | ads        | 7           |
| 3           | ads        | 6           |
| 1           | page views | 3           |
| 2           | page views | 12          |
+-------------+------------+-------------+
<strong>Output:</strong> 
+-------------+
| business_id |
+-------------+
| 1           |
+-------------+
<strong>Explanation:</strong>  
The average activity for each event can be calculated as follows:
- &#39;reviews&#39;: (7+3)/2 = 5
- &#39;ads&#39;: (11+7+6)/3 = 8
- &#39;page views&#39;: (3+12)/2 = 7.5
The business with id=1 has 7 &#39;reviews&#39; events (more than 5) and 11 &#39;ads&#39; events (more than 8), so it is an active business.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT business_id
FROM
    EVENTS AS t1
    JOIN (
        SELECT
            event_type,
            AVG(occurences) AS occurences
        FROM EVENTS
        GROUP BY event_type
    ) AS t2
        ON t1.event_type = t2.event_type
WHERE t1.occurences > t2.occurences
GROUP BY business_id
HAVING COUNT(1) > 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            business_id,
            occurences > AVG(occurences) OVER (PARTITION BY event_type) AS mark
        FROM Events
    )
SELECT business_id
FROM T
WHERE mark = 1
GROUP BY 1
HAVING COUNT(1) > 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
