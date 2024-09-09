---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1747.Leetflex%20Banned%20Accounts/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1747. 应该被禁止的 Leetflex 账户 🔒](https://leetcode.cn/problems/leetflex-banned-accounts)

[English Version](/solution/1700-1799/1747.Leetflex%20Banned%20Accounts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>LogInfo</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| account_id  | int      |
| ip_address  | int      |
| login       | datetime |
| logout      | datetime |
+-------------+----------+
该表可能包含重复项。
该表包含有关Leetflex帐户的登录和注销日期的信息。 它还包含了该账户用于登录和注销的网络地址的信息。
题目确保每一个注销时间都在登录时间之后。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，查找那些应该被禁止的Leetflex帐户编号 <code>account_id</code> 。 如果某个帐户在某一时刻从两个不同的网络地址登录了，则这个帐户应该被禁止。</p>

<p>可以以 <strong>任何顺序 </strong>返回结果。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
LogInfo table:
+------------+------------+---------------------+---------------------+
| account_id | ip_address | login               | logout              |
+------------+------------+---------------------+---------------------+
| 1          | 1          | 2021-02-01 09:00:00 | 2021-02-01 09:30:00 |
| 1          | 2          | 2021-02-01 08:00:00 | 2021-02-01 11:30:00 |
| 2          | 6          | 2021-02-01 20:30:00 | 2021-02-01 22:00:00 |
| 2          | 7          | 2021-02-02 20:30:00 | 2021-02-02 22:00:00 |
| 3          | 9          | 2021-02-01 16:00:00 | 2021-02-01 16:59:59 |
| 3          | 13         | 2021-02-01 17:00:00 | 2021-02-01 17:59:59 |
| 4          | 10         | 2021-02-01 16:00:00 | 2021-02-01 17:00:00 |
| 4          | 11         | 2021-02-01 17:00:00 | 2021-02-01 17:59:59 |
+------------+------------+---------------------+---------------------+
<strong>输出：
</strong>+------------+
| account_id |
+------------+
| 1          |
| 4          |
+------------+
<strong>解释：</strong>
Account ID 1 --&gt; 该账户从 "2021-02-01 09:00:00" 到 "2021-02-01 09:30:00" 在两个不同的网络地址(1 and 2)上激活了。它应该被禁止.
Account ID 2 --&gt; 该账户在两个不同的网络地址 (6, 7) 激活了，但在不同的时间上.
Account ID 3 --&gt; 该账户在两个不同的网络地址 (9, 13) 激活了，虽然是同一天，但时间上没有交集.
Account ID 4 --&gt; 该账户从 "2021-02-01 17:00:00" 到 "2021-02-01 17:00:00" 在两个不同的网络地址 (10 and 11)上激活了。它应该被禁止.</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自连接

我们可以通过自连接的方式，找出每个账户在同一天内，从不同的网络地址登录的情况。连接的条件是：

-   账户编号相同
-   网络地址不同
-   一次登录的时间在另一次“登录-退出”的时间范围内

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT DISTINCT
    a.account_id
FROM
    LogInfo AS a
    JOIN LogInfo AS b
        ON a.account_id = b.account_id
        AND a.ip_address != b.ip_address
        AND a.login BETWEEN b.login AND b.logout;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
