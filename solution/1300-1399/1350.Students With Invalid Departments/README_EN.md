---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1350.Students%20With%20Invalid%20Departments/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1350. Students With Invalid Departments 🔒](https://leetcode.com/problems/students-with-invalid-departments)

[中文文档](/solution/1300-1399/1350.Students%20With%20Invalid%20Departments/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Departments</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
In SQL, id is the primary key of this table.
The table has information about the id of each department of a university.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Students</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
| department_id | int     |
+---------------+---------+
In SQL, id is the primary key of this table.
The table has information about the id of each student at a university and the id of the department he/she studies at.
</pre>

<p>&nbsp;</p>

<p>Find the id and the name of all students who are enrolled in departments that no longer exist.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Departments table:
+------+--------------------------+
| id   | name                     |
+------+--------------------------+
| 1    | Electrical Engineering   |
| 7    | Computer Engineering     |
| 13   | Bussiness Administration |
+------+--------------------------+
Students table:
+------+----------+---------------+
| id   | name     | department_id |
+------+----------+---------------+
| 23   | Alice    | 1             |
| 1    | Bob      | 7             |
| 5    | Jennifer | 13            |
| 2    | John     | 14            |
| 4    | Jasmine  | 77            |
| 3    | Steve    | 74            |
| 6    | Luis     | 1             |
| 8    | Jonathan | 7             |
| 7    | Daiana   | 33            |
| 11   | Madelynn | 1             |
+------+----------+---------------+
<strong>Output:</strong> 
+------+----------+
| id   | name     |
+------+----------+
| 2    | John     |
| 7    | Daiana   |
| 4    | Jasmine  |
| 3    | Steve    |
+------+----------+
<strong>Explanation:</strong> 
John, Daiana, Steve, and Jasmine are enrolled in departments 14, 33, 74, and 77 respectively. department 14, 33, 74, and 77 do not exist in the Departments table.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Subquery

We can directly use a subquery to find all students who are not in the `Departments` table.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT id, name
FROM Students
WHERE department_id NOT IN (SELECT id FROM Departments);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Left Join

We can also use a left join to join the `Students` table with the `Departments` table on the condition of `Students.department_id = Departments.id`, and then filter out the students whose `Departments.id` is `NULL`.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT s.id, s.name
FROM
    Students AS s
    LEFT JOIN Departments AS d ON s.department_id = d.id
WHERE d.id IS NULL;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
