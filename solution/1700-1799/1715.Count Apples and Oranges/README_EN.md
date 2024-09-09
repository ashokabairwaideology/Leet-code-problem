---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1715.Count%20Apples%20and%20Oranges/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1715. Count Apples and Oranges 🔒](https://leetcode.com/problems/count-apples-and-oranges)

[中文文档](/solution/1700-1799/1715.Count%20Apples%20and%20Oranges/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Boxes</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| box_id       | int  |
| chest_id     | int  |
| apple_count  | int  |
| orange_count | int  |
+--------------+------+
box_id is the column with unique values for this table.
chest_id is a foreign key (reference column) of the chests table.
This table contains information about the boxes and the number of oranges and apples they have. Each box may include a chest, which also can contain oranges and apples.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Chests</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| chest_id     | int  |
| apple_count  | int  |
| orange_count | int  |
+--------------+------+
chest_id is the column with unique values for this table.
This table contains information about the chests and the corresponding number of oranges and apples they have.
</pre>

<p>&nbsp;</p>

<p>Write a solution to count the number of apples and oranges in all the boxes. If a box contains a chest, you should also include the number of apples and oranges it has.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Boxes table:
+--------+----------+-------------+--------------+
| box_id | chest_id | apple_count | orange_count |
+--------+----------+-------------+--------------+
| 2      | null     | 6           | 15           |
| 18     | 14       | 4           | 15           |
| 19     | 3        | 8           | 4            |
| 12     | 2        | 19          | 20           |
| 20     | 6        | 12          | 9            |
| 8      | 6        | 9           | 9            |
| 3      | 14       | 16          | 7            |
+--------+----------+-------------+--------------+
Chests table:
+----------+-------------+--------------+
| chest_id | apple_count | orange_count |
+----------+-------------+--------------+
| 6        | 5           | 6            |
| 14       | 20          | 10           |
| 2        | 8           | 8            |
| 3        | 19          | 4            |
| 16       | 19          | 19           |
+----------+-------------+--------------+
<strong>Output:</strong> 
+-------------+--------------+
| apple_count | orange_count |
+-------------+--------------+
| 151         | 123          |
+-------------+--------------+
<strong>Explanation:</strong> 
box 2 has 6 apples and 15 oranges.
box 18 has 4 + 20 (from the chest) = 24 apples and 15 + 10 (from the chest) = 25 oranges.
box 19 has 8 + 19 (from the chest) = 27 apples and 4 + 4 (from the chest) = 8 oranges.
box 12 has 19 + 8 (from the chest) = 27 apples and 20 + 8 (from the chest) = 28 oranges.
box 20 has 12 + 5 (from the chest) = 17 apples and 9 + 6 (from the chest) = 15 oranges.
box 8 has 9 + 5 (from the chest) = 14 apples and 9 + 6 (from the chest) = 15 oranges.
box 3 has 16 + 20 (from the chest) = 36 apples and 7 + 10 (from the chest) = 17 oranges.
Total number of apples = 6 + 24 + 27 + 27 + 17 + 14 + 36 = 151
Total number of oranges = 15 + 25 + 8 + 28 + 15 + 15 + 17 = 123
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Left Join + Summation

We can perform a left join on the `Boxes` table and the `Chests` table based on `chest_id`, and then calculate the total number of apples and oranges respectively. Note that if a box does not contain any small boxes, then the corresponding `chest_id` will be `null`. In this case, we need to consider the number of apples and oranges in the small boxes within that box to be 0.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    SUM(IFNULL(b.apple_count, 0) + IFNULL(c.apple_count, 0)) AS apple_count,
    SUM(IFNULL(b.orange_count, 0) + IFNULL(c.orange_count, 0)) AS orange_count
FROM
    Boxes AS b
    LEFT JOIN Chests AS c USING (chest_id);
```

#### Pandas

```python
import pandas as pd


def count_apples_and_oranges(boxes: pd.DataFrame, chests: pd.DataFrame) -> pd.DataFrame:
    merged_df = boxes.merge(
        chests, on="chest_id", how="left", suffixes=("_box", "_chest")
    )
    apple_count = (
        merged_df["apple_count_box"].fillna(0)
        + merged_df["apple_count_chest"].fillna(0)
    ).sum()
    orange_count = (
        merged_df["orange_count_box"].fillna(0)
        + merged_df["orange_count_chest"].fillna(0)
    ).sum()
    return pd.DataFrame({"apple_count": [apple_count], "orange_count": [orange_count]})
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
