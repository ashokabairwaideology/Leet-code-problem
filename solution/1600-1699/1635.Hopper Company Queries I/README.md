---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1635.Hopper%20Company%20Queries%20I/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1635. Hopper 公司查询 I 🔒](https://leetcode.cn/problems/hopper-company-queries-i)

[English Version](/solution/1600-1699/1635.Hopper%20Company%20Queries%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Drivers</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| driver_id   | int     |
| join_date   | date    |
+-------------+---------+
driver_id 是该表的主键(具有唯一值的列)。
该表的每一行均包含驾驶员的ID以及他们加入Hopper公司的日期。
</pre>

<p>&nbsp;</p>

<p>表: <code>Rides</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| ride_id      | int     |
| user_id      | int     |
| requested_at | date    |
+--------------+---------+
ride_id 是该表的主键(具有唯一值的列)。
该表的每一行均包含行程ID(ride_id)，用户ID(user_id)以及该行程的日期(requested_at)。
该表中可能有一些不被接受的乘车请求。
</pre>

<p>&nbsp;</p>

<p>表: <code>AcceptedRides</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| ride_id       | int     |
| driver_id     | int     |
| ride_distance | int     |
| ride_duration | int     |
+---------------+---------+
ride_id 是该表的主键(具有唯一值的列)。
该表的每一行都包含已接受的行程信息。
表中的行程信息都在“<code>Rides</code>”表中存在。
</pre>

<p>&nbsp;</p>

<p>编写解决方案以报告 <strong>2020</strong> 年每个月的以下统计信息：</p>

<ul>
	<li>截至某月底，当前在Hopper公司工作的驾驶员数量（<code>active_drivers</code>）。</li>
	<li>该月接受的乘车次数（<code>accepted_rides</code>）。</li>
</ul>

<p>返回按<code>month</code> 升序排列的结果表，其中<code>month</code> 是月份的数字（一月是<code>1</code>，二月是<code>2</code>，依此类推）。</p>

<p>返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
表 Drivers:
+-----------+------------+
| driver_id | join_date  |
+-----------+------------+
| 10        | 2019-12-10 |
| 8         | 2020-1-13  |
| 5         | 2020-2-16  |
| 7         | 2020-3-8   |
| 4         | 2020-5-17  |
| 1         | 2020-10-24 |
| 6         | 2021-1-5   |
+-----------+------------+
表 Rides:
+---------+---------+--------------+
| ride_id | user_id | requested_at |
+---------+---------+--------------+
| 6       | 75      | 2019-12-9    |
| 1       | 54      | 2020-2-9     |
| 10      | 63      | 2020-3-4     |
| 19      | 39      | 2020-4-6     |
| 3       | 41      | 2020-6-3     |
| 13      | 52      | 2020-6-22    |
| 7       | 69      | 2020-7-16    |
| 17      | 70      | 2020-8-25    |
| 20      | 81      | 2020-11-2    |
| 5       | 57      | 2020-11-9    |
| 2       | 42      | 2020-12-9    |
| 11      | 68      | 2021-1-11    |
| 15      | 32      | 2021-1-17    |
| 12      | 11      | 2021-1-19    |
| 14      | 18      | 2021-1-27    |
+---------+---------+--------------+
表 AcceptedRides:
+---------+-----------+---------------+---------------+
| ride_id | driver_id | ride_distance | ride_duration |
+---------+-----------+---------------+---------------+
| 10      | 10        | 63            | 38            |
| 13      | 10        | 73            | 96            |
| 7       | 8         | 100           | 28            |
| 17      | 7         | 119           | 68            |
| 20      | 1         | 121           | 92            |
| 5       | 7         | 42            | 101           |
| 2       | 4         | 6             | 38            |
| 11      | 8         | 37            | 43            |
| 15      | 8         | 108           | 82            |
| 12      | 8         | 38            | 34            |
| 14      | 1         | 90            | 74            |
+---------+-----------+---------------+---------------+
<strong>输出：</strong>
+-------+----------------+----------------+
| month | active_drivers | accepted_rides |
+-------+----------------+----------------+
| 1     | 2              | 0              |
| 2     | 3              | 0              |
| 3     | 4              | 1              |
| 4     | 4              | 0              |
| 5     | 5              | 0              |
| 6     | 5              | 1              |
| 7     | 5              | 1              |
| 8     | 5              | 1              |
| 9     | 5              | 0              |
| 10    | 6              | 0              |
| 11    | 6              | 2              |
| 12    | 6              | 1              |
+-------+----------------+----------------+
<strong>解释：</strong>
截至1月底-&gt;两个活跃的驾驶员（10,8），没有被接受的行程。
截至2月底-&gt;三个活跃的驾驶员（10,8,5），没有被接受的行程。
截至3月底-&gt;四个活跃的驾驶员（10,8,5,7），一个被接受的行程（10）。
截至4月底-&gt;四个活跃的驾驶员（10,8,5,7），没有被接受的行程。
截至5月底-&gt;五个活跃的驾驶员（10,8,5,7,4），没有被接受的行程。
截至6月底-&gt;五个活跃的驾驶员（10,8,5,7,4），一个被接受的行程（13）。
截至7月底-&gt;五个活跃的驾驶员（10,8,5,7,4），一个被接受的行程（7）。
截至8月底-&gt;五个活跃的驾驶员（10,8,5,7,4），一位接受的行程（17）。
截至9月底-&gt;五个活跃的驾驶员（10,8,5,7,4），没有被接受的行程。
截至10月底-&gt;六个活跃的驾驶员（10,8,5,7,4,1），没有被接受的行程。
截至11月底-&gt;六个活跃的驾驶员（10,8,5,7,4,1），两个被接受的行程（20,5）。
截至12月底-&gt;六个活跃的驾驶员（10,8,5,7,4,1），一个被接受的行程（2）。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    recursive Months AS (
        SELECT
            1 AS month
        UNION ALL
        SELECT
            month + 1
        FROM Months
        WHERE month < 12
    ),
    Ride AS (
        SELECT MONTH(requested_at) AS month, COUNT(1) AS cnt
        FROM
            Rides AS r
            JOIN AcceptedRides AS a
                ON r.ride_id = a.ride_id AND YEAR(requested_at) = 2020
        GROUP BY month
    )
SELECT
    m.month,
    COUNT(driver_id) AS active_drivers,
    IFNULL(r.cnt, 0) AS accepted_rides
FROM
    Months AS m
    LEFT JOIN Drivers AS d
        ON (m.month >= MONTH(d.join_date) AND YEAR(d.join_date) = 2020)
        OR YEAR(d.join_date) < 2020
    LEFT JOIN Ride AS r ON m.month = r.month
GROUP BY month;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
