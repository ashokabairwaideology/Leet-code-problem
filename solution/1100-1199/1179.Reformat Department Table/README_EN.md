---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1179.Reformat%20Department%20Table/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1179. Reformat Department Table](https://leetcode.com/problems/reformat-department-table)

[中文文档](/solution/1100-1199/1179.Reformat%20Department%20Table/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Department</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| revenue     | int     |
| month       | varchar |
+-------------+---------+
In SQL,(id, month) is the primary key of this table.
The table has information about the revenue of each department per month.
The month has values in [&quot;Jan&quot;,&quot;Feb&quot;,&quot;Mar&quot;,&quot;Apr&quot;,&quot;May&quot;,&quot;Jun&quot;,&quot;Jul&quot;,&quot;Aug&quot;,&quot;Sep&quot;,&quot;Oct&quot;,&quot;Nov&quot;,&quot;Dec&quot;].
</pre>

<p>&nbsp;</p>

<p>Reformat the table such that there is a department id column and a revenue column <strong>for each month</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Department table:
+------+---------+-------+
| id   | revenue | month |
+------+---------+-------+
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |
+------+---------+-------+
<strong>Output:</strong> 
+------+-------------+-------------+-------------+-----+-------------+
| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
+------+-------------+-------------+-------------+-----+-------------+
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |
+------+-------------+-------------+-------------+-----+-------------+
<strong>Explanation:</strong> The revenue from Apr to Dec is null.
Note that the result table has 13 columns (1 for the department id + 12 for the months).
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
    id,
    SUM(
        CASE month
            WHEN 'Jan' THEN revenue
        END
    ) AS Jan_Revenue,
    SUM(
        CASE month
            WHEN 'Feb' THEN revenue
        END
    ) AS Feb_Revenue,
    SUM(
        CASE month
            WHEN 'Mar' THEN revenue
        END
    ) AS Mar_Revenue,
    SUM(
        CASE month
            WHEN 'Apr' THEN revenue
        END
    ) AS Apr_Revenue,
    SUM(
        CASE month
            WHEN 'May' THEN revenue
        END
    ) AS May_Revenue,
    SUM(
        CASE month
            WHEN 'Jun' THEN revenue
        END
    ) AS Jun_Revenue,
    SUM(
        CASE month
            WHEN 'Jul' THEN revenue
        END
    ) AS Jul_Revenue,
    SUM(
        CASE month
            WHEN 'Aug' THEN revenue
        END
    ) AS Aug_Revenue,
    SUM(
        CASE month
            WHEN 'Sep' THEN revenue
        END
    ) AS Sep_Revenue,
    SUM(
        CASE month
            WHEN 'Oct' THEN revenue
        END
    ) AS Oct_Revenue,
    SUM(
        CASE month
            WHEN 'Nov' THEN revenue
        END
    ) AS Nov_Revenue,
    SUM(
        CASE month
            WHEN 'Dec' THEN revenue
        END
    ) AS Dec_Revenue
FROM Department
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
