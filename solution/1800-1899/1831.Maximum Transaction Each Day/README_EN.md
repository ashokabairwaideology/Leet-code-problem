---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1831.Maximum%20Transaction%20Each%20Day/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1831. Maximum Transaction Each Day 🔒](https://leetcode.com/problems/maximum-transaction-each-day)

[中文文档](/solution/1800-1899/1831.Maximum%20Transaction%20Each%20Day/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Transactions</code></p>

<pre>
+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| transaction_id | int      |
| day            | datetime |
| amount         | int      |
+----------------+----------+
transaction_id is the column with unique values for this table.
Each row contains information about one transaction.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to report the IDs of the transactions with the <strong>maximum</strong> <code>amount</code> on their respective day. If in one day there are multiple such transactions, return all of them.</p>

<p>Return the result table <strong>ordered by</strong> <code>transaction_id</code> <strong> in ascending order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Transactions table:
+----------------+--------------------+--------+
| transaction_id | day                | amount |
+----------------+--------------------+--------+
| 8              | 2021-4-3 15:57:28  | 57     |
| 9              | 2021-4-28 08:47:25 | 21     |
| 1              | 2021-4-29 13:28:30 | 58     |
| 5              | 2021-4-28 16:39:59 | 40     |
| 6              | 2021-4-29 23:39:28 | 58     |
+----------------+--------------------+--------+
<strong>Output:</strong> 
+----------------+
| transaction_id |
+----------------+
| 1              |
| 5              |
| 6              |
| 8              |
+----------------+
<strong>Explanation:</strong> 
&quot;2021-4-3&quot;  --&gt; We have one transaction with ID 8, so we add 8 to the result table.
&quot;2021-4-28&quot; --&gt; We have two transactions with IDs 5 and 9. The transaction with ID 5 has an amount of 40, while the transaction with ID 9 has an amount of 21. We only include the transaction with ID 5 as it has the maximum amount this day.
&quot;2021-4-29&quot; --&gt; We have two transactions with IDs 1 and 6. Both transactions have the same amount of 58, so we include both in the result table.
We order the result table by transaction_id after collecting these IDs.
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it without using the <code>MAX()</code> function?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Window Function

We can use the window function `RANK()`, which assigns a rank to each transaction based on its amount in descending order, and then select the transactions with a rank of $1$.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            transaction_id,
            RANK() OVER (
                PARTITION BY DAY(day)
                ORDER BY amount DESC
            ) AS rk
        FROM Transactions
    )
SELECT transaction_id
FROM T
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
