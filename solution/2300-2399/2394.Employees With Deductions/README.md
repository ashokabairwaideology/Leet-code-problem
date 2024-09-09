---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2394.Employees%20With%20Deductions/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2394. 开除员工 🔒](https://leetcode.cn/problems/employees-with-deductions)

[English Version](/solution/2300-2399/2394.Employees%20With%20Deductions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Employees</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| employee_id  | int  |
| needed_hours | int  |
+--------------+------+
employee_id 是该表具有的唯一值的列。
每一行都包含员工的 id 和他们获得工资所需的最低工作时数。
</pre>

<p>&nbsp;</p>

<p>表: <code>Logs</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| employee_id | int      |
| in_time     | datetime |
| out_time    | datetime |
+-------------+----------+
(employee_id, in_time, out_time) 是该表的主键（具有唯一值的列的组合）。
该表的每一行都显示了员工的时间戳。in_time 是员工开始工作的时间，out_time 是员工结束工作的时间。
所有时间都在 2022 年 10 月。out_time 可以是 in_time 之后的一天，这意味着该员工在午夜之后工作。
</pre>

<p>&nbsp;</p>

<p>在公司里，每个员工每个月必须工作一定的小时数。员工在工作段中工作。员工工作的小时数可以通过员工在所有工作段中工作的分钟数的总和来计算。每个工作段的分钟数是向上取整的。</p>

<ul>
	<li>例如，如果员工在一个时间段中工作了 <code>51</code> 分 <code>2</code> 秒，我们就认为它是 <code>52</code> 分钟。</li>
</ul>

<p>编写解决方案来报告将被开除的员工的 id。换句话说，报告没有工作所需时间的员工的 id。</p>

<p data-group="1-1">以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Employees 表:
+-------------+--------------+
| employee_id | needed_hours |
+-------------+--------------+
| 1           | 20           |
| 2           | 12           |
| 3           | 2            |
+-------------+--------------+
Logs 表:
+-------------+---------------------+---------------------+
| employee_id | in_time             | out_time            |
+-------------+---------------------+---------------------+
| 1           | 2022-10-01 09:00:00 | 2022-10-01 17:00:00 |
| 1           | 2022-10-06 09:05:04 | 2022-10-06 17:09:03 |
| 1           | 2022-10-12 23:00:00 | 2022-10-13 03:00:01 |
| 2           | 2022-10-29 12:00:00 | 2022-10-29 23:58:58 |
+-------------+---------------------+---------------------+
<strong>输出:</strong> 
+-------------+
| employee_id |
+-------------+
| 2           |
| 3           |
+-------------+
<strong>解释:</strong> 
员工 1:
 - 参加了三个工作段:
    - 在 2022-10-01, 他工作了 8 个小时。
    - 在 2022-10-06, 他工作了 8 小时 4 分钟。
    - 在 2022-10-12, 他工作了 4 小时 1 分钟。请注意，他一直工作到午夜。
 - 员工 1 在各个时段总共工作了 20 小时5分钟，不被开除。
员工 2:
 - 参加了一个工作段:
    - 在 2022-10-29, 他工作了 11 小时 59 分钟。
 - 员工 2 没有工作足够的时长，将被开除。
员工 3:
 - 没有任何工作段。
 - 员工 3 没有工作足够的时长，将被开除。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            employee_id,
            SUM(ceiling(TIMESTAMPDIFF(second, in_time, out_time) / 60)) / 60 AS tot
        FROM Logs
        GROUP BY employee_id
    )
SELECT employee_id
FROM
    Employees
    LEFT JOIN T USING (employee_id)
WHERE IFNULL(tot, 0) < needed_hours;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
