---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0175.Combine%20Two%20Tables/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [175. 组合两个表](https://leetcode.cn/problems/combine-two-tables)

[English Version](/solution/0100-0199/0175.Combine%20Two%20Tables/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Person</code></p>

<pre>
+-------------+---------+
| 列名         | 类型     |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
personId 是该表的主键（具有唯一值的列）。
该表包含一些人的 ID 和他们的姓和名的信息。
</pre>

<p>&nbsp;</p>

<p>表: <code>Address</code></p>

<pre>
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
addressId 是该表的主键（具有唯一值的列）。
该表的每一行都包含一个 ID = PersonId 的人的城市和州的信息。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，报告 <code>Person</code> 表中每个人的姓、名、城市和州。如果 <code>personId</code> 的地址不在&nbsp;<code>Address</code>&nbsp;表中，则报告为&nbsp;<code>null</code>&nbsp;。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Person表:
+----------+----------+-----------+
| personId | lastName | firstName |
+----------+----------+-----------+
| 1        | Wang     | Allen     |
| 2        | Alice    | Bob       |
+----------+----------+-----------+
Address表:
+-----------+----------+---------------+------------+
| addressId | personId | city          | state      |
+-----------+----------+---------------+------------+
| 1         | 2        | New York City | New York   |
| 2         | 3        | Leetcode      | California |
+-----------+----------+---------------+------------+
<strong>输出:</strong> 
+-----------+----------+---------------+----------+
| firstName | lastName | city          | state    |
+-----------+----------+---------------+----------+
| Allen     | Wang     | Null          | Null     |
| Bob       | Alice    | New York City | New York |
+-----------+----------+---------------+----------+
<strong>解释:</strong> 
地址表中没有 personId = 1 的地址，所以它们的城市和州返回 null。
addressId = 1 包含了 personId = 2 的地址信息。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：左连接

我们可以使用左连接，将 `Person` 表左连接 `Address` 表，连接条件为 `Person.personId = Address.personId`，这样就可以得到每个人的姓、名、城市和州。如果 `personId` 的地址不在 `Address` 表中，则报告为空 `null`。

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def combine_two_tables(person: pd.DataFrame, address: pd.DataFrame) -> pd.DataFrame:
    return pd.merge(left=person, right=address, how="left", on="personId")[
        ["firstName", "lastName", "city", "state"]
    ]
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT firstName, lastName, city, state
FROM
    Person
    LEFT JOIN Address USING (personId);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
