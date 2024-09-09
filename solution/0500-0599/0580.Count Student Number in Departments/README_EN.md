---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0580.Count%20Student%20Number%20in%20Departments/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [580. Count Student Number in Departments 🔒](https://leetcode.com/problems/count-student-number-in-departments)

[中文文档](/solution/0500-0599/0580.Count%20Student%20Number%20in%20Departments/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Student</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| student_id   | int     |
| student_name | varchar |
| gender       | varchar |
| dept_id      | int     |
+--------------+---------+
student_id is the primary key (column with unique values) for this table.
dept_id is a foreign key (reference column) to dept_id in the Department tables.
Each row of this table indicates the name of a student, their gender, and the id of their department.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Department</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| dept_id     | int     |
| dept_name   | varchar |
+-------------+---------+
dept_id is the primary key (column with unique values) for this table.
Each row of this table contains the id and the name of a department.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the respective department name and number of students majoring in each department for all departments in the <code>Department</code> table (even ones with no current students).</p>

<p>Return the result table <strong>ordered</strong> by <code>student_number</code> <strong>in descending order</strong>. In case of a tie, order them by <code>dept_name</code> <strong>alphabetically</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Student table:
+------------+--------------+--------+---------+
| student_id | student_name | gender | dept_id |
+------------+--------------+--------+---------+
| 1          | Jack         | M      | 1       |
| 2          | Jane         | F      | 1       |
| 3          | Mark         | M      | 2       |
+------------+--------------+--------+---------+
Department table:
+---------+-------------+
| dept_id | dept_name   |
+---------+-------------+
| 1       | Engineering |
| 2       | Science     |
| 3       | Law         |
+---------+-------------+
<strong>Output:</strong> 
+-------------+----------------+
| dept_name   | student_number |
+-------------+----------------+
| Engineering | 2              |
| Science     | 1              |
| Law         | 0              |
+-------------+----------------+
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Left Join + Grouping

We can use a left join to join the `Department` table and the `Student` table on `dept_id`, and then group by `dept_id` to count the number of students in each department. Finally, we can sort the result by `student_number` in descending order and `dept_name` in ascending order.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT dept_name, COUNT(student_id) AS student_number
FROM
    Department
    LEFT JOIN Student USING (dept_id)
GROUP BY dept_id
ORDER BY 2 DESC, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
