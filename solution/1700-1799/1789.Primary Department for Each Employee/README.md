---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1789.Primary%20Department%20for%20Each%20Employee/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1789. 员工的直属部门](https://leetcode.cn/problems/primary-department-for-each-employee)

[English Version](/solution/1700-1799/1789.Primary%20Department%20for%20Each%20Employee/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Employee</code></p>

<pre>
+---------------+---------+
| Column Name   |  Type   |
+---------------+---------+
| employee_id   | int     |
| department_id | int     |
| primary_flag  | varchar |
+---------------+---------+
这张表的主键为 employee_id, department_id (具有唯一值的列的组合)
employee_id 是员工的ID
department_id 是部门的ID，表示员工与该部门有关系
primary_flag 是一个枚举类型，值分别为('Y', 'N'). 如果值为'Y',表示该部门是员工的直属部门。 如果值是'N',则否
</pre>

<p>&nbsp;</p>

<p>一个员工可以属于多个部门。当一个员工加入<strong>超过一个部门</strong>的时候，他需要决定哪个部门是他的直属部门。请注意，当员工只加入一个部门的时候，那这个部门将默认为他的直属部门，虽然表记录的值为<code>'N'</code>.</p>

<p>请编写解决方案，查出员工所属的直属部门。</p>

<p>返回结果 <strong>没有顺序要求</strong> 。</p>

<p>返回结果格式如下例子所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Employee table:
+-------------+---------------+--------------+
| employee_id | department_id | primary_flag |
+-------------+---------------+--------------+
| 1           | 1             | N            |
| 2           | 1             | Y            |
| 2           | 2             | N            |
| 3           | 3             | N            |
| 4           | 2             | N            |
| 4           | 3             | Y            |
| 4           | 4             | N            |
+-------------+---------------+--------------+
<strong>输出：</strong>
+-------------+---------------+
| employee_id | department_id |
+-------------+---------------+
| 1           | 1             |
| 2           | 1             |
| 3           | 3             |
| 4           | 3             |
+-------------+---------------+
<strong>解释：</strong>
- 员工 1 的直属部门是 1
- 员工 2 的直属部门是 1
- 员工 3 的直属部门是 3
- 员工 4 的直属部门是 3</pre>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：合并

我们可以查出所有已经有直属部门的员工，然后再查出所有只属于一个部门的员工，最后我们可以使用 `UNION` 合并两个结果集。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT employee_id, department_id
FROM Employee
WHERE primary_flag = 'Y'
UNION
SELECT employee_id, department_id
FROM Employee
GROUP BY 1
HAVING COUNT(1) = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
