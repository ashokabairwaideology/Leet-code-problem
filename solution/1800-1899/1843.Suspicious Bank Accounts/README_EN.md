---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1843.Suspicious%20Bank%20Accounts/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1843. Suspicious Bank Accounts 🔒](https://leetcode.com/problems/suspicious-bank-accounts)

[中文文档](/solution/1800-1899/1843.Suspicious%20Bank%20Accounts/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Accounts</code></p>

<pre>
+----------------+------+
| Column Name    | Type |
+----------------+------+
| account_id     | int  |
| max_income     | int  |
+----------------+------+
account_id is the column with unique values for this table.
Each row contains information about the maximum monthly income for one bank account.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Transactions</code></p>

<pre>
+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| transaction_id | int      |
| account_id     | int      |
| type           | ENUM     |
| amount         | int      |
| day            | datetime |
+----------------+----------+
transaction_id is the column with unique values for this table.
Each row contains information about one transaction.
type is ENUM (category) type of (&#39;Creditor&#39;,&#39;Debtor&#39;) where &#39;Creditor&#39; means the user deposited money into their account and &#39;Debtor&#39; means the user withdrew money from their account.
amount is the amount of money deposited/withdrawn during the transaction.
</pre>

<p>&nbsp;</p>

<p>A bank account is <strong>suspicious</strong> if the <strong>total income</strong> exceeds the <code>max_income</code> for this account for <strong>two or more consecutive</strong> months. The <strong>total income</strong> of an account in some month is the sum of all its deposits in that month (i.e., transactions of the type <code>&#39;Creditor&#39;</code>).</p>

<p>Write a solution to report the IDs of all <strong>suspicious</strong> bank accounts.</p>

<p>Return the result table <strong>in any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Accounts table:
+------------+------------+
| account_id | max_income |
+------------+------------+
| 3          | 21000      |
| 4          | 10400      |
+------------+------------+
Transactions table:
+----------------+------------+----------+--------+---------------------+
| transaction_id | account_id | type     | amount | day                 |
+----------------+------------+----------+--------+---------------------+
| 2              | 3          | Creditor | 107100 | 2021-06-02 11:38:14 |
| 4              | 4          | Creditor | 10400  | 2021-06-20 12:39:18 |
| 11             | 4          | Debtor   | 58800  | 2021-07-23 12:41:55 |
| 1              | 4          | Creditor | 49300  | 2021-05-03 16:11:04 |
| 15             | 3          | Debtor   | 75500  | 2021-05-23 14:40:20 |
| 10             | 3          | Creditor | 102100 | 2021-06-15 10:37:16 |
| 14             | 4          | Creditor | 56300  | 2021-07-21 12:12:25 |
| 19             | 4          | Debtor   | 101100 | 2021-05-09 15:21:49 |
| 8              | 3          | Creditor | 64900  | 2021-07-26 15:09:56 |
| 7              | 3          | Creditor | 90900  | 2021-06-14 11:23:07 |
+----------------+------------+----------+--------+---------------------+
<strong>Output:</strong> 
+------------+
| account_id |
+------------+
| 3          |
+------------+
<strong>Explanation:</strong> 
For account 3:
- In 6-2021, the user had an income of 107100 + 102100 + 90900 = 300100.
- In 7-2021, the user had an income of 64900.
We can see that the income exceeded the max income of 21000 for two consecutive months, so we include 3 in the result table.

For account 4:
- In 5-2021, the user had an income of 49300.
- In 6-2021, the user had an income of 10400.
- In 7-2021, the user had an income of 56300.
We can see that the income exceeded the max income in May and July, but not in June. Since the account did not exceed the max income for two consecutive months, we do not include it in the result table.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT DISTINCT
            t.account_id,
            DATE_FORMAT(day, '%Y-%m-01') AS day,
            transaction_id AS tx,
            SUM(amount) OVER (
                PARTITION BY account_id, DATE_FORMAT(day, '%Y-%m-01')
            ) > max_income AS marked
        FROM
            Transactions AS t
            LEFT JOIN Accounts AS a ON t.account_id = a.account_id
        WHERE type = 'Creditor'
    )
SELECT DISTINCT s1.account_id
FROM
    S AS s1
    LEFT JOIN S AS s2 ON s1.account_id = s2.account_id AND TIMESTAMPDIFF(Month, s1.day, s2.day) = 1
WHERE s1.marked = 1 AND s2.marked = 1
ORDER BY s1.tx;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            account_id,
            DATE_FORMAT(day, '%Y%m') AS yearmonth,
            transaction_id AS tx
        FROM
            Transactions
            JOIN Accounts USING (account_id)
        WHERE type = 'Creditor'
        GROUP BY account_id, yearmonth
        HAVING SUM(amount) > AVG(max_income)
    )
SELECT DISTINCT account_id
FROM S
WHERE (account_id, PERIOD_ADD(yearmonth, 1)) IN (SELECT account_id, yearmonth FROM S)
ORDER BY tx;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
