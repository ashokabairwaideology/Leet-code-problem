---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0175.Combine%20Two%20Tables/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [175. Combine Two Tables](https://leetcode.com/problems/combine-two-tables)

[中文文档](/solution/0100-0199/0175.Combine%20Two%20Tables/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| personId    | int     |
| lastName    | varchar |
| firstName   | varchar |
+-------------+---------+
personId is the primary key (column with unique values) for this table.
This table contains information about the ID of some persons and their first and last names.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Address</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| addressId   | int     |
| personId    | int     |
| city        | varchar |
| state       | varchar |
+-------------+---------+
addressId is the primary key (column with unique values) for this table.
Each row of this table contains information about the city and state of one person with ID = PersonId.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the first name, last name, city, and state of each person in the <code>Person</code> table. If the address of a <code>personId</code> is not present in the <code>Address</code> table, report <code>null</code> instead.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Person table:
+----------+----------+-----------+
| personId | lastName | firstName |
+----------+----------+-----------+
| 1        | Wang     | Allen     |
| 2        | Alice    | Bob       |
+----------+----------+-----------+
Address table:
+-----------+----------+---------------+------------+
| addressId | personId | city          | state      |
+-----------+----------+---------------+------------+
| 1         | 2        | New York City | New York   |
| 2         | 3        | Leetcode      | California |
+-----------+----------+---------------+------------+
<strong>Output:</strong> 
+-----------+----------+---------------+----------+
| firstName | lastName | city          | state    |
+-----------+----------+---------------+----------+
| Allen     | Wang     | Null          | Null     |
| Bob       | Alice    | New York City | New York |
+-----------+----------+---------------+----------+
<strong>Explanation:</strong> 
There is no address in the address table for the personId = 1 so we return null in their city and state.
addressId = 1 contains information about the address of personId = 2.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: LEFT JOIN

We can use a left join to join the `Person` table with the `Address` table on the condition `Person.personId = Address.personId`, which will give us the first name, last name, city, and state of each person. If the address of a `personId` is not in the `Address` table, it will be reported as `null`.

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
