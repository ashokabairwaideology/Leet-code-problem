---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2238.Number%20of%20Times%20a%20Driver%20Was%20a%20Passenger/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2238. Number of Times a Driver Was a Passenger 🔒](https://leetcode.com/problems/number-of-times-a-driver-was-a-passenger)

[中文文档](/solution/2200-2299/2238.Number%20of%20Times%20a%20Driver%20Was%20a%20Passenger/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Rides</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| ride_id      | int  |
| driver_id    | int  |
| passenger_id | int  |
+--------------+------+
ride_id is the column with unique values for this table.
Each row of this table contains the ID of the driver and the ID of the passenger that rode in ride_id.
Note that driver_id != passenger_id.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to report the ID of each driver and the number of times they were a passenger.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Rides table:
+---------+-----------+--------------+
| ride_id | driver_id | passenger_id |
+---------+-----------+--------------+
| 1       | 7         | 1            |
| 2       | 7         | 2            |
| 3       | 11        | 1            |
| 4       | 11        | 7            |
| 5       | 11        | 7            |
| 6       | 11        | 3            |
+---------+-----------+--------------+
<strong>Output:</strong> 
+-----------+-----+
| driver_id | cnt |
+-----------+-----+
| 7         | 2   |
| 11        | 0   |
+-----------+-----+
<strong>Explanation:</strong> 
There are two drivers in all the given rides: 7 and 11.
The driver with ID = 7 was a passenger two times.
The driver with ID = 11 was never a passenger.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH T AS (SELECT DISTINCT driver_id FROM Rides)
SELECT t.driver_id, COUNT(passenger_id) AS cnt
FROM
    T AS t
    LEFT JOIN Rides AS r ON t.driver_id = r.passenger_id
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
